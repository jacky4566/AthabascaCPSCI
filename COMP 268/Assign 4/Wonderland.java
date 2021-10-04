/**
* title: Wonderland.java
* description: High level static class for running game.
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: Wonderland
*   Description: 
*    Creates and runs a new instance of class Game.
*
*   Constructors:
*    Wonderland()
*     Creates and runs a new instance of class Game.
* 
*   Methods:
*    None
*
*   Instance Variables:
*    None
*/

public class Wonderland {
        public static void main(String[] args) {
                //Create new game object and run it
                Game newGame = new Game();
                newGame.run(); 
        }
}