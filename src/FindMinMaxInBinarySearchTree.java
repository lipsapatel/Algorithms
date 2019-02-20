import Node.BinaryTreeNode;

/**
 * Find minimum and maximum in Binary Search Tree
 * Time Complexity: O(logn)
 */
public class FindMinMaxInBinarySearchTree {

    private static BinaryTreeNode root = null;

    private static void insert(int id) {

        BinaryTreeNode newNode = new BinaryTreeNode(id);

        if (root == null) {
            root = newNode;
            return;
        }

        BinaryTreeNode parent = null;
        BinaryTreeNode current = root;

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

    private static int findMinInBST() {

        if (root == null) {
            System.out.println("Tree is empty");
            return -1;
        }

        BinaryTreeNode current = root;

        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    private static int findMinInBSTRecursive(BinaryTreeNode root) {
        if (root == null) {
            System.out.println("Tree is empty");
            return -1;
        } else if (root.left == null) {
            return root.data;
        }
        return findMinInBSTRecursive(root.left);
    }

    private static int findMaxInBST() {

        if (root == null) {
            System.out.println("Tree is empty");
            return -1;
        }

        BinaryTreeNode current = root;

        while(current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    private static int findMaxInBSTRecursive(BinaryTreeNode root) {
        if (root == null) {
            System.out.println("Tree is empty");
            return -1;
        } else if (root.right == null) {
            return root.data;
        }
        return findMaxInBSTRecursive(root.right);
    }

    public static void main(String[] args) {
        insert(15);
        insert(10);
        insert(20);
        insert(7);
        insert(12);
        insert(17);
        insert(25);
        insert(5);
        insert(8);

        System.out.println("The minimum is: " + findMinInBST());
        System.out.println("The maximum is: " + findMaxInBST());
        System.out.println("The minimum using recursion: " + findMinInBSTRecursive(root));
        System.out.println("The maximum using recursion: " + findMaxInBSTRecursive(root));
    }
}
