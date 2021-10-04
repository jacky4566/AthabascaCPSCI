/**
* title: RoomCorridor.java
* description: Object for room Corridor, extended from Location class. Builds specific items in room. 
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: RoomCorridor
*   Description: 
*    Creates a new instace of a room. Adds items, doors, characters to the room.
*    Super conconstructor will handle assigning of values. Child class may contain some overrides for special case rooms. 
*
*   Constructors:
*    RoomCorridor(Alice)
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

public class RoomCorridor extends Location {

    public RoomCorridor(Alice main) {
        super(main, LocationList.CORRIDOR);
        super.addItem(new ItemKey());
        super.addItem(new ItemBottle());
        super.addItem(new ItemCake());
        super.addExit(new Door("Garden", LocationList.GARDEN, true, 1));
        super.addExit(new Door("Tea", LocationList.TEA, true, 6));
        super.addCharacter(new Character("Dormouse", CharacterList.DORMOUSE, "Tea"));
    } 
 }
