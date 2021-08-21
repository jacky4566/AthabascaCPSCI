/**
* title: TextFileReaderDemo.java
* description: A class to demo the functionality of TextFileReader.java
* date: 21/08/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* TextFileReaderDemo
*
* Purpose and Description:
* Creates a new instance of TextFileReader with a file from the command line. 
* Inputs that file to the class and executes the two print functions of TextFileReader class.
*
* Compiling and running instructions
* Compile:  javac TextFileReaderDemo.java
* Run:      java TextFileReaderDemo
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

    Problem 3: Use classes to read files
    There was nothing so VERY remarkable in that; nor did Alice 
    ...
    somebody, so managed to put it into one of the cupboards as she
    fell past it.

    line    1: There was nothing so VERY remarkable in that; nor did Alice 
    ...
    line   29: somebody, so managed to put it into one of the cupboards as she
    line   30: fell past it.
    Done

* Bad data cases:

*   User inputs no text file argument: no input given

        Problem 3: Use classes to read files
        Incorrect number of argument(s), Provide a single file name. *.txt
        Done

*   User inputs file that is not a text file: text.garbage given

        Problem 3: Use classes to read files
        Incorrect file type, expected *.txt
        Done

*/

public class TextFileReaderDemo {
    public static void main(String[] args) { //start main class
        //print some human text
        System.out.println("Problem 3: Use classes to read files"); 
        if (args.length != 1) { // check if only one argument exists
            System.out.println("Incorrect number of argument(s), Provide a single file name. *.txt");
        } else { // else we can prceed.
            if (!args[0].endsWith(".txt")) { // check if given file ends with .txt extension
                System.out.println("Incorrect file type, expected *.txt");
            } else {
                TextFileReader linerider = new TextFileReader(args[0]); //feed file into reader class
                System.out.println(linerider.contents()); //ask reader class to return the contents and print it
                linerider.display(); //ask reader class to print the contents
            }
        }
        System.out.println("Done");
    }
}
