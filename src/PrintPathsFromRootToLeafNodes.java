import Node.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Print paths from root to all leaf nodes in Binary Tree.
 *
 * resources/PrintPathsFromRootToLeafNodes.png
 *
 * Approach:
 *
 * 1) Do preorder traversal
 * 2) Take two variable path and pathLength
 * 3) Store root.data in path
 * 4) Check if you are at leaf node (root.left == null && root.right == null)
 * 5) If yes then print the path
 * 6) Else make recursive call to root.left and root.right. Pass the path and pathLevel
 * so that at each level have it's own copy of path and pathLength.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) Total number of paths will be n/2(leaf) * logn(height of complete binary tree)
 */
public class PrintPathsFromRootToLeafNodes {

    private static int pathLength = 0;
    private static int[] path = new int[500];

    private static void pathFromRootToLeafNode(BinaryTreeNode root, int pathLength, int[] path) {

        if (root == null) {
            return;
        }

        path[pathLength++] = root.data;

        if (root.left == null && root.right == null) {
            printPathFromRootToLeafNode(path, pathLength);
        } else {
            pathFromRootToLeafNode(root.left, pathLength, path);
            pathFromRootToLeafNode(root.right, pathLength, path);
        }
    }

    private static void printPathFromRootToLeafNode(int[] path, int pathLength) {

        for (int i = 0; i < pathLength; i++) {
            System.out.print(" " + path[i]);
        }

        System.out.println();
    }

    private static List<List<Integer>> allPathsOfABinaryTree(BinaryTreeNode root) {

        List<List<Integer>> list = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        allPathsOfABinaryTreeHelper(root, list, path);
        return list;
    }

    private static void allPathsOfABinaryTreeHelper(BinaryTreeNode root, List<List<Integer>> list, List<Integer> path) {

        //Base Case
        if (root == null) {
            return;
        }

        //Add to path
        path.add(root.data);

        //If leaf node, add path to list and remove that node
        if (root.left == null && root.right == null) {
            list.add(copyPath(path));
            path.remove(path.size() - 1); //remove leaf node
        } else {
            allPathsOfABinaryTreeHelper(root.left, list, path);
            allPathsOfABinaryTreeHelper(root.right, list, path);
            path.remove(path.size() - 1); //Remove the node after processing left and right child
        }
    }

    private static List<Integer> copyPath(List<Integer> path) {
        List<Integer> pathCopy = new ArrayList<>();

        for (Integer data: path) {
            pathCopy.add(data);
        }
        return pathCopy;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode (2);
        root.right = new BinaryTreeNode (3);
        root.left.left = new BinaryTreeNode (4);
        root.left.left.left = new BinaryTreeNode (7);
        root.right.left = new BinaryTreeNode (6);
        root.right.left.left = new BinaryTreeNode (8);
        root.right.left.right = new BinaryTreeNode (9);

        int [] path = new int [100];
        pathFromRootToLeafNode(root, 0, path);

        BinaryTreeNode root1 = new BinaryTreeNode(10);
        root1.left = new BinaryTreeNode(20);
        root1.left.left = new BinaryTreeNode(40);
        root1.left.right = new BinaryTreeNode(50);
        root1.right = new BinaryTreeNode(30);

        List<List<Integer>> list = allPathsOfABinaryTree(root1);

        for (List<Integer> rootToLeafPath: list) {
            for (Integer data: rootToLeafPath) {
                System.out.print(data + " ");
            }
            System.out.println();
        }
    }
}
