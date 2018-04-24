/**
 * Given sorted array of positive integers, find out the smallest integer which cannot be
 * represented as sum of subset of array.
 *
 * Input: Sorted array
 * Output: Smallest integer which cannot be represented as sum of any subset of array.
 *
 * Example
 * int[] array = {1, 1, 3, 4, 6, 7, 9} - 32
 * int[] array = {1, 1, 1, 1, 1} - 6
 * int[] array = {2, 3, 6, 7} - 1
 * int[] array = {1, 2, 6, 7, 9} - 4
 *
 * If 1 is not present in the array our answer is 1.
 * Take variable smallestNumber = 1;
 * Find the gap between array elements which cannot be represented as the sum of subset of array
 * To find that keep adding currentElement to smallestNumber.
 * If currentElement > smallestNumber we found the gap.
 * return smallestNumber
 *
 * resources/smallestIntegerSubsetOfArray.png
 *
 * Time Complexity: O(n)
 */
public class SmallestIntegerNotSumOfSubset {

    private static int smallestIntegerNotSumOfSubset(int[] array) {

        int smallestNumber = 1;

        for (int i = 0; i < array.length; i++) {
            if (array[i] <= smallestNumber) {
                smallestNumber = smallestNumber + array[i];
            } else {
                return smallestNumber;
            }
        }

        return smallestNumber;
    }

    public static void main(String[] args) {

        System.out.println("Smallest Positive Integer that cant be represented by the sum of any subset of following arrays are : ");
        int [] arrA = { 1,1,3,4,6,7,9};
        System.out.println("{1,1,3,4,6,7,9} -" + smallestIntegerNotSumOfSubset(arrA));
        int [] arrB = {1,1,1,1,1};
        System.out.println("{1,1,1,1,1} -" + smallestIntegerNotSumOfSubset(arrB));
        int [] arrC = {2,3,6,7};
        System.out.println("{2,3,6,7} -" + smallestIntegerNotSumOfSubset(arrC));
        int [] arrD = {1,2,6,7,9};
        System.out.println("{1,2,6,7,9} -"+ smallestIntegerNotSumOfSubset(arrD));
    }
}
