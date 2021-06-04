import Node.BinaryTreeNode;

/**
 * Given a binary tree, print all the nodes which are full nodes.
 *
 * Full Nodes: Nodes which has both children left and right are called full nodes.
 *
 * 1) Do any of the traversal - let do inorder traversal
 * 2) During traversal check if the node has left child and right child, if yes
 *    then print it.
 */
public class PrintFullNodesInBinaryTree {

    private static void printFullNodes(BinaryTreeNode root) {

        if (root != null) {

            printFullNodes(root.left);

            if (root.left != null && root.right != null) {
                System.out.print(root.data + " ");
            }

            printFullNodes(root.right);
        }
    }

    public static void main(String[] args) {

        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(4);
        root.left.left.right = new BinaryTreeNode(8);
        root.left.right = new BinaryTreeNode(5);
        root.right = new BinaryTreeNode(3);

        System.out.println("The full nodes in binary tree are: ");
        printFullNodes(root);
    }
}
