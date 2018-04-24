package MaximumDifferenceBetweenTwoElements;

/**
 * Given an array A write an algorithm to find maximum difference between two elements
 * where larger element appears after smaller element
 * A[j] > A[i]
 * j > i
 * A[j] - A[i] is maximum
 *
 * int[] array = {2, 5, 1, 7, 3, 9, 5}
 * Output = 8
 *
 * int[] array = {22, 12, 2, 5, 19}
 * Output: 17
 *
 * Take each element and get the difference with all other elements
 * Keep the track of largest difference and where larger element appears after
 * smaller element
 *
 * Time Complexity: O(n2)
 */
public class MaximumDifference_NestedLoop_On2 {

    private static int maximumDifferenceBetweenElements(int[] array) {

        int maximumDifference = -1;

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {

                if (array[j] > array[i] && array[j] - array[i] > maximumDifference) {
                    maximumDifference = array[j] - array[i];
                }
            }
        }

        return maximumDifference;
    }

    public static void main(String[] args) {

        int[] array = {2, 5, 1, 7, 3, 9, 5};

        System.out.println("The maximum difference is " + maximumDifferenceBetweenElements(array));

        int[] array1 = {22, 12, 2, 5, 19};

        System.out.println("The maximum difference is " + maximumDifferenceBetweenElements(array1));

        int[] array2 = {22};

        System.out.println("The maximum difference is " + maximumDifferenceBetweenElements(array2));
    }
}
