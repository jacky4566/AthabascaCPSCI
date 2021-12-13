/**
* title: Node.java
* date: 12/12/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* PQNode class
*
* Purpose and Description:
*  A worker class for Priority Queue. Holds target data and information about next item in linked list.
*  Any other data about the objects being prioritized could be added here.
*
* Compiling and running instructions
*  See Parent file Assign1PQ.java
*
* Dependent Classes:
*  none
*
*  Constructors:
    PQNode(int d) 
     Initialize with given priority. Lower numbers mean higher priority.
    
*  Methods:
    None

*  Class Variables:
    Int priority
     Priority of this object

    PQNode next
     Holds pointer to next linked list item
*/

public class PQNode { //create an object of node with our data
    int priority;
    PQNode next;

    PQNode(int d) { //initialize with given data
        priority = d;
    }
}