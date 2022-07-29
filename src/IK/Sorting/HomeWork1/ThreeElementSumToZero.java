package IK.Sorting.HomeWork1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 3 Sum

 Problem Statement:
 Given an integer array arr of size N, find all magical triplets in it.
 Magical triplet is the group of 3 integers, whose sum is zero.

 Note that magical triplets may or may not be made of consecutive numbers in arr.

 Input/Output Format For The Function:
 Input Format:
 There is only one argument: integer array arr.

 Output Format:
 Return a string array res.
 Each string in res array corresponds to a magical triplet in arr.

 Note that:

 Order of strings(magical triplets) in res does not matter.
 Order of elements inside a magical triplet does not matter. i.e. if your output triplet have same elements, but only in different order, then it's an acceptable triplet
 and will lead to an acceptable solution.
 There should be no duplicate triplets in res. Eg: 1,1,-2 and 1,-2,1 are duplicates.
 If no such triplets are found, then return an empty array as res.

 Input/Output Format For The Custom Input:
 Input Format:
 The first line of input should contain an integer N, denoting size of input array arr. In next N lines, ith line should contain an integer arr[i],
 denoting a value at index i of arr.

 If N = 6 and arr = [10, 3, -4, 1, -6, 9], then input should be:
 6
 10
 3
 -4
 1
 -6
 9

 Output Format:
 Let’s denote size of res as m, where res is the array of strings returned by solution function.
 Then, there will be m lines of output, where ith line contains res[i], denoting value at index i of res.

 For input N = 6 and arr = [10, 3, -4, 1, -6, 9], output will be:

 10,-4,-6
 3,-4,1

 Constraints:

 1 <= N <= 2000
 -10^3 <= arr[i] <= 10^3, for all valid i
 arr may contain duplicate numbers.
 arr is not necessarily sorted.

 Sample Test Case:
 Sample Test Case 1:
 Sample Input 1:
 arr = [10, 3, -4, 1, -6, 9];

 Sample Output 1:

 10,-4,-6
 3,-4,1

 Sample Test Case 2:
 Sample Input 2:

 arr = [12, 34, -46];

 Sample Output 2:

 12,-46,34

 Sample Test Case 3:
 Sample Input 3:

 arr = [0, 0, 0];

 Sample Output 3:
 0,0,0

 Sample Test Case 4:
 Sample Input 4:

 arr = [-2, 2, 0 -2, 2];

 Sample Output 4:
 2,-2,0

 Brute force solution
 A simple solution is to use three nested loops and check for each possible combination if their sum is zero. To avoid duplicate answers,
 we need to hash the triplet and store it in a set. An easy way to hash a triplet is by converting it to a string.

 Extra space needed will be the number of unique triplets that contribute to the answer.

 Space Complexity:
 O(n^2)

 Time Complexity:
 O(n^3)

 Optimal solution
 Optimal solution uses the two pointer method. We maintain left and right pointers to elements of a sorted array. If their sum is greater than
 intended we decrease the right pointer, otherwise we increase the left pointer. This method works in linear time (and to solve this particular problem
 we use this algorithm n times, once for each element in arr).

 First, we will sort arr (this will contribute O(n*log(n)) to the time complexity). Then, for every element or arr, we will apply the two
 pointer technique to find any magic triplets that include that element. We will then add unique triplets to the answer.

 Time Complexity:
 O(n^2 + n*log(n))

 Space Complexity:
 O(n^2)
*********************************************************************************************************************

 Approach:

 1) Sort the array
 2) Traverse the array and take a
 3) Find b + c by taking 2 pointers that move towards each other. For this two pointers array needs to be sorted.
 4) Duplicates needs to be removed.
 5) It's sorted array so duplicates are together
 6) Use set for removing duplicates.
 7) If sum of two pointers == b + c then increment left pointer and decrement right pointer. Add a, b and c as comma separated string in set
 8) If sum of two pointers > b + c then decrement right pointer
 9) If sum of two pointers < b + c then increment left pointer
 10) If we have input {0, 0, 0, 0, 0, 0} then the two pointer approach will give duplicates. So we need to use set.
 11) To avoid using set for avoiding duplicates, we need to check for duplicates

 Time Complexity: O(nlogn) + O(n^2)
 Space Complexity: O(n)

 */
public class ThreeElementSumToZero {

    private static String[] findZeroSum(int[] arr) {

        int size = arr.length;

        Arrays.sort(arr); //IMP to sort

        HashSet<String> set = new HashSet<>(); //- One way to avoid duplicates

        for (int i = 0; i < size; i++) {

            int a = arr[i];

            int sum = -a;

            int start = i + 1;
            int end = size - 1;

            while (start < end) {

                int tempSum = arr[start] + arr[end];

                if (tempSum == sum) {
                    set.add(a + "," + arr[start] + "," + arr[end]);
                    start++;
                    end--;
                } else if (tempSum < sum) {
                    start++;
                } else if (tempSum > sum) {
                    end--;
                }
            }
        }

        return set.toArray(new String[set.size()]);
    }

    public static void main(String[] args) {
        int[] array = {6, 10, 3, -4, 1, -6, 9};
        int[] array1 = {0, 0, 0, 0, 0, 0, 0};
        System.out.println("The elements are: " + Arrays.toString(findZeroSum(array)));

        int[] a = {-2, 2, 0, -2, 2};
        System.out.println("The elements are: " + Arrays.toString(findZeroSum(a)));
    }
}
