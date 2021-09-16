public class Tears extends Location {

    public Tears(Character main) {
        super.setLocationID(LocationName.TEARS);
        super.setMainChar(main);
        super.addItem(new Item("Golden Key","A Small gold Key"));
    }

    @Override
    public LocationName enter() {
        if (super.getFirstVisit()){
            System.out.println(Control.getFromDatabase(super.getLocationIDString(), "EntryString"));
        }else {
            System.out.println(Control.getFromDatabase(super.getLocationIDString(), "ReturnString"));
        }

        return LocationName.END;
    }
}
