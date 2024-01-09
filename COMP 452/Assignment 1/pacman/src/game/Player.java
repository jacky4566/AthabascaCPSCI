package game;

public class Player {

    private static final int PLAYER_SPEED = 3;

    public Model.direction playerDirection = Model.direction.RIGHT;;
    public Model.direction newDirection = Model.direction.RIGHT;;
    public int locationX, locationY;

    void movePlayer(short[][] levelWalls) {
        // If player position is centre in block, apply new direction
        if (((locationY % CONST.BLOCK_SIZE) == 0) && ((locationX % CONST.BLOCK_SIZE) == 0))
            playerDirection = newDirection;

        // Move Player
        switch (playerDirection) {
            case DOWN:
                locationY += PLAYER_SPEED;
                break;
            case LEFT:
                locationX -= PLAYER_SPEED;
                break;
            case RIGHT:
                locationX += PLAYER_SPEED;
                break;
            case UP:
                locationY -= PLAYER_SPEED;
                break;
            default:
                break;
        }
        // Check for walls and game bounds
        int xGrid = locationX / CONST.BLOCK_SIZE;
        int yGrid = locationY / CONST.BLOCK_SIZE;
        switch (playerDirection) {
            case DOWN:
                // Check for game bounds
                if (locationY > (CONST.BLOCK_SIZE * (CONST.N_BLOCKS - 1)))
                    locationY = (CONST.BLOCK_SIZE * (CONST.N_BLOCKS - 1));
                // check for walls
                else if (yGrid >= (CONST.N_BLOCKS - 1))
                    break;
                else if (levelWalls[yGrid + 1][xGrid] == 1)
                    locationY = yGrid * CONST.BLOCK_SIZE;
                break;
            case UP:
                // Check for game bounds
                if (locationY < 0)
                    locationY = 0;
                // check for walls
                else if (levelWalls[yGrid][xGrid] == 1)
                    locationY = (yGrid + 1) * CONST.BLOCK_SIZE;
                break;
            case RIGHT:
                // Check for game bounds
                if (locationX > (CONST.BLOCK_SIZE * (CONST.N_BLOCKS - 1)))
                    locationX = (CONST.BLOCK_SIZE * (CONST.N_BLOCKS - 1));
                // check for walls
                else if (xGrid >= (CONST.N_BLOCKS - 1))
                    break;
                else if (levelWalls[yGrid][xGrid + 1] == 1)
                    locationX = xGrid * CONST.BLOCK_SIZE;
                break;
            case LEFT:
                // Check for game bounds
                if (locationX < 0)
                    locationX = 0;
                // check for walls
                else if (levelWalls[yGrid][xGrid] == 1)
                    locationX = (xGrid + 1) * CONST.BLOCK_SIZE;
                break;
            default:
                break;
        }
    }
}
