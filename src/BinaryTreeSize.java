import Node.BinaryTreeNode;

/**
 * Given binary tree, find the size of the tree.
 *
 * Size of the tree = number of nodes in the tree.
 *
 * Input: Binary Tree
 * Output: Size of the tree
 *
 * resources/BinaryTreeSize.png
 *
 * Start from root
 * If root == null return 0
 * size = 1(root) + size of left subtree + size of right subtree
 *
 * resources/BinaryTreeSizeTrace.png
 *
 * Time Complexity: O(n)
 */
public class BinaryTreeSize {

    private static int getBinaryTreeSize(BinaryTreeNode root) {

        if (root == null) {
            return 0;
        }

        return 1 + getBinaryTreeSize(root.left) + getBinaryTreeSize(root.right);
    }

    public static void main(String[] args) {

        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(15);
        root.right = new BinaryTreeNode(25);
        root.left.left = new BinaryTreeNode(20);
        root.left.right = new BinaryTreeNode(30);
        root.right.left = new BinaryTreeNode(2);
        root.right.right = new BinaryTreeNode(10);

        System.out.println("Size of the Tree is : " + getBinaryTreeSize(root));
    }
}
