package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

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

    private Dimension d = new Dimension(400, 400);;
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);

    private Image heart;
    private Image blinkyIMG, pinkyIMG, inkyIMG, clydeIMG;
    private Image up, down, left, right;

    private boolean playing = false;
    private boolean dying = false;
    private int lives = 3;
    private int score = 0;

    private Player player = new Player();
    private List<Ghost> ghosts = new ArrayList<>();

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
    private final short[][] levelWalls = {
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
            { 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
            { 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
            { 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2 },
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2 },
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2 },
            { 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
            { 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 2, 2 },
            { 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2 },
            { 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2 },
            { 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2 },
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 2 }
    };

    public Model() {
        loadImages();
        addKeyListener(new TAdapter());
        setFocusable(true);
        initGame();
    }

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

    private void initGame() {
        // Reset empty space to pills
        for (int i = 0; i < CONST.N_BLOCKS; i++) {
            for (int j = 0; j < CONST.N_BLOCKS; j++) {
                if (levelWalls[j][i] == 0) {
                    levelWalls[j][i] = 2;
                }
            }
        }

        // Reset player location
        player.locationX = 0;
        player.locationY = 0;

        ghosts.add(new G_Pink(pinkyIMG));
        ghosts.add(new G_Blink(blinkyIMG));
        ghosts.add(new G_Inky(inkyIMG));
        ghosts.add(new G_Clyde(clydeIMG));

        // Start timer
        timer = new Timer(50, this);
        timer.start();
    }

    private void playGame(Graphics2D g2d) {
        if (dying) {
            death();
        } else {
            player.movePlayer(levelWalls);
            collectPoints();
            drawPacman(g2d);
            // moveGhosts(g2d);
            drawGhost(g2d);
            checkMaze();
        }
    }

    private void drawGhost(Graphics2D g2d) {
        for (Ghost ghost : ghosts) {
            g2d.drawImage(ghost.image, ghost.locationX, ghost.locationY, this);
        }
    }

    private void collectPoints() {
        int xGrid = player.locationX / CONST.BLOCK_SIZE;
        int yGrid = player.locationY / CONST.BLOCK_SIZE;
        if (levelWalls[yGrid][xGrid] == 2) {
            levelWalls[yGrid][xGrid] = 0;
            score++;
        }
    }

    private void drawPacman(Graphics2D g2d) {
        switch (player.playerDirection) {
            case LEFT:
                g2d.drawImage(left, player.locationX + 1, player.locationY + 1, this);
                break;
            case RIGHT:
                g2d.drawImage(right, player.locationX + 1, player.locationY + 1, this);
                break;
            case UP:
                g2d.drawImage(up, player.locationX + 1, player.locationY + 1, this);
                break;
            case DOWN:
                g2d.drawImage(down, player.locationX + 1, player.locationY + 1, this);
                break;
        }
    }

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

    private void death() {
        lives--;

        if (lives == 0) {
            score = 0;
            lives = 3;
            playing = false;
        }
    }

    private void checkMaze() {
        // Check for uncollected pills
        for (int i = 0; i < CONST.N_BLOCKS; i++) {
            for (int j = 0; j < CONST.N_BLOCKS; j++) {
                if (levelWalls[j][i] == 2) {
                    return;
                }
            }
        }

        // Start new game
        score += 50;
        playing = false;
    }

    private void drawScore(Graphics2D g) {
        g.setFont(smallFont);
        g.setColor(new Color(5, 181, 79));
        String s = "Score: " + score;
        g.drawString(s, CONST.SCREEN_SIZE / 2 + 96, CONST.SCREEN_SIZE + 16);

        for (int i = 0; i < lives; i++) {
            g.drawImage(heart, i * 28 + 8, CONST.SCREEN_SIZE + 1, this);
        }
    }

    private void drawMaze(Graphics2D g2d) {
        for (int y = 0; y < CONST.N_BLOCKS; y++) {
            for (int x = 0; x < CONST.N_BLOCKS; x++) {
                switch (levelWalls[y][x]) {
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

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (playing) {
                // Escape game
                if (key == KeyEvent.VK_ESCAPE) {
                    playing = false;
                }
                // Apply new direction
                if (key == KeyEvent.VK_LEFT) {
                    player.newDirection = Model.direction.LEFT;
                } else if (key == KeyEvent.VK_RIGHT) {
                    player.newDirection = Model.direction.RIGHT;
                } else if (key == KeyEvent.VK_UP) {
                    player.newDirection = Model.direction.UP;
                } else if (key == KeyEvent.VK_DOWN) {
                    player.newDirection = Model.direction.DOWN;
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
