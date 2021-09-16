public class Tears extends Location {

    public Tears(Character main) {
        super.setLocationID(LocationName.TEARS);
        super.setMainChar(main);
        super.addItem(new Item("Golden Key", "A Small gold Key"));
    }

    @Override
    public LocationName enter() {
        if (super.getFirstVisit()) {
            System.out.println(Control.getFromDatabase(super.getLocationIDString(), "EntryString"));
        } else {
            System.out.println(Control.getFromDatabase(super.getLocationIDString(), "ReturnString"));
        }
        while (true) {
            System.out.println("What would you like to do?");
            Action newAction = Control.getAction();
            switch (newAction.getActionType()) {
                case ENTER:
                    return LocationName.END;
                case TALK:
                    System.out.println("No one to talk too");
                    break;
                case TAKE:
                    if (super.takeItem(newAction.getSecondaryArg())) {
                        System.out.println("You take the item: " + newAction.getSecondaryArg());
                    } else {
                        System.out.println("No item called: " + newAction.getSecondaryArg());
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
                case NO:
                   break;
            }
        }
    }
}
