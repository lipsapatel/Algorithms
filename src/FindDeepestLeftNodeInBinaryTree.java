import Node.BinaryTreeNode;

/**
 * Given a Binary tree, find the deepest left node in it.
 *
 * Approach:
 *
 * 1) The approach is similar to "Find Deepest node in a Binary Tree"
 * 2) Take two variable - deepestLevel and deepestLeftNodeValue
 * 3) Start with level 0
 * 4) Do the inorder traversal
 * 5) Whenever you go to the left, increase the level
 * 6) Check if current node is left child and deepestLevel < level then
 * update deepestLevel and deepestLeftNodeValue
 * 7) Return the "deepestLeftNodeValue"
 *
 */
public class FindDeepestLeftNodeInBinaryTree {

    private static int deepestLevel;
    private static int deepestLeftNodeValue;

    private static void findDeepestLeftNodeInBinaryTree(BinaryTreeNode root, int level, boolean isLeftNode) {

        if (root != null) {

            findDeepestLeftNodeInBinaryTree(root.left, ++level, true);

            if (isLeftNode && deepestLevel < level) {

                deepestLeftNodeValue = root.data;
                deepestLevel = level;
            }

            findDeepestLeftNodeInBinaryTree(root.right, level, false);
        }
    }

    public static void main(String[] args) {

        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.left.left.right = new BinaryTreeNode(8);

        findDeepestLeftNodeInBinaryTree(root, 0, true);
        System.out.println("Deapest Left child is: " + deepestLeftNodeValue);
    }

}
