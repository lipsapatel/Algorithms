import Node.SinglyLinkedListNode;

/**
 * Merge or Combine Two sorted linked list.
 *
 * Example:
 *
 * Lista: 2 --> 6 --> 18
 * Listb: 1 --> 3 --> 17 --> 19
 *
 * Merged List: 1 --> 2 --> 3 --> 17 --> 18 --> 19
 *
 * Approach:
 *
 * With Recursion
 *
 * Best Case
 *
 * 1) If Lista gets finished, return Listb
 * 2) If Listb gets finished, return Lista
 * 3) Create result node and initialize as null
 * 4) Check which node in Lista or Listb is smaller
 * 5) Add smaller node to result node.
 * 6) Make recursive call and add return node as result.next.
 * result.next = mergeTwoSortedLinkedList(listA.next, listb)
 *
 * Without Recursion
 *
 * 1) Create a new node result and initialize as null
 * 2) Navigate through both the linked list at the same time, starting from head
 * 3) Compare the node values of both the linked list
 * 4) Add the one which is smaller to the result
 * 5) Move the head pointer of linked list with smaller node
 * 6) Again compare the node values
 * 7) Keep doing this until one list gets over
 * 8) Copy rest of nodes of the unfinished list to the result
 *
 */
public class MergeTwoSortedLinkedList {

    private static SinglyLinkedListNode headA;
    private static SinglyLinkedListNode headB;
    private static SinglyLinkedListNode result = null;
    private static SinglyLinkedListNode resultHead = null;

    private static SinglyLinkedListNode mergeLinkedListsWithRecursion(SinglyLinkedListNode headA, SinglyLinkedListNode headB) {

        SinglyLinkedListNode result = null;

        if (headA == null) {
            return headB;
        }
        if (headB == null) {
            return headA;
        }

        if (headA.data < headB.data) {
            result = headA;
            result.next = mergeLinkedListsWithRecursion(headA.next, headB);
        } else {
            result = headB;
            result.next = mergeLinkedListsWithRecursion(headA, headB.next);
        }
        return result;
    }

    private static SinglyLinkedListNode mergeLinkedListsWithoutRecursion() {

        SinglyLinkedListNode currentNodeA = headA;
        SinglyLinkedListNode currentNodeB = headB;

        while (currentNodeA != null && currentNodeB != null) {

            if (currentNodeA.data < currentNodeB.data) {
                addAtEnd(currentNodeA.data);
                currentNodeA = currentNodeA.next;
            } else {
                addAtEnd(currentNodeB.data);
                currentNodeB = currentNodeB.next;
            }
        }

        //Add remaining
        while (currentNodeA != null) {
            addAtEnd(currentNodeA.data);
            currentNodeA = currentNodeA.next;
        }

        while (currentNodeB != null) {
            addAtEnd(currentNodeB.data);
            currentNodeB = currentNodeB.next;
        }

        return result;
    }

    private static void addAtEnd(int data) {

        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        if (resultHead == null) {
            resultHead = newNode;
        } else {
            SinglyLinkedListNode currentNode = resultHead;

            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }

            currentNode.next = newNode;
        }
    }

    private static void addAtBeginListA(int data) {

        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

            newNode.next = headA;
            headA = newNode;
    }

    private static void addAtBeginListB(int data) {

        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        newNode.next = headB;
        headB = newNode;
    }

    private static void displayLinkedList(SinglyLinkedListNode head) {
        SinglyLinkedListNode currentNode = head;

        while (currentNode != null) {
            System.out.print(currentNode.data + "-->");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println("Method : without Recursion");
        addAtBeginListA(8);
        addAtBeginListA(6);
        addAtBeginListA(5);

        addAtBeginListB(9);
        addAtBeginListB(7);
        addAtBeginListB(3);
        addAtBeginListB(1);

        displayLinkedList(headA);
        displayLinkedList(headB);

        SinglyLinkedListNode result = mergeLinkedListsWithoutRecursion();
        displayLinkedList(resultHead);

        SinglyLinkedListNode result1 = mergeLinkedListsWithRecursion(headA, headB);
        displayLinkedList(result1);
    }
}
