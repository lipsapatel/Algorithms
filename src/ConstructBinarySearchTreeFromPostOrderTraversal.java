import Node.BinaryTreeNode;

import java.util.Stack;

/**
 * Given post-order traversal, construct binary search tree.
 *
 *                    10
 *                 /      \
 *                7        20
 *              /  \      /  \
 *             5   8     15  25
 *
 *  Post-Order Traversal: 5, 8, 7, 15, 25, 20, 10
 *
 *  Approach:
 *
 *  1) Start from the end
 *  2) Create root node with the last element because we know in post-order traversal the last element is the root
 *  3) Find all the elements greater than root which goes to right
 *  4) All elements less than root goes to left
 *
 *  Time Complexity: O(n ^ 2)
 *  Space Complexity: O(n)
 *
 *   Approach2:
 *
 *  Time Complexity: O(n)
 *  Space Complexity: O(n)
 *
 *  1) Create empty stack.
 *  2) Create root node.
 *  3) Push it to stack.
 *  4) Iterate all the post order elements from end to start
 *  5) If the post order element is greater than that of stack, then make right child and push to stack
 *  6) If the post order element is smaller than stack, pop all greater elements from stack, make it left child and push to stack
 */
public class ConstructBinarySearchTreeFromPostOrderTraversal {

    private static BinaryTreeNode buildBinarySearchTreeFromPost(int[] post) {

        if (post == null) {
            return null;
        }

        return buildTree(post, 0, post.length - 1);
    }

    private static BinaryTreeNode buildTree(int[] post, int start, int end) {

        if (start > end) {
            return null;
        }

        if (start == end) { //Only one element left
            return new BinaryTreeNode(post[end]);
        }

        BinaryTreeNode root = new BinaryTreeNode(post[end]);

        int i;

        for (i = end - 1; i >= start; i--) {

            if (post[i] <= post[end]) {
                break;
            }
        }

        root.right = buildTree(post, i + 1, end - 1);
        root.left = buildTree(post, start, i);

        return root;
    }

    private static BinaryTreeNode buildBSTFromPostUsingStack(int[] post) {

        if (post == null || post.length == 0) {
            return null;
        }

        int size = post.length;
        BinaryTreeNode root = new BinaryTreeNode(post[size - 1]);
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);

        //Iterate
        for (int i = size - 2; i >= 0; i--) {

            //If post is smaller, then pop all greater from stack
            BinaryTreeNode temp = null;

            while(!stack.isEmpty() && stack.peek().data > post[i]) {
                temp = stack.pop();
            }

            //Make it left child and push to stack
            if (temp != null) {
                temp.left = new BinaryTreeNode(post[i]);
                stack.push(temp.left);
            }

            else { //Make it right child and push to stack
                temp = stack.peek();
                temp.right = new BinaryTreeNode(post[i]);
                stack.push(temp.right);
            }
        }
        return root;
    }

    public static void displayInOrder(BinaryTreeNode root) {

        if(root != null) {
            displayInOrder(root.left);
            System.out.print(root.data + "-->");
            displayInOrder(root.right);
        }
    }

    public static void displayPreOrder(BinaryTreeNode root) {

        if(root != null) {
            System.out.print(root.data + "-->");
            displayPreOrder(root.left);
            displayPreOrder(root.right);
        }
    }

    public static void main(String[] args) {

        int[] postOrder = {5, 8, 7, 15, 25, 20, 10};

        BinaryTreeNode root = buildBinarySearchTreeFromPost(postOrder);

        displayPreOrder(root);
        System.out.println();
        displayInOrder(root);
        System.out.println();

        int[] postOrder2 = {5, 8, 7, 15, 25, 20, 10};

        BinaryTreeNode root2 = buildBSTFromPostUsingStack(postOrder2);

        displayPreOrder(root2);
        System.out.println();
        displayInOrder(root2);

        int[] postOrder1 = {4, 2};

        BinaryTreeNode root1 = buildBinarySearchTreeFromPost(postOrder1);

        System.out.println();
        displayPreOrder(root1);
        System.out.println();
        displayInOrder(root1);

        int[] postOrder3 = {4, 2};

        BinaryTreeNode root3 = buildBSTFromPostUsingStack(postOrder3);

        System.out.println();
        displayPreOrder(root3);
        System.out.println();
        displayInOrder(root3);
    }
}
