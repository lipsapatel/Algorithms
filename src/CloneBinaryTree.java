import Node.BinaryTreeNode;

/**
 * Given a binary tree (represented by its root node), clone it and return root node of the cloned tree.
 *
 * Approach:
 *
 * 1) Do preorder traversal
 * 2) Clone root and then clone left and right
 * 3) Return cloned root.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 */
public class CloneBinaryTree {

    private static BinaryTreeNode cloneTree(BinaryTreeNode root) {

        //Base Case
        if (root == null) {
            return null;
        }

        BinaryTreeNode newRoot = new BinaryTreeNode(root.data);
        newRoot.left = cloneTree(root.left);
        newRoot.right = cloneTree(root.right);

        return newRoot;
    }

    private static void printTree(BinaryTreeNode root) {

        if (root != null) {
            printTree(root.left);
            System.out.print(" " + root.data);
            printTree(root.right);
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(100);
        root.left = new BinaryTreeNode(200);
        root.left.left = new BinaryTreeNode(400);
        root.left.right = new BinaryTreeNode(500);
        root.right = new BinaryTreeNode(300);

        printTree(root);
        System.out.println();
        BinaryTreeNode newRoot = cloneTree(root);
        printTree(newRoot);

    }
}
