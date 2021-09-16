public class Action {

    private ActionType thisAction;
    private String secondaryArg;

    public Action(){

    }

    public Action(String userInput) {
        String[] inputArgs = userInput.split(" ");
        if (inputArgs.length > 1){
            secondaryArg = inputArgs[1];
        }
        if (inputArgs[0].contains("q") || inputArgs[0].contains("Q") || inputArgs[0].contains("quit")) {
            thisAction = ActionType.QUIT;
        } else if (inputArgs[0].contains("i") || inputArgs[0].contains("I") || inputArgs[0].contains("inventory")) {
            thisAction = ActionType.INVENTORY;
        } else if (inputArgs[0].contains("l") || inputArgs[0].contains("L") || inputArgs[0].contains("look")) {
            thisAction = ActionType.LOOKAROUND;
        } else if (inputArgs[0].contains("t") || inputArgs[0].contains("T") || inputArgs[0].contains("take")) {
            thisAction = ActionType.TAKE;
        } else if (inputArgs[0].contains("h") || inputArgs[0].contains("H") || inputArgs[0].contains("help")) {
            thisAction = ActionType.HELP;
        }else if (inputArgs[0].contains("e") || inputArgs[0].contains("E") || inputArgs[0].contains("enter") || inputArgs[0].contains("exit")) {
            thisAction = ActionType.ENTER;
        } else if (inputArgs[0].contains("y") || inputArgs[0].contains("Y") || inputArgs[0].contains("yes")) {
            thisAction = ActionType.YES;
        } else if (inputArgs[0].contains("n") || inputArgs[0].contains("N") || inputArgs[0].contains("no")) {
            thisAction = ActionType.NO;
        }
    }

    public ActionType getActionType(){
        return this.thisAction;
    }

    public String getSecondaryArg(){
        return secondaryArg;
    }

}
