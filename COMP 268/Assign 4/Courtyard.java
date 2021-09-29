public class Courtyard extends Location {

    public Courtyard(Alice main) {
        super(main, LocationList.COURTYARD);
        super.addExit(new Door("Yard", LocationList.TEA));
        super.addExit(new Door("Courtroom", LocationList.COURTROOM));
        super.addItem(new Mallet());
        super.addItem(new Ball());
    }
}
