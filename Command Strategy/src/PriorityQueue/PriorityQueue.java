package PriorityQueue;

import Command.AddCommand;
import Command.RemoveCommand;
import Strategy.PriorityStrategy;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class PriorityQueue extends AbstractQueue implements Iterable {

    private ArrayList elementList;
    private final int MAX_ELEMENT = 0;
    private int size;
    private PriorityStrategy orderStrategy;


    public PriorityQueue(PriorityStrategy orderByX) {
        size = 0;
        elementList = new ArrayList();
        orderStrategy = orderByX;

    }

    private void createHeap() {
        for (int i = (size - 2) / 2; i >= 0; i--) // Apply heap for parents(non-leaf) only
            maxHeapify(i, size);
    }

    private void maxHeapify(int index, int size) {

        int largest = index, leftchild = 2 * index + 1, rightchild = 2 * index + 2;

        if (leftchild < size && orderStrategy.comparePriority(elementList.get(leftchild), elementList.get(largest)))
            largest = leftchild;

        else if (rightchild < size && orderStrategy.comparePriority(elementList.get(rightchild), elementList.get(largest)))
            largest = rightchild;

        if (largest != index) {
            swap(index, largest);
            maxHeapify(largest, size);
        }
    }

    private void checkChildren() {
        createHeap();
        int temporary = size; //changing global size has implications for remove operation
        while (temporary > 1) {
            swap(0, temporary - 1); // recursively swap parents with children
            temporary--; // reduce size when each parent gets fixed in place
            maxHeapify(0, temporary); // re-apply heap property for children postswap
        }
    }

    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }


    @Override
    public void clear() {
        while (!isEmpty())
            remove();
    }

    @Override
    public Spliterator spliterator() {
        return null;
    }

    @Override
    public Stream stream() {
        return null;
    }

    @Override
    public Stream parallelStream() {
        return null;
    }

    private void swap(int firstindex, int secondindex) { //swaps two objects by index
        Object swap = elementList.get(firstindex);
        elementList.set(firstindex, elementList.get(secondindex));
        elementList.set(secondindex, swap);
    }

    @Override
    public Object peek() {
        return elementList.get(MAX_ELEMENT);
    }


    public boolean removeElement(Object element) {
        boolean removed = elementList.remove(element);
        size--;
        checkChildren();
        return removed;
    }

    @Override
    public Object remove() {

        if (isEmpty())
            throw new IndexOutOfBoundsException();
        Object pop = elementList.get(MAX_ELEMENT);
        elementList.set(MAX_ELEMENT, elementList.get(--size)); // replace root element with the lowest priority heap element
        elementList.remove(size); // remove the last heap element after replacement; O(1) since index is specified
        checkChildren();
        return pop;
    }

    @Override
    public boolean add(Object element) {
        size++;
        boolean element_inserted = elementList.add(element);
        if (element_inserted)
            checkChildren();
        return element_inserted;
    }

    @Override
    public boolean offer(Object element) {
        return this.add(element);
    }

    @Override
    public Object poll() {
        return this.remove();
    }

    @Override
    public Iterator iterator() {
        return new PriorityQueueIterator();
    }

    @Override
    public void forEach(Consumer consumer) {

    }

    @Override
    public boolean removeIf(Predicate predicate) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    private class PriorityQueueIterator implements Iterator {

        private int pqIndex = 0;

        PriorityQueueIterator() {
            pqIndex = 0;
        }

        @Override
        public Object next() {
            return elementList.get(pqIndex++);
        }

        @Override
        public boolean hasNext() {
            return pqIndex < size;
        }

    }

}






