public class Mushrooms extends Location {

    public Mushrooms(Alice main) {
        super.setLocationID(LocationName.MUSHROOMS);
        super.setMainChar(main);
        super.addExit(new Door("Pathway", LocationName.GARDEN));
        super.addExit(new Door("Yard", LocationName.TEA));
        super.addItem(new Item("Mushroom"));
        super.addCharacter(new Character("Caterpillar"));
    }
}
