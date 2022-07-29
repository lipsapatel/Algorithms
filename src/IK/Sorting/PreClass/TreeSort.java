package IK.Sorting.PreClass;

import Node.BinaryTreeNode;

import java.util.Arrays;

/**
 * Sort array using TreeSort
 *
 * Tree Sort is based on Binary Search Tree.
 *
 * Approach
 * 1) Create Binary Search tree from array
 * 2) Perform inorder traversal which gives ascending sorted order
 * 3) Replace array elements while performing inorder traversal.
 *
 * TC: O(nlogn) - Insert one element take logn so n elements takes nlogn. Inorder traversal takes n
 * SC: O(n) - Binary Search Tree + O(logn) recursive stack space for insert and inorder traversal
 *
 * Stable Sorting algorithm - order is maintained in case of duplicates
 *
 * If given tree in input then use tree sort. Database question like how to index value is (b+ tree) implemented.
 *
 *
 */
public class TreeSort {

    public static BinaryTreeNode root = null;
    public static int i = 0;

    public static void main(String[] args) {
        int[] a = {7, 12, 3, 4, 1, 6, 19};

        treeSort(a);
        System.out.println(Arrays.toString(a));
    }

    private static void treeSort(int[] a) {

        //Insert into Binary Search Tree
        for(int i = 0; i < a.length; i++) {
            root = insert(root, a[i]);
        }

        i = 0; //Reset the global index variable
        inorderTraversal(root, a);
    }

    private static BinaryTreeNode insert(BinaryTreeNode root, int id) {

        //Base Case
        if(root == null) {
            return new BinaryTreeNode(id);
        }

        if(id < root.data) {
            root.left = insert(root.left, id);
        } else {
            root.right = insert(root.right, id);
        }
        return root;
    }

    private static void inorderTraversal(BinaryTreeNode root, int[] a) {
        if(root != null) {
            inorderTraversal(root.left, a);
            a[i] = root.data;
            i = i + 1;
            inorderTraversal(root.right, a);
        }
    }
}
