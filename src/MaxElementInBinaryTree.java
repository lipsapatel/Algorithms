import Node.BinaryTreeNode;

/**
 * Find the max element in given binary tree.
 *
 * resources/MaxElementInBinaryTree.png
 *
 * 1) Use Recursion
 * 2) Max will be the max(root, Max element in left subtree, Max element in right subtree)
 * 3) Recursively solve for max element in left and right subtree.
 *
 */
public class MaxElementInBinaryTree {

    private static int maxElementInBinaryTree(BinaryTreeNode root) {

        if (root != null) {
            return max(root.data, maxElementInBinaryTree(root.left), maxElementInBinaryTree(root.right));
        }
        return 0;

    }

    private static int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(10);
        root.left = new BinaryTreeNode(20);
        root.right = new BinaryTreeNode(30);
        root.left.left = new BinaryTreeNode(5);
        root.left.right = new BinaryTreeNode(25);
        root.right.left=new BinaryTreeNode(35);
        root.right.right= new BinaryTreeNode(12);

        System.out.println("The max element in binary tree is " + maxElementInBinaryTree(root));
    }
}
