/**
* title: Problem1.java
* description: Reads a file and counts the number of whitespace seperates words.
* date: 21/08/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Problem1
*
* Purpose and Description:
* Reads a file and counts the number of whitespace seperates words.
* Program expects 1 argument as a basic text file for parsing.
*
* Compiling and running instructions
* Compile:  javac Problem1.java
* Run:      java Problem1   *.txt
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

        Problem 1: Count words in file.
        Checks passed. Reading taget file: excerpt.txt
        Number of words: 342

* Bad data cases:

*   User inputs no text file argument: no argument given

        Problem 1: Count words in file.
        Incorrect number of argument(s), Provide a single file name. *.txt

*   User inputs file that is not a text file: text.garbage given

        Problem 1: Count words in file.
        Incorrect file type, expected *.txt

*/

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class Problem1 {
        public static void main(String[] args) {        // Main program
                                                        //Print some human text
                System.out.println("Problem 1: Count words in file.");
                if (args.length != 1) {                 //check if only one argument exists
                        System.out.println("Incorrect number of argument(s), Provide a single file name. *.txt");
                } else {                                //else we can prceed.
                        if (!args[0].endsWith(".txt")) { //check if given file ends with .txt extension
                                System.out.println("Incorrect file type, expected *.txt");
                        } else {                
                                System.out.println("Checks passed. Reading taget file: " + args[0]);
                                File targetfile = new File(args[0]);                                    // Creation of File Descriptor for input file
                                try (Scanner sc = new Scanner(new FileInputStream(targetfile))) {       //create a new scanner. must be caught with some exceptions
                                        int count = 0;                                                  //counter
                                        while (sc.hasNext()) {                                          //while the stream has another word
                                                sc.next();                                              //get next word
                                                count++;                                                //increament counter
                                        }
                                        System.out.println("Number of words: " + count);
                                } catch (java.io.FileNotFoundException e) {                             //expected most common exception. file not found
                                        System.err.println("File Not Found ");
                                } catch (Exception e) {                                                 //catch all other exceptions
                                        System.out.println("Error reading file: " + e);
                                        e.printStackTrace();                                            //print the full program stack
                                }
                        }
                }
        }
}