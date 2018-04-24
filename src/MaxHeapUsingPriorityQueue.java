import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Priority Queue is the built in implementation in java for Min Heap and Max Heap.
 *
 * Elements of priority queue are ordered according to their natural ordering
 * or by a Comparator provided at the queue construction time.
 *
 * A priority queue is an abstract data type where each element has priority
 * assigned to it. So the element with higher priority is served before other elements.
 *
 * Does not permit null.
 *
 * Queue retrieval operations: poll, peek and remove
 * As elements are added to priority queue, it's capacity grows automatically.
 *
 * This implementation is not synchronized.
 * Multiple threads should not access priority queue instance
 * concurrently if any of the thread modifies the queue.
 *
 * Instead use thread safe PriorityBlockQueue class.
 * This implementation provides O(logn) time for enqueuing and dequeuing methods (offer, poll, remove, add)
 *
 * Linear time for remove(Object) and contains(Object) - O(n)
 * O(1) - Constant time for peek, element and size.
 *
 * Return Type                       Method             Description
 *
 * boolean                           offer(E e)         Inserts the specified element into this priority queue - O(logn)
 * E                                 peek()             Retrieves but does not remove the head of this queue - O(1)
 *                                                      Returns null if this queue is empty
 * E                                 poll()             Retrieves and remove the head of this queue - O(logn)
 *                                                      Returns null if this queue is empty
 * int                               size()             Returns the number of elements in this collection - O(1)
 * void                              clear()            Removes all of the elements from this priority queue.
 * boolean                           contains(Object o) Returns true if this queue contains the specified element - O(n)
 * Iterator<E>                       iterator()         Returns an iterator over the elements in this queue.
 * boolean                           remove(Object o)   Removes a single instance of the specified element from this queue,
 *                                                      if it is present.
 * Comparator<? super E>             comparator()       Return comparator used to order elements in this queue
 *                                                      or null if this queue is sorted according to the natural
 *                                                      ordering of elements.
 */
public class MaxHeapUsingPriorityQueue {

    static PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(Collections.reverseOrder());

    private static void insert(int[] array) {

        for (int i = 0; i < array.length; i++) {
            priorityQueue.offer(array[i]);
        }
    }

    private static int peek() {
        return priorityQueue.peek();
    }

    private static int extractMax() {
        return priorityQueue.poll();
    }

    private static int getSize() {
        return priorityQueue.size();
    }

    private static void print() {
        System.out.println(priorityQueue);
    }

    public static void main(String[] args) {

        int[] array = {1, 6, 2, 9, 4, 3, 8};

        System.out.println("Original array: " + Arrays.toString(array));

        insert(array);
        System.out.println("Priority Queue: ");
        print();

        System.out.println("Extract Max: " + extractMax());
        System.out.println("Extract Max: " + extractMax());
        System.out.println("Extract Max: " + extractMax());

        System.out.println("The size of priority queue: " + getSize());
    }
}
