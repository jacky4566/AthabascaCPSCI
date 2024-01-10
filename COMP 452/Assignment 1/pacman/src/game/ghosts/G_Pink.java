package game.ghosts;

import java.awt.Image;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import game.CONST;
import game.Player;

/*
 * Jackson Wiebe
 * 3519635
 * 08/01/2024
 * 
 * Pinky, works with Inky to hunt the player as a pair. First find Inky, then find player.
 * 
 */

public class G_Pink extends Ghost {

    public G_Pink(Image image) {
        super(image);
        this.ghostSpeed = 2;
        this.location = new Point(0 * CONST.BLOCK_SIZE, 5 * CONST.BLOCK_SIZE);
    }

    /*
     * Move in a squad with Inky to player
     */
    @Override
    public void move(short[][] level_walls, Player player, Map<Class<? extends Ghost>, Ghost> ghosts) {
        if (this.location.x % CONST.BLOCK_SIZE == 0 && this.location.y % CONST.BLOCK_SIZE == 0) {
            // Ghost needs a new direction
            // Inky and Pinky form a squad
            Map<Class<? extends Ghost>, Ghost> squad = new HashMap<>();
            // Add squad members
            squad.put(G_Inky.class, ghosts.get(G_Inky.class));
            squad.put(G_Pink.class, ghosts.get(G_Pink.class));

            seekFormation(level_walls, player.getLoc(), squad);
        }

        moveWithBounds(level_walls);
    }

}
