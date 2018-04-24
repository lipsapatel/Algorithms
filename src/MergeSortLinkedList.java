import Node.SinglyLinkedListNode;

/**
 * Given a Linked List, sort it using merge sort
 *
 * 9 --> 3 --> 4 --> 2 --> 5 --> 1
 *
 * Sorted List: 1 --> 2 --> 3 --> 4 --> 5 --> 9
 *
 * Approach:
 *
 * 1) Divide and Conquer
 * 2) Find the length of the linked list
 * 3) Mid = length/2;
 * 4) Now divide the list into two equal parts
 * 5) Take pointer old head and move it by mid
 * 6) This will give you newHead = oldHead.next
 * 7) oldHead.next = null
 * 8) oldHead = head
 * 9) Now recursively solve these two parts
 * 10) Merge them into single sorted list.
 */
public class MergeSortLinkedList {

    private static SinglyLinkedListNode mergeLinkedListsRecursively(SinglyLinkedListNode headA, SinglyLinkedListNode headB) {

        SinglyLinkedListNode result = null;

        if (headA == null) {
            return headB;
        }

        if (headB == null) {
            return headA;
        }

        if (headA.data < headB.data) {
            result = headA;
            result.next = mergeLinkedListsRecursively(headA.next, headB);
        } else {
            result = headB;
            result.next = mergeLinkedListsRecursively(headA, headB.next);
        }
        return result;
    }

    private static int getLinkedListLength(SinglyLinkedListNode node) {
        int count = 0;

        SinglyLinkedListNode currentNode = node;

        while (currentNode != null) {
            count++;
            currentNode = currentNode.next;
        }
        return count;
    }

    private static SinglyLinkedListNode mergeSortLinkedList(SinglyLinkedListNode node) {

        SinglyLinkedListNode oldHead = node;

        int mid = getLinkedListLength(node)/2;

        if (oldHead.next == null) {
            return oldHead;
        }

        //Set one pointer at the beginning of the list
        //Another pointer to the next element after mid

        while (mid - 1 > 0) {
            oldHead = oldHead.next;
            mid--;
        }

        SinglyLinkedListNode newHead = oldHead.next;
        oldHead.next = null; //Break the list

        oldHead = node;

        //Make recursive call
        SinglyLinkedListNode sortedList1 = mergeSortLinkedList(oldHead);
        SinglyLinkedListNode sortedList2 = mergeSortLinkedList(newHead);

        return mergeLinkedListsRecursively(sortedList1, sortedList2);

    }

    private static void display(SinglyLinkedListNode head) {
        SinglyLinkedListNode currentNode = head;

        while (currentNode != null) {
            System.out.print("-->" + currentNode.data);
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SinglyLinkedListNode a = new SinglyLinkedListNode(9);
        a.next = new SinglyLinkedListNode(3);
        a.next.next = new SinglyLinkedListNode(4);
        a.next.next.next = new SinglyLinkedListNode(2);
        a.next.next.next.next = new SinglyLinkedListNode(5);
        a.next.next.next.next.next = new SinglyLinkedListNode(1);

        display(a);
        SinglyLinkedListNode node = mergeSortLinkedList(a);
        System.out.println("\n Sorted List: ");
        display(node);
    }
}
