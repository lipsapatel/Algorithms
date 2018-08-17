/**
 * Linear Search Algorithm
 *
 * Given an array[] of n elements and a element 'x' write a program to search an element 'x' in the array
 *
 * Example
 *
 * Input[] = {20, 30, 40, 10, 5, 2, 60, 73}
 * int x = 10;
 * Output: Index 3
 *
 * Input[] = {20, 30, 40, 10, 5, 2, 60, 73}
 * int x = 60;
 * Output: Index 6
 *
 * Input[] = {20, 30, 40, 10, 5, 2, 60, 73}
 * int x = 9;
 * Output: Not found
 *
 * Approach:
 *
 * 1) Use a loop
 * 2) Start from left most element in array
 * 3) Check if element matches with x
 *    1) If yes, we have found the element, return it's index
 *    2) Else move to the next element in array and repeat step 3
 * 4) If all elements are scanned and none of the elements in array matches with x, means x is not present in array.
 *
 * Time Complexity: O(N)
 */
public class LinearSearch {

    private static void linearSearch(int[] array, int x) {

        for (int i = 0; i < array.length; i++) {

            if (x == array[i]) {
                System.out.println("Element " + x + " is found at index: " + i);
                return;
            }
        }

        //if here means x is not found
        System.out.println("Element " + x + " is not found in an array");
    }

    public static void main(String[] args) {

        int[] array = {20, 30, 40, 10, 5, 2, 60, 73};
        int x = 10;
        linearSearch(array, x);

        x = 60;
        linearSearch(array, x);

        x = 9;
        linearSearch(array, x);
    }
}
