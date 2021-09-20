import java.util.ArrayList;

public class Garden extends Location {

    private ArrayList<Item> safe = new ArrayList<>();

    public Garden(Alice main) {
        super.setLocationID(LocationName.GARDEN);
        super.setMainChar(main);
        super.addExit(new Door("Hall", LocationName.TEARS));
        super.addExit(new Door("Forest", LocationName.MUSHROOMS, true));
        super.addCharacter(new Character("Cat", "Forest"));
        safe.add(new Item("Leaf"));
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
            if (aliceInventory.itemExists(itemName)) {
                aliceInventory.consumeItem(itemName);
                safe.add(new Item(itemName));
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
