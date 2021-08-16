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
*
* Class: Rodent
*  Description: Parent class that acts as default
*
*  Constructors:
*   None provided
* 
*  Methods:
*   void eat():   Prints default behavior for all rodents
*   void sleep(): Prints default behavior for all rodents
*   void groom(): Prints default behavior for all rodents
*   void walk():  Prints default behavior for all rodents
*   void run():   Prints default behavior for all rodents
*   void honk():  Prints default behavior for all rodents
* 
*  Instance Variables:
*   protected String scientificName: Provides a default scientific name. Permissions are controlled with use of protected
*
* Class: Mouse
*  Description: Animal specific class of Mouse. Inherits Rodent.
*
*  Constructors:
*   None provided
* 
*  Methods:
*   void eat():   Overrides parent method with custom mouse behavior
*   void sleep(): Overrides parent method with custom mouse behavior
*   void honk():  Overrides parent method with custom mouse behavior
* 
*  Instance Variables:
*   private String scientificName: Provides a static scientific name. Hides the parent variable.
*
* Class: Gerbil
*  Description: Animal specific class of Gerbil. Inherits Rodent.
*
*  Constructors:
*   None provided
* 
*  Methods:
*   void eat():   Overrides parent method with custom Gerbil behavior
*   void sleep(): Overrides parent method with custom Gerbil behavior
*   void groom(): Overrides parent method with custom Gerbil behavior
*   void run():   Overrides parent method with custom Gerbil behavior
* 
*  Instance Variables:
*   private String scientificName: Provides a static scientific name. Hides the parent variable.
*
* Class: Hamster
*  Description: Animal specific class of Hamster. Inherits Rodent.
*
*  Constructors:
*   None provided
* 
*  Methods:
*   void sleep(): Overrides parent method with custom Hamster behavior
*   void groom(): Overrides parent method with custom Hamster behavior
*   void walk():  Overrides parent method with custom Hamster behavior
*   void run():   Overrides parent method with custom Hamster behavior
*   void honk():  Overrides parent method with custom Hamster behavior
* 
*  Instance Variables:
*   private String scientificName: Provides a static scientific name. Hides the parent variable.
*
* Class: GPih
*  Description: Animal specific class of Guinea Pig. Inherits Rodent.
*
*  Constructors:
*   None provided
* 
*  Methods:
*   void sleep(): Overrides parent method with custom Guinea Pig behavior
*   void groom(): Overrides parent method with custom Guinea Pig behavior
*   void run():   Overrides parent method with custom Guinea Pig behavior
*   void honk():  Overrides parent method with custom Guinea Pig behavior
* 
*  Instance Variables:
*   private String scientificName: Provides a static scientific name. Hides the parent variable.
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

    static class Rodent { // Parent class of rodent
        protected String scientificName = "Rodentia";

        public void eat() { // Default behaviour for rodent
            System.out.println("This Rodent Eats");
        }

        public void sleep() { // Default behaviour for rodent
            System.out.println("This Rodent Sleeps");
        }

        public void groom() { // Default behaviour for rodent
            System.out.println("This Rodent Grooms");
        }

        public void walk() { // Default behaviour for rodent
            System.out.println("This Rodent Walks");
        }

        public void run() { // Default behaviour for rodent
            System.out.println("This Rodent Runs");
        }

        public void honk() { // Default behaviour for rodent
            System.out.println("This Rodent Speaks");
        }
    }

    static class Mouse extends Rodent {
        private String scientificName = "Mus musculus"; // Hiding Parent class's variable with our new variable

        // Override parent methods with our own custom prints.
        @Override
        public void eat() {
            System.out.println("The Mouse eats the cheese");
        }

        @Override
        public void sleep() {
            System.out.println("The Mouse sleeps in the barn");
        }

        @Override
        public void honk() {
            System.out.println("The Mouse squeaks");
        }
    }

    static class Gerbil extends Rodent {
        private String scientificName = "Meriones unguiculatus"; // Hiding Parent class's variable with our new variable

        // Override parent methods with our own custom prints.
        @Override
        public void eat() {
            System.out.println("The Gerbil eats seeds");
        }

        @Override
        public void sleep() {
            System.out.println("The Gerbil sleeps in a den");
        }

        @Override
        public void groom() {
            System.out.println("The Gerbils groom each other");
        }

        @Override
        public void run() {
            System.out.println("The Gerbil can not run");
        }
    }

    static class Hamster extends Rodent {
        private String scientificName = "Cricetinae"; // Hiding Parent class's variable with our new variable

        // Override parent methods with our own custom prints.
        @Override
        public void sleep() {
            System.out.println("The Hamster sleeps in a nest");
        }

        @Override
        public void groom() {
            System.out.println("The Hamster grooms with his tongue");
        }

        @Override
        public void walk() {
            System.out.println("The Hamster walks slowly");
        }

        @Override
        public void run() {
            System.out.println("The Hamster runs with canter");
        }

        @Override
        public void honk() {
            System.out.println("The Hamster squeaks with a soft voice");
        }
    }

    static class GPig extends Rodent {
        private String scientificName = "Cavia porcellus"; // Hiding Parent class's variable with our new variable

        // Override parent methods with our own custom prints.
        @Override
        public void sleep() {
            System.out.println("The Gunea Pig sleeps a ball");
        }

        @Override
        public void groom() {
            System.out.println("The Gunea Pig grooms in a dust pit");
        }

        @Override
        public void run() {
            System.out.println("The Gunea Pig does not run fast");
        }

        @Override
        public void honk() {
            System.out.println("The Gunea Pig squeaks");
        }
    }

    public static void main(String[] args) {
        // create our objects
        Mouse mickey = new Mouse();
        Gerbil genghis = new Gerbil();
        Hamster penfold = new Hamster();
        GPig babe = new GPig();

        // Print out all the functions for each object
        System.out.println("Parameters of Mikey Mouse. AKA: " + mickey.scientificName);
        mickey.eat();
        mickey.sleep();
        mickey.groom();
        mickey.walk();
        mickey.run();
        mickey.honk();
        System.out.println();

        System.out.println("Parameters of Genghis Khan the Gerbil. AKA: " + genghis.scientificName);
        genghis.eat();
        genghis.sleep();
        genghis.groom();
        genghis.walk();
        genghis.run();
        genghis.honk();
        System.out.println();

        System.out.println("Parameters of Penfold the Gerbil. AKA: " + penfold.scientificName);
        penfold.eat();
        penfold.sleep();
        penfold.groom();
        penfold.walk();
        penfold.run();
        penfold.honk();
        System.out.println();

        System.out.println("Parameters of Babe the Guena Pig AKA: " + babe.scientificName);
        babe.eat();
        babe.sleep();
        babe.groom();
        babe.walk();
        babe.run();
        babe.honk();
        System.out.println();
    }
}