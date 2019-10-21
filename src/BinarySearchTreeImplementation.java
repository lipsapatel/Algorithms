import Node.BinaryTreeNode;

import java.util.ArrayList;

/**
 * Binary Search Tree: Nodes smaller than root goes to the left and nodes greater than root
 *                      goes to the right.
 *  Below time complexity is for best and Average case.
 *  The worst case is O(n) in which case the tree is skewed
 *
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

    //TC = O(n)
    //SC = O(1)
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

    //If the root is not global variable and we are passing it, then we need to return root because the root might get
    //changed if the tree is empty and it inserts the first node.
    //Best and Average Case: TC=O(logn) SC = O(logn)
    //Worst Case: Skewed tree: TC = O(n) SC = O(n)
    private static BinaryTreeNode recursiveInsert(BinaryTreeNode root, int id) {

        //Base Case
        if (root == null) {
            return new BinaryTreeNode(id);
        }

        if (id <= root.data) {
            root.left = recursiveInsert(root.left, id);
        } else {
            root.right = recursiveInsert(root.right, id);
        }
        return root;
    }

    private static void display(BinaryTreeNode root) {

        if (root != null) {
            display(root.left);
            System.out.print(root.data + "->");
            display(root.right);
        }
    }

    //Recursive Binary Search tree delete
    //TC = O(n) SC = O(1)
    private static BinaryTreeNode delete(BinaryTreeNode root, int val) {

        //Base Case
        if (root == null) {
            return root;
        }

        if (val < root.data) {
            root.left = delete(root.left, val);
        } else if (val > root.data) {
            root.right = delete(root.right, val);
        } else {

            //One or no child
            if (root.left == null || root.right == null) {
                //Find kid
                BinaryTreeNode kid = null;

                if (root.left != null) {
                    kid = root.left;
                }

                if (root.right != null) {
                    kid = root.right;
                }

                //If no child
                if (kid == null) {
                    root = null;
                } else { //One child
                    root = kid;
                }
            } else { //two child

                //Here we are finding the in-order successor
                BinaryTreeNode minNode = computeMin(root.right);

                //Swap the value of current with min node value
                root.data = minNode.data;

                // Delete the inorder successor
                root.right = delete(root.right, minNode.data);
            }
        }
        return root;
    }

    private static BinaryTreeNode computeMin(BinaryTreeNode root) {

        BinaryTreeNode current = root;

        while(current.left != null) {
            current = current.left;
        }
        return current;
    }

    private static boolean delete(int id) {
        if (root == null) {
            return false;
        }

        //Find the node to be deleted
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

    //Find Max in BST
    private static BinaryTreeNode findMax(BinaryTreeNode root) {
        BinaryTreeNode parent = null;
        BinaryTreeNode current = root;

        while (current != null) {
            parent = current;
            current = current.right;
        }
        return parent;
    }

    //Recursive
    private static BinaryTreeNode findMaxRecursive(BinaryTreeNode root) {

        //Base Case
        if (root.right == null) {
            return root;
        }

        return findMaxRecursive(root.right);
    }

    public static void main(String[] args) {
        root = recursiveInsert(root, 3);
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
        root = recursiveInsert(root, 17);

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

        //System.out.println("\n Delete Node with Two children (10) : " + delete(10));
        System.out.println();
        System.out.println("After delete");
        root = delete(root, 10);
        display(root);

        System.out.println("After delete");
        root = delete(root, 3);
        display(root);

        System.out.println("After delete");
        root = delete(root, 20);
        display(root);


        System.out.println();
        System.out.println("The max using iterative: " + findMax(root).data);
        System.out.println("The max using recursive: " + findMaxRecursive(root).data);
    }
}
