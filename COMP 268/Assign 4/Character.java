public class Character {
    private String name = "Not a Name";
    private Inventory inventory =  new Inventory();
    private int size = 6; //as measured by Alice in feet

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

    public void setSize(int size){
        this.size = size;
    }

    // SETTERS
    public int getSize(){
        return this.size;
    }

}
