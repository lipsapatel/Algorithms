import Node.BinaryTreeNode;

/**
 * Complete implementation of Single Threaded Binary Tree.
 *
 * resources/SingleThreadedBinaryTree.png
 *
 * Each node is threaded towards either the inorder predecessor or successor(left or right).
 * Means all right pointers will point to inorder successor.
 * All left pointers will point to inorder predecessor.
 *
 * Node Structure:
 *
 * resources/NodeStructureSingleThreadedBinaryTree.png
 *
 * Class Node {
 *     Node left;
 *     Node right;
 *     int data;
 *     boolean rightThread;
 *
 *     public Node(int data) {
 *         this.data = data;
 *         rightThread = false;
 *     }
 * }
 *
 * Two Operations in Single threaded Binary tree:
 *
 * 1) Insert node into tree
 * 2) Print or traverse the tree.
 *
 * Insert Operation:
 *
 * 1) The insert operation is similar to Binary search tree with few modifications.
 * 2) Take current = root and parent = null
 * 3) If current.data > data, go to the left. If current == null, the we found the place to insert new node.
 * 4) Add new node to the left of parent node and make right pointer points to the parent node and rightThread = true
 *
 * resources/insertSingleThreadedBinaryTree.png
 *
 * 5) If current.data < data, go to the right of the root.
 * 6) If rightThread = false and current == null, then insert new node and point it's right to the parent and rightThread = true.
 * 7) If rightThread = true, detach right pointer and store it in temp.
 * 8) Make right pointer point to new Node and make right reference point to temp stored reference.
 *
 * resources/insert1SingleThreadedBinaryTree.png
 *
 * Traverse Operation:
 *
 * 1) No need of recursion or stack for storing the node.
 * 2) Go to the left most node.
 * 3) And start traversing the tree using the right pointer.
 * 4) When rightThread = false, again go to the left most node in the right subtree.
 *
 * resources/TraverseSingleThreadedBinaryTree.png
 */
public class SingleThreadedBinaryTreeImplementation {

    private static void insertIntoSingleThreadedBinaryTree(BinaryTreeNode root, int data) {

        BinaryTreeNode newNode = new BinaryTreeNode(data);

        BinaryTreeNode current = root;
        BinaryTreeNode parent = null;

        while(true) {

            parent = current;

            if (data < current.data) {

                current = current.left;

                if (current == null) {
                    parent.left = newNode;
                    newNode.right = parent;
                    newNode.rightThread = true;
                    return;
                }
            } else {

                if (current.rightThread == false) {

                    current = current.right;

                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                } else {
                    BinaryTreeNode rightReference = current.right;
                    current.right = newNode;
                    current.rightThread = false;
                    newNode.right = rightReference;
                    newNode.rightThread = true;
                    return;
                }
            }
        }
    }

    private static void traverseSingleThreadedBinaryTree(BinaryTreeNode root) {

        //First go to the left most node
        BinaryTreeNode current = leftMostNode(root);

        while (current != null) {
            System.out.print(" " + current.data);

            //Check if the node has right thread
            if (current.rightThread) {
                current = current.right;
            } else {

                //go to the left most node in the right subtree
                current = leftMostNode(current.right);
            }
        }
        System.out.println();
    }

    private static BinaryTreeNode leftMostNode(BinaryTreeNode node) {

        if (node == null) {
            return null;
        } else {
            while(node.left != null) {
                node = node.left;
            }
            return node;
        }
    }

    public static void main(String[] args) {

        BinaryTreeNode root = new BinaryTreeNode(100);
        insertIntoSingleThreadedBinaryTree(root, 50);
        insertIntoSingleThreadedBinaryTree(root, 25);
        insertIntoSingleThreadedBinaryTree(root, 7);
        insertIntoSingleThreadedBinaryTree(root, 20);
        insertIntoSingleThreadedBinaryTree(root, 75);
        insertIntoSingleThreadedBinaryTree(root, 99);

        traverseSingleThreadedBinaryTree(root);
    }
}
