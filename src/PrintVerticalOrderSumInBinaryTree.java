import Node.BinaryTreeNode;

import java.util.Set;
import java.util.TreeMap;

/**
 * Print vertical sum in Binary Tree.
 *
 * resources/VerticalOrderSumBinaryTree.png
 *
 * Approach:
 *
 * 1) Do the inorder traversal
 * 2) Take variable level, --level when you go left and ++level when you go right.
 * 3) Create treeMap with (level, sum of nodes)
 * 4) If node == null, level++
 * 5) At the end iterate the treeMap and print the results
 *
 * Time Complexity: O(n)
 * Space Complexity: O(numberOfLevel)
 *
 * resources/VerticalOrderSumBinaryTreeTracing.png
 *
 */
public class PrintVerticalOrderSumInBinaryTree {

    private static TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();

    private static BinaryTreeNode verticalOrderSum(BinaryTreeNode root, int level) {

        if (root == null) {
            return null;
        }

        BinaryTreeNode node = verticalOrderSum(root.left, --level);

        if (node == null) {
            level++;
        }

        if (treeMap.get(level) != null) {
            treeMap.put(level, treeMap.get(level) + root.data);
        } else {
            treeMap.put(level, root.data);
        }

        return verticalOrderSum(root.right, ++level);
    }

    private static void printSum() {

        Set<Integer> keys = treeMap.keySet();

        for (int key: keys) {
            System.out.println("Level " + key + " sum is " + treeMap.get(key));
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

        verticalOrderSum(root, 0);
        printSum();
    }
}
