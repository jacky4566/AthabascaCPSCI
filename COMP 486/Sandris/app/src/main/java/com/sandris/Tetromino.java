package com.sandris;

/*
Jackson Wiebe 3519635
This class constitutes a "Tetris Block" AKA tetromino. Which gets later melted down into sand.
Has basic functions to maipulate the blocks
*/

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

public class Tetromino {
    public Point location; //Where on screen is this tetromino
    public boolean[][] shapeGrid = new boolean[4][4]; //Used to define for drawing and collision
    public int tetraColor;//What color is it?
    public int tetraScale;//What is our scale to draw it
    public boolean fastDrop = false; //Is this block falling quickly at user request?

    public Tetromino(int x, int y, int scale, int colour) {
        /*Inialitze new block at point xy
            scale is used for drawing later
            Include what color this block should be
         */
        location = new Point(x, y);
        tetraScale = scale;
        tetraColor = getColor(colour);
        switch (new Random().nextInt(6)) { //Randomly chose a shape for this tetra
            case 0:
                // I Shape
                shapeGrid = new boolean[][]{{false, true, false, false}, {false, true, false, false}, {false, true, false, false}, {false, true, false, false}};
                break;
            case 1:
                //J Shape
                shapeGrid = new boolean[][]{{false, false, true, false}, {true, true, true, false}, {false, false, false, false}, {false, false, false, false}};
                break;
            case 2:
                //L Shape
                shapeGrid = new boolean[][]{{false, false, false, false}, {true, true, true, false}, {false, false, true, false}, {false, false, false, false}};
                break;
            case 3:
                //O Shape
                shapeGrid = new boolean[][]{{false, false, false, false}, {false, true, true, false}, {false, true, true, false}, {false, false, false, false}};
                break;
            case 4:
                //S Shape
                shapeGrid = new boolean[][]{{false, false, false, false}, {false, true, true, false}, {true, true, false, false}, {false, false, false, false}};
                break;
            case 5:
                //Z Shape
                shapeGrid = new boolean[][]{{false, false, false, false}, {true, true, false, false}, {false, true, true, false}, {false, false, false, false}};
                break;
            case 6:
                //T Shape
                shapeGrid = new boolean[][]{{false, false, true, false}, {false, true, true, false}, {false, false, true, false}, {false, false, false, false}};
                break;
        }
    }

    public static int getColor(int type) {
        //Returns a color type to draw this unit
        switch (type) {
            case 0:
                return CONSTANTS.Tetra_Color_I;
            case 1:
                return CONSTANTS.Tetra_Color_J;
            case 2:
                return CONSTANTS.Tetra_Color_L;
            case 3:
                return CONSTANTS.Tetra_Color_O;
            case 4:
                return CONSTANTS.Tetra_Color_S;
            case 5:
                return CONSTANTS.Tetra_Color_Z;
            case 6:
                return CONSTANTS.Tetra_Color_T;
        }
        return 0;
    }

    public void moveDown(int playspeed) {
        //Basic function that moves down for testing
        if (fastDrop)
            location.offset(0, (int) CONSTANTS.MAX_PLAY_SPEED);
        location.offset(0, playspeed);
    }

    public void moveMotion(double phoneAngle, double phoneZAngle, int playspeed) {
        //Moves Tetromino according to phone angle
        //Gravity is set by XY plane, Speed is set by YZ plane
        double distance = Math.abs(Math.sin(phoneZAngle)) * playspeed * 2;
        int deltaX = -(int) (Math.floor(Math.cos(phoneAngle) * distance));
        int deltaY = (int) (Math.sin(phoneAngle) * distance);
        location.offset(deltaX, deltaY);
    }

    public void rotate() {
        //Right rotate 90 degrees
        boolean[][] temp = new boolean[4][4];

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                temp[i][j] = shapeGrid[4 - 1 - j][i];

        shapeGrid = temp;
    }

    public void draw(Canvas canvas) {
        //Draw itself onto the target canvas
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (shapeGrid[x][y]) {
                    //draw block
                    Paint paint = new Paint();
                    paint.setColor(tetraColor);
                    canvas.drawRect(location.x + (tetraScale * (x - 2)), location.y + (tetraScale * (y + 2)), location.x + (tetraScale * (x - 2)) + tetraScale, location.y + (tetraScale * (y + 2)) + tetraScale, paint);
                    //draw border
                    Paint border = new Paint(tetraColor);
                    border.setStyle(Paint.Style.STROKE);
                    ColorFilter filter = new LightingColorFilter(0xFFFFFFFF, 0x00222222);
                    border.setColorFilter(filter);
                    int strokeWidth = tetraScale / 10;
                    border.setStrokeWidth(strokeWidth);
                    canvas.drawRect(
                            location.x + (tetraScale * (x - 2)) + strokeWidth,
                            location.y + (tetraScale * (y + 2)) + strokeWidth,
                            location.x + (tetraScale * (x - 2)) + tetraScale - strokeWidth,
                            location.y + (tetraScale * (y + 2)) + tetraScale - strokeWidth,
                            border);
                }
            }
        }
    }


}