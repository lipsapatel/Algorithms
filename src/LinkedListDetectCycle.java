import Node.SinglyLinkedListNode;

/**
 * Given a linked list, determine if it has a cycle in it.

 Approach 1: HashSet of visited nodes
 1) Keep HashSet of visited nodes
 2) If node is visited again then it has cycle.
 3) This requires extra memory

 Example 1:

 Input: head = [3,2,0,-4, -2], pos = 1
 Output: true
 Explanation: There is a cycle in the linked list, where tail connects to the second node.

 Example 2:

 Input: head = [1,2, 1], pos = 0
 Output: true
 Explanation: There is a cycle in the linked list, where tail connects to the first node.

 Example 3:

 Input: head = [1], pos = -1
 Output: false
 Explanation: There is no cycle in the linked list.

Approach 2:
 Can you solve it using O(1) (i.e. constant) memory?

 The slow pointer moves one step at a time while the fast pointer moves two steps at a time.
 If there is no cycle in the list, the fast pointer will eventually reach the end and we can return false
 in this case.
 Now consider a cyclic list and imagine the slow and fast pointers are two runners racing around a circle track.
 The fast runner will eventually meet the slow runner. Why? Consider this case (we name it case A) -
 The fast runner is just one step behind the slow runner. In the next iteration, they both increment one
 and two steps respectively and meet each other.

 How about other cases? For example, we have not considered cases where the fast runner is two or three
 steps behind the slow runner yet. This is simple, because in the next or next's next iteration,
 this case will be reduced to case A mentioned above.

 Time Complexity: O(n)
 Space Complexity: O(1)

 To remove cycle

 1) First detect cycle
 2) Then move slow to head and fast starts from the loop node only.
 3) Start forwarding slow and fast pointer by one.
 4) The node where they meet is the start of the cycle.

 Refer the document why this works.

 This algorithm is called Floyd's cycle detection algorithm.

 Count number of nodes in cycle or loop

 We know that Floyd’s Cycle detection algorithm terminates when fast and
 slow pointers meet at a common point. We also know that this common point
 is one of the loop nodes. We store the address of this common point in a
 pointer variable say ptr.

 Then we initialize a counter with 1 and start from the common point and
 keeps on visiting next node and increasing the counter till we again reach
 the common point(ptr).

 At that point, the value of the counter will be equal to the length of the loop.

 resources/RemoveLoopInLinkedList.jpg
 */
public class LinkedListDetectCycle {

    private static boolean hasCycle(SinglyLinkedListNode head) {
        SinglyLinkedListNode fast = head;
        SinglyLinkedListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if(fast == slow) {

                //Count no of nodes in cycle
                int count = countNodesInLoop(slow);

                //Remove cycle without finding the count
               /* slow = head;

                while(slow.next != fast.next) {
                    slow = slow.next;
                    fast = fast.next;
                }
                fast.next = null; //removes cycle*/

                //Approach 2 to remove cycle
                slow = head;
                fast = head;

                //Place fast to the length of loop away from head.
                while (count != 0) {
                    fast = fast.next;
                    count--;
                }

                while(slow.next != fast.next) {
                    slow = slow.next;
                    fast = fast.next;
                }
                fast.next = null; //removes cycle

                //Has cycle
                return true;
            }
        }
        return false;
    }

    private static int countNodesInLoop(SinglyLinkedListNode node) {
        SinglyLinkedListNode temp = node;
        int count = 1; //This node is part of loop

        while(temp.next != node) {
            temp = temp.next;
            count++;
        }
        System.out.println("The number of nodes in loop is " + count);
        return count;
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
