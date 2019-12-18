import Node.BinaryTreeNode;

/**
 * Given a binary tree, find the number of unival subtrees - The unival tree is a tree which has the same value for every node in it.
 *
 * Approach:
 *
 * 1) Maintain global variable count.
 * 2) Do post order traversal.
 * 3) All null nodes return true for unival
 * 4) Leaf nodes are unival
 * 5) For any other node, check if the value matches it's left and right child and if both left and right child are unival as well.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class FindNumberOfUniValSubtree {

    private static int count = 0;

    private static int findSingleValueTrees(BinaryTreeNode root) {

        findSingleValueTreesHelper(root);
        return count;
    }

    private static boolean findSingleValueTreesHelper(BinaryTreeNode root) {

        //Base Case
        if (root == null) {
            return true;
        }

        //Leaf nodes
        if (root.left == null && root.right == null) {
            count++;
            return true;
        }

        boolean left = findSingleValueTreesHelper(root.left);
        boolean right = findSingleValueTreesHelper(root.right);

        if (left && right && (root.left == null || root.data == root.left.data) && (root.right == null || root.data == root.right.data)) {
            count++;
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(5);
        root.left.left = new BinaryTreeNode(5);

        root.left.right = new BinaryTreeNode(5);

        root.right = new BinaryTreeNode(5);
        root.right.left = new BinaryTreeNode(4);
        root.right.right = new BinaryTreeNode(5);

        System.out.println("The number of single value trees are " + findSingleValueTrees(root));
    }
}
