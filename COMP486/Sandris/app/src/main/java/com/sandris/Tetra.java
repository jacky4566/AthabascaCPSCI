package com.sandris;

/*
This class constitutes a "Tetris Block" AKA Tetra. Which gets later melted down into sand.

*/

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

public class Tetra {
    private TetraType type;
    private Point location;
    private boolean[][] shapeGrid = new boolean[4][4]; //Used to define for drawing and collision
    private Paint tetraColor = new Paint();
    private int tetraScale;

    public Tetra(int x, int y, int scale){
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
        location.offset(0,tetraScale);
    }
    public void rotate(){
        //Right rotate 90 degrees
        boolean[][] temp = new boolean[4][4];

        for (int i=0;i<4;i++)
            for (int j=0;j<4;j++)
                temp[i][j] = shapeGrid[4-1-j][i];

        shapeGrid = temp;
    }

    public void userDrag(int newX, int playAreaLeft, int playAreaRight){
        //Check for wall collision with new point

        //Range check to avoid glitchs caused by clicking off play area
        if (newX < playAreaLeft)
            newX = playAreaLeft;
        if (newX > playAreaRight)
            newX = playAreaRight;

        if (!checkWalls(newX, playAreaLeft, playAreaRight))
            //No Collision, safe to update location
                location = new Point(newX, location.y);
    }

    public boolean checkWalls(int newX, int playAreaLeft, int playAreaRight){
        //Check Left wall collision
        for (int x =0; x< 4; x++){
            for(int y = 0; y<4; y++) {
                if (shapeGrid[x][y]) {
                    //Left Wall
                    if ((newX + (tetraScale * (x - 2))) < playAreaLeft) {
                        //Left wall collision detected!
                        //Snap to wall
                        location = new Point(playAreaLeft + (tetraScale * (2- x)), location.y);
                        return true;
                    }
                }
            }
        }
        //Check right wall collsion.
        //Needs to read Right to Left
        for (int x =3; x >= 0; x--){
            for(int y = 3; y >= 0; y--) {
                if (shapeGrid[x][y]) {
                    //Right Wall
                    if ((newX + tetraScale + (tetraScale * (x - 2))) > playAreaRight) {
                        //Right Wall collision detected!
                        //Snap to wall
                        location = new Point(playAreaRight + (tetraScale * (1-x)) , location.y);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void draw(Canvas canvas){
        for (int x =0; x< 4; x++){
            for(int y = 0; y<4; y++) {
                if (shapeGrid[x][y]) {
                    //draw block
                    canvas.drawRect(location.x + (tetraScale * (x - 2)), location.y + (tetraScale * (y + 2)), location.x + (tetraScale * (x - 2)) + tetraScale, location.y + (tetraScale * (y + 2)) + tetraScale, tetraColor);
                    Paint border = new Paint(tetraColor);
                    border.setStyle(Paint.Style.STROKE);
                    ColorFilter filter = new LightingColorFilter(0xFFFFFFFF, 0x00222222);
                    border.setColorFilter(filter);
                    border.setStrokeWidth(tetraScale / 10);
                    canvas.drawRect(location.x + (tetraScale * (x - 2)), location.y + (tetraScale * (y + 2)), location.x + (tetraScale * (x - 2)) + tetraScale, location.y + (tetraScale * (y + 2)) + tetraScale, border);
                }
            }
        }
    }

    public boolean checkBottom(int playAreaBottom){
        //checks for tetra outside play boundries returns true for collision
        for (int x =0; x< 4; x++){
            for(int y = 3; y >= 0; y--) {
                if (shapeGrid[x][y]) {
                    if ((location.y+(tetraScale * (y+2))+tetraScale) > playAreaBottom) {
                        // Collision detected!
                        //Snap to floor
                        location = new Point(location.x, playAreaBottom - (tetraScale*(y+3)) - 1 );
                        return true;
                    }
                }
            }
        }
        // No collision
        return false;
    }

    public boolean checkSand(TetraType[][] sandArray, int offset){
        //Check for contact with existing sand
        for (int x =0; x< 4; x++){
            for(int y = 0; y<4; y++) {
                if (shapeGrid[x][y]) {
                    Rect Box = new Rect(location.x + (tetraScale * (x - 2)),
                            location.y + (tetraScale * (y + 2)),
                            location.x + (tetraScale * (x - 2)) + tetraScale,
                            location.y + (tetraScale * (y + 2)) + tetraScale);
                    for (int sandx =0; sandx< sandArray.length; sandx++){
                        for(int sandy = 0; sandy<sandArray[0].length; sandy++) {
                            // Collision
                            if ((sandArray[sandx][sandy] != null) &&
                                    Box.contains(sandx + offset,sandy)
                            )
                                return true;
                        }
                    }
                }
            }
        }
        // No collision
        return false;
    }

    public void explode(TetraType[][] sandArray, Rect playArea){
        //Explodes the Tetra into sand
        for (int x =0; x< 4; x++){
            for(int y = 0; y<4; y++) {
                if (shapeGrid[x][y]) {
                    //Add to sand grid
                    int sandX = (location.x + (tetraScale * (x-2)) - playArea.left) / 8;
                    int sandY = (location.y + (tetraScale * (y+2)) - playArea.top ) / 8;
                    for (int drawx = sandX;  drawx < (sandX + 10); drawx++) {
                        for (int drawy = sandY;  drawy < (sandY + 10); drawy++) {
                            sandArray[drawx][drawy] = type;
                        }
                    }
                }
            }
        }
    }
}
