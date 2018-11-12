
import java.util.Iterator;
import java.util.Random;

import Model.Student;
import Strategy.OrderByBoth;
import Strategy.OrderByGpa;
import Strategy.OrderByUnits;

import PriorityQueue.PriorityQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;



 class TestStrategy {

    private PriorityQueue studentQueue;
    private final int MAX_TESTS = 1000, MAX_STUDENTS = getRand(3, MAX_TESTS), root = 0;


    @BeforeEach
    void setUp() {
        OrderByGpa byGpa = new OrderByGpa();
        studentQueue = new PriorityQueue(byGpa);
        populateQueue(studentQueue);
    }

    private int getRand(int low, int high) // random element between low,high inclusive
    {
        Random rand = new Random();
        return rand.nextInt(high - low) + low;
    }

    private void populateQueue(PriorityQueue pQueue) { //fills the queue with n students
        for (int i = 0; i < MAX_STUDENTS; ++i)
            pQueue.offer(new Student());
    }

    @Test
     void testGpaOrdering() {

        studentQueue.clear();
        studentQueue.offer(new Student("Jake", "abc@gmail", 3.2, "12345", 120));
        studentQueue.offer(new Student("Edward", "abc@gmail", 3.7, "12345", 120));
        studentQueue.offer(new Student("Steve", "abc@gmail", 2.6, "12345", 120));
        studentQueue.offer(new Student("Jim", "abc@gmail", 3.4, "12345", 120));

        String expectedOrder = "/Edward//Jim//Jake//Steve/", actualOrder = "";

        while (!studentQueue.isEmpty())
            actualOrder += "/" + ((Student) studentQueue.poll()).getName() + "/";

        assertEquals(expectedOrder, actualOrder);
    }

    @Test
     void testUnitsOrdering() {
        OrderByUnits byUnits = new OrderByUnits();
        PriorityQueue studentQueue = new PriorityQueue(byUnits);

        studentQueue.offer(new Student("Jake", "abc@gmail", 3.0, "12345", 110));
        studentQueue.offer(new Student("Edward", "abc@gmail", 3.0, "12345", 135));
        studentQueue.offer(new Student("Steve", "abc@gmail", 3.0, "12345", 102));
        studentQueue.offer(new Student("Jim", "abc@gmail", 3.0, "12345", 108));

        String expectedOrder = "/Edward//Jake//Jim//Steve/", actualOrder = "";

        while (!studentQueue.isEmpty())
            actualOrder += "/" + ((Student) studentQueue.poll()).getName() + "/";

        assertEquals(expectedOrder, actualOrder);
        ;

    }

    @Test
     void testBoth() {
        OrderByBoth byBoth = new OrderByBoth();
        PriorityQueue studentQueue = new PriorityQueue(byBoth);

        studentQueue.offer(new Student("Jake", "abc@gmail", 3.6, "12345", 110));
        studentQueue.offer(new Student("Edward", "abc@gmail", 3.8, "12345", 135));
        studentQueue.offer(new Student("Steve", "abc@gmail", 3.0, "12345", 102));
        studentQueue.offer(new Student("Jim", "abc@gmail", 3.2, "12345", 108));

        String expectedOrder = "/Edward//Jake//Jim//Steve/", actualOrder = "";

        while (!studentQueue.isEmpty())
            actualOrder += "/" + ((Student) studentQueue.poll()).getName() + "/";

        assertEquals(expectedOrder, actualOrder);
        ;

    }

    @Test
//		@Test(expected = IndexOutOfBoundsException.class)
     void testIterator() {
        int size = 0;
        Iterator itr = studentQueue.iterator();

        while (itr.hasNext()) {
            size++;
            itr.next();
        }
        assertEquals(size, studentQueue.size()); // checks if iterator covered all elements

        studentQueue.clear();
        assertFalse(itr.hasNext());
    }

}
