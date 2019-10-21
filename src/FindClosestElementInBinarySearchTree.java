import Node.BinaryTreeNode;

/**
 * Find closest element in Binary Search Tree
 * Given a binary search tree and a target node K.
 * The task is to find the node with minimum absolute difference with given target value K.
 *
 * resources/FindClosestElementInBinarySearchTree.png
 *
 * // For above binary search tree
 Input  :  k = 4
 Output :  4

 Input  :  k = 18
 Output :  17

 Input  :  k = 12
 Output :  9

 Approach: -
 1) pre order traversal of binary search tree
 2) If root.data is equal to k set that as minDiff and minDiffKey and return
 3) Calculate and update minDiff and minDiffKey
 4) If k is greater, then root.data, go to right
 5) else go to left

 Time Complexity: O(n) skewed binary search tree
 Space Complexity: O(n) skewed binary search tree

 Leet Code Solution:

 //TC = O(n) SC = O(n)
 private int minDiffKey;
 private double minDiff;

 public int closestValue(TreeNode root, double target) {

 minDiffKey = -1;
 minDiff = Double.MAX_VALUE;

 findClosestValue(root, target);

 return minDiffKey;
 }

 public void findClosestValue(TreeNode root, double k) {

 //Base Case
 if (root == null) {
 return;
 }

 if (root.val == k) {
 minDiffKey = root.val;
 minDiff = 0;
 return;
 }

 if (Math.abs(root.val - k) <= minDiff) {
 minDiff = Math.abs(root.val - k);
 minDiffKey = root.val;
 }

 if (k < root.val) {
 findClosestValue(root.left, k);
 } else {
 findClosestValue(root.right, k);
 }
 }
 */
public class FindClosestElementInBinarySearchTree {

    private static int minDiff;
    private static int minDiffKey;

    private static int findClosest(BinaryTreeNode root, int k) {

        minDiff = Integer.MAX_VALUE;
        minDiffKey = -1;

        findClosestElement(root, k);

        return minDiffKey;
    }

    private static void findClosestElement(BinaryTreeNode root, int k) {

        //Base Case
        if (root == null) {
            return;
        }

        if (root.data == k) {
            minDiff = 0;
            minDiffKey = k;
            return;
        }

        if (minDiff > Math.abs(root.data - k)) {
            minDiff = Math.abs(root.data - k);
            minDiffKey = root.data;
        }

        if (k > root.data) {
            findClosestElement(root.right, k);
        } else {
            findClosestElement(root.left, k);
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(9);
        root.left = new BinaryTreeNode(4);
        root.left.left = new BinaryTreeNode(3);

        root.left.right = new BinaryTreeNode(6);
        root.left.right.left = new BinaryTreeNode(5);

        root.left.right.right = new BinaryTreeNode(7);
        root.right = new BinaryTreeNode(17);
        root.right.right = new BinaryTreeNode(22);

        System.out.println("The closest element to given target is: " + findClosest(root, 12));
        System.out.println("The closest element to given target is: " + findClosest(root, 18));
        System.out.println("The closest element to given target is: " + findClosest(root, 4));
    }
}
