public class ConversationNode {
    private String databaseFile;
    private boolean nodeComplete = false;
    private String query;
    private String answer;
    private String responseCorrect;
    private String responseWrong;

    public ConversationNode(String charName) {
        this.databaseFile = charName + ".txt";
        this.query = Control.getFromDatabase(this.databaseFile, "query");
        this.answer = Control.getFromDatabase(this.databaseFile, "answer");
        this.responseCorrect = Control.getFromDatabase(this.databaseFile, "responseCorrect");
        this.responseWrong = Control.getFromDatabase(this.databaseFile, "responseWrong");
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
            if (inputAnswer.equalsIgnoreCase(answer)){
                nodeComplete = true;
                return responseCorrect;
            }else{
                return responseWrong; // else return the initial question
            }
        }
    }

    public boolean getComplete(){
        return nodeComplete;
    }
}
