import java.util.*;

public class Game {
    ArrayList<Location> locationList = new ArrayList<>();

    public Game() {
        // does nothing
    }

    public void run() {
        Control.printFile(Control.WELCOMEFILE);

        LocationName currentLocation = LocationName.RIVERBANK;

        Character myPlayer = new Character("Alice");

        locationList.add(new RiverBank(myPlayer));
        locationList.add(new Tears(myPlayer));

        ActionType input = ActionType.UNDEFINED;
        while (input != ActionType.YES) {
            System.out.print("Ready to proceed? ");
            input = Control.getAction();
        }

        System.out.println();

        while (currentLocation != LocationName.END) {
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
