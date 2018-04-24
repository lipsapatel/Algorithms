/**
 * Check if Array contains all elements of some given range.
 *
 * Given an array of unsorted numbers, check if it contains all elements of some given range.
 *
 * Examples:
 *
 * int[] array = {11, 17, 13, 19, 15, 16, 12, 14}
 * Range: 12-15
 * Output: true
 *
 * Approach:
 *
 * 1) Naive Approach
 * Sort the array and find all the elements in range
 * Time Complexity: O(nlogn)
 *
 * Better Approach:
 *
 * 1) Find the range = y - x;
 * 2) Do linear scan of array.
 * 3) Check if elements falls within the range of x and y
 * array[i] >= x && array[i] <= y
 * 4) If in range, z = array[i] - x;
 * 5) array[z] = *-1 (Negate it)
 * 6) Check if all the elements from 0 to range are negative. If yes then array contains all the
 * numbers of the given range
 * 7) return true
 * 8) else return false
 *
 * Time Complexity: O(n)
 *
 * resources/CheckIfArrayContainsAllElementsInRange.png
 */
public class CheckIfArrayContainsAllElementsInRange {

    private static boolean checkIfArrayContainsAllElementsInRange(int[] array, int x, int y) {

        int range = y - x;

        //linear scan
        for (int i = 0; i < array.length; i++) {

            //If falls within range
            if (array[i] >= x && array[i] <= y) {
                int z = array[i] - x;

                if (array[z] > 0) {
                    array[z] = array[z] * -1;
                }
            }
        }

        //Scan the array and find out if elements from 0 to range are negative
        for (int i = 0; i < range; i++) {
            if (array[i] > 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] array = {11, 17, 13, 19, 15, 16, 12, 14};
        int x = 12;
        int y = 15;

        System.out.println(checkIfArrayContainsAllElementsInRange(array, x, y));
    }
}
