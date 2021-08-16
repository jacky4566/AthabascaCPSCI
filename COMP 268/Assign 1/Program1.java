/*
Jackson Wiebe
3519635
11/07/2021
*/

/* Program Overview
Create a Java program that will generate a multiplication table identical to that given in the assignment.
Program should print a multiplication table of 12x12 size.
*/

/* Test Plan
Does the output from the program match the output provided in the assignment?
Use notepad++ to check for perfect character match
No external inputs are needed.
*/

/* Classes
None Used.
*/

public class Program1 {

    static void boxPrinter(int valueToPrint) { // Function to format and print outputs
        if (valueToPrint < 0) {
            System.out.print("negatives not handled" + valueToPrint); // catch negative values
        } else if (valueToPrint == 0) {
            System.out.print("     |"); // We just want to print a blank box here
        } else if (valueToPrint < 10) {
            System.out.print("   " + valueToPrint + " |"); // Digit is 1 character long, add whitespace and divider
        } else if (valueToPrint < 100) {
            System.out.print("  " + valueToPrint + " |"); // Digit is 2 characters long, add whitespace and divider
        } else if (valueToPrint < 1000) {
            System.out.print(" " + valueToPrint + " |"); // Digit is 3 characters long, add whitespace and divider
        } else {
            System.out.print("case not handled" + valueToPrint); // catch erros
        }
    }

    public static void main(String[] args) {
        // Print the top header
        // Start a loop that executes 13 times
        for (var c = 0; c <= 12; c++) {
            boxPrinter(c);
        }
        System.out.println(); // Print a new line

        // start a loop that executes 12 times for each Row 1-12
        for (var r = 1; r <= 12; r++) {
            // print the index
            boxPrinter(r);
            // start a loop that executes 12 times for each column
            for (var c = 1; c <= 12; c++) {
                // print the value of that box, Row multiplied by Column
                boxPrinter(r * c);
            }
            System.out.println(); // Print a new line
        }
    }
}