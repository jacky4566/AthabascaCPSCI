public class ItemTea extends Item {

    public ItemTea(){
        super(ItemList.TEA);
    }

    @Override
    public boolean action(){
        System.out.println("Item Used: " + this.getName());
        System.out.println(this.getActionText());
        Control.quit();
        return true;
    }
}
