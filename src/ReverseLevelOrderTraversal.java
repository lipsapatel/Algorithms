import Node.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Reverse Level Order Traversal
 *
 * Given a binary tree, do the reverse level order traversal.
 * In reverse level order traversal, we first need to print the last level, followed by
 * second last level up to the root, which is the first level.
 *
 * resources/ReverseLevelOrderTraversal.png
 *
 * Approach:
 *
 * 1) Use queue and stack
 * 2) Instead of printing from queue push to stack
 * 3) Print from stack
 * 4) Add root to queue
 * 5) While queue is not empty
 * 6) Remove the node and add to stack
 * 7) If left and right is not null, add them to queue
 *
 * Time Complexity: O(n)
 *
 */
public class ReverseLevelOrderTraversal {

    private static void reverseLevelOrderTraversal(BinaryTreeNode root) {

        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();

        if (root == null) {
            return;
        }

        //Add root to queue
        queue.add(root);

        //Initialize stack
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();

        while(!queue.isEmpty()) {

            BinaryTreeNode removedNode = queue.remove();

            //Add to stack instead of printing
            stack.push(removedNode);

            if (removedNode.left != null) {
                queue.add(removedNode.left);
            }

            if (removedNode.right != null) {
                queue.add(removedNode.right);
            }
        }

        //Print all the elements from stack
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().data + " ");
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

        reverseLevelOrderTraversal(root);
    }
}
