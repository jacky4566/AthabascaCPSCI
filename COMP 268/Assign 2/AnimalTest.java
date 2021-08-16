/**
* title: AnimalTest.java
* description: Creates various sounds for different animals.
* date: 15/08/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Classes:
*
* Class: AnimalTest
*  Description: Requests input from the user and returns appropriate requested animal object
*
*  Constructors:
*   None
* 
*  Methods:
*   Animal request(): Requests input from the user and returns appropriate requested animal object
* 
*  Instance Variables:
*   None
*
*/

import textio.TextIO;

public class AnimalTest { //Animal test class
        
    static Animal request() { // starts a request from user and acts upon it
        System.out.println("Animal Sound generator");
        System.out.println("Please Select a Sound:");
        System.out.println("P: Pig");
        System.out.println("S: Sheep");
        System.out.println("D: Duck");
        System.out.println("C: Cow");
        System.out.print("? ");
        char userInput = TextIO.getlnChar();
        System.out.println(); // add some white space for easy reading
        switch (userInput) { // Use a switch case to output the data.
            case 'p':
            case 'P':
                return new Pig();
            case 's':
            case 'S':
                return new Sheep();
            case 'd':
            case 'D':
                return new Duck();
            case 'c':
            case 'C':
                return new Cow();
            default:
                System.out.println("Invalid Entry");
                return new Animal();
        }
    }
}