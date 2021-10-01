public class RoomCourtyard extends Location {

    public RoomCourtyard(Alice main) {
        super(main, LocationList.COURTYARD);
        super.addExit(new Door("Yard", LocationList.TEA));
        super.addExit(new Door("Courtroom", LocationList.COURT));
        super.addItem(new ItemMallet());
        super.addItem(new ItemBall());
    }
}
