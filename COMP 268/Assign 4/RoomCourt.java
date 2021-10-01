public class RoomCourt extends Location {

    public RoomCourt(Alice main) {
        super(main, LocationList.COURT);
        super.addItem(new ItemWatch());
        super.addItem(new ItemSlippers());
        super.addCharacter(new Character("Queen", CharacterList.QUEEN));
    }
}
