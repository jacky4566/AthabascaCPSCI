package com.sandris;

import static android.app.PendingIntent.getActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Calendar;

public class GameView extends SurfaceView implements Runnable{
    static volatile boolean playing = false;
    private Context context;
    private Canvas canvas;
    Thread gameThread = null;
    Thread sandThread = null;
    private SurfaceHolder ourHolder;

    private Tetromino onScreenTetromino;

    private static float marginRight = 0.20F;
    private static float marginLeft = 0.05F;
    private static float marginTop = 0.05F;
    private static float marginBottom = 0.05F;

    private int drawScaling; //How much do we scale our Tetromino

    private  Rect playArea; //Defines play area in screen coordinates

    private Sand sand;

    private static final int MAX_CLICK_DURATION = 300;
    private static final int MIN_CLICK_DURATION = 30;
    private long startClickTime;


    public GameView(Context context, int screenX, int screenY){
        super(context);
        this.context = context;

//Setup the Game area
        Rect potentialPlayArea = new Rect((int)(screenX * marginLeft),(int)(screenY * marginTop),(int)(screenX * (1.0-marginRight)),(int)(screenY * (1.0-marginBottom))); //Play area minus margins
        double potentialWidth = potentialPlayArea.width() - (potentialPlayArea.width() % CONSTANTS.gameWidth);     //Maximum possible game width considering game ratio
        double potentialHeight = potentialPlayArea.height() - (potentialPlayArea.height() % CONSTANTS.gameHeight); //Maximum possible game height considering game ratio
        double aspectRatio =  (double) CONSTANTS.gameWidth / (double) CONSTANTS.gameHeight;

        int gameWidth;
        int gameHeight;

        if (potentialWidth / aspectRatio <= potentialHeight) {
            // The width is the limiting factor
            gameWidth = (int) (potentialPlayArea.width());
            gameHeight = (int) (potentialPlayArea.width() / aspectRatio);
        } else {
            // The height is the limiting factor
            gameHeight = (int) (potentialPlayArea.height());
            gameWidth = (int) (potentialPlayArea.height() * aspectRatio);
        }

        playArea = new Rect(
                potentialPlayArea.centerX() - (gameWidth/2),
                potentialPlayArea.centerY() - (gameHeight/2),
                potentialPlayArea.centerX() + (gameWidth/2),
                potentialPlayArea.centerY() + (gameHeight/2)
        );

        drawScaling = gameWidth/CONSTANTS.gameWidth;

        sand = new Sand(CONSTANTS.gameWidth * CONSTANTS.sandScale, CONSTANTS.gameHeight * CONSTANTS.sandScale);
        sandThread = new Thread(sand);
        sandThread.start();

        ourHolder = getHolder();
    }
    @Override
    public void run() {
        while (playing) {
            logic();
            draw();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        gameOver();
    }

    private void logic() {
        //Does all game logic
        if (onScreenTetromino == null) {
            //Create new Tetromino
            onScreenTetromino = new Tetromino(playArea.centerX(), playArea.top - (drawScaling * 2), drawScaling);
        } else{
            //Move tetra
            onScreenTetromino.moveDown();
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
    }


    // Clean up our thread if the game is interrupted or the player quits
    public void pause(){
        playing = false;
        try {
            gameThread.join();
        }
        catch (InterruptedException e) {
            // do something
        }
    }

    // Make a new thread and start it
    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (onScreenTetromino == null) {
            return true; //Nothing to move
        }
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:{
                startClickTime = Calendar.getInstance().getTimeInMillis();
            }
            case MotionEvent.ACTION_UP:{
                long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if(clickDuration < MAX_CLICK_DURATION &&
                        clickDuration > MIN_CLICK_DURATION ) {
                    //click event has occurred
                    onScreenTetromino.rotate();
                    checkTetrominoWalls(onScreenTetromino); //This will catch out of bounds from rotation
                }
            }
            case MotionEvent.ACTION_MOVE: {
                int newX =(int)motionEvent.getX();
                    //Check for wall collision with new point

                //Range check to avoid glitchs caused by clicking off play area
                if (newX < playArea.left)
                    newX = playArea.left;
                if (newX > playArea.right)
                    newX = playArea.right;

                onScreenTetromino.location = new Point(newX, onScreenTetromino.location.y);
                checkTetrominoWalls(onScreenTetromino);

                return true;
            }
        }

        return true;
    }

    public boolean checkTetrominoBottom(Tetromino checkPiece){
        //checks for tetra outside play boundries returns true for collision
        for (int x =0; x< 4; x++){
            for(int y = 3; y >= 0; y--) {
                if (checkPiece.shapeGrid[x][y]) {
                    if ((checkPiece.location.y+(drawScaling * (y+2))+drawScaling) > playArea.bottom) {
                        // Collision detected!
                        //Snap to floor
                        checkPiece.location = new Point(checkPiece.location.x, playArea.bottom - (drawScaling*(y+3)) - 1 );
                        Log.d(this.getClass().getSimpleName(),"Bottom Collision");
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
                    if (checkPiece.location.x + (drawScaling * (x - 2)) < playArea.left) {
                        //Left wall collision detected!
                        //Snap to wall
                        checkPiece.location = new Point(playArea.left + (drawScaling * (2- x)), checkPiece.location.y);
                        Log.d(this.getClass().getSimpleName(),"Wall Collision");
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
                    if (checkPiece.location.x + drawScaling + (drawScaling * (x - 2)) > playArea.right) {
                        //Right Wall collision detected!
                        //Snap to wall
                        checkPiece.location = new Point(playArea.right + (drawScaling * (1-x)) -1, checkPiece.location.y);
                        Log.d(this.getClass().getSimpleName(),"Wall Collision");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkTetrominoSand(Tetromino checkPiece){
        //Check if Tetromino is below top level
        int checkPieceBottom = (checkPiece.location.y + (checkPiece.tetraScale * (6)) +checkPiece.tetraScale- playArea.top ) / (drawScaling / CONSTANTS.sandScale) - 2;
        if (checkPieceBottom < sand.sandTopLevel)
            return false;
        //Check for contact with existing sand

        for (int x =0; x< 4; x++){
            for(int y = 0; y<4; y++) {
                if (checkPiece.shapeGrid[x][y]) {
                    Rect tetraBoundBox = new Rect(
                            (checkPiece.location.x + (checkPiece.tetraScale * (x-2)) - playArea.left) / (drawScaling / CONSTANTS.sandScale),
                            (checkPiece.location.y + (checkPiece.tetraScale * (y+2)) - playArea.top ) / (drawScaling / CONSTANTS.sandScale) - 2,
                            (checkPiece.location.x + (checkPiece.tetraScale * (x-2)) + checkPiece.tetraScale- playArea.left) / (drawScaling / CONSTANTS.sandScale),
                            (checkPiece.location.y + (checkPiece.tetraScale * (y+2)) +checkPiece.tetraScale- playArea.top ) / (drawScaling / CONSTANTS.sandScale) - 2);
                    for (int sandx = 0; sandx< sand.sandArray.length; sandx++){
                        for(int sandy = 0; sandy<sand.sandArray[0].length; sandy++) {
                            // Collision
                            if ((sand.sandArray[sandx][sandy] != null) &&
                                    tetraBoundBox.contains(sandx ,sandy)
                            ) {
                                Log.d(this.getClass().getSimpleName(), "Sand Collision");
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
        for (int x =0; x< 4; x++){
            for(int y = 0; y<4; y++) {
                if (tetra.shapeGrid[x][y]) {
                    //Add to sand grid
                    int sandX = (tetra.location.x + (tetra.tetraScale * (x-2)) - playArea.left) / (drawScaling / CONSTANTS.sandScale);
                    int sandY = (tetra.location.y + (tetra.tetraScale * (y+2)) - playArea.top ) / (drawScaling / CONSTANTS.sandScale) - 2;
                    for (int drawx = sandX;  drawx < (sandX + CONSTANTS.sandScale); drawx++) {
                        for (int drawy = sandY;  drawy < (sandY + CONSTANTS.sandScale); drawy++) {
                            if (drawy <0) {
                                GameView.playing = false;
                                return;
                            }
                            sand.sandArray[drawx][drawy] = tetra.type;
                            if (drawy<sand.sandTopLevel)
                                sand.sandTopLevel = drawy;
                        }
                    }
                }
            }
        }
        return;
    }

    public void drawSand(){
        //Draw the sand Array into the target canvas
        Paint paint = new Paint();
        int sandScale = drawScaling/CONSTANTS.sandScale;
        for (int x =0; x< sand.sandArray.length; x++){
            for(int y = 0; y<sand.sandArray[0].length; y++) {
                if (sand.sandArray[x][y] != null){
                    switch (sand.sandArray[x][y]){
                        case I:
                            paint.setColor(CONSTANTS.Tetra_Color_I); // Teal
                            break;
                        case J:
                            paint.setColor(CONSTANTS.Tetra_Color_J); // Dark Blue
                            break;
                        case L:
                            paint.setColor(CONSTANTS.Tetra_Color_L); // Dark Orange
                            break;
                        case O:
                            paint.setColor(CONSTANTS.Tetra_Color_O); // Yellow
                            break;
                        case S:
                            paint.setColor(CONSTANTS.Tetra_Color_S); // Red
                            break;
                        case Z:
                            paint.setColor(CONSTANTS.Tetra_Color_Z); // Green
                            break;
                        case T:
                            paint.setColor(CONSTANTS.Tetra_Color_T); // Purple
                            break;
                    }
                    Rect sandPixel = new Rect(
                            (x*sandScale)+playArea.left,
                            ((y+1)*sandScale)+playArea.top,
                            ((x+1)*sandScale)+sandScale+playArea.left,
                            ((y+1)*sandScale)+sandScale+playArea.top);
                    canvas.drawRect(sandPixel,paint);
                }
            }
        }
    }

    private void gameOver(){
        Log.d(this.getClass().getSimpleName(),"Game Over");
    }
}
