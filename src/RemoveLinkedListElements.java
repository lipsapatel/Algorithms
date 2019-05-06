import Node.SinglyLinkedListNode;

/**
 * Remove all elements from a linked list of integers that have value val.

 Example:

 Input:  1->2->6->3->4->5->6, val = 6
 Output: 1->2->3->4->5

 TC: O(n)
 */
public class RemoveLinkedListElements {

    private static SinglyLinkedListNode removeElements(SinglyLinkedListNode head, int val) {

        SinglyLinkedListNode current = head;
        SinglyLinkedListNode prev = null;

        while(current != null) {

            if (current.data == val) {
                if (prev != null) {
                    prev.next = current.next;
                } else {
                    head = current.next;
                }
            } else {
                prev = current;
            }
            current = current.next;
        }
        return head;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode head = new SinglyLinkedListNode(1);
        head.next = new SinglyLinkedListNode(2);
        head.next.next = new SinglyLinkedListNode(6);
        head.next.next.next = new SinglyLinkedListNode(3);
        head.next.next.next.next = new SinglyLinkedListNode(4);
        head.next.next.next.next.next = new SinglyLinkedListNode(5);
        head.next.next.next.next.next.next = new SinglyLinkedListNode(6);

        SinglyLinkedListNode afterRemovingHead = removeElements(head, 6);
        SinglyLinkedListNode current = afterRemovingHead;
        while(current != null) {
            System.out.print(current.data + "-->");
            current = current.next;
        }
    }
}
