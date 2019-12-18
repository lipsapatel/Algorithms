import Node.AVLNode;

/**
 * AVL tree is self balancing binary search tree.
 * Difference of height of left and right subtree cannot be greater than one.
 *
 * There are four different use cases to insert into AVL tree
 * 1) left left = right rotation
 * 2) left right = left rotation, right rotation
 * 3) right right = left rotation
 * 4) right left = right rotation, left rotation
 *
 * Insert is same as binary search tree.
 * After that follow the rotation rules to keep the tree balanced.
 * At every node, we will also keep the height information so that we don't have to
 * recalculate values again.
 *
 * TC = O(logn)
 *
 * resources/AVLInsert1.jpg, AVLInsert2.jpg, AVLInsert2.jpg, AVLInsert4.jpg, AfterInsertIntoAVL.jpg, AVLDelete.jpg
 */
public class AVLImplementation {

    private static AVLNode rotateRight(AVLNode root) {

        AVLNode newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;

        root.height = setHeight(root);
        newRoot.height = setHeight(newRoot);

        return newRoot;
    }

    private static AVLNode rotateLeft(AVLNode root) {

        AVLNode newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;

        root.height = setHeight(root);
        newRoot.height = setHeight(newRoot);

        return newRoot;
    }

    private static int setHeight(AVLNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max((root.left != null ? root.left.height: 0), (root.right != null ? root.right.height: 0));
    }

    private static int height(AVLNode root) {
        if (root == null) {
            return 0;
        }
        return root.height;
    }

    private static AVLNode insert(AVLNode root, int data) {

        //Binary Search Tree Insert
        if (root == null) {
            return new AVLNode(data);
        }

        if (root.data <= data) {
            root.right = insert(root.right, data);
        } else {
            root.left = insert(root.left, data);
        }

        root = AVLTreeBalance(root);

        return root;
    }

    private static AVLNode delete(AVLNode root, int data) {

        //BST delete

        //Base Case
        if (root == null) {
            return root;
        }

        if (data < root.data) {
            root.left = delete(root.left, data);
        } else if (data > root.data) {
            root.right = delete(root.right, data);
        } else { //matches

            //One or zero child
            if (root.left == null || root.right == null) {

                AVLNode kid = null;

                if (root.left != null) {
                    kid = root.left;
                }

                if (root.right != null) {
                    kid = root.right;
                }

                //If no child
                if (kid == null) {
                    root = null;
                } else {
                    root = kid;
                }
            } else { //2 children

                //Find the inorder successor
                AVLNode minNode = computeMin(root.right);

                //Swap current with min node value
                root.data = minNode.data;

                //Delete inorder successor
                root.right = delete(root.right, minNode.data);
            }
        }

        //AVL Tree balancing after delete
        root = AVLTreeBalance(root);

        return root;
    }

    private static AVLNode computeMin(AVLNode root) {

        AVLNode current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    private static AVLNode AVLTreeBalance(AVLNode root) {
        //Balance
        int balance = height(root.left) - height(root.right);

        if (balance > 1) { //Left

            if (height(root.left.left) >= height(root.left.right)) { //Left
                root = rotateRight(root);
            } else { //right

                //rotate left
                root.left = rotateLeft(root.left);

                //rotate right
                root = rotateRight(root);
            }
        } else if (balance < -1) { //right

            if (height(root.right.right) >= height(root.right.left)) { //right
                root = rotateLeft(root);
            } else { //left

                //rotate right
                root.right = rotateRight(root.right);

                //rotate left
                root = rotateLeft(root);

            }
        } else {
            root.height = setHeight(root);
        }
        return root;
    }

    private static void inorderTraversal(AVLNode root) {

        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.data + "-->");
            inorderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
        AVLNode root = null;
        root = insert(root, -10);
        root = insert(root, 2);
        root = insert(root, 13);
        root = insert(root, -13);
        root = insert(root, -15);
        root = insert(root, 15);
        root = insert(root, 17);
        root = insert(root, 20);

        inorderTraversal(root);

        root = null;
        root = insert(root,9);
        root = insert(root,5);
        root = insert(root, 10);
        root = insert(root, 0);
        root = insert(root,6);
        root = insert(root,11);
        root = insert(root,-1);
        root = insert(root,1);
        root = insert(root,2);

        System.out.println();
        inorderTraversal(root);
        System.out.println();
        root = delete(root, 10);
        inorderTraversal(root);
    }
}
