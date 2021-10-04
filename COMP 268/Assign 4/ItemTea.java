/**
* title: ItemTea.java
* description: Object for type tea, contains override for use, extended from base Item class.
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: ItemTea
*   Description: 
*    This class builds the specific item by pass a target enum to a parent super constructor.
*
*   Constructors:
*    ItemTea()
*     Uses the super constructor to build item based on Enum given. Item can be designated as consumable (one time use) or not. 
* 
*   Methods:
*    action()
*     Overrides the parent Action() function. This contains special action to end the game. 
*
*   Instance Variables:
*    None
*/

public class ItemTea extends Item {

    public ItemTea(){
        super(ItemList.TEA, true);
    }

    @Override
    public boolean action(){
        System.out.println("Item Used: " + this.getName());
        System.out.println(this.getActionText());
        Control.quit();
        return true;
    }
}
