public class Tea extends Location {

    public Tea(Character main) {
        super.setLocationID(LocationName.TEA);
        super.setMainChar(main);
        super.addExit(new Door("Hall", LocationName.TEARS));
        super.addExit(new Door("Forest", LocationName.MUSHROOMS));
        super.addExit(new Door("Courtyard", LocationName.COURTYARD));
    }
}
