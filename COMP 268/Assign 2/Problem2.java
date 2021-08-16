/**
* title: Problem2.java
* description: Creates several instances of class Book then prints out the information stored within. Program demonstrates use of clases and different constructors.
* date: 13/08/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Problem2
*
* Purpose and Description:
*   Creates several instances of class Book then prints out the information stored within. 
*   Program demonstrates use of clases and different constructors.
*
*
* Compiling and running instructions
*   Compile:  javac Problem2.java
*   Run:      java Problem2.java
*
* Classes:
*  Book.java
*/

/**
* Test Plan
*/
/**
* Run the application.
* EXPECTED:
*   Terminal Output as follows:
     Book 1 Title: An Astronauts Guide to Life on Earth
     Book 1 ISBN: 9780316253017        
     Book 1 Author: Chris Hadfield     
     Book 1 Edition: first
     Book 1 Publisher: Back Bay Books  
     Book 1 Year: 2015

     Book 2 Title: The Martian
     Book 2 ISBN: 0804139024
     Book 2 Author: Andy Weir
     Book 2 Edition: First
     Book 2 Publisher: Ballantine Books
     Book 2 Year: 2014
* ACTUAL:
*    GUI frame displays as expected
*
* Good data cases:
*   No outside data expected
*
* Bad data cases:
*   No outside data expected
*/

public class Problem2 {

    public static void main(String[] args) {
        Book book1 = new Book(); //create a new instance of book with no variables.
        //assign some variables
        book1.setTitle("An Astronauts Guide to Life on Earth");
        book1.setISBN("9780316253017");
        book1.setAuthor("Chris Hadfield");
        book1.setEdition("first");
        book1.setPublisher("Back Bay Books");
        book1.setYear(2015);

        Book book2 = new Book("The Martian", "0804139024", "Andy Weir", "First", "Ballantine Books", 2014); //create a new instance of book with variables assigned in the constructor.

        //Print out the books information
        System.out.println("Book 1 Title: " + book1.getTitle());
        System.out.println("Book 1 ISBN: " + book1.getISBN());
        System.out.println("Book 1 Author: " + book1.getAuthor());
        System.out.println("Book 1 Edition: " + book1.getEdition());
        System.out.println("Book 1 Publisher: " + book1.getPublisher());
        System.out.println("Book 1 Year: " + book1.getYear());

        System.out.println(); //add some space

        System.out.println("Book 2 Title: " + book2.getTitle());
        System.out.println("Book 2 ISBN: " + book2.getISBN());
        System.out.println("Book 2 Author: " + book2.getAuthor());
        System.out.println("Book 2 Edition: " + book2.getEdition());
        System.out.println("Book 2 Publisher: " + book2.getPublisher());
        System.out.println("Book 2 Year: " + book2.getYear());
    }
}