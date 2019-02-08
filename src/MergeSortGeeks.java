import java.util.Arrays;

/**
 * Merge Sort is a divide and conquer algorithm.
 *
 * It divides input array in two halves, calls itself for the two halves and then merges the two sorted halves.
 * The merge() function is used for merging two halves.
 * The merge(arr, l, m, r) is key process that assumes that arr[l..m] and arr[m+1..r] are sorted and merges the two sorted sub-arrays into one.
 *
 * Time Complexity: For all cases O(nlogn)
 * Space Complexity: O(n)
 * Stable - in case of duplicates, the relative order is preserved in case of same key
 *
 *
 * Useful for sorting linked list.
 * In linked list random access is not possible like in array and moreover they are not stored
 * in contiguous memory location.
 *
 * The items can be inserted in linked list in middle in O(1) time.
 * There fore merge operation of mergesort can be implement without extra space for linked list.
 *
 * Quick sort needs random access therefore good in array
 * Merge sort is sequential access so linked list is good.
 *
 * 1) Recursive call to sort for left and right
 * 2) Merge left and right
 *    1) create third sorted array
 *
 *    Merge Sort is useful in sorting Linkedlist in O(nlogn) time. elements are not contiguous in linked list, and merge is done with out extra
 *    space since we can insert items in middle.
 */
public class MergeSortGeeks {

    private static void mergeSort(int[] a, int l, int r) {

        if (l < r) {

            int m = (l + r)/2; //Middle

            //sort left and right
            mergeSort(a, l, m);
            mergeSort(a, m + 1, r);

            //Merge left and right
            merge(a, l, m, r);
        }
    }

    private static void merge(int[] a, int l, int m, int r) {

        //Create 2 arrays
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = a[l + i];
        }

        for (int i = 0; i < n2; i++) {
            R[i] = a[m + i + 1];
        }

        //Merge logic
        int i = 0;
        int j = 0;
        int k = l;

        while (i < n1 && j < n2) {

            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
            k++;
        }

        //Copy remaining
        while (i < n1) {
            a[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            a[k] = R[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] a = {12, 11, 13, 5, 6, 7};

        System.out.println("Original Array");
        System.out.println(Arrays.toString(a));

        mergeSort(a, 0, a.length - 1);

        System.out.println("After merge sort");
        System.out.println(Arrays.toString(a));
    }
}