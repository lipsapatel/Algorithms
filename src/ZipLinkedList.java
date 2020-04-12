import Node.SinglyLinkedListNode;

/**
 * Zip Given Linked List From Ends
 * Given an integer singly linked list L of size n, zip it from its two ends.
 *
 * What does zipping mean?
 * Given a singly linked list L: L1 -> L2 ->… -> Ln-1 -> Ln -> NULL,
 * rearrange the nodes in the list so that the new formed linked list is :
 * L1 -> Ln -> L2 -> Ln-1 -> L3 -> Ln-2 …
 *
 * You have to do it in-place i.e. in the same linked list given in input, using only constant extra space.
 *
 * If n = 6 and L: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> NULL
 *
 * Output Format:
 * 1 -> 6 -> 2 -> 5 -> 3 -> 4 -> NULL
 *
 * Constraints:
 *     0 <= n <= 100000
 *     -2 * 10^9 <= value stored in any node <= 2 * 10^9
 *
 *  For Practice: unzip them back into original lists. i.e. unzip(zip(L1, L2)) should return L1 and L2.)
 *  *************************************************************************************************
 *
 *  Approach:
 *
 *  1) Split the list from middle using fast and slow pointer.
 *  2) Make sure slow pointer points to the end of the first list.
 *  3) Reverse the second list.
 *  4) Merge the list by taking one node from each list.
 *
 *  Time Complexity: O(n)
 *  Space Complexity: O(1)
 *
 *  resources/ZipLinkedList.jpg
 */
public class ZipLinkedList {

    private static SinglyLinkedListNode zipLinkedList(SinglyLinkedListNode head) {

        if(head == null) {
            return null;
        }

        //Split the linked list from middle using slow and fast pointer
        SinglyLinkedListNode slow = head;
        SinglyLinkedListNode fast = head.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        SinglyLinkedListNode list1 = head;
        SinglyLinkedListNode list2 = slow.next;

        //Split the list
        slow.next = null;

        //Reverse the second list
        list2 = reverseLinkedList(list2);

        //Merge two list by selecting one node from each list
        // 1 -> 2 -> 3 -> NULL   6 -> 5 -> 4 -> NULL
        while(list2 != null) {
            SinglyLinkedListNode next2 = list2.next;
            SinglyLinkedListNode next1 = list1.next;
            list1.next = list2; // 1 -> 6
            list2.next = next1; // 1 -> 6 -> 2

            list1 = next1; //list1 = 2
            list2 = next2; //list2 = 5
        }

        return head;
    }

    private static SinglyLinkedListNode reverseLinkedList(SinglyLinkedListNode head) {
        SinglyLinkedListNode current = head;
        SinglyLinkedListNode previous = null;
        SinglyLinkedListNode next = null;

        while(current != null) {
            next = current.next;

            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
        return head;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode head = new SinglyLinkedListNode(1);
        head.next = new SinglyLinkedListNode(2);
        head.next.next = new SinglyLinkedListNode(3);
        head.next.next.next = new SinglyLinkedListNode(4);
        head.next.next.next.next = new SinglyLinkedListNode(5);

        //1 -> 2 -> 3 -> 4 -> 5
        //Output: 1 -> 5 -> 2 -> 4 -> 3
        SinglyLinkedListNode resultNode = zipLinkedList(head);

        while(resultNode != null) {
            System.out.print(resultNode.data + "->");
            resultNode = resultNode.next;
        }

        head = new SinglyLinkedListNode(1);
        head.next = new SinglyLinkedListNode(2);
        head.next.next = new SinglyLinkedListNode(3);
        head.next.next.next = new SinglyLinkedListNode(4);
        head.next.next.next.next = new SinglyLinkedListNode(5);
        head.next.next.next.next.next = new SinglyLinkedListNode(6);

        //1 -> 2 -> 3 -> 4 -> 5 -> 6
        //Output: 1 -> 6 -> 2 -> 5 -> 3 -> 4
        resultNode = zipLinkedList(head);

        System.out.println();
        while(resultNode != null) {
            System.out.print(resultNode.data + "->");
            resultNode = resultNode.next;
        }
    }
}
