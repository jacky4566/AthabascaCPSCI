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