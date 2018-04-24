/**
 * Find a pair of numbers from array whose sum is equal to k.
 *
 * Write an algorithm to find out whether in a given array there exists or not two numbers whose
 * sum is exactly equals to given number.
 *
 * Input: Array and number
 * Output: True or False based on whether we have found two numbers whose sum equals to given number.
 *
 * Time Complexity: O(nlogn)
 *
 * Approach:
 *
 * 1) First sort the array using merge sort - Time Complexity: O(nlogn)
 * 2) Start from both the ends of the array, i = 0 and j = array.length - 1
 * 3) Add first and last element = S.
 * 4) If S = number return true
 * 5) If S < number, do i++
 * 6) If S > number, do j--
 * 7) Repeat it while i < j
 * 8) If not found then return false.
 *
 */
public class FindPairOfNumbersWhoseSumEqualsToK {

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

    private static boolean findPairOfNumbersWhoseSumEqualsToKUsingBothEnds(int[] array, int number) {

        //sort the array
        mergeSort(array);

        int i = 0;
        int j = inputArray.length - 1;
        int sum = 0;

        while(i != j) {
            sum = inputArray[i] + inputArray[j];

            if(sum == number) {
                return true;
            } else if (sum < number) {
                i++;
            } else if (sum > number) {
                j--;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int array[] = { 1, 2, 3, 4, 5, 16, 17, 18, 19, 249 };
        int number = 269;
        int number1 = 8;

        System.out.println("USING Both Ends -Sum of two numbers in array A is "
                + number + " ??? :" + findPairOfNumbersWhoseSumEqualsToKUsingBothEnds(array, number));

        System.out.println("USING Both Ends -Sum of two numbers in array A is "
                + number1 + " ??? :" + findPairOfNumbersWhoseSumEqualsToKUsingBothEnds(array, number1));

    }
}
