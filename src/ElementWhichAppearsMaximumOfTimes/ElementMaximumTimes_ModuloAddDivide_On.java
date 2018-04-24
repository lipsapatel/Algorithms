package ElementWhichAppearsMaximumOfTimes;

/**
 * Given an array of integers, write an algorithm to find element which occurs
 * maximum number of times
 *
 * int[] array = {4, 1, 5, 2, 1, 5, 9, 8, 6, 5, 3, 2, 4, 7}
 * Output: Element occurring maximum number of times - 5
 *          maximum count = 3
 *
 * int[] array = {2, 2, 1, 1}
 * Output: Element occurring maximum number of times - 2
 *          maximum count = 2
 *
 * The solution only works if all array elements are between 0 to n-1
 * Modulo because if you add size you cannot get the correct element
 * Update the array ith index as array[array[i] % size] = array[array[i] % size] + size
 *
 * Then iterate the array and find maximumElement and count
 * if array[i]/size = maximum count
 * maximumElement = i
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class ElementMaximumTimes_ModuloAddDivide_On {

    private static void elementAppearingMaximumTimes(int[] array) {

        int size = array.length;

        if (size < 1) {
            System.out.println("Array is empty");
            return;
        }

        for (int i = 0; i < size; i++) {
            array[array[i] % size] = array[array[i] % size] + size;
        }

        int maximumCount = 0;
        int maximumElement = 0;

        for (int i = 0; i < size; i++) {

            if (array[i]/size > maximumCount) {
                maximumCount = array[i]/size;
                maximumElement = i;
            }
        }

        System.out.println("The element appearing maximum number of times " + maximumElement +
                            " and it's count is " + maximumCount);
    }

    public static void main(String[] args) {

        int[] array = {4, 1, 5, 2, 1, 5, 9, 8, 6, 5, 3, 2, 4, 7};

        elementAppearingMaximumTimes(array);

        int[] array1 = {2, 2, 1, 1};

        elementAppearingMaximumTimes(array1);

        int[] array2 = {};

        elementAppearingMaximumTimes(array2);
    }
}
