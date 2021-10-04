/**
* title: RoomMushroom.java
* description: Object for room Mushroom, extended from Location class. Builds specific items in room. 
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: RoomRiverBank
*   Description: 
*    Creates a new instace of a room. Adds items, doors, characters to the room.
*    Super conconstructor will handle assigning of values. Child class may contain some overrides for special case rooms. 
*
*   Constructors:
*    RoomRiverBank(Alice)
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

public class RoomMushroom extends Location {

    public RoomMushroom(Alice main) {
        super(main, LocationList.MUSHROOM);
        super.addExit(new Door("Pathway", LocationList.GARDEN));
        super.addExit(new Door("Yard", LocationList.TEA));
        super.addItem(new ItemMushroom());
        super.addCharacter(new Character("Caterpillar", CharacterList.CATERPILLAR));
    }
}
