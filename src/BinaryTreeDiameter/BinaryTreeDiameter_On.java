package BinaryTreeDiameter;
import Node.BinaryTreeNode;

/**
 * If you notice every node we are calling height function. We can calculate height in the same
 * iteration.
 * Calculate height and diameter at every iteration of that node.
 *
 * Height = 1 + Math.max(leftHeight, rightHeight)
 * Diameter = Max(leftDiameter, rightDiameter, rootDiameter)
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) - Call stack
 *
 * This is counting number of nodes for diameter
 */
public class BinaryTreeDiameter_On {

    private static int ans;

    private static int[] BinaryTreeDiameter(BinaryTreeNode root) {

        int[] heightAndDiameter = {0,0};

        while (root != null) {

            int[] leftResult = BinaryTreeDiameter(root.left);
            int[] rightResult = BinaryTreeDiameter(root.right);

            int leftSubtreeHeight = leftResult[0];
            int rightSubtreeHeight = rightResult[0];
            int height = 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight);

            int leftDiameter = leftResult[1];
            int rightDiameter = rightResult[1];
            int rootDiameter = 1 + leftSubtreeHeight + rightSubtreeHeight;
            int diameter = Math.max(Math.max(leftDiameter, rightDiameter), rootDiameter);

            heightAndDiameter[0] = height;
            heightAndDiameter[1] = diameter;

            return heightAndDiameter;
        }

        return heightAndDiameter;
    }

    /**
     * This method calculates the left depth and right depth
     * And it keeps track of max(leftDepth + rightDepth + 1)
     * Calculate left depth and right depth
     * Calculate the diameter and keep the track of max diameter so far
     * @param root
     */
    private static int BinaryTreeDepth(BinaryTreeNode root) {

        if (root == null) {
            return 0;
        }

        int L = BinaryTreeDepth(root.left);
        int R = BinaryTreeDepth(root.right);
        ans = Math.max(ans, L + R + 1); //this is counting number of nodes. To count number of edges, do L + R
        return Math.max(L, R) + 1; //height is also counting no of nodes

    }

    /**
     * This calculates height using edges and diameter using edges
     * @param root
     * @return
     */
    private static int BinaryTreeDepthEdges(BinaryTreeNode root) {
        if (root == null) {
            return -1; //Since it's calculating edges
        }

        int L = BinaryTreeDepthEdges(root.left);
        int R = BinaryTreeDepthEdges(root.right);
        ans = Math.max(ans, L + R + 2); //add 2 for edges because height s also calculated using edges
        return Math.max(L, R) + 1;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.left.right.left = new BinaryTreeNode(6);
        root.left.right.left.right = new BinaryTreeNode(7);
        root.left.left.left = new BinaryTreeNode(8);

        System.out.println("The Diameter of Binary Tree is " + BinaryTreeDiameter(root)[1]);

        ans = 0;
        BinaryTreeDepth(root);
        System.out.println("The Diameter of Binary Tree is " + ans);

        ans = 0;
        BinaryTreeDepthEdges(root);
        System.out.println("The Diameter of Binary Tree using edges: " + ans);
    }
}
