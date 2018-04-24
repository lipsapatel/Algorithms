import Node.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Print right view of Binary Tree.
 *
 * resources/RightViewOfBinaryTree.png
 *
 * Approach:
 *
 * Method 1:
 *
 * 1) Traverse the tree from right to left
 * 2) Take currentLevel = 0 and nextLevel = 1
 * 3) If currentLevel < nextLevel, print root.data, so
 * this way you will print only the first right node and make currentLevel = nextLevel
 * 4) For the rest of the nodes, currentLevel = nextLevel so won't print
 * 5) Make recursive call to the right of the tree.
 * 6) Make recursive call to the left of the tree.
 *
 * Method 2:
 *
 * 1) Do Level Order Traversal
 * 2) Take queue and add root.data to queue
 * 3) While queue is not empty
 * 4) Take variable levelNodes = queue.size()
 * 5) While levelNodes > 0, remove from the queue and add left and right to the queue.
 * 6) do levelNodes--
 * 7) Print the last element which will be the right element
 */
public class PrintRightViewOfBinaryTree {

    private static int currentLevel = 0;

    private static void printRightViewRecursive(BinaryTreeNode root, int nextLevel) {

        if (root == null) {
            return;
        }

        if (currentLevel < nextLevel) {
            System.out.print( " " + root.data);
            currentLevel = nextLevel;
        }

        printRightViewRecursive(root.right, nextLevel + 1);
        printRightViewRecursive(root.left, nextLevel + 1);
    }

    private static void printRightViewLevelOrderTraversal(BinaryTreeNode root) {

        if (root == null) {
            return;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {

            int levelNodes = queue.size();

            while (levelNodes > 0) {

                BinaryTreeNode removedNode = queue.remove();

                if (levelNodes == 1) {
                    //It's the last one, print it
                    System.out.print(" " + removedNode.data);
                }

                if (removedNode.left != null) {
                    queue.add(removedNode.left);
                }

                if (removedNode.right != null) {
                    queue.add(removedNode.right);
                }

                levelNodes--;
            }
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(10);
        root.right = new BinaryTreeNode(15);
        root.left.left = new BinaryTreeNode(20);
        root.left.right = new BinaryTreeNode(25);
        root.right.left = new BinaryTreeNode(30);
        root.right.right = new BinaryTreeNode(35);
        root.left.right.right = new BinaryTreeNode(45);

        printRightViewRecursive(root, 1);
        System.out.println();
        printRightViewLevelOrderTraversal(root);
    }
}
