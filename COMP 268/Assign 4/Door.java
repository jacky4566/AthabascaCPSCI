public class Door {
    private String doorName;
    private LocationName locationCode;
    private boolean isLocked = false;
    private int maxSize = 6;

    // constructor
    public Door(String doorName, LocationName locationCode, boolean isLocked, int maxSize) {
        this.doorName = doorName;
        this.locationCode = locationCode;
        this.isLocked = isLocked;
        this.maxSize = maxSize;
    }

    public Door(String doorName, LocationName locationCode, boolean isLocked) {
        this.doorName = doorName;
        this.locationCode = locationCode;
        this.isLocked = isLocked;
    }

    public Door(String doorName, LocationName locationCode) {
        this.doorName = doorName;
        this.locationCode = locationCode;
    }

    // getter
    public String getName() {
        return doorName;
    }
    public LocationName getCode() {
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