import Node.BinaryTreeNode;

/**
 * Find the Deepest node in Binary Tree.
 *
 * Approach:
 *
 * 1) Take two variables deepestLevel and value
 * 2) Start with level = 0
 * 3) Do inorder traversal
 * 4) Increase level when you go left or right
 * 5) For root check if deepestLevel < level the update deepestLevel and value
 * 6) At the end return the value of the deepest node.
 *
 */
public class FindDeepestNodeInBinaryTree {

    static int deepestLevel;
    static int value;

    private static int findDeepestNode(BinaryTreeNode root, int level) {

        if (root != null) {

            findDeepestNode(root.left, level++);

            if (deepestLevel < level) {
                deepestLevel = level;
                value = root.data;
            }

            findDeepestNode(root.right, level++);
        }
        return value;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(7);
        root.right.right.right = new BinaryTreeNode(8);

        System.out.println("Deepest child is: " + findDeepestNode(root, 0));
    }
}
