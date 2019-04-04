import Node.BinaryTreeNode;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

 For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

 1
 / \
 2   2
 / \ / \
 3  4 4  3
 But the following [1,2,2,null,3,null,3] is not:
 1
 / \
 2   2
 \   \
 3    3
 Note:
 Bonus points if you could solve it both recursively and iteratively.

 A tree is symmetric if the left subtree is a mirror reflection of the right subtree.

 Push an element in stack

 Therefore, the question is: when are two trees a mirror reflection of each other?

 Two trees are a mirror reflection of each other if:

 Their two roots have the same value.
 The right subtree of each tree is a mirror reflection of the left subtree of the other tree.
 Push an element in stack

 This is like a person looking at a mirror. The reflection in the mirror has the same head, but the reflection's right arm corresponds to the actual person's left arm, and vice versa.

 The explanation above translates naturally to a recursive function as follows.


 Complexity Analysis

 Time complexity : O(n)O(n). Because we traverse the entire input tree once, the total run time is O(n),
 where n is the total number of nodes in the tree.

 Space complexity : The number of recursive calls is bound by the height of the tree. In the worst case, the tree is linear and the height is in O(n)O(n). Therefore, space complexity due to recursive calls on the stack is O(n)O(n) in the worst case.
 */
public class isBinaryTreeMirror {

    private static boolean isSymmetric(BinaryTreeNode root) {
        return isSymmetricHelper(root, root);
    }

    private static boolean isSymmetricHelper(BinaryTreeNode p, BinaryTreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.data == q.data) {
            return isSymmetricHelper(p.left, q.right) && isSymmetricHelper(p.right, q.left);
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(2);

        root.left.left = new BinaryTreeNode(3);
        root.left.right = new BinaryTreeNode(4);

        root.right.left = new BinaryTreeNode(4);
        root.right.right = new BinaryTreeNode(3);

        System.out.println("Is mirror: " + isSymmetric(root));
    }
}
