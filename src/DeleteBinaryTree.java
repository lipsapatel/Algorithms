import Node.BinaryTreeNode;

/**
 * To delete binary tree, we need to set all node object to null.
 * Do post order traversal and set all node object to null.
 * Garbage Collection will take care of rest of the things.
 */
public class DeleteBinaryTree {

    //Do post order traversal and set node object to null
    private static BinaryTreeNode deleteBinaryTree(BinaryTreeNode root) {
        if (root != null) {
            deleteBinaryTree(root.left);
            deleteBinaryTree(root.right);

            System.out.println("Delete Binary tree node " + root.data);
            root = null;
            return root;
        }
        return null;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(7);

        deleteBinaryTree(root);
    }
}
