/**
* title: Question3.java
* date: 19/01/2022
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Question 3
*
* Purpose and Description:
*   Showcase a scapegoat tree that has implimented the credit system described in Lemma 8.3
*
* Compiling and running instructions
*  Compile:  javac *.java 
*  Run:      java Question3.java
*
* Dependent Classes:
*  scapeGoatTree.java
*
*/

/**
* Test Plan
*/
/**
* Run the application in terminal. No User input required.
* Expected static result:

Demo of a Scape Goat tree

Adding Data 1 Added at Depth: 0
(Data, Credits)
(1, 0)

Adding Data 5 Added at Depth: 1
(Data, Credits)
(1, 1)
|R--(5, 1)

Adding Data 2 Added at Depth: 2
(Data, Credits)
(1, 2)
|R--(5, 2)
    |L--(2, 2)

Adding Data 4 Added at Depth: 3
(Data, Credits)
(1, 3)
|R--(5, 3)
    |L--(2, 3)
        |R--(4, 3)

Adding Data 3 Added at Depth: 4
depth exceeded, find scapegoat
(Data, Credits)
(1, 4)
|R--(4, 4)
    |L--(3, 4)
    |    |L--(2, 4)
    |R--(5, 4)

Done

/*
**
* Learning Sources
**/
/** 
* Class text and resources
* https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
*/
public class Question3 {
    public static void main(String[] args) {
        System.out.println("Demo of a Scape Goat tree\n"); 

        //Create our tree and add some numbers, print the tree each time
        scapeGoatTree chompy = new scapeGoatTree();
        chompy.addData(1); 
        chompy.printTree();

        chompy.addData(5);
        chompy.printTree();

        chompy.addData(2);
        chompy.printTree();

        chompy.addData(4);
        chompy.printTree();
        
        chompy.addData(3);
        chompy.printTree();

        System.out.println("Done");
    }

}