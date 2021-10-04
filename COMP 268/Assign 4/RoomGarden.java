/**
* title: RoomGarden.java
* description: Object for room Garden, extended from Location class. Builds specific items in room. 
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: RoomGarden
*   Description: 
*    Creates a new instace of a room. Adds items, doors, characters to the room.
*    Super conconstructor will handle assigning of values. Child class may contain some overrides for special case rooms. 
*
*   Constructors:
*    RoomGarden(Alice)
*     Creates a new class from the given Enum. 
*     Reference of main character Alice is given for manipulation.
*     Room also adds all target objects, doors, and characters.
* 
*   Methods:
*    void takeItem(String)
*     Takes item from Safe and puts it into alice inventory
*
*    void keepItem(String)
*     Takes item from alice and puts it into safe
*
*    String listItems()
*     Lists items in the safe.
*
*   Instance Variables:
*    None
*/

import java.util.ArrayList;

public class RoomGarden extends Location {

    //Garden room is special becuase it acts as a safe room for storing items. Item commands are overriden

    private ArrayList<Item> safe = new ArrayList<>();

    public RoomGarden(Alice main) {
        super(main, LocationList.GARDEN);
        super.addExit(new Door("Hall", LocationList.CORRIDOR));
        super.addExit(new Door("Forest", LocationList.MUSHROOM, true));
        super.addCharacter(new Character("Cat", CharacterList.CAT, "Forest"));
        safe.add(new ItemLeaf());
    }

    @Override
    public void takeItem(String itemName) {
        for (int i = 0; i < safe.size(); i++) {
            if (safe.get(i).getName().equalsIgnoreCase(itemName)) {
                super.getMainChar().getInventory().addItem(safe.get(i));
                safe.remove(i);
                System.out.println("Item Taken: " + itemName);
                return;
            }
        }
        System.out.println("Item not Found: " + itemName);
    }

    @Override
    public void keepItem(String itemName) {
        Inventory aliceInventory = super.getMainChar().getInventory();
        for (int i = 0; i < aliceInventory.countInventory(); i++) {
            if (aliceInventory.hasItem(itemName)) {
                safe.add(aliceInventory.getItem(itemName));
                aliceInventory.destroyItem(itemName);
                System.out.println("Item put in safe: " + itemName);
                return;
            }
        }
        System.out.println("Item not Found: " + itemName);
    }

    @Override
    public void listItems() {
        System.out.println("Items in your Safe:");
        if (safe.isEmpty()) {
            System.out.println("No items Found");
        } else {
            for (Item model : safe) { // print out all the items in this room
                System.out.println(" - " + model.getName() + ": " + model.getDesciption());
            }
        }
    }

}
