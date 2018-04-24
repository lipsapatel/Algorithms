import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Deque Implementation
 *
 * Java Deque Interface
 *
 * 1) It's a linear collection.
 * 2) The Deque interface is subtype of Util.Queue interface.
 * 3) Deque is "Double Ended Queue" means it support insertion and removal of data from both ends
 * So it can be used as Stack or Queue.
 *
 * Declaration

 public interface Deque extends Queue

 Methods:

 Return Type	Method	Description

 boolean	add(E e)	 Insert element at the tail of this deque

 void	addFirst(E e)	inserts the specified element at the front of this deque.

 void	addLast(E e)	Inserts the specified element at the end of this deque

 boolean	offerFirst(E e)	Inserts the specified element at the front of this deque

 boolean	offerLast(E e)	Inserts the specified element at the end of this deque

 E	removeFirst()	Retrieves and removes the first element of this deque

 E	removeLast()	Retrieves and removes the last element of this deque

 E	pollFirst()	Retrieves and removes the first element of this deque, or
 returns null if this deque is empty.

 E	pollLast()	Retrieves and removes the last element of this deque, or
 returns null if this deque is empty.

 E	getFirst()	Retrieves, but does not remove, the first element of this deque.

 E	peekFirst()	Retrieves, but does not remove, the first element of this deque,
 or returns null if this deque is empty.

 E	peekLast()	Retrieves, but does not remove, the last element of this deque, or
 returns null if this deque is empty.

 boolean	removeFirstOccurrence(Object o)	Removes the first occurrence of the specified
 element from this deque. If the deque does not contain the element, it is unchanged.

 boolean	removeLastOccurrence(Object o)	Removes the last occurrence of the
 specified element from this deque. If the deque does not contain the element, it is unchanged.

 boolean	offer(E e)	Inserts the specified element at the tail of this deque

 void	push(E e)	Pushes an element onto the stack represented by this deque

 E	pop()	Pops an element from the stack represented by this deque.
 In other words, removes and returns the first element of this deque.

 Iterator<E>	iterator()	Returns an iterator over the elements in this deque,
 The elements will be returned in order from first (head) to last (tail).

 Iterator<E>	descendingIterator()	Returns an iterator over the elements in this deque
 The elements will be returned in order from last (tail) to first (head).
 */
public class DequeImplementation {

    public static void main(String[] args) {

        Deque<String> deque = new LinkedList<>();

        //Add methods
        System.out.println("Add operations...");

        deque.add("Tail-1");
        deque.addLast("Tail-2");
        deque.offer("Tail-3");
        deque.offerLast("Tail-4");

        deque.addFirst("Head-1");
        deque.offerFirst("Head-2");
        deque.offerFirst("Head-3");

        System.out.println("Deque is: " +  deque);

        //Iterations
        System.out.println("Iterator...");

        Iterator<String> iterator = deque.iterator();

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        //Reverse iterations
        System.out.println("Reverse Iterator...");

        Iterator<String> reverseIterator = deque.descendingIterator();

        while(iterator.hasNext()) {
            System.out.println(reverseIterator.next());
        }

        //Peek
        System.out.println("Peek: " + deque.peek());
        System.out.println("Peek First: " + deque.peekFirst());
        System.out.println("Peek Last: " + deque.peekLast());

        //Push and Pop - Act like Stack
        System.out.println("Stack Operation push");
        deque.push("Head-4");
        System.out.println("Deque " + deque);

        System.out.println("Stack Operation pop");
        deque.pop();
        System.out.println("Deque " + deque);

        //Remove Operations
        System.out.println("Remove Operations...");
        deque.remove();
        deque.removeFirst();
        deque.removeLast();
        System.out.println("Deque " + deque);

        //Contains
        System.out.println("Deque contains AAAA " + deque.contains("AAAA"));
        System.out.println("Deque contains Tail-1 " + deque.contains("Tail-1"));
    }
}
