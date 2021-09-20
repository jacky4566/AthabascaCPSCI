public class Courtyard extends Location {

    public Courtyard(Alice main) {
        super.setLocationID(LocationName.COURTYARD);
        super.setMainChar(main);
        super.addExit(new Door("Yard", LocationName.TEA));
        super.addExit(new Door("Courtroom", LocationName.COURTROOM));
    }
}
