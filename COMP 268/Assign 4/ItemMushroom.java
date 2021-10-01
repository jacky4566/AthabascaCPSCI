import java.util.Random;

public class ItemMushroom extends Item {

    public ItemMushroom(){
        super(ItemList.MUSHROOM);
    }

    @Override
    public boolean action() {
        //A fun mini game
        System.out.println(
                "Alice begins to grow. OR maybe to shrink? \nYou must help Alice get to the right size. She wants to be exactly 6 feet tall!\nPress S to shrink and G to grow");
        int size = 4;
        System.out.println("Alice is now " + size + " feet tall.");
        while (size != 6) {
            char input = Control.getChar();
            Random r = new Random();
            switch (input) {
                case 'g':
                    size += r.nextInt(5);
                    System.out.println("Alice is now " + size + " feet tall.");
                    break;
                case 's':
                    size -= r.nextInt(5);
                    if (size <= 0) {
                        size = 1;
                    }
                    System.out.println("Alice is now " + size + " feet tall.");
                    break;
                case 'q':
                    System.out.println("Minigame Quit");
                    break;
                default:
                    break;
            }
            if (size == 6){
                System.out.println("Success! Alice feels much better");
                break;
            }
        }
        return true;
    }
}
