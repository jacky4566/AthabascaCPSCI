import java.util.Calendar;

public class ItemWatch extends Item {

    public ItemWatch(){
        super(ItemList.WATCH, false);
    }

    @Override
    public boolean action(){ //Has a special action that prints the day of the month
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        System.out.println("Item Used: " + this.getName());
        System.out.println(this.getActionText());
        System.out.println("The Clock reads: " + dayOfMonth);
        return isConsumable;
    }
}
