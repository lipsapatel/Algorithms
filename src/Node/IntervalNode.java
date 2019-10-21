package Node;

/**
 * Interval Node for Interval Tree
 */
public class IntervalNode {

    public IntervalNode left;
    public IntervalNode right;
    public int low;
    public int high;
    public int max;

    public IntervalNode(int lo, int hi) {
        this.low = lo;
        this.high = hi;
        this.max = hi;
        this.left = null;
        this.right = null;
    }
}
