package common;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileHandlerDemo {

    final String path = System.getProperty("user.dir") + "//src//";
    private File inputfile;
    private Scanner input;

    public String parseTxtFile() {
        StringBuilder nextLine = new StringBuilder();
        try {
            while (true) {
                String str = input.nextLine();
                nextLine.append(str + "\n");
            }
        } catch (NoSuchElementException e) {
        }
        return nextLine.toString(); //returns string with each command seperated by new line
    }

    public void getNextFile(String filename) throws IOException {
        inputfile = new File(path + filename);
        input = new Scanner(inputfile);
        input.useDelimiter("\\s+");
    }
}
