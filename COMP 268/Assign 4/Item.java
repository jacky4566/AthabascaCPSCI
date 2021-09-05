public class Item {
    private String _name;
    private String _description;

    public Item(String myName, String myDescription){
        _name = myName;
        _description = myDescription;
    }

    //GETTER
    public String getName(){
        return _name;
    }

    public String getDesciption(){
        return _description;
    }
}
