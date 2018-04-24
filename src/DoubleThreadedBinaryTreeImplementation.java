import Node.DoubleThreadedBinaryTreeNode;

/**
 * resources/DoubleThreadedBinaryTree.png
 *
 * Each node is threaded towards inorder predecessor and successor.
 * All left null pointers will point to inorder predecessor.
 * All right null pointers will point to inorder successor.
 *
 * The node structure has leftBit and rightBit.
 *
 * leftBit = 0 means left reference is pointing to inorder predecessor
 * leftBit = 1 means left reference is pointing to left child.
 *
 * rightBit = 0 means right reference is pointing to inorder successor
 * rightBit = 1 means right reference is pointing to right child.
 *
 * We need a dummy node.
 *
 * resources/NeedOfDummyNode.png
 *
 * As we saw, the left most reference and right most reference has no where to point to.
 * So we need dummy node.
 * This node will always been present even when tree is empty.
 *
 * In this dummy node we will put rightBit = 1 and it's right child will point to itself.
 * leftBit = 0, so we will construct threaded tree as the left child of dummy node.
 *
 * resources/DummyNode.png
 *
 * resources/DoubleThreadedBinaryTreeWithDummyNode.png
 *
 * Insert() Operation:
 *
 * 1) Similar to Binary Search Tree.
 * 2) First check if tree is empty, means tree has just dummy node then insert the new node
 * into the left subtree of the dummy node
 * 3) If node is smaller than current node, then check if leftBit = 0, if yes we have found place to insert the node.
 * It will be in the left of the subtree.
 * If leftBit = 1 then go left.
 * 4) If new node is greater than current node, then check if rightBit = 0, if yes we have found place to insert the node.
 * It will be in the right subtree.
 * If rightBit = 1, then go right.
 * 5) Repeat steps 3 and 4 till the place to be inserted is not found.
 * 6) Node to be inserted as left child:
 *
 * n.left = current.left;
 * current.left = n;
 * n.leftBit = current.leftBit
 * current.leftBit = 1;
 * n.right = current;
 *
 * resources/DoubleThreadedBinaryTree_InsertNodeAsLeftChild.png
 *
 * 7) Node to be inserted as right child
 *
 * n.right = current.right;
 * current.right = n;
 * n.rightBit = current.rightBit;
 * current.rightBit = 1;
 * n.left = current;
 *
 * resources/DoubleThreadedBinaryTree_InsertNodeAsRightChild.png
 *
 * Traverse() Operation:
 *
 * 1) We do not need a recursion to do that which means it won't require stack,
 * it will be done in single traversal in O(n)
 * 2) Starting from the left most node in the tree, keep traversing the inorder successor
 * and print it.
 *
 * resources/TraverseDoubleThreadedBinaryTree.png
 */
public class DoubleThreadedBinaryTreeImplementation {

    private static DoubleThreadedBinaryTreeNode root = new
            DoubleThreadedBinaryTreeNode(Integer.MAX_VALUE);

    private static boolean directionLeft;
    private static boolean directionRight;

    private static void insertIntoDoubleThreadedBinaryTree(int data) {

        //Create new node
        DoubleThreadedBinaryTreeNode newNode = new DoubleThreadedBinaryTreeNode(data);

        //Check if it's going to be the first node in the tree
        if (root == root.left && root == root.right) {

            newNode.left = root;
            root.left = newNode;
            newNode.leftBit = root.leftBit;
            root.leftBit = 1;

            newNode.right = root;
        } else {

            DoubleThreadedBinaryTreeNode current = root.left;

            while(true) {
                if (current.data > newNode.data) {

                    //Left
                    if (current.leftBit == 0) {
                        //node will be added as left child
                        directionLeft = true;
                        directionRight = false;
                        break;
                    } else {
                        current = current.left;
                    }
                } else {

                    //Right
                    if (current.rightBit == 0) {
                        //Node will be added as right child
                        directionRight = true;
                        directionLeft = false;
                        break;
                    } else {
                        current = current.right;
                    }
                }
            }

            if (directionLeft) {

                //Add newNode as left child
                newNode.left = current.left;
                current.left = newNode;
                newNode.leftBit = current.leftBit;
                current.leftBit = 1;

                newNode.right = current;
            } else if (directionRight) {

                //Add newNode as right child
                newNode.right = current.right;
                current.right = newNode;
                newNode.rightBit = current.rightBit;
                current.rightBit = 1;
                newNode.left = current;
            }
        }
    }


    private static void traverseDoubleThreadedBinaryTree() {

        //Start from left child of dummy node
        DoubleThreadedBinaryTreeNode current = root.left;

        //Go to the left most node
        while (current.leftBit == 1) {
            current = current.left;
        }

        //Now keep traversing inorder successor
        while(current != root) {
            System.out.print(" " + current.data);
            current = findNextInorderSuccesssor(current);
        }
    }

    private static DoubleThreadedBinaryTreeNode findNextInorderSuccesssor(DoubleThreadedBinaryTreeNode current) {

        //If rightBit = 0 so there is no right child
        //Use right pointer to move to inorder successor
        if (current.rightBit == 0) {
            return  current.right;
        }

        //If rightBit = 1 means current node has right child
        //Go to the left most node of the right subtree
        current = current.right;
        while(current.leftBit != 0) {
            current = current.left;
        }
        return current;
    }

    public static void main(String[] args) {

        //Dummy node
        root.leftBit = 0;
        root.rightBit = 1;

        root.left = root.right = root;

        insertIntoDoubleThreadedBinaryTree(10);
        insertIntoDoubleThreadedBinaryTree(12);
        insertIntoDoubleThreadedBinaryTree(15);
        insertIntoDoubleThreadedBinaryTree(2);
        insertIntoDoubleThreadedBinaryTree(13);
        insertIntoDoubleThreadedBinaryTree(1);
        insertIntoDoubleThreadedBinaryTree(4);

        System.out.println();
        traverseDoubleThreadedBinaryTree();
        System.out.println();
    }
}
