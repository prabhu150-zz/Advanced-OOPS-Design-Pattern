package visitor;


import common.FileHandlerDemo;
import interpreter.*;
import memento.Caretaker;
import memento.Memento;
import memento.Originator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import turtle.Point;
import turtle.TurtleDemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class VisitorTests {

    private FileHandlerDemo fileHandler;
    private Context context;
    private AbstractSyntaxTree syntaxTree;
    private DistanceVisitor distanceVisitor;
    private ProgramVisitor programVisitor;
    private Originator originator;
    private Caretaker caretaker;

    @BeforeEach
    void setUp() {
        context = new Context(new TurtleDemo());
        syntaxTree = new AbstractSyntaxTree(context);
        fileHandler = new FileHandlerDemo();
        distanceVisitor = new DistanceVisitor(context);
        programVisitor = new ProgramVisitor(context);
        originator = new Originator();
        caretaker = new Caretaker();
    }

    TurtleDemo revert(Memento currentState) {   // reverts to a state corresponding to argument
        return (TurtleDemo) originator.restoreFromMemento(currentState);
        // returns a turtle object with the state restored by originator
    }


    Memento saveState(TurtleDemo turtle) {
        originator.set(new TurtleDemo(turtle)); // creates new object with current state
        Memento currentState = originator.storeInMemento();
        caretaker.addMemento(currentState); // caretaker stores the memento in list
        return currentState;
    }

    void performInterpret(String allCommands) {
        Parser parse = new Parser(context, syntaxTree, allCommands);
        parse.execute();
        syntaxTree.interpretTree();
    }

    void visitAST(Visitor visitor) {
        ArrayList<StatementNode> visitedNodes = syntaxTree.traverseTree(syntaxTree.getROOT());
        for (StatementNode currentNode : visitedNodes) {
            Expression someExpression = currentNode.getExpression();
            someExpression.accept(visitor);
        }
    }

    @Test
    void testDistanceCalculator() throws IOException {

        fileHandler.getNextFile("repeat.txt");
        performInterpret(fileHandler.parseTxtFile());
        visitAST(distanceVisitor);
        assertEquals(distanceVisitor.getTotalDistance(), 88.0);

        setUp();
        fileHandler.getNextFile("reassign.txt");
        performInterpret(fileHandler.parseTxtFile());
        visitAST(distanceVisitor);
        assertEquals(distanceVisitor.getTotalDistance(), 55.0);

        setUp();
        fileHandler.getNextFile("multiRepeat.txt");
        performInterpret(fileHandler.parseTxtFile());
        visitAST(distanceVisitor);
        assertEquals(distanceVisitor.getTotalDistance(), 11.0);

        setUp();
        fileHandler.getNextFile("repeat_reassign.txt");
        performInterpret(fileHandler.parseTxtFile());
        visitAST(distanceVisitor);
        assertEquals(distanceVisitor.getTotalDistance(), 6.0);
    }

    @Test
    void testProgramVisitor() throws IOException {

        fileHandler.getNextFile("repeat.txt");

        Parser parse = new Parser(context, syntaxTree, fileHandler.parseTxtFile());
        parse.execute(); // creates a syntax tree ready for interpreting

        visitAST(programVisitor); // before the repeat loop we have a turtle at (22,66) 90 degrees penDown

        Vector<Memento> mementos = programVisitor.getMementos();

        Memento expectedState = saveState(new TurtleDemo(new Point(22, 0))); // state of the turtle in the middle of program

        TurtleDemo expectedTurtle = revert(expectedState);
        TurtleDemo actualTurtle = revert(mementos.get(3));

        assertEquals(expectedTurtle.location().to_String(), actualTurtle.location().to_String());
        assertEquals(actualTurtle.orientation(), 90);
        assertFalse(actualTurtle.isPenUp());

        expectedState = saveState(new TurtleDemo(new Point(22, 22)));

        expectedTurtle = revert(expectedState);
        actualTurtle = revert(mementos.get(5)); // at this pt we r inside repeat block iteration 1, (22,22) 90 degrees, penDown

        assertEquals(expectedTurtle.location().to_String(), actualTurtle.location().to_String());
        assertEquals(actualTurtle.orientation(), 90);
        assertFalse(actualTurtle.isPenUp());

    }
}