import Node.SinglyLinkedListNode;

/**
 * Reverse alternate 'K' nodes in a linked list.
 *
 * Example:
 *
 * 1 --> 2 --> 3 --> 4 --> 5 --> 6 --> 7 --> 8
 *
 * k = 2
 *
 * 2 --> 1  --> 3 --> 4 --> 6 --> 5 --> 7 -->8
 *
 * Approach:
 *
 * 1) Solution will be similar to "Reverse Linked List in groups of size K"
 * 2) Take 'k' nodes for a iteration.
 * 3) Call rest of them recursively
 * 4) In alternate iterations:
 *    Reverse 'k' nodes
 *    Skip the 'k' nodes
 * 5) We use boolean variable to identify when to reverse or skip nodes.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n/k)
 */
public class ReverseAlternateKNodesInLinkedList {

    private static SinglyLinkedListNode head = null;

    private static SinglyLinkedListNode reverseAlternateKNodesInLinkedList(SinglyLinkedListNode head, int k, boolean needReverse) {

        int x = k;
        SinglyLinkedListNode previousNode = null;
        SinglyLinkedListNode currentNode = head;
        SinglyLinkedListNode nextNode = null;

        //Check if needReverse = true then we will reverse first k nodes
        if (needReverse) {

            while(x > 0 && currentNode != null) {

                nextNode = currentNode.next;

                //This is reversing link
                currentNode.next = previousNode;

                //Now move to next node
                previousNode = currentNode;
                currentNode = nextNode;
                x--;
            }

            if (currentNode != null) { //For remaining list
                head.next = reverseAlternateKNodesInLinkedList(currentNode, k, false);
            }

            //Return new head
            return previousNode;
        } else { //check if needReverse = false then we will not reverse the next k nodes

            //This is to return
            SinglyLinkedListNode previousNodeToReturn = currentNode;

            //Just move forward using next
            while(x > 1 && currentNode != null) {

                currentNode = currentNode.next;
                x--;
            }

            //Here it should be currentNode.next instead of head.next
            if (currentNode.next != null) {
                currentNode.next = reverseAlternateKNodesInLinkedList(currentNode.next, k, true);
            }
            return previousNodeToReturn;
        }
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

        addAtBegin(12);
        addAtBegin(11);
        addAtBegin(10);
        addAtBegin(9);
        addAtBegin(8);
        addAtBegin(7);
        addAtBegin(6);
        addAtBegin(5);
        addAtBegin(4);
        addAtBegin(3);
        addAtBegin(2);
        addAtBegin(1);

        System.out.println("Original Linked List: ");
        displayLinkedList(head);

        System.out.println();

        SinglyLinkedListNode node = reverseAlternateKNodesInLinkedList(head, 3, true);

        System.out.println("Reversed Linked List: ");
        displayLinkedList(node);

        System.out.println();
    }
}
