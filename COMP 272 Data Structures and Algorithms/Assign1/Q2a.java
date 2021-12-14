/**
* title: Q2a.java
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
*  Creates a single linked list and demonstate the ability to swap elements with only thier nodes connections.
*  This demo uses a singly linked list.
*
* Compiling and running instructions
*  Compile:  javac *.java 
*  Run:      java Q2a
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

Demo of swapping links within a single linked list
List Contains: 5, 28, 40, 53, 34, 32
Swapping items 2 & 3
List Contains: 5, 40, 28, 53, 34, 32
Done

/*
**
* Learning Sources
**/
/**
 * Class text and resources
 * 
 */

public class Q2a {
    public static void main(String[] args) { // Main program
        // Some start text
        System.out.println("Demo of swapping links within a single linked list");

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
        LLNode prev = pointer;              //create a pointer for the previous node
        LLNode curr = pointer.nextPtr;      //create a pointer for the current node
        LLNode next = pointer.nextPtr.nextPtr;//create a pointer for the next node
        int position = 1;                   //Keep track of our position in the chain. we already moved 1
        while (next != null) {              //go until end of list
            if (position == 1){             //If we are at the desired position
                prev.nextPtr = next;        //Set previous nodes Next pointer to the current nodes Next node.
                curr.nextPtr = next.nextPtr;//Set current nodes Next pointer to the next nodes Next node.
                next.nextPtr = curr;        //Set Next nodes Next pointer to the current node.
                break;                      //We did what we want. break out.
            }
            prev = prev.nextPtr;            //Else move ahead in the chain
            curr = curr.nextPtr;
            next = next.nextPtr;
            position++;
        }
    }

    static void addNodes(int x, LLNode pointer){
        for (int i = 0; i < x; i++) {  
            pointer.nextPtr = new LLNode();    //Add a new node with random data
            pointer = pointer.nextPtr;         //move pointer to the next node in the chain
        }
    }

    static void print(LLNode pointer) {
        System.out.print("List Contains: ");
        while (pointer != null) {
            System.out.print(pointer.data);
            if (pointer.nextPtr != null){
                System.out.print(", ");
            }
            pointer = pointer.nextPtr;
        }
        System.out.println();
    }
}