package game;

import java.util.ArrayList;
import java.util.List;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.PathFinding.Direction;

/*
 * Jackson Wiebe
 * 3519635
 * 21/01/2024
 * 
 * Model Class for driving the game.
 */
public class Model extends JPanel implements ActionListener {

    Random random = new Random();

    // Images
    private Image up, down, left, right, home;

    // Player
    private Player player = new Player(new Point(0, CONST.BLOCK_SIZE * 15));
    private List<Direction> simPath = new ArrayList<>();

    // Timer
    private Timer ticker = new Timer(CONST.TIMER_TICK, this);

    // Cost Label
    private Label labelCost = new Label();

    /*
     * Placement data, could be an enum
     * 0 = Default, Open Terrain
     * 1 = Obstacle, Not passable
     * 2 = Grassland, Cost 3
     * 3 = Swamp, Cost 4
     */
    public short[][] levelData = new short[16][16]; // Implimented as Y, X

    /*
     * Constructor, build new game enviroment
     */
    public Model() {
        // Push the console panel to the right
        this.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        setFocusable(true);
        add(drawConsolePanel());
        randomizeLevel();
        loadImages();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClick(e.getX(), e.getY());
            }
        });
    }

    /*
     * A function to load images, will exit game if files are not found.
     */
    private void loadImages() {
        try {
            down = new ImageIcon(getClass().getResource("images/down.gif")).getImage();
            up = new ImageIcon(getClass().getResource("images/up.gif")).getImage();
            left = new ImageIcon(getClass().getResource("images/left.gif")).getImage();
            right = new ImageIcon(getClass().getResource("images/right.gif")).getImage();
            home = new ImageIcon(getClass().getResource("images/home.gif")).getImage();
        } catch (Exception e) {
            System.out.println("Unable to load images\r\n" + e.toString());
            System.exit(0);
        }
    }

    /*
     * Generates a random level with specific Weights
     */
    private void randomizeLevel() {
        final int ODDS_OBS = 20; // Odds of spawning an obsticale
        final int ODDS_GRASS = 10; // Odds of spawning Grass Land
        final int ODDS_SWAMP = 5; // Odds of spawning an Swamp Land

        // Generate Obstcales
        for (int i = 0; i < levelData.length; i++) {
            for (int j = 0; j < levelData[i].length; j++) {
                int spawntype = random.nextInt(100);
                if (spawntype > ODDS_OBS + ODDS_GRASS + ODDS_SWAMP) {
                    // Blank
                    levelData[i][j] = 0;
                } else if (spawntype > ODDS_OBS + ODDS_GRASS) {
                    // Swamp
                    levelData[i][j] = 3;
                } else if (spawntype > ODDS_OBS) {
                    // Grass
                    levelData[i][j] = 2;
                } else {
                    // Obsticle
                    levelData[i][j] = 1;
                }
            }
        }
        // Reset Start and Finish Positions
        levelData[0][15] = 0;
        levelData[15][0] = 0;
    }

    /*
     * Mouse click event on the grid will cycle through options
     */
    private void mouseClick(int x, int y) {
        // Get grid coordinates
        int gridX = x / CONST.BLOCK_SIZE;
        int gridY = y / CONST.BLOCK_SIZE;

        // Change grid
        levelData[gridX][gridY]++;
        if (levelData[gridX][gridY] > 3)
            levelData[gridX][gridY] = 0;

        // Redraw
        repaint();
    }

    /*
     * Start Game simulation
     */
    private void startSim() {
        // Reset Pacman
        player = new Player(new Point(0, CONST.BLOCK_SIZE * 15));

        // Do A Star path finding
        simPath = PathFinding.findPath(levelData, 0, 15, 15, 0);
        if (simPath.isEmpty()) {
            System.out.println("No Path Found");
            labelCost.setText("No Path Found");
            return;
        }

        // Stats
        System.out.println("Path: " + simPath);

        // Start game Tick
        ticker.start();
    }

    /*
     * Called from Timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    /*
     * Main drawing loop
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        drawMaze(g2d);
        movePlayer();
        drawPlayer(g2d);
    }

    /*
     * Draw Maze
     * 
     * 0 = Default, Open Terrain
     * 1 = Obstacle, Not passable
     * 2 = Grassland, Cost 3
     * 3 = Swamp, Cost 4
     */
    private void drawMaze(Graphics2D g2d) {
        for (int y = 0; y < CONST.N_BLOCKS; y++) {
            for (int x = 0; x < CONST.N_BLOCKS; x++) {
                switch (levelData[x][y]) {
                    case 0:
                        g2d.setColor(Color.BLACK);
                        g2d.fillRect(x * CONST.BLOCK_SIZE, y * CONST.BLOCK_SIZE, CONST.BLOCK_SIZE, CONST.BLOCK_SIZE);
                        break;
                    case 1:
                        g2d.setColor(Color.lightGray);
                        g2d.fillRect(x * CONST.BLOCK_SIZE, y * CONST.BLOCK_SIZE, CONST.BLOCK_SIZE, CONST.BLOCK_SIZE);
                        break;
                    case 2:
                        g2d.setColor(Color.green);
                        g2d.fillRect(x * CONST.BLOCK_SIZE, y * CONST.BLOCK_SIZE, CONST.BLOCK_SIZE, CONST.BLOCK_SIZE);
                        break;
                    case 3:
                        g2d.setColor(CONST.DARK_GREEN);
                        g2d.fillRect(x * CONST.BLOCK_SIZE, y * CONST.BLOCK_SIZE, CONST.BLOCK_SIZE, CONST.BLOCK_SIZE);
                        break;
                    default:
                        break;
                }
            }
        }
        // Home
        g2d.drawImage(home, CONST.BLOCK_SIZE * 15, 0, this);

        // draw path
        if (!simPath.isEmpty()) {

            int x = CONST.BLOCK_SIZE / 2; // starting x-coordinate
            int y = CONST.BLOCK_SIZE * 15 + (CONST.BLOCK_SIZE / 2); // starting y-coordinate
            // Set drawing parameters
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(3));

            // Draw Path
            for (Direction dir : simPath) {
                switch (dir) {
                    case UP:
                        g2d.drawLine(x, y, x, y - CONST.BLOCK_SIZE);
                        y -= CONST.BLOCK_SIZE;
                        break;
                    case DOWN:
                        g2d.drawLine(x, y, x, y + CONST.BLOCK_SIZE);
                        y += CONST.BLOCK_SIZE;
                        break;
                    case LEFT:
                        g2d.drawLine(x, y, x - CONST.BLOCK_SIZE, y);
                        x -= CONST.BLOCK_SIZE;
                        break;
                    case RIGHT:
                        g2d.drawLine(x, y, x + CONST.BLOCK_SIZE, y);
                        x += CONST.BLOCK_SIZE;
                        break;
                }
            }
        }
    }

    /*
     * Move Player, pop commands from list
     */
    private void movePlayer() {
        // check if we are done moving
        if (player.stepInSequence >= simPath.size()) {
            ticker.stop();
            return;
        }
        // Move Player
        Direction nextDirection = simPath.get(player.stepInSequence++);
        switch (nextDirection) {
            case UP:
                player.playerDirection = Direction.UP;
                player.location.translate(0, -CONST.BLOCK_SIZE);
                break;
            case DOWN:
                player.playerDirection = Direction.DOWN;
                player.location.translate(0, CONST.BLOCK_SIZE);
                break;
            case LEFT:
                player.playerDirection = Direction.LEFT;
                player.location.translate(-CONST.BLOCK_SIZE, 0);
                break;
            case RIGHT:
                player.playerDirection = Direction.RIGHT;
                player.location.translate(CONST.BLOCK_SIZE, 0);
                break;
        }

        // Add up costs as we go
        player.travelCost += PathFinding
                .getCost(levelData[(int) player.location.getX() / CONST.BLOCK_SIZE][(int) player.location.getY()
                        / CONST.BLOCK_SIZE]);
        labelCost.setText("Path Cost: " + player.travelCost);
    }

    /*
     * Draw Player current position
     */
    private void drawPlayer(Graphics2D g2d) {
        switch (player.playerDirection) {
            case LEFT:
                g2d.drawImage(left, player.location.x + 1, player.location.y + 1, this);
                break;
            case RIGHT:
                g2d.drawImage(right, player.location.x + 1, player.location.y + 1, this);
                break;
            case UP:
                g2d.drawImage(up, player.location.x + 1, player.location.y + 1, this);
                break;
            case DOWN:
                g2d.drawImage(down, player.location.x + 1, player.location.y + 1, this);
                break;
        }
    }

    /*
     * Draw the Console area
     */
    private JPanel drawConsolePanel() {
        // Create panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(CONST.CONSOLE_AREA_X, CONST.CONSOLE_AREA_Y));
        panel.setBackground(Color.gray);

        // Used for positioning stuff
        GridBagConstraints c = new GridBagConstraints();

        // Title
        JLabel label1 = new JLabel();
        label1.setText("A* Path Finding Example");
        label1.setForeground(Color.yellow);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 10, 0);
        panel.add(label1, c);

        // Instructions
        JLabel label2 = new JLabel();
        label2.setText("Click cell to change type");
        label2.setForeground(Color.yellow);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(label2, c);

        // Placement type 0, Blank
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        JPanel blankPanel = new JPanel();
        blankPanel.setBackground(Color.BLACK);
        panel.add(blankPanel, c);

        JLabel labelBlank = new JLabel();
        labelBlank.setForeground(Color.BLACK);
        labelBlank.setText("Blank Space, Cost 1");
        c.gridx = 1;
        c.gridy = 2;
        panel.add(labelBlank, c);

        // Placement type 1, Obstacle
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        JPanel obstacleColor = new JPanel();
        obstacleColor.setBackground(Color.lightGray);
        panel.add(obstacleColor, c);

        JLabel labelObs = new JLabel();
        labelObs.setForeground(Color.lightGray);
        labelObs.setText("Obstacle, Impassible");
        c.gridx = 1;
        c.gridy = 3;
        panel.add(labelObs, c);

        // Placement type 2, Grassland
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        JPanel grassColor = new JPanel();
        grassColor.setBackground(Color.green);
        panel.add(grassColor, c);

        JLabel labelGrass = new JLabel();
        labelGrass.setForeground(Color.green);
        labelGrass.setText("Grassland, Cost 3");
        c.gridx = 1;
        c.gridy = 4;
        panel.add(labelGrass, c);

        // Placement type 3, Swamp
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        JPanel swampColor = new JPanel();
        swampColor.setBackground(CONST.DARK_GREEN);
        panel.add(swampColor, c);

        JLabel labelSwamp = new JLabel();
        labelSwamp.setForeground(CONST.DARK_GREEN);
        labelSwamp.setText("Swamp, Cost 4");
        c.gridx = 1;
        c.gridy = 5;
        panel.add(labelSwamp, c);

        // Randomize Button
        c.gridx = 1;
        c.gridy = 9;
        JButton randomBtn = new JButton("Randomize");
        randomBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                randomizeLevel();
                repaint();
            }
        });
        panel.add(randomBtn, c);

        // Start Button
        c.gridx = 1;
        c.gridy = 10;
        JButton startButton = new JButton("START");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startSim();
            }
        });
        panel.add(startButton, c);

        // Path Cost
        labelCost.setForeground(Color.green);
        labelCost.setText("Path Cost: 0");
        c.gridx = 1;
        c.gridy = 11;
        panel.add(labelCost, c);

        return panel;
    }
}
