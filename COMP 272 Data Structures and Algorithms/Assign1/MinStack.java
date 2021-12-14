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
    push(LLNode)
     Adds a new object to our stack. Top is alwasy maintained as the top of the stack. A linked list built down from there is the remaining stack.
     increaments the size counter and assigns the minimum value thus far. 
    
    pop()
     Returns the top object in the list. Second object is now assined as top.

    min()
     returns the integer of the smallest value in the stack, this number is always stored in the top of the stack

    print()
     Prints out the contents of the stack for demonstration and debug.
    
    size()
     Returns the size of the stack.


*  Class Variables:
    LLNode top
     Holds a reference to the top of the stack. Remainig objects are built down from there.
    
    int size
     Holds a count of our stack size.
*/

public class MinStack {
    LLNode top; //top of our stack
    int size = 0;

    public void push(LLNode newNode) {
        //Add a new element to the stack
        System.out.println("Add element to Stack: " + newNode.data);
        //add new top to top of stack
        size++;
        if (top == null){
            top = newNode;
            return;
        }
        newNode.setMin(top.min); //keep track of minimum value thus far
        newNode.nextPtr = top;
        top = newNode;
    }

    public LLNode pop() {
        //Pop the top
        LLNode returnNode = top;
        size--;
        top = top.nextPtr;
        return returnNode;
    }

    public int min(){
        //returns the smallest data point
        return top.min;
    }
    
    public int size() {
        //returns the size of our stack
        return size;
    }

    public void print(){
        //Print out the stack
        System.out.print("Stack holds, Top to bottom: ");
        LLNode pointer = top;
        for (int i = 0; i < size; i++) {
            System.out.print(pointer.data + ", ");
            pointer = pointer.nextPtr;
        }
        System.out.println();
    }
}
