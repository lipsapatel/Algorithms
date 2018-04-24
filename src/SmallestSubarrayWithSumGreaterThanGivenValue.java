/**
 * In an array, find the smallest subarray with sum greater than the given value.
 *
 * Given an array and integer.
 *
 * Examples:
 *
 * int[] array = {1, 5, 20, 70, 8, 30, 80}
 * x = 97
 * Output: Min length = 2 Subarray = {30, 80}
 *
 * int[] array = {1, 10, 3, 40, 18}
 * x = 50
 * Output: Min length = 2 Subarray = {40, 18}
 *
 * Approach:
 *
 * Naive Approach: Two loops
 * Time Complexity: O(n2)
 *
 * Better approach:
 *
 * 1) Initialize minLength = arrayLength
 * 2) currentSum = 0, start = 0
 * 3) Do linear scan
 * 4) start adding each element to currentSum if the currentSum is less than x
 * 5) If currrentSum > x then check the length of subarray (i - start) if it is less than minLength
 * 6) Update minLength, answerStartIndex and answerEndIndex
 * 7) Subtract the start from the currentSum - currentSum - array[start]
 * 8) start++
 *
 * Time Complexity: O(n)
 */
public class SmallestSubarrayWithSumGreaterThanGivenValue {

    private static void findSmallestSubarrayWithSumGreaterThanGivenValue(int[] array, int x) {

        int start = 0;
        int currentSum = 0;
        int answerStartIndex = 0;
        int answerEndIndex = 0;
        int minLength = array.length;

        for (int i = 0; i <= array.length; i++) {

            while(currentSum > x) {

                //Store the indexes if the currentLength is less than minLength
                if (i - start <= minLength) {

                    minLength = i - start;
                    answerStartIndex = start;
                    answerEndIndex = i;
                }

                //Subtract from the currentSum
                currentSum = currentSum - array[start];
                start++;
            }

            if (i < array.length) {
                currentSum = currentSum + array[i];
            }
        }
        System.out.println("Minimum length of subarray is " + minLength);
        System.out.println("Subarray is ");
        for (int j = answerStartIndex; j < answerEndIndex; j++) {
            System.out.print(" " + array[j]);
        }
    }

    public static void main(String[] args) {
        int[] arrA = {1, 5, 20, 70, 8, 30, 80};

        findSmallestSubarrayWithSumGreaterThanGivenValue(arrA, 97);
    }

}
