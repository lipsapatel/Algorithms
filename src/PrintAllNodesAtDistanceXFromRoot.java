import Node.BinaryTreeNode;

/**
 * Print all the nodes which are at distance X from root
 *
 * /resources/PrintAllNodesAtDistanceXFromRoot.png
 *
 * Approach:
 *
 * 1) Maintain variable x
 * 2) Do preorder traversal and decrease x
 * 3) If x becomes 0, means you have reached node at distance x from root then print root.data
 *
 * Time Complexity: O(n)
 */
public class PrintAllNodesAtDistanceXFromRoot {

    private static void printAllNodesAtDistanceXFromRoot(BinaryTreeNode root, int x) {

        if (root != null) {

            if (x == 0) {
                System.out.print(root.data + " ,");
            }

            printAllNodesAtDistanceXFromRoot(root.left, --x);
            //do x-- because if x = 1, then you still need to go right because --x will make it zero
            //and print it.
            printAllNodesAtDistanceXFromRoot(root.right, x--);
        }
    }

    public static void main(String[] args) {

        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);

        root.left.left = new BinaryTreeNode(4);
        root. left.right = new BinaryTreeNode(5);

        root.left.right.left = new BinaryTreeNode(6);
        root.left.right.right = new BinaryTreeNode(7);

        root.right.left = new BinaryTreeNode(9);
        root.right.right = new BinaryTreeNode(8);

        root.right.right.right = new BinaryTreeNode(10);

        System.out.println("Nodes at distance 3");
        printAllNodesAtDistanceXFromRoot(root, 3);
        System.out.println();

        System.out.println("Nodes at distance 2");
        printAllNodesAtDistanceXFromRoot(root, 2);
        System.out.println();
    }
}
