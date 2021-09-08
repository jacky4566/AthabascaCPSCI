import java.util.*;

public class Location {
    private LocationName locationID;
    private String enteryString;
    private Character alice;
    private boolean firstVisit = true;
    private ArrayList<Character> NPC = new ArrayList<>();

    public Location() {
        // To be overridden
    }

    public void setMain(Character main) {
        alice = main;
    }

    public void setEntryString(String newText) {
        // TODO: get from text file
        enteryString = newText;
    }

    public void setLocationID(LocationName newLocationID) {
        locationID = newLocationID;
    }

    public LocationName getLocationID() {
        return locationID;
    }

    public LocationName enter() {
        if (firstVisit) {
            System.out.println(enteryString);
            firstVisit = false;
        }
        return run();
    }

    public LocationName run() {
        // to be overridden
        return LocationName.END;
    }

}
