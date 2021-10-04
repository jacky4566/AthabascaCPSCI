/**
* title: Inventory.java
* description: Inventory class to hold objects in a box. Handles fetching, creating and use item. 
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: Inventory
*   Description: 
*    This class contains inventory for a character. Inventory of items can be manipulated with given methods.
*
*   Constructors:
*    Inventory()
*     Does Nothing. No information needs to be given.
* 
*   Methods:
*    int countInventory(): 
*     Returns size of the private linked list
*    
*    void addItem(Item): 
*     Adds a new item to the private linked list
*    
*    boolean consumeItem(String): 
*     Find the target item in the list and perform its action. Then remove item from the list.
*    
*    boolean hasItem(String): 
*     Returns Boolean if this inventory has the requested item by string name.
*    
*    Item getItem(String): 
*     Returns onject item based on given string name.
*    
*    boolean destroyItem(String): 
*     Destorys a given item from its string name. Returns success or failure. Designated action is not performed.
*    
*    void printInventory(): 
*     Prints out the contents of this inventory to the consol. 
*
*   Instance Variables:
*    private LinkedList<Item> itemList:     Contains a linked list of items for this inventory.
*/


import java.util.*;

public class Inventory {
    private LinkedList<Item> itemList = new LinkedList<>();

    public Inventory(){
        //does nothing
    }

    public int countInventory(){
        return itemList.size();
    }

    public void addItem(Item newItem) {
        itemList.add(newItem);
    }

    public boolean consumeItem(String name){ //Alice used the item. Perform its action and destroy object
        for (Item someItem : itemList) {
            if (someItem.getName().equalsIgnoreCase(name)){
                if (someItem.action()){
                    itemList.remove(someItem);
                }
                return true;
            }
        }
        return false;
    }

    public boolean hasItem(String name){ //Does this inventory contain the target item?
        for (Item someItem : itemList) {
            if (someItem.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public Item getItem(String name){ //retrieve item from inventory
        for (Item someItem : itemList) {
            if (someItem.getName().equalsIgnoreCase(name)){
                return someItem;
            }
        }
        return null;
    }

    public boolean destroyItem(String name){ //destory item without action
        for (Item someItem : itemList) {
            if (someItem.getName().equalsIgnoreCase(name)){
                itemList.remove(someItem);
                return true;
            }
        }
        return false;
    }

    public void printInventory() { //print all items in inventory
        System.out.println("Items in your Inventory:");
        for (Item someItem : itemList) {
            System.out.println(" - " + someItem.getName() + ": " + someItem.getDesciption());
        }
    }
}
