public class Mushrooms extends Location {

    public Mushrooms(Character main) {
        super.setLocationID(LocationName.MUSHROOMS);
        super.setMainChar(main);
        super.addExit(new Door("Pathway", LocationName.GARDEN));
        super.addExit(new Door("Yard", LocationName.TEA));
    }
}
