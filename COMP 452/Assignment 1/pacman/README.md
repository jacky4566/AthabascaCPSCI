JAckson Wiebe
3519635
COMP 452 Assignment 1

#Video Demo
A video about my project can be found here:
https://youtu.be/ZawZox1aW5g

#PACMAN
Pac-Man is a classic arcade game that was first released in 1980. It was created by Toru Iwatani and developed by Namco. The game's design and logic are relatively simple yet highly engaging.

My implimentation of the game uses Java Swing to generate a 2D play area. The Model class extends JPanel and implements the ActionListener interface. The game elements include a maze represented by a 2D array, a player controlled by user input, ghosts with different behaviors, and various images for graphics.

The game has a graphical interface, utilizing Java's Swing library, with images loaded for Pac-Man, ghosts, and other elements. The maze layout, represented by the level_walls array, consists of walls (1), points to be collected by Pac-Man (2), and empty spaces (0). The game features a scoring system, lives for the player, and a timer for continuous updates.

The main game loop, executed by the playGame method, handles player movement, ghost behavior, and checks for win or loss conditions. The ghosts' movements are determined by their specific classes, such as G_Pink, G_Blink, G_Inky, and G_Clyde.

The program includes methods for loading images, initializing the game environment, drawing the maze, displaying the score, handling player death, and checking win conditions. User input is processed using a KeyAdapter, allowing the player to control Pac-Man's direction using arrow keys and start/restart the game with the spacebar.

#Instructions to compile/ run:
A compiled binary has been provided as pacman.jar.
The files can also be compiiled with VScode with the Java plugin. Simply by opening the project files in VScode, pressing F1 and typing "Java: Export Jar"

From a command prompt or Terminal
- Ensure the machine has Java 8 or newer. Use the command "java -version"
- Navigate to the game files directory
- Run the command "java -jar pacman.jar"

#Game AI
Game AI is implimented mostly in the base Ghost class. I have choosen to impliment the following methods:

Seek(): Finds a direct path to the player avoiding walls. If the method is unsuccesful the assigned direction is to wander.

Wander(): A very basic method that simply returns a random direction with no regard for game status

Flea(): Similar to Seek however the returning direction is reverse, Left > Right, Up > Down. This gives a good appearnce of the unit fleaing.

seekFormation(): A more advanced function that will attempt to keep squad members together as they approach the target. Units will first seek a centroid position then move towards the target. If moving to centroid is blocked, move randomly. 

Some ghosts also adapt more functions such as Blinky who seeks out the space 4 squares ahead of the player. This is done by applying an offset to the seek position. 

All path finding is done with a breadth first search. Other algorythems such as AStar and Dijkstra's would be equally equipped for this task.

#Known Bugs
The seekFormation() function is quite easy to trap the ghosts where they are seeking a position they can not reach. Thus the random was added.

Breadth first search, BFS is a simple and reliable algorithm, it may not be the most efficient choice in scenarios where additional information, such as edge costs and heuristic estimates are used however for a basic game it works here. We also limit our path choices as shorter options may exist that are not discovered. 

The flea() mechanism works by first finding the path to the target and  returning a direction opposite to the goal. This is not a true flea where the target should ideally be sent to a seperate zone or area away from the player. 

