package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import game.Model.placements;

/*
 * Jackson Wiebe
 * 3519635
 * 21/01/2024
 * 
 * Static implimentation of AStar path finding algo
 * 
 */
public class PathFinding {

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    /*
     * Node for each location we look at.
     */
    public static class Node implements Comparable<Node> {
        int x, y;
        int g, h; // g: cost from start, h: heuristic (estimated cost to goal)

        public Node(int x, int y, int g, int h) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.h = h;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.g + this.h, other.g + other.h);
        }
    }

    /*
     * Main function for path finding, returns a list of instructions to reach
     * finish
     */
    public static List<Direction> findPath(placements[][] levelData, int startX, int startY, int goalX, int goalY) {
        int width = levelData.length;
        int height = levelData[0].length;

        // Create an array of cells already looked at
        boolean[][] visited = new boolean[width][height];

        // Start our prioirty queue of nodes to look at
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        openSet.add(new Node(startX, startY, 0, heuristic(startX, startY, goalX, goalY)));

        int[][] cameFromX = new int[width][height];
        int[][] cameFromY = new int[width][height];

        int[][] costSoFar = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cameFromX[x][y] = -1;
                cameFromY[x][y] = -1;
                costSoFar[x][y] = Integer.MAX_VALUE;
            }
        }

        costSoFar[startX][startY] = 0;

        // Explore
        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.x == goalX && current.y == goalY) {
                // Reconstruct the path
                return reconstructPath(cameFromX, cameFromY, startX, startY, goalX, goalY);
            }

            visited[current.x][current.y] = true;

            // Explore neighbors
            int[] dx = { 0, 1, 0, -1 };
            int[] dy = { -1, 0, 1, 0 };

            for (int i = 0; i < 4; i++) {
                int neighborX = current.x + dx[i];
                int neighborY = current.y + dy[i];

                if (neighborX >= 0 && neighborX < width && neighborY >= 0 && neighborY < height
                        && levelData[neighborX][neighborY] != placements.OBSTACLE && !visited[neighborX][neighborY]) {

                    int newCost = costSoFar[current.x][current.y] + getCost(levelData[neighborX][neighborY]);

                    if (newCost < costSoFar[neighborX][neighborY]) {
                        costSoFar[neighborX][neighborY] = newCost;
                        cameFromX[neighborX][neighborY] = current.x;
                        cameFromY[neighborX][neighborY] = current.y;

                        openSet.add(
                                new Node(neighborX, neighborY, newCost, heuristic(neighborX, neighborY, goalX, goalY)));
                    }
                }
            }
        }

        // No path found
        return Collections.emptyList();
    }

    /*
     * Helper function to reverse path end to start, generating our list of
     * instructions.
     */
    private static List<Direction> reconstructPath(int[][] cameFromX, int[][] cameFromY, int startX, int startY,
            int goalX,
            int goalY) {
        List<Direction> path = new ArrayList<>();

        int currentX = goalX;
        int currentY = goalY;

        while (currentX != startX || currentY != startY) {
            int nextX = cameFromX[currentX][currentY];
            int nextY = cameFromY[currentX][currentY];

            if (nextX < currentX) {
                path.add(Direction.RIGHT);
            } else if (nextX > currentX) {
                path.add(Direction.LEFT);
            } else if (nextY < currentY) {
                path.add(Direction.DOWN);
            } else if (nextY > currentY) {
                path.add(Direction.UP);
            }

            currentX = nextX;
            currentY = nextY;
        }

        Collections.reverse(path);
        return path;
    }

    /*
     * Heuristic function to determin Manhattan distance between any two points.
     * Manhattan is faster to calculate than using pythag float point math.
     * Typically overkill for 2D grids.
     */
    private static int heuristic(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    /*
     * Returns the actual cost of each terrain type
     */
    public static int getCost(placements terrainType) {
        // Define the costs for different terrain types
        switch (terrainType) {
            case OPEN: 
                return 1;
            case FOOD: 
                return 1;
            case WATER: 
                return 1;
            case POISON: // Really Avoid Poison
                return 100;
            default:
                return Integer.MAX_VALUE; // Impassable obstacle
        }
    }
}
