import Node.BinaryTreeNode;
import Node.SinglyLinkedListNode;

/**
 * Reverse the given linked list.
 *
 * Input: Linked List
 * Output: Reverse Linked List
 *
 * Input: --> 30 --> 25 --> 20 --> 15 --> 10 --> 5
 * Reversed: --> 5 --> 10 --> 15 --> 20 --> 25 --> 30 -->
 *
 * Approach: Iterative
 *
 * 1) Take 3 nodes currentNode, previousNode and nextNode
 * 2) Initialize them as currentNode = head, previousNode = null, and nextNode = null
 * 3) Now keep reversing the pointers one by one till currentNode != null
 * 4) At the end set head = previousNode
 *
 * resources/ReverseLinkedList.png
 */
public class ReverseLinkedList {

    private static SinglyLinkedListNode head = null;

    private static void reverseLinkedListIterative(SinglyLinkedListNode head) {

        SinglyLinkedListNode currentNode = head;
        SinglyLinkedListNode previousNode = null;
        SinglyLinkedListNode nextNode = null;

        while(currentNode != null) {

            nextNode = currentNode.next;

            currentNode.next = previousNode;

            previousNode = currentNode;
            currentNode = nextNode;
        }

        head = previousNode;
        System.out.println("Reversed Linked List: ");
        displayLinkedList(head);
    }

    private static void addAtBegin(int data) {
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        newNode.next = head;
        head = newNode;
    }

    private static void displayLinkedList(SinglyLinkedListNode head) {

        SinglyLinkedListNode currentNode = head;

        while (currentNode != null) {
            System.out.print("--> " + currentNode.data);
            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args) {

        addAtBegin(5);
        addAtBegin(10);
        addAtBegin(15);
        addAtBegin(20);
        addAtBegin(25);
        addAtBegin(30);

        System.out.print("Original Link List 1 : ");
        displayLinkedList(head);
        System.out.println();

        reverseLinkedListIterative(head);

    }
}
