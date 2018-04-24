import Node.SinglyLinkedListNode;

/**
 * Alternate splitting of a given linked list.
 *
 * Given a singly linked list, split into two linked lists.
 * These linked lists will contain alternate nodes from the the given linked list.
 *
 * resources/AlternateSplittingOfLinkedList.png
 *
 * Approach:
 *
 * 1) Take two pointers headA and headB
 * 2) currentNode = head and secondNode = currentNode.next
 * 3) Do the traversal and set the next node for the first linkedlist and
 * second linkedlist
 */
public class AlternateSplittingOfLinkedList {

    private static SinglyLinkedListNode head = null;
    private static SinglyLinkedListNode headA = null;
    private static SinglyLinkedListNode headB = null;

    private static void alternateSplittingOfLinkedList() {

        SinglyLinkedListNode currentNode = head;

        if (currentNode == null || currentNode.next == null) {
            return; // there's nothing to split
        }

        headA = currentNode;
        headB = currentNode.next;

        while(currentNode != null && currentNode.next != null) {

            //Set next node for first linkedlist
            SinglyLinkedListNode secondNode = currentNode.next;
            currentNode.next = secondNode.next;

            //Set the next node for the second linkedlist
            if (currentNode.next != null && currentNode.next.next != null) {
                secondNode.next = currentNode.next.next;
            } else {
                secondNode.next = null;
                return;
            }

            //Keep moving forward
            currentNode = currentNode.next;
        }
    }

    private static void displayLinkedList(SinglyLinkedListNode head) {
        SinglyLinkedListNode currentNode = head;

        while(currentNode != null) {
            System.out.print("-->" + currentNode.data);
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        head = new SinglyLinkedListNode(1);
        head.next = new SinglyLinkedListNode(2);
        head.next.next = new SinglyLinkedListNode(1);
        head.next.next.next = new SinglyLinkedListNode(2);
        head.next.next.next.next = new SinglyLinkedListNode(1);
        head.next.next.next.next.next = new SinglyLinkedListNode(2);

        displayLinkedList(head);
        alternateSplittingOfLinkedList();
        displayLinkedList(headA);
        displayLinkedList(headB);
    }
}
