package com.sandris;

import android.graphics.Rect;

public class Sand implements Runnable {
    public TetraType[][] sandArray;
    private long threadStart;
    public int sandTopLevel; // Optimization: Keeps track of the top level of our sand for checking
    public Sand(int width, int height ) {
        sandArray = new TetraType[width][height];
    }

    @Override
    public void run() {
        while(true) {
            threadStart = System.nanoTime();
            moveSand();

            while ((System.nanoTime() - threadStart) < 100000000) {
                Thread.yield();
            }
        }
    }
    private void moveSand(){
        //Moves all the sand particles bottom to top
        boolean keepProcessing;
        int leftRight = 0; //used to generate sudo random left right choices.
        for(int y = sandArray[0].length - 2; y>=0; y--) {
            keepProcessing = false;
            for (int x =0; x< sandArray.length; x++){
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
                        }else if ((x + 1 < sandArray.length &&
                                (sandArray[x + 1][y + 1] == null))){
                            //We are not at the wall and we can move diagonal right
                            sandArray[x + 1][y + 1] = sandArray[x][y];
                            sandArray[x][y] = null;
                        }
                    } else  {
                        //Try right
                        if ((x + 1 < sandArray.length &&
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
            if (!keepProcessing) {
                sandTopLevel = y; //Update the new top level
                return;
            }
        }
    }

    private static void checkContinous(){
        //Checks for a continuous line

    }
}
