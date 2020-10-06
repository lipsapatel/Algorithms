/**
 * Given a list of intervals and given a stream of numbers, identify how many
 * intervals each number is in.
 * End interval is inclusive
 *
 * For Example: (1, 5), (12, 15), (2, 4)
 *
 * 1 --> 1 interval
 * 2 --> 2 interval
 * 3 --> 2 interval
 * 17 --> 0 interval
 * 13 --> 1 interval
 *
 * Brute Force Approach
 * 1) Check the stream of number in each interval.
 * If there are n intervals and m numbers
 *
 * Time Complexity: O(mn)
 *
 * Optimal Approach
 *
 * 1) Create frequency array.
 * 2) Do +1 for start time and -1 for end time.
 * 3) If end time is inclusive do -1 for (endTime + 1)
 * 4) Traverse frequency array from min to max and
 * do frequency[i] = frequency[i] + frequency[i - 1]
 * 5) The size of frequency array will be whatever the max interval is.
 *
 * Time to construct frequency array = O(n + m) where m = max interval
 * Space Complexity: O(m) where m is the max interval
 *
 * To find if how many interval value v occurs, simply return frequency[v] if v is
 * less than or equal to max. Else return 0.
 *
 * resources/CountNumberOfIntervalsValueLies.png
 */
public class CountIntervalsInWhichValueLies {

    private static int countIntervals(int[][] intervals, int val) {

        //Find the max and min - O(n)
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        //O(n)
        for(int[] interval: intervals) {
            if(interval[0] < min) {
                min = interval[0];
            }
            if(interval[1] > max) {
                max = interval[1];
            }
        }

        int[] frequency = new int[max + 2];

        //Scan the intervals; +1 for start and -1 for end + 1
        //O(n)
        for(int[] interval: intervals) {
            int start = interval[0];
            int end = interval[1];

            frequency[start] = frequency[start] + 1;
            frequency[end + 1] = frequency[end + 1] - 1;
        }

        //Construct the frequency array for all values between min to max
        //O(m) where m = max
        for(int i = 1; i <= max; i++) {
            frequency[i] = frequency[i] + frequency[i - 1];
        }
        if(val > max) {
            return 0;
        } else {
            return frequency[val];
        }
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 10}, {5, 10}, {15, 25}, {7, 12}, {20, 25}};

        System.out.println("Value 7 occurs in " + countIntervals(intervals, 7) + " intervals");
        System.out.println("Value 0 occurs in " + countIntervals(intervals, 0) + " intervals");
        System.out.println("Value 26 occurs in " + countIntervals(intervals, 26) + " intervals");
    }
}
