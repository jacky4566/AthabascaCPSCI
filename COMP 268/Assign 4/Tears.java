public class Tears extends Location {

    public Tears(Alice main) {
        super(main, LocationList.TEARS);
        super.addItem(new Key());
        super.addItem(new Bottle());
        super.addItem(new Cake());
        super.addExit(new Door("Garden", LocationList.GARDEN, true, 1));
        super.addExit(new Door("Tea", LocationList.TEA, true, 6));
        super.addCharacter(new Character("Dormouse", "Tea"));
    } 
 }
