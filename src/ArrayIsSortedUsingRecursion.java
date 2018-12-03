/**
 * Check if array is sorted using recursion
 * Given an array of integer write a recursive solution to check if array is sorted.
 *
 * int [] a = {1,2,3,4};
 Output: true

 int [] a = {1,2,3,4,2};
 Output: false

 Approach:
 This problem can easily be solved in single iteration by just comparing adjacent elements.
 Fun part is to write recursive solution.
 Code is self explanatory

 */
public class ArrayIsSortedUsingRecursion {

    private static boolean checkIfArrayIsSorted(int[] a, int start) {

        if (start == a.length - 1) {
            return true;
        }

        if (a[start] <= a[start + 1]) {
            return checkIfArrayIsSorted(a, start + 1);
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 8, 8, 22, 50};
        System.out.println(checkIfArrayIsSorted(a, 0));
    }
}
