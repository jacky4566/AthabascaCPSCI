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
        LocationList currentLocation = LocationList.COURTYARD;
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
