
import java.util.Random;

import Model.Student;
import Strategy.OrderByGpa;
import Strategy.OrderByUnits;

import PriorityQueue.PriorityQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


 class TestPriorityQueue {

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

     void populateQueue(PriorityQueue pQueue) { //fills the queue with n students
        for (int i = 0; i < MAX_STUDENTS; ++i)
            pQueue.offer(new Student());
    }

    @Test
     void testPeek() {
        OrderByUnits byUnits = new OrderByUnits();
        PriorityQueue studentQueue = new PriorityQueue(byUnits);
        populateQueue(studentQueue);
        Student maxPriority = new Student("Max", "abc@gmail", 4.0, "12345", 150);
        studentQueue.add(maxPriority);
        assertEquals(maxPriority, studentQueue.peek());
    }

    @Test
     void testAddStudent() {
        // populate queue returns queue with n students
        assertTrue(studentQueue.offer(new Student())); // checks if next insertion is successful
        assertEquals(MAX_STUDENTS + 1, studentQueue.size()); // checks if 'n' students are inserted
    }

    @Test
     void testRemove() {
        int removedStudents = getRand(1, MAX_STUDENTS - 1); // remove 'n' high priority students
        int new_size = MAX_STUDENTS - removedStudents;

        Student maxPriorityStudent = (Student) studentQueue.peek();

        while (removedStudents-- > 0)
            studentQueue.remove();

        assertEquals(new_size, studentQueue.size()); // check if size corresponds to multiple delete operations
        assertFalse(studentQueue.contains(maxPriorityStudent));// check if highest priority element is removed

    }

    @Test
     void testisEmpty() {
        studentQueue.clear();
        assertTrue(studentQueue.isEmpty());
    }


}
