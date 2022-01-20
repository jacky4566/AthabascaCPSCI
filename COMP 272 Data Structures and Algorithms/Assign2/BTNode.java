/**
* title: BTNode.java
* date:  15/01/2022
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* BTNode
*
* Purpose and Description:
*  An object class to contain a single node in our binary tree. Contains searchable data.
*
* Dependent Classes:
*  none
*
* Constructors:
*  BTNode(int value)
*   Constructs a new BTNode with a piece of given data. Child nodes remain null.
*
* Class Variables:
*  private int value
*   The data held in this node. Private so we dont change it.
*
*  int scapeGoatCredits
*   Used to visualize the credit system described in Lemma 8.3
*
*  BTNode left
*   Child node to the left, smaller value.
*
*  BTNode right
*   Child node to the right, larger value.
*
*  BTNode parent
*   Contains the parent node of this object
*
* Public Methods:
*  int getValue( void )
*   Returns the value of the data held within
*
*  Private Methods:
*   None
*/

public class BTNode {
    private int value;
    int scapeGoatCredits;
    BTNode left;
    BTNode right;
    BTNode parent;

    BTNode(int value) {
        this.value = value;
        this.scapeGoatCredits = 0;
        this.right = null;
        this.left = null;
        this.parent = null;
    }

    public int getValue (){
        //The value is kept private so we can't change it accidentally
        return this.value;
    }
}
