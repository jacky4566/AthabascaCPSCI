public class Tears extends Location {

    public Tears(Character main) {
        super.setLocationID(LocationName.TEARS);
        super.setMainChar(main);
        super.addItem(new Item("Key", "A Small golden Key"));
        super.addItem(new Item("Bottle", "DRINK ME"));
        super.addItem(new Item("Cake", "EAT ME"));
        super.addExit(new Door("Garden", LocationName.GARDEN, true, 1));
        super.addExit(new Door("Tea", LocationName.TEA, true, 6));
        super.addExit(new Door("Field", LocationName.COURTYARD, true, 6));
    } 
 }
