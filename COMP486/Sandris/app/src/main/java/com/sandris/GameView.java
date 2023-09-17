package com.sandris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Calendar;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {
    static double phoneAngle, phoneZAngle; //In Radians
    private GameViewListener gvListener;
    public static int score = 0;
    private Canvas canvas;
    Thread gameThread = null;
    private SurfaceHolder ourHolder;
    private Tetromino onScreenTetromino;
    private long logicTimer;
    private static float marginRight = 0.02F;
    private static float marginLeft = 0.02F;
    private static float marginTop = 0.15F;
    private static float marginBottom = 0.00F;
    private int drawScaling; //How much do we scale our Tetromino
    private  Rect playArea; //Defines play area in screen coordinates
    private Sand sand;
    private static final int MAX_CLICK_DURATION = 250;
    private static final int MIN_CLICK_DURATION = 25;
    private long startClickTime;
    private int touchXStart;
    private int nextColor;

    public GameView(Context context, int screenX, int screenY){
        super(context);

//Setup the Game area
        Rect potentialPlayArea = new Rect((int)(screenX * marginLeft),(int)(screenY * marginTop),(int)(screenX * (1.0-marginRight)),(int)(screenY * (1.0-marginBottom))); //Play area minus margins
        double potentialWidth = potentialPlayArea.width() - (potentialPlayArea.width() % CONSTANTS.gameWidth);     //Maximum possible game width considering game ratio
        double potentialHeight = potentialPlayArea.height() - (potentialPlayArea.height() % CONSTANTS.gameHeight); //Maximum possible game height considering game ratio
        double aspectRatio =  (double) CONSTANTS.gameWidth / (double) CONSTANTS.gameHeight;

        int gameWidth;
        int gameHeight;

        if (potentialWidth / aspectRatio <= potentialHeight) {
            // The width is the limiting factor
            gameWidth = (int) potentialWidth;
            gameHeight = (int) (potentialWidth / aspectRatio);
        } else {
            // The height is the limiting factor
            gameWidth = (int) (potentialHeight * aspectRatio);;
            gameHeight = (int) potentialHeight;
        }

        playArea = new Rect(
                potentialPlayArea.centerX() - (gameWidth/2),
                potentialPlayArea.centerY() - (gameHeight/2),
                potentialPlayArea.centerX() + (gameWidth/2),
                potentialPlayArea.centerY() + (gameHeight/2)
        );

        drawScaling = gameWidth/CONSTANTS.gameWidth;

        sand = new Sand(CONSTANTS.gameWidth, CONSTANTS.gameHeight);

        ourHolder = getHolder();
    }
    @Override
    public void run() {
        while (GameActivity.playing) {
            logic();
            sand.sandphysics();
            draw();
        }
        gameOver();
    }

    private void logic() {
        //Does all game logic
        if (System.currentTimeMillis() <= logicTimer + 15){
            return;
        }
        logicTimer = System.currentTimeMillis();
        if (onScreenTetromino == null) {
            //Create new Tetromino
            onScreenTetromino = new Tetromino(playArea.centerX(), 0 - (drawScaling * CONSTANTS.blockScale * 2), drawScaling * CONSTANTS.blockScale, nextColor);
            Random rand = new Random();
            nextColor = rand.nextInt(MainActivity.difficulty);
        } else{
            //Move tetra
            if (MainActivity.useMotion)
                onScreenTetromino.moveMotion(phoneAngle, phoneZAngle, playspeed());
            else
                onScreenTetromino.moveDown(playspeed());
            checkTetrominoWalls(onScreenTetromino);
            //check for collision with sand or walls
            if (checkTetrominoBottom(onScreenTetromino) || checkTetrominoSand(onScreenTetromino)){
                explodeTetromino(onScreenTetromino);
                onScreenTetromino = null;
            }
        }
    }

    private void draw() {
        if (ourHolder.getSurface().isValid()) {
            canvas = ourHolder.lockCanvas();

            //Draws all the background stuff
            drawBackground();

            //Draw Current Tetra
            if (onScreenTetromino != null){
                onScreenTetromino.draw(canvas);
            }

            //Draw Sand
            drawSand();

            //Draw Score
            drawScoreboard();

            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawBackground(){
        Paint paint = new Paint();
        paint.setColor(Color.BLACK); // Set the color of the pixel
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint); //Fill background

        //Draw Play area
        paint.setColor(Color.DKGRAY); // Set the color of the pixel
        canvas.drawRect(playArea, paint);

        Paint border = new Paint();
        border.setColor(Color.WHITE);
        border.setStyle(Paint.Style.STROKE);
        border.setStrokeWidth(drawScaling);
        canvas.drawRect(
                playArea.left - drawScaling,
                playArea.top -  drawScaling,
                playArea.right +  drawScaling,
                playArea.bottom +  drawScaling,
                border);
    }

    private void drawScoreboard(){
        Paint tetraColor = new Paint();
        tetraColor.setColor(Tetromino.getColor(nextColor));
        int tetraScale =  drawScaling * CONSTANTS.blockScale;
        Point location = new Point(playArea.left,(playArea.top / 2));

        //draw block
        canvas.drawRect(location.x , location.y , location.x + tetraScale, location.y + tetraScale, tetraColor);
        //draw border
        Paint border = new Paint(tetraColor);
        border.setStyle(Paint.Style.STROKE);
        ColorFilter filter = new LightingColorFilter(0xFFFFFFFF, 0x00222222);
        border.setColorFilter(filter);
        int strokeWidth = tetraScale / 10;
        border.setStrokeWidth(strokeWidth);
        canvas.drawRect(
                location.x + strokeWidth,
                location.y +  strokeWidth,
                location.x +  tetraScale-strokeWidth,
                location.y +  tetraScale -strokeWidth,
                border);

        //Write some text
        //Next block
        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(tetraScale/2);
        canvas.drawText("Next Colour", (int)(location.x + ((float)tetraScale * 1.2)), location.y+tetraScale, textPaint);

        //Score
        canvas.drawText(String.valueOf(GameView.score), (int)(playArea.right - ((float)tetraScale * 1.2)), location.y+tetraScale, textPaint);

    }
    // Clean up our thread if the game is interrupted or the player quits
    public void pause(){
        GameActivity.playing = false;
        try {
            gameThread.join();
        }
        catch (InterruptedException e) {
            // do something
        }
    }

    // Make a new thread and start it
    public void resume() {
        score = 0;
        GameActivity.playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (onScreenTetromino == null) {
            return true; //Nothing to move
        }
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                if (!MainActivity.useMotion) {
                    int deltaX = (int) motionEvent.getX() - touchXStart;
                    touchXStart = (int) motionEvent.getX();
                    onScreenTetromino.location = new Point(onScreenTetromino.location.x + deltaX, onScreenTetromino.location.y);
                    checkTetrominoWalls(onScreenTetromino);
                }
                return true;
            }
            case MotionEvent.ACTION_DOWN:{
                startClickTime = Calendar.getInstance().getTimeInMillis();
                touchXStart = (int)motionEvent.getX();
            }
            case MotionEvent.ACTION_UP:{
                long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if(clickDuration < MAX_CLICK_DURATION &&
                        clickDuration > MIN_CLICK_DURATION ) {
                    //click event has occurred
                    onScreenTetromino.rotate();
                    new SoundEngine(SoundEffect.tetromino_rotate);
                    checkTetrominoWalls(onScreenTetromino); //This will catch out of bounds from rotation
                }
            }
        }
        return true;
    }

    public boolean checkTetrominoBottom(Tetromino checkPiece){
        //checks for tetra outside play boundries returns true for collision
        int blockOffset = drawScaling * CONSTANTS.blockScale;
        for (int x =0; x< 4; x++){
            for(int y = 3; y >= 0; y--) {
                if (checkPiece.shapeGrid[x][y]) {
                    if ((checkPiece.location.y + (blockOffset * (y+2))+blockOffset-drawScaling ) >= playArea.bottom) {
                        // Collision detected!
                        //Snap to floor
                        checkPiece.location = new Point(checkPiece.location.x, playArea.bottom - (blockOffset * (y+3)));
                        return true;
                    }
                }
            }
        }
        // No collision
        return false;
    }

    public boolean checkTetrominoWalls(Tetromino checkPiece){
        //Check if Tetromino has collision with walls. Snaps to walls
        //Check Left wall collision
        for (int x =0; x< 4; x++){
            for(int y = 0; y<4; y++) {
                if (checkPiece.shapeGrid[x][y]) {
                    //Left Wall
                    if (checkPiece.location.x + (drawScaling * CONSTANTS.blockScale * (x - 2)) < playArea.left) {
                        //Left wall collision detected!
                        //Snap to wall
                        checkPiece.location = new Point(playArea.left + (drawScaling  * CONSTANTS.blockScale * (2- x)), checkPiece.location.y);
                        return true;

                    }
                }
            }
        }

        //Check right wall collsion.
        //Needs to read Right to Left
        for (int x =3; x >= 0; x--){
            for(int y = 3; y >= 0; y--) {
                if (checkPiece.shapeGrid[x][y]) {
                    //Right Wall
                    if (checkPiece.location.x + (drawScaling * CONSTANTS.blockScale) + (drawScaling * CONSTANTS.blockScale * (x - 2)) > playArea.right) {
                        //Right Wall collision detected!
                        //Snap to wall
                        checkPiece.location = new Point(playArea.right + (drawScaling * CONSTANTS.blockScale * (1-x)) -1, checkPiece.location.y);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkTetrominoSand(Tetromino checkPiece){
        //Check if Tetromino is below top level
        int checkPieceBottom = (checkPiece.location.y + (checkPiece.tetraScale * (6)) +checkPiece.tetraScale- playArea.top ) / (drawScaling) - 2;
        if (checkPieceBottom < 0)
            return false;

        //Check for contact with existing sand
        for (int x =0; x< 4; x++){
            for(int y = 0; y<4; y++) {
                if (checkPiece.shapeGrid[x][y]) {
                    Rect tetraBoundBox = new Rect(
                            (checkPiece.location.x + (checkPiece.tetraScale * (x-2)) - playArea.left) / (drawScaling ),
                            (checkPiece.location.y + (checkPiece.tetraScale * (y+2)) - playArea.top ) / (drawScaling ) ,
                            (checkPiece.location.x + (checkPiece.tetraScale * (x-2)) + checkPiece.tetraScale- playArea.left) / (drawScaling ),
                            (checkPiece.location.y + (checkPiece.tetraScale * (y+2)) +checkPiece.tetraScale- playArea.top ) / (drawScaling ) );
                    for (int sandx = 0; sandx< sand.sandArray.length; sandx++){
                        for(int sandy = 0; sandy<sand.sandArray[0].length; sandy++) {
                            // Collision
                            if ((sand.sandArray[sandx][sandy] != 0) &&
                                    tetraBoundBox.contains(sandx ,sandy)
                            ) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        // No collision
        return false;
    }

    public void explodeTetromino(Tetromino tetra){
        //Explodes the Tetra into sand particles
        new SoundEngine(SoundEffect.tetromino_drop);
        for (int x =0; x< 4; x++){
            for(int y = 0; y<4; y++) {
                if (tetra.shapeGrid[x][y]) {
                    //Add to sand grid
                    int sandX = (tetra.location.x + (tetra.tetraScale * (x - 2)) - playArea.left) / drawScaling;
                    int sandY = ((tetra.location.y + (tetra.tetraScale * (y + 2)) - playArea.top ) / drawScaling)-1;
                    for (int drawx = sandX;  drawx < (sandX + CONSTANTS.blockScale); drawx++) {
                        for (int drawy = sandY;  drawy < (sandY + CONSTANTS.blockScale); drawy++) {
                            if (drawy < 0) {
                                GameActivity.playing = false;
                                return;
                            }
                            sand.sandArray[drawx][drawy] = tetra.tetraColor;
                        }
                    }
                }
            }
        }
    }

    private int playspeed(){
        final double speedMultiplier = 8;
        if (score > 5000){
            return (int)(speedMultiplier * 1.5);
        }else if (score > 10000)
            return (int)(speedMultiplier * 2);
        else if (score > 20000)
            return (int)(speedMultiplier * 3);
        else if (score > 50000)
            return (int)(speedMultiplier * 4);
        else if (score > 100000)
            return (int)(speedMultiplier * 5);
        return (int)speedMultiplier;
    }

    public void drawSand(){
        //Draw the sand Array into the target canvas
        Paint paint = new Paint();
        for (int x =0; x< sand.sandArray.length; x++){
            for(int y = sand.sandArray[0].length - 1; y>=0; y--) {
                if (sand.sandArray[x][y] != 0){
                    paint.setColor(sand.sandArray[x][y]);
                    Rect sandPixel = new Rect(
                            (x*drawScaling)+playArea.left,
                            (y*drawScaling)+playArea.top,
                            (x*drawScaling)+drawScaling+playArea.left,
                            (y*drawScaling)+drawScaling+playArea.top);
                    canvas.drawRect(sandPixel,paint);
                }
            }
        }
    }

    public static void updateSensorData(SensorEvent event){
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            double ax=event.values[0];
            double ay=event.values[1];
            double az=event.values[2];

            double norm_Of_g = Math.sqrt(ax * ax + ay * ay + az * az);

            ax = ax / norm_Of_g;
            ay = ay / norm_Of_g;
            az = az / norm_Of_g;

            phoneAngle = Math.acos(ax);
            phoneZAngle = Math.acos(az);
        }
    }

    private void gameOver(){
        Log.d(this.getClass().getSimpleName(),"Game Over");
        if (gvListener != null) {
            gvListener.gameViewCallback(score);
        }
    }

    public interface GameViewListener {
        void gameViewCallback(int score);
    }

    public void setGameViewListener(GameViewListener listener) {
        this.gvListener = listener;
    }
}
