import Node.BinaryTreeNode;

/**
 * In a binary tree, check if two nodes are siblings or has same parent.
 *
 * resources/CheckIfTwoNodesAreSiblingOrSameParent.png
 *
 * Approach:
 *
 * 1) Given root, Node x and Node y
 * 2) Check if x and y are child of root. If yes return true
 * (root.left == x && root.right == y) || (root.left == y && root.right == x)
 * 3) Else make recursive call to root.left and root.right
 * 4) If root == null, return false
 */
public class CheckIfTwoNodesAreSiblingsOrSameParent {

    private static boolean checkIfTwoNodesAreSiblingsOrSameParent(BinaryTreeNode root,
                                                                  BinaryTreeNode x, BinaryTreeNode y) {

        if (root == null) {
            return false;
        }

        return ((root.left == x && root.right == y) || (root.left == y && root.right == x)
                || checkIfTwoNodesAreSiblingsOrSameParent(root.left, x, y) ||
                checkIfTwoNodesAreSiblingsOrSameParent(root.right, x, y));
    }

    public static void main(String[] args) {

        BinaryTreeNode root = new BinaryTreeNode(1);
        BinaryTreeNode x1 = new BinaryTreeNode(2);
        BinaryTreeNode y1 = new BinaryTreeNode(3);
        root.left = x1;
        root.right = y1;
        root.left.left = new BinaryTreeNode(4);
        root.right.left = new BinaryTreeNode(6);
        BinaryTreeNode x2 = new BinaryTreeNode(7);
        BinaryTreeNode y2 = new BinaryTreeNode(9);
        root.right.left.left = new BinaryTreeNode(8);
        root.right.left.right = y2;
        root.left.left.left = x2;

        System.out.println("Node " + x1.data + " and Node " + y1.data + " are siblings??? "
                + checkIfTwoNodesAreSiblingsOrSameParent(root, x1, y1));
        System.out.println("Node " + x2.data + " and Node " + y2.data + " are siblings??? "
                + checkIfTwoNodesAreSiblingsOrSameParent(root, x2, y2));
    }

}
