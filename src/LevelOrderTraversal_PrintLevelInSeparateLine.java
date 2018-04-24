import Node.BinaryTreeNode;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Level Order Traversal - Print each level in separate line
 *
 * Input: A binary tree
 * Output: Each level of binary tree, in one line
 *
 * resources/LevelOrderTraversalPrintEachLevelInOneLine.png
 *
 * Approach:
 *
 * 1) Do the level order traversal using queue
 * while queue is not empty
 * 2) levelNodes = queue.size()
 * 3) While levelNodes > 0
 * 4) Remove from queue and print it
 * 5) Add left and right child to queue
 * 6) levelNodes--;
 *
 * resources/LevelOrderTraversalTrace.png
 *
 * Time Complexity: O(n)
 */
public class LevelOrderTraversal_PrintLevelInSeparateLine {

    private static void levelOrderTraversalPrint(BinaryTreeNode root) {

        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();

        if (root == null) {
            return;
        }

        //Insert root node
        queue.add(root);
        int levelNodes = 0;

        while(!queue.isEmpty()) {

            levelNodes = queue.size();

            while (levelNodes > 0) {

                BinaryTreeNode removedNode = queue.remove();

                System.out.print(" " + removedNode.data);

                if (removedNode.left != null) {
                    queue.add(removedNode.left);
                }
                if (removedNode.right != null) {
                    queue.add(removedNode.right);
                }

                levelNodes--;
            }
            System.out.println("");
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

        System.out.println(" Output by Better Approach : ");
        levelOrderTraversalPrint(root);
    }
}
