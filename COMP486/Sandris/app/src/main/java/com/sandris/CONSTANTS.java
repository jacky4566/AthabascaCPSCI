package com.sandris;

import android.graphics.Color;

public interface CONSTANTS {
    //Colors
    public static final int Tetra_Color_I = Color.parseColor("#008080");
    public static final int Tetra_Color_J = Color.parseColor("#00008B");
    public static final int Tetra_Color_L = Color.parseColor("#ff8c00");
    public static final int Tetra_Color_O = Color.parseColor("#ffff00");
    public static final int Tetra_Color_S = Color.parseColor("#ff0000");
    public static final int Tetra_Color_Z = Color.parseColor("#7fffd4");
    public static final int Tetra_Color_T = Color.parseColor("#800080");

    //Physics and Drawing
    public static final int blockScale = 10; //Scale of Tetromino block to sand
    public static final int gameWidth = blockScale * 10; //Size of game area in sand units
    public static final int gameHeight = blockScale * 20; //Size of game area in sand units
    public static final int sandLoopDuration = 35; //Defines how fast to run the sand physics calculations in Milliseconds
    //Display Constants
    public static final float marginRight = 0.02F;
    public static final float marginLeft = 0.02F;
    public static final float marginTop = 0.15F;
    public static final float marginBottom = 0.00F;
    //Touch interface
    public static final int MAX_CLICK_DURATION = 250;
    public static final int MIN_CLICK_DURATION = 25;

}
