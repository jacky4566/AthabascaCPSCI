/**
* title: Character.java
* description: Create an Character class for NPC type characters
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: Character
*   Description: 
*    Contains a character for the user to interact with. Conversation nodes build conversation for the character. 
*    Character may also block a door given by a string name
*
*   Constructors:
*    Character(String, CharacterList)
*     Builds the character from a given string name and character ID enum.
*
*   Character(String, CharacterList, String)
*    Builds the character from a given string name and character ID enum. Also contains a string name of a door they are guarding for unlocking by the user.
* 
*   Methods:
*    CharacterList getID():
*     Returns the CharacterList Enum of this action.
*
*    String getName():
*     Returns the string name of this character
*
*    ConversationNode getConversation():
*     Returns the next conversation node for this character.
*
*    String getDoorGuard():
*     Returns the door this character is guarding. if any. 
*
*   Instance Variables:
*    private CharacterList ID:          Contains the Enum ID of this character  
*    private String name:               Contains the string name of this character. Default value given 
*    private ConversationNode question: Contains any question strings the character might have.
*    private String doorGuard:          Contains the name of the door this character may be guarding.
*
*/

public class Character {
    private CharacterList ID;
    private String name = "Not a Name"; //Stores Character name
    private ConversationNode question;
    private String doorGuard; //does this character guard a door

    // Constructor
    public Character(String myName, CharacterList ID) {
        this.name = myName;
        this.ID = ID;
        this.question = new ConversationNode(myName);
    }

    public Character(String myName, CharacterList ID, String doorName) {
        this.name = myName;
        this.question = new ConversationNode(myName);
        this.doorGuard = doorName;
    }

    // Getters
    public CharacterList getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public ConversationNode getConversation(){
        return question;
    }

    public String getDoorGuard(){
        return doorGuard;
    }

    // Setters

}
