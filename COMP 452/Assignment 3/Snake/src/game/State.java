package game;

/*
 * Jackson Wiebe
 * 3519635
 * 16/02/2024
 * 
 * A class to incorperate the state of the game for Q- Learning
 * Helpful for visualization.
 * Returns a compiled int state for processing
 * 
 */
public class State {

    //Which direction is the food
    boolean foodEast;
    boolean foodWest;
    boolean foodNorth;
    boolean foodSouth;

    //Is there immediate danger in any 4 directions
    boolean dangerEast;
    boolean dangerWest;
    boolean dangerNorth;
    boolean dangerSouth;

    public State(boolean[] stateData) {
        this.foodEast = stateData[0];
        this.foodWest = stateData[1];
        this.foodNorth = stateData[2];
        this.foodSouth = stateData[3];

        this.dangerEast = stateData[4];
        this.dangerWest = stateData[5];
        this.dangerNorth = stateData[6];
        this.dangerSouth = stateData[7];
    }

    public State() {
    }

    public int getHash() {
        int state = 0;
        state |= (foodEast ? 1 : 0) << 7;
        state |= (foodWest ? 1 : 0) << 6;
        state |= (foodNorth ? 1 : 0) << 5;
        state |= (foodSouth ? 1 : 0) << 4;
        state |= (dangerEast ? 1 : 0) << 3;
        state |= (dangerWest ? 1 : 0) << 2;
        state |= (dangerNorth ? 1 : 0) << 1;
        state |= (dangerSouth ? 1 : 0);
        return state;
    }

}
