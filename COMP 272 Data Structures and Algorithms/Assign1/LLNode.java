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
     Also pre-populates minimum value with newly assinged data

*  Methods:
    setMin(int)
     Takes a single int and will update this.min with the smallest value. Used for tracking MinStack

*  Class Variables:
    LLNode nextPtr
     A pointer for the next node in the List

    LLNode previousPtr
     A pointer for the previous node in the List. Only required for doubly linked lists.

    int data
     Integer type to hold node data. Used for demonstration purposes. Could also contain any required data elemnt. 

    int min
     Used to keep track of minimum value for MinStack
*/

 public class LLNode {
    LLNode nextPtr;
    LLNode previousPtr; // Used for double linked list
    int data;
    int min;

    LLNode() { // initialize with some data
        data = (int) (Math.random() * 100);
        min = data;
    }

    public void setMin(int value){
        min = Math.min(min,value);
    }
}
