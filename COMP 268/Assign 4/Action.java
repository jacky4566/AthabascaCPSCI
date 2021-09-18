public class Action {

    private ActionType thisAction = ActionType.INVALID;
    private String secondaryArg;

    public Action() {

    }

    public Action(String userInput) {
        userInput = userInput.toLowerCase();
        String[] inputArgs = userInput.split(" ");
        if (inputArgs.length > 1) {
            secondaryArg = inputArgs[1];
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
        } else if (inputArgs[0].startsWith("h") || inputArgs[0].startsWith("help")) {
            thisAction = ActionType.HELP;
        } else if (inputArgs[0].startsWith("e") || inputArgs[0].startsWith("enter") || inputArgs[0].startsWith("exit")) {
            thisAction = ActionType.EXIT;
        } else if (inputArgs[0].startsWith("y") || inputArgs[0].startsWith("yes")) {
            thisAction = ActionType.YES;
        } else if (inputArgs[0].startsWith("n") || inputArgs[0].startsWith("no")) {
            thisAction = ActionType.NO;
        }
    }

    public ActionType getActionType() {
        return this.thisAction;
    }

    public String getSecondaryArg() {
        return secondaryArg;
    }

}
