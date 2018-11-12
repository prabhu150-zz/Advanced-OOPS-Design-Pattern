
import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.Random;

import Command.AddCommand;
import Command.Command;
import Command.Processor;
import Command.RemoveCommand;
import Model.Student;
import Strategy.OrderByBoth;
import Strategy.OrderByGpa;
import Strategy.OrderByUnits;

import PriorityQueue.PriorityQueue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TestCommand {

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
    void testAdd()
    {
        Processor processor = new Processor();

        int initialsize = studentQueue.size(), currentsize= 0;

        Student firstStudent = new Student();
        new AddCommand(studentQueue,processor,firstStudent).execute();

        Student secondStudent = new Student();
        new AddCommand(studentQueue,processor,secondStudent).execute();

        currentsize = studentQueue.size();
        assertEquals(initialsize + 2, currentsize); // 2 elements added
        assertTrue(studentQueue.contains(firstStudent)&& studentQueue.contains(secondStudent)); // both students present

        while(!processor.empty())
        {
            processor.pop().undo();
        }
        currentsize = studentQueue.size();

        assertEquals(initialsize, currentsize); // 2 elements added
        assertFalse(studentQueue.contains(firstStudent)&& studentQueue.contains(secondStudent)); // both students present

    }

    @Test
    void testRemove()
    {
        Processor processor = new Processor();

        int initialsize = studentQueue.size(), currentsize;

        Student firstStudent = (Student) studentQueue.peek(); // get max priority student
        new RemoveCommand(studentQueue,processor).execute();

        Student secondStudent = (Student) studentQueue.peek();  // get second max priority student
        new RemoveCommand(studentQueue,processor).execute();

        currentsize = studentQueue.size();

        assertEquals(initialsize - 2, currentsize); // 2 elements removed
        assertTrue(!studentQueue.contains(firstStudent)&& !studentQueue.contains(secondStudent)); // both students absent

        while(!processor.empty())
        {
            processor.pop().undo();
        }
        currentsize = studentQueue.size();

        assertEquals(initialsize, currentsize); // 2 elements added
        assertTrue(studentQueue.contains(firstStudent)&& studentQueue.contains(secondStudent)); // both students present

    }

    @Test
    void testBoth()
    {
        Processor processor = new Processor();

        Student removedStudent = (Student) studentQueue.peek(); // get max priority student
        new RemoveCommand(studentQueue,processor).execute();

        Student firstStudent = new Student();
        new AddCommand(studentQueue,processor,firstStudent).execute();

        Student secondStudent = new Student();
        new AddCommand(studentQueue,processor,secondStudent).execute();

        int initialsize = studentQueue.size(), currentsize;

        // initial state contains 2 new students after the max priority student was removed

        processor = new Processor(); // new Processor to avoid considering earlier commands

        int random_inserts = getRand(1,MAX_STUDENTS), random_deletes = getRand(1,random_inserts);

        for (int i = 0; i < random_inserts; i++) {
            new AddCommand(studentQueue,processor,new Student()).execute();
        }

        for (int i = 0; i < random_deletes; i++) {
            new RemoveCommand(studentQueue,processor).execute();
        }

        assertEquals(studentQueue.size(),initialsize + random_inserts - random_deletes); // ensures all commands worked

        while(!processor.empty())
        {
            processor.pop().undo(); // undoing every command!
        }

        currentsize = studentQueue.size();
        assertEquals(initialsize,currentsize);
        assertTrue(studentQueue.contains(firstStudent)&& studentQueue.contains(secondStudent)); // both students present
        assertFalse(studentQueue.contains(removedStudent));
    }



}
