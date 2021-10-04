/**
* title: ItemWatch.java
* description: Object for type watch, extended from base Item class.
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: ItemWatch
*   Description: 
*    This class builds the specific item by pass a target enum to a parent super constructor.
*
*   Constructors:
*    ItemWatch()
*     Uses the super constructor to build item based on Enum given. Item can be designated as consumable (one time use) or not. 
* 
*   Methods:
*    action()
*     Overrides the parent Action() function. This item gets the current day of the month as text to the user. As based on the rabbits pocket watch. 
*
*   Instance Variables:
*    None
*/

import java.util.Calendar;

public class ItemWatch extends Item {

    public ItemWatch(){
        super(ItemList.WATCH, false);
    }

    @Override
    public boolean action(){ //Has a special action that prints the day of the month
        Calendar cal = Calendar.getInstance(); //Get a new instance of Calender from Java
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH); //Get day of the month
        System.out.println("Item Used: " + this.getName());
        System.out.println(this.getActionText());
        System.out.println("The Clock reads: " + dayOfMonth); //Print out the clock text and current day of the month
        return super.getConsumable();
    }
}
