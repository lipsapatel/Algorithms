import Node.BinaryTreeNode;
/**
 * images/BinaryTreeHeight.png
 *
 * Get the height of the left subtree, leftHeight
 * Get the height of the right subtree, rightHeight
 * Take the max(leftHeight, rightHeight) and add 1 for root and return
 * Call Recursively
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class BinaryTreeHeight {

    private static int BinaryTreeHeight(BinaryTreeNode root) {
        if (root == null) {
            //return 0; //If counting number of nodes
            return -1; //If counting number of edges
        } else {
            return Math.max(BinaryTreeHeight(root.left), BinaryTreeHeight(root.right)) + 1;
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(10);
        root.right = new BinaryTreeNode(15);

        root.left.left = new BinaryTreeNode(20);
        root.left.right = new BinaryTreeNode(25);

        root.left.left.left = new BinaryTreeNode(30);

        root.left.right.left = new BinaryTreeNode(35);
        root.left.right.left.left = new BinaryTreeNode(40);

        root.left.right.left.left.right = new BinaryTreeNode(45);
        root.left.right.left.left.right.left = new BinaryTreeNode(50);

        System.out.println("The height of Binary Tree is " + BinaryTreeHeight(root));


    }
}
