import java.util.*;

public class Location {
    private LocationName locationID;
    private Character alice;
    private boolean firstVisit = true;
    private ArrayList<Character> NPC = new ArrayList<>();
    private ArrayList<Item> loot = new ArrayList<>();

    public Location() {
        // To be overridden
    }

    public void setMainChar(Character main) {
        alice = main;
    }

    public Character getMainChar() {
        return this.alice;
    }

    public void setLocationID(LocationName newLocationID) {
        locationID = newLocationID;
    }

    public LocationName getLocationID() {
        return locationID;
    }

    public void addItem(Item newLoot) {
        this.loot.add(newLoot);
    }

    public String getLocationIDString() {
        return locationID.toString();
    }

    public boolean getFirstVisit() {
        if (firstVisit){
            return true;
        }else{
            firstVisit = false;
            return false;
        }
    }

    public LocationName enter() {
        // to be overridden
        return LocationName.END;
    }

    public ArrayList<Item> getLoot(){
        return this.loot;
    }

    public void lookAround() {
        //Describe room and list items
        System.out.println(Control.getFromDatabase(this.getLocationIDString(), "ROOMDESCRIPTION")); //print out a room description
        System.out.println("Items you can see:");
        for (Item model : loot) { //print out all the items in this room
            System.out.println(" - " + model.getName() + ": " + model.getDesciption());
        }
        System.out.println("Characters in this room: ");
        for (Character selectedNPC : NPC) { //print out all the items in this room
            System.out.println(" - " + selectedNPC.getName());
        }
        System.out.println(); //add some whitespace
    }

    public boolean takeItem(String itemName){
        for (int i = 0; i < loot.size(); i++) {
            if (this.loot.get(i).getName().equals(itemName)){
                this.alice.addInventory(this.loot.get(i));
                this.loot.remove(i);
                return true;
            }
        }
        return false;
    }
}
