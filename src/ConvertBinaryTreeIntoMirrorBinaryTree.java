import Node.BinaryTreeNode;

/**
 * Given a binary tree, convert it into in mirror tree.
 *
 * Mirror tree of a binary tree in which left and right child of every node is interexchanged.
 *
 * resources/MirrorTree.png
 *
 * Do the preorder traversal
 * Start from root swap the left and right child
 * Recursively call the same procedure in the left subtree and the right subtree.
 */
public class ConvertBinaryTreeIntoMirrorBinaryTree {

    private static BinaryTreeNode convertBinaryTreeIntoMirrorBinaryTree(BinaryTreeNode root) {

        if (root != null) {

            //Swap left and right
            BinaryTreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;

            convertBinaryTreeIntoMirrorBinaryTree(root.left);
            convertBinaryTreeIntoMirrorBinaryTree(root.right);
        }

        return root;
    }

    private static void displayBinaryTree(BinaryTreeNode root) {

        if (root != null) {
            displayBinaryTree(root.left);
            System.out.print(root.data + "-->");
            displayBinaryTree(root.right);
        }
    }

    public static void main(String[] args) {

        BinaryTreeNode root = new BinaryTreeNode(4);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(6);
        root.left.left = new BinaryTreeNode(1);
        root.left.right = new BinaryTreeNode(3);
        root.right.left = new BinaryTreeNode(5);
        root.right.right = new BinaryTreeNode(7);

        displayBinaryTree(root);
        System.out.println();

        BinaryTreeNode root1 = convertBinaryTreeIntoMirrorBinaryTree(root);

        System.out.println("Mirror Binary Tree:");
        displayBinaryTree(root1);
        System.out.println();
    }
}
