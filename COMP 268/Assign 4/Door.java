/**
* title: Door.java
* description: Creates a connection between rooms and holds linking information
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: Door
*   Description: 
*    A class for linking rooms with doors. Contains common names for hallways and such. 
*    contains status of door locks and door size for alice.
*
*   Constructors:
*    Door(String, LocationList,  boolean, int) {
*     First argument. Display name for door.
*     Second argument. Enum locationcode of next room
*     Third argument. Initial status of this doors lock state
*     Forth argument. Size of door for alice to fit through.
*
*    Door(String, LocationList,  boolean) {
*     First argument. Display name for door.
*     Second argument. Enum locationcode of next room
*     Third argument. Initial status of this doors lock state
*
*    Door(String, LocationList) {
*     First argument. Display name for door.
*     Second argument. Enum locationcode of next room
* 
*   Methods:
*    String getName():
*     Returns the printable name for this door.
*
*    LocationList getCode():
*     Returns the inteded exit for this door as location Enum
*
*    boolean getLock():
*     Returns the current state of the door lock.
*
*    int getMaxSize():
*     Returns the maximum size of alice to fit through this door. 
*
*    void setLock(boolean):
*     Sets a new door lock state.
*
*   Instance Variables:
*    private String doorName;           Printable door name
*    private LocationList locationCode; Enum for the next location after this door
*    private boolean isLocked = false;  State of door lock. default is unlocked
*    private int maxSize = 6;           size of this door. default is size 6.
*
*/

public class Door {
    private String doorName;
    private LocationList locationCode;
    private boolean isLocked = false;
    private int maxSize = 6;

    // constructor
    public Door(String doorName, LocationList locationCode, boolean isLocked, int maxSize) {
        this.doorName = doorName;
        this.locationCode = locationCode;
        this.isLocked = isLocked;
        this.maxSize = maxSize;
    }

    public Door(String doorName, LocationList locationCode, boolean isLocked) {
        this.doorName = doorName;
        this.locationCode = locationCode;
        this.isLocked = isLocked;
    }

    public Door(String doorName, LocationList locationCode) {
        this.doorName = doorName;
        this.locationCode = locationCode;
    }

    // getter
    public String getName() {
        return doorName;
    }

    public LocationList getCode() {
        return locationCode;
    }

    public boolean getLock() {
        return isLocked;
    }

    public int getMaxSize(){
        return maxSize;
    }

    // setter
    public void setLock(boolean newState) {
        this.isLocked = newState;
    }

}