import Node.BinaryTreeNode;

import java.util.Stack;

/**
 * To find the size of Binary tree in a non-recursive way, we will do preorder traversal
 * and push it to stack
 * every time we pop, increment size by one
 *
 * /images/BinaryTreeSizeNonRecursive.png
 */
public class BinaryTreeSizeNonRecursive {

    private static int getBinaryTreeSize(BinaryTreeNode root) {
        int size = 0;

        if (root != null) {
            Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();

            stack.push(root);
            while(!stack.isEmpty()) {

                BinaryTreeNode poppedNode = stack.pop();
                size++;

                if (poppedNode.right != null) {
                    stack.push(poppedNode.right);
                }

                if (poppedNode.left != null) {
                    stack.push(poppedNode.left);
                }
            }
        }
        return size;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);

        System.out.println("The size of Binary Tree is " + getBinaryTreeSize(root));
    }
}
