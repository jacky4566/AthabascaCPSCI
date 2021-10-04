/**
* title: Action.java
* description: Create an Action object that holds the result of the intended user action
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: Action
*   Description: 
*    Gets user information from the terminal and converts that to a useable enum with assigned actions. 
*
*   Constructors:
*    Action(String)
*     Given an input string. This constructor will decode the input for our assigned input enums. 
*     method also splits our input into appropriate arguments for later processing.
* 
*   Methods:
*    ActionType getActionType():
*     Returns the actionType Enum of this action.
*
*    String getFirstArg():
*     Returns the first argument as assigned in the constructor.
*
*    String getSecondArg():
*     Returns the second argument as assigned in the constructor.
*
*   Instance Variables:
*    private ActionType thisAction:     Contains the enum ActionType of this Action. Default is INVALID.
*    private String firstArg:           Contains a string of the first argument, if present.
*    private String secondArg:          Contains a string of all text after the first argument, if present. 
*
*/

public class Action {
    private ActionType thisAction = ActionType.INVALID;
    private String firstArg;
    private String secondArg;

    public Action(String userInput) {
        userInput = userInput.toLowerCase(); // change input to lowercase to simplifer switch
        String[] inputArgs = userInput.split(" ", 3);// split input by spaces. Maximum 3 Strings.
        if (inputArgs.length > 2) { // If we have 3 arguments
            secondArg = inputArgs[2];
            firstArg = inputArgs[1];
        } else if (inputArgs.length > 1) {// if we have 2 arguments
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
