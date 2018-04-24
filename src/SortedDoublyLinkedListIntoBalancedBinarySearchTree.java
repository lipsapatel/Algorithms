import Node.BinaryTreeNode;
import Node.DoublyLinkedListNode;

/**
 * Convert sorted Doubly Linked List into Balanced Binary Search Tree.
 *
 * resources/SortedDoublyLinkedListIntoBalancedBinarySearchTree.png
 *
 * Approach
 *
 * 1) Get the size of DoublyLinkedList
 * 2) mid is the middle node of the linked list
 * 3) Recursively construct left subtree from start to mid - 1
 * 4) Make middle node as root and assign left subtree to root
 * 5) Here root = head.data and head = head.next
 * 6) Recursively construct right subtree from mid + 1 to end
 * 7) Assign the right subtree to the root
 *
 * Time Complexity: O(n)
 */
public class SortedDoublyLinkedListIntoBalancedBinarySearchTree {

    private static DoublyLinkedListNode head = null;
    private static DoublyLinkedListNode tail = null;

    private static int size = 0;

    private static void addAtStartOfDoublyLinkedList(int data) {

        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }

        size++;
    }

    private static BinaryTreeNode doublyLinkedListToBST(int start, int end) {

        if (start > end) {
            return null;
        }

        int mid = (start + end)/2;

        BinaryTreeNode leftChild = doublyLinkedListToBST(start, mid - 1);

        BinaryTreeNode root = new BinaryTreeNode(head.data);
        head = head.next;
        root.left = leftChild;

        BinaryTreeNode rightChild = doublyLinkedListToBST(mid + 1, end);
        root.right = rightChild;

        return root;
    }

    private static void printInorderTraversal(BinaryTreeNode root) {

        if (root != null) {
            printInorderTraversal(root.left);
            System.out.print(" " + root.data);
            printInorderTraversal(root.right);
        }
    }

    private static void printDoublyLinkedList(DoublyLinkedListNode head) {
        DoublyLinkedListNode current = head;

        while (current != null) {
            System.out.print(" " + current.data);
            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        addAtStartOfDoublyLinkedList(9);
        addAtStartOfDoublyLinkedList(8);
        addAtStartOfDoublyLinkedList(7);
        addAtStartOfDoublyLinkedList(6);
        addAtStartOfDoublyLinkedList(5);
        addAtStartOfDoublyLinkedList(4);
        addAtStartOfDoublyLinkedList(3);
        addAtStartOfDoublyLinkedList(2);
        addAtStartOfDoublyLinkedList(1);

        DoublyLinkedListNode head1 = head;
        System.out.println("Doubly Linked List is : ");
        printDoublyLinkedList(head1);

        BinaryTreeNode root = doublyLinkedListToBST(1, size);

        System.out.println("Inorder traversal of constructed BST");
        printInorderTraversal(root);
    }
}
