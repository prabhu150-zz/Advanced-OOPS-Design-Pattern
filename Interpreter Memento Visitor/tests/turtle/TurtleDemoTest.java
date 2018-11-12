package turtle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurtleDemoTest {

    TurtleDemo turtle;

    @BeforeEach
    void setUp() {
        double x = 10, y = 20;
        turtle = new TurtleDemo(new Point(x, y));
    }

    @Test
    void isPenUp() {
        turtle.penUp();
        assertTrue(turtle.isPenUp());
        turtle.penDown();
        assertFalse(turtle.isPenUp());
    }

    @Test
    void defaultAndCopyConstrutors() {
        turtle = new TurtleDemo();
        assertEquals("0.0 0.0", turtle.location().to_String());

        TurtleDemo turtleCopy = new TurtleDemo(turtle);
        assertEquals(turtleCopy.location().to_String(), turtle.location().to_String());
        assertEquals(turtleCopy.orientation(), turtle.orientation());
    }


    @Test
    void testMoveAndTurn() {
        turtle.turn(30);
        turtle.move(15);
        Point point = turtle.location();
        assertEquals("22.99 27.5", point.to_String());
        assertEquals(turtle.orientation(), 30);
    }
}