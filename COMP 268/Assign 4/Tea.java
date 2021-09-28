public class Tea extends Location {

    

    public Tea(Alice main) {
        super.setLocationID(LocationName.TEA);
        super.setMainChar(main);
        super.addExit(new Door("Hall", LocationName.TEARS));
        super.addExit(new Door("Forest", LocationName.MUSHROOMS));
        super.addExit(new Door("Courtyard", LocationName.COURTYARD, true, 6));
        super.addCharacter(new Character("Hatter", "Courtyard"));
    }
}
