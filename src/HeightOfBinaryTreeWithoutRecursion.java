import Node.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Find the height of tree without Recursion.
 *
 * Approach: Level Order Traversal
 *
 * 1) Take queue
 * 2) Add root to queue
 * 3) While queue is not empty
 * 4) levelNodes = queue size
 * 5) height = height + 1;
 * 5) While levelNodes > 0
 * 6) Remove from queue
 * 7) Add the left child
 * 8) Add the right child
 *
 * Time Complexity: O(n)
 */
public class HeightOfBinaryTreeWithoutRecursion {

    private static int heightOfBinaryTreeWithoutRecursion(BinaryTreeNode root) {

        if (root == null) {
            return 0;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();

        //Add root to the queue
        queue.add(root);

        int levelNodes;
        int height = 0;

        while (!queue.isEmpty()) {

            levelNodes = queue.size();
            height++;

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
        return height;
    }

    public static void main(String[] args) {

        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.left.left.right = new BinaryTreeNode(8);

        System.out.println("Tree Height " + heightOfBinaryTreeWithoutRecursion(root));
    }
}
