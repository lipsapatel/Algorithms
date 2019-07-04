import java.util.Arrays;

/**
 * Print all the permutations of char array where first letters are consonants
 * and not vowels.
 *
 * resources/PermutationFirstTwoConsonants.png
 *
 * Time Complexity = O(n!)
 */
public class PermutationFirstTwoConsonants {

    private static void printPermutation(char[] input, int left) {

        //Base Case
        if (left == input.length) {

            //Print only 2 such permutations
            System.out.println(Arrays.toString(input));

            return;
        } else { //Recursive case

        for (int i = left; i < input.length; i++) {

            if (((left < 2 && !isVowel(input, i)) || left > 1)) {
                swap(input, left, i);
                printPermutation(input, left + 1);
                swap(input, left, i);
            }
        }
    }
}

    private static boolean isVowel(char[] input, int current) {

        if (input[current] == 'a' || input[current] == 'e' || input[current] == 'i' || input[current] == 'o' || input[current] == 'u') {
            return true;
        }
        return false;
    }

    private static void swap(char[] input, int left, int i) {
        char temp = input[left];
        input[left] = input[i];
        input[i] = temp;
    }

    public static void main(String[] args) {
        char[] input = {'a', 'b', 'c', 'd'};
        printPermutation(input, 0);
    }
}
