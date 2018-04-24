import Node.BinaryTreeNode;

/**
 * Binary Tree represents hierarchical data.
 * Binary tree has left and right child nodes. Topmost node is root.
 * Nodes with no child are leaves
 * No upper limit on number of nodes.
 *
 * Get sum of all nodes which are left and leaves
 * images/BinaryTreeLeftLeavesSum.PNG
 *
 * Approach: Do inorder traversal(left, root, right)
 * Check if node is left child and leaf node.
 * If yes then add to the sum
 *
 */
public class BinaryTreeLeftLeavesSum {

    private static int leftLeavesSum = 0;

    private static void getLeftLeavesSum(BinaryTreeNode root, BinaryTreeNode parent) {

        if (root != null) {
            getLeftLeavesSum(root.left, root);

            //If left and right node are null then it is the leaf and if it's parent.left node
            // than it is left leaf
            if (root.left == null && root.right == null && parent.left == root) {
                leftLeavesSum += root.data;
            }

            getLeftLeavesSum(root.right, root);
        }
     }

    private static int getLeftLeavesSumDifferentWay(BinaryTreeNode root, BinaryTreeNode parent) {

        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null && parent.left == root) {
            return root.data;
        }

        return getLeftLeavesSumDifferentWay(root.left, root) + getLeftLeavesSumDifferentWay(root.right, root);
    }

    public static void main(String[] args) {

        BinaryTreeNode root = new BinaryTreeNode(10);

        root.left = new BinaryTreeNode(5);
        root.right = new BinaryTreeNode(15);

        root.left.left = new BinaryTreeNode(10);
        root.left.right = new BinaryTreeNode(12);

        root.left.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(8);
        root.right.right.left = new BinaryTreeNode(5);

        getLeftLeavesSum(root, root);

        System.out.println("The sum of all left leaves in different way is " + getLeftLeavesSumDifferentWay(root, root));

        System.out.println("The sum of all left leaves is " + leftLeavesSum);
    }
}
