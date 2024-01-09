package game.ghosts;

import java.awt.Image;

import game.CONST;

/*
 * Jackson Wiebe
 * 3519635
 * 08/01/2024
 * 
 * Blinky, Aggresively chases the player
 * 
 */

public class G_Blink extends Ghost {

    public G_Blink(Image image){
        super(image);
        this.ghostSpeed = 6;
        this.locationX = 13 * CONST.BLOCK_SIZE;
        this.locationY = 2 * CONST.BLOCK_SIZE;
    }

}
