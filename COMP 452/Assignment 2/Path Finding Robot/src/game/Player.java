package game;

import java.awt.Point;

/*
 * Jackson Wiebe 
 * 3519635
 * 21/01/2024
 * 
 * Class of player that interacts with the game.
 * 
 */

public class Player {
    public PathFinding.Direction playerDirection = PathFinding.Direction.RIGHT;
    public Point location;
    public int stepInSequence = 0;
    public int travelCost = 0;

    public Player(Point start) {
        this.location = start;
    }
}
