import Node.SinglyLinkedListNode;

/**
 * Delete node in the middle of the linked list, given an access only to that node.
 *
 * Example:
 *
 * Original List:
 * 1 --> 2 --> 8 --> 3 --> 7 --> 0 --> 4
 *
 * After deleting middle node say 7:
 * 1 --> 2 --> 8 --> 3 --> 0 --> 4
 *
 * Input: A linked list and access to the node that needs to be deleted.
 * Output: Linked list with deleted node.
 *
 * Approach:
 *
 * 1) Copy the value of next node to the node to be deleted.
 * 2) Change the next pointer to node.next.next.
 *
 * resources/DeleteNodeInMiddleOfLinkedList.png
 */
public class DeleteNodeInMiddleOfLinkedList {

    private static SinglyLinkedListNode head = null;

    private static void deleteNodeInMiddleOfLinkedList(SinglyLinkedListNode middle) {

        //If it's the last node, cannot be deleted.
        if (middle.next == null) {
            return;
        }

        SinglyLinkedListNode currentNode = middle;

        currentNode.data = currentNode.next.data;
        currentNode.next = currentNode.next.next;
    }

    private static void addAtBegin(SinglyLinkedListNode newNode) {

        //SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        newNode.next = head;
        head = newNode;
    }

    private static void display(SinglyLinkedListNode head) {

        SinglyLinkedListNode currentNode = head;

        while(currentNode != null) {
            System.out.print(currentNode.data + " --> ");
            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args) {

        addAtBegin(new SinglyLinkedListNode(4));
        addAtBegin(new SinglyLinkedListNode(0));
        SinglyLinkedListNode middle = new SinglyLinkedListNode(7);
        addAtBegin(middle);
        addAtBegin(new SinglyLinkedListNode(3));
        addAtBegin(new SinglyLinkedListNode(8));
        addAtBegin(new SinglyLinkedListNode(2));
        addAtBegin(new SinglyLinkedListNode(1));

        System.out.println("Original List: ");

        display(head);

        System.out.println();
        deleteNodeInMiddleOfLinkedList(middle);

        System.out.println("Linked List after deleting:");
        display(head);
    }
}
