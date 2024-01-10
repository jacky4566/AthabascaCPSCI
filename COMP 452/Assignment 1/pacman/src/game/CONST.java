package game;

/*
 * Jackson Wiebe
 * 3519635
 * 09/01/2024
 * 
 * A class of global constants for the game
 */

public final class CONST {

    private CONST() {
    }

    public static final int TIMER_TICK = 60;
    public static final int PLAYER_SPEED = 4;
    public static final int GHOST_SPEED = 3;

    public static final int BLOCK_SIZE = 24;
    public static final int N_BLOCKS = 15;
    public static final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;

}
