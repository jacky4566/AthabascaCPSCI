public class Action {

    private ActionType thisAction = ActionType.INVALID;
    private String firstArg;
    private String secondArg;

    public Action(String userInput) {
        userInput = userInput.toLowerCase();
        String[] inputArgs = userInput.split(" ");
        if (inputArgs.length > 2) {
            secondArg = inputArgs[2];
            firstArg = inputArgs[1];
        }else if (inputArgs.length > 1) {
            firstArg = inputArgs[1];
        }
        if (inputArgs[0].startsWith("q") || inputArgs[0].startsWith("quit")) {
            thisAction = ActionType.QUIT;
        } else if (inputArgs[0].startsWith("i") || inputArgs[0].startsWith("inventory")) {
            thisAction = ActionType.INVENTORY;
        } else if (inputArgs[0].startsWith("u") || inputArgs[0].startsWith("use")) {
            thisAction = ActionType.USE;
        } else if (inputArgs[0].startsWith("l") || inputArgs[0].startsWith("look")) {
            thisAction = ActionType.LOOKAROUND;
        } else if (inputArgs[0].startsWith("t") || inputArgs[0].startsWith("take")) {
            thisAction = ActionType.TAKE;
        } else if (inputArgs[0].startsWith("s") || inputArgs[0].startsWith("speak")) {
            thisAction = ActionType.SPEAK;
        } else if (inputArgs[0].startsWith("h") || inputArgs[0].startsWith("help")) {
            thisAction = ActionType.HELP;
        } else if (inputArgs[0].startsWith("e") || inputArgs[0].startsWith("enter") || inputArgs[0].startsWith("exit")) {
            thisAction = ActionType.EXIT;
        } else if (inputArgs[0].startsWith("y") || inputArgs[0].startsWith("yes")) {
            thisAction = ActionType.YES;
        } else if (inputArgs[0].startsWith("n") || inputArgs[0].startsWith("no")) {
            thisAction = ActionType.NO;
        } else if (inputArgs[0].startsWith("k") || inputArgs[0].startsWith("keep")) {
            thisAction = ActionType.KEEP;
        } 
    }

    public ActionType getActionType() {
        return this.thisAction;
    }

    public String getFirstArg() {
        return firstArg;
    }

    public String getSecondArg() {
        return secondArg;
    }

}
