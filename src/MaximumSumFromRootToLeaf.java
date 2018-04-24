import Node.BinaryTreeNode;
import jdk.nashorn.internal.ir.BinaryNode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Given a binary tree, find out the maximum sum from root to each leaf.
 * Means in all the path from root to leaves, find out the path which has maximum sum.
 *
 * resources/MaximumSumFromRootToLeaf.png
 *
 * Approach:
 *
 * 1) Find the maxLeaf and maxSum
 * 2) Do preorder traversal
 * 3) Maintain sum = sum + root.data
 * 4) Whenever you reach leaf node, check if sum > maxSum then update maxSum and maxLeaf
 *
 * Print path from root to maxLeaf
 *
 * 1) Compare with root, if matched we found it else go left or right
 * 2) Going back to the root, store the values in ArrayList.
 * 3) Print the ArrayList in reverse order.
 *
 * Time Complexity: O(n)
 */
public class MaximumSumFromRootToLeaf {

    private static ArrayList<Integer> path = new ArrayList<>();
    private static int maxSum = -1;
    private static BinaryTreeNode maxLeaf = null;

    private static void findMaxSumFromRootToLeaf(BinaryTreeNode root, int sum) {

        if (root != null) {

            sum = sum + root.data;

            if (root.left == null && root.right == null && sum > maxSum) {
                maxSum = sum;
                maxLeaf = root;
            }

            findMaxSumFromRootToLeaf(root.left, sum);
            findMaxSumFromRootToLeaf(root.right, sum);
        }
    }

    private static boolean findPathFromRootToLeaf(BinaryTreeNode root, BinaryTreeNode maxLeaf) {

        if (root == null) {
            return false;
        }

        if (root == maxLeaf || findPathFromRootToLeaf(root.left, maxLeaf)
                || findPathFromRootToLeaf(root.right, maxLeaf)) {

            path.add(root.data);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode (2);
        root.right = new BinaryTreeNode (3);
        root.left.left = new BinaryTreeNode (4);
        root.left.right = new BinaryTreeNode (5);
        root.right.left = new BinaryTreeNode (6);
        root.right.left.left = new BinaryTreeNode (8);

        findMaxSumFromRootToLeaf(root, 0);
        findPathFromRootToLeaf(root, maxLeaf);
        Collections.reverse(path);
        System.out.println("Max sum is " + maxSum + " and path is " + path);

    }
}
