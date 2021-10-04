/**
* title: RoomCourtyard.java
* description: Object for room Courtyard, extended from Location class. Builds specific items in room. 
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: RoomCourtyard
*   Description: 
*    Creates a new instace of a room. Adds items, doors, characters to the room.
*    Super conconstructor will handle assigning of values. Child class may contain some overrides for special case rooms. 
*
*   Constructors:
*    RoomCourtyard(Alice)
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

public class RoomCourtyard extends Location {

    public RoomCourtyard(Alice main) {
        super(main, LocationList.COURTYARD);
        super.addExit(new Door("Yard", LocationList.TEA));
        super.addExit(new Door("Courtroom", LocationList.COURT));
        super.addItem(new ItemMallet());
        super.addItem(new ItemBall());
    }

    
}
