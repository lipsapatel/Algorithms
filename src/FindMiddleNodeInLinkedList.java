import Node.SinglyLinkedListNode;

/**
 * Find The Middle Node In A Singly Linked List
 * Given an integer singly linked list L, find its middle element. L has n nodes.
 *
 * If the list has even number of nodes, consider the second of the middle two nodes
 * as the middle node.
 *
 * Example One
 * Input: 1 -> 2 -> 3 -> 4 -> 5 -> NULL
 * Output: Node containing value 3.
 *
 * Example Two
 * Input: 1 -> 2 -> 3 -> 4 -> NULL
 * Output: Node containing value 3.
 *
 * Constraints:
 *
 *     0 <= n <= 10^5
 *     -2 * 10^9 <= value contained in any node <= 2 * 10^9
 *     Do it in one pass over the linked list.
 *     If the given linked list is empty, return null.
 *
 * For input n = 4 and L: 3 -> 7 -> 2 -> 9 -> NULL, output will be: 2
 *
 * *******************************************************************************
 * Approach 1: Traverse linked list and find the length
 *
 * 1) Traverse the linked list and find the length.
 * 2) Find the mid = length/2
 * 3) Traverse and go to mid element.
 * 4) Return mid element.
 *
 * Time Complexity: O(2n)
 * Space Complexity: O(1)
 *
 * ********************************************************************************
 * Approach 2: Fast pointer and Slow pointer
 *
 * 1) Point slow and fast pointer to head.
 * 2) Move slow pointer by one node
 * 3) Move fast pointer by two node
 * 4) When the fast pointer reaches the end of the list, slow pointer is at midpoint.
 * 5) Return slow pointer
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * resources/FindMiddleNodeInLinkedList.jpg
 */
public class FindMiddleNodeInLinkedList {

    private static SinglyLinkedListNode findMiddleNode(SinglyLinkedListNode head) {
        SinglyLinkedListNode slow = head;
        SinglyLinkedListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode head = new SinglyLinkedListNode(1);
        head.next = new SinglyLinkedListNode(2);
        head.next.next = new SinglyLinkedListNode(3);
        head.next.next.next = new SinglyLinkedListNode(4);

        System.out.println("Middle node " + findMiddleNode(head).data);

        head.next.next.next.next = new SinglyLinkedListNode(5);
        System.out.println("Middle node " + findMiddleNode(head).data);
    }
}
