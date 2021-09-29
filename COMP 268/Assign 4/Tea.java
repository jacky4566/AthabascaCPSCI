public class Tea extends Location {

    

    public Tea(Alice main) {
        super(main, LocationList.TEA);
        super.addExit(new Door("Hall", LocationList.TEARS));
        super.addExit(new Door("Forest", LocationList.MUSHROOMS));
        super.addExit(new Door("Courtyard", LocationList.COURTYARD, true, 6));
        super.addCharacter(new Character("Hatter", "Courtyard"));
    }
}
