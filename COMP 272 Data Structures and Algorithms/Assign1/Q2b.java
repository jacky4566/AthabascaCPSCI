/**
* title: Q2b.java
* date: 13/12/2021
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
*  Creates a doubly linked list and demonstate the ability to swap elements with only thier nodes connections.
*  This demo uses a doubly linked list.
*
* Compiling and running instructions
*  Compile:  javac *.java 
*  Run:      java Q2b
*
* Dependent Classes:
*  LLNode.java
*
*
*  Methods:
    swap23(LLNode)
     Swaps nodes 2 and 3 in a linked list. Input head node of List.

    addNodes(int, LLNode)
     Adds X number of nodes to a linked list. 
     First argument: Number of elements to add
     Second argument: Head of Linked List

    print(LLNode)
     Prints out a linked list from a given head node.

*  Class Variables:
    LLNode head
     A master head node for the Linked List

*/

/**
* Test Plan
*/
/**
* Run the application in terminal. No User input required.
* Expected static result:

Demo of swapping links within a doubly linked list
List Contains:
(Prev Curr Next)
(Null   49   61),
(  49   61   14),
(  61   14   30),
(  14   30   97),
(  30   97   86),
(  97   86 Null)
Swapping items 2 & 3
List Contains:
(Prev Curr Next)
(Null   49   14),
(  49   14   61),
(  14   61   30),
(  61   30   97),
(  30   97   86),
(  97   86 Null)
Done

/*
**
* Learning Sources
**/
/**
 * Class text and resources
 * https://www.geeksforgeeks.org/stringbuilder-class-in-java-with-examples/
 * https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html
 *
 */

public class Q2b {
    public static void main(String[] args) { // Main program
        // Some start text
        System.out.println("Demo of swapping links within a doubly linked list");

        // create a head node
        LLNode head = new LLNode();

        // Add 5 more nodes to the list
        addNodes(5, head);

        // print out the list
        print(head);

        // Swap 2nd and 3rd position data
        swap23(head);

        // print out the list
        print(head);

        // Done
        System.out.println("Done");
    }

    static void swap23(LLNode pointer){
        // swaps the 2nd and 3rd items in a linked list
        System.out.println("Swapping items 2 & 3");
        LLNode prev = pointer;                  //create a pointer for the previous node
        LLNode curr = pointer.nextPtr;          //create a pointer for the current node
        LLNode next = pointer.nextPtr.nextPtr;  //create a pointer for the next node
        int position = 1;                       //Keep track of our position in the chain. we already moved 1
        while (next != null) {                  //go until end of list
            if (position == 1){                 //If we are at the desired position
                prev.nextPtr = next;            //Set previous nodes Next pointer to the current nodes Next node.
                curr.nextPtr = next.nextPtr;    //Set current nodes Next pointer to the next nodes Next pointer.
                next.nextPtr.previousPtr = curr;//Set Next nodes previous pointer to the next node.
                next.nextPtr = curr;            //Set Next nodes Next pointer to the current node.
                curr.previousPtr = next;        //Set current nodes previous pointer to the next node.
                next.previousPtr = prev;        //Set next nodes previous pointer to the previous node.
                break;                          //We did what we want. break out.
            }
            prev = prev.nextPtr;                //Else move ahead in the chain
            curr = curr.nextPtr;
            next = next.nextPtr;
            position++;
        }
    }

    static void addNodes(int x, LLNode pointer) {
        for (int i = 0; i < x; i++) {
            pointer.nextPtr = new LLNode();    //Add a new node with random data to the next position
            pointer.nextPtr.previousPtr = pointer;//assign the next nodes previous to our current node.
            pointer = pointer.nextPtr;         //move pointer to the next node in the chain
        }
    }

    static void print(LLNode pointer) {
        System.out.println("List Contains: ");
        System.out.println("(Prev Curr Next)"); //Print a header
        while (pointer != null) {
            StringBuilder str = new StringBuilder(); //Start a new string builder
            str.append("(");

            if (pointer.previousPtr != null){ //if exists. add the previous node data 
                str.append(String.format("%4d", pointer.previousPtr.data));
            }else{
                str.append("Null");
            }

            str.append(" " + String.format("%4d", pointer.data) + " "); //add the current node data

            if (pointer.nextPtr != null){ //if exists. add the next node data 
                str.append(String.format("%4d", pointer.nextPtr.data));
            }else{
                str.append("Null");
            }

            str.append(")"); //add finalizer
            if (pointer.nextPtr != null){
                str.append(",");
            }
            System.out.println(str); //print the string
            pointer = pointer.nextPtr;
        }
    }
}