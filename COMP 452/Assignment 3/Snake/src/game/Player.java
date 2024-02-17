package game;

import game.Model.direction;

/*
 * Class for our long boi
 */
public class Player {
    // What direction is the player requesting
    direction newDirection = Model.direction.DOWN;

    direction prevDirection = Model.direction.DOWN;

    // Where is the head of the snake
    int x;
    int y;

    // How long is the snake
    int length = 0;

    //Was the last move legal
    public boolean moved;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
