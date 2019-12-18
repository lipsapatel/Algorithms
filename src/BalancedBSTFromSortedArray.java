import Node.BinaryTreeNode;

/**
 * Given a sorted array a of size N containing distinct integers, you have to build a balanced binary search tree of a.
 *
 * A binary search tree is balanced if for each node, a condition holds that the number of nodes in the left subtree and the
 * number of nodes in the right subtree differ by at most 1.
 *
 * Output:
 *
 * Return the root of the balanced BST.
 * There can be multiple balanced BST for given input. So you are free to return any of the valid one.
 *
 * Sample Input:
 a = [8 10 12 15 16 20 25]

 Sample Output:

 Root of the Balanced BST where:
 15 is the root node.
 10 is 15's left child.
 20 is 15's right child.
 8 is 10's left child.
 12 is 10's right child.
 16 is 20's left child.
 25 is 20's right child.

In this problem we need to consider weight-balanced tree.

 Approach:

 Time Complexity: O(n)
 Space Complexity: O(n)

 1) Calculate mid
 2) Create root node with mid.
 3) Make recursive call for left and right
 4) return root

 */
public class BalancedBSTFromSortedArray {

    private static BinaryTreeNode balancedBSTFromSortedArray(int[] a) {
        return balancedBSTFromSortedArrayHelper(a, 0, a.length - 1);
    }

    private static BinaryTreeNode balancedBSTFromSortedArrayHelper(int[] a, int start, int end) {

        if (start > end) {
            return null;
        }

        int mid = start + (end - start)/2;

        BinaryTreeNode root = new BinaryTreeNode(a[mid]);

        root.left = balancedBSTFromSortedArrayHelper(a, start, mid - 1);
        root.right = balancedBSTFromSortedArrayHelper(a, mid + 1, end);
        return root;
    }

    public static void main(String[] args) {
        int[] a = {8, 10, 12, 15, 16, 20, 25};

        BinaryTreeNode root = balancedBSTFromSortedArray(a);

        System.out.println("Root " + root.data);
        System.out.println("Root left " + root.left.data);
        System.out.println("Root right " + root.right.data);
    }
}
