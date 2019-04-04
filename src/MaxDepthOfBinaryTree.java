import Node.BinaryTreeNode;

/**
 * Given a binary tree, find its maximum depth.

 The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

 Note: A leaf is a node with no children.

 Example:

 Given binary tree [3,9,20,null,null,15,7],

 3
 / \
 9  20
 /  \
 15   7
 return its depth = 3.
 */
public class MaxDepthOfBinaryTree {

    private static int maxDepth(BinaryTreeNode root) {

        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(3);
        root.left = new BinaryTreeNode(9);
        root.right = new BinaryTreeNode(20);

        root.right.left = new BinaryTreeNode(15);
        root.right.right = new BinaryTreeNode(7);

        System.out.println("Depth is: " + maxDepth(root));
    }
}
