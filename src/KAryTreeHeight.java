import Node.BinaryTreeNode;

/**
 * Given k-ary tree, find the height of tree (longest path from root to any node)
 * We are looking for number of edges on the longest path from root to any node
 * From any node, you can access all its children using node's property named children.
 *
 * Sample Input:
 3-ary tree:
 1 is the root of the tree.
 2's parent is 1.
 3's parent is 1.
 5's parent is 1.
 4's parent is 5.

 Sample Output:
 2

 Approach:

 Time Complexity: O(n)
 Space Complexity: O(n)

 DFS Approach
 1) Add the base cases, if root == null or root have no children
 2) Make recursive call for all children, set height to max of returned height and current max.

 height(parent) = max(height(children)) + 1 : when parent is not a leaf node.
 0 : when parent is a leaf node.

 Height of K-Ary tree = log n base k instead of 2.

 */
public class KAryTreeHeight {

    private static int findHeight(BinaryTreeNode root) {

        //Base Case
        if (root == null) {
            return -1;
        }
        if (root.children.isEmpty()) {
            return 0;
        }

        int maxHeight = Integer.MIN_VALUE;
        for (BinaryTreeNode child: root.children) {

            maxHeight = Math.max(maxHeight, findHeight(child));
        }
        return maxHeight + 1;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        BinaryTreeNode child = new BinaryTreeNode(2);
        BinaryTreeNode child1 = new BinaryTreeNode(3);
        BinaryTreeNode child2 = new BinaryTreeNode(5);
        BinaryTreeNode child3 = new BinaryTreeNode(4);

        root.children.add(child1);
        root.children.add(child2);
        root.children.add(child);

        child2.children.add(child3);

        System.out.println("The height of K-ary tree is " + findHeight(root));
    }


}
