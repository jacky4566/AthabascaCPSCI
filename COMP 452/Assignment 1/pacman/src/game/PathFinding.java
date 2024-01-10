package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.awt.Point;

/*
 * Jackson Wiebe
 * 3519635
 * 09/01/2024
 * 
 * Static path finding algorythem
 * 
 */
public class PathFinding {
    // Represent directions (up, down, left, right)
    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    /*
     * Represents a point in the grid
     */
    private static class Node {
        Point xy = new Point();
        Node previous;

        Node(Point p, Node prev) {
            this.xy = p;
            this.previous = prev;
        }
    }

    /*
     * Breadth first search
     * Performance could be improved with A* or other algorythems
     * Returns a list of directions to point
     */
    public static List<Model.direction> findPath(short[][] grid, Point start, Point end) {
        if (start.equals(end))
            return new ArrayList<>();

        int rows = grid.length;
        int cols = grid[0].length;

        // Keeps track of explored nodes
        boolean[][] explored = new boolean[rows][cols];

        // Next nodes to visit
        LinkedList<Node> nextToVisit = new LinkedList<>();

        // Points are scaled to data
        Point startSearch = new Point(start.x / CONST.BLOCK_SIZE, start.y / CONST.BLOCK_SIZE);
        Point endSearch = new Point(end.x / CONST.BLOCK_SIZE, end.y / CONST.BLOCK_SIZE);

        Node startNode = new Node(startSearch, null);
        nextToVisit.add(startNode);

        while (!nextToVisit.isEmpty()) {
            Node cur = nextToVisit.remove();

            // Mark as viewed
            explored[cur.xy.y][cur.xy.x] = true;

            // Check for exit
            if (cur.xy.equals(endSearch))
                return reconstructPath(cur);

            // Add each child node
            for (int[] direction : DIRECTIONS) {
                Node newNode = new Node(new Point(cur.xy.x + direction[0], cur.xy.y + direction[1]), cur);
                if (newNode.xy.y < 0 || newNode.xy.y >= rows || newNode.xy.x < 0 || newNode.xy.x >= cols)
                    continue;
                if (grid[newNode.xy.y][newNode.xy.x] == 1 || explored[newNode.xy.y][newNode.xy.x])
                    continue;
                nextToVisit.add(newNode);
            }
        }

        // No Path found
        return new ArrayList<>();

    }

    /*
     * Returns a list of directions to follow the path
     */
    private static List<Model.direction> reconstructPath(Node current) {
        List<Model.direction> path = new ArrayList<>();

        while (current.previous != null) {
            Node previous = current.previous;
            if (previous.xy.x < current.xy.x) {
                path.add(Model.direction.RIGHT);
            } else if (previous.xy.x > current.xy.x) {
                path.add(Model.direction.LEFT);
            } else if (previous.xy.y < current.xy.y) {
                path.add(Model.direction.DOWN);
            } else if (previous.xy.y > current.xy.y) {
                path.add(Model.direction.UP);
            }
            current = previous;
        }

        Collections.reverse(path);
        return path;
    }

}
