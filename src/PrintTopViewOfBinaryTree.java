import Node.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Print the top view of a binary tree
 *
 * Given binary tree, print the top view of it.
 *
 * Top view means when you look the tree from the top, the nodes you will see will be called
 * top view of tree.
 *
 * resources/TopViewOfBinaryTree.png
 *
 * Approach:
 *
 * 1) Similar to "print vertical order nodes"
 * 2) Modified the code so that it will print only the first node in vertical order
 * 3) Take variable level
 * 4) level - 1 when go to left
 * 5) level + 1 when go to right
 * 6) We need to do level order traversal using queue to ensure that the top most
 * node is visited first at any level
 * 7) We will create QueuePack class which contains BinaryTreeNode and level.
 * 8) If it's the first element to be inserted for that level in TreeMap then print it.
 */
public class PrintTopViewOfBinaryTree {

    private static TreeMap<Integer,Integer> treeMap = new TreeMap<>();

    public static class QueuePack {

        int level;
        BinaryTreeNode node;

        public QueuePack(int level, BinaryTreeNode node) {
            this.level = level;
            this.node = node;
        }
    }

    private static void printTopViewOfBinaryTree(BinaryTreeNode root, int level) {

        if (root == null) {
            return;
        } else {

            //Queue for level order traversal
            Queue<QueuePack> queue = new LinkedList<>();

            //Add root with level 0
            queue.add(new QueuePack(0, root));

            while (!queue.isEmpty()) {

                QueuePack queuePack = queue.remove();

                BinaryTreeNode node = queuePack.node;
                int level1 = queuePack.level;

                if (treeMap.containsKey(level1)) {
                    //Do nothing
                } else {
                    System.out.print(node.data + " ");
                    treeMap.put(level1, node.data);
                }

                //Add the left and right child to queue
                if (node.left != null) {
                    queue.add(new QueuePack(level1 - 1, node.left));
                }

                if (node.right != null) {
                    queue.add(new QueuePack(level1 + 1, node.right));
                }
            }
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.left.left = new BinaryTreeNode(8);
        root.left.left.right = new BinaryTreeNode(9);
        root.left.right = new BinaryTreeNode(5);
        root.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(7);

        printTopViewOfBinaryTree(root, 0);
    }
}


