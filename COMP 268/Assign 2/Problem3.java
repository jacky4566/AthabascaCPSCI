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
*
* Class: Elevator
*   Description: 
*    Used to store instance of Elevator. Elevator holds a total number of floors, top and bottom. 
*
*   Constructors:
*    Elevator(int, String):
*     Creates new instance of elevator with a defined number of floors and name.
*
*    Elevator(String):
*     Creates new instance of elevator with a defined name. # floor defaults to 5.
*
*    Elevator(int):
*     Creates new instance of elevator with a defined number of floors and a default name "Alpha".
*
*    Elevator():
*     Creates new instance of elevator with a default name "Alpha" and default floors 5.
* 
*    Methods:
*     void requestNewFloor (int):
*      Used to request new floor for the target elevator. Method also handles terminal printing and error processing.
*
*    public void finalize():
*     Overrides default java finalize. Checks if Elevator was returned to default first floor and prints message accordingly. 
*
*    Instance Variables:
*     public static final int BOTTOMFLOOR = 1; // defines the minimum floor for class elevator, unchangedable as defined by assignment parameters.
*     public static final int DEFAULTFLOORS = 5; // defines the minimum floor for class elevator
*     public final String elevatorName; //Stores the name of the elevator
*     private int topFloor = DEFAULTFLOORS; // Stores the total number of floors in the elevator shaft
*     private int currentFloor = BOTTOMFLOOR; // Stores the current location of this elevator
*
*/

/**
* Test Plan
*/
/**
* Run the application.
* EXPECTED:
    Terminal will print the following: 

    Example 1: Elevator does not move
    Elevator created: Alpha, top floor is 5

    Elevator ending: Alpha elevator returned to the first floor
    Example 2: Elevator moves twice and returns to first floor
    Elevator created: Bravo, top floor is 20
    Elevator request: Move Bravo to floor 10
    Elevator moved: Bravo Successfully moved to floor 10
    Elevator request: Move Bravo to floor 6
    Elevator moved: Bravo Successfully moved to floor 6
    Elevator request: Move Bravo to floor 1
    Elevator moved: Bravo Successfully moved to floor 1

    Elevator ending: Bravo elevator returned to the first floor
    Example 3: Elevator moves past top floor then returns to first floor
    Elevator created: Chalie, top floor is 5
    Elevator request: Move Chalie to floor 20
    Elevator request failed: Target floor 20 exceeds building top floor
    Elevator request: Move Chalie to floor 1
    Elevator moved: Chalie Successfully moved to floor 1

    Elevator ending: Chalie elevator returned to the first floor
    Example 4: Elevator moves to top floor and stays
    Elevator created: Delta, top floor is 5
    Elevator request: Move Delta to floor 5
    Elevator moved: Delta Successfully moved to floor 5

    Elevator ending: Delta not returned to the first floor
    Example 5: Elevator bottom floor and left there
    Elevator created: Echo, top floor is 5
    Elevator request: Move Echo to floor -2
    Elevator request failed: Target floor -2 exceeds building bottom floor

    Elevator ending: Echo elevator returned to the first floor

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

    static class Elevator { // Class of Elevator
        public static final int BOTTOMFLOOR = 1; // defines the minimum floor for class elevator
        public static final int DEFAULTFLOORS = 5; // defines the minimum floor for class elevator
        public final String elevatorName; //Stores the name of the elevator
        private int topFloor = DEFAULTFLOORS; // Stores the total number of floors in the elevator shaft
        private int currentFloor = BOTTOMFLOOR; // Stores the current location of this elevator

        public Elevator(int floors, String newName) {// constructor with custom set of floors and name
            this.topFloor = floors;
            this.elevatorName = newName;
            this.currentFloor = 1;
            System.out.println("Elevator created: " + this.elevatorName + ", top floor is " + Integer.toString(this.topFloor));
        }

        public Elevator(String newName) {// constructor with default 5 floors and custom name
            this.topFloor = Elevator.DEFAULTFLOORS;
            this.elevatorName = newName;
            this.currentFloor = 1;
            System.out.println("Elevator created: " + this.elevatorName + ", top floor is " + Integer.toString(this.topFloor));
        }

        public Elevator(int floors) {// constructor with custom floors and default name
            this.topFloor = floors;
            this.elevatorName = "Alpha";
            this.currentFloor = 1;
            System.out.println("Elevator created: " + this.elevatorName + ", top floor is " + Integer.toString(this.topFloor));
        }

        public Elevator() {// constructor with default 5 floors and name
            this.topFloor = Elevator.DEFAULTFLOORS;
            this.elevatorName = "Alpha";
            this.currentFloor = 1;
            System.out.println("Elevator created: " + this.elevatorName + ", top floor is " + Integer.toString(this.topFloor));
        }

        void requestNewFloor(int targetFloor) { // attempts to set a new floor. Returns value of new floor or -1 for error.
            System.out.println("Elevator request: Move " + this.elevatorName + " to floor " + Integer.toString(targetFloor));
            if (targetFloor <= this.topFloor && targetFloor >= Elevator.BOTTOMFLOOR) {
                currentFloor = targetFloor;
                System.out.println("Elevator moved: " + this.elevatorName + " Successfully moved to floor " + Integer.toString(targetFloor));
            }else if (targetFloor > topFloor){
                System.out.println("Elevator request failed: Target floor " + targetFloor + " exceeds building top floor");
            }
            else if (targetFloor < Elevator.BOTTOMFLOOR){
                System.out.println("Elevator request failed: Target floor " + targetFloor + " exceeds building bottom floor");
            }
        }

        @Override
        public void finalize() {
            if (this.currentFloor == Elevator.BOTTOMFLOOR){
                System.out.println("Elevator ending: " + this.elevatorName + " elevator returned to the first floor");
            } else{
                System.out.println("Elevator ending: " + this.elevatorName + " not returned to the first floor");
            }
        }
    }

    public static void main(String[] args) {
        // Example 1, Elevator does not move
        System.out.println("Example 1: Elevator does not move");
        Elevator alpha = new Elevator(); // Create default instance of elevator
        alpha = null; // Set the instance to null for garbage collection
        System.gc(); // Request a garbage man
        System.out.println("");

        // Example 2, Elevator moves twice and returns to first floor
        System.out.println("Example 2: Elevator moves twice and returns to first floor");
        Elevator beta = new Elevator(20, "Bravo"); // Create instance of elevator with 20 floors
        beta.requestNewFloor(10);// Request elevator move
        beta.requestNewFloor(6);// Request elevator move
        beta.requestNewFloor(Elevator.BOTTOMFLOOR);// Request elevator move
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