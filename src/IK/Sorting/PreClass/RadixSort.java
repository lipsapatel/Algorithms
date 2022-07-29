package IK.Sorting.PreClass;

import java.util.Arrays;

/**
 * The lower bound for Comparison based sorting algorithm (Merge Sort, Heap Sort, Quick-Sort .. etc) is Ω(nLogn), i.e.,
 * they cannot do better than nLogn.
 Counting sort is a linear time sorting algorithm that sort in O(n+k) time when elements are in range from 1 to k.

 What if the elements are in range from 1 to n^2?
 We can’t use counting sort because counting sort will take O(n2) which is worse than comparison based sorting algorithms.
 Can we sort such an array in linear time?
 Radix Sort is the answer. The idea of Radix Sort is to do digit by digit sort starting from least significant digit to most significant digit.
 Radix sort uses counting sort as a subroutine to sort.

 Do following for each digit i where i varies from least significant digit to the most significant digit.
 ………….a) Sort input array using counting sort (or any stable sort) according to the i’th digit.

 Example:
 Original, unsorted list:

 170, 45, 75, 90, 802, 24, 2, 66
 Sorting by least significant digit (1s place) gives: [*Notice that we keep 802 before 2, because 802 occurred before 2 in the original list, and similarly for pairs 170 & 90 and 45 & 75.]

 170, 90, 802, 2, 24, 45, 75, 66

 Sorting by next digit (10s place) gives: [*Notice that 802 again comes before 2 as 802 comes before 2 in the previous list.]

 802, 2, 24, 45, 66, 170, 75, 90
 Sorting by most significant digit (100s place) gives:

 2, 24, 45, 66, 75, 90, 170, 802

 Radix Sort takes O(d*(n+b)) time where b is the base for representing numbers, for example,
 for decimal system, b is 10. What is the value of d? If k is the maximum possible value,
 then d would be O(logb(k)). So overall time complexity is O((n+b) * logb(k)).
 Which looks more than the time complexity of comparison based sorting algorithms for a large k.
 Let us first limit k. Let k <= n^c where c is a constant.

 In that case, the complexity becomes O(nLogb(n)). But it still doesn’t beat comparison based sorting algorithms.
 If we set b as n, we get the time complexity as O(n).
 In other words, we can sort an array of integers with range from 1 to nc if the numbers are represented in base n

 The constant factors hidden in asymptotic notation are higher for Radix Sort and Quick-Sort uses hardware caches more effectively.
 Also, Radix sort uses counting sort as a subroutine and counting sort takes extra space to sort numbers.

 Approach:
 1) Iterate from Least Significant digit to Most significant digit - ones, tens, hundreds
 2) Sort by counting sort.
 3) In counting sort, create array of count
 4) Sum total count value of each index.
 5) Iterate original array from right to left to maintain stability
 6) Create result array and copy that into the original array

 Time Complexity: O(d * (n + k)) where k = count array in base 10
 Space Complexity: O(n + k)

 If we have 32 bit number represented in base 2, our d = 32
 But by increasing the base we can have less d. 8 bit is 1 byte so we can use 4 byte which base 256 and our d = 4
 So by increasing the base we can reduce time complexity of radix sort.

 You can also sort strings using LSD radix sort.
 Radix sort with base 256 performed better than merge sort to sort 1 million 32-bit integers.

 */
public class RadixSort {

    private static void radixSort(int[] array) {
        //Get max to find the number of digits
        int max = getMax(array);

        for (int exp = 1; max/exp > 0; exp *= 10) {
            countSort(array, exp, array.length);
        }
    }

    private static void countSort(int[] array, int exp, int n) {

        //Create count and result array
        int[] count = new int[10]; //Base 10 number 0 -9
        int[] result = new int[n]; //Because starts from 1

        //Store the count of each integer in count array
        for (int i = 0; i < n; i++) {
            count[(array[i]/exp) % 10]++;
        }

        //Update the count so that each index will store the sum till the previous step.
        //Which will give us the index
        for (int i = 1; i < 10; i++) {
            count[i] = count[i] + count[i - 1];
        }

        //Now traverse the input array from right to left
        //Get index from count and create result
        //This needs to be reversed order because 2a should come before 2b - for stability
        for (int i = n - 1; i >= 0; i--) {
            int index = count[(array[i]/exp) % 10];
            result[index - 1] = array[i];

            //decrement the count
            count[(array[i]/exp) % 10]--;
        }

        //Copy the result array into the original array
        for (int i = 0; i < n; i++) {
            array[i] = result[i]; //Since array starts with 0 index
        }
    }

    private static int getMax(int[] array) {
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.println("Original array: ");
        System.out.println(Arrays.toString(array));

        System.out.println("After sort");
        radixSort(array);
        System.out.println(Arrays.toString(array));

        int[] array1 = {100000001, 0, 1000000000};
        radixSort(array1);
        System.out.println(Arrays.toString(array1));
    }
}
