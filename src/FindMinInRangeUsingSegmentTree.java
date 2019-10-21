import java.util.Arrays;

/**
 * Segment Trees are used for range-based query.
 * Find minimum in range, maximum in range, sum in range
 *
 * Time Complexity: O(n) - To build segment tree
 * Space Complexity: O(n) space for segment tree
 *
 * Query: O(logn)
 *
 * When you query segment tree, there could be 3 types of overlap
 *
 * 1) Total overlap, return
 * 2) No overlap, return max
 * 3) Partial overlap - go to left and right
 *
 * If given array size some power of 2, the size of segment tree = 2n - 1
 * If given array size is not power of 2, consider next power of 2
 *
 * Left child is at 2i + 1
 * Right child is at 2i + 2
 * Parent is at (i - 1)/2
 *
 * resources/FindMinInRangeUsingSegmentTree.jpg
 * resources/Increment[0,3]By3.jpg
 * resources/Increment[0,3]By1.jpg
 * resources/Increment[0,0]By2.jpg
 * resources/RangeMinQuery[3,5].jpg
 *
 */
public class FindMinInRangeUsingSegmentTree {

    private static int[] segmentTree;
    private static int[] lazyTree;

    private static void constructSegmentTree(int[] input) {

        int n = input.length;

        //Find next power of 2
        int x = (int)Math.ceil(Math.log(n)/Math.log(2));

        int size = (int) (2 * Math.pow(2, x) - 1);

        segmentTree = new int[size];
        lazyTree = new int[size];

        Arrays.fill(lazyTree, 0);

        constructSegmentTreeHelper(input, segmentTree, 0, n - 1, 0);
    }

    private static void constructSegmentTreeHelper(int[] input, int[] segmentTree, int low, int high, int pos) {

        if (low == high) { //leaf
            segmentTree[pos] = input[low];
            return;
        }

        int mid = low + (high - low)/2;

        //Left
        constructSegmentTreeHelper(input, segmentTree, low, mid, 2 * pos + 1);

        //Right
        constructSegmentTreeHelper(input, segmentTree, mid + 1, high, 2 * pos + 2);

        segmentTree[pos] = Math.min(segmentTree[2 * pos + 1], segmentTree[2 * pos + 2]);
    }

    private static int rangeMinQuery(int[] segmentTree, int qlow, int qhigh, int low, int high, int pos) {

        //Total overlap
        if (low >= qlow && high <= qhigh) {
            return segmentTree[pos];
        }

        //No overlap
        if (qlow > high || qhigh < low) {
            return Integer.MAX_VALUE;
        }

        //Partial overlap
        int mid = low + (high - low)/2;

        return Math.min(rangeMinQuery(segmentTree, qlow, qhigh, low, mid, 2 * pos + 1),
                        rangeMinQuery(segmentTree, qlow, qhigh, mid + 1, high, 2 * pos + 2));
    }

    private static void updateSegmentTreeRangeUsingLazyPropagation(int[] segmentTree, int[] lazyTree, int startRange, int endRange, int delta, int low, int high, int pos) {

        if (low > high) {
            return;
        }

        //Lazy propagation at pos
        if (lazyTree[pos] != 0) {
            segmentTree[pos] += lazyTree[pos];

            if (low != high) { //Not leaf node
                lazyTree[2 * pos + 1] += lazyTree[pos];
                lazyTree[2 * pos + 2] += lazyTree[pos];
            }
            lazyTree[pos] = 0;
        }

        //No overlap
        if (startRange > high || endRange < low) {
            return;
        }

        //Total overlap
        if (low >= startRange && high <= endRange) {
            segmentTree[pos] += delta;

            if (low != high) { //Not leaf nodes
                lazyTree[2 * pos + 1] += delta;
                lazyTree[2 * pos + 2] += delta;
            }
            return;
        }

        //Partial overlap so look both left and right
        int mid = low + (high - low)/2;

        //Left
        updateSegmentTreeRangeUsingLazyPropagation(segmentTree, lazyTree, startRange, endRange, delta, low, mid, 2 * pos + 1);

        //Right
        updateSegmentTreeRangeUsingLazyPropagation(segmentTree, lazyTree, startRange, endRange, delta, mid + 1, high, 2 * pos + 2);

        segmentTree[pos] = Math.min(segmentTree[2 * pos + 1], segmentTree[2 * pos + 2]);
    }

    private static int rangeMinimumQueryLazyPropagation(int[] segmentTree, int[] lazyTree, int qlow, int qhigh, int low, int high, int pos) {

        if (low > high) {
            return Integer.MAX_VALUE;
        }

        //Lazy propagation at pos
        if (lazyTree[pos] != 0) {

            segmentTree[pos] += lazyTree[pos];

            if (low != high) { //Not leaf node
                lazyTree[2 * pos + 1] += lazyTree[pos];
                lazyTree[2 * pos + 2] += lazyTree[pos];
            }

            lazyTree[pos] = 0;
        }

        //No overlap
        if (qlow > high || qhigh < low) {
            return Integer.MAX_VALUE;
        }

        //Total Overlap
        if (low >= qlow && high <= qhigh) {
            return segmentTree[pos];
        }

        //Partial Overlap
        int mid = low + (high - low)/2;

        return Math.min(rangeMinimumQueryLazyPropagation(segmentTree, lazyTree, qlow, qhigh, low, mid, 2 * pos + 1),
                        rangeMinimumQueryLazyPropagation(segmentTree, lazyTree, qlow, qhigh, mid + 1, high, 2 * pos + 2));
    }

    public static void main(String[] args) {
        int[] input = {-1, 2, 4, 0};

        constructSegmentTree(input);

        System.out.println("The segment tree is: " + Arrays.toString(segmentTree));

        System.out.println("The min in range 1 to 3: " + rangeMinQuery(segmentTree, 1, 3, 0, 3, 0));

        int[] input1 = {-1, 2, 4, 1, 7, 1, 3, 2};

        constructSegmentTree(input1);

        System.out.println("The segment tree is: " + Arrays.toString(segmentTree));

        updateSegmentTreeRangeUsingLazyPropagation(segmentTree, lazyTree, 0, 3, 3, 0, 7, 0);

        System.out.println("The segment tree after incrementing [0, 3] by 3: " + Arrays.toString(segmentTree));

        updateSegmentTreeRangeUsingLazyPropagation(segmentTree, lazyTree, 0, 3, 1, 0, 7, 0);

        System.out.println("The segment tree after incrementing [0, 3] by 1: " + Arrays.toString(segmentTree));

        updateSegmentTreeRangeUsingLazyPropagation(segmentTree, lazyTree, 0, 0, 2, 0, 7, 0);

        System.out.println("The segment tree after incrementing [0, 0] by 2: " + Arrays.toString(segmentTree));

        System.out.println("The minimum in range [3, 5] is: " + rangeMinimumQueryLazyPropagation(segmentTree, lazyTree, 3, 5, 0, 7, 0));

    }
}
