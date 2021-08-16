/**
* title: Problem4.java
* description: ***
* date: 13/08/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Problem4
*
* Purpose and Description:
*
* Compiling and running instructions
* Compile:  javac Problem3.java
* Run:      java Problem3.java
*
* Classes:
*  Rodent.java
*
*/

/**
* Test Plan
*/
/**
* Run the application.
* EXPECTED:
Parameters of Mikey Mouse. AKA: Mus musculus
The Mouse eats the cheese
The Mouse sleeps in the barn
This Rodent Grooms
This Rodent Walks
This Rodent Runs
The Mouse squeaks

Parameters of Genghis Khan the Gerbil. AKA: Meriones unguiculatus
The Gerbil eats seeds
The Gerbil sleeps in a den
The Gerbils groom each other
This Rodent Walks
The Gerbil can not run
This Rodent Speaks

Parameters of Penfold the Gerbil. AKA: Cricetinae
This Rodent Eats
The Hamster sleeps in a nest
The Hamster grooms with his tongue
The Hamster walks slowly
The Hamster runs with canter
The Hamster squeaks with a soft voice

Parameters of Babe the Guena Pig AKA: Cavia porcellus
This Rodent Eats
The Gunea Pig sleeps a ball
The Gunea Pig grooms in a dust pit
This Rodent Walks
The Gunea Pig does not run fast
The Gunea Pig squeaks

* ACTUAL:
*    Terminal frame displays as expected
*
* Good data cases:
*   No outside data expected
*
* Bad data cases:
*   No outside data expected
*/
public class Problem4 {
    public static void main(String[] args) {
        // create our objects
        Mouse mickey = new Mouse();
        Gerbil genghis = new Gerbil();
        Hamster penfold = new Hamster();
        GPig babe = new GPig();

        // Print out all the functions for each object
        System.out.println("Parameters of Mikey Mouse. AKA: " + Mouse.scientificName);
        mickey.eat();
        mickey.sleep();
        mickey.groom();
        mickey.walk();
        mickey.run();
        mickey.honk();
        System.out.println();

        System.out.println("Parameters of Genghis Khan the Gerbil. AKA: " + Gerbil.scientificName);
        genghis.eat();
        genghis.sleep();
        genghis.groom();
        genghis.walk();
        genghis.run();
        genghis.honk();
        System.out.println();

        System.out.println("Parameters of Penfold the Gerbil. AKA: " + Hamster.scientificName);
        penfold.eat();
        penfold.sleep();
        penfold.groom();
        penfold.walk();
        penfold.run();
        penfold.honk();
        System.out.println();

        System.out.println("Parameters of Babe the Guena Pig AKA: " + GPig.scientificName);
        babe.eat();
        babe.sleep();
        babe.groom();
        babe.walk();
        babe.run();
        babe.honk();
        System.out.println();
    }
}