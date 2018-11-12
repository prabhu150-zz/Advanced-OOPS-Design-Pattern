package memento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import turtle.Point;
import turtle.TurtleDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MementoTest {

    private TurtleDemo turtle;
    private Originator originator;
    private Caretaker caretaker;

    @BeforeEach
    void setUp() {
        turtle = new TurtleDemo();
        originator = new Originator();
        caretaker = new Caretaker();
    }

    void saveState(TurtleDemo turtle) {
        originator.set(new TurtleDemo(turtle)); // creates new object with current state
        caretaker.addMemento(originator.storeInMemento()); // caretaker stores the memento in list
    }

    TurtleDemo revert(int currentState) {   // reverts to a state corresponding to argument
        Memento pastState = caretaker.getMemento(currentState); // retrieves memento
        return (TurtleDemo) originator.restoreFromMemento(pastState);
        // returns a turtle object with the state restored by originator
    }

    @Test
    void testGetSavedState() {

        int xDistance = 15, turnDegrees = 90, yDistance = 30, currentState = 0;

        turtle.move(xDistance); // turtle at 15,0. 0 degrees
        saveState(turtle); // state: 0

        currentState++;
        turtle.turn(turnDegrees); // turtle at 15,0. 90 degrees
        saveState(turtle); // state: 1

        currentState++;
        turtle.move(yDistance); // turtle at 15,30 90 degrees penUp
        saveState(turtle); // state: 2

        currentState++;
        turtle.penDown(); // pen is now Down
        saveState(turtle); // state: 3

        TurtleDemo pastTurtle = revert(--currentState);

        assertEquals(pastTurtle.location().to_String(), new Point(xDistance, yDistance).to_String());
        assertEquals(pastTurtle.orientation(), turnDegrees);
        assertTrue(pastTurtle.isPenUp());
        // reverted back to previous state

        pastTurtle = revert(1); // reverting back to a specific state 1 (15,0) 90 degrees
        assertEquals(pastTurtle.location().to_String(), new Point(xDistance, 0).to_String());
        assertEquals(pastTurtle.orientation(), 90);

    }
}