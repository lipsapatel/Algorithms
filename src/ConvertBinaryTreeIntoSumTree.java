import Node.BinaryTreeNode;

/**
 * Convert Binary tree into sum tree.
 *
 * What is Sum Tree?
 * Sum tree of a binary tree is tree where every node in the tree will contain sum of left and
 * right subtree in the original tree.
 *
 * resources/BinaryTreeIntoSumTree.png
 *
 * Approach:
 *
 * 1) Recursively calculate the sum of left subtree.
 * 2) Recursively calculate the sum of right subtree.
 * 3) return root.data + leftSum + rightSum;
 * 4) root.data = leftSum + rightSum;
 */
public class ConvertBinaryTreeIntoSumTree {

    private static int convertBinaryTreeIntoSumTree(BinaryTreeNode root) {

        if (root != null) {
            int leftSum = convertBinaryTreeIntoSumTree(root.left);
            int rightSum = convertBinaryTreeIntoSumTree(root.right);

            //Calculate this before root.data is update with new data
            int returnResult = root.data + leftSum + rightSum;

            root.data = leftSum + rightSum;

            return returnResult;
        }
        return 0;
    }

    private static void displayBinaryTree(BinaryTreeNode root) {
        if (root != null) {
            displayBinaryTree(root.left);
            System.out.print(" " + root.data);
            displayBinaryTree(root.right);
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(-1);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(-2);
        root.left.right = new BinaryTreeNode(4);
        root.right.left = new BinaryTreeNode(-6);
        root.right.right = new BinaryTreeNode(10	);

        System.out.println("Original Tree: ");
        displayBinaryTree(root);

        System.out.print("\nSum tree: ");
        convertBinaryTreeIntoSumTree(root);

        //Print the new tree
        displayBinaryTree(root);
    }

}
