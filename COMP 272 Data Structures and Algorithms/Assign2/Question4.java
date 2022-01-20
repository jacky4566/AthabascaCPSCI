/**
* title: Question4.java
* date: 15/01/2022
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Question 4
*
* Purpose and Description:
*   Impliment a hasing table to demostrate colision handling with a linear probing technique. 
*
* Compiling and running instructions
*  Compile:  javac *.java 
*  Run:      java Question4.java
*
* Dependent Classes:
*  HashTable.java
*
*/

/**
* Test Plan
*/
/**
* Run the application in terminal. No User input required.
* Expected static result:
Demo of hash table collision

Adding Int: 1 added to slot: 1
Adding Int: 5 added to slot: 5
Adding Int: 21 added to slot: 8
Adding Int: 26 added to slot: 0
Collsion! Trying new slot: 1
Collsion! Trying new slot: 2
Adding Int: 39 added to slot: 2
Collsion! Trying new slot: 2
Collsion! Trying new slot: 3
Adding Int: 14 added to slot: 3
Collsion! Trying new slot: 3
Collsion! Trying new slot: 4
Adding Int: 15 added to slot: 4
Collsion! Trying new slot: 4
Collsion! Trying new slot: 5
Collsion! Trying new slot: 6
Adding Int: 16 added to slot: 6
Collsion! Trying new slot: 5
Collsion! Trying new slot: 6
Collsion! Trying new slot: 7
Adding Int: 17 added to slot: 7
Collsion! Trying new slot: 6
Collsion! Trying new slot: 7
Collsion! Trying new slot: 8
Collsion! Trying new slot: 9
Adding Int: 18 added to slot: 9
Collsion! Trying new slot: 7
Collsion! Trying new slot: 8
Collsion! Trying new slot: 9
Collsion! Trying new slot: 10
Adding Int: 19 added to slot: 10
Collsion! Trying new slot: 8
Collsion! Trying new slot: 9
Collsion! Trying new slot: 10
Collsion! Trying new slot: 11
Adding Int: 20 added to slot: 11
Collsion! Trying new slot: 8
Collsion! Trying new slot: 9
Collsion! Trying new slot: 10
Collsion! Trying new slot: 11
Collsion! Trying new slot: 12
Adding Int: 111 added to slot: 12
Collsion! Trying new slot: 3
Collsion! Trying new slot: 4
Collsion! Trying new slot: 5
Collsion! Trying new slot: 6
Collsion! Trying new slot: 7
Collsion! Trying new slot: 8
Collsion! Trying new slot: 9
Collsion! Trying new slot: 10
Collsion! Trying new slot: 11
Collsion! Trying new slot: 12
Collsion! Trying new slot: 0
Collsion! Trying new slot: 1
Collsion! Trying new slot: 2
Table full: Item dropped
Collsion! Trying new slot: 4
Collsion! Trying new slot: 5
Collsion! Trying new slot: 6
Collsion! Trying new slot: 7
Collsion! Trying new slot: 8
Collsion! Trying new slot: 9
Collsion! Trying new slot: 10
Collsion! Trying new slot: 11
Collsion! Trying new slot: 12
Collsion! Trying new slot: 0
Collsion! Trying new slot: 1
Collsion! Trying new slot: 2
Collsion! Trying new slot: 3
Table full: Item dropped
Done

/*
**
* Learning Sources
**/
/** 
* Class text and resources
*/
public class Question4 {
    public static void main(String[] args) {
        System.out.println("Demo of hash table collision\n"); 

        HashTable knights = new HashTable();

        knights.add(1);
        knights.add(5);
        knights.add(21);
        knights.add(26);
        knights.add(39);
        knights.add(14);
        knights.add(15);
        knights.add(16);
        knights.add(17);
        knights.add(18);
        knights.add(19);
        knights.add(20);
        knights.add(111);
        knights.add(145);
        knights.add(146);

        System.out.println("Done");
    }

}