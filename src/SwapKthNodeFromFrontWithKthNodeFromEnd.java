import Node.SinglyLinkedListNode;

/**
 * Swap Kth node from front with Kth node from end.
 *
 * Given a linked list and a number k, swap kth node from front with kth node from end.
 *
 * Example:
 *
 * 10 --> 20 --> 30 --> 40 --> 50 --> 60 --> 70 --> null
 *
 * Swapping 1st node from front and end
 * 70 --> 20 --> 30 --> 40 --> 50 --> 60 --> 10 --> null
 *
 * Swapping 2nd node from front and end
 * 70 --> 60 --> 30 --> 40 --> 50 --> 20 --> 10 --> null
 *
 * Swapping 3rd node from front and end
 * 70 --> 60 --> 50 --> 40 --> 30 --> 20 --> 10 --> null
 *
 * Swapping 4th node from front and end
 * Nodes are same from front and end so no swapping
 *
 * Swapping 5th node from front and end
 * 70 --> 60 --> 30 --> 40 --> 50 --> 20 --> 10 --> null
 *
 * Swapping 6th node from front and end
 * 70 --> 20 --> 30 --> 40 --> 50 --> 60 --> 10 --> null
 *
 * Swapping 7th node from front and end
 * 10 --> 20 --> 30 --> 40 --> 50 --> 60 --> 70 --> null
 *
 * If k > length of list, invalid number and no swapping
 *
 * Approach:
 *
 * 1) Length of list
 * 2) If K > length then no swapping
 * 3) 2k - 1 = length, which means kth node is same from front and end
 *
 * 4) Take pointer left, move it by k nodes.
 * 5) Also keep track of left_previous.
 * 6) left_previous = null if k = 1
 *
 * 7) Take pointer right, move it by length - k + 1
 * 8) Also keep track of right_previous
 * 9) right_previous = null, if k = length
 *
 * 10) If left_previous != null then left node is not the first node.
 * left_previous will point to right
 *
 * 11) If right_previous != null then right node is not the first node.
 * right_previous will point to left
 *
 * 12) Just swap the next and right.next pointers
 * 13) We need to change the head of the list
 * if k = 1 then head = right
 * if k = length then head = left
 *
 */
public class SwapKthNodeFromFrontWithKthNodeFromEnd {

    private static int length;
    private static SinglyLinkedListNode head = null;

    private static SinglyLinkedListNode swapKthNodeFromFrontWithKthNodeFromEnd(int k) {

        length = getLinkedListLength(head);

        if (k > length) {
            System.out.println("K is greater than the length of the list - No swapping");
            return null;
        }

        if (2 * k - 1 == length) {
            System.out.println("Both the nodes are same from front and the end - No swapping");
            return null;
        }

        System.out.println("Swapping " + k + "node from the front and end");

        SinglyLinkedListNode left = head;
        SinglyLinkedListNode left_previous = null;

        //Traverse k node
        int j = k;
        while (j > 1) {

            left_previous = left;
            left = left.next;
            j--;
        }

        SinglyLinkedListNode right = head;
        SinglyLinkedListNode right_previous = null;

        int l = length - k + 1;

        while (l > 1) {
            right_previous = right;
            right = right.next;
            l--;
        }

        //point to right
        if (left_previous != null) {
            left_previous.next = right;
        }

        //point to left
        if (right_previous != null) {
            right_previous.next = left;
        }

        //Swap left.next and right.next
        SinglyLinkedListNode tempNode = left.next;
        left.next = right.next;
        right.next = tempNode;

        //Set the head correctly
        if (k == 1) {
            head = right;
        }
        if (k == length) {
            head = left;
        }

        return head;
    }

    private static void addAtEnd(int data) {
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        if (head == null) {
            newNode.next = head;
            head = newNode;
        } else {
            SinglyLinkedListNode currentNode = head;

            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
    }

    private static void displayLinkedList() {

        SinglyLinkedListNode currentNode = head;

        while(currentNode != null) {
            System.out.print(currentNode.data + "-->");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        addAtEnd(10);
        addAtEnd(20);
        addAtEnd(30);
        addAtEnd(40);
        addAtEnd(50);
        addAtEnd(60);
        addAtEnd(70);
        displayLinkedList();

        for (int x = 1; x < 9; x++) {

            swapKthNodeFromFrontWithKthNodeFromEnd(x);
            displayLinkedList();
        }
    }

    private static int getLinkedListLength(SinglyLinkedListNode head) {

        SinglyLinkedListNode currentNode = head;
        int count = 0;

        while(currentNode != null) {
            count++;
            currentNode = currentNode.next;
        }
        return count;
    }
}
