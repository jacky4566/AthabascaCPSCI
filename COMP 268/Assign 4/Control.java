/**
* title: Contorl.java
* description: Processes user actions, fetches information, and prints special commands to the terminal
* date: 03/10/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Class: Control
*   Description: 
*    A static class for interacting with the user terminal and database files.
*
*   Constructors:
*    Control()
*     Class only contains static methods
* 
*   Methods:
*    Action getAction():
*     Returns a new object of Action from a string read from the user terminal
*
*    char getChar():
*     Returns next char from the user terminal
*
*    void printFile(String):
*     Locates, reads, and prints a target file to the console. File should be located in the same location as class files and contain only TXT information.
*
*    String getFromDatabase(String, String):
*     Gets a target string from a target database. 
*     First argument. Target file.
*     Second argument. Target key in file.
*     This function also replaces '\\n' with '\n' so as to keep new lines printing on terminal
*
*    void clearTerminal():
*     Prints 50 lines in an effort to clear the terminal
*
*    void quit():
*     Instantly ends the game with exit text
*
*    void delayPrinter(int):
*     Delays the process by 100ms. Used for printing graphics and forcing the user to wait. 
*
*   Instance Variables:
*    private CharacterList ID:          Contains the Enum ID of this character  
*    private String name:               Contains the string name of this character. Default value given 
*    private ConversationNode question: Contains any question strings the character might have.
*    private String doorGuard:          Contains the name of the door this character may be guarding.
*
*/

import java.io.*;
import java.util.Scanner;
import textio.TextIO;

class Control {
    private Control() {
        // only static methods used
    }

    static Action getAction() {
        return new Action(TextIO.getlnString());
    }

    static char getChar() {
        return TextIO.getlnChar();
    }

    static void printFile(String target) {//prints the contents of the target file
        try {
            File myFile = new File(target);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + target);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static String getFromDatabase(String dataSet, String key) { //Gets a target string from a data file which matches the key
        String returnValue = null;
        try {
            File myFile = new File(dataSet);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data != null) {// If not empty line
                    if (data.charAt(0) != '#') { // Ignore comment lines
                        String[] parts = { data.substring(0, data.indexOf(":")),
                                data.substring(data.indexOf(":") + 1, data.length()) };
                        parts[1] = parts[1].replace("\\n", "\n");
                        if (parts[0].equalsIgnoreCase(key)) {
                            returnValue = parts[1];
                        }
                    }
                }
            }
            if (returnValue == null) { // obj not found
                returnValue = "String: " + dataSet + ": " + key + " Not Found";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + dataSet);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return returnValue;
    }

    static void clearTerminal() { //prints a bunch of lines to clear the terminal
        for (int i = 0; i < 50; ++i)
            System.out.println();
    }

    static void quit() {//ends java runtime
        System.out.println("Thanks for Playing");
        System.exit(0);
    }

    static void delayPrinter(int timer) { //use for delay
        for (int i = 0; i < timer; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}