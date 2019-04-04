/*Given a sorted linked list, delete all duplicates such that each element appear only once.

        Example 1:

        Input: 1->1->2
        Output: 1->2
        Example 2:

        Input: 1->1->2->3->3
        Output: 1->2->3*/

class RemoveDuplicatesFromSortedLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(1);
        node.next.next = new ListNode(2);
        node.next.next.next = new ListNode(3);
        node.next.next.next.next = new ListNode(3);
        ListNode current = new RemoveDuplicatesFromSortedLinkedList().deleteDuplicates(node);
        while (current != null) {
            System.out.print(current.val + " -->");
            current = current.next;
        }
    }
}

