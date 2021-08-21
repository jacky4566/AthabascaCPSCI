/**
* title: Bookshelf.java
* description: Creates an ArrayList of books and sorts it
* date: 21/08/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Problem1
*
* Purpose and Description:
* Creates an ArrayList of books and sorts it. No user input required
*
* Compiling and running instructions
* Compile:  javac Bookshelf.java
* Run:      java Bookshelf
*
* Classes:
*/

/**
* Test Plan
*/
/**
* Run the application.
* EXPECTED:
* Static test:
*

Book Name:      BILLY SUMMERS
Book ISBN:      123
Book Author:    Stephen King
Book Edition:   first
Book Publisher: Charles Scribner's Sons
Book Year:      2021

Book Name:      VORTEX
Book ISBN:      123
Book Author:    Catherine Coulter
Book Edition:   second
Book Publisher: VORTEX
Book Year:      2009

Book Name:      IT ENDS WITH US
Book ISBN:      123
Book Author:    Colleen Hoover
Book Edition:   first
Book Publisher: somePublisher
Book Year:      2021

Book Name:      THE LAST THING HE TOLD ME
Book ISBN:      123
Book Author:    Laura Dave
Book Edition:   first
Book Publisher: junk records
Book Year:      2021

Book Name:      PEOPLE WE MEET ON VACATION
Book ISBN:      123
Book Author:    Emily Henry
Book Edition:   first
Book Publisher: newPublisher
Book Year:      2018

Book Name:      AMERICAN MARXISM
Book ISBN:      123
Book Author:    Mark R. Levin
Book Edition:   third
Book Publisher: steve
Book Year:      2009

Book Name:      THE LONG SLIDE
Book ISBN:      123
Book Author:    Tucker Carlson
Book Edition:   fifth
Book Publisher: newPublisher
Book Year:      1999

Book Name:      I ALONE CAN FIX IT
Book ISBN:      123
Book Author:    Carol Leonnig and Philip Rucker
Book Edition:   1
Book Publisher: newPublisher
Book Year:      2018

Book Name:      GIANNIS
Book ISBN:      123
Book Author:    Mirin Fader
Book Edition:   first
Book Publisher: garbage can
Book Year:      2018

Book Name:      ATitle
Book ISBN:      123
Book Author:    myAuthor
Book Edition:   3
Book Publisher: Coffe house
Book Year:      2018

Book Name:      ATitle
Book ISBN:      123
Book Author:    someAuthor
Book Edition:   34
Book Publisher: 0xPublishing
Book Year:      2017

Book Name:      Zebra
Book ISBN:      123
Book Author:    Jackson Wiebe
Book Edition:   first
Book Publisher: newPublisher
Book Year:      2018

Sort the list

Book Name:      AMERICAN MARXISM
Book ISBN:      123
Book Author:    Mark R. Levin
Book Edition:   third
Book Publisher: steve
Book Year:      2009

Book Name:      ATitle
Book ISBN:      123
Book Author:    someAuthor
Book Edition:   34
Book Publisher: 0xPublishing
Book Year:      2017

Book Name:      ATitle
Book ISBN:      123
Book Author:    myAuthor
Book Edition:   3
Book Publisher: Coffe house
Book Year:      2018

Book Name:      BILLY SUMMERS
Book ISBN:      123
Book Author:    Stephen King
Book Edition:   first
Book Publisher: Charles Scribner's Sons
Book Year:      2021

Book Name:      GIANNIS
Book ISBN:      123
Book Author:    Mirin Fader
Book Edition:   first
Book Publisher: garbage can
Book Year:      2018

Book Name:      I ALONE CAN FIX IT
Book ISBN:      123
Book Author:    Carol Leonnig and Philip Rucker
Book Edition:   1
Book Publisher: newPublisher
Book Year:      2018

Book Name:      IT ENDS WITH US
Book ISBN:      123
Book Author:    Colleen Hoover
Book Edition:   first
Book Publisher: somePublisher
Book Year:      2021

Book Name:      PEOPLE WE MEET ON VACATION
Book ISBN:      123
Book Author:    Emily Henry
Book Edition:   first
Book Publisher: newPublisher
Book Year:      2018

Book Name:      THE LAST THING HE TOLD ME
Book ISBN:      123
Book Author:    Laura Dave
Book Edition:   first
Book Publisher: junk records
Book Year:      2021

Book Name:      THE LONG SLIDE
Book ISBN:      123
Book Author:    Tucker Carlson
Book Edition:   fifth
Book Publisher: newPublisher
Book Year:      1999

Book Name:      VORTEX
Book ISBN:      123
Book Author:    Catherine Coulter
Book Edition:   second
Book Publisher: VORTEX
Book Year:      2009

Book Name:      Zebra
Book ISBN:      123
Book Author:    Jackson Wiebe
Book Edition:   first
Book Publisher: newPublisher
Book Year:      2018

*
* ACTUAL:
* Output as expected above
*
* Good data cases:
*   No user input

* Bad data cases:
*   No user input

*/

import java.util.ArrayList; // import the ArrayList class
import java.util.Collections;

public class Bookshelf {

    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>(); // Create an ArrayList object
        books.add(new Book("BILLY SUMMERS",      "123",       "Stephen King",         "first",    "Charles Scribner's Sons", 2021)); //Add book 1
        books.add(new Book("VORTEX",             "123",       "Catherine Coulter",    "second",   "VORTEX",                  2009)); //Add book 2
        books.add(new Book("IT ENDS WITH US",    "123",       "Colleen Hoover",       "first",    "somePublisher",           2021)); //Add book 3
        books.add(new Book("THE LAST THING HE TOLD ME", "123", "Laura Dave",          "first",    "junk records",            2021)); //Add book 4
        books.add(new Book("PEOPLE WE MEET ON VACATION", "123", "Emily Henry",        "first",    "newPublisher",            2018)); //Add book 5
        books.add(new Book("AMERICAN MARXISM",   "123",       "Mark R. Levin",        "third",    "steve",                   2009)); //Add book 6
        books.add(new Book("THE LONG SLIDE",     "123",       "Tucker Carlson",       "fifth",    "newPublisher",            1999)); //Add book 7
        books.add(new Book("I ALONE CAN FIX IT", "123",       "Carol Leonnig and Philip Rucker",  "1", "newPublisher",       2018)); //Add book 8
        books.add(new Book("GIANNIS",            "123",       "Mirin Fader",          "first",    "garbage can",             2018)); //Add book 9
        books.add(new Book("ATitle",             "123",       "myAuthor",             "3",        "Coffe house",             2018)); //Add book 10
        books.add(new Book("ATitle",             "123",       "someAuthor",           "34",       "0xPublishing",            2017)); //Add book 11
        books.add(new Book("Zebra",              "123",       "Jackson Wiebe",        "first",    "newPublisher",            2018)); //Add book 12

        //print out the list in the order of entry
        for (Book target : books) {
            target.display();
        }

        //Sort the array
        System.out.println("Sort the list");
        Collections.sort(books);
        System.out.println();

        //print out the sorted list
        for (Book target : books) {
            target.display();
        }
    }
}
