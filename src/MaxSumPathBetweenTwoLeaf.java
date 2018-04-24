import Node.BinaryTreeNode;

/**
 * Given a binary tree, find the maximum sum path between any two leaves.
 *
 * resources/MaximumSumPathBetweenTwoLeaf.png
 * resources/MaxSumPathBetweenTwoLeaf.png
 *
 * Approach:
 *
 * 1) Max path will be either in left subtree
 * 2) Max path will be either in right subtree
 * 3) Max path will have some part in left, some part in right and passes through the root.
 * 4) Take variable maxSum
 * 5) Do post order traversal
 * 6) At each node calculate currentSum = Math.max(leftSum + rightSum + root.data, Math.max(leftSum,
 *                                                  rightSum));
 * 7) If currentSum > maxSum, update maxSum
 * 8) At each node return Math.max(leftSum, rightSum) + root.data;
 *
 * resources/MaxSumPathBetweenTwoLeafTrace.png
 *
 */
public class MaxSumPathBetweenTwoLeaf {

    private static int maxSum = 0;

    private static int maxSumPathBetweeenTwoLeaf(BinaryTreeNode root) {

        if (root != null) {

            int leftSum = maxSumPathBetweeenTwoLeaf(root.left);
            int rightSum = maxSumPathBetweeenTwoLeaf(root.right);

            int currentSum = 0;

            if (leftSum < 0 && rightSum < 0) {
                currentSum = root.data;
            } else {
                currentSum = Math.max(leftSum + rightSum + root.data, Math.max(leftSum, rightSum));
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
            }

            return Math.max(leftSum, rightSum) + root.data;
        }

        return 0;
    }

    private static void printInorderTraversal(BinaryTreeNode root) {
        if (root != null) {
            printInorderTraversal(root.left);
            System.out.print(" " + root.data);
            printInorderTraversal(root.right);
        }
    }

    public static void main(String[] args) {

        BinaryTreeNode root = new BinaryTreeNode(-5);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(4);
        root.left.left = new BinaryTreeNode(-6);
        root.left.right = new BinaryTreeNode(5);
        root.left.right.left = new BinaryTreeNode(-2);
        root.left.right.right = new BinaryTreeNode(3);
        root.left.left.left = new BinaryTreeNode(9);
        root.left.left.right = new BinaryTreeNode(10);
        root.right.left = new BinaryTreeNode(11);
        root.right.right = new BinaryTreeNode(-2);
        root.right.right.right = new BinaryTreeNode(-8);
        root.right.right.left = new BinaryTreeNode(7);
        root.right.right.right.left = new BinaryTreeNode(1);
        root.right.right.right.right = new BinaryTreeNode(7);
        root.right.right.right.right.left = new BinaryTreeNode(12);

        maxSumPathBetweeenTwoLeaf(root);
        System.out.println("Max Path Sum Between Two Leaves is " + maxSum);
        printInorderTraversal(root);
    }
}
