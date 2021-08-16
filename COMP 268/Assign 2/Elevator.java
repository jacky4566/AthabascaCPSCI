/**
* title: Elevator.java
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
*     public int topFloor = DEFAULTFLOORS; // Stores the total number of floors in the elevator shaft
*     public int currentFloor = BOTTOMFLOOR; // Stores the current location of this elevator
*/

public class Elevator { // Class of Elevator
    public static final int BOTTOMFLOOR = 1; // defines the minimum floor for class elevator
    public static final int DEFAULTFLOORS = 5; // defines the minimum floor for class elevator
    public final String elevatorName; //Stores the name of the elevator
    int topFloor = DEFAULTFLOORS; // Stores the total number of floors in the elevator shaft
    int currentFloor = BOTTOMFLOOR; // Stores the current location of this elevator

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
        }else if (targetFloor < Elevator.BOTTOMFLOOR){
            System.out.println("Elevator request failed: Target floor " + targetFloor + " exceeds building bottom floor");
        }
    }

    @Override
    public void finalize() {
        this.currentFloor = Elevator.BOTTOMFLOOR;
        if (this.currentFloor == Elevator.BOTTOMFLOOR){
            System.out.println("Elevator ending: " + this.elevatorName + " returned to the first floor");
        }
    }
}