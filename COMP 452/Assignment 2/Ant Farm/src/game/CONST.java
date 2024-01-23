package game;

import java.awt.Point;

/*
 * Jackson Wiebe
 * 3519635
 * 
 * A class of global constants for the game
 */

public final class CONST {

    private CONST() {
    }

    public static final int TIMER_TICK = 200;
    public static final int FOOD_TICK = 150;
    public static final int WATER_TICK = 150;
    public static final int POISON_TICK = 150;

    public static final Point HOME = new Point(15, 15);

    public static final int MAX_WATER = 50;
    public static final int MAX_FOOD = 50;
    public static final int MAX_POISON = 50;

    public static final int N_BLOCKS = 30;
    public static final int BLOCK_SIZE = 24;
    public static final int GAME_AREA_X = N_BLOCKS * BLOCK_SIZE;
    public static final int GAME_AREA_Y = N_BLOCKS * BLOCK_SIZE;
    public static final int CONSOLE_AREA_X = 200;
    public static final int CONSOLE_AREA_Y = GAME_AREA_Y;
}
