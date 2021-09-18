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
        LocationName currentLocation = LocationName.TEARS;
        Character myPlayer = new Character("Alice");
        locationList.add(new RiverBank(myPlayer));
        locationList.add(new Tears(myPlayer));
        locationList.add(new Garden(myPlayer));
        locationList.add(new Mushrooms(myPlayer));
        locationList.add(new Tea(myPlayer));
        locationList.add(new Courtyard(myPlayer));
        locationList.add(new Courtroom(myPlayer));

        // Start user input
        System.out.print("Press enter to continue");
        Control.getAction();

        // Main game loop
        while (currentLocation != LocationName.END) {
            Control.clearTerminal();
            currentLocation = locationList.get(getLocationID(currentLocation)).enter();
        }
    }

    public int getLocationID(LocationName findThis) { // looks for the target location in an arraylist
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
