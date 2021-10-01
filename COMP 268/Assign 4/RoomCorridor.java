public class RoomCorridor extends Location {

    public RoomCorridor(Alice main) {
        super(main, LocationList.CORRIDOR);
        super.addItem(new ItemKey());
        super.addItem(new ItemBottle());
        super.addItem(new ItemCake());
        super.addExit(new Door("Garden", LocationList.GARDEN, true, 1));
        super.addExit(new Door("Tea", LocationList.TEA, true, 6));
        super.addCharacter(new Character("Dormouse", CharacterList.DORMOUSE, "Tea"));
    } 
 }
