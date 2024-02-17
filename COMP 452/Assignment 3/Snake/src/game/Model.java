package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.JPanel;

/*
 * Jackson Wiebe
 * 3519635
 * 08/02/2024
 * 
 * Model Class for driving the game.
 */
public class Model extends JPanel implements ActionListener {

    // Set some graphic parameters
    private Dimension gameArea = new Dimension(CONST.SCREEN_SIZE, CONST.SCREEN_SIZE);

    private Timer timer = new Timer(CONST.TIMER_TICK_FAST, this);

    private int gameCounter = 1;
    private int highScore = 0;

    private boolean playing = false;

    private Random rand = new Random();

    private AI frank = new AI();

    public enum direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private Player player = new Player(5, 5);

    /*
     * Level data
     * 0 = empty space
     * 1+ = snake tail, higher number = longer tail
     */
    public int[][] level_data = new int[CONST.N_BLOCKS][CONST.N_BLOCKS];

    public Point food = new Point();

    /*
     * Constructor, build new game enviroment
     */
    public Model() {
        setFocusable(true);
        setPreferredSize(gameArea);
        timer.start();
        initGame();
    }

    /*
     * Build up game variables for each play
     */
    private void initGame() {
        // clear level
        for (int y = 0; y < CONST.N_BLOCKS; y++) {
            for (int x = 0; x < CONST.N_BLOCKS; x++) {
                level_data[y][x] = 0;
            }
        }
        /*if (highScore > 20) {
            // slow down model
            System.out.println("Learning goal reached");
            timer.setDelay(CONST.TIMER_TICK_SLOW);
        }*/
        //adjust learning factor based on high score
        frank.epsilon = Math.max(0, 12 - highScore);

        placeFood();
        player = new Player(5, 5);
        playing = true;
    }

    /*
     * Main drawing loop
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        if (!playing)
            initGame();

        // Update state and process frank AI
        frank.updateState(food, level_data, player);
        processDirection(frank.process());

        moveSnake();

        // process results of our decsion
        frank.updateQValue(checkGameOver(), food, player);

        drawGame(g);

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    /*
     * Place Food randomly where snake is not.
     * Return True when grid is full of snake
     */
    private void placeFood() {
        int placeX = rand.nextInt(CONST.N_BLOCKS);
        int placeY = rand.nextInt(CONST.N_BLOCKS);
        int counter = CONST.N_BLOCKS * CONST.N_BLOCKS;

        while (counter > 0) {
            counter--;
            if (level_data[placeY][placeX] != 0) {
                placeX++;
                if (placeX >= CONST.N_BLOCKS) {
                    placeX = 0;
                    placeY++;
                    if (placeY >= CONST.N_BLOCKS) {
                        placeY = 0;
                    }
                }
            } else {
                food = new Point(placeX, placeY);
                return;
            }
        }
    }

    /*
     * Check for game over condition
     */
    private boolean checkGameOver() {
        // if next space is a body section or game edge, game over
        int checkX = player.x;
        int checkY = player.y;
        // Check for out of bounds
        if ((checkX < 0) || (checkX >= CONST.N_BLOCKS) || (checkY < 0) || (checkY >= CONST.N_BLOCKS)) {
            gameover();
            return true;
        }

        // Check if we ate our tail
        if ((level_data[checkY][checkX] >= 1)) {
            gameover();
            return true;
        }

        // if there is empty space, keep playing
        for (int y = 0; y < CONST.N_BLOCKS; y++) {
            for (int x = 0; x < CONST.N_BLOCKS; x++) {
                if (level_data[y][x] == 0)
                    return false;
            }
        }

        // game over
        gameover();
        return true;
    }

    private void gameover() {
        System.out.println("Game " + gameCounter++ + " Score: " + player.length);
        highScore = Math.max(player.length, highScore);
        playing = false;
    }

    /*
     * Process inputs and move snake
     */
    private void moveSnake() {
        // Store new tail section in current head location
        level_data[player.y][player.x] = player.length;
        // Set moved flag for erasing tail
        player.moved = false;
        // Move head
        switch (player.newDirection) {
            case DOWN:
                player.y++;
                player.moved = true;
                player.prevDirection = player.newDirection;
                break;
            case LEFT:
                player.x--;
                player.moved = true;
                player.prevDirection = player.newDirection;
                break;
            case RIGHT:
                player.x++;
                player.moved = true;
                player.prevDirection = player.newDirection;
                break;
            case UP:
                player.y--;
                player.moved = true;
                player.prevDirection = player.newDirection;
                break;
            default:
                break;
        }
        // if head is on food, grow
        if (food.y == player.y && food.x == player.x) {
            player.length++;
            placeFood();
        }

    }

    /*
     * Draw game area
     */
    private void drawGame(Graphics g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, gameArea.width, gameArea.height);

        // Draw Game Area
        for (int y = 0; y < CONST.N_BLOCKS; y++) {
            for (int x = 0; x < CONST.N_BLOCKS; x++) {
                g2d.setColor(Color.darkGray);
                g2d.drawRect(x * CONST.BLOCK_SIZE, y * CONST.BLOCK_SIZE, CONST.BLOCK_SIZE, CONST.BLOCK_SIZE);
                if (level_data[y][x] > 0) {
                    // Draw Snake
                    int diameter = CONST.BLOCK_SIZE;
                    g2d.setColor(new Color(0, 255, 50));
                    g2d.fillOval((x * CONST.BLOCK_SIZE),
                            (y * CONST.BLOCK_SIZE),
                            diameter, diameter);
                    // reduce length amount so tail shrinks
                    if (player.moved)
                        level_data[y][x]--;
                }
            }
        }
        // Draw Snake head
        int diameter = CONST.BLOCK_SIZE;
        g2d.setColor(new Color(0, 255, 200));
        g2d.fillOval((player.x * CONST.BLOCK_SIZE),
                (player.y * CONST.BLOCK_SIZE),
                diameter, diameter);

        // Draw Food
        int diameterFood = CONST.BLOCK_SIZE / 2;
        g2d.setColor(new Color(255, 0, 100));
        g2d.fillOval((food.x * CONST.BLOCK_SIZE) + (diameterFood / 2),
                (food.y * CONST.BLOCK_SIZE) + (diameterFood / 2),
                diameterFood, diameterFood);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private void processDirection(direction newDir) {
        switch (newDir) {
            case DOWN:
                player.newDirection = Model.direction.DOWN;
                break;
            case LEFT:
                player.newDirection = Model.direction.LEFT;
                break;
            case RIGHT:
                player.newDirection = Model.direction.RIGHT;
                break;
            case UP:
                player.newDirection = Model.direction.UP;
                break;
            default:
                break;
        }
    }

}
