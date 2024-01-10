package game.ghosts;

import java.awt.Image;
import java.awt.Point;
import java.util.Map;

import game.CONST;
import game.Player;

/*
 * Jackson Wiebe
 * 3519635
 * 08/01/2024
 * 
 * Blinky, Aggressive asshole who pursuses pacman future position
 * 
 */

public class G_Blink extends Ghost {

    private static final int SEEKAHEAD = (4 * CONST.BLOCK_SIZE); // How far blinky will seek

    public G_Blink(Image image) {
        super(image);
        this.location = new Point(13 * CONST.BLOCK_SIZE, 2 * CONST.BLOCK_SIZE);
    }

    /*
     * Blinky is a hunter. He tracks pacman 4 square ahead
     */
    @Override
    public void move(short[][] level_walls, Player player, Map<Class<? extends Ghost>, Ghost> ghosts) {
        if (this.location.x % CONST.BLOCK_SIZE == 0 && this.location.y % CONST.BLOCK_SIZE == 0) {
            // Ghost needs a new direction
            Point predictPlayer = new Point(player.getLoc());
            switch (player.getDir()) {
                case DOWN:
                    predictPlayer.y += SEEKAHEAD;
                    break;
                case LEFT:
                    predictPlayer.x -= SEEKAHEAD;
                    break;
                case RIGHT:
                    predictPlayer.x += SEEKAHEAD;
                    break;
                case UP:
                    predictPlayer.y -= SEEKAHEAD;
                    break;
                default:
                    break;

            }
            seek(level_walls, predictPlayer);
        }

        moveWithBounds(level_walls);
    }

}
