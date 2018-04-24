import Node.BinaryTreeNode;
/**
 * Means number of edges between two nodes.
 * Find the number of nodes
 * Number of edges = number of nodes - 1
 */
public class BinaryTreeDistanceFromRootToGivenNode {

    private static int PathLength(BinaryTreeNode root, int n1) {

        int numberOfNodes = 0;

        if (root == null) {
            return 0;
        }

        if (root.data == n1 || (numberOfNodes = PathLength(root.left, n1)) > 0
                || (numberOfNodes = PathLength(root.right, n1)) > 0) {
            return numberOfNodes + 1;
        }

        return 0;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(10);
        root.right = new BinaryTreeNode(15);
        root.left.left = new BinaryTreeNode(20);
        root.left.right = new BinaryTreeNode(25);
        root.right.left = new BinaryTreeNode(30);
        root.right.right = new BinaryTreeNode(35);
        root.left.right.right = new BinaryTreeNode(45);

        System.out.println("Distance from root to 45 is : "
                + (PathLength(root, 45)-1));
    }
}
