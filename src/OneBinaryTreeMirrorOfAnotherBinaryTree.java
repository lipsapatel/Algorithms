import Node.BinaryTreeNode;

/**
 * Check if one binary tree is mirror of another binary tree.
 *
 * Given two binary tree check if one is mirror of another.
 *
 * resources/OneBinaryTreeIsMirrorOfAnother.png
 *
 * 1) Do the preorder traversal on both of the trees simultaneously.
 * 2) If any node doesn't have corresponding node in another tree, return false
 * 3) If the data is not equal then return false
 * 3) Check if the left node in one tree is the right node in another tree.
 */
public class OneBinaryTreeMirrorOfAnotherBinaryTree {

    private static boolean checkIfBinaryTreeIsMirrorOfAnotherBinaryTree(BinaryTreeNode root1,
                                                                        BinaryTreeNode root2) {

        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1.data != root2.data) {
            return false;
        }

        //If any node doesn't have corresponding node in another binary tree then return false
        if ((root1 != null && root2 == null) || (root1 == null && root2 != null)) {
            return false;
        }

        //Check if the left node in one tree is the right node in another tree and vice versa
        return checkIfBinaryTreeIsMirrorOfAnotherBinaryTree(root1.left, root2.right) &&
                checkIfBinaryTreeIsMirrorOfAnotherBinaryTree(root1.right, root2.left);
    }

    public static void main(String[] args) {

        BinaryTreeNode root1 = new BinaryTreeNode(1);
        root1.left = new BinaryTreeNode(2);
        root1.left.right = new BinaryTreeNode(3);
        root1.right = new BinaryTreeNode(4);

        BinaryTreeNode root2 = new BinaryTreeNode(1);
        root2.right = new BinaryTreeNode(2);
        root2.right.left = new BinaryTreeNode(3);
        root2.left = new BinaryTreeNode(4);

        System.out.println("Binary Trees are mirror: " + checkIfBinaryTreeIsMirrorOfAnotherBinaryTree(root1, root2));
    }
}
