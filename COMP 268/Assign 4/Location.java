import java.util.*;

public class Location {
    private LocationName locationID;
    private LocationName nextLocation = null;
    private Character alice;
    private boolean firstVisit = true;
    private ArrayList<Character> NPC = new ArrayList<>();
    private ArrayList<Item> loot = new ArrayList<>();
    private ArrayList<Door> roomExits = new ArrayList<>();

    public Location() {
        // To be overridden
    }

    public void setMainChar(Character main) {
        this.alice = main;
    }

    public Character getMainChar() {
        return this.alice;
    }

    public void setLocationID(LocationName newLocationID) {
        this.locationID = newLocationID;
    }

    public void addExit(Door newDoor) {
        this.roomExits.add(newDoor);
    }

    public ArrayList<Door> getExits() {
        return this.roomExits;
    }

    public LocationName getLocationID() {
        return locationID;
    }

    public void addItem(Item newLoot) {
        this.loot.add(newLoot);
    }

    public void setNextLocation(LocationName nextLocation) {
        this.nextLocation = nextLocation;
    }

    public String getLocationIDString() {
        return locationID.toString();
    }

    public void getFirstVisit() {
        if (firstVisit) {
            System.out.println(Control.getFromDatabase(this.getLocationIDString(), "EntryString"));
            firstVisit = false;
        } else {
            System.out.println(Control.getFromDatabase(this.getLocationIDString(), "ReturnString"));
        }
    }

    public ArrayList<Item> getLoot() {
        return this.loot;
    }

    public int getDoorID(String findThis) { // looks for the target location in an arraylist
        for (int i = 0; i < roomExits.size(); i++) {
            if (roomExits.get(i).getName().equalsIgnoreCase(findThis)) {
                return i;
            }
        }
        return -1;
    }

    public LocationName enter() {
        getFirstVisit();
        while (true) {
            if (nextLocation != null) {
                LocationName returnLocation = nextLocation;
                this.nextLocation = null;
                return returnLocation;
            }
            System.out.print(Control.getFromDatabase(this.getLocationIDString(), "PromptString"));
            Action newAction = Control.getAction();
            switch (newAction.getActionType()) {
                case TALK:
                    talk(newAction.getSecondaryArg());
                    break;
                case TAKE:
                    takeItem(newAction.getSecondaryArg());
                    break;
                case USE:
                    useItem(newAction.getSecondaryArg());
                    break;
                case QUIT:
                    quit();
                    break;
                case INVENTORY:
                    alice.getInventory().printInventory();
                    break;
                case HELP:
                    Control.printFile(Constants.HELPFILE);
                    break;
                case LOOKAROUND:
                    lookAround();
                    break;
                case EXIT:
                    LocationName nextRoom = exit(newAction.getSecondaryArg()); // get target for next room
                    if (nextRoom == this.locationID) { // if we are not leaving the room then loop again
                        break;
                    } else {
                        return nextRoom;
                    }
                case YES:
                    yes();
                    break;
                case NO:
                    no();
                    break;
                case INVALID:
                    System.out.println("Invalid Action");
                    break;
            }
        }
    }

    public void talk(String target) {
        // talk to someone in the room
    }

    public void takeItem(String itemName) {
        for (int i = 0; i < loot.size(); i++) {
            if (this.loot.get(i).getName().equalsIgnoreCase(itemName)) {
                this.alice.addInventory(this.loot.get(i));
                this.loot.remove(i);
                System.out.println("Item Taken: " + itemName);
                return;
            }
        }
        System.out.println("Item not Found: " + itemName);
    }

    public void useItem(String itemName) {
        if (alice.getInventory().consumeItem(itemName)) {
            System.out.println("Item Used: " + itemName);
            switch (itemName) {
                case "bottle":
                    alice.setSize(1);
                    System.out.println("Alice was now only ten inches high, and her face brightened up at the thought that she was now the right size for going through the little door into that lovely garden");
                    break;
                 case "cake":
                    alice.setSize(9);
                    System.out.println("Alice was more than 9 feet tall!");
                    break;
                case "leaf":
                    System.out.println("You crumple the leaf");
                    break;
                case "key":
                    if (this.locationID == LocationName.TEARS) { // key only works in one room
                        getExits().get(getDoorID("garden")).setLock(false);
                        System.out.println("Garden door unlocked");
                    }
                    break;
                default:
                    System.out.println("unknown item " + itemName);
                    break;
            }
        } else {
            System.out.println("Item not found: " + itemName);
        }
    }

    public void quit() {
        System.out.println("Thanks for Playing");
        System.exit(0);
    }

    public void lookAround() {
        // Describe room and list items
        System.out.println(Control.getFromDatabase(this.getLocationIDString(), "ROOMDESCRIPTION")); // print out a room
                                                                                                    // description
        System.out.println("Items you can see:");
        if (loot.isEmpty()) {
            System.out.println("No Loot Found");
        } else {
            for (Item model : loot) { // print out all the items in this room
                System.out.println(" - " + model.getName() + ": " + model.getDesciption());
            }
        }
        System.out.println("Characters in this room: ");
        if (NPC.isEmpty()) {
            System.out.println("No Characters Found");
        } else {
            for (Character selectedNPC : NPC) { // print out all the items in this room
                System.out.println(" - " + selectedNPC.getName());
            }
        }
        System.out.println("Doors in this room: ");
        for (Door someDoor : roomExits) { // print out all the items in this room
            System.out.println(" - " + someDoor.getName());
        }
        System.out.println(); // add some whitespace
    }

    public LocationName exit(String target) {
        if (getDoorID(target) >= 0) {
            if (getExits().get(getDoorID(target)).getLock()) {
                System.out.println(target + " is Locked!");
                return this.locationID;
            }else if (alice.getSize() > getExits().get(getDoorID(target)).getMaxSize()){
                System.out.println("Alice is too big");
                return this.locationID;
            } else {
                System.out.println(getExits().get(getDoorID(target)).getCode().name());
                return getExits().get(getDoorID(target)).getCode();
            }
        } else {
            System.out.println("No exit called: " + target);
            return this.locationID;
        }
    }

    public void yes() {
        // to be overriden
    }

    public void no() {
        // to be overriden
    }
}
