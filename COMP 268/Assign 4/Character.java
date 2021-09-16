public class Character {
    private String name = "Not a Name";
    private Inventory inventory =  new Inventory();

    public Character(String myName) {
        this.name = myName;
    }

    // GETTERS
    public String getName() {
        return this.name;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void addInventory(Item newItem) {
        inventory.addItem(newItem);
    }

    // SETTERS

}
