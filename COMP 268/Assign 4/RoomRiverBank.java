/**
* title: RoomRiverBank.java
* description: Object for room RiverBank, extended from Location class. Builds specific items in room. 
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
*    yes()
*     Overrides default class Yes(). Extra graphics are printed.
*
*    no()
*     Overrides default class no(). Immediatly ends game.
*
*    tumblingGraphics()
*     Displays tumbling graphics as Alice falls down the well. 
*
*   Instance Variables:
*    None
*/

public class RoomRiverBank extends Location {

    public RoomRiverBank(Alice main) {
        super(main, LocationList.RIVERBANK);
        super.addItem(new ItemLeaf());
    }
    
    @Override
    public void yes() { //Since the first room is special we need to override the yes and no commands.
        System.out.println(Control.getFromDatabase(super.getdatabaseFile(), "DownTheRabbitHole"));
        tumblingGraphics();
        super.setNextLocation(LocationList.CORRIDOR);
    }

    @Override
    public void no() {
        System.out.println(Control.getFromDatabase(super.getdatabaseFile(), "EndString"));
        Control.quit();
    }

    public void tumblingGraphics() { //generates some fun graphics
        for (int j = 0; j < 40; j++) { // print stars
            StringBuilder starLine = new StringBuilder();
            for (int i = 0; i < Constants.CONSOLEWIDTH; i++) { // print stars
                if (Math.random() < 0.85) {
                    starLine.append(" ");
                } else {
                    starLine.append("*");
                }
            }
            if (j == 20) {
                System.out.println("   *   Orange Marmalade   *             *                     *"); //print this on line 20 of the star generator
            }
            System.out.println(starLine);
            Control.delayPrinter(1);
        }
        Control.delayPrinter(5);
    }
}
