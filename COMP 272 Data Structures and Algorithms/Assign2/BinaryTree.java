/**
* title: BinaryTree.java
* date:  15/01/2022
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* BinaryTree
*
* Purpose and Description:
*  Impliments a binary tree data structure with methods for adding and searching the tree.
*  Counter variables were added to demonstrate run times.
*  Pre-Order: root left right
*  In-Order: left root right
*  Post-Order: left right root
*
* Dependent Classes:
*  BTNode.java
*
* Constructors:
*  None
*
* Class Variables:
*  BTNode root
*   Contains the root of our binary tree
*
*  int loopCounter
*   Holds a temporary variable to count the number of recurssive loops our functions perform
*
*  int treeSize
*   Holds the current size of our tree. Primarily to demonstrate search times. 
*
*  int treeMin
*   Holds the smallest value in our tree.
*
*  int treeMax
*   Holds the largest value in our tree.
*
* Public Methods:
*  void addData( int )
*   Adds a new piece of data to the binary tree of type Integer. Uses addRecursive().
*
*  boolean isValid( void )
*   Searches the tree and verifies every node is correctly ordered relative to each other node.
*
*  void preOrderNext( int )
*   Searches the binary tree with a Pre-Order traversal (root, left, right). 
*   Expect a search variable. Uses preOrderRecursive()
*
*  void inOrderNext( int )
*   Searches the binary tree with a In- Order traversal (left, root, right). 
*   Expect a search variable. Uses inOrderRecursive()
*
*  void postOrderNext( int )
*   Searches the binary tree with a Post-Order traversal (left, right, root). 
*   Expect a search variable. Uses postOrderRecursive()
*
* Private Methods:
*  boolean isValidRecursive(BTNode target, int min, int max)
*   Checks if the tree is valid. Is each node min-1 or max+1 of its parents. 
*
*  BTNode addRecursive( BTNode, int )
*   A recursive function to add new data into our binary tree. Will return the root of our tree.
*
*  boolean preOrderRecursive( BTNode, int)
*   A recursive function to search our tree with a pre-order traversal. Returns boolean if the object was found.
*
*  boolean inOrderRecursive( BTNode, int)
*   A recursive function to search our tree with a in-order traversal. Returns boolean if the object was found.
*
*  boolean postOrderRecursive( BTNode, int)
*   A recursive function to search our tree with a post-order traversal. Returns boolean if the object was found.
*/

public class BinaryTree {
    private BTNode root = null;
    
    private int loopCounter = 0;
    private int treeSize = 0;
    private int treeMin = 0;
    private int treeMax = 0;

    public void addData(int newData) {
        //Start a recursive function to add the new data
        loopCounter = 0;
        root = addRecursive(root, newData);
        System.out.println("Adding Data: " + String.format("%3d", newData) + " \tloops: " + String.format("%3d", loopCounter) + " \tTree Size: " + String.format("%3d", treeSize));  
    }

    private BTNode addRecursive(BTNode current, int newData) {
        loopCounter++;
        //Current pointer is empty, put data here
        if (current == null) {
            treeSize++;
            if (newData < treeMin){
                treeMin = newData;
            }
            if (newData > treeMax){
                treeMax = newData;
            }
            return new BTNode(newData);
        }
        //Traverse tree until we find our spot
        if (newData < current.getValue()) {
            current.left = addRecursive(current.left, newData);
        } else if (newData > current.getValue()) {
            current.right = addRecursive(current.right, newData);
        } else {
            // value already exists
            return current;
        }
        return current;
    }

    public boolean isValid(){
        return isValidRecursive(root, treeMin, treeMax);
    }

    public boolean isValidRecursive(BTNode target, int min, int max){
        //Tree is empty
        if (target == null){
            return true;
        }

        //Check if this node is within the constraints
        if (target.getValue() < min || target.getValue() > max){
            System.out.println("Verifed false: " + min + " < " + target.getValue() + " < " + max); 
            return false;
        }else{
            System.out.println("Verifed true: " + min + " < " + target.getValue() + " < " + max); 
        }

        //Then we can check its children shrinking our constraints current target +/- 1
        return (isValidRecursive(target.left, min, target.getValue()-1) && isValidRecursive(target.right, target.getValue()+1, max));
    }

    public void preOrderNext(int searchValue){
        //Use a recursive function to find our value with a pre-order traversal of BT
        loopCounter = 0;
        if (preOrderRecursive(root, searchValue)){
            System.out.println("Item Found " + searchValue + " \tloops: " + loopCounter + " \tTree Size: " + treeSize);
        }else {
            System.out.println("Item Not Found " + searchValue + " \tloops: " + loopCounter + " \tTree Size: " + treeSize);
        }
    }

    private boolean preOrderRecursive(BTNode current, int searchValue) {
        loopCounter++;
        //If pointer is null the object was not found
        if (current == null) {
            return false;
        } 
        //we found the value
        if (searchValue == current.getValue()) {
            return true;
        } 
        //Keep searching the tree
        if (current.left != null){
            return preOrderRecursive(current.left, searchValue);
        }else{
            return preOrderRecursive(current.right, searchValue);
        }
    }

    public void inOrderNext(int searchValue){
        //Use a recursive function to find our value with a in-order traversal of BT
        loopCounter = 0;
        if (inOrderRecursive(root, searchValue)){
            System.out.println("Item Found " + searchValue + " \tloops: " + loopCounter + " \tTree Size: " + treeSize);
        }else {
            System.out.println("Item Not Found " + searchValue + " \tloops: " + loopCounter + " \tTree Size: " + treeSize);
        }
    }

    private boolean inOrderRecursive(BTNode current, int searchValue) {
        loopCounter++;
        System.out.println("Looking at: " + current.getValue());
        //If pointer is null the object was not found
        if (current == null) {
            return false;
        } 
        if (current.left != null){
            //If the pointer has a left, traverse down
            return inOrderRecursive(current.left, searchValue);
        }
        //we found the value
        if (searchValue == current.getValue()) {
            return true;
        } 
        //else we need to search right
        if (current.right != null){
            inOrderRecursive(current.right, searchValue);
        }
        return false;
    }

    public void postOrderNext(int searchValue){
        //Use a recursive function to find our value with a post order traversal of BT
        loopCounter = 0;
        if (postOrderRecursive(root, searchValue)){
            System.out.println("Item Found " + searchValue + " \tloops: " + loopCounter + " \tTree Size: " + treeSize);
        }else {
            System.out.println("Item Not Found " + searchValue + " \tloops: " + loopCounter + " \tTree Size: " + treeSize);
        }
    }

    private boolean postOrderRecursive(BTNode current, int searchValue) {
        loopCounter++;
        //If pointer is null the object was not found
        if (current == null) {
            return false;
        } 
        if (current.left != null){
            postOrderRecursive(current.left, searchValue);
        } 
        if (current.right != null){
            postOrderRecursive(current.right, searchValue);
        }
        //we found the value
        if (searchValue == current.getValue()) {
            return true;
        } else{
            return false;
        }
    }
}
