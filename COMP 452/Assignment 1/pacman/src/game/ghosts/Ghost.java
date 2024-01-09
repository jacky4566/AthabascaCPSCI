package game.ghosts;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Ghost {

    int ghostSpeed = 4;
    public Image image;
    public int locationX, locationY;

    public enum State {
        WANDER,
        SEEK
    }

    ImageIcon icon;
    State myState;

    public Ghost(Image image) {
        this.image = image;
        this.myState = State.WANDER;
    }

}
