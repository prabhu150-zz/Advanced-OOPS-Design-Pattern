//import interpreter.Parser;
//import turtle.TurtleDemo;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.PrintWriter;
//import java.util.NoSuchElementException;
//import java.util.Scanner;
//
//
///**
// * @author aprabhu2187
// */
//public class DesignDemo {
//
//
//    private static void printToFile(String msg, PrintWriter output) {
//        System.out.println(msg);
//        output.println(msg);
//    }
//
//    public static void main(String args[]) throws FileNotFoundException {
//
//        final String path = System.getProperty("user.dir") + "//src//";
//        File inputfile, outputfile;
//        Scanner input;
//        PrintWriter output;
//
//        inputfile = new File(path + "reassign.txt");
//        input = new Scanner(inputfile);
//        input.useDelimiter("\\s+");
//
//        int ops;
//        StringBuilder nextLine = new StringBuilder();
//
//        try {
//            while (true) {
//                String str = input.nextLine();
//                nextLine.append(str + "\n");
//            }
//        } catch (NoSuchElementException e) {
//        }
//
//        TurtleDemo turtle = new TurtleDemo();
//        Parser context = new Parser(turtle, nextLine.toString());
//        context.interpret();
//
//        System.out.println("Final Location " + turtle.location().to_String());
//
//
//    }
//}