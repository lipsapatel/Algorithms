import Node.SinglyLinkedListNode;

/**
 * Print Linked list using recursion
 * Time Complexity: O(n)
 */
public class PrintLinkedListUsingRecursion {

    private static SinglyLinkedListNode head = null;

    private static void insertAtStart(int data) {
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        newNode.next = head;
        head = newNode;
    }

    private static void printLinkedListUsingRecursion(SinglyLinkedListNode currentNode) {

        //Base Case
        if (currentNode == null) {
            return;
        } else { //Recursive Case
            System.out.print(currentNode.data + "-->");
            printLinkedListUsingRecursion(currentNode.next);
        }
    }

    public static void main(String[] args) {
        insertAtStart(12);
        insertAtStart(7);
        insertAtStart(5);
        insertAtStart(2);
        printLinkedListUsingRecursion(head);
    }
}
