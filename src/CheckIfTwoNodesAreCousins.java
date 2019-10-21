import Node.BinaryTreeNode;
import javafx.util.Pair;

/**
 * In a Binary Tree, Check if two nodes are cousins.
 *
 * Cousins are the nodes who are at the same level and whose parents
 * are siblings.
 * They never have same parent.
 *
 * resources/CheckIfTwoNodesAreCousins.png
 *
 * Approach:
 *
 * 1) Check the height of both the nodes, if heights are different return false
 * 2) Check if both nodes has same parent, if yes then return false
 * 3) Else return true
 */
public class CheckIfTwoNodesAreCousins {

    private static boolean twoNodesAreCousins(BinaryTreeNode root, BinaryTreeNode x, BinaryTreeNode y) {

        if (root == null) {
            return false;
        }

        if (getNodeHeight(root, x, 1) != getNodeHeight(root, y, 1)) {
            return false;
        }

        if (hasSameParent(root, x, y)) {
            return false;
        } else {
            return true;
        }
    }

    private static int getNodeHeight(BinaryTreeNode root, BinaryTreeNode x, int height) {

        if (root == null) {
            return 0;
        }

        if (root == x) {
            return height;
        }

        int level = getNodeHeight(root.left, x, height + 1);
        if (level != 0) {
            return level;
        }
        return getNodeHeight(root.right, x, height + 1);
    }

    private static boolean hasSameParent(BinaryTreeNode root, BinaryTreeNode x, BinaryTreeNode y) {

        if (root == null) {
            return false;
        }

        return ((root.left == x && root.right == y) ||
                (root.left == y && root.right == x) ||
                hasSameParent(root.left, x, y) ||
                hasSameParent(root.right, x, y));
    }

    /***************************************************************************************************************************************/

    //Time Complexity: O(n)
    //Space Complexity: O(n)

    private static Pair<BinaryTreeNode, Integer> getNodeParentAndHeight(BinaryTreeNode root, BinaryTreeNode x, BinaryTreeNode parent, int height) {

        //Base Cases
        if (root == null) {
            return new Pair<BinaryTreeNode, Integer>(parent, 0);
        }

        if (root == x) {
            return new Pair<BinaryTreeNode, Integer>(parent, height);
        }

        //Recursive Case
        Pair<BinaryTreeNode, Integer> pair = getNodeParentAndHeight(root.left, x, root, height + 1);
        if (pair.getValue() != 0) {
            return pair;
        }

        return getNodeParentAndHeight(root.right, x, root, height + 1);
    }

    private static boolean areTwoNodesCousins(BinaryTreeNode root, BinaryTreeNode x, BinaryTreeNode y) {

        if (root == null) {
            return false;
        }

        Pair<BinaryTreeNode, Integer> pair1 = getNodeParentAndHeight(root, x, null, 1);
        Pair<BinaryTreeNode, Integer> pair2 = getNodeParentAndHeight(root, y, null, 1);

        if (pair1.getValue() == pair2.getValue() && pair1.getKey() != pair2.getKey()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        BinaryTreeNode x1 = new BinaryTreeNode(2);
        BinaryTreeNode y1 = new BinaryTreeNode(3);
        root.left = x1;
        root.right = y1;
        root.left.left = new BinaryTreeNode(4);
        root.right.left = new BinaryTreeNode(6);
        BinaryTreeNode x2 = new BinaryTreeNode(7);
        BinaryTreeNode y2 = new BinaryTreeNode(9);
        root.right.left.left = new BinaryTreeNode(8);
        root.right.left.right = y2;
        root.left.left.left = x2;

        System.out.println("Node " + x1.data + " and Node " + y1.data + " are cousins??? " + twoNodesAreCousins(root, x1, y1));
        System.out.println("Node " + x2.data + " and Node " + y2.data + " are cousins??? " + twoNodesAreCousins(root, x2, y2));

        System.out.println("Node " + x1.data + " and Node " + y1.data + " are cousins??? " + areTwoNodesCousins(root, x1, y1));
        System.out.println("Node " + x2.data + " and Node " + y2.data + " are cousins??? " + areTwoNodesCousins(root, x2, y2));
    }





}
