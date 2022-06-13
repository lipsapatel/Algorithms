package IK.Sorting.HomeWork1;

import java.util.Arrays;

/**
 * Given an array which contains even and odd integers, write an algorithm that separates
 * even and odd numbers.
 *
 * int[] array = {1, 2, 3, 4, 6, 8, 7, 12}
 * Output: [12, 2, 4, 6, 1, 3, 7]
 *
 * 1) Do Lomuto's partition of quick sort
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * 1) Do Hoare's partition.
 * 2) Take two pointer left and right
 * 3) while left <= right
 * 4) If left is odd, then swap and decrement right
 * 5) else if left is even, increment left.
 *
 * Group the numbers
 Problem Statement:
 You are given an integer array arr, of size n. Group and rearrange them (in-place) into evens and odds in such a way that group of all even integers
 appears on the left side and group of all odd integers appears on the right side.

 Input/Output Format For The Function:
 Input Format:
 There is only one argument: Integer array arr.

 Output Format:
 Return the same integer array, with evens on left side and odds on the right side.
 There is no need to preserve order within odds or within evens.

 Input/Output Format For The Custom Input:

Input Format:
The first line of input should contain an integer n, denoting size of input array arr.
 In next n lines, ith line should contain an integer arr[i], denoting a value at index i of arr.

 If n = 4 and arr = [1, 2, 3, 4], then input should be:
 4
 1
 2
 3
 4

 Output Format:
 There will be n lines of output, where ith line contains an integer res[i], denoting value at index i of res.
 Here, res is the result array returned by solution function.

For input n = 4 and arr = [1, 2, 3, 4], output will be:
 4
 2
 1
 3

 Constraints:

 1 <= n <= 10^5
 arr contains only positive integers.
 arr may contains duplicate numbers.
 Solution with linear time complexity and constant auxiliary space is expected.

 Sample Test Case:
 Sample Input:
 [1, 2, 3, 4]

 Sample Output:
 [4, 2, 1, 3]

 Explanation:
 Order does not matter. Other valid solution are
 [2, 4, 1, 3]
 [2, 4, 3, 1]
 [4, 2, 3, 1]
 */
public class SeparateEvenAndOdd {

    private static int[] separateEvenAndOdd(int[] array) {
       //partition(array, 0, array.length - 1);
        hoarePartition(array, 0, array.length - 1);
       return array;
    }

    private static void partition(int[] a, int low, int high) {
        int i = low - 1;

        for (int j = low; j <= high; j++) {
            if(a[j] % 2 == 0) { //even
                i++;
                swap(a, i, j);
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void hoarePartition(int[] a, int low, int high) {
        int left = low;
        int right = high;

        while(left <= right) {
            if(a[left] % 2 == 0) { //even
                left++;
            } else {
                swap(a, left, right);
                right--;
            }
        }
    }

    public static void main(String[] args) {
        int [] array = {1,2,3,4,6,8,7,12};
        System.out.println("Rearranging arrays using left and right indexes");
        array = separateEvenAndOdd(array);
        System.out.println(Arrays.toString(array));
    }
}
