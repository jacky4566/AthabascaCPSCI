public class Garden extends Location {

    public Garden(Character main) {
        super.setLocationID(LocationName.GARDEN);
        super.setMainChar(main);
        super.addExit(new Door("Hall", LocationName.TEARS));
        super.addExit(new Door("Forest", LocationName.MUSHROOMS));
    }

}
