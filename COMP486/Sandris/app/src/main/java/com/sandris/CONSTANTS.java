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
    public static final int BLOCK_SCALE = 10; //Scale of Tetromino block to sand
    public static final int GAME_WIDTH = BLOCK_SCALE * 10; //Size of game area in sand units
    public static final int GAME_HEIGHT = BLOCK_SCALE * 20; //Size of game area in sand units
    public static final int SAND_LOOP_TIMER_SLOW = 35; //Defines how fast to run the sand physics calculations in Milliseconds at the slowest speed
    public static final int SAND_LOOP_TIMER_FAST = 35; //Defines how fast to run the sand physics calculations in Milliseconds at the fastest speed
    //Display Constants
    public static final float MARGIN_RIGHT = 0.02F;
    public static final float MARGIN_LEFT = 0.02F;
    public static final float MARGIN_TOP = 0.15F;
    public static final float MARGIN_BOTTOM = 0.00F;
    //Touch interface
    public static final int MAX_CLICK_DURATION = 250;
    public static final int MIN_CLICK_DURATION = 25;
    //Game Elements
    public static final double MAX_PLAY_SPEED = 40;
    public static final double MIN_PLAY_SPEED = 6;
    public static final int RESOLVE_TIMER_DUR = 6000; //successive resolves must happen under this timer

}
