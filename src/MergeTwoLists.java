import java.util.List;

class MergeTwoLists {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        ListNode newNode = new ListNode(-9);
        newNode.next = new ListNode(3);
        //newNode.next.next = new ListNode(4);

        ListNode newNode1 = new ListNode(5);
        newNode1.next = new ListNode(7);
        //newNode1.next.next = new ListNode(4);

        ListNode node = mergeTwoLists(newNode, newNode1);

    }
}