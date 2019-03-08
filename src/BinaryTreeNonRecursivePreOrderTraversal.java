import Node.BinaryTreeNode;

import java.util.Stack;

/**
 * Non recursive preorder traversal
 * (root, left, right)
 *
 * images/BinaryTreeNonRecursivePreOrderTraversal.png
 *
 * Follow the preorder traversal with right first
 *
 * push root node in stack1
 *
 * while stack1 is not empty
 *
 * popped node from stack1
 * Print the popped node
 *
 * push popped node right and left.
 *
 * Space Complexity: O(h) for recursive stack frame and non recursive too
 * Height of the tree in worst case will be n - 1, so space complexity in worst case will be O(n)
 * Height of the tree in best and average case will be logn, so space complexity in worst case will be O(logn)
 * Time Complexity: O(n)
 */
public class BinaryTreeNonRecursivePreOrderTraversal {

    //Recursive Pre Order Traversal
    private void recursivePreOrderTraversal(BinaryTreeNode root) {
        if (root != null) {
            System.out.print(root.data + "->");
            recursivePreOrderTraversal(root.left);
            recursivePreOrderTraversal(root.right);
        }
    }

    //Non-recursive pre order traversal
    private void nonRecursivePreOrderTraversal(BinaryTreeNode root) {
        if (root != null) {

            System.out.println("Non Recursive Pre Order Traversal:");
            Stack<BinaryTreeNode> stack1 = new Stack<BinaryTreeNode>();

            //Push root node
            stack1.push(root);

            while(!stack1.isEmpty()) {
                //Pop from stack1
                BinaryTreeNode poppedNode = stack1.pop();

                System.out.print(poppedNode.data + "->");

                //Push right
                if (poppedNode.right != null) {
                    stack1.push(poppedNode.right);
                }

                //Push left
                if (poppedNode.left != null) {
                    stack1.push(poppedNode.left);
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

        root.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(7);

        BinaryTreeNonRecursivePreOrderTraversal binaryTreeNonRecursivePreOrderTraversal = new BinaryTreeNonRecursivePreOrderTraversal();

        System.out.println("Recursive Pre Order Traversal");
        binaryTreeNonRecursivePreOrderTraversal.recursivePreOrderTraversal(root);
        System.out.println();

        binaryTreeNonRecursivePreOrderTraversal.nonRecursivePreOrderTraversal(root);
    }
}
