/**
 * Given three sorted (in ascending order) arrays of integers, find out all the common elements
 * in them.
 *
 * Input: 3 Sorted arrays
 * Output: Common elements
 *
 * Time complexity: O(n)
 *
 * int[] a = {1, 2, 3, 4, 5, 6, 7 8, 9, 10};
 * int[] b = {1, 3, 5, 6, 7, 8, 12};
 * int[] c = {2, 3, 4, 5, 8, 9};
 *
 * Common Elements are 3, 5, 8
 *
 * 1) Navigate array a, b, c simultaneously using i, j, k index.
 * 2) if (a[i] == b[j] == c[k]) then print a[i], i++, j++, k++
 * 3) If not then compare all three of them, whichever is smaller increase it's index.
 * 4) Stop when any of these array gets over.
 */
public class FindCommonNumbersInThreeSortedArrays {

    private static void findCommonNumbersInThreeSortedArrays(int[] a, int[] b, int[] c) {

        int i = 0, j = 0, k = 0;

        while(i < a.length && j < b.length && k < c.length) {

            if ((a[i] == b[j]) && (b[j] == c[k])) {
                System.out.print(a[i] + " ");
                i++;
                j++;
                k++;
            } else if (a[i] <= b[j] && a[i] <= c[k]) {
                i++;
            } else if (b[j] <= a[i] && b[j] <= c[k]) {
                j++;
            } else {
                k++;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] b = {1, 3, 5, 6, 7, 8, 12};
        int[] c = {2, 3, 4, 5, 8, 9};

        System.out.println("The common numbers in three sorted arrays are ");
        findCommonNumbersInThreeSortedArrays(a, b, c);
    }
}
