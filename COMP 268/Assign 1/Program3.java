/*
Jackson Wiebe
3519635
11/07/2021
*/

/* Program Overview
Create a Java program that will generate a table of temperature conversions.
*/

/* Test Plan
Does the output from the program match the output provided in the assignment?
Use notepad++ to check for perfect character match
No external inputs are needed.
*/

/* Classes
None Used.
*/

public class Program3 {
    public static final String NUMBERFORMAT = "%04.3f"; // Used to format output

    static double fTOc(double input) { // Converts Float Fahrenheit to Celsius
        return ((input - 32.0) * (5.0 / 9.0));
    }

    static double cTOf(double input) { // Converts Float Celsius to Fahrenheit
        return ((input * (9.0 / 5.0)) + 32.0);
    }

    public static void main(String[] args) {
        // Print the top header fixed widths
        System.out.println("            Temperature Conversion Tables");
        System.out.println(); // Print a new line
        System.out.println("       Temperature      |       Temperature");
        System.out.println("        (degrees)       |        (degrees)");
        System.out.println("      F           C     |      C           F");
        // start new loop until we reach 455
        for (double x = -40; x <= 455; x += 5) {
            System.out.println(String.format("%10s %11s  | %10s %11s", String.format(NUMBERFORMAT, x),
                    String.format(NUMBERFORMAT, fTOc(x)), String.format(NUMBERFORMAT, x),
                    String.format(NUMBERFORMAT, cTOf(x))));
        }
    }
}