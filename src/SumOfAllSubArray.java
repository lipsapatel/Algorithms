/**
 * Given an array of integers, write an algorithm to find sum of all possible sub-arrays.
 *
 * Example:
 * int[] array = {1, 2, 3}
 *
 * All possible subarrays
 * {1}, {1, 2}, {1, 2, 3}, {2}, {2, 3}, {3}
 *
 * Sum = 20
 *
 * Approach:
 *
 * Time Complexity: O(n3)
 *
 * 1) Take 3 nested loops
 * 2) Outer loop for starting point
 * 3) Second Inner loop for group size
 * 4) Inner loop to iterate from startPoint to group size and find sum
 *
 * Time Complexity: O(n)
 *
 * /resources/SumOfAllSubstrings.png
 *
 * 1) Array is {1, 2, 3, 4}
 *
 * All possible subarray
 *
 * {1}
 * {1, 2}
 * {1, 2, 3}
 * {1, 2, 3, 4}
 * {2}
 * {2, 3}
 * {2, 3, 4}
 * {3}
 * {3, 4}
 * {4}
 *
 * Total = 50
 *
 * 2) Total number of occurrence of each element
 *
 * 1 = 4 Times
 * 2 = 6 Times
 * 3 = 6 Times
 * 4 = 4 Times
 *
 * 3) Each element at first place
 *
 * 1 = 4 Times (n - 0)
 * 2 = 3 Times (n - 1)
 * 3 = 2 Times (n - 2)
 * 4 = 1 Times (n - 3)
 *
 * (n - i)
 *
 * 4) Remaining occurrences = Total Occurrences - First place occurrences
 *
 * 1 = 0 (4 - 4) i = 0
 * 2 = 3 (6 - 3) i = 1
 * 3 = 4 (6 - 2) i = 2
 * 4 = 3 (4 - 1) i = 3
 *
 * (n - i) * i
 *
 * 5) Total no of occurrences = First place occurrences + remaining occurrences
 * = (n - i) + (n - i) * i
 * = (n - i) * (i + 1)
 *
 */
public class SumOfAllSubArray {

    private static int findSumOfSubArrayUsingNestedLoop(int[] array) {

        int length = array.length;
        int totalSum = 0;

        //startPoint
        for (int startPoint = 0; startPoint < length; startPoint++) {

            //groupSize
            for (int groupSize = startPoint + 1; groupSize <= length; groupSize++) {

                //groupSize = 1, 2, 3, 4
                for (int j = startPoint; j < groupSize; j++) {

                    totalSum += array[j];
                }
            }
        }

        return totalSum;
    }

    private static int findSumOfSubarrayUsingFormula(int[] array) {

        int length = array.length;
        int totalSum = 0;

        for (int i = 0; i < length; i++) {

            totalSum += array[i] * (length - i)*(i + 1);
        }
        return totalSum;
    }

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4};

        System.out.println("The sum of all subarrays using nested loop is " +
                findSumOfSubArrayUsingNestedLoop(array));

        System.out.println("The sum of all subarrays using formula is " +
            findSumOfSubarrayUsingFormula(array));
    }
}
