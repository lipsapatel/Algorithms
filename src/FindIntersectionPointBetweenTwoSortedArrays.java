/**
 * Find the intersection point between two sorted arrays.
 *
 * int[] a = {1, 2, 3, 6, 8, 10};
 * int[] b = {4, 5, 6, 11, 15, 20};
 *
 * Output: Intersection point is 6
 *
 * Approach 1: Use two loops if you find the intersection point then return.
 *
 * Time Complexity: O(n2)
 *
 * Approach 2:
 *
 * Use index x for array a
 * Use index y for array b
 * x = 0 and y = 0
 * If both elements are same, then we have our intersection point, return it
 * else if a[x] < b[y], x++
 * else if a[x] > b[y], y++
 * If you are still not able to find the intersection point then return -1
 *
 * Relative size of two arrays is important
 *
 * Time complexity: O(n)
 */
public class FindIntersectionPointBetweenTwoSortedArrays {

    private static int findIntersectionPointInArray(int[] a, int[] b) {

        int x = 0;
        int y = 0;

        while(x < a.length && y < b.length) {

            if (a[x] < b[y]) {
                x++;
            } else if (a[x] > b[y]) {
                y++;
            } else {
                return a[x];
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] a = {1, 2, 3, 6, 8, 10};
        int[] b = {4, 5, 6, 11, 15, 20};

        System.out.println("The intersection point is " + findIntersectionPointInArray(a, b));

        int[] c = {1, 2};
        int[] d = {3, 4};

        System.out.println("The intersection point is " + findIntersectionPointInArray(c, d));
    }
}
