/**
* title: Problem1.java
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
* Problem1
*
* Purpose and Description:
* Creates various sounds for different animals. Uses a parent class with a default sound and child classes to change that sound
*
* Compiling and running instructions
* Compile:  javac Problem3.java
* Run:      java Problem3.java
*
* Classes:
*  Animal.java
*  AnimalTest.java
*
*/

/**
* Test Plan
*/
/**
* Run the application.
* EXPECTED:
* Static test:

    I am an animal
    I am a Pig
    pig says "oink"

    I am an animal
    I am a Sheep
    sheep says "baah"

    I am an animal
    I am a Duck
    duck says "quack"

    I am an animal
    I am a Cow
    cow says "moo"

    Animal Sound generator
    Please Select a Sound:
    P: Pig
    S: Sheep
    D: Duck
    C: Cow
    ?

* User question will diverge the output from here, see good/bad cases.
* Program will end with the following print:

Thank you, Good bye

* ACTUAL:
*    Terminal frame displays as expected
*
* Good data cases:
*   User input 'p' or 'P':

        I am an animal
        I am a Pig
        pig says "oink"

*   User input 's' or 'S':

        I am an animal
        I am a Sheep
        sheep says "baah"

*   User input 'd' or 'D':

        I am an animal
        I am a Duck
        duck says "quack"

*   User input 'c' or 'C':
        I am an animal
        I am a Cow
        cow says "moo"

* Bad data cases:

*   User input other character(s):

        Invalid Entry
        I am an animal
        An animal makes a sound based on the animal that it is

*/

public class Problem1 {
    public static void main(String[] args) { //Main program
        Pig porky = new Pig(); //create new instance of Pig
        porky.sound();         //Make Pig noise
        System.out.println();  //add whitespace
        
        Sheep dolly = new Sheep();//create new instance of Sheep
        dolly.sound();         //Make Sheep noise
        System.out.println();  //add whitespace
        
        Duck daffy = new Duck();//create new instance of Duck
        daffy.sound();         //Make Duck noise
        System.out.println();  //add whitespace
        
        Cow beef = new Cow();  //create new instance of Cow
        beef.sound();          //Make Cow noise
        System.out.println();  //add whitespace

        //AnimalTest userAnimal = new AnimalTest(); //Create new class of animal test
        Animal testAnimal = AnimalTest.request(); //Class does the user request and returns requested type
        testAnimal.sound();                        //Newly created animat makes noise. 

        System.out.println("Thank you, Good bye");
    }
}