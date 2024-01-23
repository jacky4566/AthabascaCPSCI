package game;

import java.util.ArrayList;
import java.util.Iterator;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
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

    public enum placements {
        OPEN,
        OBSTACLE,
        FOOD,
        WATER,
        POISON,
        HOME;

        // Method to simulate increment with rollover
        public placements incrementWithRollover() {
            placements[] values = values();
            int nextIndex = (this.ordinal() + 1) % values.length;
            return values[nextIndex];
        }
    }

    public static Random random = new Random();

    // Images
    private Image up, down, left, right, home;

    // Timer
    private Timer ticker = new Timer(CONST.TIMER_TICK, this);

    // Ants
    private JSpinner numberSpinner;
    private List<Ant> antPopulation = new ArrayList<>();

    // Stats
    JLabel populationLabel = new JLabel();
    JLabel populationAgeLabel = new JLabel();

    /*
     * Placement data, could be an enum
     * 0 = Default, Open Terrain
     * 1 = Obstacle, Not passable
     * 2 = Food
     * 3 = Water
     * 4 = Poison
     * 5 = Home
     */
    public placements[][] levelData = new placements[CONST.N_BLOCKS][CONST.N_BLOCKS]; // Implimented as Y, X

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
        final int ODDS_OBS = 15; // Odds of spawning an obsticale
        final int ODDS_FOOD = 10; // Odds of spawning Grass Land
        final int ODDS_WATER = 5; // Odds of spawning an Swamp Land
        final int ODDS_POISON = 5; // Odds of spawning an Swamp Land

        // Generate Obstcales
        for (int i = 0; i < levelData.length; i++) {
            for (int j = 0; j < levelData[i].length; j++) {
                int spawntype = random.nextInt(100);
                if (spawntype > ODDS_OBS + ODDS_FOOD + ODDS_WATER + ODDS_POISON) {
                    // Blank
                    levelData[i][j] = placements.OPEN;
                } else if (spawntype > ODDS_OBS + ODDS_FOOD + ODDS_WATER) {
                    // Poison
                    levelData[i][j] = placements.POISON;
                } else if (spawntype > ODDS_OBS + ODDS_FOOD) {
                    // Water
                    levelData[i][j] = placements.WATER;
                } else if (spawntype > ODDS_OBS) {
                    // Food
                    levelData[i][j] = placements.FOOD;
                } else {
                    // Obsticle
                    levelData[i][j] = placements.OBSTACLE;
                }
            }
        }
        // Reset Home Location
        levelData[(int) CONST.HOME.getX()][(int) CONST.HOME.getY()] = placements.HOME;
    }

    /*
     * Mouse click event on the grid will cycle through options
     */
    private void mouseClick(int x, int y) {
        // Get grid coordinates
        int gridX = x / CONST.BLOCK_SIZE;
        int gridY = y / CONST.BLOCK_SIZE;

        // Change grid
        levelData[gridX][gridY] = levelData[gridX][gridY].incrementWithRollover();

        // Redraw
        repaint();
    }

    /*
     * Start Game simulation
     */
    private void startSim() {
        // Add fresh ants
        antPopulation.clear();
        for (int i = 0; i < (int) numberSpinner.getValue(); i++) {
            antPopulation.add(new Ant());
        }

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

        randomItemGenerator();
        drawMaze(g2d);
        moveAnts();
        drawAnts(g2d);
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
                // Always draw a black square
                g2d.setColor(Color.BLACK);
                g2d.fillRect(x * CONST.BLOCK_SIZE, y * CONST.BLOCK_SIZE, CONST.BLOCK_SIZE, CONST.BLOCK_SIZE);

                // Draw different types
                switch (levelData[x][y]) {
                    case OPEN:
                        break;
                    case OBSTACLE:
                        g2d.setColor(Color.lightGray);
                        g2d.fillRect(x * CONST.BLOCK_SIZE, y * CONST.BLOCK_SIZE, CONST.BLOCK_SIZE, CONST.BLOCK_SIZE);
                        break;
                    case FOOD:
                        g2d.setColor(Color.green);
                        int diameter = CONST.BLOCK_SIZE / 3;
                        g2d.fillOval((x * CONST.BLOCK_SIZE) + diameter,
                                (y * CONST.BLOCK_SIZE) + diameter,
                                diameter,
                                diameter);
                        break;
                    case WATER:
                        g2d.setColor(Color.blue);
                        g2d.fillRect(x * CONST.BLOCK_SIZE, y * CONST.BLOCK_SIZE, CONST.BLOCK_SIZE, CONST.BLOCK_SIZE);
                        break;
                    case POISON:
                        g2d.setColor(Color.red);
                        diameter = CONST.BLOCK_SIZE / 3;
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
        // Home
        g2d.drawImage(home, CONST.BLOCK_SIZE * 15, CONST.BLOCK_SIZE * 15, this);
    }

    /*
     * Move Player, pop commands from list
     */
    private void moveAnts() {
        Iterator<Ant> antI = antPopulation.iterator();
        List<Ant> newAnts = new ArrayList<>();
        while (antI.hasNext()) {
            Ant ant = antI.next();
            ant.age++;
            switch (ant.move(levelData)) {
                case DEATH:
                    // The ant died...
                    antI.remove();
                    break;
                case HOME:
                    newAnts.add(new Ant());
                    // Add new ant
                    break;
                case MOVE:
                    // No significant action
                    break;
                default:
                    break;
            }
        }

        // Add all newly generated ants
        antPopulation.addAll(newAnts);

        // Calc Average ant Age
        int avgAge = 0;
        if (!antPopulation.isEmpty()) {
            for (Ant ant : antPopulation) {
                avgAge += ant.age;
            }
            avgAge = avgAge / antPopulation.size();
        }

        populationLabel.setText("Current Population: " + antPopulation.size());
        populationAgeLabel.setText("Pop. Avg. Age: " + avgAge);
    }

    /*
     * Randomly generates items based on constant maximums
     */
    private void randomItemGenerator() {
        int countFood = 0;
        int countPoison = 0;
        int countWater = 0;
        for (int i = 0; i < levelData.length; i++) {
            for (int j = 0; j < levelData[i].length; j++) {
                switch (levelData[i][j]) {
                    case FOOD:
                        countFood++;
                        break;
                    case HOME:
                        break;
                    case OBSTACLE:
                        break;
                    case OPEN:
                        break;
                    case POISON:
                        countPoison++;
                        break;
                    case WATER:
                        countWater++;
                        break;
                    default:
                        break;

                }
            }
        }

        // If resource is low, randomly add one
        if (countFood < CONST.MAX_FOOD) {
            int randomX = random.nextInt(CONST.N_BLOCKS);
            int randomY = random.nextInt(CONST.N_BLOCKS);
            if (levelData[randomX][randomY] == placements.OPEN)
                levelData[randomX][randomY] = placements.FOOD;
        }

        if (countWater < CONST.MAX_WATER) {
            int randomX = random.nextInt(CONST.N_BLOCKS);
            int randomY = random.nextInt(CONST.N_BLOCKS);
            if (levelData[randomX][randomY] == placements.OPEN)
                levelData[randomX][randomY] = placements.WATER;
        }

        if (countPoison < CONST.MAX_POISON) {
            int randomX = random.nextInt(CONST.N_BLOCKS);
            int randomY = random.nextInt(CONST.N_BLOCKS);
            if (levelData[randomX][randomY] == placements.OPEN)
                levelData[randomX][randomY] = placements.POISON;
        }
    }

    /*
     * Draw ants
     */
    private void drawAnts(Graphics2D g2d) {
        for (Ant ant : antPopulation) {
            switch (ant.antDirection) {
                case LEFT:
                    g2d.drawImage(left, ant.location.x + 1, ant.location.y + 1, this);
                    break;
                case RIGHT:
                    g2d.drawImage(right, ant.location.x + 1, ant.location.y + 1, this);
                    break;
                case UP:
                    g2d.drawImage(up, ant.location.x + 1, ant.location.y + 1, this);
                    break;
                case DOWN:
                    g2d.drawImage(down, ant.location.x + 1, ant.location.y + 1, this);
                    break;
            }

            // draw path
            if (!ant.pathHome.isEmpty()) {
                int x = ant.location.x + CONST.BLOCK_SIZE / 2; // starting x-coordinate
                int y = ant.location.y + (CONST.BLOCK_SIZE / 2); // starting y-coordinate
                // Set drawing parameters
                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(2));

                // Draw Path
                for (Direction dir : ant.pathHome) {
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
        label1.setText("Ant Farm Example");
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
        labelBlank.setText("Blank Space");
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
        labelObs.setText("Obstacle");
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
        labelGrass.setText("Food");
        c.gridx = 1;
        c.gridy = 4;
        panel.add(labelGrass, c);

        // Placement type 3, Water
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        JPanel waterColor = new JPanel();
        waterColor.setBackground(Color.BLUE);
        panel.add(waterColor, c);

        JLabel labelWater = new JLabel();
        labelWater.setForeground(Color.BLUE);
        labelWater.setText("Water");
        c.gridx = 1;
        c.gridy = 5;
        panel.add(labelWater, c);

        // Placement type 3, Swamp
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        JPanel poisonColor = new JPanel();
        poisonColor.setBackground(Color.red);
        panel.add(poisonColor, c);

        JLabel labelPoison = new JLabel();
        labelPoison.setForeground(Color.red);
        labelPoison.setText("Poison");
        c.gridx = 1;
        c.gridy = 6;
        panel.add(labelPoison, c);

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

        // Instructions
        JLabel selLabel = new JLabel();
        selLabel.setText("Select Number of Ants and Start");
        selLabel.setForeground(Color.yellow);
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 10;
        panel.add(selLabel, c);

        // Number Spinner
        SpinnerModel spinnerModel = new SpinnerNumberModel(3, 0, 100, 1);
        numberSpinner = new JSpinner(spinnerModel);
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 11;
        panel.add(numberSpinner, c);

        // Start Button
        c.gridx = 1;
        c.gridy = 11;
        JButton startButton = new JButton("START");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startSim();
            }
        });
        panel.add(startButton, c);

        // Current Population
        populationLabel.setText("Current Population: 0");
        populationLabel.setForeground(Color.yellow);
        c.gridx = 1;
        c.gridy = 12;
        panel.add(populationLabel, c);

        // Current Population
        populationAgeLabel.setText("Pop. Avg. Age: 0");
        populationAgeLabel.setForeground(Color.yellow);
        c.gridx = 1;
        c.gridy = 13;
        panel.add(populationAgeLabel, c);

        return panel;
    }
}
