import Node.SinglyLinkedListNode;

/**
 * Implement Queue using LinkedList
 *
 * 1)	Linked List has nodes which has data and next pointer.
 2)	The head of the Linked List is the front of the queue and the tail of the Linked List is the rear of the queue.
 3)	We cannot make tail as the front because for removing we will have to traverse the Linked List.

 Time Complexity: O(1)
 */
public class QueueUsingLinkedList {

    private static SinglyLinkedListNode head = null;
    private static SinglyLinkedListNode tail = null;

    /**
     * Add at rear
     * @param x - Add x at the rear of queue
     */
    private static void enqueue(int x) {
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(x);

        //If queue is empty then the new node is head and tail
        if (tail == null) {

            head = tail = newNode;
            return;
        }

        //Add new node at the end of the queue and change tail
        tail.next = newNode;
        tail = newNode;
    }

    /**
     * Removes the node from the front
     * @return
     */
    private static SinglyLinkedListNode dequeue() {

        //If queue is empty return null
        if (head == null) {
            return null;
        }

        SinglyLinkedListNode removedNode = head;

        head = head.next;

        if (head == null) { //only one element
            tail = null;
        }
        return removedNode;
    }

    private static void displayLinkedList() {
        SinglyLinkedListNode currentNode = head;

        while (currentNode != null) {
            System.out.print("--> " + currentNode.data);
            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args) {

        enqueue(10);
        enqueue(20);
        dequeue();
        dequeue();
        enqueue(30);
        enqueue(40);
        enqueue(50);
        dequeue();
        displayLinkedList();
    }
}
