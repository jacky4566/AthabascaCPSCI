package game;

import java.util.Random;

import game.Model.GameSQ;

/*
 * Jackson Wiebe
 * 3519635
 * 12/02/2024
 * 
 * MiniMax Algorthem.
 * 
 * Computer implimentation of a classic min / max approach for winning 2 player games. 
 * Heuristic function looks for improvements to number of connected squares.
 * 
 * We also impliment Alpha-Beta prunning to reduce computation by removing known dead branches. 
 * 
 * Learning Resource Used:
 * https://www.youtube.com/watch?v=l-hh51ncgDI
 */

public class gameAI {

    private static Random rand = new Random();

    /*
     * Min Max algorythem to determine best computer move.
     */
    public static int[] minimax(GameSQ[][] gameState, int depth, int alpha, int beta, boolean maxPlayer) {
        // Check if the function can do anything
        if (depth == 0 || checkTie(gameState))
            return new int[] { heuristic(gameState), 0 };

        // If its the players turn, find the lowest score
        if (maxPlayer) {
            int minEval = Integer.MAX_VALUE;
            int worstMove = rand.nextInt(CONST.N_BLOCKS_X);
            // For every possible move
            for (int x = 0; x < CONST.N_BLOCKS_X; x++) {
                // if the top slot is empty
                if (gameState[0][x] == GameSQ.EMPTY) {
                    // Perb the move down the column
                    int y = 0;
                    while (y < (CONST.N_BLOCKS_Y - 1)) {
                        // Check if row below is full
                        if (gameState[y + 1][x] != GameSQ.EMPTY)
                            break;
                        y++;
                    }
                    // Clone array and apply change
                    GameSQ[][] newState = Model.cloneArray(gameState);
                    newState[y][x] = GameSQ.PLAYER;
                    // evalulate new potential state
                    int eval = minimax(newState, depth - 1, alpha, beta, false)[0];
                    beta = Math.min(alpha, eval);
                    if (beta <= alpha)
                        break;
                    if (eval < minEval) {
                        minEval = eval;
                        worstMove = x;
                    }
                }
            }
            return new int[] { minEval, worstMove };
        } else {
            int maxEval = Integer.MIN_VALUE;
            int bestMove = rand.nextInt(CONST.N_BLOCKS_X);
            // For every possible move
            for (int x = 0; x < CONST.N_BLOCKS_X; x++) {
                // if the top slot is empty
                if (gameState[0][x] == GameSQ.EMPTY) {
                    // Perb the move down the column
                    int y = 0;
                    while (y < (CONST.N_BLOCKS_Y - 1)) {
                        // Check if row below is full
                        if (gameState[y + 1][x] != GameSQ.EMPTY)
                            break;
                        y++;
                    }
                    // Clone array and apply change
                    GameSQ[][] newState = Model.cloneArray(gameState);
                    newState[y][x] = GameSQ.COMPUTER;
                    // evalulate new potential state
                    int eval = minimax(newState, depth - 1, alpha, beta, true)[0];
                    alpha = Math.max(alpha, eval);
                    if (beta <= alpha)
                        break;
                    if (eval > maxEval) {
                        maxEval = eval;
                        bestMove = x;
                    }
                }
            }
            return new int[] { maxEval, bestMove };
        }
    }

    /*
     * Returns the change in game score from a move on a game board
     * The heuristic function is proportional to the number of chips that
     * are continuous in a row/column/diagonal with a multiplicative effect. This is
     * added up
     * for every row,column and diagonal.
     */
    public static int heuristic(GameSQ[][] gameState) {
        int score = 0;

        // Count Horizontal
        for (int y = 0; y < CONST.N_BLOCKS_Y; y++) {
            sequenceScorer rowScore = new sequenceScorer();
            for (int x = 0; x < CONST.N_BLOCKS_X; x++) {
                rowScore.inputVal(gameState[y][x]);
            }
            score += rowScore.getTotal();
        }

        // Count Vertical
        for (int x = 0; x < CONST.N_BLOCKS_X; x++) {
            sequenceScorer colScore = new sequenceScorer();
            for (int y = 0; y < CONST.N_BLOCKS_Y; y++) {
                colScore.inputVal(gameState[y][x]);
            }
            score += colScore.getTotal();
        }

        // Count Diagonally Top Left to Bottom Right
        int[][] startsTLBR = new int[][] { { 2, 0 }, { 1, 0 }, { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 } };
        for (int[] d : startsTLBR) {
            int dy = d[0];
            int dx = d[1];
            sequenceScorer diagScore = new sequenceScorer();
            while (dy < CONST.N_BLOCKS_Y && dx < CONST.N_BLOCKS_X) {
                diagScore.inputVal(gameState[dy][dx]);
                dy++;
                dx++;
            }
            score += diagScore.getTotal();
        }

        // Count Diagonally Top Right to Bottom Left
        int[][] startsTRBL = new int[][] { { 2, 6 }, { 1, 6 }, { 0, 6 }, { 0, 5 }, { 0, 4 }, { 0, 3 } };
        for (int[] d : startsTRBL) {
            int dy = d[0];
            int dx = d[1];
            sequenceScorer diagScore = new sequenceScorer();
            while (dy < CONST.N_BLOCKS_Y && dx > 0) {
                diagScore.inputVal(gameState[dy][dx]);
                dy++;
                dx--;
            }
            score += diagScore.getTotal();
        }

        return score;
    }

    /*
     * Helper function to debug program with command line printing
     */
    public static void boardPrinter(GameSQ[][] gameState) {
        for (int y = 0; y < CONST.N_BLOCKS_Y; y++) {
            System.out.print("|");
            for (int x = 0; x < CONST.N_BLOCKS_X; x++) {
                switch (gameState[y][x]) {
                    case COMPUTER:
                        System.out.print("c|");
                        break;
                    case PLAYER:
                        System.out.print("p|");
                        break;
                    case EMPTY:
                        System.out.print(" |");
                        break;
                    default:
                        break;
                }
            }
            System.out.println();
        }
    }

    /*
     * Looks for a tie condition, return true if no possible moves
     */
    public static boolean checkTie(GameSQ[][] gameState) {
        for (int y = 0; y < CONST.N_BLOCKS_Y; y++) {
            for (int x = 0; x < CONST.N_BLOCKS_X; x++) {
                if (gameState[y][x] == GameSQ.EMPTY)
                    return false;
            }
        }

        return true;
    }
}
