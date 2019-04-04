import Node.SinglyLinkedListNode;

/**
 * Given a linked list, determine if it has a cycle in it.

 To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.



 Example 1:

 Input: head = [3,2,0,-4], pos = 1
 Output: true
 Explanation: There is a cycle in the linked list, where tail connects to the second node.


 Example 2:

 Input: head = [1,2], pos = 0
 Output: true
 Explanation: There is a cycle in the linked list, where tail connects to the first node.


 Example 3:

 Input: head = [1], pos = -1
 Output: false
 Explanation: There is no cycle in the linked list.




 Follow up:

 Can you solve it using O(1) (i.e. constant) memory?

 The slow pointer moves one step at a time while the fast pointer moves two steps at a time.

 If there is no cycle in the list, the fast pointer will eventually reach the end and we can return false in this case.

 Now consider a cyclic list and imagine the slow and fast pointers are two runners racing around a circle track. The fast runner will eventually meet the slow runner. Why? Consider this case (we name it case A) - The fast runner is just one step behind the slow runner. In the next iteration, they both increment one and two steps respectively and meet each other.

 How about other cases? For example, we have not considered cases where the fast runner is two or three steps behind the slow runner yet. This is simple, because in the next or next's next iteration, this case will be reduced to case A mentioned above.

 Time Complexity: O(n)
 Space Complexity: O(1)
 */
public class LinkedListCycle {

    private static boolean hasCycle(SinglyLinkedListNode head) {

        if (head == null || head.next == null) {
            return false;
        }

        SinglyLinkedListNode slow = head;
        SinglyLinkedListNode fast = head.next;

        while (slow != fast) {

            if (fast == null || fast.next == null) {
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;
        }


        //no cycle
        return true;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode head = new SinglyLinkedListNode(3);
        SinglyLinkedListNode temp = new SinglyLinkedListNode(2);
        head.next = temp;
        head.next.next = new SinglyLinkedListNode(0);
        head.next.next.next = new SinglyLinkedListNode(-4);
        head.next.next.next.next = temp;

        System.out.println("Has cycle: " + hasCycle(head));
    }
}
