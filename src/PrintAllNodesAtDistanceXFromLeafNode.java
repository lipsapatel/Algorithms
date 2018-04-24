import Node.BinaryTreeNode;

/**
 * Print all the nodes which are at X distance from leaf nodes.
 *
 * resources/PrintAllNodesWhichAreAtXDistanceFromLeafNode.png
 *
 * Approach:
 *
 * 1) We need to have array path, int pathLength, boolean array pathVisited
 * 2) One node can be at distance X from multiple leaf nodes, so to avoid printing redundant values
 * maintain boolean array pathVisited. Mark it true when the node is printed.
 * 3) Do the preorder traversal
 * 4) Print the node at distance pathLength - distance - 1 if it's not already printed.
 *
 * Time Complexity: O(n)
 */
public class PrintAllNodesAtDistanceXFromLeafNode {

    private static void printAllNodesAtDistanceXFromLeafNode(BinaryTreeNode root, int distance,
                                                             int[] path, boolean[] pathVisited,
                                                             int pathLength) {

        //Return if root is null
        if (root == null) {
            return;
        }

        //Pre-order traversal
        path[pathLength] = root.data;
        pathVisited[pathLength] = false;
        pathLength++;

        //Check if you have reached the leaf node
        if (root.left == null && root.right == null) {

            //To get actual pathLength you need to do -1 because pathLength has the count of number
            //of nodes.
            int positionOfNodeAtDistanceX = pathLength - distance - 1;

            //Check if it's not already visited, if not then print it and mark visited = true
            if (positionOfNodeAtDistanceX >= 0 && !pathVisited[positionOfNodeAtDistanceX]) {

                //print it
                System.out.print(path[positionOfNodeAtDistanceX] + ", ");

                //Mark it as true
                pathVisited[positionOfNodeAtDistanceX] = true;
            }
        } else {

            //Recursive call
            printAllNodesAtDistanceXFromLeafNode(root.left, distance, path, pathVisited, pathLength);
            printAllNodesAtDistanceXFromLeafNode(root.right, distance, path, pathVisited, pathLength);
        }
    }

    public static void main(String[] args) {

        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);

        root.left.left = new BinaryTreeNode(6);
        root.left.right = new BinaryTreeNode(8);

        root.left.left.left = new BinaryTreeNode(9);

        root.right.right = new BinaryTreeNode(5);
        root.right.right.right = new BinaryTreeNode(4);
        root.right.right.left = new BinaryTreeNode(7);

        int[] path = new int[100];
        boolean[] pathVisisted = new boolean[100];

        System.out.println("Nodes at Distance 2 from leaf node: ");
        printAllNodesAtDistanceXFromLeafNode(root, 2, path, pathVisisted, 0);

        System.out.println();

        System.out.println("Nodes at Distance 1 from leaf node: ");
        printAllNodesAtDistanceXFromLeafNode(root, 1, path, pathVisisted, 0);
    }
}
