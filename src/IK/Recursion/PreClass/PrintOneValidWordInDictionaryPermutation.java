package IK.Recursion.PreClass;

import java.util.Arrays;

/**
 * Assume that the input is an array of characters. Print any one permutation that is also a word in the dictionary.
 * Assume that you have two library functions you can use.
 *
 bool ValidWord(char array a) returns true if and only if the string a is a dictionary word.
 bool ValidWordPrefix(char array a, int a_size) returns true if and only if a[0...a_size-1] is a
 prefix of at least one word in the dictionary.

 Pause to write the code. Possible code structure will be in next section.

 Approach: Recursion
 Time Complexity: O(n) Because we are not going in recursive branch which is not in dictionary
 Space Complexity: O(n)
 */
public class PrintOneValidWordInDictionaryPermutation {

    private static boolean validWord(char[] array) {
        if (new String(array).equals("bca")) {
            return true;
        }
        return false;
    }

    private static boolean validWordPrefix(char[] array, int size) {
        if (new String(array, 0, size + 1).equals("b")) {
            return true;
        } else if (new String(array, 0, size + 1).equals("bc")) {
            return true;
        } else if (new String(array, 0, size + 1).equals("bca")) {
            return true;
        }
        return false;
    }

    private static boolean printPermutations(char[] array, int left) {

        //Base Case
        if (left == array.length - 1) {
            if (validWord(array)) {
                System.out.println(Arrays.toString(array));
                return true;
            } else {
                return false;
            }
        }

        //Recursive Case
        //Try all possible options
        for (int i = left; i < array.length; i++) {

            swap(array, left, i);

            if (validWordPrefix(array, left)) {
                if (printPermutations(array, left + 1)) {
                    return true;
                }
            }
            swap(array, left, i);
        }
        return false;
    }

    private static void swap(char[] array, int left, int i) {
        char temp = array[left];
        array[left] = array[i];
        array[i] = temp;
    }

    public static void main(String[] args) {
        char[] array = {'a', 'b', 'c'};
        printPermutations(array, 0);
    }
}
