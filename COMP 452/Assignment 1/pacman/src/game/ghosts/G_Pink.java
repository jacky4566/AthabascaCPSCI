package game.ghosts;

import java.awt.Image;

import game.CONST;

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
        this.ghostSpeed = 4;
        this.locationX = 0 * CONST.BLOCK_SIZE;
        this.locationY = 5 * CONST.BLOCK_SIZE;
    }

}
