import Node.BinaryTreeNode;

import java.util.Stack;

/**
 * Given binary tree, do the Depth First Search or Traversal
 *
 * Depth First Search means you go the maximum depth first.
 *
 * resources/BinaryTreeTraversal_DepthFirstSearch.png
 *
 * Take stack
 * Add root node to the stack
 * while stack is not empty
 * popped the node
 * print it
 * add right and left children
 *
 */
public class BinaryTreeTraversal_DepthFirstSearch {

    private static void depthFirstSearch(BinaryTreeNode root) {

        if (root == null) {
            return;
        }

        Stack<BinaryTreeNode> stack = new Stack<>();

        //Push root node
        stack.add(root);

        while (!stack.isEmpty()) {

            //Popped the node
            BinaryTreeNode poppedNode = stack.pop();

            System.out.print(poppedNode.data + " --> ");

            if (poppedNode.right != null) {
                stack.add(poppedNode.right);
            }

            if (poppedNode.left != null) {
                stack.add(poppedNode.left);
            }
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.right = new BinaryTreeNode(3);
        root.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(7);

        System.out.println("Depth-First-Search : ");
        depthFirstSearch(root);
    }
}
