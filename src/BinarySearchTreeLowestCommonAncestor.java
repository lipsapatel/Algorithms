import Node.BinaryTreeNode;
/**
 * The Lowest Common Ancestor of two nodes n1 and n2 is Node x such that x will be
 * the lowest
 * node which has n1 and n2
 * as it's descendant.
 *
 * resources/BinarySearchTreeLowestCommonAncestor.png
 * resources/BinarySearchTreeLowestCommonAncestorTracing.png
 *
 * Start with the root
 * If root.data > n1.data and root.data > n2.data then lowest common ancestor will be
 * in left subtree
 * If root.data < n1.data and root.data < n2.data then lowest common ancestor will be
 * in right subtree
 * If step 2 and 3 are false then root is the lowest common ancestor and return it.
 *
 * Time Complexity = O(logn)
 * Space Complexity = O(1)
 */
public class BinarySearchTreeLowestCommonAncestor {

    private static BinaryTreeNode root = null;

    private static BinaryTreeNode getLCA(BinaryTreeNode root, BinaryTreeNode n1, BinaryTreeNode n2) {
        if (root == null) {
            return null;
        }

        if (root.data > n1.data && root.data > n2.data) {
            return getLCA(root.left, n1, n2);
        } else if (root.data < n1.data && root.data < n2.data) {
            return getLCA(root.right, n1, n2);
        }

        //If you are here that mean root is the Lowest Common Ancestor
        return root;
    }

    private static boolean find(int n) {
        BinaryTreeNode current = root;

        while (current != null) {
            if (current.data == n) {
                return true;
            } else if (current.data > n) {
                current = current.left;
            } else if (current.data < n) {
                current = current.right;
            }
        }
        return false;
    }

    private static void insert(int id) {
        BinaryTreeNode newNode = new BinaryTreeNode(id);

        BinaryTreeNode parent = null;
        BinaryTreeNode current = root;

        if (root == null) {
            root = newNode;
            return;
        }

        while(true) {
            parent = current;

            if (current.data < id) {
                current = current.right;

                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            } else {
                current = current.left;

                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            }
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

        BinaryTreeNode node1 = new BinaryTreeNode(6);
        BinaryTreeNode node2 = new BinaryTreeNode(20);

        BinaryTreeNode lca = getLCA(root, node1, node2);

        System.out.println("The lowest common ancestor between 6 and 20 is " + lca.data);

        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node4 = new BinaryTreeNode(10);

        BinaryTreeNode lca1 = getLCA(root, node3, node4);

        System.out.println("The lowest common ancestor between 3 and 10 is " + lca1.data);

        BinaryTreeNode node5 = new BinaryTreeNode(16);
        BinaryTreeNode node6 = new BinaryTreeNode(2);

        BinaryTreeNode lca2 = getLCA(root, node5, node6);

        System.out.println("The lowest common ancestor between 16 and 2 is " + lca2.data);

        BinaryTreeNode node7 = new BinaryTreeNode(40);
        BinaryTreeNode node8 = new BinaryTreeNode(50);

        BinaryTreeNode lca3 = getLCA(root, node7, node8);

        if (lca3 == null) {
            System.out.println("Both nodes are not present");
        } else {
            System.out.println("The lowest common ancestor between 40 and 50 is " + lca3.data);
        }

        //If one node is present and other is not
        BinaryTreeNode node9 = new BinaryTreeNode(40);
        BinaryTreeNode node10 = new BinaryTreeNode(20);

        if (find(40) && find(20)) {
            BinaryTreeNode lca4 = getLCA(root, node9, node10);

            if (lca4 == null) {
                System.out.println("Both nodes are not present");
            } else {
                System.out.println("The lowest common ancestor between 40 and 20 is " + lca4.data);
            }
        } else {
            System.out.println("One of the node or both nodes are not present");
        }
    }
}
