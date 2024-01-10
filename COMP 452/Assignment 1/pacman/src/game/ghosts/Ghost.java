package game.ghosts;

import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import game.CONST;
import game.Model;
import game.PathFinding;
import game.Player;

/*
 * Jackson Wiebe
 * 3519635
 * 09/01/2024
 */

public class Ghost {

    int ghostSpeed = CONST.GHOST_SPEED;
    public Image image;
    public Point location = new Point();
    public Model.direction direction = Model.direction.RIGHT;
    Random random = new Random();

    ImageIcon icon;

    public Ghost(Image image) {
        this.image = image;
    }

    /*
     * Each ghost will have different movement algo
     */
    public void move(short[][] level_walls, Player player, Map<Class<? extends Ghost>, Ghost> ghosts) {

    }

    /*
     * Find path to target and return distance in number of spaces to travel
     */
    int seek(short[][] level_walls, Point target) {
        List<Model.direction> pathToTarget = PathFinding.findPath(level_walls, this.location, target);
        if (pathToTarget.isEmpty())
            // Pathing was unsucceful, Assign random direction
            wander();
        else
            this.direction = pathToTarget.get(0);

        return pathToTarget.size();
    }

    /*
     * Similar to Seek with opposited direction
     */
    int flea(short[][] level_walls, Point target) {
        List<Model.direction> pathToPlayer = PathFinding.findPath(level_walls, this.location, target);
        if (pathToPlayer.size() > 0) {
            switch (pathToPlayer.get(0)) {
                case DOWN:
                    this.direction = Model.direction.UP;
                    break;
                case LEFT:
                    this.direction = Model.direction.RIGHT;
                    break;
                case RIGHT:
                    this.direction = Model.direction.LEFT;
                    break;
                case UP:
                    this.direction = Model.direction.DOWN;
                    break;
                default:
                    break;

            }
        }
        return pathToPlayer.size();
    }

    /*
     * Seeks out a target only after seeking its squad mates.
     */
    void seekFormation(short[][] level_walls, Point target, Map<Class<? extends Ghost>, Ghost> squad) {
        // Find squad Center
        Point squadCentre = centroid(squad);
        // Check if squad is tight
        if (squadTight(squad, squadCentre)) {
            // Move centriod towards Player
            seek(level_walls, target);
        } else
            seek(level_walls, squadCentre);
    }

    /*
     * Simply returns a random direction
     */
    void wander() {
        Model.direction[] directions = Model.direction.values();
        this.direction = directions[this.random.nextInt(directions.length)];
    }

    private Point centroid(Map<Class<? extends Ghost>, Ghost> squad) {
        int squadSize = squad.size();
        int centroidX = 0;
        int centroidY = 0;
        for (Ghost ghost : squad.values()) {
            centroidX += ghost.location.x;
            centroidY += ghost.location.y;
        }
        return new Point(centroidX / squadSize, centroidY / squadSize);
    }

    private boolean squadTight(Map<Class<? extends Ghost>, Ghost> squad, Point squadCentre) {
        for (Ghost ghost : squad.values()) {
            int distanceToCentre = (int) Point2D.distance(squadCentre.x, squadCentre.y, ghost.location.x,
                    ghost.location.y);
            if (distanceToCentre > (2 * CONST.BLOCK_SIZE))
                return false;
        }
        return true;
    }

    /*
     * Function to move ghost within level bounds
     */
    void moveWithBounds(short[][] level_walls) {
        switch (this.direction) {
            case DOWN:
                location.y += ghostSpeed;
                break;
            case LEFT:
                location.x -= ghostSpeed;
                break;
            case RIGHT:
                location.x += ghostSpeed;
                break;
            case UP:
                location.y -= ghostSpeed;
                break;
            default:
                break;
        }

        // Check for walls and game bounds
        int xGrid = location.x / CONST.BLOCK_SIZE;
        int yGrid = location.y / CONST.BLOCK_SIZE;
        switch (this.direction) {
            case DOWN:
                // Check for game bounds
                if (location.y > (CONST.BLOCK_SIZE * (CONST.N_BLOCKS - 1)))
                    location.y = (CONST.BLOCK_SIZE * (CONST.N_BLOCKS - 1));
                // check for walls
                else if (yGrid >= (CONST.N_BLOCKS - 1))
                    break;
                else if (level_walls[yGrid + 1][xGrid] == 1)
                    location.y = yGrid * CONST.BLOCK_SIZE;
                break;
            case UP:
                // Check for game bounds
                if (location.y < 0)
                    location.y = 0;
                // check for walls
                else if (level_walls[yGrid][xGrid] == 1)
                    location.y = (yGrid + 1) * CONST.BLOCK_SIZE;
                break;
            case RIGHT:
                // Check for game bounds
                if (location.x > (CONST.BLOCK_SIZE * (CONST.N_BLOCKS - 1)))
                    location.x = (CONST.BLOCK_SIZE * (CONST.N_BLOCKS - 1));
                // check for walls
                else if (xGrid >= (CONST.N_BLOCKS - 1))
                    break;
                else if (level_walls[yGrid][xGrid + 1] == 1)
                    location.x = xGrid * CONST.BLOCK_SIZE;
                break;
            case LEFT:
                // Check for game bounds
                if (location.x < 0)
                    location.x = 0;
                // check for walls
                else if (level_walls[yGrid][xGrid] == 1)
                    location.x = (xGrid + 1) * CONST.BLOCK_SIZE;
                break;
            default:
                break;
        }
    }

    Model.direction invertDirection(Model.direction dir) {
        switch (dir) {
            case DOWN:
                return Model.direction.UP;
            case LEFT:
                return Model.direction.RIGHT;
            case RIGHT:
                return Model.direction.LEFT;
            case UP:
                return Model.direction.DOWN;
            default:
                return Model.direction.UP;

        }

    }

}
