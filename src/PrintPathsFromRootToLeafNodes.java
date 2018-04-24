import Node.BinaryTreeNode;

/**
 * Print paths from root to all leaf nodes in Binary Tree.
 *
 * resources/PrintPathsFromRootToLeafNodes.png
 *
 * Approach:
 *
 * 1) Do preorder traversal
 * 2) Take two variable path and pathLength
 * 3) Store root.data in path
 * 4) Check if you are at leaf node (root.left == null && root.right == null)
 * 5) If yes then print the path
 * 6) Else make recursive call to root.left and root.right. Pass the path and pathLevel
 * so that at each level have it's own copy of path and pathLength.
 */
public class PrintPathsFromRootToLeafNodes {

    private static int pathLength = 0;
    private static int[] path = new int[500];

    private static void pathFromRootToLeafNode(BinaryTreeNode root, int pathLength, int[] path) {

        if (root == null) {
            return;
        }

        path[pathLength++] = root.data;

        if (root.left == null && root.right == null) {
            printPathFromRootToLeafNode(path, pathLength);
        } else {
            pathFromRootToLeafNode(root.left, pathLength, path);
            pathFromRootToLeafNode(root.right, pathLength, path);
        }
    }

    private static void printPathFromRootToLeafNode(int[] path, int pathLength) {

        for (int i = 0; i < pathLength; i++) {
            System.out.print(" " + path[i]);
        }

        System.out.println();
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode (2);
        root.right = new BinaryTreeNode (3);
        root.left.left = new BinaryTreeNode (4);
        root.left.left.left = new BinaryTreeNode (7);
        root.right.left = new BinaryTreeNode (6);
        root.right.left.left = new BinaryTreeNode (8);
        root.right.left.right = new BinaryTreeNode (9);

        int [] path = new int [100];
        pathFromRootToLeafNode(root, 0, path);
    }
}
