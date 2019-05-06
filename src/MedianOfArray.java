/**
 * /*
 Given a unsorted array with integers, find the median of it.
 A median is the middle number of the array after it is sorted.
 If there are even numbers in the array, return the N/2-th number after sorted.
 Example
 Given [4, 5, 1, 2, 3], return 3
 Given [7, 9, 4, 5], return 5
 Challenge
 O(n) time.
 Tags Expand
 LintCode Copyright Quick Sort Array
 */

/*
Thoughts:
Goal: Find the median, which is N/2-th.
Enumerate a few examples and find median index = (nums.length  - 1) / 2
Quick sort has the nature of putting all smaller items on left, and larger numbers on right.
If that pivot point happends to hit the midian index, that's our target.
We don't necessarily need to sort all items, but just need to locate the median index.

Time complexity of QuickSelect: O(n) in case of odd
O(2n) in case of even
Space Complexity: O(1)
*/

public class MedianOfArray {

    public static double median(int[] a) {

        if (a == null || a.length == 0) {
            return 0;
        }

        if (a.length == 1) {
            return a[0];
        }

        int k = a.length/2;

        int median = quickSelect(a, 0, a.length - 1, k);

        if (a.length % 2 == 0) {
            int median1 = quickSelect(a, 0, a.length - 1, k - 1);
            return (double)(median + median1)/2;
        } else {
            return median;
        }
    }

    public static int quickSelect(int[] a, int low, int high, int k) {
        int pi = partition(a, low, high);

        if (pi == k) {
            return a[pi];
        } else if (pi < k) {
            return quickSelect(a, pi + 1, high, k);
        } else {
            return quickSelect(a, low, pi - 1, k);
        }
    }

    public static int partition(int[] a, int low, int high) {
        int middle = (low + high)/2;
        medianOfThree(a, low, middle, high);
        swap(a, middle, high);

        int pivot = a[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (a[j] < pivot) {
                i++;
                swap(a, i, j);
            }
        }

        i++;
        swap(a, i, high);
        return i;
    }

    public static void medianOfThree(int[] a, int l, int m, int h) {

        if (a[m] < a[l]) {
            swap(a, l, m);
        }
        if (a[h] < a[l]) {
            swap(a, l, h);
        }
        if (a[h] < a[m]) {
            swap(a, m, h);
        }
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {4, 5, 1, 2, 3};
        System.out.println("The median of array is: " + median(a));

        int[] a1 = {4, 5, 1, 2, 3, 6};
        System.out.println("The median of array is: " + median(a1));
    }
}
