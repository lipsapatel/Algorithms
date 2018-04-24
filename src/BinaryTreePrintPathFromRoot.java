import java.util.ArrayList;
import java.util.Collections;

import Node.BinaryTreeNode;

/**
 * Given binary tree, print path from root to the given node
 * images/BinaryTreePathFromRoot.png
 *
 * Compare with root.data if matched we found it
 * else go to the left and right
 * Recursively do the above two
 * Once you find the node, stop the recursion
 * going back to the root, store the values in arraylist
 * print the arraylist in reverse order
 *
 * Time Complexity: O(n)
 */
public class BinaryTreePrintPathFromRoot {

    private static ArrayList path;

    private static boolean printPath(BinaryTreeNode root, BinaryTreeNode destination) {
        if (root == null) {
            return false;
        }

        if (root == destination || printPath(root.left, destination) || printPath(root.right, destination)) {
            path.add(root.data);
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode (2);
        root.right = new BinaryTreeNode (3);
        BinaryTreeNode n1 = new BinaryTreeNode (4);
        root.left.left = n1;
        root.left.right = new BinaryTreeNode (5);
        BinaryTreeNode n2 = new BinaryTreeNode (8);
        root.left.right.right = n2;
        root.left.right.left = new BinaryTreeNode (7);

        path = new ArrayList();
        printPath(root,n2);
        Collections.reverse(path);
        System.out.println(" Path " + path);

    }
}
