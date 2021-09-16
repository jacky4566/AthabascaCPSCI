public class RiverBank extends Location {

    public RiverBank(Character main) {
        super.setLocationID(LocationName.RIVERBANK);
        super.setMainChar(main);
        super.addItem(new Item("Leaf", "A green leaf"));
    }

    @Override
    public LocationName enter() {
        System.out.println(Control.getFromDatabase(super.getLocationIDString(), "EntryString"));
        while (true) {
            System.out.print(Control.getFromDatabase(super.getLocationIDString(), "Q1"));
            Action newAction = Control.getAction();
            switch (newAction.getActionType()) {
                case TALK:
                    System.out.println("No one to talk too");
                    break;
                case TAKE:
                    if (super.takeItem(newAction.getSecondaryArg())){
                        System.out.println("You take the item: " + newAction.getSecondaryArg());
                    }else{
                        System.out.println("No item called " + newAction.getSecondaryArg());
                    }
                    break;
                case QUIT:
                    System.out.println("Thanks for Playing");
                    System.exit(0);
                    break;
                case INVENTORY:
                    super.getMainChar().getInventory().printInventory();
                    break;
                case HELP:
                    Control.printFile(Constants.HELPFILE);
                    break;
                case LOOKAROUND:
                    super.lookAround();
                    break;
                case YES:
                    System.out.println(Control.getFromDatabase(super.getLocationIDString(), "DownTheRabbitHole"));
                    tumblingGraphics();
                    return LocationName.TEARS;
                case NO:
                    System.out.println(Control.getFromDatabase(super.getLocationIDString(), "EndString"));
                    return LocationName.END;
            }
        }
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
                System.out.println("     Orange Marmalade   *             *");
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
