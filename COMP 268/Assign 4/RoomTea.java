public class RoomTea extends Location {
    public RoomTea(Alice main) {
        super(main, LocationList.TEA);
        super.addExit(new Door("Hall", LocationList.CORRIDOR));
        super.addExit(new Door("Forest", LocationList.MUSHROOM));
        super.addExit(new Door("Courtyard", LocationList.COURTYARD, true, 6));
        super.addCharacter(new Character("Hatter", CharacterList.HATTER, "Courtyard"));
        super.addItem(new ItemLeaf());
        super.addItem(new ItemTea());
        super.addItem(new ItemGloves());
        super.addItem(new ItemFan());
    }
}
