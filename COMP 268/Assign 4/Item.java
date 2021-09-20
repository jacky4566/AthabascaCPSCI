public class Item {
    private String name;
    private String description;

    public Item(String myName){
        this.name = myName;
        this.description = Control.getFromDatabase(Constants.ITEMDATABASE, myName);
    }

    //GETTER
    public String getName(){
        return this.name;
    }

    public String getDesciption(){
        return this.description;
    }
}
