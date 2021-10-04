/**
* title: Item.java
* description: Object for items, contains overridable values for unique items
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: Item
*   Description: 
*    Parent class for Items used in game. Gets information from target files upon creation in constructor.
*    Can return variables as required. 
*    Contains an action action script that is changed by some child classes. 
*
*   Constructors:
*    Item()
*     No to be used and this generates nothing.
*
*    Item(ItemList, boolean)
*     Builds new item based on a given Enum. Item can be designated as consumable or not
* 
*   Methods:
*    ItemList getID()
*      Returns assigned Enum of this object.
*
*    String getName()
*      Returns assigned readable name of this object. Formatted for first letter capitalization. 
*
*    String getDesciption()
*      Returns assigned Desciption name of this object. Read from file in constructor.
*
*    boolean getConsumable()
*      Returns assigned Consumable state for later destruction.
*
*    String getActionText()
*      Returns assigned getActionText name of this object. Read from file in constructor. 
*
*    boolean action()
*      Overriden by some child classes. This method performs the unique action of each item. 
*      Default action is to print out the use of the item and its action text.
*      Returns consumable state of object for later destruction.
*
*   Instance Variables:
*     private boolean isConsumable:     Determines if this object can be consumed later in the code
*     private ItemList itemID:          Assigns an Enum ID
*     private String name:              Commmon name of item for printing and identiciation by user
*     private String description:       A description of the item assigned by a text file read in the constructor
*     private String actionText:        A text action of the item assigned by a text file read in the constructor
*/

public class Item {
    private boolean isConsumable = true;
    private ItemList itemID; 
    private String name;
    private String description;
    private String actionText;

    public Item(){
       //Not to be used
    }

    public Item(ItemList itemID, boolean consumable){
        this.isConsumable = consumable;
        this.itemID = itemID;
        this.name = itemID.toString();
        String databaseFile = "ITEM" + itemID + ".txt";
        this.description = Control.getFromDatabase(databaseFile, "DESCRIPTION");
        this.actionText = Control.getFromDatabase(databaseFile, "ACTION");
    }

    //GETTER
    public ItemList getID(){
        return itemID;
    }

    public String getName(){ //Gets name. Format 1st letter capital
        return this.name.substring(0, 1).toUpperCase() + this.name.substring(1).toLowerCase();
    }

    public String getDesciption(){
        return this.description;
    }

    public boolean getConsumable(){
        return this.isConsumable;
    }

    public String getActionText(){
        return this.actionText;
    }

    public boolean action(){
        //can be overridden
        System.out.println("Item Used: " + this.getName());
        System.out.println(this.getActionText());
        return isConsumable;
    }
}
