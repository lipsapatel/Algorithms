import Node.BinaryTreeNode;

/**
 * Given a binary tree, where every node has either 0 or 2 children and every right node is a leaf node.
 * Flip it upside down turning into a binary tree where all left nodes are leafs.
 *
 *    1
 *   /  \
 *  2    3
 *
 *  Output:
 *
 *     2
 *    /  \
 *   3    1
 *
 * resources/FlipUpsideDown.png
 *
 * root.left.left = root.right
 * root.left.right = root
 * root.left = null
 * root.right = null
 *
 * Approaches
 *
 * Iterative Approach
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * 1) Save the left and right for next iteration of root.
 *
 * Recursive Approach
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * 1) Recursively flip the leftmost subtree
 * 2) Add code to flip the root.
 * 3) Return newRoot
 */
public class FlipUpsideDown {

    private static BinaryTreeNode flipUpsideDown(BinaryTreeNode root) {

        //Base Case
        if (root == null) {
            return root;
        }

        BinaryTreeNode left = null;
        BinaryTreeNode right = null;

        while(root != null) {

            BinaryTreeNode nextRoot = root.left;
            BinaryTreeNode nextLeft = root.right;
            BinaryTreeNode nextRight = root;

            root.left = left;
            root.right = right;

            if (nextRoot != null) {
                root = nextRoot;
                left = nextLeft;
                right = nextRight;
            } else {
                return root;
            }
        }

        return null;
    }

    private static BinaryTreeNode flipUpsideDownRecursive(BinaryTreeNode root) {

        //Base Case
        if (root == null) {
            return root;
        }

        //If it's the leaf node, then it will be the root of our flipped upside down tree.
        if (root.left == null && root.right == null) {
            return root;
        }

        //Recursive case: To flip leftmost subtree
        BinaryTreeNode newRoot = flipUpsideDownRecursive(root.left);

        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;

        //Return the root of our flipped upside down tree
        return newRoot;
    }

    private static void printTree(BinaryTreeNode root) {

        if (root != null) {
            printTree(root.left);
            System.out.print(" " + root.data);
            printTree(root.right);
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);

        printTree(root);
        System.out.println();
        BinaryTreeNode newRoot = flipUpsideDown(root);
        printTree(newRoot);
        System.out.println();

        BinaryTreeNode root1 = new BinaryTreeNode(1);
        root1.left = new BinaryTreeNode(2);
        root1.right = new BinaryTreeNode(3);
        root1.left.left = new BinaryTreeNode(4);
        root1.left.right = new BinaryTreeNode(5);

        System.out.println("Recursive Case");
        BinaryTreeNode newRoot1 = flipUpsideDownRecursive(root1);
        printTree(newRoot1);
        System.out.println();
    }
}
