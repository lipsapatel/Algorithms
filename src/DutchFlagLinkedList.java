import Node.SinglyLinkedListNode;

/**
 * Given linked list of values and int x
 *
 * Move all values < x to left
 * Move all values equal to x in middle
 * Move all values > x to right
 *
 * Approach:
 *
 * 1) Iterate the linked list
 * 2) Maintain 3 pointers. Add dummy head to all those 3 pointer
 * 3) Append values to the end.
 * 4) Join the three pointers.
 * 5) Return the head of first pointer.
 * 6) We use three dummy pointers, to avoid many null checks
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class DutchFlagLinkedList {

    private static SinglyLinkedListNode sortLinkedList(SinglyLinkedListNode head, int x) {
        SinglyLinkedListNode head1 = new SinglyLinkedListNode(-1);
        SinglyLinkedListNode head2 = new SinglyLinkedListNode(-1);
        SinglyLinkedListNode head3 = new SinglyLinkedListNode(-1);

        //Maintain the tail to keep appending to the tail
        SinglyLinkedListNode tail1 = head1;
        SinglyLinkedListNode tail2 = head2;
        SinglyLinkedListNode tail3 = head3;

        SinglyLinkedListNode current = head;

        while(current != null) {
            if(current.data < x) {
                tail1.next = current;
                tail1 = tail1.next;
            } else if (current.data == x) {
                tail2.next = current;
                tail2 = tail2.next;
            } else {
                tail3.next = current;
                tail3 = tail3.next;
            }
            current = current.next;
        }

        //Join 3 list
        tail1.next = (head2.next != null) ? head2.next : head3.next;
        tail2.next = head3.next;
        tail3.next = null;

        return head1.next;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode head = new SinglyLinkedListNode(1);
        head.next = new SinglyLinkedListNode(2);
        head.next.next = new SinglyLinkedListNode(0);
        head.next.next.next = new SinglyLinkedListNode(1);

        SinglyLinkedListNode result = sortLinkedList(head, 1);

        while(result != null) {
            System.out.println(result.data + "-->");
            result = result.next;
        }
    }
}
