package IK.Trees.PreClass;

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
 * Binary Search Tree don't allow for duplicates since the search tree don't have any advantage in allowing for duplicates.
 *
 * images/BinarySearchTree.png
 */
public class BinarySearchTreeImplementation {

    public static BinaryTreeNode root = null;
    private static ArrayList<Integer> path;
    private static int numberOfNodes;

    private static boolean find(int n) {

        BinaryTreeNode current = root;
        numberOfNodes = 0;
        path = new ArrayList<>();

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

    //Worst case TC = O(n) if the tree is not balanced
    //Worst case TC = O(logn) if tree is balanced
    //SC = O(1)
    private static void insert(int id) {
        BinaryTreeNode newNode = new BinaryTreeNode(id);

        if (root == null) {
            root = newNode;
            return;
        }

        BinaryTreeNode parent = null;
        BinaryTreeNode current = root;

        while (current != null) {

            if(current.data == id) {
                //Duplicates are not allowed
                return;
            } else if (current.data < id) {
                parent = current;
                current = current.right;
            } else {
                parent = current;
                current = current.left;
            }
        }

        if(id < parent.data) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
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

        if (id == root.data) {
            //Duplicates not allowed so do nothing
        } else if (id < root.data) {
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

        if(root == null) {
            return null;
        }

        BinaryTreeNode current = root;

        while(current.left != null) {
            current = current.left;
        }
        return current;
    }

    private static BinaryTreeNode computeMax(BinaryTreeNode root) {
        if(root == null) {
            return null;
        }

        BinaryTreeNode current = root;
        while(current.right != null) {
            current = current.right;
        }

        return current;
    }

    /**
     * To find successor of a Node P
     * 1) Check if the node P has right subtree.
     * 2) If it has right subtree, the successor is the minimum in right subtree or left most node in right subtree
     * 3) Return that node
     * 4) Search for P starting from root
     * 5) Keep track of ancestor everytime we take left
     * 6) Return that ancestor.
     * 7) While current != null is changed to curr.data != p.data because we are going to always find P
     *
     * TC: O(logn) If the tree is balanced.
     */
    private static BinaryTreeNode findSuccessor(BinaryTreeNode root, BinaryTreeNode p) {
        if(root == null) {
            return null;
        }

        //Check if it has right subtree
        if(p.right != null) {
            BinaryTreeNode curr = p.right;

            while(curr.left != null) {
                curr = curr.left;
            }
            return curr; //This returns the smallest node in right subtree or left most node in right subtree
        }

        //Search for Node P starting from root
        BinaryTreeNode curr = root;
        BinaryTreeNode ancestor = null; //Keep track of ancestor everytime taking left

        while(curr.data != p.data) {

            if(p.data < curr.data) {
                ancestor = curr;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return ancestor;
    }

    /**
     * To find predecessor of a Node P
     * Every thing in successor code is reversed
     * 1) Check if the node P has left subtree.
     * 2) If it has left subtree, the successor is the maximum in left subtree or right most node in left subtree
     * 3) Return that node
     * 4) Search for P starting from root
     * 5) Keep track of ancestor everytime we take right
     * 6) Return that ancestor.
     * 7) While current != null is changed to curr.data != p.data because we are going to always find P
     *
     * TC: O(logn) If the tree is balanced.
     */
    private static BinaryTreeNode findPredecessor(BinaryTreeNode root, BinaryTreeNode p) {
        if(root == null) {
            return null;
        }

        //Check if it has right subtree
        if(p.left != null) {
            BinaryTreeNode curr = p.left;

            while(curr.right != null) {
                curr = curr.right;
            }
            return curr; //This returns the smallest node in right subtree or left most node in right subtree
        }

        //Search for Node P starting from root
        BinaryTreeNode curr = root;
        BinaryTreeNode ancestor = null; //Keep track of ancestor everytime taking left

        while(curr.data != p.data) {

            if(p.data < curr.data) {
                curr = curr.left;
            } else {
                ancestor = curr;
                curr = curr.right;
            }
        }
        return ancestor;
    }

    private static BinaryTreeNode deleteFinal(BinaryTreeNode root, int id) {

        //Search for the node to be deleted.
        BinaryTreeNode curr = root;
        BinaryTreeNode prev = null;

        while(curr != null) {

            if(curr.data == id) {
                break;
            } else if (id < curr.data) {
                prev = curr;
                curr = curr.left;
            } else {
                prev = curr;
                curr = curr.right;
            }
        }

        //If the node is not found or if the tree is empty then curr can be null
        if(curr == null) {
            return root;
        }

        //Case 1: If node to be deleted is a leaf node
        if(curr.left == null && curr.right == null) {

            if(prev == null) { //If there is only one node
                return null;
            }

            if(curr == prev.left) {
                prev.left = null;
            } else {
                prev.right = null;
            }

            return root;
        }

        //Case 2: If node to be deleted has one child. curr node has one child
        BinaryTreeNode child = null;

        if(curr.left != null && curr.right == null) {
            child = curr.left;
        }

        if(curr.right != null && curr.left == null) {
            child = curr.right;
        }

        if(child != null) {

            if(prev == null) { //curr is root node
                root = child;
                return root;
            }

            if(curr == prev.left) {
                prev.left = child;
            } else {
                prev.right = child;
            }
            return root;
        }

        //Case 3: If node to be delete has two child. curr node has 2 child
        if(curr.left != null && curr.right != null) {

            BinaryTreeNode succ = curr.right;
            prev = curr;

            //Find the leftmost node or smallest node in right subtree
            while(succ.left != null) {
                prev = succ;
                succ = succ.left;
            }

            //Swap the current with successor
            curr.data = succ.data;

            //Delete successor, Successor has only right child
            if(prev.left == succ) {
                prev.left = succ.right;
            } else {
                prev.right = succ.right;
            }
            return root;
        }

        return root;
    }

    //Find Max in BST
    private static BinaryTreeNode findMax(BinaryTreeNode root) {
        BinaryTreeNode current = root;

        while (current.right != null) {
            current = current.right;
        }
        return current;
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
        root = recursiveInsert(root, 15);
        insert(16);
        root = recursiveInsert(root, 17);

        System.out.println("Original Tree: ");
        display(root);
        System.out.println();
        System.out.println("Check whether Node with value 4 exists : " + find(4));
        System.out.println(" Path " + path);
        System.out.println("Distance of 4 from root is " + (numberOfNodes - 1));

        System.out.println("The minimum is " + computeMin(root).data);
        System.out.println("The maximum is " + computeMax(root).data);

        System.out.println("Check whether Node with value 16 exists : " + find(16));
        System.out.println(" Path " + path);
        System.out.println("Distance of 16 from root is " + (numberOfNodes - 1));

        System.out.println("Delete Node with no children (2) : ");
        root = deleteFinal(root, 2);
        display(root);

        System.out.println("\n Delete Node with one child (4) : ");
        root = deleteFinal(root, 4);
        display(root);

        //System.out.println("\n Delete Node with Two children (10) : " + delete(10));
        System.out.println();
        System.out.println("After delete 10");
        root = deleteFinal(root, 10);
        display(root);

        System.out.println("After delete 3");
        root = deleteFinal(root, 3);
        display(root);

        System.out.println("After delete 20");
        root = deleteFinal(root, 20);
        display(root);


        System.out.println();
        System.out.println("The max using iterative: " + findMax(root).data);
        System.out.println("The max using recursive: " + findMaxRecursive(root).data);

        System.out.println("The successor of " + findSuccessor(root, root.right.right.left).data);
        System.out.println("The predecessor of " + findPredecessor(root, root.right.right.left).data);
    }
}
