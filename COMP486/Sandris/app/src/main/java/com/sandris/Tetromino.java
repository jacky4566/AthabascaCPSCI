package com.sandris;

/*
This class constitutes a "Tetris Block" AKA tetromino. Which gets later melted down into sand.

*/

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

public class Tetromino {
    public TetraType type;
    public Point location;
    public boolean[][] shapeGrid = new boolean[4][4]; //Used to define for drawing and collision
    public Paint tetraColor = new Paint();
    public int tetraScale;

    public Tetromino(int x, int y, int scale, int color){
        type = TetraType.values()[color];
        location = new Point(x,y);
        tetraScale = scale;
        tetraColor = getColor(type);
        switch (type){
            case I:
                shapeGrid = new boolean[][]{{false, true, false, false}, {false, true, false, false}, {false, true, false, false}, {false, true, false, false}};
                break;
            case J:
                shapeGrid = new boolean[][]{{false, false, true, false}, {true, true, true, false}, {false, false, false, false}, {false, false, false, false}};
                break;
            case L:
                shapeGrid = new boolean[][]{{false, false, false, false}, {true, true, true, false}, {false, false, true, false}, {false, false, false, false}};
                break;
            case O:
                shapeGrid = new boolean[][]{{false, false, false, false}, {false, true, true, false}, {false, true, true, false}, {false, false, false, false}};
                break;
            case S:
                shapeGrid = new boolean[][]{{false, false, false, false}, {false, true, true, false}, {true, true, false, false}, {false, false, false, false}};
                break;
            case Z:
                shapeGrid = new boolean[][]{{false, false, false, false}, {true, true, false, false}, {false, true, true, false}, {false, false, false, false}};
                break;
            case T:
                shapeGrid = new boolean[][]{{false, false, true, false}, {false, true, true, false}, {false, false, true, false}, {false, false, false, false}};
                break;
        }
    }

    public static Paint getColor(TetraType type){
        Paint pain = new Paint();
        switch (type){
            case I:
                pain.setColor(CONSTANTS.Tetra_Color_I);
                return pain;
            case J:
                pain.setColor(CONSTANTS.Tetra_Color_J);
                return pain;
            case L:
                pain.setColor(CONSTANTS.Tetra_Color_L);
                return pain;
            case O:
                pain.setColor(CONSTANTS.Tetra_Color_O);
                return pain;
            case S:
                pain.setColor(CONSTANTS.Tetra_Color_S);
                return pain;
            case Z:
                pain.setColor(CONSTANTS.Tetra_Color_Z);
                return pain;
            case T:
                pain.setColor(CONSTANTS.Tetra_Color_T);
                return pain;
        }
        return pain;
    }

    public void moveDown(){
        //Basic function that moves down for testing
        location.offset(0,CONSTANTS.blockScale);
    }
    public void moveMotion(double phoneAngle, double phoneZAngle) {
        //Moves Tetromino according to phone angle
        double distance = Math.abs(Math.sin(phoneZAngle)) * 30.0;
        int deltaX = -(int)(Math.floor(Math.cos(phoneAngle) * distance));
        int deltaY = (int)(Math.floor(Math.sin(phoneAngle) * distance));
        location.offset(deltaX,deltaY);
    }
    public void rotate(){
        //Right rotate 90 degrees
        boolean[][] temp = new boolean[4][4];

        for (int i=0;i<4;i++)
            for (int j=0;j<4;j++)
                temp[i][j] = shapeGrid[4-1-j][i];

        shapeGrid = temp;
    }

    public void draw(Canvas canvas){
        //Draw itself onto the target canvas
        for (int x =0; x< 4; x++){
            for(int y = 0; y<4; y++) {
                if (shapeGrid[x][y]) {
                    //draw block
                    canvas.drawRect(location.x + (tetraScale * (x - 2)), location.y + (tetraScale * (y + 2)), location.x + (tetraScale * (x - 2)) + tetraScale, location.y + (tetraScale * (y + 2)) + tetraScale, tetraColor);
                    //draw border
                    Paint border = new Paint(tetraColor);
                    border.setStyle(Paint.Style.STROKE);
                    ColorFilter filter = new LightingColorFilter(0xFFFFFFFF, 0x00222222);
                    border.setColorFilter(filter);
                    int strokeWidth = tetraScale / 10;
                    border.setStrokeWidth(strokeWidth);
                    canvas.drawRect(
                            location.x + (tetraScale * (x - 2))+ strokeWidth,
                            location.y + (tetraScale * (y + 2))+ strokeWidth,
                            location.x + (tetraScale * (x - 2)) + tetraScale-strokeWidth,
                            location.y + (tetraScale * (y + 2)) + tetraScale -strokeWidth,
                            border);
                }
            }
        }
    }


}
