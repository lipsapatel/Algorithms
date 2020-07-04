import Node.SinglyLinkedListNode;

/**
 * Find Median Of Sorted Circular Singly Linked List
 * Given a pointer to an arbitrary node in a sorted circular linked list,
 * find the median value of the elements.
 *
 * Example
 * Input: Pointer to node 4 in this circular linked list:
 *
 * 2 --> 4 --> 6 --> 8 --> 10 --> 2
 *
 * Output: 6
 * There are 5 nodes. The middle node in the sorted sequence of the elements
 * would be the median. Regardless of the node given as an input,
 * the answer would be 6 as long as the list is as pictured.
 *
 * Constraints:
 *
 *     1 <= number of elements <= 10^5
 *     - 2 * 10^9 <= any value in the list <= 2 * 10^9
 *     All the values in the input list are even.
 *
 * If the list has an even number of elements,
 * the median is the average of the two middle elements in the sorted sequence.
 *
 * Approach
 *
 * 1) Find the length of the linked list.
 * 2) Given pointer to node, find the first small node.
 * 3) Start from that first small node and find if the linked list is increasing
 * or decreasing
 * 4) If it's increasing, then find the median from first small node.
 * 5) If it's decreasing, then find the first big node.
 * 6) Find the median from the first big node.
 * 7) In case of even nodes, median will be the average of two middle nodes.
 * 8) To avoid Integer overflow, when adding two numbers use long.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * a) sorting order can be ascending or descending or
 * b) while any one value from the list is guaranteed to fit in int32,
 * a sum of two values might overflow int32.
 *
 * Now coming to the actual solution, a linked list will be one of these 3 types
 * 1) 2 -> 2 -> 2 -> 2 -> 2 -> 2
 * 2) 2 -> 2 -> 4 -> 6 -> 8 -> 8
 * 3) 8 -> 8 -> 6 -> 4 -> 2 -> 2
 *
 * First case is trivial.
 *
 *  In the 2nd case if we can find the first smallest element (call it head)
 *  then finding the median will be easy: just find the middle element/elements.
 * (2 -> 2 -> 4 -> 6 -> 8 -> 8) then head will be ([2]head -> 2 -> 4 -> 6 -> 8 -> 8)
 *
 * In the 3rd case if we can find the first largest element (call it head)
 * then finding the median will be easy: just find the middle element/elements.
 * (8 -> 8 -> 4 -> 6 -> 2 -> 2) then head will be ([8]head -> 8 -> 4 -> 6 -> 2 -> 2)
 *
 * resources/MedianOfCircularlySortedSinglyLinkedList1.jpg
 * resources/MedianOfCircularlySortedSinglyLinkedList2.jpg
 */
public class MedianOfSortedCircularlySinglyLinkedList {

    private static int findMedian(SinglyLinkedListNode ptr) {
        int length = getLength(ptr);

        SinglyLinkedListNode firstSmallNode = findFirstSmallNode(ptr);

        if(firstSmallNode == null) { //All nodes have same value
            return ptr.data;
        }

        SinglyLinkedListNode head = null;
        if(isIncreasing(firstSmallNode)) {
            head = firstSmallNode;
        } else {
            head = findFirstBigNode(ptr);
        }

        for (int i = 0; i < (length - 1)/2; i++) {
            head = head.next;
        }

        if(length % 2 != 0) {
            return head.data;
        }
        return (int)(((long)head.data + (long)head.next.data)/(long)2);
    }

    private static int getLength(SinglyLinkedListNode ptr) {
        int length = 1;

        SinglyLinkedListNode current = ptr;
        while(current.next != ptr) {
            length++;
            current = current.next;
        }
        return length;
    }

    private static SinglyLinkedListNode findFirstSmallNode(SinglyLinkedListNode ptr) {
        SinglyLinkedListNode current = ptr;

        //For first node
        if(current.data > current.next.data) {
            return current.next;
        }

        current = current.next;
        while(current != ptr) {
            if(current.data > current.next.data) {
                return current.next;
            }
            current = current.next;
        }
        return null;
    }

    private static boolean isIncreasing(SinglyLinkedListNode ptr) {
        SinglyLinkedListNode current = ptr;

        while(current.next != ptr) {
            if(current.data > current.next.data) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    private static SinglyLinkedListNode findFirstBigNode(SinglyLinkedListNode ptr) {
        SinglyLinkedListNode current = ptr;

        if(current.data < current.next.data) {
            return current.next;
        }

        current = current.next;
        while(current != ptr) {
            if(current.next.data > current.data) {
                return current.next;
            }
            current = current.next;
        }
        return null;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode head = new SinglyLinkedListNode(1);
        head.next = new SinglyLinkedListNode(2);
        head.next.next = new SinglyLinkedListNode(3);
        head.next.next.next = new SinglyLinkedListNode(4);
        head.next.next.next.next = new SinglyLinkedListNode(5);
        head.next.next.next.next.next = head;

        System.out.println(findMedian(head));

        head = new SinglyLinkedListNode(2);
        head.next = new SinglyLinkedListNode(4);
        head.next.next = new SinglyLinkedListNode(6);
        head.next.next.next = new SinglyLinkedListNode(8);
        head.next.next.next.next = new SinglyLinkedListNode(10);
        head.next.next.next.next.next = head;

        System.out.println(findMedian(head.next.next));

        head = new SinglyLinkedListNode(10);
        head.next = new SinglyLinkedListNode(8);
        head.next.next = new SinglyLinkedListNode(6);
        head.next.next.next = new SinglyLinkedListNode(4);
        head.next.next.next.next = new SinglyLinkedListNode(2);
        head.next.next.next.next.next = head;

        System.out.println(findMedian(head));

        head = new SinglyLinkedListNode(2);
        head.next = new SinglyLinkedListNode(2);
        head.next.next = new SinglyLinkedListNode(2);
        head.next.next.next = new SinglyLinkedListNode(2);
        head.next.next.next.next = new SinglyLinkedListNode(2);
        head.next.next.next.next.next = head;

        System.out.println(findMedian(head));
    }
}
