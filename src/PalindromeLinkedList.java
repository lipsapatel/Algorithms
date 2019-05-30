import Node.SinglyLinkedListNode;

/**
 * Given a singly linked list, determine if it is a palindrome.

 Example 1:

 Input: 1->2
 Output: false
 Example 2:

 Input: 1->2->2->1
 Output: true
 Follow up:
 Could you do it in O(n) time and O(1) space?

 Use two pointers left and right. Move right and left using recursion and check for following in each recursive call.
 1) Sub-list is palindrome.
 2) Value at current left and right are matching.

 If both above conditions are true then return true.

 The idea is to use function call stack as container. Recursively traverse till the end of list.
 When we return from last NULL, we will be at last node. The last node to be compared with first node of list.

 In order to access first node of list, we need list head to be available in the last call of recursion.
 Hence we pass head also to the recursive function. If they both match we need to compare (2, n-2) nodes.
 Again when recursion falls back to (n-2)nd node, we need reference to 2nd node from head. We advance the head pointer in previous call, to refer to next node in the list.

 However, the trick in identifying double pointer. Passing single pointer is as good as pass-by-value, and
 we will pass the same pointer again and again.
 We need to pass the address of head pointer for reflecting the changes in parent recursive calls.
 */
public class PalindromeLinkedList {

    private static SinglyLinkedListNode left;

    public static boolean isPalindromeLinkedList(SinglyLinkedListNode head) {
        left = head;
        return isPalindromeLinkedListHelper(head);
    }

    public static boolean isPalindromeLinkedListHelper(SinglyLinkedListNode right) {

        if (right == null) {
            return true;
        }

        //Last node = right, sublist is true or false
        boolean isPalindrome = isPalindromeLinkedListHelper(right.next);

        if (isPalindrome == false) {
            return false;
        }

        if (right.data == left.data) {
            left = left.next;
            return true; //particular node is equal
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        SinglyLinkedListNode head = new SinglyLinkedListNode(1);
        head.next = new SinglyLinkedListNode(2);
        head.next.next = new SinglyLinkedListNode(3);
        head.next.next.next = new SinglyLinkedListNode(2);
        head.next.next.next.next = new SinglyLinkedListNode(1);

        System.out.println("The linked list is palindrome: " + isPalindromeLinkedList(head));
    }
}
