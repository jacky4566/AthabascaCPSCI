public class Character {
    private String name = "Not a Name"; //Stores Character name
    private Inventory inventory = new Inventory(); //Stores Character Inventory
    private ConversationNode question;
    private String doorGuard; //does this character guard a door

    // Constructor
    public Character(String myName) {
        this.name = myName;
        this.question = new ConversationNode(myName);
    }

    public Character(String myName, String doorName) {
        this.name = myName;
        this.question = new ConversationNode(myName);
        this.doorGuard = doorName;
    }

    // Functions
    public void addInventory(Item newItem) {
        inventory.addItem(newItem);
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public ConversationNode getConversation(){
        return question;
    }

    public String getDoorGuard(){
        return doorGuard;
    }

    // Setters

}
