/**
 * 2D Array Search
 * You are given a sorted two-dimensional integer array arr of size r * c,
 * where all the numbers are in non-decreasing order from left to right and
 * top to bottom. I.e. arr[i][j] <= arr[i+1][j] and arr[i][j] <= arr[i][j+1]
 * for all i = 0,1,...,(r - 2) and j = 0,1,...,(c - 2).
 *
 * Check if a given number x exists in arr or not.  Given an arr,
 * you have to answer q such queries.
 *
 * Example One
 * Input: arr = [[1, 2, 3, 12], [4, 5, 6, 45], [7, 8, 9, 78]], queries = [6, 7, 23]
 * Output: [“present”, “present”, “not present”]
 *
 * Given number x=6 is present at arr[1][2] and x=7 is present at arr[2][0].
 * Hence, "present" returned for them, while
 *
 * x=23 is not present in arr, hence "not present" returned
 *
 * Example Two
 * Input: arr = [[3, 4], [5, 10]], queries = [12, 32]
 *
 * Output: [“not present”, “not present”]
 * Given number x=12 and x=32 are not present in arr. Hence, "not present"
 * returned for both of the queries.
 *
 * Constraints:
 *
 *     1 <= r <= 10^3
 *     1 <= c <= 10^3
 *     1 <= q <= 10^4
 *     -10^9 <= arr[i][j] <= 10^9, (i = 0,1,...,(r - 1) and j = 0,1,...,(c - 1))
 *     -10^9 <= x <= 10^9
 *
 * Brute Force Approach
 *
 * 1) Iterate the entire array
 *
 * Time Complexity: O(r * c * q) where r = rows, c = cols, and q = queries
 * Space Complexity: O(1)
 *
 * Optimal Approach
 *
 * 1) Start at cell (0, c - 1)
 * 2) Compare a[i][j] with x
 *    i) If x == a[i][j], then return "present"
 *    ii) If x < a[i][j], then go to prev col
 *               a[i][j - 1]
 *    iii) If x > a[i][j], then go to next row
 *                a[i + 1][j]
 *
 * Time Complexity: O((r + c) * q)
 * Space Complexity: O(1)
 *
 * resources/TwoDArraySearch1.jpg
 * resources/TwoDArraySearch2.jpg
 *
 */
public class TwoDArraySearch {

    private static String isPresent(int[][] a, int x) {
        int r = a.length;
        int c = a[0].length;

        int i = 0;
        int j = c - 1;

        while(i < r && j >= 0) {

            if(a[i][j] == x) {
                return "present";
            } else if(x > a[i][j]) {
                i++;
            } else if(x < a[i][j]) {
                j--;
            }
        }
        return "not present";
    }

    public static void main(String[] args) {
        int[][] a = {{1, 2, 3, 12}, {4, 5, 6, 45},{7, 8, 9, 78}};

        System.out.println(isPresent(a, 6));
        System.out.println(isPresent(a, 45));
        System.out.println(isPresent(a, 7));
        System.out.println(isPresent(a, 77));
    }
}
