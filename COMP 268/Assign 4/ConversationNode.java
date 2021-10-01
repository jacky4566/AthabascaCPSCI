public class ConversationNode {
    private String databaseFile;
    private boolean nodeComplete = false;
    private String query;
    private String answer;
    private String responseCorrect;
    private String responseWrong;
    private String responseHint;
    private int guessCount = 0; //keeps track of the number of guesses

    public ConversationNode(String charName) {
        this.databaseFile = "CHAR" + charName + ".txt";
        this.query = Control.getFromDatabase(this.databaseFile, "query");
        this.answer = Control.getFromDatabase(this.databaseFile, "answer");
        this.responseCorrect = Control.getFromDatabase(this.databaseFile, "responseCorrect");
        this.responseWrong = Control.getFromDatabase(this.databaseFile, "responseWrong");
        this.responseHint = Control.getFromDatabase(this.databaseFile, "responseHint");
    }

    // Setters

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
