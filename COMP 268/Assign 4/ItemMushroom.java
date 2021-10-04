/**
* title: ItemMushroom.java
* description: Object for type mushroom, extended from base Item class. Contains minigame
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: ItemMushroom
*   Description: 
*    This class builds the specific item by pass a target enum to a parent super constructor.
*
*   Constructors:
*    ItemMushroom()
*     Uses the super constructor to build item based on Enum given. Item can be designated as consumable (one time use) or not. 
* 
*   Methods:
*    action()
*     Overrides the default action. This class contains a minigame where the user must fix Alice's size by clicking g or s on the keyboard. 
*     Minigame only exits on correctly solving the puzzle.
*
*   Instance Variables:
*    None
*/

import java.util.Random;

public class ItemMushroom extends Item {

    public ItemMushroom(){
        super(ItemList.MUSHROOM, true);
    }

    @Override
    public boolean action() { //A minigame
        //A fun mini game
        System.out.println(
                "Alice begins to grow. OR maybe to shrink? \nYou must help Alice get to the right size. She wants to be exactly 6 feet tall!\nPress S to shrink and G to grow");
        int size = 4;
        System.out.println("Alice is now " + size + " feet tall.");
        while (size != 6) {
            char input = Control.getChar();
            Random r = new Random();
            switch (input) {
                case 'g':
                    size += r.nextInt(5);
                    System.out.println("Alice is now " + size + " feet tall.");
                    break;
                case 's':
                    size -= r.nextInt(5);
                    if (size <= 0) {
                        size = 1;
                    }
                    System.out.println("Alice is now " + size + " feet tall.");
                    break;
                case 'q':
                    System.out.println("Minigame Quit");
                    break;
                default:
                    break;
            }
            if (size == 6){
                System.out.println("Success! Alice feels much better");
                break;
            }
        }
        return true;
    }
}
