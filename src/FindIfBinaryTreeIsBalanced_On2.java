import Node.BinaryTreeNode;

/**
 * Given a binary tree, find whether if the given binary tree is balanced.
 *
 * Balanced Tree: A balanced tree is a binary tree in which difference between the left subtree
 * and right subtree height of any node is not greater than 1.
 *
 * Input: Binary Tree
 * Output: True if it's balanced else false.
 *
 * resources/BalancedTree.png
 *
 * Naive Approach:
 *
 * 1) For each node of the tree, get the left subtree height and right subtree height.
 * 2) Calculate the difference, if it's greater than 1 then return false
 *
 * Time Complexity: O(n2)
 */
public class FindIfBinaryTreeIsBalanced_On2 {

    private static int getHeightOfBinaryTree(BinaryTreeNode root) {

        if (root == null) {
            return 0;
        }

        return 1 + Math.max(getHeightOfBinaryTree(root.left), getHeightOfBinaryTree(root.right));
    }

    private static boolean isBalancedTree(BinaryTreeNode root) {

        if (root == null) {
            return true;
        }

        int heightDifference = getHeightOfBinaryTree(root.left) - getHeightOfBinaryTree(root.right);

        if (Math.abs(heightDifference) > 1) {
            return false;
        } else {
            return isBalancedTree(root.left) && isBalancedTree(root.right);
        }
    }
    public static void main(String[] args) {

        System.out.println(" Is Tree Balanced : " + isBalancedTree(null));

        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(10);
        root.right = new BinaryTreeNode(15);
        root.left.left = new BinaryTreeNode(20);
        root.left.right = new BinaryTreeNode(25);
        root.right.left = new BinaryTreeNode(30);
        root.right.right = new BinaryTreeNode(35);

        System.out.println(" Is Tree Balanced : " + isBalancedTree(root));

        root.right.right.right = new BinaryTreeNode (40);
        root.right.right.right.right = new BinaryTreeNode (45);

        System.out.println(" Is Tree Balanced : " + isBalancedTree(root));
    }
}
