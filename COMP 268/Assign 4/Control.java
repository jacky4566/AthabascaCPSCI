import java.io.*;
import java.util.Scanner;
import textio.TextIO;

public class Control {
    public static final String HELPFILE = "Help.txt";
    public static final String WELCOMEFILE = "Welcome.txt";

    static String getAction(){
        return TextIO.getlnString(); 
    }

    static void printFile(String target){
        try {
            File myFile = new File(target);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {    
              String data = myReader.nextLine();
              System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + target);
            e.printStackTrace();
        } catch (Exception  e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}