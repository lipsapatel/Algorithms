import Node.SinglyLinkedListNode;

/**
 * Merge a linked list into another Linked List at alternate positions.
 *
 * If second list has extra nodes, print them as well.
 *
 * Example:
 *
 * 5 --> 10 --> 15 --> 20 --> 25 --> null
 * 3 --> 6 --> 9 --> 12 --> 15 --> 18 --> 21 --> null
 *
 * Output:
 *
 * 5 --> 3 --> 10 --> 6 --> 15 --> 9 --> 20 --> 12 --> 25 --> 15 --> null
 * Remaining List:
 * 18 --> 21 --> null
 *
 * Approach:
 *
 * 1) NodeA and NodeB are the head of two linked list
 * 2) Temp = nodeA
 * 3) aNext = nodeA.next
 * bNext = nodeB.next
 * 4) nodeA.next = nodeB ( A points to B)
 * 5) nodeB.next = aNext
 * 6) Do the above two steps until the list burns out.
 * 7) Print the list using the temp node
 * 8) Print the remaining list in nodeB, nodeB will be pointing to the head of the
 * remaining list
 */
public class MergeLinkedListAtAlternatePositions {

    private static void mergeLinkedListAtAlternatePositions(SinglyLinkedListNode a, SinglyLinkedListNode b) {

        SinglyLinkedListNode temp = a; //Save the head of the new linked list

        while (a != null && b != null) {

            SinglyLinkedListNode aNext = a.next;
            SinglyLinkedListNode bNext = b.next;

            a.next = b;
            b.next = aNext;

            a = aNext;
            b = bNext;
        }

        System.out.println("Alternate merged linked list");
        displayLinkedList(temp);
        System.out.println("Remaining Second List");
        displayLinkedList(b);
    }

    private static void displayLinkedList(SinglyLinkedListNode head) {
        SinglyLinkedListNode currentNode = head;

        while (currentNode != null) {
            System.out.print("-->" + currentNode.data);
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        SinglyLinkedListNode a = new SinglyLinkedListNode(5);
        a.next = new SinglyLinkedListNode(10);
        a.next.next = new SinglyLinkedListNode(15);
        a.next.next.next = new SinglyLinkedListNode(20);
        a.next.next.next.next = new SinglyLinkedListNode(25);

        displayLinkedList(a);
        System.out.println("");

        SinglyLinkedListNode b = new SinglyLinkedListNode(3);
        b.next = new SinglyLinkedListNode(6);
        b.next.next = new SinglyLinkedListNode(9);
        b.next.next.next = new SinglyLinkedListNode(12);
        b.next.next.next.next = new SinglyLinkedListNode(15);
        b.next.next.next.next.next = new SinglyLinkedListNode(18);

        displayLinkedList(b);
        mergeLinkedListAtAlternatePositions(a, b);
    }
}

