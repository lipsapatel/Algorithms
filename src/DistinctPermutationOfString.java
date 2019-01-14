import java.util.Arrays;

/**
 * Find distinct permutations of string if the given string has duplicate characters
 *
 * For Example : BAA
 *
 * The different permutations are BAA, BAA, ABA, AAB, ABA
 *
 * This should return only BAA, ABA, AAB
 *
 * If there are duplicates from left to ith position, don't swap
 *
 */
public class DistinctPermutationOfString {

    private static boolean hasDuplicate(char[] input, int left, int current) {

        for (int i = left; i < current; i++) {
            if (input[i] == input[current]) {
                return true;
            }
        }
        return false;
    }

    private static void findPermutations(char[] input, int left) {

        //Base Case
        if (left == input.length) {
            System.out.println(Arrays.toString(input));
            return;
        }

        for (int i = left; i < input.length; i++) {
            //Do swap if there are no duplicate
            if (!hasDuplicate(input, left, i)) {
                swap(input, left, i);
                findPermutations(input, left + 1);
                swap(input, left, i);
            }
        }
    }

    private static void swap(char[] input, int left, int i) {
        char temp = input[left];
        input[left] = input[i];
        input[i] = temp;
    }

    public static void main(String[] args) {
        String input = "BAA";
        findPermutations(input.toCharArray(), 0);
    }
}
