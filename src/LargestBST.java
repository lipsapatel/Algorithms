import Node.BinaryTreeNode;

/**
 * Given a binary tree, find the largest subtree which is also a BST.
 * Here largest subtree means subtree with maximum number of nodes.
 *
 * resources/LargestBST.png
 *
 * Approach:
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * 1) Leaf node is a valid BST
 * 2) If both left and right subtrees are BST and root's value is greater than left max and less than right min, then the
 * subtree at current root is BST.
 * 3) We will have a class with following variables
 * isBST
 * count
 * min
 * max
 */
public class LargestBST {

    public static class BSTInfo {
        boolean isBST;
        int count = 0;
        int min;
        int max;

        BSTInfo(boolean b, int c, int min, int max) {
            this.isBST = b;
            this.count = c;
            this.min = min;
            this.max = max;
        }
    }

    private static int findLargestBST(BinaryTreeNode root) {

        BSTInfo bstInfo = findLargestBSTHelper(root);
        return bstInfo.count;
    }

    private static BSTInfo findLargestBSTHelper(BinaryTreeNode root) {

        //Base Case
        if (root == null) {
            return new BSTInfo(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        BSTInfo left = findLargestBSTHelper(root.left);
        BSTInfo right = findLargestBSTHelper(root.right);

        if (left.isBST && right.isBST && root.data >= left.max && root.data <= right.min) {
            int min = Math.min(root.data, left.min);
            int max = Math.max(root.data, right.max);

            return new BSTInfo(true, 1 + left.count + right.count, min, max);
        }

        return new BSTInfo(false, Math.max(left.count, right.count), Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(100);
        root.left = new BinaryTreeNode(300);
        root.left.left = new BinaryTreeNode(200);
        root.left.right = new BinaryTreeNode(400);
        root.right = new BinaryTreeNode(500);
        root.right.right = new BinaryTreeNode(700);
        root.right.left = new BinaryTreeNode(600);

        System.out.println("The size of largest BST is " + findLargestBST(root));
    }
}
