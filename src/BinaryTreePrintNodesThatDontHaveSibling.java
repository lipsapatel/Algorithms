import Node.BinaryTreeNode;

/**
 * Given a binary tree, print all the nodes that don't have siblings.
 *
 * Sibling node has the same parent as the given node.
 * So you need to print the nodes who is single child of his parent.
 *
 * resources/BinaryTreePrintAllNodesThatDontHaveSibling.png
 *
 * Do the inorder traversal
 * Check if that node is the only child, if yes then print that node.
 */
public class BinaryTreePrintNodesThatDontHaveSibling {

    private static void printNodesThatDontHaveSibling(BinaryTreeNode root) {

        if (root != null) {
            printNodesThatDontHaveSibling(root.left);

            if (root.left == null && root.right != null) {
                System.out.print( root.right.data + " ");
            }

            if (root.left != null && root.right == null) {
                System.out.print(root.left.data + " ");
            }

            printNodesThatDontHaveSibling(root.right);
        }
    }

    public static void main(String[] args) {

        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.left.right.left = new BinaryTreeNode(7);
        root.left.left.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(8);
        root.right.right.left = new BinaryTreeNode(9);

        System.out.println("Nodes that don't have siblings are");
        printNodesThatDontHaveSibling(root);
    }
}
