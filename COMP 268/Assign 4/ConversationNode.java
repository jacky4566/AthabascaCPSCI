/**
* title: ConversationNode.java
* description: Create a converstation node, contians a question and answer for user to interact with
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: ConversationNode
*   Description: 
*    A class for creating converstaion between the user and target character. Contains string values and status of this conversation node.
*
*   Constructors:
*    ConversationNode(String)
*     Takes a common name for the character and looks up all the relavent String information from a database of the same name.
* 
*   Methods:
*    String getConversation():
*     Approach a character without argument. Prints out the question or statement of this character.
*
*    String getConversation(String):
*     Approach a character with argument. Checks if answer is correct and prints out resulting statement of this character.
*
*    int getAttempts():
*     Returns the number of attempts on this puzzle.
*
*    boolean getComplete():
*     Returns the status of this node. 
*
*   Instance Variables:
*    private boolean nodeComplete:      Boolean status if this node is complete
*    private String query:              String of this characters query, fetched from txt file of same name
*    private String answer:             String of this characters correct answer, fetched from txt file of same name
*    private String responseCorrect:    String of this characters response when correct answer given, fetched from txt file of same name
*    private String responseWrong:      String of this characters response when incorrect answer given, fetched from txt file of same name
*    private String responseHint:       String of hint given after 3 failed attempts, fetched from txt file of same name
*    private int guessCount:            Counter for number of attempts on this puzzle
*
*/

public class ConversationNode {
    private boolean nodeComplete = false;
    private String query;
    private String answer;
    private String responseCorrect;
    private String responseWrong;
    private String responseHint;
    private int guessCount = 0; //keeps track of the number of guesses

    public ConversationNode(String charName) {
        String databaseFile = "CHAR" + charName + ".txt";
        this.query = Control.getFromDatabase(databaseFile, "query");
        this.answer = Control.getFromDatabase(databaseFile, "answer");
        this.responseCorrect = Control.getFromDatabase(databaseFile, "responseCorrect");
        this.responseWrong = Control.getFromDatabase(databaseFile, "responseWrong");
        this.responseHint = Control.getFromDatabase(databaseFile, "responseHint");
    }

    // Getters
    public String getConversation() {
        // you approach a character without argument
        if (nodeComplete) { // If this conversation node is complete
            return responseCorrect; // else return the final retort
        } else {
            return query; // else return the initial question
        }
    }

    public String getConversation(String inputAnswer) {
        if (nodeComplete) { // If this conversation node is complete
            return responseCorrect; // else return the final retort
        } else {
            guessCount++;
            if (inputAnswer.toLowerCase().contains(this.answer)){
                nodeComplete = true;
                return responseCorrect;
            }else{
                if (guessCount >= 3){
                    return responseHint; // else return the initial question
                }else{
                    return responseWrong; // else return the initial question
                }
            }
        }
    }

    public int getAttempts(){
        return guessCount;
    }

    public boolean getComplete(){
        return nodeComplete;
    }
}
