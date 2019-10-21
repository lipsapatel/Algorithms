import Node.BinaryTreeNode;

/**
 * Print All Paths from root in a Binary tree whose sum is equal to a given number.
 *
 * Given a binary tree and X, print all the paths starting from root so that sum of all
 * the nodes
 * in path equals to a given number.
 *
 * resources/PrintAllPathsFromRootWhoseSumEqualToGivenNumber.png
 *
 * Approach:
 *
 * 1) Global variable String path
 * 2) Do preorder traversal
 * 3) If root is greater than sum then return
 * 4) If not then add root.data to path
 * 5) Update sum = sum - root.data
 * 6) If sum == 0 then print path
 *
 * This path is not from root to leaf
 * This path is from root to any node.
 */
public class PrintAllPathsFromRootWhoseSumEqualToGivenNumber {

    private static String path;

    private static void printAllPathsFromRootWhoseSumEqualToGivenNumber(BinaryTreeNode root,
                                                                        int sum, String path) {

        if (root != null) {

            if (root.data > sum) {
                return;
            } else {

                path = path + " " + root.data;
                sum = sum - root.data;

                if (sum == 0) {
                    System.out.println(path);
                }

                printAllPathsFromRootWhoseSumEqualToGivenNumber(root.left, sum, path);
                printAllPathsFromRootWhoseSumEqualToGivenNumber(root.right, sum, path);
            }
        }
    }

    public static void main(String[] args) {

        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(7);
        root.left.right = new BinaryTreeNode(5);
        root.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(7);

        printAllPathsFromRootWhoseSumEqualToGivenNumber(root, 3, "");
    }
}
