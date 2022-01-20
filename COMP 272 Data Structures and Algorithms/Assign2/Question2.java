/**
* title: Question2.java
* date: 15/01/2022
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Question 1
*
* Purpose and Description:
*   Design a recursive linear-time algorithm that tests whether a binary tree satisfies the search tree order property at every node.
*
* Compiling and running instructions
*  Compile:  javac *.java 
*  Run:      java Question2.java
*
* Dependent Classes:
*  BinaryTree.java
*
*/

/**
* Test Plan
*/
/**
* Run the application in terminal. No User input required.
* Expected static result:

Demo of Binary Tree Verification

Add some data
Adding Data: 1  loops: 1        Tree Size: 1
Adding Data: 4  loops: 2        Tree Size: 2
Adding Data: 3  loops: 3        Tree Size: 3
Adding Data: 8  loops: 3        Tree Size: 4
Adding Data: 0  loops: 2        Tree Size: 5
Verifed true: 0 < 1 < 8
Verifed true: 0 < 0 < 0
Verifed true: 2 < 4 < 8
Verifed true: 2 < 3 < 3
Verifed true: 5 < 8 < 8
Verify if our tree is valid: true
Done

/*
**
* Learning Sources
**/
/** 
* Class text and resources
* https://en.wikipedia.org/wiki/Binary_tree
* https://www.baeldung.com/java-binary-tree
*/

public class Question2 {
    public static void main(String[] args) {
        System.out.println("Demo of Binary Tree Verification\n"); 

        BinaryTree oakTree = new BinaryTree();
        System.out.println("Add some data"); 
        oakTree.addData(1);
        oakTree.addData(4);
        oakTree.addData(3);
        oakTree.addData(8);
        oakTree.addData(0);

        System.out.println("Verify if our tree is valid: " + oakTree.isValid()); 

        System.out.println("Done");
    }

}