/**
 * Find number of reverse pairs in an array which is sorted in two parts in O(N)
 *
 * Given an array of integers A[] which is sorted in two parts (both parts are individually sorted), find no of reverse
 * pairs means no of (i,j) pairs where i belongs to the part one and j belongs to part 2 and A[i] > A[j]
 *
 * Example:
 *
 * A[] = {4, 6, 8, 9, 1, 5, 10, 11}
 *
 * Output: 7
 *
 * Explanation:
 *
 * Part One: {4, 6, 8, 9}
 * Part two: {1, 5, 10, 11}
 *
 * Reversed pairs: (4, 1), (6, 1), (6, 5), (8, 1), (8, 5), (9, 1), (9, 5) = 7
 *
 * Approach:
 *
 * Naive Approach:
 *
 * Use nested for loops and compare each element with all others elements in array and check if it is reversed pair,
 * if yes, then add it to the result. At the end print the result.
 *
 * Time Complexity: O(N2)
 *
 * Better Approach:
 *
 * 1) Iterate the array and find the start index of second sorted array, say it is m.
 * 2) Part one is from 0 to m - 1 and Part two is from m to length - 1
 * 3) Take two pointer i and j, i at index 0 and j at index m
 * 4) If A[i] > A[j], then all the elements from part one start index to end index will be greater than A[j]
 * 5) result = result + end_partOne - i + 1;
 *
 * Pseudo Code:
 *
 * Initialize result = 0
 *
 * Do while i < m - 1 && j <= length - 1
 *     If A[i] > A[j] then
 *     result = result + m - i + 1
 *     j++;
 *
 *     If A[i] < A[j]
 *     do i++;
 *
 * End Loop
 *
 * Time Complexity: O(N)
 *
 * Example
 *
 * A[] = {4, 6, 8, 9, 1, 5, 10, 11}
 * i = 0
 * end_partOne = 3
 * j = 4;
 * end_partTwo = 7
 * result = 0
 *
 * A[i] > A[j] 4 > 1
 * result = result + end_partOne - i + 1;
 * result = 0 + 3 - 0 + 1 = 4;
 * j++;
 *
 * ***********************************************************************
 *
 * i = 0
 * end_partOne = 3;
 * j = 5;
 * end_partTwo = 7;
 *
 * result = 4;
 *
 * A[i] < A[j] 4 < 5
 * i++;
 *
 * ****************************************************************************
 *
 * i = 1;
 * end_partOne = 3;
 * j = 5;
 * end_partTwo = 7;
 *
 * result = 4;
 *
 * A[i] > A[j] 6 > 5
 * result = result + end_partOne - i + 1;
 * result = 4 + 3 - 1 + 1;
 * result = 7;
 * j++;
 *
 * ******************************************************************************
 *
 * i = 1;
 * end_partOne = 3;
 * j = 6;
 * end_partTwo = 7;
 *
 * result = 7;
 *
 * A[i] < A[j] 6 < 10
 * i++;
 *
 * ****************************************************************************
 *
 * i = 2;
 * end_partOne = 3;
 * j = 6;
 * end_partTwo = 7;
 *
 * result = 7;
 *
 * A[i] < A[j] 8 < 10
 * i++;
 *
 * **************************************************************************
 *
 * i = 3;
 * end_partOne = 3;
 * j = 6;
 * end_partTwo = 7;
 *
 * result = 7;
 *
 * A[i] < A[j] 9 < 10
 * i++ break here
 *
 */
public class FindReversePairs {

    private static void findReversePairs(int[] A) {

        int length = A.length;

        //Find the start index of part two sorted array
        int m = 0;
        for (int i = 0; i < length - 1; i++) {

            if (A[i] > A[i + 1]) {

                m = i + 1;
                break;
            }
        }

        //Initialize the result
        int result = 0;

        //Part one
        int start_partOne = 0;
        int end_partOne = m - 1;


        //Part two
        int start_partTwo = m;
        int end_partTwo = length - 1;

        //Take two pointers at the beginning of each part
        int i = start_partOne;
        int j = start_partTwo;

        while (i <= end_partOne && j <= end_partTwo) {

            if (A[i] <= A[j]) {
                i++;
            } else if (A[i] > A[j]) {

                result = result + end_partOne - i + 1;
                j++;
            }
        }

        System.out.println("No of reversed pairs: " + result);
    }

    public static void main(String[] args) {

        int[] A = {4, 6, 8, 9, 0, 1, 2, 5, 10, 11};
        findReversePairs(A);
    }
}
