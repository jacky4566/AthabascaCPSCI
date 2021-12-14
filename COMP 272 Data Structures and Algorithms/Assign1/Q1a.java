/**
* title: Q1a.java
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

Demo of Priority Queue
Adding Element: 75
Adding Element: 5
Adding Element: 9
Adding Element: 7
Adding Element: -6
Adding Element: 0
Data in queue: -6, 0, 5, 7, 9, 75
Current size of Queue: 6
Element Deleted: -6
Element Deleted: 0
Data in queue: 5, 7, 9, 75
Current size of Queue: 4
Done

/*
**
* Learning Sources
**/
/** 
* Class text and resources
* https://www.geeksforgeeks.org/priority-queue-class-in-java/
* https://www.freecodecamp.org/news/priority-queue-implementation-in-java/
* https://algorithmtutor.com/Data-Structures/Tree/Priority-Queues/
* https://en.wikipedia.org/wiki/Queue_(abstract_data_type)
* 
*/

public class Q1a {
    public static void main(String[] args) { //Main program
        //Some start text
        System.out.println("Demo of Priority Queue"); 

        //Create new instance of a PriorityQueue
        PriorityQueue PQ = new PriorityQueue(); 

        // Add items to a Priority Queue (ENQUEUE)
        PQ.add(new PQNode(75));
        PQ.add(new PQNode(5));
        PQ.add(new PQNode(9));
        PQ.add(new PQNode(7));
        PQ.add(new PQNode(-6));
        PQ.add(new PQNode(0));

        //Print Queue
        PQ.print();

        //Get size of Queue
        System.out.println("Current size of Queue: " + PQ.size());

        // Remove 2 smallest items from the Priority Queue (DEQUEUE)
        System.out.println("Element Deleted: " + PQ.deleteMin().priority);
        System.out.println("Element Deleted: " + PQ.deleteMin().priority);

        //Print Queue
        PQ.print();

        //Get size of Queue
        System.out.println("Current size of Queue: " + PQ.size());

        //Done
        System.out.println("Done");
    }

}