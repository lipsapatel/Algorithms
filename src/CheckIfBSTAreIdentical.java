import Node.BinaryTreeNode;

/**
 * Check if two BST are identical.
 *
 * Given two Binary Search Tree, check if both are identical.
 *
 * Input: Two Binary Search Tree.
 *
 * resources/CheckIfBSTAreIdentical.png
 *
 * Approach:
 *
 * 1) Traverse both the trees at the same time starting from root.
 * 2) If root is not null and data matched, the make recursive call to root.left and root.right
 * 3) If any of the tree gets over and other not then return false
 * 4) If both of the traversal ends at the same time, then return true.
 */
public class CheckIfBSTAreIdentical {

    private static boolean checkIfBSTAreIdentical(BinaryTreeNode root1, BinaryTreeNode root2) {

        if (root1 == null && root2 == null) {
            return true;
        } else if ((root1 == null && root2 != null) || (root1 != null && root2 == null)) {
            return false;
        } else if (root1.data == root2.data) {
            return checkIfBSTAreIdentical(root1.left, root2.left) &&
                    checkIfBSTAreIdentical(root1.right, root2.right);
        } else {
            return false;
        }
    }

    private static BinaryTreeNode insertIntoBinarySearchTree(int data, BinaryTreeNode root) {

        BinaryTreeNode newNode = new BinaryTreeNode(data);

        if (root == null) {
            root = newNode;
            return root;
        }

        BinaryTreeNode current = root;
        BinaryTreeNode parent = null;

        while(true) {

            parent = current;

            if (data < current.data) {
                current = current.left;

                if (current == null) {
                    parent.left = newNode;
                    return root;
                }
            } else {

                current = current.right;

                if (current == null) {
                    parent.right = newNode;
                    return root;
                }
            }
        }

    }

    public static void main(String[] args) {

        BinaryTreeNode root1 = null;

        root1 = insertIntoBinarySearchTree(2, root1);
        root1 = insertIntoBinarySearchTree(4, root1);
        root1 = insertIntoBinarySearchTree(1, root1);
        root1 = insertIntoBinarySearchTree(3, root1);
        root1 = insertIntoBinarySearchTree(5, root1);

        BinaryTreeNode root2 = null;

        root2 = insertIntoBinarySearchTree(2, root2);
        root2 = insertIntoBinarySearchTree(4, root2);
        root2 = insertIntoBinarySearchTree(1, root2);
        root2 = insertIntoBinarySearchTree(3, root2);
        root2 = insertIntoBinarySearchTree(5, root2);

        System.out.println("BST are identical: " + checkIfBSTAreIdentical(root1, root2));

        root1 = insertIntoBinarySearchTree(11, root1);
        root1 = insertIntoBinarySearchTree(12, root1);

        System.out.println("BST are identical: " + checkIfBSTAreIdentical(root1, root2));
    }
}
