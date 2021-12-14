/**
* title: LLNode.java
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
*  A node element for use in a linked list. Contains pointers for both single and doubly linked lists. 
*  Also generates a random int 0-100 for demonstation purposes.
*
* Compiling and running instructions
*  See parent file Q2a.Java
*
* Dependent Classes:
*  None
*
*  Constructor:
    LLNode()
     Generates a new node with a random Int for data.

*  Methods:
    None

*  Class Variables:
    LLNode nextPtr
     A pointer for the next node in the List

    LLNode previousPtr
     A pointer for the previous node in the List. Only required for doubly linked lists.

    int data
     Integer type to hold node data. Used for demonstration purposes. Could also contain any required data elemnt. 
*/

 public class LLNode {
    LLNode nextPtr;
    LLNode previousPtr; // Used for double linked list
    int data;

    LLNode() { // initialize with some data
        data = (int) (Math.random() * 100);
    }
}
