import Node.SinglyLinkedListNode;

/**
 * Find Intersection Of Two Singly Linked Lists
 * Given two singly linked lists, find their intersection if any.
 * The intersection is the node where the two lists merge, the first node that
 * belongs to both lists
 *
 * * Output: Function must return an integer, the value from the intersection node
 * or -1 if no intersection exists.
 *
 * Constraints:
 *
 *     0 <= values in the list nodes <= 10^9
 *     0 <= number of nodes in a list <= 10^5
 *
 * Example:
 *
 * list1 = 1 --> 2 --> 3 --> 4 --> 7 --> 8 --> 9
 * list2 = 5 --> 6 --> 4 --> 7 --> 8 --> 9
 *
 * Output: 4
 *
 * Size of list1 = 7
 * Size of list2 = 6
 *
 * Approach:
 * 1) Calculate size of both the list.
 * 2) Start the longer list from the difference in size.
 * 3) Compare one to one
 * 4) Move forward
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * resources/IntersectionOfTwoLinkedList.jpg
 *
 * We could start comparing from the first node of both lists.
 * If the two nodes are the same (same address) then it is the intersection node
 * else we advance both pointers to their next nodes and again compare and so on.
 *
 * If we reach the end of both lists, it means they do not intersect.
 *
 * Now suppose one list is longer than the other by X nodes. It is easy to see
 * that the intersection cannot be found among the first X nodes of the longer list.
 * X is easy to calculate by measuring the length of each list.
 * Then we can reduce the problem to the simpler one by skipping the first X nodes
 * in the longer list.
 *
 * Using this approach we would walk through the lists two times total: once for
 * measuring their lengths and once again for comparing the nodes.
 *
 */
public class IntersectionOfTwoLinkedList {

    private static int findIntersection(SinglyLinkedListNode list1, SinglyLinkedListNode list2) {

        int size1 = getSize(list1);
        int size2 = getSize(list2);

        if(size1 > size2) {
            list1 = list1.next;
            size1--;
        }

        if(size2 > size1) {
            list2 = list2.next;
            size2--;
        }

        while(list1 != null && list1 != list2) {
            list1 = list1.next;
            list2 = list2.next;
        }

        if(list1 == null) {
            return -1; //not found intersecting node
        }
        return list1.data;
    }

    private static int getSize(SinglyLinkedListNode list) {
        int size = 0;

        while(list != null) {
            size++;
            list = list.next;
        }
        return size;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode list1 = new SinglyLinkedListNode(0);

        SinglyLinkedListNode one = new SinglyLinkedListNode(1);
        SinglyLinkedListNode two = new SinglyLinkedListNode(2);
        SinglyLinkedListNode three = new SinglyLinkedListNode(3);

        list1.next = one;
        list1.next.next = two;
        list1.next.next.next = three;

        SinglyLinkedListNode list2 = one;
        list2.next = two;
        list2.next.next = three;

        System.out.println(findIntersection(list1, list2));
    }
}