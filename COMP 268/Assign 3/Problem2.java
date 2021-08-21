/**
* title: Problem2.java
* description: Reads a file and prints it back out line by line to the user terminal
* date: 21/08/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Problem2
*
* Purpose and Description:
* Reads a file and prints it back out line by line to the user terminal
* User must enter a target file via command line.
* User is expected to press <enter> between each line print.
*
* Compiling and running instructions
* Compile:  javac Problem2.java
* Run:      java Problem2
*
* Classes:
*
*/

/**
* Test Plan
*/
/**
* Run the application.
* EXPECTED:
* Static test:
*  No static output is expect
* ACTUAL:
*
* Good data cases:
*   User inputs a good text file with some or no words: excerpt.txt shown below

    Problem 2: Line Printer
    Press enter for each new line.
    Please input target file: excerpt.txt
    Checks passed. Reading taget file: excerpt.txt
    Line#    1 contains text: There was nothing so VERY remarkable in that; nor did Alice 
    Line#    2 contains text: think it so VERY much out of the way to hear the Rabbit say to 
    ...
    Line#   30 contains text: fell past it.
    Total Line count of file: excerpt.txt : 30

* Bad data cases:

*   User inputs no text file argument: no input given

        Problem 2: Line Printer
        Press enter for each new line.
        Please input target file:
        Incorrect file type, expected *.txt

*   User inputs file that is not a text file: text.garbage given

        Problem 2: Line Printer
        Press enter for each new line.
        Please input target file: text.garbage
        Incorrect file type, expected *.txt

*/

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import textio.TextIO;

public class Problem2 {
    public static void main(String[] args) { // Main program
                                             // Print some human text
        System.out.println("Problem 2: Line Printer");
        System.out.println("Press enter for each new line.");
        System.out.print("Please input target file: ");
        String userInput = TextIO.getlnString(); //Get the next entered line from the user
        if (!userInput.endsWith(".txt")) { // check if given file ends with .txt extension
            System.out.println("Incorrect file type, expected *.txt");
        } else {
            System.out.println("Checks passed. Reading taget file: " + userInput);
            File targetfile = new File(userInput); // Creation of File Descriptor for input file
            try (Scanner sc = new Scanner(new FileInputStream(targetfile))) { // create a new scanner. must be caught with some exceptions
                int lineCount = 0; // counter
                while (sc.hasNext()) { // while the stream has more data
                    String aLine = sc.nextLine(); // get next line
                    lineCount++; // increament counter
                    System.out.print("Line# " + String.format("%4d", lineCount) + " contains text: " + aLine); //Print the text line
                    TextIO.getln();
                }
                System.out.print("Total Line count of file: " + targetfile + " : " + lineCount);
            } catch (java.io.FileNotFoundException e) { // expected most common exception. file not found
                System.err.println("File Not Found ");
            } catch (Exception e) { // catch all other exceptions
                System.out.println("Error reading file: " + e);
                e.printStackTrace(); // print the full program stack
            }
        }
    }
}