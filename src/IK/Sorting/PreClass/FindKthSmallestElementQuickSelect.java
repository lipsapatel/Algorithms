package IK.Sorting.PreClass;

import java.util.Random;

/**
 * Given an array of integers, find the kth smallest element in array.
 *
 * int[] array = {1, 2, 10, 20, 40, 32, 44, 51, 6}
 * k = 4
 * Kth smallest element = 10
 *
 * Approach
 *
 * 1) Do partition to find pivot
 * 2) If k is less than pivot, make recursive call to left (low, pi - 1)
 * 3) If k is greater than pivot, make recursive call to right (pi + 1, high)
 * 4) If k is equal to pivot, then return element at that pivot index.
 *
 * Time Complexity: O(n)
 * Space Complexity: (1)
 * Worst Case Time Complexity in case of sorted array: O(n^2)
 *
 */
public class FindKthSmallestElementQuickSelect {

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //The average time complexity is O(n)
    //Worst Case time complexity is O(n^2)
    //Stack space = O(logn)
    //Heap Space = O(1)
    //n + n/2 + n/4 + ..1 = n(1+ 1/2...) = n (1/i < 2) = Work at each level is not same
    //Not to do complete quicksort, do it till pivot == kth element
    //Make recursive call to either left or right sides of pivot based on the position of pivot
    private static int findKthSmallestElementUsingQuickSelect(int[] a, int low, int high, int k) {

        //CHECKS
        //In your original method check if array is null or empty
        //k is from 1 to n

        //If k is smaller than number of elements in array
        if (k > 0 && k <= a.length) {

            int pi = partition(a, low, high);

            if (pi == k - 1) {
                return a[pi];
            }
            if (pi > k - 1) {
                return findKthSmallestElementUsingQuickSelect(a, low, pi - 1, k);
            } else {
                return findKthSmallestElementUsingQuickSelect(a, pi + 1, high, k);
            }
        }
        return Integer.MAX_VALUE;
    }

    private static int partition(int[] a, int low, int high) { //O(n)
        //Choosing pivot randomly also takes some time
        //Random random = new Random(System.currentTimeMillis());
        //int p = low + random.nextInt(high - low + 1);
        //swap(a, p, high);

        //Selecting pivot using median of three is faster and it balances out in case of sorted array so will not have n^2 complexity
        int middle = (low + high)/2;
        medianOfThree(a, low, middle, high);
        swap(a, middle, high);

        int pivot = a[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if(a[j] <= pivot) {
                i++;
                swap(a, i, j);
            }
        }
        i++;
        swap(a, i, high);
        return i;
    }

    //Sort low, mid and high
    private static void medianOfThree(int[] a, int low, int middle, int high) {

        if (a[middle] < a[low]) {
            swap(a, low, middle);
        }
        if (a[high] < a[low]) {
            swap(a, low, high);
        }

        if (a[high] < a[middle]) {
            swap(a, middle, high);
        }
    }

    public static void main(String[] args) {

        int[] array = new int[]{1, 6, 2, 9, 4, 3, 8};
        System.out.println("The 4th smallest element using quick-select is " + findKthSmallestElementUsingQuickSelect(array, 0, array.length - 1, 4));

        int[] array1 = {1, 2, 10, 20, 40, 32, 44, 51, 6};
        System.out.println("The 4th smallest element using quick-select is " + findKthSmallestElementUsingQuickSelect(array1, 0, array1.length - 1, 4));

    }
}
