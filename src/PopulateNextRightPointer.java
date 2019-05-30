import Node.BinaryTreeNode;

/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

 struct Node {
 int val;
 Node *left;
 Node *right;
 Node *next;
 }
 Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

 Initially, all next pointers are set to NULL.



 Example:
resources/PopulateNextRightPointer.png


 Input: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}

 Output: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}

 Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B.


 Note:

 You may only use constant extra space.
 Recursive approach is fine, implicit stack space does not count as extra space for this problem.
 Time Complexity: O(n)
 Space Complexity: O(1) if the stack space is not counted.
 */
public class PopulateNextRightPointer {

    public static BinaryTreeNode connect(BinaryTreeNode root) {
        connectHelper(root);
        return root;
    }

    public static void connectHelper(BinaryTreeNode root) {

        if (root == null) {
            return;
        }

        if (root.left != null) {
            root.left.next = root.right;

            if (root.next != null) {
                root.right.next = root.next.left;
            }
        }
        connectHelper(root.left);
        connectHelper(root.right);
    }

    public static void displayBinaryTree(BinaryTreeNode root) {
        BinaryTreeNode current = root;

        if (current != null) {
            System.out.print(current.data + "-->");
            displayBinaryTree(current.left);
            displayBinaryTree(current.right);
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);

        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);

        root.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(7);

        BinaryTreeNode root1 = connect(root);
        displayBinaryTree(root1);
    }
}
