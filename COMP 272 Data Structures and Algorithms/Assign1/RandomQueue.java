/**
* title: RandomQueue.java
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
*  Class implimention of a Random Queue.
*  This implimentation uses a singly linked list based on Q1
*
* Compiling and running instructions
*  See parent file Q4.java
*
* Dependent Classes:
*  LLNode.java
*
*  Constructors:
    None given
    
*  Methods:
    add(LLNode)
     Adds a new LLNode object to our random queue. New items are added at the back of the queue represented by the tail.

    remove()
     Grabs a random object and removes it from the queue. Links within the list are maintained.

    print()
     Prints out the current queue from tail to head.

*  Class Variables:
    LLNode tail
     Holds a LLNode object with the smallest priority. Represents the end of our queue.

    Int size
     Holds a count of the items in our list. 
*
**
* Learning Sources
**/
/** 
* Class text and resources
* 
*/

public class RandomQueue {
    private LLNode tail; // start of list
    private int size; // Used to keep track of list size

    public void add(LLNode newNode) {
        System.out.println("Adding Element: " + newNode.data); // Confirm back to user
        size++;             // incrament counter
        if (tail == null){  // If ths is the first item in our queue
            tail = newNode;
            return;
        }
        newNode.nextPtr = tail; //else make the new item our head and shuffle everything else back as tail
        tail = newNode;
    }

    public LLNode remove() {
        LLNode returnNode = tail.nextPtr;
        LLNode prev = tail;
        int targetNode = (int) (Math.random() * size); //pick our random item to remove, Min 0, Max = list size
        System.out.println("Removing Item: " + targetNode); //verify back to user

        if (targetNode == 0){   //If we want the tail, directly return it.
            size--;
            returnNode = tail;  //gather return object into holder
            tail = tail.nextPtr;//assign new tail
            return returnNode;
        }
        targetNode--;
        while(targetNode-- != 0){               //search through the list until find our node
            returnNode = returnNode.nextPtr;    //increament to next linked item
            prev = prev.nextPtr;                //increament to next linked item
        }
        size--;
        prev.nextPtr = returnNode.nextPtr; //ensure linked list is maintained.
        return returnNode;
    }

    public void print(){
        //Print out the linked list
        LLNode pointer = tail;
        for (int i = 0; i < size; i++) {
            System.out.print(pointer.data);
            if (pointer.nextPtr != null){
                System.out.print(", ");
            }
            pointer = pointer.nextPtr;
        }
        System.out.println();
    }
}
