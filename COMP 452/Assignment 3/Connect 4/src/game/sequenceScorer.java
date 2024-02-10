package game;

import game.Model.GameSQ;

/*
 * Helper class to score sequences of values
 * Consecutive values will 4x the adder to give more influcence to longer chains
 */
public class sequenceScorer {
    private int sequenceScore = 0;
    private int adder = 1;
    private GameSQ lastSeen = GameSQ.EMPTY;

    public void inputVal(GameSQ input) {
        switch (input) {
            case COMPUTER:
                if (lastSeen == GameSQ.COMPUTER) {
                    adder = adder * 4;
                    sequenceScore += adder;
                } else {
                    adder = 1;
                    sequenceScore += adder;
                }
                break;
            case EMPTY:
                // No effect
                break;
            case PLAYER:
                if (lastSeen == GameSQ.PLAYER) {
                    adder = adder * 4;
                    sequenceScore -= adder;
                } else {
                    adder = 1;
                    sequenceScore -= adder;
                }
                break;
            default:
                break;
        }
        lastSeen = input;
    }

    public int getTotal() {
        return sequenceScore;
    }
}
