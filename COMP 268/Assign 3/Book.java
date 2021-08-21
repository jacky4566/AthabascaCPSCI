/**
* title: Book.java
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
*    int getYear(): Returns the current value of private variable Year as a int.
* 
*    setTitle(String): Sets the current value of private variable Title. Accepts a single String value.
*    setISBN(String): Sets the current value of private variable ISBN as a String. Accepts a single String value.
*    setAuthor(String): Sets the current value of private variable Author as a String. Accepts a single String value.
*    setEdition(String): Sets the current value of private variable Edition as a String. Accepts a single String value.
*    setPublisher(String): Sets the current value of private variable Publisher as a String. Accepts a single String value.
*    setYear(int): Sets the current value of private variable Year as a integer. Accepts a single integer value.
*
*    display(): prints out all the private data in a readable format
*    compareTo(Book): Used by the Comparable methods to sort an arrayList by our defined rules
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

public class Book implements Comparable<Book> { //Class of Book
    private String title; //Private variables can only be modified by functions internal to the class
    private String ISBN;
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

    public int getYear() {
        return year;
    }

    public void setYear(int newYear) {
        this.year = newYear;
    }

    public void display(){ //prints out all the information of this book
        System.out.println("Book Name:      " + this.title);
        System.out.println("Book ISBN:      " + this.ISBN);
        System.out.println("Book Author:    " + this.author);
        System.out.println("Book Edition:   " + this.edition);
        System.out.println("Book Publisher: " + this.publisher);
        System.out.println("Book Year:      " + this.year);
        System.out.println();
    }

    public int compareTo(Book bookCompare){
        int compareResult = this.getTitle().compareTo(bookCompare.getTitle());
        if (compareResult < 0) {  
            return -1;
        }
        else if (compareResult > 0) {
            return 1;
        }
        else {  
            return compare(this.getYear(), bookCompare.getYear());
        }
    }

    public static int compare (int x, int y) {
        return x - y;
    }
}