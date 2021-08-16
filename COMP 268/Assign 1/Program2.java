/*
Jackson Wiebe
3519635
11/07/2021
*/

/* Program Overview
Create a program that converts temperature from Fahrenheit to Celsius and vice versa.
*/

/* Test Plan
INPUT: 32 F
Expected OUPUT: 0 C

INPUT: 100 C
Expected OUPUT: 212 F

INPUT: 100 *Character not 'c' or 'f'*
Expected OUPUT: Units unknown
*/

/* Classes
None Used.
*/

import textio.TextIO;

public class Program2 {

    static double fTOc(double input) { // Converts double Fahrenheit to Celsius
        return ((input - 32.0) * (5.0 / 9.0));
    }

    static double cTOf(double input) { // Converts double Celsius to Fahrenheit
        return ((input * (9.0 / 5.0)) + 32.0);
    }

    static String outputFormatter(float input, String unitType) { // function to print output
        String returnString = "A temperature of "; // print precursor text
        returnString = returnString.concat(String.valueOf(input));
        if (unitType.equals("f") || unitType.equals("F")) { // if the input was Fahrenheit. we print Celsius
            returnString = returnString.concat(" degrees Fahrenheit is equivalent to ");
            returnString = returnString.concat(String.valueOf(fTOc(input)));
            returnString = returnString.concat(" degrees Celsius.");
        } else if (unitType.equals("c") || unitType.equals("C")) { // if the input was Celsius. we print Fahrenheit
            returnString = returnString.concat(" degrees Celsius is equivalent to ");
            returnString = returnString.concat(String.valueOf(cTOf(input)));
            returnString = returnString.concat(" degrees Fahrenheit.");
        }
        return returnString;
    }

    public static void main(String[] args) {
        // start a forever loop
        System.out.println("This program converts Temperatures from Fahrenheit to Celsius and vice versa.");
        boolean keepLooping = true;
        while (keepLooping) {
            System.out.print("Please enter your temperature: ");
            float inputValue = TextIO.getlnFloat();// Get input
            System.out.print("Please enter the units (F/C): ");
            String inputUnits = TextIO.getln(); // Get input
            System.out.println();// print whitespace

            // dummy check the units
            if (inputUnits.equals("F") || inputUnits.equals("f") || inputUnits.equals("C") || inputUnits.equals("c")) {
                System.out.println(outputFormatter(inputValue, inputUnits));
            } else {
                System.out.print("Units unknown"); // print out an error for the user
            }

            System.out.print("Do you wish another conversion? (Y/N): "); /// request if we want to do another conversion
            char continueInput = TextIO.getlnChar(); // get character
            System.out.println();// print whitespace

            if (continueInput == 'n' || continueInput == 'N') { // Loop is always true until we set false and exit
                                                                // program
                System.out.println("Thank you. Goodbye.");
                keepLooping = false;
            }
        }
    }
}