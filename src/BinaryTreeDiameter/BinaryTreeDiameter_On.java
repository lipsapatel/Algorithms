package BinaryTreeDiameter;
import Node.BinaryTreeNode;

/**
 * If you notice every node we are calling height function. We can calculate height in the same
 * iteration.
 * Calculate height and diameter at every iteration of that node.
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class BinaryTreeDiameter_On {

    private static int[] BinaryTreeDiameter(BinaryTreeNode root) {

        int[] heightAndDiameter = {0,0};

        while (root != null) {

            int[] leftResult = BinaryTreeDiameter(root.left);
            int[] rightResult = BinaryTreeDiameter(root.right);

            int leftSubtreeHeight = leftResult[0];
            int rightSubtreeHeight = rightResult[0];
            int height = 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight);

            int leftDiameter = leftResult[1];
            int rightDiameter = rightResult[1];
            int rootDiameter = 1 + leftSubtreeHeight + rightSubtreeHeight;
            int diameter = Math.max(Math.max(leftDiameter, rightDiameter), rootDiameter);

            heightAndDiameter[0] = height;
            heightAndDiameter[1] = diameter;

            return heightAndDiameter;
        }

        return heightAndDiameter;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.left.right.left = new BinaryTreeNode(6);
        root.left.right.left.right = new BinaryTreeNode(7);
        root.left.left.left = new BinaryTreeNode(8);

        System.out.println("The Diameter of Binary Tree is " + BinaryTreeDiameter(root)[1]);
    }
}
