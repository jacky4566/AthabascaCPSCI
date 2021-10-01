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

    public boolean consumeItem(String name){ //Alice used the item. Perform its action and destroy object
        for (Item someItem : itemList) {
            if (someItem.getName().equalsIgnoreCase(name)){
                if (someItem.action()){
                    itemList.remove(someItem);
                }
                return true;
            }
        }
        return false;
    }

    public boolean hasItem(String name){ //Does this inventory contain the target item?
        for (Item someItem : itemList) {
            if (someItem.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public Item getItem(String name){ //retrieve item from inventory
        for (Item someItem : itemList) {
            if (someItem.getName().equalsIgnoreCase(name)){
                return someItem;
            }
        }
        return null;
    }

    public boolean destroyItem(String name){ //destory item without action
        for (Item someItem : itemList) {
            if (someItem.getName().equalsIgnoreCase(name)){
                itemList.remove(someItem);
                return true;
            }
        }
        return false;
    }


    public void printInventory() { //print all items in inventory
        System.out.println("Items in your Inventory:");
        for (Item someItem : itemList) {
            System.out.println(" - " + someItem.getName() + ": " + someItem.getDesciption());
        }
    }
}
