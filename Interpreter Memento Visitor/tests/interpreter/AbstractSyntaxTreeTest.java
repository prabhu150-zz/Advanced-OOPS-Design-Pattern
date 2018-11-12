package interpreter;

import common.FileHandlerDemo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import turtle.TurtleDemo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractSyntaxTreeTest {

    Context context;
    AbstractSyntaxTree syntaxTree;
    ConstantExpression distance, orientation;
    FileHandlerDemo fileHandler;

    @BeforeEach
    void setUp() {
        context = new Context(new TurtleDemo());
        syntaxTree = new AbstractSyntaxTree(context);
        distance = new ConstantExpression(20);
        orientation = new ConstantExpression(90);
        fileHandler = new FileHandlerDemo();
    }

    private void performInterpret(String allCommands) {
        Parser parse = new Parser(context, syntaxTree, allCommands);
        parse.execute();
        syntaxTree.interpretTree();
    }

    @Test
    void testAST() throws IOException {

        fileHandler.getNextFile("repeat.txt"); // tests single repeat
        performInterpret(fileHandler.parseTxtFile());

        assertEquals(syntaxTree.traverseTree(syntaxTree.getROOT()).size(), 7); // tests if all nodes are added to tree
        assertEquals(context.getTurtle().location().to_String(), "22.0 66.0"); // tests if nodes are added and interpreted in correct order
        assertEquals(context.getTurtle().orientation(), 90);
    }

}