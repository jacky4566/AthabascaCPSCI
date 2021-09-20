import java.io.*;
import java.util.Scanner;
import textio.TextIO;

class Control {
    private Control() {
        // only static methods used
    }

    static Action getAction() {
        return new Action(TextIO.getlnString());
    }

    static char getChar(){
        return TextIO.getlnChar();
    }

    static void printFile(String target) {
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
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static String getFromDatabase(String dataSet, String key) {
        String returnValue = null;
        try {
            File myFile = new File(dataSet);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data != null) {// If not empty line
                    if (data.charAt(0) != '#') { // Ignore comment lines
                        String[] parts = { data.substring(0, data.indexOf(":")), data.substring(data.indexOf(":") + 1, data.length()) };
                        parts[1] = parts[1].replace("\\n", "\n");
                        if (parts[0].equalsIgnoreCase(key)) {
                            returnValue = parts[1];
                        }
                    }
                }
            }
            if (returnValue == null) { // obj not found
                returnValue = "String: " + dataSet + ": " + key + " Not Found";
            }
            myReader.close();
        } catch (

        FileNotFoundException e) {
            System.out.println("File Not Found: " + dataSet);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return returnValue;
    }

    static void clearTerminal() {
        for (int i = 0; i < 50; ++i)
            System.out.println();
    }
}