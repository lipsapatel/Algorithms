import Node.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.

 Note: A leaf is a node with no children.

 Example:

 Input:

 1
 /   \
 2     3
 \
 5

 Output: ["1->2->5", "1->3"]

 Explanation: All root-to-leaf paths are: 1->2->5, 1->3

 1) Do preorder traversal

 */
public class BinaryTreePath {

    public static List<String> binaryTreePaths(BinaryTreeNode root) {

        List<String> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        binaryTreePathsHelper(root, result, new StringBuilder());
        return result;
    }

    public static void binaryTreePathsHelper(BinaryTreeNode root, List<String> path, StringBuilder singlePath) {

        if (root.left == null && root.right == null) { //leaf node
            singlePath.append(root.data);
            path.add(singlePath.toString());
            return;
        }

        singlePath.append(root.data);
        singlePath.append("->");

        if (root.left != null) {
            binaryTreePathsHelper(root.left, path, new StringBuilder(singlePath.toString()));
        }

        if (root.right != null) {
            binaryTreePathsHelper(root.right, path, new StringBuilder(singlePath.toString()));
        }

    }

    public static void main(String[] args) {
        BinaryTreeNode node = new BinaryTreeNode(1);
        node.left = new BinaryTreeNode(2);
        node.right = new BinaryTreeNode(3);
        node.left.right = new BinaryTreeNode(5);

        System.out.println(binaryTreePaths(node));
    }
}
