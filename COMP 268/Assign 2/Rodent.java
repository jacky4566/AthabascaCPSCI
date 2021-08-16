/**
* title: Rodent.java
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

public class Rodent { // Parent class of rodent
    static String scientificName = "Rodentia";

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

class Mouse extends Rodent {
    static String scientificName = "Mus musculus"; // Hiding Parent class's variable with our new variable

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

class Gerbil extends Rodent {
    static String scientificName = "Meriones unguiculatus"; // Hiding Parent class's variable with our new variable

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

class Hamster extends Rodent {
    static String scientificName = "Cricetinae"; // Hiding Parent class's variable with our new variable

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

class GPig extends Rodent {
    static String scientificName = "Cavia porcellus"; // Hiding Parent class's variable with our new variable

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