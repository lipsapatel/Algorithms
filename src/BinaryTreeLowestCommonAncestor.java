import Node.BinaryTreeNode;
/**
 * The lowest common ancestor between node n1 and n2 will be Node x such that Node x
 * will be the lowest node which has n1 and n2 as it's descendant.
 *
 * images/BinaryTreeLowestCommonAncestor.png
 *
 * Start with root
 * If root.data = n1.data or root.data = n2.data return n1 or n2
 * Make recursive call to search the left and right for n1 and n2
 *
 * Now both the nodes will be in the left subtree
 * or both the nodes will be in the right subtree or
 * or if both n1 and n2 will be in each side of current visiting node.
 *
 * If you find node which has one node in it's left subtree and one in right subtree then
 * the current
 * node is LCA
 * If both n1 and n2 are in left subtree go to left
 * If both n1 and n2 are in right subtree go to right
 *
 * images/BinaryTreeLowestCommonAncestorBacktrack.png
 *
 */
public class BinaryTreeLowestCommonAncestor {

    private static boolean v1 = false;
    private static boolean v2 = false;

    private static BinaryTreeNode getLCAUtil(BinaryTreeNode root, BinaryTreeNode n1, BinaryTreeNode n2) {

        if (root == null) {
            return null;
        } else {
            BinaryTreeNode temp = null; //Store so that we can search for other key

            //if (root.data == n1.data || root.data == n2.data) { //Do this if we don't need to check for existence
            //    return root;
            //}
            if (root.data == n1.data) {
                v1 = true;
                temp = root;
            }
            if (root.data == n2.data) {
                v2 = true;
                temp = root;
            }

            BinaryTreeNode left = getLCAUtil(root.left, n1, n2);
            BinaryTreeNode right = getLCAUtil(root.right, n1, n2);

            if (temp != null) {
                return temp;
            }

            if (left != null && right != null) {
                return root;
            }

            if (left != null) {
                return left;
            } else if (right != null) {
                return right;
            }

            return null;
        }
    }

    // Finds lca of n1 and n2 under the subtree rooted with 'node'
    public static BinaryTreeNode getLCA(BinaryTreeNode root, BinaryTreeNode n1, BinaryTreeNode n2)
    {
        // Initialize n1 and n2 as not visited
        v1 = false;
        v2 = false;

        // Find lca of n1 and n2 using the technique discussed above
        BinaryTreeNode lca = getLCAUtil(root, n1, n2);

        // Return LCA only if both n1 and n2 are present in tree
        if (v1 && v2)
            return lca;

        // Else return NULL
        return null;
    }

    public static void main(String[] args) {
        BinaryTreeNode root  = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        BinaryTreeNode n3 = new BinaryTreeNode(5);
        root.left.right = n3;
        root.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(7);
        BinaryTreeNode n1 = new BinaryTreeNode(8);
        root.left.left.left = n1;
        root.left.left.right = new BinaryTreeNode(9);
        root.left.right.left = new BinaryTreeNode(10);
        root.left.right.right = new BinaryTreeNode(20);

        root.left.right.left.left = new BinaryTreeNode(11);
        BinaryTreeNode n2 = new BinaryTreeNode(30);
        root.left.right.left.right = n2 ;

        BinaryTreeNode n4 = new BinaryTreeNode(12);
        BinaryTreeNode n5 = new BinaryTreeNode(14);

        BinaryTreeNode lca = getLCA(root,n1,n2);
        System.out.println("Lowest Common Ancestor ("+n1.data+", "+ n2.data +" ) is " + lca.data);

        lca = getLCA(root,n2,n3);
        System.out.println("Lowest Common Ancestor ("+n2.data+", "+ n3.data +" ) is " + lca.data);

        v1 = false;
        v2 = false;
        //n4 doesn't exist but n3 exist
        lca = getLCA(root, n4, n3);
        if (lca == null || !v1 || !v2) {
            System.out.println("Both nodes or one node doesn't exist");
        } else {
            System.out.println("Lowest Common Ancestor (" + n4.data + ", " + n5.data + " ) is " + lca.data);
        }

        v1 = false;
        v2 = false;
        //n4 and n5 doesn't exist
        lca = getLCA(root, n4, n5);
        if (lca == null || !v1 || !v2) {
            System.out.println("Both nodes or one node doesn't exist");
        } else {
            System.out.println("Lowest Common Ancestor (" + n4.data + ", " + n5.data + " ) is " + lca.data);
        }
    }
}
