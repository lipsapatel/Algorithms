import java.util.Arrays;

/**
 * Given an array of unsorted numbers, check if all the numbers in the array are consecutive
 * numbers.
 *
 * Example:
 * int[] array = {21, 24, 22,  26, 23, 25} - True
 * All integers are consecutive from 21 to 26
 *
 * int[] array = {11, 10, 12, 14, 13} - True
 * All integers are consecutive from 10 to 14
 *
 * int[] array = {11, 10, 14, 13} - False
 * All integers are not consecutive - 12 is missing
 *
 * int[] array = {4, 5, 6, 6, 8} - False - has duplicates
 * All integers are not consecutive - 7 is missing
 *
 * int[] array = {-4, -5, -6, -6, -8} - False - has duplicates
 * All integers are not consecutive - (-7) is missing
 *
 * Sorting - Time Complexity O(nlogn)
 *
 * Better Approach
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Limitation: It has to be all positive integers
 * Works only if all the elements are in the range of 0 to n-1 where n is the size of the array.
 * (We can fulfill this limitation by subtracting min)
 *
 * 1) Find the minimum element in array
 * 2) Find the maximum element in array
 * 3) check if array.length = max - min + 1 else return false
 * 4) To have all the elements in the range of 0 to n - 1 subtract min from all the elements of
 * array
 * 5) Check if the array does not contain duplicates.
 *
 *  To check for duplicates
 *
 *  1) Iterate the array
 *  2) Negate the ith element of array.
 *  3) If it's already negative then we have duplicates
 *  4) If the element is 0 then update it to INTEGER_MIN_VALUE
 *
 *  array[array[i]] = array[array[i]] * -1;
 *
 *  Another Approach
 *
 *  Time Complexity: O(n)
 *  Space Complexity: O(n)
 *
 *  Limitation: Works only if all the elements are in the range of 0 to n-1 where n is the size
 *  or array (We can fulfill this limitation by subtracting min)
 *
 *  1) Find the minimum element in array
 *  2) Find the maximum element in array
 *  3) Check if array.length = max - min + 1 else return false
 *  4) To have all the elements in the range of 0 to n - 1 subtract min from all the elements
 *  of array
 *  5) Check if the array does not contain duplicates.
 *
 *  To check for duplicates
 *
 *  1) Create aux array and insert 0 at every position
 *  2) Iterate main array
 *  3) if aux[array[i]] == 0 then aux[array[i]] = 1 else if it's already 1 return false
 *  we have duplicates.
 *
 *
 */
public class CheckIfArrayIsConsecutiveIntegers {

    private static boolean checkIfArrayIsConsecutiveIntegersByNegative(int[] array) {

        if (!checkForArrayLength(array)) {
            return false;
        }

        int min = findMin(array);

        //Subtract min from all the elements to have it in the range of 0 to n-1
        //Because for finding the duplicates by negate it requires element in the range of
        //0 to n - 1
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] - min;
        }

        if (hasDuplicateByNegate(array)) {
            return false;
        }

        return true;
    }

    private static boolean checkIfArrayIsConsecutiveIntegersUsingAuxArray(int[] array) {
        if (!checkForArrayLength(array)) {
            return false;
        }

        int min = findMin(array);

        //Subtract min from all the elements to have it in the range of 0 to n - 1
        //Because for finding the duplicates by negate it requires element in the range of
        //0 to n - 1
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] - min;
        }

        if (hasDuplicateUsingAuxArray(array)) {
            return false;
        }
        return true;
    }

    private static boolean hasDuplicateUsingAuxArray(int[] array) {

        int[] auxArray = new int[array.length];

        //Initialize it with all 0
        for (int i = 0; i < auxArray.length; i++) {
            auxArray[i] = 0;
        }

        //Make it 1 if 0 else duplicate
        for (int i = 0; i < array.length; i++) {

            if (auxArray[array[i]] == 0) {
                auxArray[array[i]] = 1;
            } else {
                return true; //has duplicate
            }
        }
        return false;
    }

    private static boolean hasDuplicateByNegate(int[] array) {

        for (int i = 0; i < array.length; i++) {

            int index;
            if (array[i] == Integer.MIN_VALUE) {
                index = 0;
            } else {
                index = Math.abs(array[i]);
            }
            if (array[index] < 0) {
                return true;
            } else if (array[index] == 0) {
                array[index] = Integer.MIN_VALUE;
            } else {
                array[index] = array[index] * -1;
            }
        }
        return false;

    }

    private static boolean checkForArrayLength(int[] array) {

        int max = findMax(array);
        int min = findMin(array);
        int length = max - min + 1;

        if (array.length != length) {
            return false;
        } else {
            return true;
        }
    }

    private static int findMax(int[] array) {

        int max = array[0];

        for (int i = 0; i < array.length; i++) {

            if (max < array[i]) {
                max = array[i];
            }
        }

        return max;
    }

    private static int findMin(int[] array) {

        int min = array[0];

        for (int i = 0; i < array.length; i++) {
            if (min > array[i]) {
                min = array[i];
            }
        }

        return min;
    }

    public static void main(String[] args) {

        int[] array = {4, 5, 6, 6, 8};
        System.out.println(Arrays.toString(array) + " :" + checkIfArrayIsConsecutiveIntegersByNegative(array));

        int[] array1 = {21, 24, 22, 26, 23, 25};
        System.out.println(Arrays.toString(array1) + " : " + checkIfArrayIsConsecutiveIntegersByNegative(array1));

        int[] array2 = {11, 10, 12, 14, 13};
        System.out.println(Arrays.toString(array2) + " : " + checkIfArrayIsConsecutiveIntegersByNegative(array2));

        int[] array3 = {11, 10, 14, 13};
        System.out.println(Arrays.toString(array3) + " : " + checkIfArrayIsConsecutiveIntegersByNegative(array3));

        int[] array4 = {-4, -5, -6, -6, -8};
        System.out.println(Arrays.toString(array4) + ": " + checkIfArrayIsConsecutiveIntegersByNegative(array4));

        int[] array5 = {4, 5, 6, 6, 8};
        System.out.println(Arrays.toString(array5) + " :" + checkIfArrayIsConsecutiveIntegersUsingAuxArray(array5));

        int[] array6 = {21, 24, 22, 26, 23, 25};
        System.out.println(Arrays.toString(array6) + " : " + checkIfArrayIsConsecutiveIntegersUsingAuxArray(array6));

        int[] array7 = {11, 10, 12, 14, 13};
        System.out.println(Arrays.toString(array7) + " : " + checkIfArrayIsConsecutiveIntegersUsingAuxArray(array7));

        int[] array8 = {11, 10, 14, 13};
        System.out.println(Arrays.toString(array8) + " : " + checkIfArrayIsConsecutiveIntegersUsingAuxArray(array8));

    }
}
