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
*
* Class: Animal
*  Description: Parent class that acts as default
*
*  Constructors:
*   Animal()
*    Prints the generic phrase "I am an animal"
* 
*  Methods:
*   void sound():  Prints a default sound
* 
*  Instance Variables:
*   None
*
* Class: Pig
*  Description: Creates a child class pig that inherits from Animal class
*
*  Constructors:
*   Pig()
*    Prints the phrase "I am a Pig"
* 
*  Methods:
*   void sound():  Prints a pig specific sound
* 
*  Instance Variables:
*   None
*
* Class: Sheep
*  Description: Creates a child class pig that inherits from Animal class
*
*  Constructors:
*   Sheep()
*    Prints the phrase "I am a Sheep"
* 
*  Methods:
*   void sound():  Prints a Sheep specific sound
* 
*  Instance Variables:
*   None
*
* Class: Duck
*  Description: Creates a child class Duck that inherits from Animal class
*
*  Constructors:
*   Duck()
*    Prints the phrase "I am a Duck"
* 
*  Methods:
*   void sound():  Prints a Duck specific sound
* 
*  Instance Variables:
*   None
*
* Class: Cow
*  Description: Creates a child class Cow that inherits from Animal class
*
*  Constructors:
*   Cow()
*    Prints the phrase "I am a Cow"
* 
*  Methods:
*   void sound():  Prints a Cow specific sound
* 
*  Instance Variables:
*   None
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

import textio.TextIO;

public class Problem1 {

    static class Animal{ //Default class for all animals

        public Animal() {// constructor
            System.out.println("I am an animal");
        }

        public void sound(){
            System.out.println("An animal makes a sound based on the animal that it is");
        }
    }

    static class Pig extends Animal{ //Pig Class

        public Pig() {// constructor
            System.out.println("I am a Pig");
        }

        @Override
        public void sound() {
            System.out.println("pig says \"oink\"");
        }
    }

    static class Sheep extends Animal{ //Sheep Class

        public Sheep() {// constructor
            System.out.println("I am a Sheep");
        }

        @Override
        public void sound() {
            System.out.println("sheep says \"baah\"");
        }
    }

    static class Duck extends Animal{ //Duck Class

        public Duck() {// constructor
            System.out.println("I am a Duck");
        }

        @Override
        public void sound() {
            System.out.println("duck says \"quack\"");
        }
    }

    static class Cow extends Animal{ //Cow Class

        public Cow() {// constructor
            System.out.println("I am a Cow");
        }

        @Override
        public void sound() {
            System.out.println("cow says \"moo\"");
        }
    }

    static class AnimalTest { //Animal test class
        
        Animal request() { // starts a request from user and acts upon it
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

        AnimalTest userAnimal = new AnimalTest(); //Create new class of animal test
        Animal testAnimal = userAnimal.request(); //Class does the user request and returns requested type
        testAnimal.sound();                        //Newly created animat makes noise. 

        System.out.println("Thank you, Good bye");
    }
}