/**
* title: TextFileReader.java
* description: A class to read files and store information
* date: 21/08/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* TextFileReaderDemo
*
* Purpose and Description:
* Reads a given file and stors each line in an array for later processing with 2 functions.
*
* Compiling and running instructions
* Compile:  javac TextFileReader.java
* Run:      java TextFileReader
*
* Classes:
*
* Class: TextFileReader
*  Description: Stores a file in string array and has some printing functions
*
*  Constructors:
*   TextFileReader()
*    Does nothing
*
*   TextFileReader(String)
*    Reads a target file, parses each line into a String array.
* 
*  Methods:
*   StringBuffer contents()
*    Returns the contents of the file as a StringBuffer
*
*   display()
*    Prints the contents of the file to the terminal
* 
*  Instance Variables:
*   private String[100]
*    Holds the contents of a file. Each line is stored in an array element of type String.
*
*/

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class TextFileReader {
    private String[] fileLinesArray = new String[100];

    TextFileReader(){ //default contrsuctor
        //does not really do anything
    }

    TextFileReader(String inputFile){ //Contrsuctor with string
        File readingfile = new File(inputFile); // Creation of File Descriptor for input file
        try (Scanner sc = new Scanner(new FileInputStream(readingfile))) { // create a new scanner. must be caught with some exceptions
            int arrayPointer = 0;
            while (sc.hasNext() && arrayPointer < fileLinesArray.length) { // while the stream has more data and we are not exceeding the array size
                fileLinesArray[arrayPointer++] = sc.nextLine(); //read the file line by line and increamen the pointer
            }
        } catch (java.io.FileNotFoundException e) { // expected most common exception. file not found
            System.err.println("File Not Found ");
        } catch (Exception e) { // catch all other exceptions
            System.out.println("Error reading file: " + e);
            e.printStackTrace(); // print the full program stack
        }
    }

    StringBuffer contents(){
        StringBuffer buffer1 = new StringBuffer(); //create a new instance of StringBuffer
        int arrayPointer = 0; //create a new point for our array 
        while(arrayPointer < fileLinesArray.length && fileLinesArray[arrayPointer] != null) { //execture while we are in the bounds of the array and the next character is not null
            buffer1.append(fileLinesArray[arrayPointer++]); //add line to buffer
            buffer1.append("\n"); //Since new lines were stripped in the reading. we add those again
        }
        return buffer1;
    }

    void display(){
        int arrayPointer = 0; //create a new point for our array 
        while(arrayPointer < fileLinesArray.length && fileLinesArray[arrayPointer] != null) { //execture while we are in the bounds of the array and the next character is not null
            System.out.print("line " + String.format("%4d", arrayPointer + 1) + ": " + fileLinesArray[arrayPointer++] + "\n"); //Print the text line
        }
    }
}