package Node;

public class BinaryTreeNodeWithParent {

    public BinaryTreeNodeWithParent left;
    public BinaryTreeNodeWithParent right;
    public BinaryTreeNodeWithParent parent;
    public int data;

    public BinaryTreeNodeWithParent(int data) {
        this.left = null;
        this.right = null;
        this.parent = null;
        this.data = data;
    }
}
