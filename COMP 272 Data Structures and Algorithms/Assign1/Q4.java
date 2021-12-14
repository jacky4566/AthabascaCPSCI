/**
* title: Q4.java
* date: 14/12/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Random Queue
*
* Purpose and Description:
*  Creates a random queue and demonstrates the methods: add(x), remove().
*  This implimentation uses a singly linked list.
*
* Compiling and running instructions
*  Compile:  javac *.java 
*  Run:      java Q4
*
* Dependent Classes:
*  RandomQueue.java
*  LLNode.java
*
*/

/**
* Test Plan
*/
/**
* Run the application in terminal. No User input required.
* Expected static result:

Demo of a Random queue
Adding Element: 31
Adding Element: 94
Adding Element: 68
Adding Element: 9
Adding Element: 13
Adding Element: 7
Adding Element: 22
Adding Element: 24
Adding Element: 14
Adding Element: 66
66, 14, 24, 22, 7, 13, 9, 68, 94, 31
Removing Item: 4
Data of removed: 7
66, 14, 24, 22, 13, 9, 68, 94, 31
Done

/*
**
* Learning Sources
**/
/** 
* Class text and resources
* 
*/

public class Q4 {
    public static void main(String[] args) { //Main program
        //Some start text
        System.out.println("Demo of a Random queue"); 

        //Create a new queue
        RandomQueue RQ = new RandomQueue();

        //Add some items to our queue
        for (int i = 0; i < 10; i++) {
            RQ.add(new LLNode());
        }

        //Print out the list
        RQ.print();

        //remove a random item and print it
        System.out.println("Data of removed: " + RQ.remove().data);

        //Print out the list
        RQ.print();

        //Done
        System.out.println("Done");
    }
}