/**
* title: BinaryTreeNumbers.java
* date:  17/01/2022
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* BinaryTreeNumbers
*
* Purpose and Description:
*  Impliments a binary tree data structure with methods for adding and searching the tree.
*  Pre-Order: root left right
*  In-Order: left root right
*  Post-Order: left right root
*
* Dependent Classes:
*  BTNodeNum.java
*
* Constructors:
*  None
*
* Class Variables:
*  BTNode root
*   Contains the root of our binary tree
*
* int nodeNumberer
*  Global variable used to track the current target node
*
* Public Methods:
*  void addData( int )
*   Adds a new piece of data to the binary tree of type Integer. Uses addRecursive().
*
*  void preOrderNumbers(  )
*   Searches the binary tree with a Pre-Order traversal (root, left, right). 
*   Assigns a number based on the position of this node in the search algorythem
*
*  void inOrderNumbers(  )
*   Searches the binary tree with a In- Order traversal (left, root, right). 
*   Assigns a number based on the position of this node in the search algorythem
*
*  void postOrderNumbers(  )
*   Searches the binary tree with a Post-Order traversal (left, right, root). 
*   Assigns a number based on the position of this node in the search algorythem
*
*  void printTree(  )
*   Prints a graphical representation of the tree
*
* Private Methods:
*  BTNodeNum addRecursive( BTNodeNum, int )
*   A recursive function to add new data into our binary tree. Will return the root of our tree.
*
*  boolean preOrderRecursive( BTNodeNum, int)
*   A recursive function to search our tree with a pre-order traversal. 
*
*  boolean inOrderRecursive( BTNodeNum, int)
*   A recursive function to search our tree with a in-order traversal.
*
*  boolean postOrderRecursive( BTNodeNum, int)
*   A recursive function to search our tree with a post-order traversal.
*
*  String traversePreOrder( BTNodeNum )
*   Used to traverse the tree and print out a graphical represenation
*
*  void traversePreOrder( StringBuilder , String , String , BTNodeNum , boolean )
*   Used to traverse the tree and print out a graphical represenation
*/

public class BinaryTreeNumbers {
    private BTNodeNum root = null;
    private int nodeNumberer;

    public void addData(int newData) {
        //Start a recursive function to add the new data
        root = addRecursive(root, newData);
        System.out.println("Adding Data: " + String.format("%3d", newData));  
    }

    private BTNodeNum addRecursive(BTNodeNum current, int newData) {
        //Current pointer is empty, put data here
        if (current == null) {
            return new BTNodeNum(newData);
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

    public void preOrderNumbers(){
        //Use a recursive function to find our value with a pre-order traversal of BT
        nodeNumberer = 1;
        preOrderRecursive(root);
    }

    private void preOrderRecursive(BTNodeNum current) {
        //If pointer is null the object we can ignore it
        if (current == null) {
            return;
        }  
        current.preOrderNumber = nodeNumberer++;
        preOrderRecursive(current.left);
        preOrderRecursive(current.right);
    }

    public void inOrderNumbers(){
        //Use a recursive function to find our value with a pre-order traversal of BT
        nodeNumberer = 1;
        inOrderRecursive(root);
    }

    private void inOrderRecursive(BTNodeNum current) {
        //If pointer is null the object we can ignore it
        if (current == null) {
            return;
        } 
        inOrderRecursive(current.left);
        current.inOrderNumber = nodeNumberer++;
        inOrderRecursive(current.right);
    }

    public void postOrderNumbers(){
        //Use a recursive function to find our value with a pre-order traversal of BT
        nodeNumberer = 1;
        postOrderRecursive(root);
    }

    private void postOrderRecursive(BTNodeNum current) {
        //If pointer is null the object we can ignore it
        if (current == null) {
            return;
        } 
        postOrderRecursive(current.left);
        postOrderRecursive(current.right);
        current.postOrderNumber = nodeNumberer++;
    }

    public void printTree(){
        //Print out the tree in a readable fashion
        System.out.println("(Value, Pre#, In#, Post#)");
        System.out.println(traversePreOrder(root));
    }

    private String traversePreOrder(BTNodeNum root) {
    //With help from: https://www.baeldung.com/java-print-binary-tree-diagram
        if (root == null) {
            return "";
        }
    
        StringBuilder sb = new StringBuilder();
        sb.append("(" + root.getValue() + "," + root.preOrderNumber + "," + root.inOrderNumber + "," + root.postOrderNumber + ")");
    
        String pointerRight = "|R--";
        String pointerLeft = (root.right != null) ? "|L--" : "|L--";
    
        traverseNodes(sb, "", pointerLeft, root.left, root.right != null);
        traverseNodes(sb, "", pointerRight, root.right, false);
    
        return sb.toString();
    }

    private void traverseNodes(StringBuilder sb, String padding, String pointer, BTNodeNum node, boolean hasRightSibling) {
    //With help from: https://www.baeldung.com/java-print-binary-tree-diagram
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append("(" + node.getValue() + "," + node.preOrderNumber + "," + node.inOrderNumber + "," + node.postOrderNumber + ")");

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("|    ");
            } else {
                paddingBuilder.append("    ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "|R--";
            String pointerLeft = (node.right != null) ? "|L--" : "|L--";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.left, node.right != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.right, false);
        }
    }
}
