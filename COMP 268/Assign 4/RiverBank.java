public class RiverBank extends Location {

    public RiverBank(Alice main) {
        super(main, LocationList.RIVERBANK);
        super.addItem(new Leaf());
    }
    
    @Override
    public void yes() {
        System.out.println(Control.getFromDatabase(super.getdatabaseFile(), "DownTheRabbitHole"));
        tumblingGraphics();
        super.setNextLocation(LocationList.TEARS);
    }

    @Override
    public void no() {
        System.out.println(Control.getFromDatabase(super.getdatabaseFile(), "EndString"));
        super.quit();
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
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
