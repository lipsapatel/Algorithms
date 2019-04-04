import Node.BinaryTreeNode;

/**
 * Given two binary trees, write a function to check if they are the same or not.

 Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

 Example 1:

 Input:     1         1
 / \       / \
 2   3     2   3

 [1,2,3],   [1,2,3]

 Output: true
 Example 2:

 Input:     1         1
 /           \
 2             2

 [1,2],     [1,null,2]

 Output: false
 Example 3:

 Input:     1         1
 / \       / \
 2   1     1   2

 [1,2,1],   [1,1,2]

 Output: false
 */
public class isSameBinaryTree {

    private static boolean isSameTree(BinaryTreeNode p, BinaryTreeNode q) {

        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.data != q.data) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        BinaryTreeNode p = new BinaryTreeNode(1);
        p.left = new BinaryTreeNode(2);
        p.right = new BinaryTreeNode(1);

        BinaryTreeNode q = new BinaryTreeNode(1);
        q.left = new BinaryTreeNode(1);
        q.right = new BinaryTreeNode(2);

        System.out.println("Is binary tree: " + isSameTree(p, q));
    }
}
