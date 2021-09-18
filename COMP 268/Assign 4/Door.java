public class Door {
    private String doorName;
    private LocationName locationCode;
    private boolean isLocked;
    private int maxSize;

    // constructor
    public Door(String doorName, LocationName locationCode, boolean isLocked, int maxSize) {
        this.doorName = doorName;
        this.locationCode = locationCode;
        this.isLocked = isLocked;
        this.maxSize = maxSize;
    }

    public Door(String doorName, LocationName locationCode) {
        this.doorName = doorName;
        this.locationCode = locationCode;
        this.isLocked = false;
        this.maxSize = 6;
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