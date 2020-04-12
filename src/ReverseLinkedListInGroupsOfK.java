import Node.SinglyLinkedListNode;

/**
 * Reverse linked list in group of given size k.
 *
 * Given linked list and number k, write an algorithm to reverse linked list in groups of size k.
 *
 * Example:
 * 1 --> 2 --> 3 --> 4 --> 5 --> 6 --> null
 *
 * K = 3;
 *
 * 3 --> 2 --> 1 --> 6 --> 5 --> 4 --> null
 *
 * Approach:
 *
 * 1) Reverse first k nodes of the linked list.
 * 2) The kth node will be newHead
 * 3) Make a recursive call to the rest of the list and attach it to the last node.
 *
 * resources/ReverseLinkedListInGroupOfK.png
 *
 * 1) Take 3 nodes currentNode, previousNode, nextNode
 * 2) currentNode = head, previousNode = null and nextNode = null
 * 3) while currentNode != null and k > 0
 * 4) At the end set head = previousNode
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n/k) recursive call stack
 * If k = 1 then its O(n)
 */
public class ReverseLinkedListInGroupsOfK {

    private static SinglyLinkedListNode head = null;

    private static SinglyLinkedListNode reverseLinkedListInGroupOfK (SinglyLinkedListNode head, int k) {

        SinglyLinkedListNode currentNode = head;
        SinglyLinkedListNode previousNode = null;
        SinglyLinkedListNode nextNode = null;
        int x = k;

        while (currentNode != null && x > 0) {

            nextNode = currentNode.next;

            //This is reversing the link
            currentNode.next = previousNode;

            //Now move to next node
            previousNode = currentNode;
            currentNode = nextNode;
            x--;
        }

        if (nextNode != null) { //For remaining list
            head.next = reverseLinkedListInGroupOfK(nextNode, k);
        }

        //Return the new head.
        return previousNode;

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

        addAtBegin(8);
        addAtBegin(7);
        addAtBegin(6);
        addAtBegin(5);
        addAtBegin(4);
        addAtBegin(3);
        addAtBegin(2);
        addAtBegin(1);

        System.out.print("Original Link List 1 : ");
        displayLinkedList(head);
        System.out.println();

        SinglyLinkedListNode node = reverseLinkedListInGroupOfK(head, 3);

        System.out.println("Reversed Linked List: ");
        displayLinkedList(node);
    }
}
