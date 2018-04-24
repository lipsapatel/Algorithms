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
 * Use two loops
 * Compare each element with every other element
 * Maintain maximum count
 *
 * Time Complexity: O(n2)
 * Space Complexity: O(1)
 */
public class ElementMaximumTimes_NestedLoop_On2 {

    private static void elementAppearingMaximumTimes(int[] array) {

        int maximumCount = 0;
        int maxElement = 0;

        if(array.length < 1) {
            System.out.println("Array is empty");
            return;
        }

        for (int i = 0; i < array.length; i++) {

            int count = 0;

            for (int j = i; j < array.length; j++) {

                if (array[i] == array[j]) {
                    count++;
                }
            }

            if (count > maximumCount) {
                maximumCount = count;
                maxElement = array[i];
            }
        }

        System.out.println("The element appearing maximum number of times is " + maxElement +
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
