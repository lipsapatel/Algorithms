package Node;

/**
 * Created by lpatel on 10/2/2019.
 */
public class AVLNode {

    public int data;
    public int height;
    public AVLNode left;
    public AVLNode right;

    public AVLNode(int data) {
        this.data = data;
        this.height = 1;
        this.left = null;
        this.right = null;
    }
}
