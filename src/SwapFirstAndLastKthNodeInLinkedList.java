import Node.SinglyLinkedListNode;

/**
 * Swap kth Nodes In Given Linked List
 * Given an integer singly linked list L of size n, and an integer k,
 * you have to swap kth (1-indexed) node from the beginning, with kth node from the end.
 * Note that you have to swap the nodes themselves, not just the contents.
 *
 * Input:
 * list: 1 -> 2 -> 3 -> 4 -> 7 -> 0 -> NULL
 * k: 2
 *
 * Output:
 * 1 -> 7 -> 3 -> 4 -> 2 -> 0 -> NULL
 *
 * Constraints:
 *     1 <= n <= 100000
 *     -2 * 10^9 <= value stored in any node <= 2 * 10^9
 *     1 <= k <= n
 *     Try to access linked list nodes minimum number of times.
 *
 * Approach:
 *
 * 1) Traverse the linked list to find kth node.
 * 2) You will now have prev1 and ptr1
 * 3) Point ptr2 to head and temp to ptr1
 * 4) Traverse both the pointers by one node till temp reaches the end of linked list
 * 5) At that point ptr2 will be pointing to n - k + 1th node
 * 6) Swap the nodes ptr1 and ptr2
 * 7) Return head.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * resources/SwapFirstAndLastKthNodeInLinkedList.jpg
 */
public class SwapFirstAndLastKthNodeInLinkedList {

    private static SinglyLinkedListNode swapFirstAndLastKthNode(SinglyLinkedListNode head, int k) {

        SinglyLinkedListNode prev1 = null;
        SinglyLinkedListNode ptr1 = head;
        k--;

        while(k != 0) {
            prev1 = ptr1;
            ptr1 = ptr1.next;
            k--;
        }

        SinglyLinkedListNode prev2 = null;
        SinglyLinkedListNode ptr2 = head;
        SinglyLinkedListNode temp = ptr1;

        while(temp.next != null) {
            prev2 = ptr2;
            ptr2 = ptr2.next;
            temp = temp.next;
        }

        if(prev1 != null) {
            prev1.next = ptr2;
        } else {
            head = ptr2;
        }

        if(prev2 != null) {
            prev2.next = ptr1;
        } else {
            head = ptr1;
        }

        temp = ptr1.next;
        ptr1.next = ptr2.next;
        ptr2.next = temp;
        return head;
    }

    public static void main(String[] args) {
        //1 -> 2 -> 3 -> 4 -> 7 -> 0 -> NULL
        SinglyLinkedListNode head = new SinglyLinkedListNode(1);
        head.next = new SinglyLinkedListNode(2);
        head.next.next = new SinglyLinkedListNode(3);
        head.next.next.next = new SinglyLinkedListNode(4);
        head.next.next.next.next = new SinglyLinkedListNode(7);
        head.next.next.next.next.next = new SinglyLinkedListNode(0);

        SinglyLinkedListNode resultHead = swapFirstAndLastKthNode(head, 2);

        while(resultHead != null) {
            System.out.print(resultHead.data + "-->");
            resultHead = resultHead.next;
        }
    }
}
