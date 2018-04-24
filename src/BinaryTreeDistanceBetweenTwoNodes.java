import Node.BinaryTreeNode;

import java.nio.file.Path;

/**
 * images/BinaryTreeDistanceBetweenTwoNodes.png
 *
 * Distance(X,Y) = Distance(root, X) + Distance(root, Y) - 2 * Distance(root, LCA)
 *
 *
 */
public class BinaryTreeDistanceBetweenTwoNodes {

    private static int PathLength(BinaryTreeNode root, BinaryTreeNode n1) {
        if (root != null) {
            int x = 0;

            if (root.data == n1.data || (x = PathLength(root.left, n1)) > 0
                    || (x=PathLength(root.right, n1)) > 0) {
                return x + 1;
            }

            return 0;
        }

        return 0;
    }

    private static BinaryTreeNode LCA(BinaryTreeNode root, BinaryTreeNode n1, BinaryTreeNode n2) {
        if (root != null) {

            if (root.data == n1.data || root.data == n2.data) {
                return root;
            }

            BinaryTreeNode left = LCA(root.left, n1, n2);
            BinaryTreeNode right = LCA(root.right, n1, n2);

            if (left != null && right != null) {
                return root;
            }
            if (left != null) {
                return left;
            }
            if (right != null) {
                return right;
            }
        }
        return null;
    }

    private static int findDistance(BinaryTreeNode root, BinaryTreeNode n1, BinaryTreeNode n2) {
        int x = PathLength(root, n1) - 1; //Number of nodes - 1 = number of edges
        int y = PathLength(root, n2) - 1;

        BinaryTreeNode lcaNode = LCA(root, n1, n2);

        int lcaDistance = PathLength(root, lcaNode) - 1;

        return (x+y) - 2 * lcaDistance;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(10);
        root.right = new BinaryTreeNode(15);

        BinaryTreeNode node1 = new BinaryTreeNode(20);
        root.left.left = node1;

        root.left.right = new BinaryTreeNode(25);
        root.right.left = new BinaryTreeNode(30);
        root.right.right = new BinaryTreeNode(35);

        BinaryTreeNode node2 = new BinaryTreeNode(45);
        root.left.right.right = node2;


        System.out.println("Distance between 45 and 20 is : "
                + findDistance(root, node1, node2));
    }
}
