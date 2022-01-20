/**
* title: Question1.java
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
*   Impliment a Binary tree and demonstrate worst-case running times for each implementation of
*   pre-order traversal, post-order traversal, and in-order traversal.
*
* Compiling and running instructions
*  Compile:  javac *.java 
*  Run:      java Question1.java
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

Demo of Binary Tree Traversal

Add some data that should result in worst case Pre Order search
Adding Data: 1  loops: 1        Tree Size: 1
Adding Data: 2  loops: 2        Tree Size: 2
Adding Data: 4  loops: 3        Tree Size: 3
Adding Data: 8  loops: 4        Tree Size: 4
Adding Data: 9  loops: 5        Tree Size: 5
Perform a Pre Order search for 9
Item Found 9    loops: 5        Tree Size: 5

Start a new tree
Add some data that should result in worst case In Order search
Adding Data: 9  loops: 1        Tree Size: 1
Adding Data: 8  loops: 2        Tree Size: 2
Adding Data: 7  loops: 3        Tree Size: 3
Adding Data: 6  loops: 4        Tree Size: 4
Adding Data: 0  loops: 5        Tree Size: 5
Perform a In Order search for 0
Looking at: 9
Looking at: 8
Looking at: 7
Looking at: 6
Looking at: 0
Item Found 0    loops: 5        Tree Size: 5

Start a new tree
Add some data that should result in worst case Post Order search
Adding Data: 5  loops: 1        Tree Size: 1
Adding Data: 4  loops: 2        Tree Size: 2
Adding Data: 6  loops: 2        Tree Size: 3
Adding Data: 3  loops: 3        Tree Size: 4
Adding Data: 7  loops: 3        Tree Size: 5
Perform a Post Order search for 5
Item Found 5    loops: 5        Tree Size: 5
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

public class Question1 {
    public static void main(String[] args) {
        System.out.println("Demo of Binary Tree Traversal\n"); 

        BinaryTree oakTree = new BinaryTree();
        System.out.println("Add some data that should result in worst case Pre Order search"); 
        oakTree.addData(1);
        oakTree.addData(2);
        oakTree.addData(4);
        oakTree.addData(8);
        oakTree.addData(9);

        System.out.println("Perform a Pre Order search for 9"); 
        oakTree.preOrderNext(9);

        System.out.println("\nStart a new tree"); 
        BinaryTree walnutTree = new BinaryTree();
        System.out.println("Add some data that should result in worst case In Order search"); 
        walnutTree.addData(9);
        walnutTree.addData(8);
        walnutTree.addData(7);
        walnutTree.addData(6);
        walnutTree.addData(0);

        System.out.println("Perform a In Order search for 0"); 
        walnutTree.inOrderNext(0);

        System.out.println("\nStart a new tree"); 
        BinaryTree birchTree = new BinaryTree();
        System.out.println("Add some data that should result in worst case Post Order search"); 
        birchTree.addData(5);
        birchTree.addData(4);
        birchTree.addData(6);
        birchTree.addData(3);
        birchTree.addData(7);

        System.out.println("Perform a Post Order search for 5"); 
        birchTree.postOrderNext(5);

        System.out.println("Done");
    }

}