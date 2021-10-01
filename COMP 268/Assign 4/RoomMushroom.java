public class RoomMushroom extends Location {

    public RoomMushroom(Alice main) {
        super(main, LocationList.MUSHROOM);
        super.addExit(new Door("Pathway", LocationList.GARDEN));
        super.addExit(new Door("Yard", LocationList.TEA));
        super.addItem(new ItemMushroom());
        super.addCharacter(new Character("Caterpillar", CharacterList.CATERPILLAR));
    }
}
