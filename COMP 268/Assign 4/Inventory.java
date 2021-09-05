import java.util.*;

public class Inventory {
    private LinkedList<Item> itemList = new LinkedList<Item>();

    public void addItem(Item newItem){
        itemList.add(newItem);
    }
}
