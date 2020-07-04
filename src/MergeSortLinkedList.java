import Node.SinglyLinkedListNode;

/**
 * Merge Sort A Linked List
 * Given a singly linked list, sort it in the ascending order using the Merge Sort algorithm.
 *
 * Example One
 * Input:
 * 0
 * 1
 * 10
 * 7
 *
 * Output:
 * 0
 * 1
 * 7
 * 10
 *
 * Example Two
 * Input:
 * 1
 * 2
 * 3
 *
 * Output:
 * 1
 * 2
 * 3
 *
 * Here the input is already sorted.
 *
 * Notes
 * Output: Return the head of the sorted linked list.
 *
 * Constraints:
 *     1 <= n <= 10^5
 *     -10^9 <= value of each node of linked list <= 10^9
 ***************************************************************
 * Approach:
 *
 * 1) Find the middle element using fast and slow pointer.
 * 2) Split the linked list and recursively call merge sort on left half and right half.
 * 3) Merge the linked list.
 *
 * Time Complexity: O(nlogn) - logn time for recursive call and n time for merge
 * Space Complexity: O(logn) - call stack
 * It's a stable sorting algorithm. Merge sort has sequential acess which is possible in linked list.
 * In linked list random access is not possible.
 *
 * Merge operation can be implemented without requiring extra space in linked list because items can be
 * inserted in linked list in middle.
 *
 * resources/MergeSortLinkedList1.jpg
 * resources/MergeSortLinkedList2.jpg
 */
public class MergeSortLinkedList {

    private static SinglyLinkedListNode mergeSort(SinglyLinkedListNode head) {
        if(head == null || head.next == null) { //This is important to break the recursive call
            return head;
        }

        SinglyLinkedListNode middle = split(head);
        SinglyLinkedListNode middleNext = middle.next;

        middle.next = null;
        SinglyLinkedListNode left = mergeSort(head);
        SinglyLinkedListNode right = mergeSort(middleNext);

        SinglyLinkedListNode sortedList = merge(left, right);
        return sortedList;
    }

    private static SinglyLinkedListNode merge(SinglyLinkedListNode list1, SinglyLinkedListNode list2) {
        SinglyLinkedListNode result;
        if(list1 == null) {
            return list2;
        }

        if(list2 == null) {
            return list1;
        }

        if(list1.data <= list2.data) {
            result = list1;
            result.next = merge(list1.next, list2);
        } else {
            result = list2;
            result.next = merge(list1, list2.next);
        }
        return result;
    }

    private static SinglyLinkedListNode split(SinglyLinkedListNode head) {
        SinglyLinkedListNode slow = head;
        SinglyLinkedListNode fast = head.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode head = new SinglyLinkedListNode(4);
        head.next = new SinglyLinkedListNode(0);
        head.next.next = new SinglyLinkedListNode(1);
        head.next.next.next = new SinglyLinkedListNode(10);
        head.next.next.next.next = new SinglyLinkedListNode(7);

        SinglyLinkedListNode result = mergeSort(head);

        while(result != null) {
            System.out.print(result.data + " ");
            result = result.next;
        }
    }

}