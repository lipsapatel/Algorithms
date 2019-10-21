import java.util.Arrays;
import java.util.Comparator;

/**
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

 Note:
 The number of people is less than 1,100.


 Example

 Input:
 [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

 Output:
 [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

 resources/QueueReconstructionByHeight.jpg

 Approach

 Smaller people in front
 1) Sort by ascending order of height
 2) Descending order of no of people
 3) Construct segment tree with all position available (all 1)
 4) Go to left if there are available empty slots
 5) Else go to right

 Time complexity: O(nlogn) to sort + n * logn(segment tree find the right position) + n(to construct segment tree)
 TC: O(nlogn)

 Space Complexity: O(n) for segment tree and result

 */
public class QueueReconstructionByHeight {

    private static int[] segmentTree;

    private static int[][] reconstructQueue(int[][] people) {

        //Sort in ascending order of height and descending order of no of shorter people
        Arrays.sort(people, new Comparator<int[]>() {

            @Override
            public int compare(int[] a, int[] b) {

                if (a[0] != b[0]) {
                    return a[0] - b[0];
                } else {
                    return b[1] - a[1];
                }
            }
        });

        int[][] result = new int[people.length][];

        if (people.length == 0) {
            return result;
        }

        //All position are available
        int[] input = new int[people.length];
        Arrays.fill(input, 1);

        constructSegmentTree(input);

        for (int i = 0; i < people.length; i++) {
            updateSegmentTree(people[i][1] + 1, result, 0, people.length - 1, 0, people[i]);
        }
        return result;
    }

    private static void constructSegmentTree(int[] input) {

        int n = input.length;

        //The next power of 2
        int x = (int) Math.ceil(Math.log(n)/Math.log(2));

        //Size of segment tree = 2n - 1
        int size = (int) (2 * Math.pow(2, x) - 1);

        segmentTree = new int[size];

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

        segmentTree[pos] = segmentTree[2 * pos + 1] + segmentTree[2 * pos + 2];
    }

    private static void updateSegmentTree(int k, int[][] result, int low, int high, int pos, int[] people) {

        if (low == high) { //leaf - set node as not available and add to the result
            segmentTree[pos] = 0; //not available
            result[low] = people; //add to result
            return;
        }

        int mid = low + (high - low)/2;

        //If slots available to left go to left or else go to right
        if (k <= segmentTree[2 * pos + 1]) {
            updateSegmentTree(k, result, low, mid, 2 * pos + 1, people);
        } else { //right
            updateSegmentTree(k - segmentTree[2 * pos + 1], result, mid + 1, high, 2 * pos + 2, people);
        }

        segmentTree[pos]--;
    }

    public static void main(String[] args) {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};

        System.out.println("The reconstructed queue by height: ");

        int[][] result = reconstructQueue(people);

        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
    }
}
