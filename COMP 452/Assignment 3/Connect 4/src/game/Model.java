package game;

import java.util.Random;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/*
 * Jackson Wiebe
 * 3519635
 * 08/02/2024
 * 
 * Model Class for driving the game.
 */
public class Model extends JPanel implements ActionListener, MouseListener {

    // Set some graphic parameters
    private Dimension gameArea = new Dimension(CONST.N_BLOCKS_X * CONST.BLOCK_SIZE,
            (CONST.N_BLOCKS_Y * CONST.BLOCK_SIZE) + CONST.TEXT_HEIGHT);
    private final Font font = new Font("Arial", Font.BOLD, 14);

    // A String to print information to the user
    private String console;

    private boolean playerTurn;
    private boolean playing;

    private Random rand = new Random();

    private Timer timer = new Timer(CONST.TIMER_TICK, this);

    /*
     * Game square
     * 0 = Empty Space
     * 1 = User Play, Draw white
     * 2 = Computer Play, Draw red
     */
    public enum GameSQ {
        EMPTY,
        PLAYER,
        COMPUTER,
    }

    public GameSQ[][] game_board = new GameSQ[CONST.N_BLOCKS_Y][CONST.N_BLOCKS_X];

    /*
     * Constructor, build new game enviroment
     */
    public Model() {
        setFocusable(true);
        setPreferredSize(gameArea);
        addMouseListener(this);
        initGame();
    }

    /*
     * Build up game variables for each play
     */
    private void initGame() {
        System.out.println("Game Reset");
        for (int x = 0; x < CONST.N_BLOCKS_X; x++) {
            for (int y = 0; y < CONST.N_BLOCKS_Y; y++) {
                game_board[y][x] = GameSQ.EMPTY;
            }
        }
        playerTurn = rand.nextBoolean();
        if (playerTurn)
            console = "Player Starts";
        else
            console = "Computer Starts";
        timer.start();
        playing = true;
    }

    /*
     * Main drawing loop
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, gameArea.width, gameArea.height);

        drawMaze(g2d);
        drawConsole(g2d);

        if (!playing) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            initGame();
        }

        if (playerTurn)
            drawPlayer(g2d);
        else
            computerTurn();

        if (gameAI.checkTie(game_board)) {
            playing = false;
            console = "Tie Game";
            System.out.println("Tie Game");
        }

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    /*
     * Draw Maze
     */
    private void drawMaze(Graphics2D g2d) {
        for (int y = 0; y < CONST.N_BLOCKS_Y; y++) {
            for (int x = 0; x < CONST.N_BLOCKS_X; x++) {
                g2d.setColor(Color.WHITE);
                g2d.drawRect(x * CONST.BLOCK_SIZE, y * CONST.BLOCK_SIZE, CONST.BLOCK_SIZE - 1, CONST.BLOCK_SIZE - 1);
                int diameter = CONST.BLOCK_SIZE - 1;
                switch (game_board[y][x]) {
                    case PLAYER:
                        g2d.setColor(new Color(255, 255, 255));
                        g2d.fillOval(x * CONST.BLOCK_SIZE,
                                y * CONST.BLOCK_SIZE,
                                diameter,
                                diameter);
                        break;
                    case COMPUTER:
                        g2d.setColor(new Color(255, 0, 0));
                        g2d.fillOval(x * CONST.BLOCK_SIZE,
                                y * CONST.BLOCK_SIZE,
                                diameter,
                                diameter);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /*
     * Writes a simple text line at the bottom for user instruction
     */
    private void drawConsole(Graphics2D g2d) {
        g2d.setColor(Color.yellow);
        g2d.setFont(font);
        g2d.drawString(console, 4, (int) gameArea.getHeight() - 4);
    }

    /*
     * Check for Win
     * Scans the board from the last given position
     * Takes argumen to last played position and who we need to look for a winner
     */
    private boolean checkWin(int[] pos, GameSQ target) {
        // Check Horizontal by looking at row for 4 continuous
        for (int x = 0, count = 0; x < CONST.N_BLOCKS_X; x++) {
            count = (game_board[pos[0]][x] == target) ? count + 1 : 0;
            if (count == 4)
                return true;
        }

        // Check Vertically by looking at column for 4 continuous
        for (int y = 0, count = 0; y < CONST.N_BLOCKS_Y; y++) {
            count = (game_board[y][pos[1]] == target) ? count + 1 : 0;
            if (count == 4)
                return true;
        }

        // Check for win Diagonally Top Left to Bottom Right
        // Wind position back to its top left corner
        int[] temppos = { pos[0], pos[1] };
        while (temppos[0] > 0 && temppos[1] > 0) {
            temppos[0]--;
            temppos[1]--;
        }
        // Check all in bounds positions while moving down and right
        for (int count = 0; temppos[0] < CONST.N_BLOCKS_Y
                && temppos[1] < CONST.N_BLOCKS_X; temppos[0]++, temppos[1]++) {
            count = (game_board[temppos[0]][temppos[1]] == target) ? count + 1 : 0;
            if (count == 4)
                return true;
        }

        return false;
    }

    /*
     * Parent method to impliment computers turn.
     */
    private void computerTurn() {
        int bestMove = gameAI.minimax(game_board, 6, Integer.MIN_VALUE, Integer.MAX_VALUE, false)[1];

        System.out.println("AI Move is " + bestMove);

        // Apply move
        int y = 0;
        // Perb the move down the column
        while (y < (CONST.N_BLOCKS_Y - 1)) {
            // Check if row below is full
            if (game_board[y + 1][bestMove] != GameSQ.EMPTY)
                break;
            y++;
        }
        game_board[y][bestMove] = GameSQ.COMPUTER;

        //Check for a win
        if (checkWin(new int[] { y, bestMove }, GameSQ.COMPUTER)) {
            // Computer wins
            console = "Computer Wins";
            System.out.println("Computer Win");
            playing = false;
            return;
        }

        playerTurn = !playerTurn;
        console = "Player Move";
    }

    /*
     * Perform Player Move
     */
    private void performPlayer() {
        int[] pos = mouseToDropPosition();
        if (game_board[pos[0]][pos[1]] != GameSQ.EMPTY) {
            // invalid move
            console = "Invalid Move";
            return;
        }
        game_board[pos[0]][pos[1]] = GameSQ.PLAYER;
        System.out.println("Played: X:" + pos[1] + ", Y:" + pos[0]);
        playerTurn = !playerTurn;
        if (checkWin(pos, GameSQ.PLAYER)) {
            // Player wins
            console = "Player Wins";
            System.out.println("Player Win");
            playing = false;
            return;
        }
        console = "Computer Move";
    }

    /*
     * Draws the potential user options following the mouse
     */
    private void drawPlayer(Graphics2D g2d) {
        int[] pos = mouseToDropPosition();

        // draw circle
        int diameter = CONST.BLOCK_SIZE - 1;
        g2d.setColor(new Color(255, 255, 255));
        g2d.fillOval(pos[1] * CONST.BLOCK_SIZE,
                pos[0] * CONST.BLOCK_SIZE,
                diameter,
                diameter);
    }

    /*
     * Helper function to convert mouse position to a position where a dropped coin
     * would land. Returns Y,X
     */
    private int[] mouseToDropPosition() {
        int[] pos = new int[2];
        // Convert mouse position to a column
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mouseLocation, this);
        pos[1] = Math.min(CONST.N_BLOCKS_X - 1, Math.max(((int) mouseLocation.getX() / CONST.BLOCK_SIZE), 0));

        // Find lowest slot in column
        int row = 0;
        while (row < (CONST.N_BLOCKS_Y - 1)) {
            // Check if row below is full
            if (game_board[row + 1][pos[1]] != GameSQ.EMPTY)
                break;
            row++;
        }
        pos[0] = row;

        return pos;
    }

    /*
     * Helper to copy a 2D array
     */
    public static GameSQ[][] cloneArray(GameSQ[][] src) {
        int length = src.length;
        GameSQ[][] target = new GameSQ[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        performPlayer();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
