import Node.BinaryTreeNode;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Binary Tree can also be traversed using Breadth First Search
 * or Level Order Traversal
 *
 * What is Breadth First Search?
 * Breadth First Search - BFS is algorithm for traversing or searching
 * a graph or tree data structure.
 *
 * It starts at the tree root and explores the neighbour nodes first,
 * before moving to the next level neighbours.
 *
 * images/BinaryTreeTraversal_BreadthFirstSearch.png
 *
 * Take Queue
 * Insert root node in the Queue
 * While queue is not empty
 * remove the node
 * print it
 * and insert left and right children
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) for worst case/Average Case
 * O(1) in best case - Tree is like linked list
 */
public class BinaryTreeTraversal_BreadthFirstSearch {

    private static void breadthFirstSearch(BinaryTreeNode root) {

        if (root == null) {
            return;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();

        //Add root node
        queue.add(root);

        while(!queue.isEmpty()) {

            BinaryTreeNode removedNode = queue.remove();

            System.out.print(removedNode.data + " --> ");

            if (removedNode.left != null) {
                queue.add(removedNode.left);
            }
            if (removedNode.right != null) {
                queue.add(removedNode.right);
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

        System.out.println("Breadth First Search : ");
        breadthFirstSearch(root);
    }
}
