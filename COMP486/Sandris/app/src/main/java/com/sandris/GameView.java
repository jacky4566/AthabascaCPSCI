package com.sandris;

/*
Jackson Wiebe 3519635
22/09/2023

Class: GameView

Constructor:  GameView(Context context, int screenX, int screenY)
Create a new instance of the game with given screen dimensions


 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Calendar;
import java.util.Random;


public class GameView extends SurfaceView implements Runnable {
    //Touch and interface Objects
    static double phoneAngle, phoneZAngle; //In Radians
    private long startClickTime;
    private int touchXStart, touchYStart;
    //Logic Objects
    private GameViewListener gvListener;
    Thread gameThread = null;
    private int nextColor;
    //Drawing Objects
    private Canvas canvas;
    private int drawScaling; //How much do we scale our Tetromino
    private Rect playArea; //Defines play area in screen coordinates
    private Rect pauseButton; //If user clicks in this area
    private SurfaceHolder ourHolder;
    private Tetromino onScreenTetromino;
    private long logicTimer;
    //Physics
    private Sand sand;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

//Setup the Game area
        Rect potentialPlayArea = new Rect((int) (screenX * CONSTANTS.MARGIN_LEFT), (int) (screenY * CONSTANTS.MARGIN_TOP), (int) (screenX * (1.0 - CONSTANTS.MARGIN_RIGHT)), (int) (screenY * (1.0 - CONSTANTS.MARGIN_BOTTOM))); //Play area minus margins
        double potentialWidth = potentialPlayArea.width() - (potentialPlayArea.width() % CONSTANTS.GAME_WIDTH);     //Maximum possible game width considering game ratio
        double potentialHeight = potentialPlayArea.height() - (potentialPlayArea.height() % CONSTANTS.GAME_HEIGHT); //Maximum possible game height considering game ratio
        double aspectRatio = (double) CONSTANTS.GAME_WIDTH / (double) CONSTANTS.GAME_HEIGHT;

        int gameWidth;
        int gameHeight;

        if (potentialWidth / aspectRatio <= potentialHeight) {
            // The width is the limiting factor
            gameWidth = (int) potentialWidth;
            gameHeight = (int) (potentialWidth / aspectRatio);
        } else {
            // The height is the limiting factor
            gameWidth = (int) (potentialHeight * aspectRatio);
            ;
            gameHeight = (int) potentialHeight;
        }

        playArea = new Rect(
                potentialPlayArea.centerX() - (gameWidth / 2),
                potentialPlayArea.centerY() - (gameHeight / 2),
                potentialPlayArea.centerX() + (gameWidth / 2),
                potentialPlayArea.centerY() + (gameHeight / 2)
        );

        drawScaling = gameWidth / CONSTANTS.GAME_WIDTH;

        sand = new Sand(CONSTANTS.GAME_WIDTH, CONSTANTS.GAME_HEIGHT);

        ourHolder = getHolder();
    }

    @Override
    public void run() {
        while (GameActivity.gameDisplay) {
            if (GameActivity.gamePause) {
                drawPause();
            } else {
                logic();
                sand.sandphysics(sandSpeed());
                drawGame();
            }
        }
    }

    // Clean up our thread if the game is interrupted or the player quits
    public void pause() {
        GameActivity.gameDisplay = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // do something
        }
    }

    public void resume() {
        gameThread = new Thread(this);
        gameThread.start();
        GameActivity.gameDisplay = true;
    }

    private void logic() {
        //Does all game logic
        if (System.currentTimeMillis() <= logicTimer + 15) {
            return;
        }
        logicTimer = System.currentTimeMillis();
        if (onScreenTetromino == null) {
            //Create new Tetromino
            onScreenTetromino = new Tetromino(playArea.centerX(), 0 - (drawScaling * CONSTANTS.BLOCK_SCALE * 2), drawScaling * CONSTANTS.BLOCK_SCALE, nextColor);
            nextColor = new Random().nextInt(MainActivity.difficulty);
            onScreenTetromino.fastDrop = false;// Reset fast drop request
        } else {
            //Move tetra
            if (MainActivity.useMotion)
                onScreenTetromino.moveMotion(phoneAngle, phoneZAngle, (int) playspeed());
            else
                onScreenTetromino.moveDown((int) playspeed());
            checkTetrominoWalls(onScreenTetromino);
            //check for collision with sand or walls
            if (checkTetrominoBottom(onScreenTetromino) || checkTetrominoSand(onScreenTetromino)) {
                explodeTetromino(onScreenTetromino);
                onScreenTetromino = null;
            }
        }
    }

    private void drawGame() {
        if (ourHolder.getSurface().isValid()) {
            canvas = ourHolder.lockCanvas();

            //Draws all the background stuff
            drawBackground();

            //Draw Current Tetra
            if (onScreenTetromino != null) {
                onScreenTetromino.draw(canvas);
            }

            //Draw Sand
            drawSand();

            //Draw Score
            drawScoreboard();

            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawBackground() {
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
                playArea.top - drawScaling,
                playArea.right + drawScaling,
                playArea.bottom + drawScaling,
                border);
    }

    private void drawScoreboard() {
        Paint tetraColor = new Paint();
        //Draw next Tetra
        tetraColor.setColor(Tetromino.getColor(nextColor));
        int tetraScale = drawScaling * CONSTANTS.BLOCK_SCALE;
        Point location = new Point(playArea.left, (playArea.top / 2) - tetraScale);

        //draw block
        canvas.drawRect(location.x, location.y, location.x + tetraScale, location.y + tetraScale, tetraColor);
        //draw border
        Paint border = new Paint(tetraColor);
        border.setStyle(Paint.Style.STROKE);
        ColorFilter filter = new LightingColorFilter(0xFFFFFFFF, 0x00222222);
        border.setColorFilter(filter);
        int strokeWidth = tetraScale / 10;
        border.setStrokeWidth(strokeWidth);
        canvas.drawRect(
                location.x + strokeWidth,
                location.y + strokeWidth,
                location.x + tetraScale - strokeWidth,
                location.y + tetraScale - strokeWidth,
                border);

        //Write some text
        //Next block
        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(tetraScale / 2);
        canvas.drawText(" Next Colour", (int) location.x + tetraScale, (int) (location.y + tetraScale), textPaint);

        //Score
        textPaint.setTextSize(tetraScale);
        canvas.drawText(String.valueOf(GameActivity.gameScore), location.x, (int) (location.y + tetraScale + (textPaint.getTextSize())), textPaint);

        //Pause Button
        //Draw box
        Paint pausetextPaint = new Paint();
        Paint pausePainter = new Paint();
        pausePainter.setColor(Color.LTGRAY);
        pauseButton = new Rect(playArea.right - (playArea.width() / 3), playArea.top - (playArea.width() / 3), playArea.right, playArea.top - 50);
        canvas.drawRect(pauseButton, pausePainter);
        pausePainter.setShader(null);
        pausePainter.setStyle(Paint.Style.STROKE);
        pausePainter.setColor(Color.WHITE);
        pausePainter.setStrokeWidth(strokeWidth);
        canvas.drawRect(pauseButton, pausePainter);
        //Write Text
        pausetextPaint.setColor(Color.WHITE);
        pausetextPaint.setTextSize(pauseButton.height() / 4);
        canvas.drawText("PAUSE", pauseButton.centerX() - (pausetextPaint.measureText("PAUSE") / 2), pauseButton.centerY() + (textPaint.getTextSize() / 3), pausetextPaint);
    }

    private void drawPause() {
        if (ourHolder.getSurface().isValid()) {
            canvas = ourHolder.lockCanvas();

            //Pause
            //Draw box
            Paint pausePainter = new Paint();
            pausePainter.setColor(Color.LTGRAY);
            Rect pauseArea = new Rect(playArea.left, playArea.top, playArea.right, playArea.bottom);
            canvas.drawRect(pauseArea, pausePainter);
            pausePainter.setStyle(Paint.Style.STROKE);
            pausePainter.setColor(Color.WHITE);
            pausePainter.setStrokeWidth(10);
            canvas.drawRect(pauseArea, pausePainter);
            //Write Text
            pausePainter.setColor(Color.BLACK);
            pausePainter.setTextSize(pauseArea.width() / 10);
            canvas.drawText("PAUSE", playArea.centerX() - (pausePainter.measureText("PAUSE") / 2), playArea.centerY() + (pausePainter.getTextSize() / 3), pausePainter);

            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        //Called from andiord on touch events
        if (onScreenTetromino == null) {
            return true; //Nothing to move
        }
        int deltaX = (int) motionEvent.getX() - touchXStart;
        int deltaY = (int) motionEvent.getY() - touchYStart;
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                if (!MainActivity.useMotion) {
                    if (deltaY > 500) {
                        //Quick Drop
                        onScreenTetromino.fastDrop = true;
                    } else {
                        //Move Piece
                        touchXStart = (int) motionEvent.getX();
                        onScreenTetromino.location = new Point(onScreenTetromino.location.x + deltaX, onScreenTetromino.location.y);
                    }
                    checkTetrominoWalls(onScreenTetromino);
                    if (checkTetrominoSand(onScreenTetromino)) {
                        explodeTetromino(onScreenTetromino);
                        onScreenTetromino = null;
                    }
                }
                return true;
            }
            case MotionEvent.ACTION_DOWN: {
                startClickTime = Calendar.getInstance().getTimeInMillis();
                touchXStart = (int) motionEvent.getX();
                touchYStart = (int) motionEvent.getY();
            }
            case MotionEvent.ACTION_UP: {
                long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if (clickDuration < CONSTANTS.MAX_CLICK_DURATION &&
                        clickDuration > CONSTANTS.MIN_CLICK_DURATION) {
                    //click event has occurred within our restraints
                    if (pauseButton.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                        //User clicked the pause button
                        GameActivity.gamePause = !GameActivity.gamePause;
                        return true;
                    }
                    if (deltaX > 50 || deltaY > 50) //User moved too much, ignore rotation requests
                        return true;
                    onScreenTetromino.rotate();
                    new SoundEngine(SoundEffect.tetromino_rotate);
                    checkTetrominoWalls(onScreenTetromino); //This will catch out of bounds from rotation
                }
            }
        }
        return true;
    }

    public boolean checkTetrominoBottom(Tetromino checkPiece) {
        //checks for tetra outside play boundries returns true for collision
        int blockOffset = drawScaling * CONSTANTS.BLOCK_SCALE;
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {
                if (checkPiece.shapeGrid[x][y]) {
                    if ((checkPiece.location.y + (blockOffset * (y + 2)) + blockOffset - drawScaling) >= playArea.bottom) {
                        // Collision detected!
                        //Snap to floor
                        checkPiece.location = new Point(checkPiece.location.x, playArea.bottom - (blockOffset * (y + 3)));
                        return true;
                    }
                }
            }
        }
        // No collision
        return false;
    }

    public boolean checkTetrominoWalls(Tetromino checkPiece) {
        //Check if Tetromino has collision with walls. Snaps to walls
        //Check Left wall collision
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (checkPiece.shapeGrid[x][y]) {
                    //Left Wall
                    if (checkPiece.location.x + (drawScaling * CONSTANTS.BLOCK_SCALE * (x - 2)) < playArea.left) {
                        //Left wall collision detected!
                        //Snap to wall
                        checkPiece.location = new Point(playArea.left + (drawScaling * CONSTANTS.BLOCK_SCALE * (2 - x)), checkPiece.location.y);
                        return true;
                    }
                }
            }
        }

        //Check right wall collsion.
        //Needs to read Right to Left
        for (int x = 3; x >= 0; x--) {
            for (int y = 3; y >= 0; y--) {
                if (checkPiece.shapeGrid[x][y]) {
                    //Right Wall
                    if (checkPiece.location.x + (drawScaling * CONSTANTS.BLOCK_SCALE) + (drawScaling * CONSTANTS.BLOCK_SCALE * (x - 2)) > playArea.right) {
                        //Right Wall collision detected!
                        //Snap to wall
                        checkPiece.location = new Point(playArea.right + (drawScaling * CONSTANTS.BLOCK_SCALE * (1 - x)) - 1, checkPiece.location.y);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkTetrominoSand(Tetromino checkPiece) {
        //Check if Tetromino is below top level
        int checkPieceBottom = (checkPiece.location.y + (checkPiece.tetraScale * (6)) + checkPiece.tetraScale - playArea.top) / (drawScaling) - 2;
        if (checkPieceBottom < 0)
            return false;

        //Check for contact with existing sand
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (checkPiece.shapeGrid[x][y]) {
                    Rect tetraBoundBox = new Rect(
                            (checkPiece.location.x + (checkPiece.tetraScale * (x - 2)) - playArea.left) / (drawScaling),
                            (checkPiece.location.y + (checkPiece.tetraScale * (y + 2)) - playArea.top) / (drawScaling),
                            (checkPiece.location.x + (checkPiece.tetraScale * (x - 2)) + checkPiece.tetraScale - playArea.left) / (drawScaling),
                            (checkPiece.location.y + (checkPiece.tetraScale * (y + 2)) + checkPiece.tetraScale - playArea.top) / (drawScaling));
                    for (int sandx = 0; sandx < sand.sandArray.length; sandx++) {
                        for (int sandy = 0; sandy < sand.sandArray[0].length; sandy++) {
                            // Collision
                            if ((sand.sandArray[sandx][sandy] != 0) &&
                                    tetraBoundBox.contains(sandx, sandy)
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

    public void explodeTetromino(Tetromino tetra) {
        //Explodes the Tetra into sand particles
        new SoundEngine(SoundEffect.tetromino_drop); //Play sound effect
        //Check every Tetromino block in its 4x4 grid
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (tetra.shapeGrid[x][y]) {
                    //Add to sand grid
                    int sandStartX = (tetra.location.x + (tetra.tetraScale * (x - 2)) - playArea.left) / drawScaling;
                    int sandStartY = ((tetra.location.y + (tetra.tetraScale * (y + 2)) - playArea.top) / drawScaling) - 1;
                    //Each Tetromino block will decompose into a X x Y sand block of blockScale x blockScale
                    for (int drawx = sandStartX; drawx <= (sandStartX + CONSTANTS.BLOCK_SCALE); drawx++) {
                        for (int drawy = sandStartY; drawy <= (sandStartY + CONSTANTS.BLOCK_SCALE); drawy++) {
                            if (drawy < 0) {
                                //If we are trying to draw above the top of the play area its game over
                                gameOver();
                                return;
                            }
                            try {
                                sand.sandArray[drawx][drawy] = tetra.tetraColor;
                            } catch (ArrayIndexOutOfBoundsException e) {
                                //Attempted to draw out of bounds
                            }
                        }
                    }
                }
            }
        }
    }

    private double playspeed() {
        /*
        Returns a new speed for tetormino drops based on current score
        We use a polynomial expression that should increase speed based on score
        Score 0 = 6 speed
        Score 50000 = 10 speed
        Score 200000 = 40 speed
         */
        double poly1 = 6E-10 * Math.pow(GameActivity.gameScore, 2);
        double poly2 = 5E-5 * GameActivity.gameScore;
        double poly3 = CONSTANTS.MIN_PLAY_SPEED;
        double maxSpeed = Math.min(CONSTANTS.MAX_PLAY_SPEED, poly1 + poly2 + poly3); //Cap the polynomial

        return maxSpeed;
    }

    private double sandSpeed() {
        /*
        Since the drop speed increases with score we also need to move the sand faster to reflect the increase game pace
         */
        double playSpeedMultiplier = playspeed() / CONSTANTS.MIN_PLAY_SPEED; //Get a multiper from out current speed to the min speed
        return CONSTANTS.SAND_LOOP_TIMER_SLOW / playSpeedMultiplier; //Return new timer value from multiplier
    }

    private void drawSand() {
        //Draw the sand Array into the target canvas
        Paint paint = new Paint();
        for (int y = sand.sandArray[0].length - 1; y >= 0; y--) {
            for (int x = 0; x < sand.sandArray.length; x++) {
                if (sand.sandArray[x][y] != 0) {
                    paint.setColor(sand.sandArray[x][y]);
                    Rect sandPixel = new Rect(
                            (x * drawScaling) + playArea.left,
                            (y * drawScaling) + playArea.top,
                            (x * drawScaling) + drawScaling + playArea.left,
                            (y * drawScaling) + drawScaling + playArea.top);
                    canvas.drawRect(sandPixel, paint);
                }
            }
        }
    }

    public static void updateSensorData(SensorEvent event) {
        // Passes in new sensor data from the Activity
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            double ax = event.values[0];
            double ay = event.values[1];
            double az = event.values[2];

            // Normalize the data
            double norm_Of_g = Math.sqrt(ax * ax + ay * ay + az * az);
            ax = ax / norm_Of_g;
            ay = ay / norm_Of_g;
            az = az / norm_Of_g;

            phoneAngle = Math.acos(ax);     //Angle of phone in XY plane
            phoneZAngle = Math.acos(az);    //Angle of phone in ZY plane
        }
    }

    private void gameOver() {
        Log.d(this.getClass().getSimpleName(), "Game Over");
        if (gvListener != null) {
            gvListener.gameViewCallbackEnd();
        }
    }

    public interface GameViewListener {
        //Callback listener
        void gameViewCallbackEnd();
    }

    public void setGameViewListener(GameViewListener listener) {
        this.gvListener = listener;
    }
}
