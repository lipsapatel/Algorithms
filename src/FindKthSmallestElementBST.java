import Node.BinaryTreeNode;

/**
 * Given BST and an integer k, find kth smallest element.
 *
 *     2
 *    /  \
 *   1    3
 *
 *   k = 3
 *
 *   Output: 3
 *   Inorder traversal: 1, 2, 3
 *
 *   Approach:
 *
 *   Time Complexity: O(n) O(h + K)
 *   Space Complexity: O(n) O(h)
 *
 *   1) Do inorder traversal.
 *   2) Maintain the count variable
 *   3) When count == k, return root.data
 */
public class FindKthSmallestElementBST {

    private static int count = 0;

    private static int KthSmallestElement(BinaryTreeNode root, int k) {

        if (root != null) {

            int kthSmallestElement = KthSmallestElement(root.left, k);

            if (kthSmallestElement != -1) {
                return kthSmallestElement;
            }

            count++;
            if (count == k) {
                return root.data;
            }

            return KthSmallestElement(root.right, k);
        }
        return -1;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(2);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(4);

        System.out.println("The Kth Smallest element is: " + KthSmallestElement(root, 3));
    }
}
