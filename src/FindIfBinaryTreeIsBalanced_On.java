import Node.BinaryTreeNode;

/**
 *  * Given a binary tree, find whether if the given binary tree is balanced.
 *
 * Balanced Tree: A balanced tree is a binary tree in which difference between the left subtree
 * and right subtree height of any node is not greater than 1.
 *
 * Input: Binary Tree
 * Output: True if it's balanced else false.
 *
 * resources/BalancedTree.png
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 *
 * 1) Post Order Traversal
 * 2) Travel all the way down to leaf nodes
 * 3) While going up calculate the height of left subtree and right subtree
 * 4) If the difference is greater than 1, return -1
 * 5) else calculate 1 + Math.max(left Height, right Height)
 * 6) Here the height is stored at each level.
 *
 * resources/BalancedTreeTrace.png
 *
 */
public class FindIfBinaryTreeIsBalanced_On {

    private static int isBalancedTree(BinaryTreeNode root) {

        if (root == null) {
            return 0;
        }

        int leftHeight = isBalancedTree(root.left);

        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = isBalancedTree(root.right);

        if (rightHeight == -1) {
            return -1;
        }

        int difference = leftHeight - rightHeight;

        if (Math.abs(difference) > 1) {
            return -1;
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }

    private static boolean checkIfBinaryTreeIsBalanced(BinaryTreeNode root) {

        int result = isBalancedTree(root);

        if (result >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        System.out.println(" Is Tree Balanced : " + checkIfBinaryTreeIsBalanced(null));

        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(10);
        root.right = new BinaryTreeNode(15);
        root.left.left = new BinaryTreeNode(20);
        root.left.right = new BinaryTreeNode(25);
        root.right.left = new BinaryTreeNode(30);
        root.right.right = new BinaryTreeNode(35);

        System.out.println(" Is Tree Balanced : " + checkIfBinaryTreeIsBalanced(root));

        root.right.right.right = new BinaryTreeNode (40);
        root.right.right.right.right = new BinaryTreeNode (45);

        System.out.println(" Is Tree Balanced : " + checkIfBinaryTreeIsBalanced(root));
    }
}
