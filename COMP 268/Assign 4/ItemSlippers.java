public class ItemSlippers extends Item {

    public ItemSlippers(){
        super(ItemList.SLIPPERS, false);
    }

    @Override
    public boolean action(){
        System.out.println("Item Used: " + this.getName());
        System.out.println(this.getActionText());
        return false;
    }
}
