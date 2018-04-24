/**
 * Find the second largest element in an array
 * int[] array = {12, 13, 1, 10, 34, 1}
 * Output: 10
 *
 * Time Complexity: O(n)
 *
 * Initialize both first and second to Integer.Max
 * Loop through all elements
 *
 * If currentElement > first, update first and second
 *
 * else if currentElement is greater than second and it's not equal to first
 * the update second.
 */
public class SecondLargestElement {

    private static int findSecondLargestElement(int[] array) {

        if (array.length < 2) {
            return -1;
        }

        int firstElement = Integer.MIN_VALUE;
        int secondElement = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {

            if (array[i] > firstElement) {
                secondElement = firstElement;
                firstElement = array[i];
            } else if (array[i] > secondElement && array[i] != firstElement) {
                secondElement = array[i];
            }
        }

        if (secondElement == Integer.MIN_VALUE) {
            return -1;
        }

        return secondElement;
    }

    public static void main(String[] args) {
        int[] array = {12, 13, 1, 10, 34, 1};

        int result = findSecondLargestElement(array);

        if (result == -1) {
            System.out.println("There is no second largest element");
        } else {
            System.out.println("The second smallest largest is " + result);
        }

        int[] array1 = {1, 1, 1};

        int result1 = findSecondLargestElement(array1);

        if (result1 == -1) {
            System.out.println("There is no second largest element");
        } else {
            System.out.println("The second largest element is " + result1);
        }

        int[] array2 = {2};

        int result2 = findSecondLargestElement(array2);

        if (result2 == -1) {
            System.out.println("There is no second largest element");
        } else {
            System.out.println("The second largest element is " + result2);
        }
    }
}
