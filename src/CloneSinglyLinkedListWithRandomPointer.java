import Node.SinglyLinkedListNode;

/**
 * Clone Singly Linked List With Random Pointer
 * Given a singly linked list called list of n elements. The data stored
 * in the nodes of the linked list are the continuous sequence of the integral
 * natural numbers i.e. the head node stores the integer 1 , next node stores 2
 * and so on until the last node of the list stores n. Now, apart from the standard
 * next pointer of the linked list node there is a special random pointer that may
 * or may not exist on each node. This random pointer of a node can point to any
 * node of the linked list including itself.
 *
 * Your task is to clone the given list in an efficient manner, both in terms of time
 * and space.
 *            |----|
 *            |    |
 * 1 -> 2 -> 3 -> 4 -> 5
 * |         |
 * |---------|
 *
 * Output:
 * Node  Next Random
 * 1     2    3
 * 2     3    -1
 * 3     4    4
 * 4     5    -1
 * 5    -1    -1
 *
 * Here the newly cloned list will be the same as the input linked list.
 *
 * Output: Return the head node of the newly cloned linked list.
 *
 * Constraints:
 *     1 <= n <= 100000
 *
 * You may modify the given input linked-list list for cloning purpose.
 ************************************************************************
 * Approach:
 *
 * 1) Scan the linked list. Clone each node and insert it to it's next. TC = O(n)
 * 2) Traverse the list by 2 nodes and if a node has random pointer then point
 * next node random pointer to the random pointer next.
 * x.next.randomPointer = x.randomPointer.next TC = O(n)
 * 3) Extract cloned nodes. Traverse the linked list by 2 nodes and for every node
 * point it's next pointer to nextNode's next.
 * x.next = x.next.next; TC = O(n)
 *
 * TC = O(3n) = O(n)
 * SC = O(n)
 *
 * resources/CloneSinglyLinkedListWithRandomPointer1.jpg
 * resources/CloneSinglyLinkedListWithRandomPointer2.jpg
 */
public class CloneSinglyLinkedListWithRandomPointer {

    private static SinglyLinkedListNode cloneSinglyLinkedList(SinglyLinkedListNode list) {

        //Step1: Traverse the linked list and clone the node and insert it to the next.
        SinglyLinkedListNode tmpHead = list;

        while(tmpHead != null) {
            SinglyLinkedListNode nextNode = tmpHead.next;
            SinglyLinkedListNode node = new SinglyLinkedListNode(tmpHead.data);
            tmpHead.next = node;
            node.next = nextNode;

            tmpHead = nextNode;
        }

        //Step2: Traverse the linked list and link random pointer.
        // x.randomPointer != null then x.next.randomPointer = x.randomPointer.next
        tmpHead = list;

        while(tmpHead != null) {
            if(tmpHead.randomPointer != null) {
                tmpHead.next.randomPointer = tmpHead.randomPointer.next;
            }
            tmpHead = tmpHead.next.next; //Skip by 2 nodes
        }

        //Step3: Extract cloned list
        tmpHead = list;
        SinglyLinkedListNode clonedListHead = null;
        SinglyLinkedListNode clonedListTail = null;

         while(tmpHead != null) {
             SinglyLinkedListNode clonedNode = tmpHead.next;

             tmpHead.next = tmpHead.next.next;

             if(clonedListHead == null) {
                 clonedListHead = clonedNode;
                 clonedListTail = clonedNode;
             } else {
                 clonedListTail.next = clonedNode;
                 clonedListTail = clonedListTail.next;
             }
             tmpHead = tmpHead.next;
         }
         return clonedListHead;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode head = new SinglyLinkedListNode(1);
        SinglyLinkedListNode two = new SinglyLinkedListNode(2);
        SinglyLinkedListNode three = new SinglyLinkedListNode(3);
        SinglyLinkedListNode four = new SinglyLinkedListNode(4);
        SinglyLinkedListNode five = new SinglyLinkedListNode(5);

        head.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        head.randomPointer = three;
        three.randomPointer = four;

        SinglyLinkedListNode clonedHead = cloneSinglyLinkedList(head);
        System.out.println();
    }
}
