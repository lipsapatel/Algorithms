import Node.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, convert it into in mirror tree.
 *
 * Mirror tree of a binary tree in which left and right child of every node is interexchanged.
 *
 * resources/MirrorTree.png
 *
 * Approach 1: Recursive Approach
 *
 * 1) Do the post order traversal
 * 2) Left Recursive call and right recursive call
 * 3) Swap the left and right child of root
 *
 * TC: O(n)
 * SC: O(n)
 *
 * Approach 2: Iterative Approach
 *
 * 1) Do BFS traversal
 * 2) Take queue and add root to queue
 * 3) while queue is not empty, pop from queue
 * 4) Swap it's left and right child
 * 5) push left and right child to queue.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class ConvertBinaryTreeIntoMirrorBinaryTree {

    private static BinaryTreeNode convertBinaryTreeIntoMirrorBinaryTree(BinaryTreeNode root) {

        //Base Case
       if (root == null) {
           return null;
       }

       BinaryTreeNode left = convertBinaryTreeIntoMirrorBinaryTree(root.left);
       BinaryTreeNode right = convertBinaryTreeIntoMirrorBinaryTree(root.right);

       root.left = right;
       root.right = left;
       return root;
    }

    private static BinaryTreeNode convertBinaryTreeIntoMirrorBinaryTreeIterative(BinaryTreeNode root) {

        if (root == null) {
            return null;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        //Do BFS while doing BFS swap left and right children
        while(!queue.isEmpty()) {
            BinaryTreeNode node = queue.remove();

            //Swap
            BinaryTreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            //Push left and right children
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

        return root;
    }

    private static void displayBinaryTree(BinaryTreeNode root) {

        if (root != null) {
            displayBinaryTree(root.left);
            System.out.print(root.data + "-->");
            displayBinaryTree(root.right);
        }
    }

    public static void main(String[] args) {

        BinaryTreeNode root = new BinaryTreeNode(4);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(6);
        root.left.left = new BinaryTreeNode(1);
        root.left.right = new BinaryTreeNode(3);
        root.right.left = new BinaryTreeNode(5);
        root.right.right = new BinaryTreeNode(7);

        displayBinaryTree(root);
        System.out.println();

        BinaryTreeNode root3 = convertBinaryTreeIntoMirrorBinaryTree(root);

        System.out.println("Mirror Binary Tree:");
        displayBinaryTree(root3);
        System.out.println();

        BinaryTreeNode root1 = new BinaryTreeNode(4);
        root1.left = new BinaryTreeNode(2);
        root1.right = new BinaryTreeNode(6);
        root1.left.left = new BinaryTreeNode(1);
        root1.left.right = new BinaryTreeNode(3);
        root1.right.left = new BinaryTreeNode(5);
        root1.right.right = new BinaryTreeNode(7);

        displayBinaryTree(root1);
        System.out.println();

        BinaryTreeNode root2 = convertBinaryTreeIntoMirrorBinaryTreeIterative(root1);

        System.out.println("Mirror Binary Tree:");
        displayBinaryTree(root2);
        System.out.println();
    }
}
