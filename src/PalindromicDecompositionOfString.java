import java.util.ArrayList;
import java.util.Arrays;

/**
 * Print all the substrings which are palindrome
 *
 * For example:
 *
 * Input: nitin
 *  n i t i n
 *  n iti n
 *  nitin
 *
 *  geeks
 *  g e e k s
 *  g ee k s
 *
 * resources/PalindromicDecompositionOfString.png
 *
 * Time Complexity: O(n ^ n)
 * Space Complexity: O(n)
 *
 * Degree = n
 * height = n
 * Branching factor or degree is not constant it could be the length of string or 0
 * Because degree goes from 5 to 4 to 3 to 1 and no of leaves will be n!
 */
public class PalindromicDecompositionOfString {

    private static void printPalindromicDecomposition(String input, ArrayList<String> list, int start) {

        //Base Case when none of the string is remaining, print all the palindromic substring
        if (start == input.length()) {
            System.out.println(list);
        } else { //Recursive call

            for (int i = start; i < input.length(); i++) {

                if (isPalindrome(input, start, i)) {

                    //Add to list
                    list.add(input.substring(start, i + 1));

                    //Recur for remaining string
                    printPalindromicDecomposition(input, list, i + 1);

                    //Backtrack - remove last from list
                    list.remove(list.size() - 1);
                }
            }
        }
    }

    private static void printPalindromicDecomposition(String input, int start, String result) {

        //Base Case
        if (start == input.length()) {
            System.out.println(result);
        } else { //Recursive Case

            //All options
            for (int i = start; i < input.length(); i++) {

                //Check if it's palindrome
                if (isPalindrome(input, start, i)) {

                    //Recur for remaining string
                    printPalindromicDecomposition(input, i + 1, result + input.substring(start, i + 1) + " | ");
                }
            }
        }
    }

    private static boolean isPalindrome(String input, int start, int end) {

        while (start < end) {

            if (input.charAt(start) != input.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        String input = "nitin";
        System.out.println("The plaindromic substring decomposition is: ");
        printPalindromicDecomposition(input, new ArrayList<String>(), 0);
        System.out.println();
        printPalindromicDecomposition(input, 0, "");

        String input1 = "geeks";
        System.out.println("The plaindromic substring decomposition is: ");
        printPalindromicDecomposition(input1, new ArrayList<String>(), 0);
        System.out.println();
        printPalindromicDecomposition(input1, 0, "");

        String input2 = "aabbaa";
        System.out.println("The plaindromic substring decomposition is: ");
        printPalindromicDecomposition(input2, new ArrayList<String>(), 0);
        System.out.println();
        printPalindromicDecomposition(input2, 0, "");

        String input3 = "abcd";
        System.out.println("The plaindromic substring decomposition is: ");
        printPalindromicDecomposition(input3, new ArrayList<String>(), 0);
        System.out.println();
        printPalindromicDecomposition(input3, 0, "");
    }
}
