/**
* title: Q6.java
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
*  Creates and demonstrates a MinStack implimentation with push(x), pop(), min(), and size() funcitons
*  All operations should be complete in O(1) time
*
* Compiling and running instructions
*  Compile:  javac *.java 
*  Run:      java Q6
*
* Dependent Classes:
*  MinStack.java
*  LLNode.java
*
*/

/**
* Test Plan
*/
/**
* Run the application in terminal. No User input required.
* Expected static result:

Demo of a MinStack
Add element to Stack: 74
Add element to Stack: 98
Add element to Stack: 72
Add element to Stack: 33
Add element to Stack: 46
Add element to Stack: 70
Add element to Stack: 17
Add element to Stack: 99
Add element to Stack: 25
Add element to Stack: 2
Stack holds, Top to bottom: 2, 25, 99, 17, 70, 46, 33, 72, 98, 74,
Stack Size: 10
Minimum item: 2
Pop item: 2
Pop item: 25
Minimum item: 17
Stack holds, Top to bottom: 99, 17, 70, 46, 33, 72, 98, 74,
Stack Size: 8
Done

/*
**
* Learning Sources
**/
/** 
* Class text and resources
* 
*/

public class Q6 {
    public static void main(String[] args) { //Main program
        //Some start text
        System.out.println("Demo of a MinStack"); 

        //Create a new stack
        MinStack MStack = new MinStack();

        //Add some items to our queue
        for (int i = 0; i < 10; i++) {
            MStack.push(new LLNode());
        }

        //Print out the list
        MStack.print();

        //Get stack size
        System.out.println("Stack Size: " + MStack.size());

        //Get smallest value in stack
        System.out.println("Minimum item: " + MStack.min());

        //Pop two items off the stack
        System.out.println("Pop item: " + MStack.pop().data);
        System.out.println("Pop item: " + MStack.pop().data);

        //Get smallest value in stack
        System.out.println("Minimum item: " + MStack.min());

        //Print out the list
        MStack.print();

        //Get stack size
        System.out.println("Stack Size: " + MStack.size());

        //Done
        System.out.println("Done");
    }
}