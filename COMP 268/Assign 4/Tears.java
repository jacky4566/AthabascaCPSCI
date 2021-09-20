public class Tears extends Location {

    public Tears(Alice main) {
        super.setLocationID(LocationName.TEARS);
        super.setMainChar(main);
        super.addItem(new Item("Key"));
        super.addItem(new Item("Bottle"));
        super.addItem(new Item("Cake"));
        super.addExit(new Door("Garden", LocationName.GARDEN, true, 1));
        super.addExit(new Door("Tea", LocationName.TEA, true, 6));
        super.addCharacter(new Character("Dormouse", "Tea"));
    } 
 }
