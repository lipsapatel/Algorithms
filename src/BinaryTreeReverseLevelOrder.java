import Node.BinaryTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its bottom-up level order traversal as:
 [
 [15,7],
 [9,20],
 [3]
 ]
 */
public class BinaryTreeReverseLevelOrder {

    private static List<List<Integer>> levelOrderBottom(BinaryTreeNode root) {

        List<List<Integer>> array = new ArrayList<>();

        if (root == null) {
            return array;
        }

        List<Integer> temp = new ArrayList<>();
        Queue<BinaryTreeNode> queue = new LinkedList<>();

        queue.add(root);
        temp.add(root.data);
        array.add(temp);

        while(!queue.isEmpty()) {

            int size = queue.size();
            temp = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                BinaryTreeNode removedNode = queue.remove();

                if (removedNode.left != null) {
                    queue.add(removedNode.left);
                    temp.add(removedNode.left.data);
                }

                if (removedNode.right != null) {
                    queue.add(removedNode.right);
                    temp.add(removedNode.right.data);
                }
            }
            if (!temp.isEmpty()) {
                array.add(0, temp);
            }
        }
        return array;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(3);
        root.left = new BinaryTreeNode(9);
        root.right = new BinaryTreeNode(20);

        root.right.left = new BinaryTreeNode(15);
        root.right.right = new BinaryTreeNode(7);

        System.out.println("Reverse order: " + levelOrderBottom(root));
    }
}
