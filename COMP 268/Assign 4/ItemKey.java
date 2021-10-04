/**
* title: ItemKey.java
* description: Object for type key, extended from base Item class.
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: ItemKey
*   Description: 
*    This class builds the specific item by pass a target enum to a parent super constructor.
*
*   Constructors:
*    ItemKey()
*     Uses the super constructor to build item based on Enum given. Item can be designated as consumable (one time use) or not. 
* 
*   Methods:
*    None
*
*   Instance Variables:
*    None
*/

public class ItemKey extends Item {

    public ItemKey(){
        super(ItemList.KEY, true);
    }
}
