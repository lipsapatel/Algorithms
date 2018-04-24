import Node.BinaryTreeNode;

import java.util.Stack;

/**
 * Search element in binary tree both using Recursive and Non-Recursive Approach
 *
 * Recursive Approach: Do preorder traversal (root, left, right)
 * if root is equal to search element then return true
 * else do recursive search in left and right subtree
 *
 *
 */
public class SearchElementInBinaryTree {

    //Preorder traversal
    private static boolean recursiveSearchElementInBinaryTree(BinaryTreeNode root, int data) {

        if (root != null) {
            //Check if root is equal to the element
            if (root.data == data) {
                return true;
            } else {
                //search left and then search right subtree
                return recursiveSearchElementInBinaryTree(root.left, data) || recursiveSearchElementInBinaryTree(root.right, data);
            }
        }
        return false;
    }

    /**
     * We need stack to store the traversal
     * Do Preorder traversal (root, left, right)
     * if root is not null
     *
     * push root node to stack
     *
     * while stack is not empty
     * pop node
     * check if popped node == data
     * if not then push left node and right node
     * @param root - Root node
     * @param data - data to search in binary tree
     * @return boolean true if data found else false
     */
    private static boolean nonRecursiveSearchElementInBinaryTree(BinaryTreeNode root, int data) {
        if (root != null) {

            Stack<BinaryTreeNode> stack1 = new Stack<BinaryTreeNode>();

            //Push root node
            stack1.push(root);

            while(!stack1.isEmpty()) {
                BinaryTreeNode poppedNode = stack1.pop();

                if (poppedNode.data == data) {
                    return true;
                }

                if (poppedNode.left != null) {
                    stack1.push(poppedNode.left);
                }

                if(poppedNode.right != null) {
                    stack1.push(poppedNode.right);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);

        System.out.println("Does 4 exist in the tree by recursive search: " + recursiveSearchElementInBinaryTree(root, 4));
        System.out.println("Does 7 exist in the tree by recursive search: " + recursiveSearchElementInBinaryTree(root, 7));

        System.out.println("Does 4 exist in the tree by non-recursive search: " + nonRecursiveSearchElementInBinaryTree(root, 4));
        System.out.println("Does 7 exist in the tree by non-recursive search: " + nonRecursiveSearchElementInBinaryTree(root, 7));
    }

}
