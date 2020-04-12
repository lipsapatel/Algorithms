import Node.SinglyLinkedListNode;

/**
 * Given a linked list with next and child pointer, flatten a list so that it can
 * be read using the next pointer.
 *
 * resources/FlattenAList.png
 *
 * BFS
 * Use queue. Add all child to the queue.
 * Once you reach null, deque and point that element to deque element.
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Approach:
 *
 * 1) Keep track of tail node
 * 2) As soon as you see the child, append it to the tail node
 * 3) Calculate new tail from that child.
 *
 * You always find the tail from the next child. This will maintain O(n) time complexity.
 * If you always find the tail from head, then it’s O(n^2) time complexity.
 *
 * Time Complexity: O(2n) - one pass to find the tail, one pass to iterate the nodes
 * Space Complexity: O(1)
 */
public class FlattenAList {

    private static void flattenAList(SinglyLinkedListNode head) {
        SinglyLinkedListNode tail = getTail(head);

        SinglyLinkedListNode current = head;
        while(current != null) {
            if(current.child != null) {
                tail.next = current.child;
                tail = getTail(current.child);
            }
            current = current.next;
        }
    }

    private static SinglyLinkedListNode getTail(SinglyLinkedListNode head) {
        SinglyLinkedListNode tail = head;
        while(tail.next != null) {
            tail = tail.next;
        }
        return tail;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode head = new SinglyLinkedListNode(1);

        SinglyLinkedListNode two = new SinglyLinkedListNode(2);
        SinglyLinkedListNode three = new SinglyLinkedListNode(3);
        SinglyLinkedListNode four = new SinglyLinkedListNode(4);
        SinglyLinkedListNode five = new SinglyLinkedListNode(5);
        SinglyLinkedListNode six = new SinglyLinkedListNode(6);
        SinglyLinkedListNode seven = new SinglyLinkedListNode(7);
        SinglyLinkedListNode eight = new SinglyLinkedListNode(8);
        SinglyLinkedListNode nine = new SinglyLinkedListNode(9);
        SinglyLinkedListNode ten = new SinglyLinkedListNode(10);
        SinglyLinkedListNode eleven = new SinglyLinkedListNode(11);
        SinglyLinkedListNode twelve = new SinglyLinkedListNode(12);
        SinglyLinkedListNode thirteen = new SinglyLinkedListNode(13);

        head.next = two;
        two.child = five;
        head.next.next = three;
        head.next.next.next = four;
        four.child = seven;

        five.next = six;
        five.child = ten;

        seven.next = eight;
        seven.child = eleven;
        eight.next = nine;
        eight.child = twelve;
        twelve.next = thirteen;

        flattenAList(head);

        //Print flatten list
        SinglyLinkedListNode current = head;
        while(current != null) {
            System.out.print(current.data + "-->");
            current = current.next;
        }
    }
}
