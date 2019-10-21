import Node.BinaryTreeNodeWithParent;

/**
 * Given node, find the inorder successor in BST
 *
 * Approach:
 *
 * Case 1: If the node has right child, then the smallest in right subtree
 * Case 2: If there is no right child, find the node who is left child of it's parent and return that parent.
 *
 * Assuming that we are having node structure with parent
 *
 * Time Complexity: O(n)
 */
public class FindInOrderSuccessorInBST {

    private static BinaryTreeNodeWithParent inOrderSuccessor(BinaryTreeNodeWithParent n) {

        //Case1: Go to right and find min
        if (n.right != null) {

            BinaryTreeNodeWithParent current = n.right;

            while (current.left != null) {
                current = current.left;
            }
            return current;
        }

        //Case2: Find node which is left child of parent
        BinaryTreeNodeWithParent parent = n.parent;

        while (parent != null) {

            if (n == parent.left) {
                break;
            }

            n = parent;
            parent = n.parent;
        }
        return parent;
    }

    public static void main(String[] args) {

       /* BinaryTreeNodeWithParent root = new BinaryTreeNodeWithParent(30);
        root.parent = null;

        root.right = new BinaryTreeNodeWithParent(50);
        root.right.parent = root;

        root.right.left = new BinaryTreeNodeWithParent(45);
        root.right.left.parent = root.right;

        root.right.left.right = new BinaryTreeNodeWithParent(48);
        root.right.left.right.parent = root.right.left;

        root.right.left.right.right = new BinaryTreeNodeWithParent(49);
        root.right.left.right.right.parent = root.right.left.right;*/

       BinaryTreeNodeWithParent root = new BinaryTreeNodeWithParent(10);
       root.parent = null;

       root.left = new BinaryTreeNodeWithParent(5);
       root.left.parent = root;

       root.left.left = new BinaryTreeNodeWithParent(3);
       root.left.left.parent = root.left;

       root.left.right = new BinaryTreeNodeWithParent(8);
       root.left.right.parent = root.left;

       root.left.right.left = new BinaryTreeNodeWithParent(20);
       root.left.right.left.parent = root.left.right;

       root.left.right.left.left = new BinaryTreeNodeWithParent(30);
       root.left.right.left.left.parent = root.left.right.left;

       root.left.right.right = new BinaryTreeNodeWithParent(9);
       root.left.right.right.parent = root.left.right;

        System.out.println("The inorder successor is: " + inOrderSuccessor(root.left.right.left.left).data);
    }
}
