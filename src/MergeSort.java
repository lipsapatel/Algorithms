import java.util.Arrays;

/**
 * Write merge sort to sort an elements of an array.
 *
 * Input: Unsorted array.
 * Output: Sorted array.
 *
 * Approach:
 *
 * Divide and Conquer
 *
 * In this approach we will divide the main problem into smaller problems.
 * Solve them and merge them to get the final results.
 *
 * We divide the elements into two half's by middle of the array.
 * We solve the left half and right half recursively and merge the results.
 *
 * Merge:
 *
 * Once sorting is done individually on both half's, our next task will be merge them.
 * To merge we start with both the array at the beginning. pick the smaller one and put it in the array.
 * then compare the next element and so on.
 *
 * resources/MergeSort.png
 *
 * Time Complexity: O(nlogn)
 * logn for dividing
 * n for merging
 *
 * Space Complexity: O(n)
 *
 * Two approaches for merge:
 *
 * 1) Using Auxiliary array for copying data:
 * In this approach you won't create new array every time for merging instead you create Auxiliary
 * array and this will save memory for you.
 *
 * 2) Alternate merging between Primary and Auxiliary array:
 * This is the best approach for merging. You don't copy the entire array to auxiliary.
 * Instead you do alternate merging between primary and auxiliary array.
 *
 * Below is the running time comparison between all three approaches
 *
 Data Size	Dynamic Memo Allo­ca­tion Algo	Using Aux­il­lary Array with copy­ing data	Alter­nate Merg­ing Between Pri­mary and Aux­il­lary Array
 1 Mil­lion	600–630 mili sec	            450–470 mili sec	                        400–425 mili sec
 10 mil­lion	6 secs	                    4.2 secs	                                2.3 secs
 100 mil­lion	56 secs	                    46 sec	                                    18 sec
 */
public class MergeSort {

    private static int[] inputArray;
    private static int[] auxiliaryArray;
    private static int size;

    private static void mergeSort(int[] array) {
        inputArray = array;
        size = array.length;
        auxiliaryArray = new int[array.length];

        sort(0, inputArray.length - 1);
    }

    private static void sort(int low, int high) {

        if (low < high) {

            int mid = low + (high - low)/2;
            sort (low, mid);
            sort (mid + 1, high);

            merge(low, mid, high);
        }

    }

    private static void merge(int low, int mid, int high) {

        //Copy the entire array in auxiliary array
        for (int i = low; i <= high; i++) {
            auxiliaryArray[i] = inputArray[i];
        }

        int i = low;
        int j = mid + 1;

        int k = low;

        while(i <= mid && j <= high) {

            if (auxiliaryArray[i] < auxiliaryArray[j]) {
                inputArray[k] = auxiliaryArray[i];
                i++;
            } else {
                inputArray[k] = auxiliaryArray[j];
                j++;
            }
            k++;
        }

        while(i <= mid) {
            inputArray[k] = auxiliaryArray[i];
            i++;
            k++;
        }

    }

    public static void main(String[] args) {

        int [] array = {2,1,6,3,9,4,5,10};
        //int array[] = { 1, 2, 3, 4, 5, 16, 17, 18, 19, 249 };
        System.out.println("Input Array: " + Arrays.toString(array));
        mergeSort(array);
        System.out.println("Sorted Array: " + Arrays.toString(inputArray));
    }

}
