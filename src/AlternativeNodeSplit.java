import Node.SinglyLinkedListNode;

/**
 * Alternative Node Split
 * Given a linked list, split it into two, such that every other node
 * goes into the new list. For lists with odd number of nodes, first one should be
 * longer. For example: an input list: {a, b, c, d, e, f, g} results in
 * {a, c, e, g} and {b, d, f}.
 *
 * Raw input of sample:
 * 5
 * 1 2 3 4 5
 *
 * Raw output of sample:
 * 1 3 5
 * 2 4
 *
 * Constraints:
 *
 *     0 <= n <= 100000
 *     1 <= values stored in the nodes <= 1000000000
 *     Input will be a singly linked list
 *
 * Explanation:
 * Node values in odd positions are: 1, 3 and 5.
 * Node values in even positions are: 2 and 4
 * So, a linked list will contain node having values 1,3 and 5 and
 * another linked list will contain node having values 2 and 4.
 *
 * **************************************************************************
 * Alternate splitting of a given linked list.
 *
 * Given a singly linked list, split into two linked lists.
 * These linked lists will contain alternate nodes from the the given linked list.
 *
 * resources/AlternateSplittingOfLinkedList.png
 * resources/AlternativeNodeSplit.png
 *
 * Approach:
 *
 * 1) Take two pointers headA and headB
 * 2) currentNode = head and secondNode = currentNode.next
 * 3) Do the traversal and set the next node for the first linkedlist and
 * second linkedlist
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class AlternativeNodeSplit {

    private static void displayLinkedList(SinglyLinkedListNode head) {
        SinglyLinkedListNode currentNode = head;

        while(currentNode != null) {
            System.out.print("-->" + currentNode.data);
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    private static SinglyLinkedListNode[] alternativeSplit(SinglyLinkedListNode head) {
        SinglyLinkedListNode headA = head;
        SinglyLinkedListNode headB = null;

        if(head != null) {
            headB = head.next;
        }

        SinglyLinkedListNode list1 = headA;
        SinglyLinkedListNode list2 = headB;

        while(list2 != null && list2.next != null) {
            list1.next = list2.next;
            list2.next = list2.next.next;

            //Move forward
            list1 = list1.next;
            list2 = list2.next;
        }

        //After coming out, make sure last node next points to null
        if(list1 != null) {
            list1.next = null;
        }

        if(list2 != null) {
            list2.next = null;
        }
        return new SinglyLinkedListNode[]{headA, headB};
    }

    public static void main(String[] args) {

        SinglyLinkedListNode head = new SinglyLinkedListNode(1);
        head.next = new SinglyLinkedListNode(2);
        head.next.next = new SinglyLinkedListNode(3);
        head.next.next.next = new SinglyLinkedListNode(4);
        head.next.next.next.next = new SinglyLinkedListNode(5);

        SinglyLinkedListNode[] nodes = alternativeSplit(head);

        displayLinkedList(nodes[0]);
        displayLinkedList(nodes[1]);

        head = new SinglyLinkedListNode(1);
        head.next = new SinglyLinkedListNode(2);

        nodes = alternativeSplit(head);

        displayLinkedList(nodes[0]);
        displayLinkedList(nodes[1]);

        head = new SinglyLinkedListNode(1);

        nodes = alternativeSplit(head);

        displayLinkedList(nodes[0]);
        displayLinkedList(nodes[1]);
    }
}
