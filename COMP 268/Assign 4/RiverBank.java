public class RiverBank extends Location {

    public RiverBank(Character main) {
        super.setEntryString("First time on the riverbank");
        super.setLocationID(LocationName.RIVERBANK);
        super.setMain(main);
    }

    @Override
    public LocationName run() {
        // Put the main stuffs here
        ActionType input = ActionType.UNDEFINED;
        while (!(input == ActionType.YES || input == ActionType.NO)) {
            System.out.print("You see a white rabbit. Follow him? ");
            input = Control.getAction();
        }
        if (input == ActionType.YES) {
            return LocationName.TEARS;
        } else {
            System.out.println("You wake from a restful nap.");
            return LocationName.END;
        }
    }
}
