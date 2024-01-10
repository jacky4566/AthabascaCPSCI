package game;

import java.awt.Point;

public class Player {

    

    public Model.direction newDirection = Model.direction.RIGHT;
    private Model.direction playerDirection = Model.direction.RIGHT;
    private Point location;

    public Player() {
        location = new Point(0, 0);
    }

    void movePlayer(short[][] levelWalls) {
        // If player position is centre in block, apply new direction
        if (((location.y % CONST.BLOCK_SIZE) == 0) && ((location.x % CONST.BLOCK_SIZE) == 0))
            playerDirection = newDirection;

        // Move Player
        switch (playerDirection) {
            case DOWN:
                location.y += CONST.PLAYER_SPEED;
                break;
            case LEFT:
                location.x -= CONST.PLAYER_SPEED;
                break;
            case RIGHT:
                location.x += CONST.PLAYER_SPEED;
                break;
            case UP:
                location.y -= CONST.PLAYER_SPEED;
                break;
            default:
                break;
        }
        // Check for walls and game bounds
        int xGrid = location.x / CONST.BLOCK_SIZE;
        int yGrid = location.y / CONST.BLOCK_SIZE;
        switch (playerDirection) {
            case DOWN:
                // Check for game bounds
                if (location.y > (CONST.BLOCK_SIZE * (CONST.N_BLOCKS - 1)))
                    location.y = (CONST.BLOCK_SIZE * (CONST.N_BLOCKS - 1));
                // check for walls
                else if (yGrid >= (CONST.N_BLOCKS - 1))
                    break;
                else if (levelWalls[yGrid + 1][xGrid] == 1)
                    location.y = yGrid * CONST.BLOCK_SIZE;
                break;
            case UP:
                // Check for game bounds
                if (location.y < 0)
                    location.y = 0;
                // check for walls
                else if (levelWalls[yGrid][xGrid] == 1)
                    location.y = (yGrid + 1) * CONST.BLOCK_SIZE;
                break;
            case RIGHT:
                // Check for game bounds
                if (location.x > (CONST.BLOCK_SIZE * (CONST.N_BLOCKS - 1)))
                    location.x = (CONST.BLOCK_SIZE * (CONST.N_BLOCKS - 1));
                // check for walls
                else if (xGrid >= (CONST.N_BLOCKS - 1))
                    break;
                else if (levelWalls[yGrid][xGrid + 1] == 1)
                    location.x = xGrid * CONST.BLOCK_SIZE;
                break;
            case LEFT:
                // Check for game bounds
                if (location.x < 0)
                    location.x = 0;
                // check for walls
                else if (levelWalls[yGrid][xGrid] == 1)
                    location.x = (xGrid + 1) * CONST.BLOCK_SIZE;
                break;
            default:
                break;
        }
    }

    public Point getLoc() {
        return this.location;
    }

    public Model.direction getDir() {
        return this.playerDirection;
    }
}
