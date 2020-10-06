import java.util.ArrayList;
import java.util.List;

/**
 * Minimum Element In A Sorted And Rotated Array
 *
 * Find the minimum element in an array that has been sorted and rotated
 * by an unknown pivot.
 *
 * Example
 * Input: [ 4, 5, 6, 7, 8, 1, 2, 3]
 * Output: 1
 *
 * The array is sorted in the ascending order and right rotated by pivot 5.
 * The minimum value 1 is at index 5.
 *
 * Constraints:
 *
 *     1 <= number of array elements <= 10^5
 *     -10^9 <= any array element <= 10^9
 *     Array elements are unique.
 *
 * Brute Force Approach
 *
 * 1) Iterate over array and maintain minimum value.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Optimal Solution
 *
 * 1) First find whether array is sorted in ascending order or descending order.
 * 2) If array is rotated at 0 then whole array will either be ascending or descending.
 *
 *    Compare (0, n - 1), (0, 1), (n - 2, n - 1)
 *    This requires array with atleast 4 elements.
 * 3) For ascending sorted and rotated at point other than 0
 *    a[0] > a[n - 1]
 * 4) Else it's descending sorted order.
 *
 * Ascending sorted order binary search
 *
 * 1) If we find subarray where high >= low, then that subarray is sorted in ascending
 * order. In that case low will be the minimum element.
 * 2) Do binary search
 *    if mid >= low, search right (mid + 1, high)
 *    else
 *    search left (low, mid)
 *
 *  Catch here is to include mid
 *
 *  Descending sorted order binary search
 *
 *  1) If we find subarray where low >= high, then that subarray is sorted in
 *  descending order. In that case high will be the minimum element.
 *  2) If mid <= low, search right (mid, high) mid could be lowest element
 *  so include mid in both search
 *  3) else that part is not sorted
 *  search left (low, mid)
 *
 *  Time Complexity: O(logn)
 *  Space Complexity: O(1) for iterative and O(logn) for recursive binary search.
 *
 *  resources/MinimumElementInSortedRotatedArray1.jpg
 *  resources/MinimumElementInSortedRotatedArray2.jpg
 *  resources/MinimumElementInSortedRotatedArray3.jpg
 *  resources/MinimumElementInSortedRotatedArray4.jpg
 *  resources/MinimumElementInSortedRotatedArray5.jpg
 *  resources/MinimumElementInSortedRotatedArray6.jpg
 *  resources/MinimumElementInSortedRotatedArray7.jpg
 *  resources/MinimumElementInSortedRotatedArray8.jpg
 */
public class MinimumElementInSortedAndRotatedArray {

    private static int findMinimum(List<Integer> a) {
        int minimum = Integer.MAX_VALUE;

        for(int n: a) {
            minimum = Math.min(minimum, n);
        }
        return minimum;
    }

    private static int findMinimumBinarySearch(List<Integer> a) {
        int n = a.size();

        if(n == 1) {
            return a.get(0);
        }

        if(n == 2) {
            return Math.min(a.get(0), a.get(1));
        }

        if(n == 3) {
            return Math.min(a.get(0), Math.min(a.get(1), a.get(2)));
        }

        //If a = {4, 7, 8, 10, 15}
        //Rotated right at 0
        //Ascending order
        if(a.get(0) < a.get(n - 1) &&
           a.get(0) < a.get(1) &&
           a.get(n - 2) < a.get(n - 1)) {
            return a.get(0);
        }

        //If a = {15, 10, 8, 7, 4}
        //Rotated right at 0
        //Descending order
        if(a.get(0) > a.get(n - 1) &&
           a.get(0) > a.get(1) &&
           a.get(n - 2) > a.get(n - 1)) {
            return a.get(n - 1);
        }

        //Ascending order and right rotated by any other point
        if(a.get(0) > a.get(n - 1)) {
            return findMinimumIncreasing(a);
        } else {
            return findMinimumDecreasing(a);
        }
    }

    public static int findMinimumIncreasing(List<Integer> a) {
        int low = 0;
        int high = a.size() - 1;

        while(low <= high) {
            if(a.get(high) >= a.get(low)) {
                return a.get(low);
            }

            int mid = low + (high - low)/2;

            if(a.get(mid) >= a.get(low)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return -1;
    }

    public static int findMinimumDecreasing(List<Integer> a) {
        int low = 0;
        int high = a.size() - 1;

        while(low <= high) {

            if(a.get(high) <= a.get(low)) {
                return a.get(high);
            }

            int mid = low + (high - low)/2;

            if(a.get(mid) < a.get(low)) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(4);
        a.add(5);
        a.add(6);
        a.add(7);
        a.add(8);
        a.add(1);
        a.add(2);
        a.add(3);

        System.out.println("Minimum number " + findMinimum(a));
        System.out.println("Minimum number " + findMinimumBinarySearch(a));

        List<Integer> a1 = new ArrayList<>();
        a1.add(5);
        a1.add(4);
        a1.add(3);
        a1.add(2);
        a1.add(1);
        a1.add(11);
        a1.add(10);
        a1.add(9);
        a1.add(8);
        a1.add(7);
        a1.add(6);

        System.out.println("Minimum number " + findMinimumBinarySearch(a1));
    }
}
