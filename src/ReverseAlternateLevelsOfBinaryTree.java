import Node.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Reverse alternate levels of given Binary Tree.
 *
 * Approach:
 *
 * 1) Do inorder traversal.
 * 2) Store alternate level nodes in ArrayList
 * 3) Reverse the ArrayList
 * 4) Do inorder traversal.
 * 5) Placed reversed ArrayList in the same order in which it was fetched in step one.
 * 6) Do level order traversal and print nodes
 *
 * Time Complexity: O(n)
 *
 */
public class ReverseAlternateLevelsOfBinaryTree {

    private static ArrayList<Integer> arrayList = new ArrayList<>();

    private static void storeAlternateLevels(BinaryTreeNode root, int level) {

        if (root != null) {

            storeAlternateLevels(root.left, level + 1);

            if (level % 2 != 0) {
                arrayList.add(root.data);
            }

            storeAlternateLevels(root.right, level + 1);
        }
    }

    private static void reverseAlternateLevels(BinaryTreeNode root, int level) {

        if (root != null) {

            reverseAlternateLevels(root.left, level + 1);

            if (level % 2 != 0) {
                root.data = arrayList.remove(0);
            }

            reverseAlternateLevels(root.right, level + 1);
        }
    }

    private static void printLevelOrderTraversal(BinaryTreeNode root) {
        Queue<BinaryTreeNode> queue = new LinkedList<>();

        int levelNodes = 0;

        if (root == null) {
            return;
        }

        queue.add(root);

        while (!queue.isEmpty()) {

            levelNodes = queue.size();

            while (levelNodes > 0) {

                BinaryTreeNode removedNode = queue.remove();

                System.out.print(" " + removedNode.data);

                if (removedNode.left != null) {
                    queue.add(removedNode.left);
                }

                if (removedNode.right != null) {
                    queue.add(removedNode.right);
                }

                levelNodes--;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws java.lang.Exception {

        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode (2);
        root.right = new BinaryTreeNode (3);
        root.left.left = new BinaryTreeNode (4);
        root.left.right = new BinaryTreeNode (5);
        root.right.left = new BinaryTreeNode (6);
        root.right.right = new BinaryTreeNode (7);
        root.left.left.left = new BinaryTreeNode (8);
        root.left.left.right = new BinaryTreeNode (9);
        root.left.right.left = new BinaryTreeNode (10);
        root.left.right.right = new BinaryTreeNode (11);

        root.right.left.left = new BinaryTreeNode (12);
        root.right.left.right = new BinaryTreeNode (13);
        root.right.right.left = new BinaryTreeNode (14);
        root.right.right.right = new BinaryTreeNode (15);

        System.out.println("Orininal Tree");
        printLevelOrderTraversal(root);

        storeAlternateLevels(root,0);

        Collections.reverse(arrayList);

        reverseAlternateLevels(root,0);

        System.out.println("\n New Tree, Alternate Levels Reversed..");
        printLevelOrderTraversal(root);

    }
}
