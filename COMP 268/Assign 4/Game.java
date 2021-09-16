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
        LocationName currentLocation = LocationName.RIVERBANK;
        Character myPlayer = new Character("Alice");
        locationList.add(new RiverBank(myPlayer));
        locationList.add(new Tears(myPlayer));

        // Start user input
        do {
            System.out.print("Ready to Play? ");
        } while (Control.getAction().getActionType() != ActionType.YES);

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
        return 0; // Should never return here
    }
}
