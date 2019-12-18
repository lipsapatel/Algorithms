import Node.BinaryTreeNode;

/**
 * resources/BinaryTreeIntoCircularlyDoublyLinkedList.png
 *
 * Approach
 *
 * 1) Do inorder traversal
 * 2) Maintain head and tail
 * 3) Connect tail with root.
 * 4) At the end of inorder traversal connect tail with head and head with tail.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class BinaryTreeIntoCircularlyDoublyLinkedList {


    private static BinaryTreeNode head = null;
    private static BinaryTreeNode tail = null;

    private static BinaryTreeNode binaryTreeIntoCircularDoublyLinkedList(BinaryTreeNode root) {

        //Base Case
        if (root == null) {
            return null;
        }

        binaryTreeIntoCircularDoublyLinkedListHelper(root);

        //Connect head and tail
        tail.right = head;
        head.left = tail;

        return head;
    }

    private static void binaryTreeIntoCircularDoublyLinkedListHelper(BinaryTreeNode root) {

        //Base Case
        if (root == null) {
            return;
        }

        //Inorder traversal
        binaryTreeIntoCircularDoublyLinkedListHelper(root.left);

        //Connect tail to root
        if (head == null) {
            head = root;
        }

        if (tail == null) {
            tail = root;
        } else {
            tail.right = root;
            root.left = tail;
        }

        //node is the new tail
        tail = root;

        binaryTreeIntoCircularDoublyLinkedListHelper(root.right);
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(4);
        root.left = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(1);
        root.left.right = new BinaryTreeNode(3);
        root.right = new BinaryTreeNode(5);

        BinaryTreeNode head = binaryTreeIntoCircularDoublyLinkedList(root);
        BinaryTreeNode current = head;

        while (current != tail) {
            System.out.print(current.data + " ");
            current = current.right;
        }
        System.out.println(current.data + " ");
    }
}
