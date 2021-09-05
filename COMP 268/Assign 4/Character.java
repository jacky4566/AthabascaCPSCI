public class Character {
    private double _health = 100;
    private String _name = "Not a Name";
    private int _location = 0;
    private Inventory _inventory;

    public Character(String myName){
        this._name = myName;
    }

    //GETTERS
    public double getHealth(){
        return this._health;
    }

    public String getName(){
        return this._name;
    }

    public int getLocation(){
        return this._location;
    }

    //SETTERS
    public void setHealth(double newHealth){
        this._health = newHealth;
    }

    public void setLocation(int newLocation){
        this._location = newLocation;
    }
}
