public class RoomRiverBank extends Location {

    public RoomRiverBank(Alice main) {
        super(main, LocationList.RIVERBANK);
        super.addItem(new ItemLeaf());
    }
    
    @Override
    public void yes() {
        System.out.println(Control.getFromDatabase(super.getdatabaseFile(), "DownTheRabbitHole"));
        tumblingGraphics();
        super.setNextLocation(LocationList.CORRIDOR);
    }

    @Override
    public void no() {
        System.out.println(Control.getFromDatabase(super.getdatabaseFile(), "EndString"));
        Control.quit();
    }

    public void tumblingGraphics() {
        for (int j = 0; j < 40; j++) { // print stars
            StringBuilder starLine = new StringBuilder();
            for (int i = 0; i < Constants.CONSOLEWIDTH; i++) { // print stars
                if (Math.random() < 0.85) {
                    starLine.append(" ");
                } else {
                    starLine.append("*");
                }
            }
            if (j == 20) {
                System.out.println("   *   Orange Marmalade   *             *                     *");
            }
            System.out.println(starLine);
            Control.delayPrinter(1);
        }
        Control.delayPrinter(5);
    }
}
