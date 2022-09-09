package IK.Recursion.PreClass;

import java.util.Arrays;

/**
 * Assume that the input is an array of size 'n' where 'n' is an even number.
 * Additionally, assume that half the integers are even and the other half are odd.
 * Print only those permutations where odd and even integers alternate, starting with odd.
Pause and write code for this problem. Possible code structure will be in next section.

 Approach:
 1) Since odd and even alternate and it starts with odd, all even index will have odd numbers and all
 odd index will have even numbers.
 2) In the code to find the permutation, in recursive case for loop add condition to check the left index and array element

 Time Complexity: O(n!)
 Space Complexity: O(n) - Call stack

 Approach: Recursion

 */
public class PermuteWhereOddAndEvenAlternateWithOddFirst {

    private static void printPermutations(int[] array, int left) {

        //Base Case
        if (left == array.length - 1) {
            System.out.println(Arrays.toString(array));
            return;
        }

        //Recursive Case
        for (int i = left; i < array.length; i++) { //Try different options
            if ((left % 2 == 0 && array[i] % 2 != 0) || (left % 2 != 0 && array[i] % 2 == 0)) {
                swap(array, left, i);
                printPermutations(array, left + 1);
                swap(array, left, i); //restore original order
            }
        }
    }

    private static void swap(int[] array, int left, int i) {
        int temp = array[left];
        array[left] = array[i];
        array[i] = temp;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4};
        printPermutations(array, 0);
    }
}
