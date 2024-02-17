package game;

import java.awt.Point;
import java.util.Random;
import java.util.Arrays;
import java.util.HashMap;

import game.Model.direction;

/*
 * Here we impliment Q-Learning to make our decisions
 */
public class AI {
    private Random rand = new Random();

    // epsilon value for making random decisions
    public int epsilon = 10;

    /*
     * Create a 2D Q table
     * Number of actions x number of states
     */
    HashMap<Integer, int[]> QTable = new HashMap<>();

    // Last state we took action on
    State currentState = new State();

    // Last Direction chosen from state
    int lastDir = 0;

    // Manhatten distance from last choice
    int distanceToGoal;

    /*
     * Function to update Q Values from last action
     * if Snake died -> Negative -100 reward
     * if Snake got further from food - Negative -1 reward
     * if Snake got food -> Positive +100 reward
     * if Snake got closer to food- > Positive +2 reward
     */
    public void updateQValue(boolean dead, Point food, Player ply) {
        // Get Q values for last state
        int[] values = QTable.get(currentState.getState());

        if (dead) {
            // Snake died, apply penalty
            values[lastDir] = values[lastDir] - 100;
            QTable.put(currentState.getState(), values);
            return;
        }

        if (food.y == ply.y && food.x == ply.x) {
            // Snake found food, apply reward
            values[lastDir] = values[lastDir] + 100;
            QTable.put(currentState.getState(), values);
            return;
        }

        // Check for distance to food
        int foodDistanceX = food.x - ply.x;
        int foodDistanceY = food.y - ply.y;

        int changeInDistance = (Math.abs(foodDistanceX) + Math.abs(foodDistanceY)) - distanceToGoal;

        if (changeInDistance < 0) {
            // Reduced distance
            values[lastDir] = values[lastDir] + 2;
            QTable.put(currentState.getState(), values);
        } else if (changeInDistance > 0) {
            // distance increased
            values[lastDir] = values[lastDir] - 1;
            QTable.put(currentState.getState(), values);
        }

    }

    public void updateState(Point food, int[][] level_data, Player ply) {
        // Get some distances to food
        int foodDistanceX = food.x - ply.x;
        int foodDistanceY = food.y - ply.y;

        // Store this for later processing
        distanceToGoal = Math.abs(foodDistanceX) + Math.abs(foodDistanceY);

        // Is there any immediate danger in the cardinal directions
        boolean dangerEast = (ply.x == (CONST.N_BLOCKS - 1))
                || (ply.x < (CONST.N_BLOCKS - 1) && level_data[ply.y][ply.x + 1] > 0);
        boolean dangerWest = (ply.x == 0)
                || (ply.x > 0 && level_data[ply.y][ply.x - 1] > 0);
        boolean dangerNorth = (ply.y == 0)
                || (ply.y > 0 && level_data[ply.y - 1][ply.x] > 0);
        boolean dangerSouth = (ply.y == (CONST.N_BLOCKS - 1))
                || ply.y < (CONST.N_BLOCKS - 1) && level_data[ply.y + 1][ply.x] > 0;

        currentState = new State(new boolean[] {
                foodDistanceX > 0,
                foodDistanceX < 0,
                foodDistanceY < 0,
                foodDistanceY > 0,
                dangerEast,
                dangerWest,
                dangerNorth,
                dangerSouth,
        });

        // This state has not been seen yet, add to the QTable
        if (!QTable.containsKey(currentState.getState())) {
            QTable.put(currentState.getState(), new int[] { 0, 0, 0, 0 });
            System.out.println("QTable size " + QTable.size());
        }
    }

    /*
     * Main decision making function, returns a new direction
     */
    public direction process() {
        // Epsilon Greedy, Do random action
        if ((rand.nextInt(100) + 1 < epsilon)) {
            lastDir = rand.nextInt(4);
        } else {
            // Find the largest Q values for the current state
            int[] Qvalues = QTable.get(currentState.getState());
            int largestQ = Integer.MIN_VALUE;
            int indexLargestQ = 0;
            for (int i = 0; i < Qvalues.length; i++) {
                if (Qvalues[i] > largestQ) {
                    largestQ = Qvalues[i];
                    indexLargestQ = i;
                }
            }
            lastDir = indexLargestQ;
        }

        return direction.values()[lastDir];
    }
}
