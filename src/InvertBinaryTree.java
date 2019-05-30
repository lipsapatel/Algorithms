import Node.BinaryTreeNode;

/**
 * Invert a binary tree.

 Example:

 Input:

 4
 /   \
 2     7
 / \   / \
 1   3 6   9
 Output:

 4
 /   \
 7     2
 / \   / \
 9   6 3   1
 Trivia:
 This problem was inspired by this original tweet by Max Howell:

 Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so f*** off.

 Time Complexity: O(n) - all nodes are visited
 Space Complexity: O(n) - Worst case if it's a skewed binary tree.
 */
public class InvertBinaryTree {

    public static BinaryTreeNode invertTree(BinaryTreeNode root) {
        //Base Case
        if (root == null) {
            return null;
        }

        //Recursive Case
        BinaryTreeNode left =  invertTree(root.left);
        BinaryTreeNode right = invertTree(root.right);

        //Invert
        root.left = right;
        root.right = left;
        return root;
    }

    public static void displayBinaryTree(BinaryTreeNode root) {

        if (root != null) {
            displayBinaryTree(root.left);
            System.out.print(root.data + "-->");
            displayBinaryTree(root.right);
        }
    }
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(4);
        root.left = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(1);
        root.left.right = new BinaryTreeNode(3);

        root.right = new BinaryTreeNode(7);
        root.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(9);

        displayBinaryTree(root);
        invertTree(root);
        System.out.println();
        displayBinaryTree(root);

    }
}
