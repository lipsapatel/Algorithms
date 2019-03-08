import Node.BinaryTreeNode;

import java.util.ArrayList;

/**
 * Binary Search Tree: Nodes smaller than root goes to the left and nodes greater than root
 *                      goes to the right.
 * insert(int n) - O(logn)
 * find(int n) - O(logn)
 * delete(int n) - O(logn)
 * display() - prints entire tree in increasing order - O(n) -
 * Inorder traversal left, root, right
 *
 * Height of balanced tree is = logn
 * Time complexity is O(h) for all the operations in BST.
 *
 * Find - Either go to search in left or right by comparing n with root.data
 * insert - Either go the left or right and when current == null, insert your node.
 * delete - Node to be deleted is leaf node
 * Node to be deleted has only one child
 * Node to be deleted has 2 child. Find successor the smallest node in the
 * right subtree.
 *
 * images/BinarySearchTree.png
 */
public class BinarySearchTreeImplementation {

    public static BinaryTreeNode root = null;
    private static ArrayList path;
    private static int numberOfNodes;

    private static boolean find(int n) {

        BinaryTreeNode current = root;
        numberOfNodes = 0;
        path = new ArrayList();

        while(current != null) {
            if (current.data == n) {
                numberOfNodes++;
                path.add(current.data);
                return true;
            } else if (current.data > n) {
                numberOfNodes++;
                path.add(current.data);
                current = current.left;
            } else if (current.data < n) {
                numberOfNodes++;
                path.add(current.data);
                current = current.right;
            }
        }

        return false;
    }

    private static void insert(int id) {
        BinaryTreeNode newNode = new BinaryTreeNode(id);

        BinaryTreeNode parent;
        BinaryTreeNode current = root;

        if (root == null) {
            root = newNode;
            return;
        }

        while (true) {
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

    private static void display(BinaryTreeNode root) {

        if (root != null) {
            display(root.left);
            System.out.print(root.data + "->");
            display(root.right);
        }
    }

    private static boolean delete(int id) {
        if (root == null) {
            return false;
        }

        BinaryTreeNode current = root;
        BinaryTreeNode parent = null;
        boolean isLeftNode = false;

        while (current.data != id) {
            parent = current;

            if (current.data < id) {
                current = current.right;
                isLeftNode = false;
            } else {
                current = current.left;
                isLeftNode = true;
            }

            if (current == null) {
                return false;
            }
        }

        //Case 1: If current is a leaf node
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftNode) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }

        //Case 2 If node to be delete has one child
        else if (current.left == null) {
            if (current == root) {
                root = current.right;
            }
            if (isLeftNode) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        }

        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            }

            if (isLeftNode) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        }

        //Case 3 If current has both left and right child, calculate the successor
        // from right subtree
        else if (current.left != null && current.right != null) {
            BinaryTreeNode successor = getInOrderSuccessor(current);

            if (current == root) {
                root = successor;
            }
            if (isLeftNode) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }

            successor.left = current.left;
        }

        return true;
    }

    private static BinaryTreeNode getInOrderSuccessor(BinaryTreeNode deleteNode) {
        BinaryTreeNode successor = null;
        BinaryTreeNode successorParent = null;
        BinaryTreeNode current = deleteNode.right; //To get inorder predecessor, this becomes left

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left; //This becomes right for inorder predecessor.
        }

        //Check if the successor has right child, it cannot have left child for sure
        //If it has right child then add that to the successorParent.left
        if (successor != deleteNode.right) {
            successorParent.left = successor.right;
            successor.right = deleteNode.right;
        }

        return successor;
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
        System.out.println("Check whether Node with value 4 exists : " + find(4));
        System.out.println(" Path " + path);
        System.out.println("Distance of 4 from root is " + (numberOfNodes - 1));

        System.out.println("Check whether Node with value 16 exists : " + find(16));
        System.out.println(" Path " + path);
        System.out.println("Distance of 16 from root is " + (numberOfNodes - 1));

        System.out.println("Delete Node with no children (2) : " + delete(2));
        display(root);

        System.out.println("\n Delete Node with one child (4) : " + delete(4));
        display(root);

        System.out.println("\n Delete Node with Two children (10) : " + delete(10));
        display(root);
    }
}
