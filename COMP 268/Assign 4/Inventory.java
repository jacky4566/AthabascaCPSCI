import java.util.*;

public class Inventory {
    private LinkedList<Item> itemList = new LinkedList<>();

    public Inventory(){
        //does nothing
    }

    public int countInventory(){
        return itemList.size();
    }

    public void addItem(Item newItem) {
        itemList.add(newItem);
    }

    public boolean consumeItem(String name){
        for (Item someItem : itemList) {
            if (someItem.getName().equalsIgnoreCase(name)){
                itemList.remove(someItem);
                return true;
            }
        }
        return false;
    }

    public void printInventory() {
        System.out.println("Items in your Inventory:");
        for (Item someItem : itemList) {
            System.out.println(" - " + someItem.getName() + ": " + someItem.getDesciption());
        }
    }
}
