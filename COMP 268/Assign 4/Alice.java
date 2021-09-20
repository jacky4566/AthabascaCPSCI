public class Alice {
    private Inventory inventory = new Inventory(); //Stores Character Inventory
    private int size = 6; // as measured by Alice in feet

    // Constructor
    public Alice() {
        //No constructor needed
    }

    // Functions
    public void addInventory(Item newItem) {
        inventory.addItem(newItem);
    }

    // Getters
    public Inventory getInventory() {
        return this.inventory;
    }

    // Setters
    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
