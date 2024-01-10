package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.ghosts.G_Blink;
import game.ghosts.G_Clyde;
import game.ghosts.G_Inky;
import game.ghosts.G_Pink;
import game.ghosts.Ghost;

/*
 * Jackson Wiebe
 * 3519635
 * 08/01/2024
 * 
 * Model Class for driving the game.
 */

public class Model extends JPanel implements ActionListener {

    // Set some graphic parameters
    private Dimension d = new Dimension(400, 400);
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);

    // Images
    private Image heart;
    private Image blinkyIMG, pinkyIMG, inkyIMG, clydeIMG;
    private Image up, down, left, right;

    // Game stats
    private boolean playing = false;
    private boolean dying = false;
    private int lives = 3;
    private int score = 0;

    private Player player = new Player();
    private Map<Class<? extends Ghost>, Ghost> ghosts = new HashMap<>();

    private Timer timer;

    public enum direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    /*
     * Placement data, could be an enum
     * 0 = Blank space
     * 1 = Wall
     * 2 = Points
     */
    public short[][] level_walls = {
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
            { 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 2 },
            { 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
            { 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2 },
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2 },
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2 },
            { 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 1, 1, 1, 2 },
            { 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2 },
            { 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 2, 2 },
            { 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2 },
            { 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2 },
            { 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 1, 2, 2 },
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }
    };

    /*
     * Constructor, build new game enviroment
     */
    public Model() {
        loadImages();
        addKeyListener(new TAdapter());
        setFocusable(true);
        initGame();
    }

    /*
     * Main game loop, will execute with timer
     */
    private void playGame(Graphics2D g2d) {
        if (dying) {
            death();
        } else {
            player.movePlayer(level_walls);
            drawPacman(g2d);
            moveDrawGhosts(g2d);
            checkMaze();
        }
    }

    /*
     * A function to load images, will exit game if files are not found.
     */
    private void loadImages() {
        try {
            pinkyIMG = new ImageIcon(getClass().getResource("images/pinky.gif")).getImage();
            clydeIMG = new ImageIcon(getClass().getResource("images/clyde.gif")).getImage();
            blinkyIMG = new ImageIcon(getClass().getResource("images/blinky.gif")).getImage();
            inkyIMG = new ImageIcon(getClass().getResource("images/inky.gif")).getImage();
            heart = new ImageIcon(getClass().getResource("images/heart.png")).getImage();
            down = new ImageIcon(getClass().getResource("images/down.gif")).getImage();
            up = new ImageIcon(getClass().getResource("images/up.gif")).getImage();
            left = new ImageIcon(getClass().getResource("images/left.gif")).getImage();
            right = new ImageIcon(getClass().getResource("images/right.gif")).getImage();
        } catch (Exception e) {
            System.out.println("Unable to load images\r\n" + e.toString());
            System.exit(0);
        }
    }

    /*
     * Build up game variables for each play
     */
    private void initGame() {
        // Reset empty space to pills
        for (int i = 0; i < CONST.N_BLOCKS; i++) {
            for (int j = 0; j < CONST.N_BLOCKS; j++) {
                if (level_walls[j][i] == 0) {
                    level_walls[j][i] = 2;
                }
            }
        }

        // Reset player location
        player = new Player();

        // Reset ghosts
        ghosts.clear();
        ghosts.put(G_Pink.class, new G_Pink(pinkyIMG));
        ghosts.put(G_Blink.class, new G_Blink(blinkyIMG));
        ghosts.put(G_Inky.class, new G_Inky(inkyIMG));
        ghosts.put(G_Clyde.class, new G_Clyde(clydeIMG));

        // Start timer
        timer = new Timer(CONST.TIMER_TICK, this);
        timer.start();
    }

    /*
     * Move and draw each ghost
     */
    private void moveDrawGhosts(Graphics2D g2d) {
        for (Map.Entry<Class<? extends Ghost>, Ghost> entry : ghosts.entrySet()) {
            entry.getValue().move(level_walls, player, ghosts);
            g2d.drawImage(entry.getValue().image, entry.getValue().location.x, entry.getValue().location.y, this);
        }
    }

    private void drawPacman(Graphics2D g2d) {
        switch (player.getDir()) {
            case LEFT:
                g2d.drawImage(left, player.getLoc().x + 1, player.getLoc().y + 1, this);
                break;
            case RIGHT:
                g2d.drawImage(right, player.getLoc().x + 1, player.getLoc().y + 1, this);
                break;
            case UP:
                g2d.drawImage(up, player.getLoc().x + 1, player.getLoc().y + 1, this);
                break;
            case DOWN:
                g2d.drawImage(down, player.getLoc().x + 1, player.getLoc().y + 1, this);
                break;
        }
    }

    /*
     * Main drawing loop
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);
        drawScore(g2d);

        if (playing) {
            playGame(g2d);
        } else {
            showIntroScreen(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    private void showIntroScreen(Graphics2D g2d) {
        String start = "Press SPACE to start";
        g2d.setColor(Color.yellow);
        g2d.drawString(start, (CONST.SCREEN_SIZE) / 4, 150);
    }

    /*
     * Run on player death
     */
    private void death() {
        lives--;

        if (lives == 0) {
            score = 0;
            lives = 3;
            playing = false;
        }

        playing = false;
        dying = false;
    }

    /*
     * Check maze for win conditions
     */
    private void checkMaze() {
        // Collect points
        int xGrid = player.getLoc().x / CONST.BLOCK_SIZE;
        int yGrid = player.getLoc().y / CONST.BLOCK_SIZE;
        if (level_walls[yGrid][xGrid] == 2) {
            level_walls[yGrid][xGrid] = 0;
            score++;
        }

        // Check for ghost collision
        Point PlayerLoc = new Point(player.getLoc().x / CONST.BLOCK_SIZE, player.getLoc().y / CONST.BLOCK_SIZE);
        for (Map.Entry<Class<? extends Ghost>, Ghost> entry : ghosts.entrySet()) {
            Point ghostLoc = new Point(entry.getValue().location.x / CONST.BLOCK_SIZE,
                    entry.getValue().location.y / CONST.BLOCK_SIZE);
            if (ghostLoc.equals(PlayerLoc))
                dying = true;
        }

        // Check for uncollected pills
        for (int i = 0; i < CONST.N_BLOCKS; i++) {
            for (int j = 0; j < CONST.N_BLOCKS; j++) {
                if (level_walls[j][i] == 2) {
                    // Game is not over
                    return;
                }
            }
        }

        /*
         * Game has ended
         */
        score += 50;
        playing = false;
    }

    /*
     * Draw score board
     */
    private void drawScore(Graphics2D g) {
        g.setFont(smallFont);
        g.setColor(new Color(5, 181, 79));
        String s = "Score: " + score;
        g.drawString(s, CONST.SCREEN_SIZE / 2 + 96, CONST.SCREEN_SIZE + 16);

        for (int i = 0; i < lives; i++) {
            g.drawImage(heart, i * 28 + 8, CONST.SCREEN_SIZE + 1, this);
        }
    }

    /*
     * Draw Maze
     */
    private void drawMaze(Graphics2D g2d) {
        for (int y = 0; y < CONST.N_BLOCKS; y++) {
            for (int x = 0; x < CONST.N_BLOCKS; x++) {
                switch (level_walls[y][x]) {
                    case 1:
                        g2d.setColor(new Color(0, 72, 251));
                        g2d.setStroke(new BasicStroke(5));
                        g2d.fillRect(x * CONST.BLOCK_SIZE, y * CONST.BLOCK_SIZE, CONST.BLOCK_SIZE, CONST.BLOCK_SIZE);
                        break;
                    case 2:
                        int diameter = CONST.BLOCK_SIZE / 4;
                        g2d.setColor(new Color(255, 255, 255));
                        g2d.fillOval((x * CONST.BLOCK_SIZE) + diameter,
                                (y * CONST.BLOCK_SIZE) + diameter,
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
     * KeyAdapter for proccessing key presses
     */
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (playing) {
                if (key == KeyEvent.VK_LEFT) {
                    player.newDirection = Model.direction.LEFT;
                } else if (key == KeyEvent.VK_RIGHT) {
                    player.newDirection = Model.direction.RIGHT;
                } else if (key == KeyEvent.VK_UP) {
                    player.newDirection = Model.direction.UP;
                } else if (key == KeyEvent.VK_DOWN) {
                    player.newDirection = Model.direction.DOWN;
                } else if (key == KeyEvent.VK_ESCAPE) {
                    playing = false;
                }

            } else {
                if (key == KeyEvent.VK_SPACE) {
                    playing = true;
                    initGame();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}
