/**
* title: BTNodeNum.java
* date:  15/01/2022
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* BTNodeNum
*
* Purpose and Description:
*  An object class to contain a single node in our binary tree. Contains searchable data.
*  For this version also contains a number position for the relavent search order.
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
*  int preOrderNumber
*   Contains the position of this node in a pre-order search
*
*  int inOrderNumber
*   Contains the position of this node in a in-order search
*
*  int postOrderNumber
*   Contains the position of this node in a post-order search
*
*  BTNode left
*   Child node to the left, smaller value.
*
*  BTNode right
*   Child node to the right, larger value.
*
* Public Methods:
*  int getValue( void )
*   Returns the value of the data held within
*
*  Private Methods:
*   None
*/

public class BTNodeNum {
    private int value;
    int preOrderNumber;
    int inOrderNumber;
    int postOrderNumber;
    BTNodeNum left;
    BTNodeNum right;

    BTNodeNum(int value) {
        this.value = value;
        this.preOrderNumber = -1;
        this.inOrderNumber = -1;
        this.postOrderNumber = -1;
        right = null;
        left = null;
    }

    public int getValue (){
        //The value is kept private so we can't change it accidentally
        return this.value;
    }
}
