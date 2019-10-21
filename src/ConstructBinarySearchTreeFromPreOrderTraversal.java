import Node.BinaryTreeNode;

import java.util.Stack;

/**
 * Given pre-order traversal, construct binary search tree.
 *
 *             10
 *            /  \
 *           7    20
 *         /  \   /  \
 *       5    8  15   25
 *
 *  Pre-Order Traversal = 10, 7, 5, 8, 20, 15, 25
 *
 *  Approach:
 *
 *  1) Create root node with first element because first element is root in Pre-order traversal
 *  2) Find all the elements less than root which goes to left
 *  3) All elements greater than root goes to right
 *
 *  Time Complexity: O(n ^2)
 *  Space Complexity: O(n)
 *
 *  Approach2:
 *
 *  Time Complexity: O(n)
 *  Space Complexity: O(n)
 *
 *  1) Create empty stack.
 *  2) Create root node.
 *  3) Push it to stack.
 *  4) Iterate all the pre order elements.
 *  5) If the preorder element is less than that of stack, then make left child and push to stack
 *  6) If the preorder element is greater than stack, pop all less elements from stack, make it right child and push to stack
 */
public class ConstructBinarySearchTreeFromPreOrderTraversal {

    private static BinaryTreeNode buildBinarySearchTreeFromPre(int[] pre) {

        if (pre == null) {
            return null;
        }

        return buildTree(pre, 0, pre.length - 1);
    }

    private static BinaryTreeNode buildTree(int[] pre, int start, int end) {

        if (start > end) {
            return null;
        }

        if (start == end) { //Only one element left
            return new BinaryTreeNode(pre[start]);
        }

        BinaryTreeNode root = new BinaryTreeNode(pre[start]);

        int i;

        //Find all the elements less than root
        for (i = start + 1; i <= end; i++) {

            if (pre[i] > pre[start]) {
                break;
            }
        }

        root.left = buildTree(pre, start + 1, i - 1);
        root.right = buildTree(pre, i, end);
        return root;
    }

    //TC = O(n) and SC = O(n)
    private static BinaryTreeNode buildBSTFromPreUsingStack(int[] pre) {

        if (pre == null || pre.length == 0) {
            return null;
        }

        int size = pre.length;

        BinaryTreeNode root = new BinaryTreeNode(pre[0]);
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);

        //Iterate
        for (int i = 1; i < size; i++) {

            //If pre is greater, then pop all less from stack
            BinaryTreeNode temp = null;

            while(!stack.isEmpty() && stack.peek().data < pre[i]) {
                temp = stack.pop();
            }

            //Make it right child and push to stack
            if (temp != null) {
                temp.right = new BinaryTreeNode(pre[i]);
                stack.push(temp.right);
            }

            else { //Make it left child and push to stack
                temp = stack.peek();
                temp.left = new BinaryTreeNode(pre[i]);
                stack.push(temp.left);
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

    public static void displayPostOrder(BinaryTreeNode root) {

        if(root != null) {
            displayPostOrder(root.left);
            displayPostOrder(root.right);
            System.out.print(root.data + "-->");
        }
    }

    public static void main(String[] args) {
        int[] preorder = {10, 7, 5, 8, 20, 15, 25};

        BinaryTreeNode root = buildBinarySearchTreeFromPre(preorder);
        displayPostOrder(root);
        System.out.println();
        displayInOrder(root);

        BinaryTreeNode root2 = buildBSTFromPreUsingStack(preorder);
        System.out.println();
        displayPostOrder(root2);
        System.out.println();
        displayInOrder(root2);

        int[] preorder1 = {2, 1};

        BinaryTreeNode root1 = buildBinarySearchTreeFromPre(preorder1);
        System.out.println();
        displayPostOrder(root1);
        System.out.println();
        displayInOrder(root1);

        BinaryTreeNode root3 = buildBSTFromPreUsingStack(preorder1);
        System.out.println();
        displayPostOrder(root3);
        System.out.println();
        displayInOrder(root3);
    }
}
