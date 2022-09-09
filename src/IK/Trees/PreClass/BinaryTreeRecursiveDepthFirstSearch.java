package IK.Trees.PreClass;

import Node.BinaryTreeNode;

/**
 * Recursive Depth First Traversal of Binary Tree
 *
 * 1) PreOrder - root, left, right
 * 2) InOrder - left, root, right
 * 3) PostOrder - left, right, root
 * 4) InOrder traversal of Binary Search tree gives sorted order in ascending
 * 5) Base Case: If root == null return
 *
 * TC: O(n) for all 3 traversal
 * SC: (logn) - Recursive call stack space if it's balanced binary tree
 * O(n) - If it's not balanced binary tree
 */
public class BinaryTreeRecursiveDepthFirstSearch {

    private static void preOrder(BinaryTreeNode root) {

        //Base Case
        if(root == null) {
            return;
        }

        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    private static void inOrder(BinaryTreeNode root) {

        //Base Case
        if(root == null) {
            return;
        }

        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);
    }

    private static void postOrder(BinaryTreeNode root) {

        //Base Case
        if(root == null) {
            return;
        }

        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);
    }

    public static void main(String[] args) {
        BinaryTreeNode node = new BinaryTreeNode(1);
        node.left = new BinaryTreeNode(2);
        node.right = new BinaryTreeNode(3);

        node.left.left = new BinaryTreeNode(4);
        node.left.right = new BinaryTreeNode(5);

        node.right.left = new BinaryTreeNode(6);
        node.right.right = new BinaryTreeNode(7);

        System.out.println("PreOrder Traversal");
        preOrder(node);

        System.out.println("Inorder Traversal");
        inOrder(node);

        System.out.println("Postorder Traversal");
        postOrder(node);

    }
}
