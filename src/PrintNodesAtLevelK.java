import Node.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Print nodes at level k
 * Given binary tree, print nodes at level k
 *
 * Time Complexity: O(n) or 2 ^ k
 * Space Complexity: O(n) At most there will be n/2 nodes in queues, considering all leaf nodes which are n/2
 *
 * Use queue
 *
 *         4
 *       /   \
 *      6     2
 *     /
 *    8
 *
 *   K = 2
 *   Output: 6, 2
 */
public class PrintNodesAtLevelK {

    private static void printNodesAtLevelK(BinaryTreeNode root, int k) {

        if (root == null) {
            return;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();

        queue.add(root);
        int levelNodes = 0;

        while (k != 0) {

            k--;
            levelNodes = queue.size();

            while (levelNodes > 0) {

                BinaryTreeNode removedNode = queue.remove();

                if (k == 0) {
                    System.out.print(removedNode.data);
                }

                if (removedNode.left != null) {
                    queue.add(removedNode.left);
                }

                if (removedNode.right != null) {
                    queue.add(removedNode.right);
                }
                levelNodes--;

                System.out.println();
            }
        }
    }

    private static void printNodesAtLevelKUsingDFS(BinaryTreeNode root, int k) {
        printNodes(root, k);
    }

    //Time Complexity: O(n) or 2 ^ k
    //Space Complexity: O(logn) or k
    //Worst case is O(n) if the tree is left skewed.
    //so the space complexity is the height of the tree.
    //Code is intuitive and space complexity is the height of tree.
    private static void printNodes(BinaryTreeNode root, int k) {

        if (root == null) {
            return;
        }

        //Base Case
        if (k == 1) {
            System.out.println(root.data);
            return;
        }

        printNodes(root.left, k - 1);
        printNodes(root.right, k - 1);
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(4);
        root.left = new BinaryTreeNode(6);
        root.right = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(8);

        printNodesAtLevelK(root, 2);
        printNodesAtLevelKUsingDFS(root, 2);
    }
}
