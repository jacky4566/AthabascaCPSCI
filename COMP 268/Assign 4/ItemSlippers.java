/**
* title: ItemSlippers.java
* description: Object for type slippers, extended from base Item class.
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
*    None
*
*   Instance Variables:
*    None
*/

public class ItemSlippers extends Item {

    public ItemSlippers(){
        super(ItemList.SLIPPERS, false);
    }
}
