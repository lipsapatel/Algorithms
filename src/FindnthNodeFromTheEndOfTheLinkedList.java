import Node.SinglyLinkedListNode;

/**
 * Given a linked list and an integer n, write an algorithm to find nth node from the end
 * of the linked list.
 *
 * Example:
 * Original List: {1, 2, 8, 3, 7, 0, 4}
 * 3rd node from the end of the linked list is 7
 *
 * Input: An unsorted Linked List and integer k
 * Output: kth node from the end of the linked list.
 *
 * Approach:
 *
 * Recursive Approach:
 *
 * 1) Recurse through the linked list
 * 2) When we reach the end of the linked list, the base case will return 0
 * 3) Now with each passing back call through stack, add 1 and return
 * 4) when count hits k, print value.
 *
 * Iterative Approach:
 *
 * 1) Take two pointers.
 * 2) Move first pointer by k
 * 3) After that move both pointers together
 * 4) When the first pointer will reach at the end of the linked list, the second
 * pointer will be at kth element behind from the end of the linked list because
 * it started late.
 * 5) return kth node data
 */
public class FindnthNodeFromTheEndOfTheLinkedList {

    private static SinglyLinkedListNode head = null;

    private static int findnthNodeFromEndRecursive(SinglyLinkedListNode head, int k) {

        if (head == null) {
            return 0;
        }

        int i = findnthNodeFromEndRecursive(head.next, k) + 1;

        if (i == k) {
            System.out.println("The " + k + " th node from the end of the linked list is: " + head.data);
        }

        return i;
    }

    private static void addAtBegin(int data) {

        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        newNode.next = head;
        head = newNode;
    }

    private static void displayLinkedList(SinglyLinkedListNode head) {

        SinglyLinkedListNode currentNode = head;

        while(currentNode != null) {
            System.out.print(currentNode.data + "-->");
            currentNode = currentNode.next;
        }
    }

    private static int findnthNodeFromEndByIteration(SinglyLinkedListNode head, int k) {

        if (head == null) {
            return 0;
        }

        SinglyLinkedListNode firstPointer = head;
        SinglyLinkedListNode secondPointer = head;

        while(k > 0) {
            firstPointer = firstPointer.next;
            k--;
        }

        while(firstPointer != null) {
            firstPointer = firstPointer.next;
            secondPointer = secondPointer.next;
        }

        System.out.println("The 3rd node from the end of the linked list is: " + secondPointer.data);

        return secondPointer.data;
    }

    public static void main(String[] args) {

        addAtBegin(4);
        addAtBegin(0);
        addAtBegin(7);
        addAtBegin(3);
        addAtBegin(8);
        addAtBegin(2);
        addAtBegin(1);

        System.out.println("The original Linked List: ");
        displayLinkedList(head);
        System.out.println();

        findnthNodeFromEndRecursive(head, 3);

        findnthNodeFromEndByIteration(head, 3);
    }
}
