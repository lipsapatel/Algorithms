import java.util.Arrays;

/**
 * Counting sort is a sorting algorithm which sorts the integers given in specific range.
 *
 * Approach:
 *
 * 1) Take two array count and result
 * 2) Input array given
 * 3) Store the count of each integer in count array
 * 4) Update count so that each index will store the sum till previous step
 * 5) Now traverse the input array. count[input[i]] will give you the correct index position
 * for that element in result array.
 * Decrement count[input[i]]
 *
 * resources/CountingSort.png
 *
 * Time Complexity: O(n + k) when the elements are in the range from 1 to k
 * Space Complexity O(n)
 *
 * //count array stores the count of each element
 * //Sum the count array  which gives you index
 * //find the index of input element from count array and add to result array
 */
public class CountingSort {

    private static int[] countSort(int[] input) {

        //Take two array - count and result
        int[] count = new int[input.length + 1];
        int[] result = new int[input.length + 1];

        //Insert 0 in count
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }

        //Store the count of each integer in given array
        for (int i = 0; i < input.length; i++) {
            count[input[i]] = count[input[i]] + 1;
        }

        //Update the count so that each index will store the sum till the previous step.
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }

        //Now traverse the input array
        //count[input[i]] gives you the index position in the result array
        for (int i = 0; i < input.length; i++) {

            int index = count[input[i]];
            result[index] = input[i];

            //decrement the count
            count[input[i]] = count[input[i]] - 1;
        }

        return result;
    }

    public static void main(String[] args) {
        int input[] = { 2, 1, 4, 5, 7, 1, 1, 8, 9, 10, 11, 14, 15, 3, 2, 4 };

        System.out.println("Orginal Array " + Arrays.toString(input));

        int[] B = countSort(input);
        System.out.println("Sorted Array : " + Arrays.toString(B));
    }
}
