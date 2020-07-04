import Node.SinglyLinkedListNode;
/**
 * Add Two Numbers
 * Write a function which adds two numbers a and b, represented as linked lists of
 * size lenA and lenB respectively, and returns the sum c in form of a new linked list.
 * Ignoring the allocation of a new linked list, try to use constant memory when solving it.
 * A number is represented by a linked list, with the head of the linked
 * list being the least significant digit. For example 125 is represented as 5->2->1,
 * and 371 is represented as 1->7->3. Adding 5->2->1(125) and 1->7->3(371)
 * should produce 6->9->4(496).
 *
 * Input:
 * l_a = 7->5->2
 * l_b = 2->0->3
 *
 * Output: result = 9->5->5
 *
 * As l_a = 7->5->2 means number 257 and l_b = 2->0->3 means number 302.
 * Sum of 257 and 302 is 559 so, result will represent 9->5->5.
 *
 * Input:
 * l_a = 5->8->3
 * l_b = 9->4->1
 *
 * Output: result = 4->3->5
 *
 * As l_a = 5->8->3 means number 385 and l_b = 9->4->1 means number 149.
 * Sum of 385 and 149 is 534 so, result will represent 4->3->5.
 *
 * Constraints
 *
 *     1 <= length of the input linked lists <= 100000
 *     0 <= linked list node’s value <= 9
 *     Leading zeros will not appear in the input and will not be accepted in the output.
 *     No negative numbers.
 **************************************************************************************
 * Approach:
 *
 * 1) Scan both the list from head.
 * 2) Sum = a + b + carryForward
 * 3) Store result = sum % 10 in one of the list
 * 4) carryForward = sum/10
 * 5) When both the list becomes null, return head of the list in which result is stored.
 * 6) If there's any carryForward left then append to the result list before returning.
 *
 * Time Complexity: O(max(lena, lenb))
 * Space Complexity: O(1)
 *
 * resources/AddTwoNumbersInLinkedList.jpg
 */
public class AddTwoNumbersInLinkedList {

    private static SinglyLinkedListNode addTwoNumber(SinglyLinkedListNode heada, SinglyLinkedListNode headb) {

        SinglyLinkedListNode list1 = heada;
        SinglyLinkedListNode list2 = headb;
        int carryForward = 0;
        SinglyLinkedListNode prev2 = null;

        while(list1 != null || list2 != null) {

            int a = 0;
            if(list1 != null) {
                a = list1.data;
            }

            int b = 0;
            if(list2 != null) {
                b = list2.data;
            }

            int sum = a + b + carryForward;
            int result = sum % 10;
            carryForward = sum/10;

            if(list2 != null) {
                list2.data = result;
            } else {
                list2 = new SinglyLinkedListNode(result);
                if(prev2 == null) { //new head
                    headb = list2;
                } else {
                    prev2.next = list2; //point prev to new node
                }
            }

            prev2 = list2;
            if(list1 != null) {
                list1 = list1.next;
            }
            list2 = list2.next;
        }

        //If there's any carryforward remaining add to list2
        if(carryForward != 0) {
            list2 = new SinglyLinkedListNode(carryForward);
            prev2.next = list2;
        }

        return headb;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode heada = new SinglyLinkedListNode(6);
        heada.next = new SinglyLinkedListNode(5);
        heada.next.next = new SinglyLinkedListNode(3);

        SinglyLinkedListNode headb = new SinglyLinkedListNode(6);
        headb.next = new SinglyLinkedListNode(5);

        SinglyLinkedListNode sum = addTwoNumber(heada, headb);

        while(sum != null) {
            System.out.print(sum.data + "->");
            sum = sum.next;
        }
        System.out.println();

        heada = new SinglyLinkedListNode(8);
        headb = new SinglyLinkedListNode(7);

        //This will have carry forward 1 when both list becomes null
        sum = addTwoNumber(heada, headb);

        while(sum != null) {
            System.out.print(sum.data + "->");
            sum = sum.next;
        }
        System.out.println();
    }
}
