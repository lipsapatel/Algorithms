import Node.BinaryTreeNode;

/**
 *
 * Print one path from root to leaf whose sum equals to k and return boolean if path exists
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) - O(h)
 *
 * Preorder traversal
 */
public class PrintAllPathsFromRootToLeafWhoseSumEqualsTok {

    private static boolean printAllPathsFromRootToLeaf(BinaryTreeNode root, int sum, String path) {

        //Base Case
        if (root == null) {
            return false;
        }

        path = path + " " + root.data;
        sum = sum - root.data;

        if (sum == 0 && root.left == null && root.right == null) { //sum == 0 and is leaf node
            System.out.println(path);
            return true;
        }

        //This is to print all paths
        boolean leftPathExists = printAllPathsFromRootToLeaf(root.left, sum, path);
        boolean rightPathExists = printAllPathsFromRootToLeaf(root.right, sum, path);

        return leftPathExists || rightPathExists;

        //This is to print only one path
        //return printAllPathsFromRootToLeaf(root.left, sum, path) || printAllPathsFromRootToLeaf(root.right, sum, path);
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(4);
        root.left = new BinaryTreeNode(6);
        root.left.left = new BinaryTreeNode(8);

        root.right = new BinaryTreeNode(2);
        root.right.right = new BinaryTreeNode(12);

        System.out.println("Path exists: " + printAllPathsFromRootToLeaf(root, 18, ""));
    }
}
