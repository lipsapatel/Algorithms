import Node.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

/**
 * Print the bottom view of Binary Tree
 *
 * Bottom view mean when you look at the tree from the bottom, the node you will see.
 *
 * resources/BottomViewOfBinaryTree.png
 *
 * Approach:
 *
 * 1) Similar to "Print vertical node in Binary Tree" or "Print top view of Binary Tree"
 * 2) Take variable level
 * 3) Do level - 1 when go to left
 * 4) Do level + 1 when go to right
 * 5) Level order traversal using queue
 * 6) Treemap with store the level and nodes
 * 7) Store the most recent encounter node in treemap for that level
 * 8) At the end traverse the treemap and print the nodes.
 *
 *
 */
public class PrintTheBottomViewOfBinaryTree {

    private static TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    public static class QueuePack {
        int level;
        BinaryTreeNode node;

        public QueuePack(int level, BinaryTreeNode node) {
            this.level = level;
            this.node = node;
        }
    }

    private static void printTheBottomViewOfBinaryTree(BinaryTreeNode root, int level) {

        if (root == null) {
            return;
        } else {

            Queue<QueuePack> queue = new LinkedList<>();

            queue.add(new QueuePack(level, root));

            while(!queue.isEmpty()) {

                QueuePack queuePack = queue.remove();

                int level1 = queuePack.level;
                BinaryTreeNode node = queuePack.node;

                //Keep updating the treemap so that it will have last entry from each level
                treeMap.put(level1, node.data);

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
        root.left.right = new BinaryTreeNode(5);
        root.left.right.left = new BinaryTreeNode(6);
        root.left.right.right = new BinaryTreeNode(7);
        root.right.right = new BinaryTreeNode(8);

        printTheBottomViewOfBinaryTree(root, 0);
        display();
    }

    public static void display() {
        Set<Integer> keySet = treeMap.keySet();

        for (Integer key: keySet) {
            System.out.print(treeMap.get(key) + " ");
        }
    }
}
