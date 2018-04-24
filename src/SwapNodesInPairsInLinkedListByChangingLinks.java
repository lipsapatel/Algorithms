import Node.SinglyLinkedListNode;

/**
 * Given linked list write an algorithm to swap nodes in pairs by changing links
 *
 * Example:
 *
 * 1 --> 2 --> 3 --> 4 --> 5 --> 6 --> 7
 * 2 --> 1 --> 4 --> 3 --> 6 --> 5 --> 7
 *
 * Approach:
 *
 * Iterative method:
 *
 * 1) Take 4 pointers
 * 2) pointerOne = head
 * 3) pointerOnePrev = null
 * 4) pointerTwo = head.next
 * 5) pointerTwoNext = pointerTwo.next
 * 6) newHead
 * 7) Make pointerOne points to pointerTwoNext pointerOne.next = pointerTwoNext
 * 8) If pointerOnePrev is not null then pointerOnePrev.next = pointerTwo
 * 9) if pointerOnePrev is null, newHead = pointerTwo
 * 10) pointerTwo.next = head;
 *
 * 11) Move two nodes forward
 * 12) pointerOnePrev = pointerOne;
 * 13) head = pointerTwoNext;
 * 14) return newHead
 *
 * Recursive Method
 *
 * 1) pointerOne = head;
 * 2) pointerTwo = head.next;
 * 3) pointerTwoNext = pointerTwo.next;
 * 4) Swap pointerTwo.next = head
 * 5) newHead = pointerTwo
 * 6) pointerOne.next = recursive call(pointerTwoNext)
 * 7) return newHead
 */
public class SwapNodesInPairsInLinkedListByChangingLinks {

    private static SinglyLinkedListNode head = null;

    private static SinglyLinkedListNode swapNodesInPairsIterative(SinglyLinkedListNode head) {

        SinglyLinkedListNode pointerOnePrev = null;
        SinglyLinkedListNode newHead = null;

        while(head != null && head.next != null) {

            SinglyLinkedListNode pointerOne = head;
            SinglyLinkedListNode pointerTwo = head.next;
            SinglyLinkedListNode pointerTwoNext = pointerTwo.next;

            //Make pointerOne to point to pointer two next
            pointerOne.next = pointerTwoNext;

            if (pointerOnePrev != null) {
                pointerOnePrev.next = pointerTwo;
            } else {
                newHead = pointerTwo;
            }

            //Reverse the next link of pointerTwo
            pointerTwo.next = head;

            //Mode 2 nodes forward.
            pointerOnePrev = pointerOne;
            head = pointerTwoNext;
        }

        return newHead;
    }

    private static SinglyLinkedListNode swapNodesInPairsByUsingRecursion(SinglyLinkedListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        SinglyLinkedListNode pointerOne = head;
        SinglyLinkedListNode pointerTwo = head.next;
        SinglyLinkedListNode pointerTwoNext = pointerTwo.next;

        //Swap
        pointerTwo.next = head;
        SinglyLinkedListNode newHead = pointerTwo;
        pointerOne.next = swapNodesInPairsByUsingRecursion(pointerTwoNext);
        return newHead;
    }

    private static void addAtBegin(int data) {
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        newNode.next = head;
        head = newNode;
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

        addAtBegin(7);
        addAtBegin(6);
        addAtBegin(5);
        addAtBegin(4);
        addAtBegin(3);
        addAtBegin(2);
        addAtBegin(1);

        System.out.println("Original Linked list: ");
        displayLinkedList(head);

        SinglyLinkedListNode node = swapNodesInPairsIterative(head);
        System.out.println("Swapping nodes using iteration: ");
        displayLinkedList(node);

        System.out.println("Swapping nodes using recursion: ");
        SinglyLinkedListNode node1 = swapNodesInPairsByUsingRecursion(node);
        displayLinkedList(node1);
    }
}
