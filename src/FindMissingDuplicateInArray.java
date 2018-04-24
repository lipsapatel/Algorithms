/**
 * Given an Integer array. Array contains duplicates of all the numbers except one number.
 * Find that missing number.
 *
 * int[] array = {2, 1, 3, 5, 5, 3, 2, 1, 6, 7, 7, 8, 8};
 * Output: Missing duplicate number is 6
 *
 * We can use HashTable. Store the number and their count. Return the number with count 1.
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Better solution: XOR
 *
 * A ^ A = 0
 * A ^ B ^ A = B
 *
 * If we XOR all the elements, answer will be the missing number
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class FindMissingDuplicateInArray {

    private static int findMissingDuplicateInArray(int[] array) {

        int missingNumber = array[0]; //If the array has only one element, the missing number will be that number

        for (int i = 1; i < array.length; i++) {
            missingNumber = missingNumber ^ array[i];
        }

        return missingNumber;
    }

    public static void main(String[] args) {

        int[] array = {2, 1, 3, 5, 5, 3, 2, 1,6, 7, 7, 8, 8};

        System.out.println("The missing number is " + findMissingDuplicateInArray(array));
    }
}
