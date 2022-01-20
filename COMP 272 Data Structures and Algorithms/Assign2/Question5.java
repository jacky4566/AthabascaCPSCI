/**
* title: Question5.java
* date: 17/01/2022
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Question 5
*
* Purpose and Description:
*   Impliment Binary tree. Show demonstration of traversing the tree while numbering the nodes correctly.
*
* Compiling and running instructions
*  Compile:  javac *.java 
*  Run:      java Question5.java
*
* Dependent Classes:
*  BinaryTreeNumbers.java
*
*/

/**
* Test Plan
*/
/**
* Run the application in terminal. No User input required.
* Expected static result:

Demo of a Binary tree with Assigned number

Create our tree and add some numbers
Adding Data:  10
Adding Data:   9
Adding Data:   5
Adding Data:   8
Adding Data:   2
Adding Data:   1
Adding Data:  22
Adding Data:  13
Adding Data:  16
Adding Data:   3
Adding Data:  24
Traverse the tree, pre-Order, assign numbers
Traverse the tree, in-Order, assign numbers
Traverse the tree, post-Order, assign numbers
Finally, print the entire tree contents
(Value, Pre#, In#, Post#)
(10,1,7,11)
|L--(9,2,6,6)
|    |L--(5,3,4,5)
|        |L--(2,4,2,3)
|        |    |L--(1,5,1,1)
|        |    |R--(3,6,3,2)
|        |R--(8,7,5,4)
|R--(22,8,10,10)
    |L--(13,9,8,8)
    |    |R--(16,10,9,7)
    |R--(24,11,11,9)
Done

/*
**
* Learning Sources
**/
/** 
* Class text and resources
* https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
*/
public class Question5 {
    public static void main(String[] args) {
        System.out.println("Demo of a Binary tree with Assigned number\n"); 

        System.out.println("Create our tree and add some numbers"); 
        BinaryTreeNumbers aspen = new BinaryTreeNumbers();
        aspen.addData(10);
        aspen.addData(9);
        aspen.addData(5);
        aspen.addData(8);
        aspen.addData(2);
        aspen.addData(1);
        aspen.addData(22);
        aspen.addData(13);
        aspen.addData(16);
        aspen.addData(3);
        aspen.addData(24);

        System.out.println("Traverse the tree, pre-Order, assign numbers"); 
        aspen.preOrderNumbers();

        System.out.println("Traverse the tree, in-Order, assign numbers"); 
        aspen.inOrderNumbers();

        System.out.println("Traverse the tree, post-Order, assign numbers"); 
        aspen.postOrderNumbers();

        System.out.println("Finally, print the entire tree contents"); 
        aspen.printTree();

        System.out.println("Done");
    }

}