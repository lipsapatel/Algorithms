package Node;

/**
 * Binary Tree has Root node, Left node and Right node
 */
public class BinaryTreeNode {

    public int data;
    public BinaryTreeNode left;
    public BinaryTreeNode right;
    public BinaryTreeNode next;

    //This is for single threaded binary tree
    public boolean rightThread;

    public BinaryTreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.next = null;

        //This is for single threaded binary tree
        rightThread = false;
    }
}
