import Node.BinaryTreeNode;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

 Note: A leaf is a node with no children.

 Example:

 Given the below binary tree and sum = 22,

 5
 / \
 4   8
 /   / \
 11  13  4
 /  \      \
 7    2      1
 return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

 Time Complexity: O(n)
 Space Complexity: O(n)
 */
public class hasPathSum {

    public static boolean hasPathSum(BinaryTreeNode root, int sum) {
        return hasPathSumHelper(root, sum, 0);
    }

    public static boolean hasPathSumHelper(BinaryTreeNode root, int sum, int totalSum) {
        if (root != null) {
            totalSum += root.data;

            //If leaf node and sum met return
            if (root.left == null && root.right == null && totalSum == sum) {
                //Leaf node
                return true;
            }
            return hasPathSumHelper(root.left, sum, totalSum)
                    || hasPathSumHelper(root.right, sum, totalSum);
        }
        return false;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(4);
        root.right = new BinaryTreeNode(8);

        root.left.left = new BinaryTreeNode(11);
        root.right.left = new BinaryTreeNode(13);
        root.right.right = new BinaryTreeNode(4);

        root.left.left.left = new BinaryTreeNode(7);
        root.left.left.right = new BinaryTreeNode(2);

        root.right.right.right = new BinaryTreeNode(1);

        System.out.println("Has Path sum: " + hasPathSum(root, 22));
    }
}
