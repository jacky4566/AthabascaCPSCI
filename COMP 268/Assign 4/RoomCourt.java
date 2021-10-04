/**
* title: RoomCourt.java
* description: Object for room Court, extended from Location class. Builds specific items in room. 
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: RoomCourt
*   Description: 
*    Creates a new instace of a room. Adds items, doors, characters to the room.
*    Super conconstructor will handle assigning of values. Child class may contain some overrides for special case rooms. 
*
*   Constructors:
*    RoomCourt(Alice)
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

public class RoomCourt extends Location {

    public RoomCourt(Alice main) {
        super(main, LocationList.COURT);
        super.addItem(new ItemWatch());
        super.addItem(new ItemSlippers());
        super.addCharacter(new Character("Queen", CharacterList.QUEEN));
    }
}
