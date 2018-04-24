import Node.BinaryTreeNode;

/**
 * Five different types of tree traversal
 * 1) Inorder Traversal
 * 2) Postorder Traversal
 * 3) Preorder Traversal
 * 4) Breadth-First Search or Level Order Traversal (BFS)
 * 5) Depth-First Search (DFS)
 *
 * Inorder Traversal = left, root, right
 * Preorder Traversal = root, left, right
 * Postorder Traversal = left, right, root
 *
 * /images/RecursiveTreeTraversal.png
 */
public class RecursiveTreeTraversal {

    private static void recursiveInorderTraversal(BinaryTreeNode root) {
        if (root != null) {
            recursiveInorderTraversal(root.left);
            System.out.print(root.data + "->");
            recursiveInorderTraversal(root.right);
        }
    }

    private static void recursivePostorderTraversal(BinaryTreeNode root) {
        if (root != null) {
            recursivePostorderTraversal(root.left);
            recursivePostorderTraversal(root.right);
            System.out.print(root.data + "->");
        }
    }

    private static void recursivePreorderTraversal(BinaryTreeNode root) {
        if (root != null) {
            System.out.print(root.data + "->");
            recursivePreorderTraversal(root.left);
            recursivePreorderTraversal(root.right);
        }
    }

    public static void main(String[] args) {

        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(7);

        System.out.println("Recursive Preorder traversal");
        recursivePreorderTraversal(root);

        System.out.println();

        System.out.println("Recursive Postorder traversal");
        recursivePostorderTraversal(root);

        System.out.println();

        System.out.println("Recursive Inorder traversal");
        recursiveInorderTraversal(root);
    }
}
