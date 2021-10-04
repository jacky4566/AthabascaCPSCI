/**
* title: RoomTea.java
* description: Object for room Tea, extended from Location class. Builds specific items in room. 
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: RoomTea
*   Description: 
*    Creates a new instace of a room. Adds items, doors, characters to the room.
*    Super conconstructor will handle assigning of values. Child class may contain some overrides for special case rooms. 
*
*   Constructors:
*    RoomTea(Alice)
*     Creates a new class from the given Enum. 
*     Reference of main character Alice is given for manipulation.
*     Room also adds all target objects, doors, and characters.
* 
*   Methods:
*    None
*
*   Instance Variables:
*    None
*/

public class RoomTea extends Location {
    public RoomTea(Alice main) {
        super(main, LocationList.TEA);
        super.addExit(new Door("Hall", LocationList.CORRIDOR));
        super.addExit(new Door("Forest", LocationList.MUSHROOM));
        super.addExit(new Door("Courtyard", LocationList.COURTYARD, true, 6));
        super.addCharacter(new Character("Hatter", CharacterList.HATTER, "Courtyard"));
        super.addItem(new ItemLeaf());
        super.addItem(new ItemTea());
        super.addItem(new ItemGloves());
        super.addItem(new ItemFan());
    }
}
