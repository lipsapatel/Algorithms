import Node.BinaryTreeNode;

import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST).
 * Your iterator will be initialized with the root node of BST.
 *
 * 1) Calling next() will return the next smallest number in the BST
 * 2) Calling hasNext() should return whether the next element exists.
 *
 * Both function should run in O(1) time and O(h) memory where h is the height of the tree.
 *
 * Sample Test Case:

 Sample Input:
        2
       /  \
      1    3

 Sample Output:

 1 2 3

Approach:

 1) Take stack
 2) Push all the left most nodes to stack
 3) when popped push the left most nodes of all the right's node.
 4) Continue till stack is empty and all nodes are traversed.

 Time Complexity: O(n)
 Space Complexity: O(n)

 */
public class BSTIterator {

    private static Stack<BinaryTreeNode> stack;

    public BSTIterator(BinaryTreeNode root) {
        stack = new Stack<>();
        pushLeft(root);
    }

    private static void pushLeft(BinaryTreeNode root) {
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public int next() {
        BinaryTreeNode current = null;

        if (!stack.isEmpty()) {
            current = stack.pop();
            pushLeft(current.right);
        }
        if (current == null) {
            return Integer.MIN_VALUE;
        } else {
            return current.data;
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(2);
        root.left = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(3);

        BSTIterator iterator = new BSTIterator(root);
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
    }
}
