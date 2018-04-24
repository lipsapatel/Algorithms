package ThreeElementsSumZero;

/**
 * Given an array of integer find 3 elements that sum to zero
 * a + b + c = 0
 *
 * int[] a = {3, -1, -4, -5, -7, 9, 10}
 * Elements are -4, -5 and 9
 *
 * Use three nested loops and find 3 elements that sum to 0
 * Time Complexity: O(n3)
 */
public class NestedLoopOn3
{
    private static void findThreeElementsThatSumToZero(int[] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                for (int k = j + 1; k < array.length; k++) {
                    if (array[i] + array[j] + array[k] == 0) {
                        System.out.println("The three elements that sum to zero are " + array[i] +
                        "," + array[j] + "," + array[k]);
                        return;
                    }
                }
            }
        }
        System.out.println("No three elements found that sum to zero");
    }

    public static void main(String[] args) {
        int[] array = {3, -1, -4, -5, -7, 9, 10};

        findThreeElementsThatSumToZero(array);
    }
}
