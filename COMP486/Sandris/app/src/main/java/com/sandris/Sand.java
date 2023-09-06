package com.sandris;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;


public class Sand implements Runnable {

    public TetraType[][] sandArray;
    private int sandPitWidth;
    private int sandPitHeight;
    volatile boolean playing = false;
    public Sand(int width, int height, boolean runThread) {
        sandPitWidth = width;
        sandPitHeight = height;
        sandArray = new TetraType[sandPitWidth][sandPitHeight];
        playing = runThread;
    }

    @Override
    public void run() {
        while(true){
            moveSand();
        }
    }

    public void draw(Canvas canvas, int leftOffset){
        Paint paint = new Paint();
        for (int x =0; x< sandPitWidth; x++){
            for(int y = 0; y<sandPitHeight; y++) {
                if (sandArray[x][y] != null){
                    paint.setColor(Color.RED); // Set the color of the pixel
                    canvas.drawPoint(x+leftOffset, y, paint);
                }
            }
        }
    }

    private void moveSand(){
        //Moves all the sand particles bottom to top
        int leftRight = 0; //used to generate sudo random left right choices.
        for (int x =0; x< sandPitWidth; x++){
            for(int y = sandPitHeight-1; y>=0; y--) {
                if (sandArray[x][y] != null){
                    //For each entity that is affected by gravity
                    //If there’s an empty space below it, move it down.
                    //If there’s an empty space down and to the left, move it down and to the left.
                    //If there’s an empty space down and to the right, move it down and to the right.
                    if (y + 1 < sandPitHeight-1) {
                        //We are not at the bottom
                        if (sandArray[x][y + 1] == null) {
                            //we can move down
                            sandArray[x][y + 1] = sandArray[x][y];
                            sandArray[x][y] = null;
                        }else if (leftRight % 2 == 0){
                            //Move Left
                            if (x-1 > 0) {
                                //We are not at the wall
                                if (sandArray[x - 1][y + 1] == null) {
                                    //we can move down
                                    sandArray[x - 1][y + 1] = sandArray[x][y];
                                    sandArray[x][y] = null;
                                }
                            }
                        }else if (leftRight % 2 == 1) {
                            //Move right
                            if (x+1 < sandPitWidth) {
                                //We are not at the wall
                                if (sandArray[x + 1][y + 1] == null) {
                                    //we can move down
                                    sandArray[x + 1][y + 1] = sandArray[x][y];
                                    sandArray[x][y] = null;
                                }
                            }
                        }
                    }
                }
                leftRight++;
            }
        }
    }
}
