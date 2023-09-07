package com.sandris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Calendar;

public class GameView extends SurfaceView implements Runnable{

    //Game area 10x20
    volatile boolean playing = false;
    private Context context;
    private Canvas canvas;
    Thread gameThread = null;
    Thread sandThread = null;
    private SurfaceHolder ourHolder;

     int scaling;

    private Tetra onScreenTetra;

    private static float marginRight = 0.20F;
    private static float marginLeft = 0.05F;
    private static float marginTop = 0.05F;
    private static float marginBottom = 0.05F;

    private int screenX;
    private int screenY;

    private  Rect playArea; //Defines play area in screen coordinates

    private Sand sand;

    private static final int MAX_CLICK_DURATION = 300;
    private static final int MIN_CLICK_DURATION = 30;
    private long startClickTime;


    public GameView(Context context, int x, int y ){
        super(context);
        this.context = context;

        screenX = x;
        screenY = y;

        Rect potentialPlayArea = new Rect((int)(screenX * marginLeft),(int)(screenY * marginTop),(int)(screenX * (1.0-marginRight)),(int)(screenY * (1.0-marginBottom)));
        int potentialWidth = potentialPlayArea.width() - (potentialPlayArea.width() % 10);
        int potentialHeight = (potentialPlayArea.height() - (potentialPlayArea.height() % 10))/2; //Game ratio is 1:2
        scaling = Math.min(potentialWidth,potentialHeight) / 10;
        playArea = new Rect(potentialPlayArea.centerX() - (scaling*5),potentialPlayArea.centerY() - (scaling*10),potentialPlayArea.centerX() + (scaling*5),potentialPlayArea.centerY() + (scaling*10));

        sand = new Sand(100, 200);

        ourHolder = getHolder();
    }
    @Override
    public void run() {
        while (playing) {
            logic();
            draw();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void logic() {
        //Does all game logic
        if (onScreenTetra == null) {
            onScreenTetra = new Tetra(playArea.centerX(), playArea.top, scaling);
        } else{
            //Move tetra
            onScreenTetra.moveDown();
        }
        //check for collision with sand or walls

        if (onScreenTetra.checkBottom(playArea.bottom) ){//|| onScreenTetra.checkSand(sand.sandArray, playArea.left)){
            onScreenTetra.explode(sand.sandArray, playArea);
            onScreenTetra = null;
        }
    }

    private void draw() {
        if (ourHolder.getSurface().isValid()) {
            canvas = ourHolder.lockCanvas();

            //Draws all the background stuff
            drawBackground();

            //Draw Current Tetra
            if (onScreenTetra != null){
                onScreenTetra.draw(canvas);
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

    public void drawSand(){
        Paint paint = new Paint();
        int sandScale = scaling/10;
        for (int x =0; x< sand.getSandPitWidth(); x++){
            for(int y = 0; y<sand.getSandPitHeight(); y++) {
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
                    Rect sandPixel = new Rect((x*sandScale)+playArea.left,(y*sandScale)+playArea.top,(x*sandScale)+sandScale+playArea.left,(y*sandScale)+sandScale+playArea.top);
                    canvas.drawRect(sandPixel,paint);
                }
            }
        }
    }
    // Clean up our thread if the game is interrupted or the player quits
    public void pause(){
        playing = false;
        try {
            gameThread.join();
            sandThread.join();
        }
        catch (InterruptedException e) {
            // do something
        }
    }

    // Make a new thread and start it
    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        sandThread = new Thread(sand);
        gameThread.start();
        sandThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (onScreenTetra == null) {
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
                    Log.d("touch", "click");
                    onScreenTetra.rotate();
                    onScreenTetra.checkWalls((int)motionEvent.getX(),playArea.left,playArea.right); //This will catch out of bounds from rotation
                }
            }
            case MotionEvent.ACTION_MOVE: {
                onScreenTetra.userDrag((int)motionEvent.getX(),playArea.left,playArea.right);
                return true;
            }
        }

        return true;
    }
}
