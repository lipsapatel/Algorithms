import Node.BinaryTreeNode;

import java.util.Stack;

/**
 * Non Recursive InOrder Traversal
 * (left,root,right)
 *
 * Go to left extremities and push it to the stack
 *
 * while(root != null) {
 *     push to stack1
 *     root = root.left
 * }
 *
 * If stack1 is empty then return
 *
 * Pop from the stack and print it
 * Add node at right to the stack
 *
 * Recursive
 * TC = O(n)
 * SC = O(n)
 *
 * Non Recursive
 * TC = O(n)
 * SC = O(n) - Because it case of left skewed binary tree it will store all nodes in stack
 * Best and Average case is O(logn)
 *
 */
public class BinaryTreeNonRecursiveInOrderTraversal {

    private void recursiveInOrderTraversal(BinaryTreeNode root) {
        if (root != null) {
            recursiveInOrderTraversal(root.left);
            System.out.print(root.data + "->");
            recursiveInOrderTraversal(root.right);
        }
    }

    private void nonRecursiveInOrderTraversal(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack1 = new Stack<BinaryTreeNode>();

        System.out.println("Binary Tree using Non Recursive Inorder Traversal: ");
        while(true) {

            //Go to left extremities and push it to stack
            while(root != null) {
                stack1.push(root);
                root = root.left;
            }

            if(stack1.isEmpty()) {
                return;
            }

            //Pop from stack
            BinaryTreeNode poppedNode = stack1.pop();

            //Print it
            System.out.print(poppedNode.data + "->");

            //Add the right node
            root = poppedNode.right;
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

        BinaryTreeNonRecursiveInOrderTraversal binaryTreeNonRecursiveInOrderTraversal = new BinaryTreeNonRecursiveInOrderTraversal();

        System.out.println("Recursive In Order Traversal");
        binaryTreeNonRecursiveInOrderTraversal.recursiveInOrderTraversal(root);
        System.out.println();

        binaryTreeNonRecursiveInOrderTraversal.nonRecursiveInOrderTraversal(root);
    }
}
