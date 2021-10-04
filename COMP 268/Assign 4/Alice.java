/**
* title: Alice.java
* description: Special class for Alice the main character who holds unique properties
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: Alice
*   Description: 
*    This special class exists for the main character alice and her unique interations and inventory.
*
*   Constructors:
*    Alice()
*     No variables to assign.
* 
*   Methods:
*    void addInventory(Item):
*     adds a new item to our personal inventory.
*
*    Inventory getInventory():
*     returns the entire inventory of Alice
*
*    int getSize():
*     returns the assigned size of alice
*
*    void setSize(int):
*     assigns a new size for alice.
*
*   Instance Variables:
*    private Inventory inventory:   Inventory system for main character alice.
*    private int size:              Assigns the physical size of alice for fitting through various doors.
*/

public class Alice {
    private Inventory inventory = new Inventory(); //Stores Character Inventory
    private int size = 6; // as measured by Alice in feet

    // Constructor
    public Alice() {
        //No constructor needed
    }

    // Functions
    public void addInventory(Item newItem) {
        inventory.addItem(newItem);
    }

    // Getters
    public Inventory getInventory() {
        return this.inventory;
    }

    public int getSize() {
        return this.size;
    }

    // Setters
    public void setSize(int size) {
        this.size = size;
    }
}
