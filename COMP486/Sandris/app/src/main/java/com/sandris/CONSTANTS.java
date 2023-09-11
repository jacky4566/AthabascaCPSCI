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
    public static final int sandScale = 10; //ratio of sand pixel to tetromino size
    public static final int gameWidth = 10; //Size of game area in tetromino block units
    public static final int gameHeight = 20; //Size of game area in tetromino block units

}
