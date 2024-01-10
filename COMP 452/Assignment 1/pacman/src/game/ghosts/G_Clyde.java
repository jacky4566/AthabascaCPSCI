package game.ghosts;

import java.awt.Image;
import java.util.Map;
import java.awt.Point;

import game.CONST;
import game.Model;
import game.Player;

/*
 * Jackson Wiebe
 * 3519635
 * 08/01/2024
 * 
 * Clyde, Will evade pacman when distance is less than 8 squares, otherwise wander.
 * 
 */

public class G_Clyde extends Ghost {

    public G_Clyde(Image image) {
        super(image);
        this.ghostSpeed = CONST.GHOST_SPEED - 1;
        this.location = new Point(10 * CONST.BLOCK_SIZE, 10 * CONST.BLOCK_SIZE);
        this.direction = Model.direction.LEFT;
    }

    /*
     * Clyde Logic is to flee the player when his distance is less than 8 square
     * from
     * the player then wander.
     * Clyde is "Doing his own thing"
     */
    @Override
    public void move(short[][] level_walls, Player player, Map<Class<? extends Ghost>, Ghost> ghosts) {
        if (this.location.x % CONST.BLOCK_SIZE == 0 && this.location.y % CONST.BLOCK_SIZE == 0) {
            // Ghost needs a new direction
            if (flea(level_walls, player.getLoc()) > 8) {
                // Wander if we are farther than 8 squares
                wander();
            }

        }
        moveWithBounds(level_walls);
    }
}
