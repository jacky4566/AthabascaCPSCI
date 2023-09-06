package com.sandris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.VelocityTracker;

import androidx.core.view.VelocityTrackerCompat;

import java.util.Calendar;

public class GameView extends SurfaceView implements Runnable{
    volatile boolean playing = false;
    VelocityTracker mVelocityTracker;
    private Context context;
    private Canvas canvas;
    Thread gameThread = null;
    Thread sandThread = null;
    private SurfaceHolder ourHolder;

    private float scaling = 1.0F;

    private Tetra onScreenTetra;

    private static float marginRight = 0.20F;
    private static float marginLeft = 0.05F;
    private int screenX;
    private int screenY;
    private int playAreaLeft;
    private int playAreaRight;
    private int tetraSize;

    private Sand sand;

    private static final int MAX_CLICK_DURATION = 300;
    private static final int MIN_CLICK_DURATION = 30;
    private long startClickTime;


    public GameView(Context context, int x, int y ){
        super(context);
        this.context = context;

        screenX = x;
        screenY = y;
        playAreaLeft = (int)(screenX * marginLeft);
        playAreaRight = (int)(screenX * (1.0F - marginRight)); //
        playAreaRight = playAreaRight - ((playAreaLeft - playAreaRight) % 10); //Ensure width fits 10 blocks from left to right
        tetraSize = (playAreaRight - playAreaLeft) / 10; //Size of each Tetra

        sand = new Sand(playAreaRight - playAreaLeft, screenY, playing);

        ourHolder = getHolder();
    }
    @Override
    public void run() {
        while (playing) {
            logic();
            draw();
        }
    }

    private void logic(){
        //Does all game logic
        if (onScreenTetra == null){
            onScreenTetra = new Tetra(((playAreaRight - playAreaLeft) / 2) + playAreaLeft , tetraSize);
        }
    }

    private void draw() {
        if (ourHolder.getSurface().isValid()) {
            canvas = ourHolder.lockCanvas();

            //Draws all the background stuff
            drawBackground();

            //Draw Current Tetra
            if (onScreenTetra != null){
                //Move tetra
                onScreenTetra.moveDown();
                //check for collision with sand or walls
                if (onScreenTetra.checkBottom(screenY) || onScreenTetra.checkSand(sand.sandArray, playAreaLeft)){
                    onScreenTetra.explode(sand.sandArray, playAreaLeft);
                    onScreenTetra = null;
                }else {
                    //draw it
                    onScreenTetra.draw(canvas);
                }
            }

            //Draw Sand
            sand.draw(canvas, playAreaLeft);

            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawBackground(){
        Paint paint = new Paint();
        paint.setColor(Color.BLACK); // Set the color of the pixel
        canvas.drawRect(0, 0, screenX, screenY, paint);

        paint.setColor(Color.DKGRAY); // Set the color of the pixel
        canvas.drawRect(0, 0, playAreaLeft, screenY, paint);
        canvas.drawRect(playAreaRight, 0, screenX, screenY, paint);
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
    // Execution moves to the R
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

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
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
                    onScreenTetra.checkWalls((int)motionEvent.getX(),playAreaLeft,playAreaRight); //This will catch out of bounds from rotation
                }

            }
            case MotionEvent.ACTION_MOVE: {
                onScreenTetra.userDrag((int)motionEvent.getX(),playAreaLeft,playAreaRight);
                mVelocityTracker.addMovement(motionEvent);
                mVelocityTracker.computeCurrentVelocity(1000);
                float vy = mVelocityTracker.getYVelocity();
                if (vy >800.0){
                    onScreenTetra.userSmash();
                }
                return true;
            }
        }


        return true;
    }
}
