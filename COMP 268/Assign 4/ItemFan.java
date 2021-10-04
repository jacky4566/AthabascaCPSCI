/**
* title: ItemBall.java
* description: Object for type fan, extended from base Item class.
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: ItemFan
*   Description: 
*    This class builds the specific item by pass a target enum to a parent super constructor.
*
*   Constructors:
*    ItemFan()
*     Uses the super constructor to build item based on Enum given. Item can be designated as consumable (one time use) or not. 
* 
*   Methods:
*    None
*
*   Instance Variables:
*    None
*/

public class ItemFan extends Item {

    public ItemFan(){
        super(ItemList.FAN, false);
    }
}
