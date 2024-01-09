package game.ghosts;

import java.awt.Image;

import game.CONST;

/*
 * Jackson Wiebe
 * 3519635
 * 08/01/2024
 * 
 * Inky, works with pinky to hunt the player as a pair. First find pinky, then find player.
 * Is slightly slower than pinky
 * 
 */

public class G_Inky extends Ghost {

    public G_Inky(Image image) {
        super(image);
        this.ghostSpeed = 3;
        this.locationX = 5 * CONST.BLOCK_SIZE;
        this.locationY = 5 * CONST.BLOCK_SIZE;
    }

}
