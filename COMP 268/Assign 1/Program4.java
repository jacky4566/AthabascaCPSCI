/*
Jackson Wiebe
3519635
11/07/2021
*/

/* Program Overview
Create a Java help program to educate the user on a varity of basic commands
*/

/* Test Plan

User input '1' : Output: Program should print data in switch case 1
User input '2' : Output: Program should print data in switch case 2
User input '3' : Output: Program should print data in switch case 3
User input '4' : Output: Program should print data in switch case 4
User input '5' : Output: Program should print data in switch case 5
User input 'x' or 'X' : Output: Program should quit
User input any other character: Output: Program will notifiy of invalid input

*/

/* Classes
None Used.
*/

import textio.TextIO;

public class Program4 {

    public static void main(String[] args) {
        char userInput = '0'; // setup our variable for user input and program state control
        while (!(userInput == 'x' || userInput == 'X')) { // loop forever until user input x
            // Print header
            System.out.println("Java Help Menu");
            System.out.println("Select Help on:");
            System.out.println("1. If");
            System.out.println("2. Switch");
            System.out.println("3. For");
            System.out.println("4. While");
            System.out.println("5. Do-while");
            System.out.println("x. Exit");
            System.out.println(); // add some white space for easy reading1

            // Get the next char from user input, disgard remaining input.
            System.out.print("Selection: ");
            userInput = TextIO.getlnChar();
            System.out.println(); // add some white space for easy reading

            switch (userInput) { // Use a switch case to output the data.
                case '1':
                    System.out.println("Define \"if\":");
                    System.out.println("Syntax: if (condition){ code; }");
                    System.out.println("Only executes code if condition evaluates true");
                    System.out.println("Powerful tool for simple decision making and essential process flow control");
                    break;
                case '2':
                    System.out.println("Define \"switch\":");
                    System.out.println("Syntax: switch(expression) { case x: code; break; }");
                    System.out.println("Use the switch statement to select one of many code blocks to be executed.");
                    break;
                case '3':
                    System.out.println("Define \"for\":");
                    System.out.println("Syntax: for ( setup ; end condition ; each loop) {code;}");
                    System.out.println("Used to loop through a block of code x number of times");
                    break;
                case '4':
                    System.out.println("Define \"while\":");
                    System.out.println("Syntax: while (condition) { code; }");
                    System.out
                            .println("Loops can execute a block of code as long as a specified condition is reached.");
                    break;
                case '5':
                    System.out.println("Define \"do-while\":");
                    System.out.println("Syntax: do{ code; } while(condition);");
                    System.out.println(
                            "Similar to while, with the addtion that the loop is excuted before the condition is cheaked");
                    break;
                case 'x':
                case 'X': // We can stack cases here to handle multiple inputs
                    System.out.println("Thank you. Good bye.");
                    break;
                default:
                    // default case handles any case not shown above
                    System.out.println("invalid input");
            }
            System.out.println(); // add some white space for easy reading
        }
    }
}