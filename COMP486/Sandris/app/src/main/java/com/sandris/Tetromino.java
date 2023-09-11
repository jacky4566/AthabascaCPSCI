package com.sandris;

/*
This class constitutes a "Tetris Block" AKA tetromino. Which gets later melted down into sand.

*/

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.Random;

public class Tetromino {
    public TetraType type;
    public Point location;
    public boolean[][] shapeGrid = new boolean[4][4]; //Used to define for drawing and collision
    public Paint tetraColor = new Paint();
    public int tetraScale;

    public Tetromino(int x, int y, int scale){
        Random rand = new Random();
        type = TetraType.values()[rand.nextInt(TetraType.values().length)];
        //type = TetraType.I;
        location = new Point(x,y);
        tetraScale = scale;
        switch (type){
            case I:
                tetraColor.setColor(CONSTANTS.Tetra_Color_I); // Teal
                shapeGrid = new boolean[][]{{false, true, false, false}, {false, true, false, false}, {false, true, false, false}, {false, true, false, false}};
                break;
            case J:
                tetraColor.setColor(CONSTANTS.Tetra_Color_J); // Dark Blue
                shapeGrid = new boolean[][]{{false, false, true, false}, {true, true, true, false}, {false, false, false, false}, {false, false, false, false}};
                break;
            case L:
                tetraColor.setColor(CONSTANTS.Tetra_Color_L); // Dark Orange
                shapeGrid = new boolean[][]{{false, false, false, false}, {true, true, true, false}, {false, false, true, false}, {false, false, false, false}};
                break;
            case O:
                tetraColor.setColor(CONSTANTS.Tetra_Color_O); // Yellow
                shapeGrid = new boolean[][]{{false, false, false, false}, {false, true, true, false}, {false, true, true, false}, {false, false, false, false}};
                break;
            case S:
                tetraColor.setColor(CONSTANTS.Tetra_Color_S); // Red
                shapeGrid = new boolean[][]{{false, false, false, false}, {false, true, true, false}, {true, true, false, false}, {false, false, false, false}};
                break;
            case Z:
                tetraColor.setColor(CONSTANTS.Tetra_Color_Z); // Green
                shapeGrid = new boolean[][]{{false, false, false, false}, {true, true, false, false}, {false, true, true, false}, {false, false, false, false}};
                break;
            case T:
                tetraColor.setColor(CONSTANTS.Tetra_Color_T); // Purple
                shapeGrid = new boolean[][]{{false, false, true, false}, {false, true, true, false}, {false, false, true, false}, {false, false, false, false}};
                break;
        }
    }

    public void moveDown(){
        location.offset(0,CONSTANTS.sandScale);
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
