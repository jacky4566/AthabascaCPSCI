public class Action {

    private Action(){
        // only static methods used
    }

    public static ActionType process(String userInput){
        ActionType returnvalue = ActionType.UNDEFINED;
        if (userInput.contains("q") || userInput.contains("Q") || userInput.contains("quit")) {
            System.out.println("Thanks for Playing");
            System.exit(0);
        } else if (userInput.contains("i") || userInput.contains("I") || userInput.contains("inventory")) {
            returnvalue = ActionType.INVENTORY;
            System.out.println("Inventory");
        } else if (userInput.contains("h") || userInput.contains("H") || userInput.contains("help")) {
            returnvalue = ActionType.HELP;
            System.out.println("Help");
        }else if (userInput.contains("y") || userInput.contains("Y") || userInput.contains("yes")) {
            returnvalue = ActionType.YES;
        }else if (userInput.contains("n") || userInput.contains("N") || userInput.contains("no")) {
            returnvalue = ActionType.NO;
        }
        return returnvalue;
    }
    
}
