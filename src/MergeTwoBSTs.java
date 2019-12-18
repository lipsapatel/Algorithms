import Node.BinaryTreeNode;

import java.util.ArrayList;

/**
 * Given two BSTs(Binary Search Trees) one with N1 number of nodes and other one with N2 number of nodes.
 *
 * Your task is to merge them such that:
 - Resultant tree is height balanced.
 - Resultant tree is a BST.
 - Resultant tree contains all values from given BST-1.
 - Resultant tree contains all values from given BST-2.
 - Size of the resultant tree is N1 + N2.
 - For any value, no of occurrences in resultant tree = no of occurrences in BST-1 + no of occurrences in BST-2. (If BST-1 contains 3 nodes with value 50 and BST-2 contains 4 nodes with value 50, then resultant tree should contain exactly 7 nodes with value 50.)

 Any resultant tree, satisfying all the above conditions will be considered as valid answer.

 Sample Input:
 Tree-1:

 2

 /  \

 1   3

 Tree-2:

 7

 /  \

 6   8

 Sample Output:
 (one possible resultant tree)

 6

 /  \

 2   7

 /  \   \

 1   3   8

Approach:
 1) Store values of BST1 in an array using in-order traversal
 2) Store values of BST2 in an array using in-order traversal
 3) Merge both the arrays (merge sort)
 4) Build height balanced BST recursively like this

 root = mergeArray[mid]
 root.left = build(start, mid - 1)
 root.right = build(mid + 1, end)

 Time Complexity: O(N1 + N2)
Create list1 - O(N1)
 Create list2 - O(N2)
 Merge sort for merging two lists - O(N1 + N2)
 Build Balanced binary BST - O(N1 + N2)

 Space Complexity: O(N1 + N2)
 list1 - O(N1)
 list2 - O(N2)
 merged list - O(N1 + N2)
 Stack call trace for building balanced BST - O(N1 + N2)
 */
public class MergeTwoBSTs {

    private static BinaryTreeNode mergeTwoBSTs(BinaryTreeNode root1, BinaryTreeNode root2) {

        //Create list from inorder traversal of first tree
        ArrayList<Integer> list1 = new ArrayList<>();
        inorderTraversal(root1, list1);

        //Create list from inorder traversal of second tree
        ArrayList<Integer> list2 = new ArrayList<>();
        inorderTraversal(root2, list2);

        //Merge two sorted list using merge sort
        ArrayList<Integer> list = mergeLists(list1, list2);

        //Build balanced BST using sorted list
        BinaryTreeNode root = buildBalancedBST(list, 0, list.size() - 1);

        return root;
    }

    private static BinaryTreeNode buildBalancedBST(ArrayList<Integer> list, int start, int end) {

        if (start > end) {
            return null;
        }

        if (start == end) {
            return new BinaryTreeNode(list.get(start));
        }

        int mid = start + (end - start)/2;
        BinaryTreeNode root =  new BinaryTreeNode(list.get(mid));
        root.left = buildBalancedBST(list, start, mid - 1);
        root.right = buildBalancedBST(list, mid + 1, end);

        return root;
    }

    private static ArrayList<Integer> mergeLists(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> list = new ArrayList<>();

        int i = 0;
        int j = 0;
        int list1Size = list1.size();
        int list2Size = list2.size();

        while (i < list1Size && j < list2Size) {
            if (list1.get(i) <= list2.get(j)) {
                list.add(list1.get(i));
                i++;
            } else {
                list.add(list2.get(j));
                j++;
            }
        }

        //Copy the remaining elements
        while (i < list1Size) {
            list.add(list1.get(i));
            i++;
        }

        while (j < list2Size) {
            list.add(list2.get(j));
            j++;
        }
        return list;
    }

    private static void inorderTraversal(BinaryTreeNode root, ArrayList<Integer> list) {

        if (root == null) {
            return;
        }

        inorderTraversal(root.left, list);
        list.add(root.data);
        inorderTraversal(root.right, list);
    }

    public static void main(String[] args) {
        BinaryTreeNode root1 = new BinaryTreeNode(2);
        root1.left = new BinaryTreeNode(1);
        root1.right = new BinaryTreeNode(3);

        BinaryTreeNode root2 = new BinaryTreeNode(7);
        root2.left = new BinaryTreeNode(6);
        root2.right = new BinaryTreeNode(8);

        BinaryTreeNode root = mergeTwoBSTs(root1, root2);

        printInorder(root);
    }

    private static void printInorder(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        printInorder(root.left);
        System.out.print(" " + root.data);
        printInorder(root.right);
    }
}
