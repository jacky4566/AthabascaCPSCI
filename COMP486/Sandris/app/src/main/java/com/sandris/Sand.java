package com.sandris;

import android.graphics.Point;
import android.util.Log;

import java.util.LinkedList;

public class Sand {
    /*
    Jackson Wiebe 3519635
    Description: Sand Class for Sandris game. Contains the sand array and functions to move sand around.
     */
    public volatile int[][] sandArray; //Holds all our sand elements
    private long lastRun;   //Used to calulate how fast to run the phyiscs loop
    private long resolveTimer;  //Keeps track of when the last resolve was
    private int resolveCounter; //Keeps track of how many resolves the player has had

    public Sand(int width, int height) {
        resolveCounter = 1;
        sandArray = new int[width][height];
    }

    public void sandphysics(double v) {
        /*
        Use a timer loop to slow down the movement of sand. This gives a more natural look.
        Take variable v to denote the delay between movement events so we can speed up as the game progresses.
         */
        if (System.currentTimeMillis() <= lastRun + v) {
            return;
        }
        lastRun = System.currentTimeMillis();
        moveSand();
        checkClearLine();
    }


    private void moveSand() {
        //Moves all the sand particles bottom to top
        long leftRight = System.currentTimeMillis(); //used to generate sudo random left right choices.
        for (int y = sandArray[0].length - 2; y >= 0; y--) {
            for (int x = 0; x < sandArray.length; x++) {
                if (sandArray[x][y] != 0) {
                    /*For each entity that is affected by gravity
                    If there’s an empty space below it, move it down.
                    Randomly between these two:
                        If there’s an empty space down and to the left, move it down and to the left.
                        If there’s an empty space down and to the right, move it down and to the right.
                    */
                    if (sandArray[x][y + 1] == 0) {
                        //we can move down
                        sandArray[x][y + 1] = sandArray[x][y];
                        sandArray[x][y] = 0;
                    } else if (leftRight % 2 == 0) {
                        //Try Left
                        if (((x - 1) >= 0) &&
                                (sandArray[x - 1][y + 1] == 0)) {
                            //We are not at the wall and we can move diagonal left
                            sandArray[x - 1][y + 1] = sandArray[x][y];
                            sandArray[x][y] = 0;
                        } else if ((x + 1 < sandArray.length &&
                                (sandArray[x + 1][y + 1] == 0))) {
                            //We are not at the wall and we can move diagonal right
                            sandArray[x + 1][y + 1] = sandArray[x][y];
                            sandArray[x][y] = 0;
                        }
                    } else {
                        //Try right
                        if ((x + 1 < sandArray.length &&
                                (sandArray[x + 1][y + 1] == 0))) {
                            //We are not at the wall and we can move diagonal right
                            sandArray[x + 1][y + 1] = sandArray[x][y];
                            sandArray[x][y] = 0;
                        } else if (((x - 1) >= 0) &&
                                (sandArray[x - 1][y + 1] == 0)) {
                            //We are not at the wall and we can move diagonal left
                            sandArray[x - 1][y + 1] = sandArray[x][y];
                            sandArray[x][y] = 0;
                        }
                    }
                    leftRight++;
                }
            }
        }
    }

    private void checkClearLine() {
        //Look for a continous trail of sand left to right.
        for (int y = sandArray[0].length - 1; y >= 0; y--) {
            int searchType = sandArray[0][y];
            if (searchType == 0)
                break;//Nothing to check from this start
            Point startP = new Point(0, y);
            if (pathChecker(startP, searchType)) {
                Log.d("pathChecker", "Path found");
                if ((System.currentTimeMillis() - resolveTimer) <= CONSTANTS.RESOLVE_TIMER_DUR) {
                    if (resolveCounter < 10)
                        resolveCounter++;
                } else {
                    resolveCounter = 1;
                }
                resolveTimer = System.currentTimeMillis();
                new SoundEngine(SoundEffect.values()[SoundEffect.resolve_1.ordinal() + resolveCounter - 1]);
                GameActivity.gameScore = GameActivity.gameScore + (pathDestoryer(startP, searchType) * resolveCounter);
                return;
            }
        }
    }

    private boolean pathChecker(Point currentpos, int searchType) {
        //Use a Breadth first search (BFS) algorithm to find a complete path
        boolean[][] visited = new boolean[sandArray.length][sandArray[0].length];
        LinkedList<Point> queue = new LinkedList<>();
        queue.offer(currentpos);
        //Mark cell visited
        visited[currentpos.x][currentpos.y] = true;

        while (!queue.isEmpty()) {
            Point current = queue.pop();

            // Check if we reached the right edge
            if (current.x + 1 >= sandArray.length) {
                return true;
            }

            //Mark cell visited
            visited[current.x][current.y] = true;

            Point next = new Point(current.x + 1, current.y);
            if ((sandArray[next.x][next.y] == searchType) && !visited[next.x][next.y]) {
                //If right position is our search type and unvisited
                queue.add(next);
                visited[next.x][next.y] = true;
            }

            next = new Point(current.x, current.y - 1);
            if ((next.y >= 0) && !visited[next.x][next.y]) {
                //If upper position is our search type and unvisited
                if (sandArray[next.x][next.y] == searchType) {
                    queue.add(next);
                    visited[next.x][next.y] = true;
                }
            }

            next = new Point(current.x, current.y + 1);
            if ((next.y < sandArray[0].length) && !visited[next.x][next.y]) {
                if (sandArray[next.x][next.y] == searchType) {
                    //If lower position is our search type and unvisited
                    queue.add(next);
                    visited[next.x][next.y] = true;
                }
            }
        }
        // No valid path from left edge to right edge
        return false;
    }

    private int pathDestoryer(Point searchP, int searchType) {
        int score = 0; //Keep track of cells destroyed for score
        //Use a Breadth first recursive search to remove elements of the target type
        boolean[][] visited = new boolean[sandArray.length][sandArray[0].length];
        LinkedList<Point> queue = new LinkedList<>();
        queue.offer(searchP);
        //Mark cell visited
        visited[searchP.x][searchP.y] = true;

        while (!queue.isEmpty()) {
            Point current = queue.pop();

            //Destroy the sand piece
            sandArray[current.x][current.y] = 0;
            score++;

            //Add neighbours in 4 directions, Check for valid search and matches target
            Point next = new Point(current.x + 1, current.y);
            if ((next.x < sandArray.length) && (sandArray[next.x][next.y] == searchType) && !visited[next.x][next.y]) {
                //If right position is our search type and unvisited
                queue.offer(next);
                //Mark cell visited
                visited[next.x][next.y] = true;
            }

            next = new Point(current.x, current.y - 1);
            if ((next.y >= 0) && !visited[next.x][next.y]) {
                if (sandArray[next.x][next.y] == searchType) {
                    //If upper position is our search type and unvisited
                    queue.offer(next);
                    //Mark cell visited
                    visited[next.x][next.y] = true;
                }
            }

            next = new Point(current.x, current.y + 1);
            if ((next.y < sandArray[0].length) && !visited[next.x][next.y]) {
                //If lower position is our search type and unvisited
                if (sandArray[next.x][next.y] == searchType) {
                    //If upper position is our search type and unvisited
                    queue.offer(next);
                    //Mark cell visited
                    visited[next.x][next.y] = true;
                }
            }

            next = new Point(current.x - 1, current.y);
            if ((next.x >= 0) && !visited[next.x][next.y]) {
                //If backward position is our search type and unvisited
                if (sandArray[next.x][next.y] == searchType) {
                    //If upper position is our search type and unvisited
                    queue.offer(next);
                    //Mark cell visited
                    visited[next.x][next.y] = true;
                }
            }
        }
        return score;
    }
}
