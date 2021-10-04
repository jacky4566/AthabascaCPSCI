/**
* title: Location.java
* description: Mid level location parent class, Contains all primary code for room loops and action to execute.
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: Location
*   Description: 
*    A large class that contains loops for handling user input and task execution. Keeps track of all item, characters, and exits within a room.
*
*   Constructors:
*    Location()
*     To be overriden by child room classes.
*
*    Location(Alice, LocationList)
*     Takes a reference to the main character alice and the enum LocationList for this room. Gets all the String text from target TXT file of the same name. 
* 
*   Methods:
*    void addExit(Door):
*     Add a new door to this room.
*
*    void addCharacter(Character):
*     Add a new charcter to this room.
*
*    void addItem(Item):
*     Add a new item to this room.
*
*    LocationList enter():
*     Code runs upon entry to room, returns the next room for the user to enter. Acts as a run() for this room.
*     Checks if we have visted this room before. Checks for end game condition and processes user choice.
*
*    void checkEnd():
*     Checks for end game conditions
*
*    void speak(String, String):
*     Speak to a given character (first arg) with player reponse (Second arg)
*     Can unlock doors and perform actions if user is succesful with thier prompt.
*
*    void keepItem(String):
*     to be overriden for safe room
*
*    void takeItem(String):
*     Take item stored in room. Removes from room linked list and add to alice inventory.
*
*    void unlockDoor(String):
*     Unlocks door based on given name
*
*    void useItem(String):
*     Uses a target item. Switch is used to determine each items unique action based on the item Enum
*
*    void lookAround():
*     Prints a description of the room including items, characters, and exits.
*
*    void listItems():
*     Prints all items found in room. Overriden by safe room.
*
*    LocationList exit(String):
*     Attempts to exit the room based on a given string door name. Returns next room to traverse based on LocationList Enum.
*
*    void yes():
*     To be overriden
*
*    void no():
*     To be overriden
*
*    Alice getMainChar():
*     Returns reference to Alice object stored in room.
*
*    ArrayList<Door> getExits():
*     Returns list of exits in this room.
*
*    LocationList getLocationID():
*     Returns LocationList enum of this room
*
*    String getLocationIDString():
*     Returns name of room as string
*
*    String getLocationIDString():
*     Returns name of room as string
*
*    String getdatabaseFile():
*     Returns TXT database file of text information for this room
*
*    void getFirstVisit():
*     If this is a first visit to the room print out the entry text
*
*    ArrayList<Item> getLoot():
*     Returns list of items in this room
*
*    int getDoorID():
*     returns list pointer to a target door
*
*    void setNextLocation():
*     Sets the next location to traverse once we exit the room
*
*   Instance Variables:
*    private LocationList locationID;       Contains Enum LocationList of this location
*    private Alice alice;                   Contains reference to the main character for manipulation
*    private boolean firstVisit;            Boolean state if user has not visited this room before, Default is true        
*    private String databaseFile;           TXT name of database file for Strings related to this room.
*    private String roomDescription;        String text of description of this room, fetched from database file
*    private String promptDescription;      String text of prompt display by room at each input, fetched from database file
*    private String entryString;            String text of text play upon entry to room, fetched from database file
*    private String returnString;           String text of text play upon re-entry to room, fetched from database file
*    private ArrayList<Character> NPC:      LinkList of None Playable characters in this room
*    private ArrayList<Item> loot:          LinkList of items in this room
*    private ArrayList<Door> roomExits:     LinkList of exits in this room
*    private LocationList nextRoom;         Enum LocationList of next room after exit has ben decided.
*
*/

import java.util.*;

public class Location {
    private LocationList locationID;
    private Alice alice;
    private boolean firstVisit = true;
    private String databaseFile;
    private String roomDescription;
    private String promptDescription;
    private String entryString;
    private String returnString;
    private ArrayList<Character> NPC = new ArrayList<>();
    private ArrayList<Item> loot = new ArrayList<>();
    private ArrayList<Door> roomExits = new ArrayList<>();
    private LocationList nextRoom;

    public Location() {
        // To be overridden
    }

    public Location(Alice main, LocationList locationID) {
        this.locationID = locationID;
        nextRoom = locationID;
        this.alice = main;
        databaseFile = "ROOM" + locationID + ".txt";
        roomDescription = Control.getFromDatabase(databaseFile, "ROOMDESCRIPTION");
        promptDescription = Control.getFromDatabase(databaseFile, "PromptString");
        entryString = Control.getFromDatabase(databaseFile, "EntryString");
        returnString = Control.getFromDatabase(databaseFile, "ReturnString");
    }

    public void addExit(Door newDoor) {
        this.roomExits.add(newDoor);
    }

    public void addCharacter(Character newNPC) {
        this.NPC.add(newNPC);
    }

    public void addItem(Item newLoot) {
        this.loot.add(newLoot);
    }

    public LocationList enter() {
        getFirstVisit(); //check if this is first visit to the room
        while (true) { //loop until we have a new target room
            checkEnd();
            System.out.print(promptDescription);
            Action newAction = Control.getAction();
            switch (newAction.getActionType()) {
                case SPEAK:
                    speak(newAction.getFirstArg(), newAction.getSecondArg());
                    break;
                case TAKE:
                    takeItem(newAction.getFirstArg());
                    break;
                case USE:
                    useItem(newAction.getFirstArg());
                    break;
                case QUIT:
                    Control.quit();
                    break;
                case KEEP:
                    if (this.locationID == LocationList.GARDEN) { // if we are not leaving the room then loop again
                        keepItem(newAction.getFirstArg());
                    }
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
                    nextRoom = exit(newAction.getFirstArg()); // get target for next room
                    break;
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
            if (nextRoom != this.locationID) { // if we are not leaving the room then loop again
                return nextRoom;
            }
        }
    }

    public void checkEnd() {
        if (this.locationID == LocationList.COURT) { // only really need this in the courtroom
            for (int i = 0; i < NPC.size(); i++) {
                if (NPC.get(i).getID() == CharacterList.QUEEN) {
                    if (NPC.get(i).getConversation().getComplete()) { // End the game
                        System.out.println(Control.getFromDatabase(this.getdatabaseFile(), "EndStringWin"));
                        Control.quit();
                    } else if (NPC.get(i).getConversation().getAttempts() >= 3) {
                        System.out.println(Control.getFromDatabase(this.getdatabaseFile(), "EndStringLose"));
                        Control.quit();
                    }
                }
            }
        }
    }

    public void speak(String target, String response) {
        for (int i = 0; i < NPC.size(); i++) {
            if (NPC.get(i).getName().equalsIgnoreCase(target)) {
                if (response != null) {
                    System.out.println(NPC.get(i).getConversation().getConversation(response));
                    if (NPC.get(i).getConversation().getComplete() && NPC.get(i).getDoorGuard() != null) {
                        unlockDoor(NPC.get(i).getDoorGuard());
                    }
                } else {
                    System.out.println(NPC.get(i).getConversation().getConversation());
                }
                return;
            }
        }
        System.out.println("Character not Found: " + target);
    }

    public void keepItem(String itemName) {
        // to be overriden for safe room
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

    public void unlockDoor(String doorName) {
        getExits().get(getDoorID(doorName)).setLock(false);
        System.out.println(doorName + " door unlocked");
    }

    public void useItem(String itemName) {
        if (alice.getInventory().hasItem(itemName)) {
            Item selectedItem = alice.getInventory().getItem(itemName);
            switch (selectedItem.getID()) {
                case BOTTLE:
                    alice.getInventory().consumeItem(itemName);
                    alice.setSize(1);
                    break;
                case CAKE:
                    alice.getInventory().consumeItem(itemName);
                    alice.setSize(9);
                    break;
                case KEY:
                    if (this.locationID == LocationList.CORRIDOR) { // key only works in one room
                        unlockDoor("garden");
                        alice.getInventory().consumeItem(itemName);
                    }else{
                        System.out.println("That item doesnt work here");
                    }
                    break;
                case MALLET:
                    if (alice.getInventory().hasItem("ball") && this.locationID == LocationList.COURTYARD) {
                        alice.getInventory().consumeItem("ball");
                        alice.getInventory().consumeItem(itemName);
                        nextRoom = LocationList.COURT;
                        System.out.print("Press enter to continue");
                        Control.getAction();
                    }
                    break;
                case BALL:
                case TEA:
                case WATCH:
                case SLIPPERS:
                case FAN:
                case GLOVES:
                case LEAF:
                case MUSHROOM:
                    alice.getInventory().consumeItem(itemName);
                    break;
                default:
                    System.out.println("unknown item " + itemName);
                    break;
            }
        } else {
            System.out.println("Item not found: " + itemName);
        }
    }

    public void lookAround() {
        // Describe room and list items
        System.out.println(roomDescription);
        listItems();
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

    public void listItems() {
        // to be overriden for safe room
        System.out.println("Items you can see:");
        if (loot.isEmpty()) {
            System.out.println("No Loot Found");
        } else {
            for (Item model : loot) { // print out all the items in this room
                System.out.println(" - " + model.getName() + ": " + model.getDesciption());
            }
        }
    }

    public LocationList exit(String target) {
        if (getDoorID(target) >= 0) {
            if (getExits().get(getDoorID(target)).getLock()) {
                System.out.println(target + " is Locked!");
                return this.locationID;
            } else if (alice.getSize() > getExits().get(getDoorID(target)).getMaxSize()) {
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

    //GETTERS
    public Alice getMainChar() {
        return this.alice;
    }

    public ArrayList<Door> getExits() {
        return this.roomExits;
    }

    public LocationList getLocationID() {
        return locationID;
    }

    public String getLocationIDString() {
        return locationID.toString();
    }

    public String getdatabaseFile(){
        return this.databaseFile;
    }

    public void getFirstVisit() {
        if (firstVisit) {
            System.out.println(entryString);
            firstVisit = false;
        } else {
            System.out.println(returnString);
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

    //SETTERS
    public void setNextLocation(LocationList nextLocation) {
        this.nextRoom = nextLocation;
    }
}
