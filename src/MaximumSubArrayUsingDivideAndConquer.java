/**
 * Maximum Subarray or Largest Sum Contiguous Subarray problem - Divide and Conquer
 *
 * The maximum subarray problem is the task of finding the contiguous subarray within a one dimensional
 * array of numbers which has the largest sum.
 *
 * Example:
 *
 * int[] a = {-2, 1, -3, 4, -1, 2, 1, -5, 4}
 *
 * mid = 4
 *
 * -1+4 = 3 ..-3 for -2+1-3
 * 3 for 2, 1
 * -1 for 4
 *
 * so 3 + 3 = 6
 * max (3, 3, 6)
 *
 * int[] a = {-2, 1, -3, 4, -1, 2, 1, -5, 4}
 * Output: Contiguous subarray with the largest sum is 4, -1, 2, 1 with sum 6
 *
 * Approach:
 *
 * 1) Find out the 2 indexes (left index and right index) between which the sum is maximum.
 * 2) Divide the problem into 2 parts, left half and right half
 * 3) The final result will be in one if the three possibilities
 *    1) Left half of the array ( Left index and right index both are in left half of the array)
 *    2) Right half of the array (Left index and right index both are in right half of the array)
 *    3) Result will cross the middle of the array (Left index in the left half of the array and right index in the right
 *       half of the array)
 *    4) Solve all the three and return maximum among them
 *    5) Left half and right half of the array will be solved recursively
 */
public class MaximumSubArrayUsingDivideAndConquer {

    private static int maxSubArrayDivideAndConquer(int[] a) {

        if (a.length == 0) { //If length = 0, return 0
            return 0;
        } else {
            return maxSubArrayDivideAndConquer(a, 0, a.length - 1);
        }
    }

    private static int maxSubArrayDivideAndConquer(int[] a, int start, int end) {

        if (start == end) {
            //If only one element, return that
            return a[start];
        }

        int mid = start + (end - start)/2;
        int leftMaxSum = maxSubArrayDivideAndConquer(a, start, mid);
        int rightMaxSum = maxSubArrayDivideAndConquer(a, mid + 1, end);

        //Lets calculate the part in which subarray will start in left half and ends in right half
        int sum = 0;
        int leftMidMax = 0;
        for (int i = mid; i >= start; i--) {
            sum += a[i];
            if (sum > leftMidMax) {
                leftMidMax = sum;
            }
        }

        sum = 0;
        int rightMidMax = 0;
        for (int i = mid + 1; i <= end; i++) {
            sum += a[i];
            if (sum > rightMidMax) {
                rightMidMax = sum;
            }
        }

        int centerSum = leftMidMax + rightMidMax;
        return Math.max(centerSum, Math.max(leftMaxSum, rightMaxSum));
    }

    public static void main(String[] args) {
        int[] a = {-2, 12, -5, 10, -1, -6, 4};
        System.out.println("Maximum Subarray sum is: " + maxSubArrayDivideAndConquer(a));
    }
}
