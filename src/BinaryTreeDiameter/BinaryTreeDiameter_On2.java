package BinaryTreeDiameter;

import Node.BinaryTreeNode;
/**
 * Given Binary Tree, find Diameter of it.
 *
 * Diameter of Tree is the longest path between the two nodes of a tree.
 * The path may or may not pass through root.
 *
 * images/DiameterBinaryTree.png
 *
 * Diameter of a tree = Max(left subtree diameter, right subtree diameter, Longest
 *                          Path between two nodes which passes through root)
 *
 * Longest Path which passes through root = 1 + height of left subtree + height of right subtree
 *
 * Time Complexity: O(n2)
 * When calculating Diameter recursively, every iteration for every node is
 * calculating height of tree separately in which we iterate tree from top to bottom
 *
 * Find the height of left subtree
 * Find the height of right subtree
 * Find the left subtree diameter
 * Find the right subtree diameter
 * return Maximum(max(Left subtree Diameter, right subtree Diameter), (1+ left subtree
 *                  height + right subtree height))
 */
public class BinaryTreeDiameter_On2 {

    private static int BinaryTreeHeight(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(BinaryTreeHeight(root.left), BinaryTreeHeight(root.right));
        }
    }
    private static int BinaryTreeDiameter(BinaryTreeNode root) {

        if (root == null) {
            return 0;
        } else {

            int leftSubtreeHeight = BinaryTreeHeight(root.left);
            int rightSubtreeHeight = BinaryTreeHeight(root.right);

            int leftSubtreeDiameter = BinaryTreeDiameter(root.left);
            int rightSubtreeDiameter = BinaryTreeDiameter(root.right);

            return Math.max(Math.max(leftSubtreeDiameter, rightSubtreeDiameter), 1 + leftSubtreeHeight + rightSubtreeHeight);
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.left.right.left = new BinaryTreeNode(6);
        root.left.right.left.right = new BinaryTreeNode(7);
        root.left.left.left = new BinaryTreeNode(8);

        System.out.println("The Diameter of Binary Tree is " + BinaryTreeDiameter(root));

    }
}
