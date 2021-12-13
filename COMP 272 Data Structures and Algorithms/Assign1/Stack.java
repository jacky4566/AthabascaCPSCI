/**
* title: Stack.java
* date: 12/12/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Stack Class
*
* Purpose and Description:
*  Creates a Stack implimentation using 2 standard queues. 
*  Impliments the required functions psuh(x), pop(). Size was also included for debug.
*
* Compiling and running instructions
*  See Parent file Assign1Stack.java
*
* Dependent Classes:
*  java.util.LinkedList
*
*  Constructors:
    None given
    
*  Methods:
    push(int)
     Adds a new integer to our "stack". If queue 1 is empty we add the element there. Else dequeue 1 into queue 2. add the element, then reverse the order again back into queue 1
     This method should always maintain queue 1 as the virtual stack.
    
    pop()
     This method returns the top integer from Queue 1. Q1 is always maintained as the virtual stack. 

    print()
     Prints out the contents of Q1 for debugging and display.
    
    size()
     Returns the size of Q1 as integer.


*  Class Variables:
    LinkedList queue1
     Holds a virtual "stack"
    
    LinkedList queue2
     Used for adding items to our stack. Required to reverse the order of FIFO to FILO
*/

import java.util.*;

public class Stack {
    // Use 2 Java LinkedLists as Queues
    private Queue<Integer> queue1 = new LinkedList<>();
    private Queue<Integer> queue2 = new LinkedList<>();

    public void push(int i) {
        System.out.println("Add element to Stack: " + i);

        if (queue1.isEmpty()) //If we only have 1 item, add it to the virtual stack
            queue1.add(i);
        else { //Else copy elements of Queue1 to Queue2
            int sizeOfstack = queue1.size();
            for (int j = 0; j < sizeOfstack; j++){
                queue2.add(queue1.remove());
            }

            //add our new element which is to become the new top of stack
            queue1.add(i); 

            // Copy elements for Queue2 to Queue1, reverse the order.
            for (int k = 0; k < sizeOfstack; k++)
                queue1.add(queue2.remove());
        }
    }

    public int pop() {
        //Get the first item from Q1 which will be FILO
        return queue1.remove();
    }
    
    public int size() {
        return queue1.size();
    }
}
