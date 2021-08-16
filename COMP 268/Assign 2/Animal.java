/**
* title: Animal.java
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
*
*/
public class Animal{ //Default class for all animals

    public Animal() {// constructor
        System.out.println("I am an animal");
    }

    public void sound(){
        System.out.println("An animal makes a sound based on the animal that it is");
    }
}

class Pig extends Animal{ //Pig Class

    public Pig() {// constructor
        System.out.println("I am a Pig");
    }

    @Override
    public void sound() {
        System.out.println("pig says \"oink\"");
    }
}

class Sheep extends Animal{ //Sheep Class

    public Sheep() {// constructor
        System.out.println("I am a Sheep");
    }

    @Override
    public void sound() {
        System.out.println("sheep says \"baah\"");
    }
}

class Duck extends Animal{ //Duck Class

    public Duck() {// constructor
        System.out.println("I am a Duck");
    }

    @Override
    public void sound() {
        System.out.println("duck says \"quack\"");
    }
}

class Cow extends Animal{ //Cow Class

    public Cow() {// constructor
        System.out.println("I am a Cow");
    }

    @Override
    public void sound() {
        System.out.println("cow says \"moo\"");
    }
}