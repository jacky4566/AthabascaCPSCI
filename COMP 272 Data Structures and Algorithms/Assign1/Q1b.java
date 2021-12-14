/**
* title: Q1b.java
* date: 12/12/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Stack
*
* Purpose and Description:
*  Creates a stack using 2 data type queues. Impliment the pop() and push(x) functions. 
*
* Compiling and running instructions
*  Compile:  javac *.java 
*  Run:      java Q1b
*
* Dependent Classes:
*  Stack.java
*
*/

/**
* Test Plan
*/
/**
* Run the application in terminal. No User input required.
* Expected static result:

Demo of a stack with 2 Queues
Add element to Stack: 1
Add element to Stack: 5
Add element to Stack: 13
Add element to Stack: -42
Current size: 4
Get element from Stack: -42
Get element from Stack: 13
Current size: 2
Done

**
* Learning Sources
**
* Class text and resources
* https://java2blog.com/implement-stack-using-two-queues-in-java/
* https://en.wikipedia.org/wiki/Stack_(abstract_data_type)
*
*/

public class Q1b {
    public static void main(String[] args) { //Main program
        //Some start text
        System.out.println("Demo of a stack with 2 Queues"); 

        //Create a new stack, add some items
        Stack S = new Stack(); 
        S.push(1);
        S.push(5);
        S.push(13);
        S.push(-42);
 
         //Print the current size
        System.out.println("Current size: " + S.size());

        //pop 2 elements
        System.out.println("Get element from Stack: " + S.pop());
        System.out.println("Get element from Stack: " + S.pop());

        //Print the current size
        System.out.println("Current size: " + S.size());

        System.out.println("Done");
    }

}