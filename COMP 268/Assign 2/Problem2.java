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
*
* Class: Book
*   Description: 
*    Stores and retrieves information regarding a target book.
*    Target is stored as a set of private strings.
*
*   Constructors:
*    Book(String, String, String, String, String, Int)
*    Use this contrustor when we want to create a new instance and assign all the relavent information. Fields as follows:
*     newtitle
*     newISBN
*     newAuthor
*     newEdition
*     newPublisher
*     newYear
*
*   Book()
*    Use this contrustor when we want to create a new instance with no information assigned. Variables will remain NULL.
* 
*   Methods:
*    String getTitle(): Returns the current value of private variable Title as a String.
*    String getISBN(): Returns the current value of private variable ISBN as a String.
*    String getAuthor(): Returns the current value of private variable Author as a String.
*    String getEdition(): Returns the current value of private variable Edition as a String.
*    String setPublisher(): Returns the current value of private variable Publisher as a String.
*    String getYear(): Returns the current value of private variable Year as a String for easy printing. 
* 
*    setTitle(String): Sets the current value of private variable Title. Accepts a single String value.
*    setISBN(String): Sets the current value of private variable ISBN as a String. Accepts a single String value.
*    setAuthor(String): Sets the current value of private variable Author as a String. Accepts a single String value.
*    setEdition(String): Sets the current value of private variable Edition as a String. Accepts a single String value.
*    setPublisher(String): Sets the current value of private variable Publisher as a String. Accepts a single String value.
*    setYear(int): Sets the current value of private variable Year as a integer. Accepts a single integer value.
*
*    Instance Variables:
*    Private String title: Private variable of type String that stores information about the title of a book.
*    Private String ISBN: Private variable of type String that stores information about the ISBN of a book. 
*    Private String author: Private variable of type String that stores information about the author of a book.
*    Private String edition: Private variable of type String that stores information about the edition of a book.
*    Private String publisher: Private variable of type String that stores information about the publisher of a book.
*    Private int year: Private variable of type integer that stores information about the first year published. Expected value will never exceed 32bit signed.
*
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

    static class Book { //Class of Book
        private String title; //Private variables can only be modified by functions internal to the class
        private String ISBN ;
        private String author;
        private String edition;
        private String publisher;
        private int year;

        public Book(String newtitle, String newISBN, String newAuthor, String newEdition, String newPublisher,
        int newYear) {
            // If the book is given data into the constructor, Store it in the private
            // variables.
            this.title = newtitle;
            this.ISBN = newISBN;
            this.author = newAuthor;
            this.edition = newEdition;
            this.publisher = newPublisher;
            this.year = newYear;
        }

        public Book() {// constructor
            // If no information is given the private variables will remain null.
        }

        //Getters and Setters
        public String getTitle() {
            return title;
        }

        public void setTitle(String newtitle) {
            this.title = newtitle;
        }

        public String getISBN() {
            return ISBN;
        }

        public void setISBN(String newISBN) {
            this.ISBN = newISBN;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String newAuthor) {
            this.author = newAuthor;
        }

        public String getEdition() {
            return edition;
        }

        public void setEdition(String newEdition) {
            this.edition = newEdition;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String newPublisher) {
            this.publisher = newPublisher;
        }

        public String getYear() {
            return Integer.toString(year);
        }

        public void setYear(int newYear) {
            this.year = newYear;
        }
    }

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