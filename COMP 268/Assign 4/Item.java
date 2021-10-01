public class Item {
    boolean isConsumable = true;
    private ItemList itemID;
    private String databaseFile;
    private String name;
    private String description;
    private String actionText;

    public Item(){

    }

    public Item(ItemList itemID){
        this.itemID = itemID;
        this.name = itemID.toString();
        this.databaseFile = "ITEM" + itemID + ".txt";
        this.description = Control.getFromDatabase(databaseFile, "DESCRIPTION");
        this.actionText = Control.getFromDatabase(databaseFile, "ACTION");
    }

    public Item(ItemList itemID, boolean consumable){
        this.isConsumable = consumable;
        this.itemID = itemID;
        this.name = itemID.toString();
        this.databaseFile = "ITEM" + itemID + ".txt";
        this.description = Control.getFromDatabase(databaseFile, "DESCRIPTION");
        this.actionText = Control.getFromDatabase(databaseFile, "ACTION");
    }

    //GETTER
    public ItemList getID(){
        return itemID;
    }

    public String getName(){
        return this.name.substring(0, 1).toUpperCase() + this.name.substring(1).toLowerCase();
    }

    public String getDesciption(){
        return this.description;
    }

    public String getActionText(){
        return this.actionText;
    }

    public boolean action(){
        //can be overridden
        System.out.println("Item Used: " + this.getName());
        System.out.println(this.getActionText());
        return isConsumable;
    }
}
