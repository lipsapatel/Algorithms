import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * Brute Force Approach
 *
 * 1) Start with first interval and compare with all other intervals for overlapping.
 * 2) If it overlaps with any other interval, then remove the other interval from the list
 * and merge the other into the first interval.
 * 3) Repeat the same steps for remaining intervals
 *
 * Time Complexity: O(n^2)
 *
 * Efficient Approach - Sort
 *
 * 1) Sort the intervals according to their start time
 * 2) We can combine all intervals in linear traversal.
 *
 * 1) Sort the intervals based on increasing order of starting time.
 * 2) Push the first interval on to a stack.
 * 3) For each interval do the following:
 *    a) If current interval does not overlap with stack top, push it.
 *    b) If the current interval overlaps with stack top, and ending time of
 *       current interval is more than that of stack top, update the stack top
 *       with ending time of current interval.
 * 4) At the end stack contains merged intervals.
 *
 * Time Complexity: O(nlogn + n) = O(nlogn) where nlogn is for sort and n for merge.
 * Space Complexity: O(n)
 *
 * You can also use queue/Linked list
 *
 * resources/MergeIntervals1.jpg
 * resources/MergeIntervals2.jpg
 */
public class MergeIntervals {

    public static int[][] mergeIntervals(int[][] intervals) {
        //Sort intervals by start time
        /*ArIrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });*/

        //Using lambda
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);

        LinkedList<int[]> mergedIntervals = new LinkedList<>();

        for(int[] interval: intervals) {

            //If mergeInterval is empty or interval does not overlap with last,
            //then add
            if(mergedIntervals.isEmpty() || mergedIntervals.getLast()[1] < interval[0]) {
                mergedIntervals.add(interval);
            } else {
                //Overlap and update end time
                mergedIntervals.getLast()[1] = Math.max(mergedIntervals.getLast()[1],
                                                        interval[1]);
            }
        }
        Arrays.copyOfRange(intervals, 0, 1);

        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{2, 6}, {8, 10}, {15, 18}, {1, 3}};

        int[][] mergedIntervals = mergeIntervals(intervals);

        for(int[] interval: mergedIntervals) {
            System.out.println(Arrays.toString(interval));
        }
    }
}
