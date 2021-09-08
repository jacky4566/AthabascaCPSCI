public class Tears extends Location {

    public Tears(Character main) {
        super.setEntryString("TEARS");
        super.setLocationID(LocationName.TEARS);
        super.setMain(main);
    }

    @Override
    public LocationName run() {
        // Put the main stuffs here
        return LocationName.END;
    }
}
