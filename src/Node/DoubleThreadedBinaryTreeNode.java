package Node;

/**
 * leftBit = 0 Left reference points to inorder predecessor
 * leftBit = 1 Left reference points to left child
 *
 * rightBit = 0 Right reference points to inorder successor
 * rightBit = 1 Right reference points to right child
 */
public class DoubleThreadedBinaryTreeNode {

    public int data;
    public int leftBit;
    public int rightBit;

    public DoubleThreadedBinaryTreeNode right;
    public DoubleThreadedBinaryTreeNode left;

    public DoubleThreadedBinaryTreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
