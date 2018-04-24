/**
 * Given an array, find the maximum element in array.
 *
 * Time Complexity:O(n)
 */
public class FindMaximumElementInArray {

    private static int findMaximumElement(int[] array) {

        int currentMax = array[0];

        for (int i = 1; i < array.length; i++) {

            if (array[i] > currentMax) {
                currentMax = array[i];
            }
        }

        return currentMax;
    }

    public static void main(String[] args) {

        int[] array = {4, 6, 3, 4};

        System.out.println("The maximum element is " + findMaximumElement(array));
    }
}
