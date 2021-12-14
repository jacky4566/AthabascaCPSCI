/**
* title: Q4.java
* date: 12/12/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Priority Queue
*
* Purpose and Description:
*  Creates a priority queue and demonstrates the methods: add(x), deleteMin(), and size().
*  This implimentation uses a singly linked list.
*
* Compiling and running instructions
*  Compile:  javac *.java 
*  Run:      java Q1a
*
* Dependent Classes:
*  PriorityQueue.java
*  PQNode.java
*
*/

/**
* Test Plan
*/
/**
* Run the application in terminal. No User input required.
* Expected static result:


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
        System.out.println("Demo of bag"); 

        RandomQueue RQ = new RandomQueue();

        for (int i = 0; i < 10; i++) {
            RQ.add(new LLNode());
        }

        RQ.print();

        System.out.println(RQ.remove().data);

        //Done
        System.out.println("Done");
    }
}