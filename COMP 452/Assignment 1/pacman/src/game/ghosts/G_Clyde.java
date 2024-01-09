package game.ghosts;

import java.awt.Image;

import game.CONST;

/*
 * Jackson Wiebe
 * 3519635
 * 08/01/2024
 * 
 * Clyde, alternates between chasing and running away from the player
 * 
 */

public class G_Clyde extends Ghost {

    public G_Clyde(Image image){
        super(image);
        this.ghostSpeed = 3;
        this.locationX = 10 * CONST.BLOCK_SIZE;
        this.locationY = 10 * CONST.BLOCK_SIZE;
    }

}
