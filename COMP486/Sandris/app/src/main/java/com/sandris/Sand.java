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
                    switch (sandArray[x][y]){
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
                    canvas.drawPoint(x+leftOffset, y, paint);
                }
            }
        }
    }

    private void moveSand(){
        //Moves all the sand particles bottom to top
        boolean keepProcessing;
        int leftRight = 0; //used to generate sudo random left right choices.
        for(int y = sandPitHeight - 2; y>=0; y--) {
            keepProcessing = false;
            for (int x =0; x< sandPitWidth; x++){
                if (sandArray[x][y] != null) {
                    keepProcessing = true;
                    //For each entity that is affected by gravity
                    //If there’s an empty space below it, move it down.
                    //If there’s an empty space down and to the left, move it down and to the left.
                    //If there’s an empty space down and to the right, move it down and to the right.
                    if (sandArray[x][y + 1] == null) {
                        //we can move down
                        sandArray[x][y + 1] = sandArray[x][y];
                        sandArray[x][y] = null;
                    } else if (leftRight % 2 == 0) {
                        //Try Left
                        if ((x - 1 > 0) &&
                                (sandArray[x - 1][y + 1] == null)) {
                            //We are not at the wall and we can move diagonal left
                            sandArray[x - 1][y + 1] = sandArray[x][y];
                            sandArray[x][y] = null;
                        }else if ((x + 1 < sandPitWidth &&
                                (sandArray[x + 1][y + 1] == null))){
                            //We are not at the wall and we can move diagonal right
                            sandArray[x + 1][y + 1] = sandArray[x][y];
                            sandArray[x][y] = null;
                        }
                    } else  {
                        //Try right
                        if ((x + 1 < sandPitWidth &&
                                (sandArray[x + 1][y + 1] == null))){
                            //We are not at the wall and we can move diagonal right
                                sandArray[x + 1][y + 1] = sandArray[x][y];
                                sandArray[x][y] = null;
                        } else if ((x - 1 > 0) &&
                                (sandArray[x - 1][y + 1] == null)) {
                            //We are not at the wall and we can move diagonal left
                            sandArray[x - 1][y + 1] = sandArray[x][y];
                            sandArray[x][y] = null;
                        }
                    }
                }
                leftRight++;
            }
            if (!keepProcessing)
                return;
        }
    }
}
