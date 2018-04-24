import Node.SinglyLinkedListNode;

/**
 * Swap every kth node in a linked list.
 *
 * If at the end of the list remaining nodes are less than k, leave them untouched.
 *
 * Input: A linked list, number k
 *
 * Input: 1 --> 2 --> 3 --> 4 --> 5 -- 6 --> 7 --> 8 --> 9 --> 10
 * K = 4;
 *
 * Output: 4 --> 2 --> 3 --> 1 --> 8 --> 6 --> 7 --> 5 --> 9 --> 10
 *
 * Approach:
 *
 * 1) Take 3 pointers.
 * 2) pointerOne, pointerTwoPrev, pointerTwo.
 * 3) pointerOne = pointerTwoPrev = head
 * 4) Move pointerTwo and pointerTwoPrev k - 2 times.
 * 5) Create another pointer newHead and point it to pointerTwo.next
 * 6) Now we have pointerOne at head and pointerTwo at kth position, swap them with the help of pointerTwoPrev
 * 7) point pointerOne.next = recursive call with newHead and k
 */
public class SwapEveryKthNodeInLinkedList {

    private static SinglyLinkedListNode head = null;

    private static SinglyLinkedListNode swapEveryKthNode(SinglyLinkedListNode head, int k) {

        int x = k;

        SinglyLinkedListNode pointerOne = head;
        SinglyLinkedListNode pointerTwoPrev = head;
        SinglyLinkedListNode pointerTwo;

        if (k < 2) {
            return head;
        }

        if (pointerOne == null) {
            return null;
        } else {
            pointerTwo = head.next;
        }

        while (x - 2 > 0) {
            if (pointerTwo != null) {
                pointerTwoPrev = pointerTwo;
                pointerTwo = pointerTwo.next;
                x--;
            } else {
                return head;
            }
        }

        SinglyLinkedListNode newHead = pointerTwo.next;
        pointerTwoPrev.next = pointerOne;
        pointerTwo.next = pointerOne.next;

        pointerOne.next = swapEveryKthNode(newHead, k);
        return pointerTwo;
    }

    private static void addAtBegin(int data) {
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        newNode.next = head;
        head = newNode;
    }

    private static void displayLinkedList(SinglyLinkedListNode head) {
        SinglyLinkedListNode current = head;

        while (current != null) {
            System.out.print("--> " + current.data);
            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

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

        SinglyLinkedListNode node = swapEveryKthNode(head, 4);
        System.out.println("Swap every kth node results: ");
        displayLinkedList(node);
    }
}
