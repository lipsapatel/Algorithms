import Node.BinaryTreeNode;

/**
 * Given Binary Search Tree (BST), convert it into greater sum tree.
 *
 * What is Greater Sum Tree?
 * Greater Sum Tree is a tree in which every node contains the sum of all nodes which are
 * greater than that node.
 *
 * resources/BSTToGreaterSumTree.png
 *
 * Approach:
 *
 * Naive Approach:
 *
 * 1) For every node, traverse the tree and find out all the nodes which are greater and
 * update the node.
 * 2) Time Complexity: O(n2)
 *
 * We know that the right most node in the BST is the biggest node among all nodes.
 * So we start from there.
 * Do the reverse inorder traversal (right, root, left)
 *
 * 1) Since we are visiting nodes in the reverse order so we need to maintain sum of the nodes
 * visited.
 * 2) Global variable sum maintains the sum of nodes till now.
 * 3) Before update node value, store it in temp we need it later.
 * 4) sum = sum + temp;
 */
public class ConvertBSTToGreaterSumTree {

    private static int sum = 0;

    private static void convertBSTToGreaterSumTree(BinaryTreeNode root) {

        if (root != null) {

            convertBSTToGreaterSumTree(root.right);

            //Store the node value in temp
            int temp = root.data;

            //Update sum till previous visited node
            root.data = sum;

            //Update sum for the next node
            sum = sum + temp;

            convertBSTToGreaterSumTree(root.left);

        } else {
            return;
        }
    }

    private static void displayBSTInorderTraversal(BinaryTreeNode root) {

        if (root != null) {
            displayBSTInorderTraversal(root.left);
            System.out.print(root.data + " ");
            displayBSTInorderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(10);
        root.left = new BinaryTreeNode(5);
        root.right = new BinaryTreeNode(15);
        root.left.left = new BinaryTreeNode(2);
        root.left.right = new BinaryTreeNode(7);
        root.right.left = new BinaryTreeNode(12);
        root.right.right = new BinaryTreeNode(20	);

        displayBSTInorderTraversal(root);
        System.out.println();
        convertBSTToGreaterSumTree(root);
        displayBSTInorderTraversal(root);
        System.out.println();
    }
}
