package interpreter;

import common.FileHandlerDemo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import turtle.Point;
import turtle.TurtleDemo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class InterpreterTest {

    private FileHandlerDemo fileHandler;
    private Context context;
    private AbstractSyntaxTree syntaxTree;

    @BeforeEach
    void setUp() {
        context = new Context(new TurtleDemo());
        syntaxTree = new AbstractSyntaxTree(context);
        fileHandler = new FileHandlerDemo();
    }

    void performInterpret(String allCommands) {
        Parser parse = new Parser(context, syntaxTree, allCommands);
        parse.execute();
        syntaxTree.interpretTree();
    }

    @Test
    void testInterpreter() throws IOException {

        fileHandler.getNextFile("reassign.txt"); // tests basic syntax with reassignment
        performInterpret(fileHandler.parseTxtFile());
        assertEquals(context.getTurtle().location().to_String(), new Point(22.99, 27.5).to_String());

        setUp();
        fileHandler.getNextFile("repeat.txt"); // tests single repeat
        performInterpret(fileHandler.parseTxtFile());
        assertEquals(context.getTurtle().location().to_String(), new Point(22.0, 66.0).to_String());
        assertEquals(context.getTurtle().orientation(), 90);
        assertTrue(context.getTurtle().isPenUp());

        setUp();
        fileHandler.getNextFile("multiRepeat.txt"); // tests multiple repeat
        performInterpret(fileHandler.parseTxtFile());
        assertEquals(context.getTurtle().location().to_String(), new Point(0.0, -1.0).to_String());

        setUp();
        fileHandler.getNextFile("repeat_reassign.txt"); // tests multiple repeat with reassignment
        performInterpret(fileHandler.parseTxtFile());
        assertEquals(context.getTurtle().location().to_String(), new Point(10.0, 0.0).to_String());
    }
}