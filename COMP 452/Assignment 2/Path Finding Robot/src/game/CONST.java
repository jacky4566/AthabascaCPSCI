package game;

import java.awt.Color;

/*
 * Jackson Wiebe
 * 3519635
 * 
 * A class of global constants for the game
 */

public final class CONST {

    private CONST() {
    }

    public static final int TIMER_TICK = 150;
    public static final int PLAYER_SPEED = 3;

    public static final int N_BLOCKS = 16;
    public static final int BLOCK_SIZE = 24;
    public static final int GAME_AREA_X = N_BLOCKS * BLOCK_SIZE;
    public static final int GAME_AREA_Y = N_BLOCKS * BLOCK_SIZE;
    public static final int CONSOLE_AREA_X = 200;
    public static final int CONSOLE_AREA_Y = GAME_AREA_Y;

    public static final Color DARK_GREEN = new Color(1, 50, 32);
}
