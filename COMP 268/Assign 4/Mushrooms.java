public class Mushrooms extends Location {

    public Mushrooms(Alice main) {
        super(main, LocationList.MUSHROOMS);
        super.addExit(new Door("Pathway", LocationList.GARDEN));
        super.addExit(new Door("Yard", LocationList.TEA));
        super.addItem(new Mushroom());
        super.addCharacter(new Character("Caterpillar"));
    }
}
