public class Item {
    private ItemList itemID;
    private String databaseFile;
    private String name;
    private String description;
    private String actionText;

    public Item(){

    }

    public Item(ItemList itemID){
        this.itemID = itemID;
        this.name = this.itemID.toString();
        this.databaseFile = itemID + ".txt";
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

    public void action(){
        //to be overridden
    }
}
