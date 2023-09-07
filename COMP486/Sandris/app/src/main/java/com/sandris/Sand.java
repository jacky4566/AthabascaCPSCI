package com.sandris;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;


public class Sand implements Runnable {

    public TetraType[][] sandArray;
    private int sandPitWidth;
    private int sandPitHeight;
    public Sand(int width, int height) {
        sandPitWidth = width;
        sandPitHeight = height;
        sandArray = new TetraType[sandPitWidth][sandPitHeight];
    }

    @Override
    public void run() {
        while(true){
            moveSand();
        }
    }

    public int getSandPitWidth(){
        return sandPitWidth;
    }

    public int getSandPitHeight(){
        return sandPitHeight;
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
