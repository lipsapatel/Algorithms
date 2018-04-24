import Node.BinaryTreeNode;
/**
 * Distance between two nodes
 * Distance(X, Y) = Distance (root, X) + Distance (root, Y) -
 *                  2 * Distance (root, LCA)
 */
public class BinarySearchTreeDistanceBetweenTwoNodes {

    private static BinaryTreeNode root = null;

    private static BinaryTreeNode getLCA(BinaryTreeNode root,
                                         BinaryTreeNode n1, BinaryTreeNode n2) {
        if (root == null) {
            return null;
        }

        if (root.data > n1.data && root.data > n2.data) {
            return getLCA(root.left, n1, n2);
        } else if (root.data < n1.data && root.data < n2.data) {
            return getLCA(root.right, n1, n2);
        }

        //If we reached here then the root is the lca
        return root;
    }

    private static int distanceFromRootToNode(BinaryTreeNode n1) {
        BinaryTreeNode current = root;
        int numberOfNodes = 0;

        while(current != null) {
            if (current.data > n1.data) {
                current = current.left;
                numberOfNodes++;
            } else if (current.data < n1.data) {
                current = current.right;
                numberOfNodes++;
            } else if (current.data == n1.data) {
                numberOfNodes++;
                return numberOfNodes;
            }
        }

        return numberOfNodes;
    }

    private static int distanceBetweenTwoNodes(BinaryTreeNode n1, BinaryTreeNode n2) {

        int x = distanceFromRootToNode(n1);
        int y = distanceFromRootToNode(n2);
        BinaryTreeNode lca = getLCA(root, n1, n2);
        int lcaDistanceFromRoot = distanceFromRootToNode(lca);

        return x + y - 2*(lcaDistanceFromRoot);
    }

    private static void insert(int data) {
        BinaryTreeNode newNode = new BinaryTreeNode(data);

        if (root == null) {
            root = newNode;
            return;
        }

        BinaryTreeNode current = root;
        BinaryTreeNode parent = null;

        while(true) {
            parent = current;
            if (current.data > data) {
                current = current.left;

                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            } else if (current.data < data) {
                current = current.right;

                 if (current == null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    private static void display(BinaryTreeNode root) {
        if (root != null) {
            display(root.left);
            System.out.print(root.data + "->");
            display(root.right);
        }
    }

    public static void main(String[] args) {
        insert(3);
        insert(8);
        insert(1);
        insert(4);
        insert(6);
        insert(2);
        insert(10);
        insert(9);
        insert(20);
        insert(25);
        insert(15);
        insert(16);

        System.out.println("Original Tree: ");
        display(root);
        System.out.println();

        BinaryTreeNode n1 = new BinaryTreeNode(6);
        BinaryTreeNode n2 = new BinaryTreeNode(16);

        BinaryTreeNode lcaNode = getLCA(root, n1, n2);

        System.out.println("The LCA of 6 and 16 is " + lcaNode.data);

        int numberOfNodes = distanceFromRootToNode(n2);
        System.out.println("The distance from root to node 16 is " + (numberOfNodes - 1));

        System.out.println("The distance between node 6 and 16 is " + distanceBetweenTwoNodes(n1, n2));
    }
}
