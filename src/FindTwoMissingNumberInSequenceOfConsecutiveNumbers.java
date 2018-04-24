/**
 * Find two missing numbers in a sequence of consecutive numbers.
 *
 * Input: int[] array with two missing numbers and range.
 * Output: Two missing numbers
 *
 * Approach:
 *
 * 1) Find sum of those missing numbers
 *    sum = totalSumForRange - totalSumForArray
 *    a+b
 * 2) Find product of those missing numbers
 *    product = totalProductForRange/TotalProductForArray
 *    a*b
 * 3) (a-b)^2 = (a + b)^2 -4ab, take squareroot to find a-b
 *    (a-b)
 * 4) a = (a+b)+(a-b)/2
 * 5) a + b = sum
 *    b = sum - a
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Example:
 *
 * int[] array = {10,2,3,5,7,8,9,1} Range = 10
 *
 * sum = 55 - 45 = 10
 * a + b = 10
 *
 * product = 3628800/151200 = 24
 *
 * (a - b) = Squareroot(100 - 4*24)
 *  a - b = 2
 *
 *  a = (a + b) + (a -b)/2
 *  a = 6
 *
 *  a + b = sum
 *  b = 10 - 6
 *  b = 4
 *
 */
public class FindTwoMissingNumberInSequenceOfConsecutiveNumbers {

    private static int[] findTwoMissingNumbers(int[] array, int range) {

        //Find sum a + b

        int totalSumRange = 0;
        for (int i = 1; i <= range; i++) {
            totalSumRange = totalSumRange + i;
        }

        int totalSumArray = 0;
        for (int i = 0; i < array.length; i++) {
            totalSumArray = totalSumArray + array[i];
        }

        int sum = totalSumRange - totalSumArray;

        //Find product a * b

        int totalProductRange = 1;
        for (int i = 1; i <= range; i++) {
            totalProductRange = totalProductRange * i;
        }

        int totalProductArray = 1;
        for (int i = 0; i < array.length; i++) {
            totalProductArray = totalProductArray * array[i];
        }

        int product = totalProductRange/totalProductArray;

        double difference = Math.sqrt(sum * sum - 4 * product);

        int a = (int)(sum + difference)/2;

        int b = sum - a;

        int[] result = {a,b};

        return result;
    }

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 5, 7, 8, 9, 10};

        int[] missingNumbers = findTwoMissingNumbers(array, 10);

        System.out.println("The missing numbers are " + missingNumbers[0] + " , " + missingNumbers[1]);
    }
}
