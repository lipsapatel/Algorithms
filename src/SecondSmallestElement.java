/**
 * Find the second smallest element in an array
 * int[] array = {12, 13, 1, 10, 34, 1}
 * Output: 10
 *
 * Time Complexity: O(n)
 *
 * Initialize both first and second to Integer.Max
 * Loop through all elements
 *
 * If currentElement < first, update first and second
 *
 * else if currentElement is smaller than second and it's not equal to first
 * the update second.
 */
public class SecondSmallestElement {

    private static int findSecondSmallestElement(int[] array) {

        if (array.length < 2) {
            return -1;
        }

        int firstElement = Integer.MAX_VALUE;
        int secondElement = Integer.MAX_VALUE;

        for (int i = 0; i < array.length; i++) {

            if (array[i] < firstElement) {
                secondElement = firstElement;
                firstElement = array[i];
            } else if (array[i] < secondElement && array[i] != firstElement) {
                secondElement = array[i];
            }
        }

        if (secondElement == Integer.MAX_VALUE) {
            return -1;
        }

        return secondElement;
    }

    public static void main(String[] args) {
        int[] array = {12, 13, 1, 10, 34, 1};

        int result = findSecondSmallestElement(array);

        if (result == -1) {
            System.out.println("There is no second smallest element");
        } else {
            System.out.println("The second smallest element is " + result);
        }

        int[] array1 = {1, 1, 1};

        int result1 = findSecondSmallestElement(array1);

        if (result1 == -1) {
            System.out.println("There is no second smallest element");
        } else {
            System.out.println("The second smallest element is " + result1);
        }

        int[] array2 = {2};

        int result2 = findSecondSmallestElement(array2);

        if (result2 == -1) {
            System.out.println("There is no second smallest element");
        } else {
            System.out.println("The second smallest element is " + result2);
        }
    }
}
