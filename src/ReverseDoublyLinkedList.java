import Node.DoublyLinkedListNode;

/**
 * Reverse the Doubly Linked List
 *
 * resources/ReverseDoublyLinkedList.png
 *
 * Approach
 *
 * 1) Every node in doubly linked list has next and previous pointer.
 * 2) Do the linear traversal of linked list and keep swapping the next and previous pointer.
 * 3) Make the last pointer as the head of the linked list
 */
public class ReverseDoublyLinkedList {

    private static DoublyLinkedListNode head = null;
    private static DoublyLinkedListNode tail = null;
    private static int size = 0;

    private static DoublyLinkedListNode reverseDoublyLinkedList() {

        DoublyLinkedListNode current = head;
        DoublyLinkedListNode temp = null;

        //Traverse the Doubly Linked List
        while (current != null) {

            //Swap the previous and next pointer
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;

            current = current.prev;
        }
        return temp.prev;
    }

    private static void displayDoublyLinkedList(DoublyLinkedListNode head) {
        DoublyLinkedListNode current = head;

        while (current != null) {
            System.out.print("-->" + current.data);
            current = current.next;
        }
    }

    private static void insertAtBegin(int data) {

        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public static void main(String[] args) {

        insertAtBegin(1);
        insertAtBegin(2);
        insertAtBegin(3);
        insertAtBegin(4);

        displayDoublyLinkedList(head);
        DoublyLinkedListNode newHead = reverseDoublyLinkedList();
        System.out.println("");
        displayDoublyLinkedList(newHead);
    }
}
