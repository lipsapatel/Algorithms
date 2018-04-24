import Node.BinaryTreeNode;
import Node.SinglyLinkedListNode;

/**
 * Given a sorted Singly Linked List Array, convert it into Balanced Binary Search Tree.
 *
 * You can also create first node as root and insert all other nodes to the right of the tree
 * because the linked list is sorted.
 *
 * This will not be balanced tree but skewed tree and to perform operations on this tree
 * required O(n) instead of O(logn)
 *
 * resources/SinglyLinkedListToBST.png
 *
 * Approach
 *
 * 1) mid is the middle node of linked list
 * 2) Recursively construct left subtree from start to mid - 1
 * 3) Make middle node as the root and assign the left subtree to it.
 * 4) Here root = head .data and head = head.next
 * 5) Recursively construct right subtree from mid + 1 to end
 * 6) Assign the right subtree to root.right
 *
 * Time Complexity: O(n)
 */
public class SortedLinkedListIntoBalancedBinarySearchTree {

    private static SinglyLinkedListNode head = null;

    private static BinaryTreeNode LinkedListToBST(int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end)/2;

        BinaryTreeNode leftChild = LinkedListToBST(start, mid - 1);

        BinaryTreeNode root = new BinaryTreeNode(head.data);
        head = head.next;
        root.left = leftChild;

        BinaryTreeNode rightChild = LinkedListToBST(mid + 1, end);

        root.right = rightChild;

        return root;
    }

    private static int getLinkedListSize() {
        SinglyLinkedListNode current = head;
        int size = 0;

        while (current != null) {
            current = current.next;
            size++;
        }
        return size;
    }

    private static void printInorderTraversal(BinaryTreeNode root) {

        if (root != null) {
            printInorderTraversal(root.left);
            System.out.print(" " + root.data);
            printInorderTraversal(root.right);
        }
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        head = new SinglyLinkedListNode(1);
        head.next = new SinglyLinkedListNode(2);
        head.next.next = new SinglyLinkedListNode(3);
        head.next.next.next = new SinglyLinkedListNode(4);
        head.next.next.next.next = new SinglyLinkedListNode(5);
        SinglyLinkedListNode tail = new SinglyLinkedListNode(6);
        head.next.next.next.next.next = tail;

        BinaryTreeNode root = LinkedListToBST(1, getLinkedListSize()) ;
        System.out.print("Constructed BST is :");
        printInorderTraversal(root);

    }
}