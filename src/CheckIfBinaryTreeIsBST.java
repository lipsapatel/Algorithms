import Node.BinaryTreeNode;

/**
 * Check if the given binary tree is Binary Search Tree - BST
 *
 *
 * Time Complexity: O(n2) which is bad
 * For another approach: O(n)
 * Space Complexity: O(1)
 *
 * resources/CheckIfBinaryTreeIsBST.png
 */
public class CheckIfBinaryTreeIsBST {

    private static boolean checkIfBinaryTreeIsBST(BinaryTreeNode root) {

       if (root == null) {
           return true;
       }

       if (isSubtreeLesser(root.left, root.data) && isSubtreeGreater(root.right, root.data) &&
           checkIfBinaryTreeIsBST(root.left) && checkIfBinaryTreeIsBST(root.right)) {
           return true;
       } else {
           return false;
       }
    }

    private static boolean isSubtreeLesser(BinaryTreeNode root, int value) {
        if (root == null) {
            return true;
        }

        if (root.data <= value && isSubtreeLesser(root.left, value) && isSubtreeLesser(root.right, value)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isSubtreeGreater(BinaryTreeNode root, int value) {
        if (root == null) {
            return true;
        }

        if (root.data > value && isSubtreeGreater(root.right, value) && isSubtreeGreater(root.right, value)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isBST(BinaryTreeNode root, int minValue, int maxValue) {
        if (root == null) {
            return true;
        }

        if(root.data > minValue && root.data < maxValue && isBST(root.left, minValue, root.data) &&
                isBST(root.right, root.data, maxValue)) {
            return true;
        } else {
            return false;
        }
    }
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(10);
        root.left = new BinaryTreeNode(5);
        root.right = new BinaryTreeNode(16);

        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(7);
        root.left.right.right = new BinaryTreeNode(11);

        System.out.println("The given Binary Tree is Binary Search Tree: " + checkIfBinaryTreeIsBST(root));
        System.out.println("The given Binary Tree is Binary Search Tree: " + isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));

    }
}
