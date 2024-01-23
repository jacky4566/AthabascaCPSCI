package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import game.Model.placements;
import game.PathFinding.Direction;

/*
 * Jackson Wiebe
 * 3519635
 * 21/01/2024
 * 
 * Class Ant, 
 * Contains a state machine and basic logic for each ant
 * 
 * Ant follows this logic
 * 
    - The ants move randomly in their environment in an attempt to locate a piece of food.
    - When an ant finds a piece of food, it returns to its home position. You can implement the path to its home position as you wish.
    - When an ant arrives home, it (a) becomes thirsty, (b) drops its food, and (c) starts a search for drinking water.
    - Ants looking for water roam randomly in their environment in their attempt to find water.
    - When an ant finds water, it drinks and then resumes its search for food.
    - New ants are born in the colony. Every time an ant returns food to the home position, a new ant is added (born) to the team that will start a search for food.
    - The ant population continues to grow as long as more food is brought home.
    - The environment is a grilled area with cells that may be of different types: empty ground, food, water or poison.
    - Food, water and poison are randomly placed in the environment.
    - Ants can walk safely on empty ground, food and water cells; however, any ant that steps on a poison cell must die.  
    - The only thing the user has to specify is the starting number of ants in the colony. Food, water and poison should be generated and randomly distributed by the program.
    - Ants must be roaming randomly when looking for food or water; otherwise, use your creativity to implement other movements (i.e., returning to the home position).
 */

public class Ant {

    enum moveResult {
        DEATH, // Ant found poison
        HOME, // Ant is home
        MOVE, // Regular Move
    }

    public PathFinding.Direction antDirection = PathFinding.Direction.RIGHT;
    public Point location;
    public placements target = placements.FOOD;
    public int age = 0;
    public List<Direction> pathHome = new ArrayList<>(); // Ants know the way home

    public Ant() {
        this.location = new Point((int) CONST.HOME.getX() * CONST.BLOCK_SIZE,
                (int) CONST.HOME.getY() * CONST.BLOCK_SIZE);
    }

    /*
     * Move Ant through the field.
     * Return various results
     */
    public moveResult move(placements[][] levelData) {
        switch (target) {
            case FOOD:
            case WATER:
                // Move randomly looking for food and water
                Point nextRandomMove = moveRandom();
                if (nextRandomMove.getX() < 0 || nextRandomMove.getX() >= (CONST.N_BLOCKS * CONST.BLOCK_SIZE)
                        || nextRandomMove.getY() < 0 || nextRandomMove.getY() >= (CONST.N_BLOCKS * CONST.BLOCK_SIZE))
                    return moveResult.MOVE; // Random move was not valid
                if (levelData[(int) nextRandomMove.getX() / CONST.BLOCK_SIZE][(int) nextRandomMove.getY()
                        / CONST.BLOCK_SIZE] != placements.OBSTACLE)
                    this.location = nextRandomMove; // Random move was valid
                break;
            case HOME:
                // Use A* to go home
                if (pathHome.isEmpty()) {
                    // generate a new path home
                    pathHome = PathFinding.findPath(levelData, this.location.x / CONST.BLOCK_SIZE,
                            this.location.y / CONST.BLOCK_SIZE, CONST.HOME.x, CONST.HOME.y);
                    System.out.println("Going Home " + pathHome);
                } else {
                    // follow path
                    switch (pathHome.remove(0)) {
                        case DOWN:
                            location.translate(0, CONST.BLOCK_SIZE);
                            break;
                        case LEFT:
                            location.translate(-CONST.BLOCK_SIZE, 0);
                            break;
                        case RIGHT:
                            location.translate(CONST.BLOCK_SIZE, 0);
                            break;
                        case UP:
                            location.translate(0, -CONST.BLOCK_SIZE);
                            break;
                        default:
                            break;
                    }
                }
                break;
            default:
                break;
        }

        // Check for collision
        switch (levelData[(int) location.getX() / CONST.BLOCK_SIZE][(int) location.getY() / CONST.BLOCK_SIZE]) {
            case OPEN:
                // Empty
                break;
            case OBSTACLE:
                // Obstacle, Why is there an ant here?
                System.out.println("Ant Died");
                return moveResult.DEATH;
            case HOME:
                if (target != placements.HOME)
                    break;
                target = placements.WATER;
                System.out.println("Looking for Water");
                return moveResult.HOME;
            case FOOD:
                // Food
                if (target != placements.FOOD)
                    break;
                // Go Home
                target = placements.HOME;
                // Carry food
                levelData[(int) location.getX() / CONST.BLOCK_SIZE][(int) location.getY()
                        / CONST.BLOCK_SIZE] = placements.OPEN;
                break;
            case WATER:
                // Water
                if (target != placements.WATER)
                    break;
                // Go Home
                target = placements.FOOD;
                System.out.println("Looking for Food");
                // consume water
                levelData[(int) location.getX() / CONST.BLOCK_SIZE][(int) location.getY()
                        / CONST.BLOCK_SIZE] = placements.OPEN;
                break;
            case POISON:
                // Poison
                // Consume poison
                System.out.println("Ant Died");
                levelData[(int) location.getX() / CONST.BLOCK_SIZE][(int) location.getY()
                        / CONST.BLOCK_SIZE] = placements.OPEN;
                return moveResult.DEATH;
            default:
                break;
        }
        return moveResult.MOVE;
    }

    /*
     * Function to generate a new random position in a cardinal direction from home
     */
    private Point moveRandom() {
        Point nexPoint = new Point(location);
        switch (Model.random.nextInt(4)) {
            case 0:
                nexPoint.translate(CONST.BLOCK_SIZE, 0);
                break;
            case 1:
                nexPoint.translate(-CONST.BLOCK_SIZE, 0);
                break;
            case 2:
                nexPoint.translate(0, CONST.BLOCK_SIZE);
                break;
            case 3:
                nexPoint.translate(0, -CONST.BLOCK_SIZE);
                break;
            default:
                break;
        }
        return nexPoint;
    }

}
