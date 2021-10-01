import java.util.ArrayList;

public class RoomGarden extends Location {

    private ArrayList<Item> safe = new ArrayList<>();

    public RoomGarden(Alice main) {
        super(main, LocationList.GARDEN);
        super.addExit(new Door("Hall", LocationList.CORRIDOR));
        super.addExit(new Door("Forest", LocationList.MUSHROOM, true));
        super.addCharacter(new Character("Cat", CharacterList.CAT, "Forest"));
        safe.add(new ItemLeaf());
    }

    @Override
    public void takeItem(String itemName) {
        for (int i = 0; i < safe.size(); i++) {
            if (safe.get(i).getName().equalsIgnoreCase(itemName)) {
                super.getMainChar().getInventory().addItem(safe.get(i));
                safe.remove(i);
                System.out.println("Item Taken: " + itemName);
                return;
            }
        }
        System.out.println("Item not Found: " + itemName);
    }

    @Override
    public void keepItem(String itemName) {
        Inventory aliceInventory = super.getMainChar().getInventory();
        for (int i = 0; i < aliceInventory.countInventory(); i++) {
            if (aliceInventory.hasItem(itemName)) {
                safe.add(aliceInventory.getItem(itemName));
                aliceInventory.destroyItem(itemName);
                System.out.println("Item put in safe: " + itemName);
                return;
            }
        }
        System.out.println("Item not Found: " + itemName);
    }

    @Override
    public void listItems() {
        System.out.println("Items in your Safe:");
        if (safe.isEmpty()) {
            System.out.println("No items Found");
        } else {
            for (Item model : safe) { // print out all the items in this room
                System.out.println(" - " + model.getName() + ": " + model.getDesciption());
            }
        }
    }

}
