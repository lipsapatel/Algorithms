import java.util.Arrays;

/**
 * Merge First Sorted Array Into Second Sorted Array

Problem Statement:
 Given two arrays:
 1) arr1 of size n, which contains n positive integers sorted in increasing order.
 2) arr2 of size (2*n) (twice the size of first), which contains n positive integers sorted in increasing order in its first half. Second half of this array arr2 is empty. (Empty elements are marked by 0).

 Write a function that takes these two arrays, and merges the first one into second one, resulting in an increasingly sorted array of (2*n) positive integers.

 Input Format:
 There are two arguments. First one is an integer array denoting arr1 and second one is also an integer array denoting arr2.

 Output Format:
 Return arr2, containing (2*n) increasingly sorted integers.

 Input/Output Format For The Custom Input:
 Input Format:
 The first line of input should contain an integer n, denoting size of input array arr1. In next n lines, ith line should contain an integer arr1[i], denoting value at index i of arr1.
 In next line, there should be an integer (2*n), denoting size of input array arr2. In next (2*n) lines, ith line should contain an integer arr2[i], denoting value at index i of arr2.

 If n = 3, arr1 = [1, 3, 5] and arr2 = [2, 4, 6, 0, 0, 0], then input should be:
 3
 1
 3
 5
 6
 2
 4
 6
 0
 0
 0

 Output Format:
 There will (2*n) lines of output, where ith line contains an integer res[i], denoting value at index i of res.
 Here, res is the result array returned by solution function.

 For input n = 3, arr1 = [1, 3, 5] and arr2 = [2, 4, 6, 0, 0, 0], output will be:
 1
 2
 3
 4
 5
 6

 Constraints:
 1 <= n <= 10^5
 1 <= arr1[i] <= 2 * 10^9
 1 <= arr2[i] <= 2 * 10^9 (for the first half)
 arr2[i] = 0 (for the second half)
 You can use only constant extra space.
 0 denotes an empty space.

 Sample Test Case:
 Sample Input:
 n = 3
 arr1 = [1 3 5]
 arr2 = [2 4 6 0 0 0]

 Sample Output:
 arr2 = [1 2 3 4 5 6]
 Notes:
 Maximum time allowed in interview: 20 Minutes.

 We are allowed to use only constant extra space.
 We can start merging from the end. (Or we can first create empty space at the beginning instead of the end by copying arr2[i] to arr2[n + i] for i from 0 to n - 1, and then start merging from the beginning. But this will add one more loop unnecessarily.)
 Have a look at the solution provided by us.

 Time Complexity:
 O(n) as we are traversing through the arr1 and arr2 only once.
SC = O(1)

 Auxiliary Space Used:
 O(1).

 Space Complexity:
 O(n) due to input size.
 */
public class MergeFirstSortedArrayIntoSecond {

    public static int[] mergeFirstIntoSecond(int[] a1, int[] a2) {

        int n = a1.length;
        int last1 = n - 1;
        int last2 = n - 1;
        int last = (2*n) - 1;

        while (last >= 0) {

            //Array 1 exhausted
            if (last1 < 0) {
                a2[last--] = a2[last2--];
            } else if (last2 < 0) { //Array 2 is exhausted
                a2[last--] = a1[last1--];
            } else if (a1[last1] > a2[last2]) {
                a2[last--] = a1[last1--];
            } else {
                a2[last--] = a2[last2--];
            }
        }
        return a2;
    }

    public static void main(String[] args) {
        int[] a1 = {1, 3, 5};
        int[] a2 = {2, 4, 6, 0, 0, 0};

        System.out.println("Sorted array: " + Arrays.toString(mergeFirstIntoSecond(a1, a2)));
    }
}
