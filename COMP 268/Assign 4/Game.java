/**
* title: Game.java
* description: Builds the basic blocks of the game including rooms and game loop
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: Game
*   Description: 
*    Creates a base instance of our game. Prints out the user welcome package and Creates all the room objects. 
*    This class contains the main while loop of our game.
*
*   Constructors:
*    Game()
*     Does nothing.
* 
*   Methods:
*    void run():
*     Our main game loop. Prints user welcome package. Loads game resources and performs the main loop until exit condition is given. 
*
*    int getLocationID(LocationList):
*     Finds the array pointers to a given room as assigned by the previous room. 
*
*   Instance Variables:
*    ArrayList<Location> locationList: Contains a list of all of the new location classes for exploration by the user. 
*
*/

import java.util.*;

public class Game {
    ArrayList<Location> locationList = new ArrayList<>();

    public Game() {
        // does nothing
    }

    public void run() {
        Control.clearTerminal();
        Control.printFile(Constants.WELCOMEFILE);
        Control.printFile(Constants.HELPFILE);
        // Load game resources
        LocationList currentLocation = LocationList.RIVERBANK;
        Alice myPlayer = new Alice();
        locationList.add(new RoomRiverBank(myPlayer));
        locationList.add(new RoomCorridor(myPlayer));
        locationList.add(new RoomGarden(myPlayer));
        locationList.add(new RoomMushroom(myPlayer));
        locationList.add(new RoomTea(myPlayer));
        locationList.add(new RoomCourtyard(myPlayer));
        locationList.add(new RoomCourt(myPlayer));

        // Start user input
        System.out.print("Press enter to continue");
        Control.getAction();

        // Main game loop
        while (currentLocation != LocationList.END) {
            Control.clearTerminal();
            currentLocation = locationList.get(getLocationID(currentLocation)).enter();
        }
    }

    public int getLocationID(LocationList findThis) { // looks for the target location in an arraylist
        for (int i = 0; i < locationList.size(); i++) {
            if (locationList.get(i).getLocationID() == findThis) {
                return i;
            }
        }
        // Should never return here
        System.out.println("Room not Found");
        return -1;
    }
}
