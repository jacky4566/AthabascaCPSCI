import java.util.*;

public class Inventory {
    private LinkedList<Item> itemList = new LinkedList<>();

    public void addItem(Item newItem) {
        itemList.add(newItem);
    }

    public void printInventory() {
        for (Item someItem : itemList) {
            System.out.println(someItem.getName());
        }
    }
}
