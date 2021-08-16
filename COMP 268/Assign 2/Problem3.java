/**
* title: Problem3.java
* description: Create a class which represents and elevator. Move the elevator around and check for end conditions.
* date: 13/08/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Problem3
*
* Purpose and Description:
*  Creates several instances of class Book then prints out the information stored within. 
*  Program demonstrates use of clases and different constructors.
*
*
* Compiling and running instructions
*  Compile:  javac Problem3.java
*  Run:      java Problem3.java
*
* Classes:
*  Elevator.java
*/

/**
* Test Plan
*/
/**
* Run the application.
* EXPECTED:
*  Terminal will print the following: 

    Example 1: Elevator does not move
    Elevator created: Alpha, top floor is 5

    Example 2: Elevator moves twice
    Elevator created: Bravo, top floor is 20
    Elevator request: Move Bravo to floor 10
    Elevator moved: Bravo Successfully moved to floor 10
    Elevator request: Move Bravo to floor 6
    Elevator moved: Bravo Successfully moved to floor 6

    Example 3: Elevator moves past top floor then returns to first floor
    Elevator created: Chalie, top floor is 5
    Elevator request: Move Chalie to floor 20
    Elevator ending: Alpha returned to the first floor
    Elevator ending: Bravo returned to the first floor
    Elevator request failed: Target floor 20 exceeds building top floor
    Elevator request: Move Chalie to floor 1
    Elevator moved: Chalie Successfully moved to floor 1

    Elevator ending: Chalie returned to the first floor
    Example 4: Elevator moves to top floor and stays
    Elevator created: Delta, top floor is 5
    Elevator request: Move Delta to floor 5
    Elevator moved: Delta Successfully moved to floor 5

    Elevator ending: Delta returned to the first floor
    Example 5: Elevator bottom floor and left there
    Elevator created: Echo, top floor is 5
    Elevator request: Move Echo to floor -2
    Elevator request failed: Target floor -2 exceeds building bottom floor

    Elevator ending: Echo returned to the first floor

* ACTUAL:
*    Terminal frame displays as expected
*
* Good data cases:
*   No outside data expected
*
* Bad data cases:
*   No outside data expected
*/

public class Problem3 {

    public static void main(String[] args) {
        // Example 1, Elevator does not move
        System.out.println("Example 1: Elevator does not move");
        Elevator alpha = new Elevator(); // Create default instance of elevator
        alpha = null; // Set the instance to null for garbage collection
        System.gc(); // Request a garbage man
        System.out.println("");

        // Example 2, Elevator moves twice
        System.out.println("Example 2: Elevator moves twice");
        Elevator beta = new Elevator(20, "Bravo"); // Create instance of elevator with 20 floors
        beta.requestNewFloor(10);// Request elevator move
        beta.requestNewFloor(6);// Request elevator move
        beta = null; // Set the instance to null for garbage collection
        System.gc(); // Request a garbage man
        System.out.println("");

        // Example 3, Elevator moves past top floor then returns to first floor
        System.out.println("Example 3: Elevator moves past top floor then returns to first floor");
        Elevator chalie = new Elevator("Chalie"); // Create default instance of elevator
        chalie.requestNewFloor(20);// Request elevator move out of bounds
        chalie.requestNewFloor(Elevator.BOTTOMFLOOR);// Request elevator move
        chalie = null; // Set the instance to null for garbage collection
        System.gc(); // Request a garbage man
        System.out.println("");

        // Example 4, Elevator moves past top floor and left there
        System.out.println("Example 4: Elevator moves to top floor and stays");
        Elevator delta = new Elevator("Delta"); // Create default instance of elevator
        delta.requestNewFloor(delta.topFloor);// Request elevator move out of bounds
        delta = null; // Set the instance to null for garbage collection
        System.gc(); // Request a garbage man
        System.out.println("");

        // Example 5, Elevator moves past bottom floor and left there
        System.out.println("Example 5: Elevator bottom floor and left there");
        Elevator echo = new Elevator("Echo"); // Create default instance of elevator
        echo.requestNewFloor(-2);// Request elevator move out of bounds
        echo = null; // Set the instance to null for garbage collection
        System.gc(); // Request a garbage man
        System.out.println("");        
    }
}