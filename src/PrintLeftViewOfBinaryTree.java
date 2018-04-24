import Node.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Print Left View of Binary Tree.
 *
 * When just looked at the tree from left side, all the nodes you can see will be the left view
 * of binary tree.
 *
 * resources/LeftViewOfBinaryTree.png
 *
 * Approach:
 *
 * Method 1:
 *
 * 1) Traverse the tree in order traversal from left to right
 * 2) Take currentLevel = 0 and nextLevel = 1
 * 3) If currentLevel < nextLevel, print the root.data, so
 * this way you will print only the first left node and make currentLevel = nextLevel
 * 4) For rest of the nodes, currentLevel = nextLevel so won't print
 * 5) Make recursive call to left of the tree
 * 6) Make recursive call to the right of the tree
 *
 * resources/LeftViewOfBinaryTreeTrace.png
 *
 * Method 2:
 *
 * 1) Do the level order traversal
 * 2) Take queue, add root.data to queue
 * while queue is not empty
 * 3) Take variable levelNodes = queue.size
 * 4) While levelNodes > 0, remove from queue and add left and right to queue
 * do levelNodes--;
 * 5) Print the first element which will be the left element
 */
public class PrintLeftViewOfBinaryTree {

    private static int currentLevel = 0;

    private static void printLeftViewRecursive(BinaryTreeNode root, int nextLevel) {

        if (root == null) {
            return;
        }

        if (currentLevel < nextLevel) {
            System.out.print(" " + root.data);
            currentLevel = nextLevel;
        }

        printLeftViewRecursive(root.left, nextLevel + 1);
        printLeftViewRecursive(root.right, nextLevel + 1);
    }

    private static void printLeftViewByLevelOrderTraversal(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();

        queue.add(root);

        int levelNodes = 0;

        while (!queue.isEmpty()) {
            levelNodes = queue.size();

            if (levelNodes > 0) {
                System.out.print(" " + queue.peek().data);
            }

            while (levelNodes > 0) {

                BinaryTreeNode removedNode = queue.remove();

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

        System.out.println("METHOD 1: ");
        printLeftViewRecursive(root, 1);
        System.out.println("\nMETHOD 2 : Using Level Order, Left view ");
        printLeftViewByLevelOrderTraversal(root);
    }
}
