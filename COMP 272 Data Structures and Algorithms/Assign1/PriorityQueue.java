/**
* title: PriorityQueue.java
* date: 12/12/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Priority Queue Class
*
* Purpose and Description:
*  Creates a priority queue implimentation. Using a subclass PQNode to hold data and create a single linked list of data.
*  Impliments the required functions add(x), deleteMin(), and size().
*
* Compiling and running instructions
*  See Parent file Assign1PQ.java
*
* Dependent Classes:
*  PQNode.java
*
*  Constructors:
    None given
    
*  Methods:
    add(PQNode)
     Adds a new PQNode object to our priority queue. Slot 0 will be maintained as the smallet number.

    print()
     Prints out the current linked list of data. Data is already sorted by queue priority.

    deleteMin()
     Removes the smallest priority PQNode which is already stored in Slot 0. Returns PQNode of item deleted.

    size()
     returns the current size of the linked list as int

*  Class Variables:
    PQNode head
     Holds a PQNode with the smallest priority. Slot 0 for the priority queue

    Int size
     Holds a count of the items in our list. 
*/

public class PriorityQueue {
    private PQNode head; // start of list
    private int size; //Used to keep track of list size

    public void add(PQNode newNode) {
        System.out.println("Adding Element: " + newNode.priority); //Confirm back to user
        size++; //incrament counter
        if (head == null || newNode.priority < this.head.priority) { //If the list is or we are inserting to slot 0
            newNode.next = head;
            head = newNode;
            return;
        }  
        //else find smallest slot for new data
        PQNode pointer = head; //create a pointer to keep track
        while (pointer.next != null && pointer.next.priority < newNode.priority){ //while next pointer is not null AND next slot data is smaller than our target
            pointer = pointer.next; //get next slot
        }
        newNode.next = pointer.next;
        pointer.next = newNode;        
    }

    public void print() {
        PQNode printNode = head; //get our slot 0

        System.out.print("Data in queue: ");

        while (printNode != null) { //while the target slot to print is not null, print it our
            System.out.print(printNode.priority);
            if (printNode.next != null) {
                System.out.print(", ");
            }
            printNode = printNode.next; //get next slot
        }

        System.out.println();
    }

    public PQNode deleteMin() { //remove slot 0
        PQNode returnValue = head;
        head = head.next;
        size--;
        return returnValue;
    }

    public int size() { //return size of queue
        return this.size;
    }
}
