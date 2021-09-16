import java.util.*;

public class Inventory {
    private LinkedList<Item> itemList = new LinkedList<>();

    public Inventory(){
        
    }

    public void addItem(Item newItem) {
        itemList.add(newItem);
    }

    public void printInventory() {
        System.out.println("Items in your Inventory:");
        for (Item someItem : itemList) {
            System.out.println(" - " + someItem.getName());
        }
    }
}
