import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 *
 * aaa
 *
 * a|a|a
 * aa|a
 * a|aa
 * aaa
 *
 * 2 ^(3 -1) = 2^2 = 4
 *
 * resources/PalindromicDecompositionOfString.png
 *
 * So for string of length n, there will be 2 ^ (n - 1) palindromic decomposition and for each it will take n to check for palindrome
 * So Time Complexity = O(2 ^ (n - 1) * n)
 *
 * Auxillary Space: O(2 ^ (n - 1) * n)
 * Because there will be 2 ^(n - 1) palindromic decomposition and each will be of length n
 *
 * Time Complexity: O(n ^ n)
 * Space Complexity: O(n)
 *
 * Degree = n
 * height = n
 * Branching factor or degree is not constant it could be the length of string or 0
 * Because degree goes from 5 to 4 to 3 to 1 and no of leaves will be n!
 *
 * *****************************************************************************************
 *
 * We have provided two solutions:
 1) Solution Using Recursion: other_solution.cpp
 2) Solution Using Dynamic Programming: optimal_solution.cpp

 Try to solve the problem using both the approaches.
 First look at the solution using recursion and then solution using dynamic programming.
 In solution using dynamic programming we have pre-calculated is_palindrome array, but in solution using recursion we have not. But you should do it in recursive solution also. (To make code readable and easy to understand, we have not pre-calculated in recursive solution.)

 Time Complexity Of The Optimal Solution:
 O((2^(n - 1)) * n).
 Consider input s = "aaaaaaaaaaaaaaaaaaaa" (20 times.)
 For strings like this, every substring will be a palindrome, hence total number of palindromic decomposition in worst case will be 2^(n - 1).
 (Try s = "aaa" and it will be more clear why 2^(n - 1).)
 We will store 2^(n - 1) palindromic decomposition and length of each will be O(n) hence time complexity will be O((2^(n - 1)) * n).

 Auxiliary Space Used Of The Optimal Solution:
 O((2^(n - 1)) * n).
 In answer array we will store all 2^(n - 1) palindromic decomposition of length O(n).
 Also is_palindrome array is O(n ^ 2) so O((2^(n - 1)) * n) + O(n ^ 2) -> O((2^(n - 1)) * n).

 Space Complexity Of The Optimal Solution:
 O((2^(n - 1)) * n).

 Auxiliary space used is O((2^(n - 1)) * n) and input size is O(n) hence O((2^(n - 1)) * n) + O(n) -> O((2^(n - 1)) * n).

 resources/PalindromeForAllSubstrings.png

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

    private static String[] generate_palindromic_decomposition(String s) {

        List<String> result = new ArrayList<>();

        if (s.isEmpty()) {
            return result.toArray(new String[0]);
        }

        palindromicDecomposition(s, 0, result, "");

        return result.toArray(new String[0]);
    }

    private static void palindromicDecomposition(String s, int start, List<String> result, String res) {

        //Base Case
        if (start == s.length()) {

            result.add(res.substring(0, res.length() - 1));

        } else { //Recursive Case

            for (int i = start; i < s.length(); i++) {

                //if (isPalindrome(s, start, i)) {
                if (isPalindrome[start][i]) {

                    palindromicDecomposition(s, i + 1, result, res + s.substring(start, i + 1) + "|");
                }
            }
        }
    }

    /**
     * PreCalculate isPalindrome array for all possible substrings
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     * This will not affect overall time complexity and space complexity of O(2 ^ (n - 1) * n)
     */
    private static boolean[][] isPalindrome;

    private static void isPalindromeForAllSubstrings(String s) {

        int n = s.length();
        isPalindrome = new boolean[s.length()][s.length()];

        for (int length = 1; length <= n; length++) {
            for (int start = 0; start < n; start++) {
                int stop = start + length - 1;

                if (stop >= n) {
                    break;
                }

                //For 1 char and 2 char
                if (length <= 2) {
                    isPalindrome[start][stop] = s.charAt(start) == s.charAt(stop);
                } else if (s.charAt(start) == s.charAt(stop)) {

                    /*
				        When first and last characters are same then whether string is a palindrome or
				        not, depends on the inner string.
				        For example:
				        1) In "abcba", 'a' = 'a' so string is a palindrome or not will depend on "bcb".
				        2) In "abcca", 'a' = 'a' so string is a palindrome or not will depend on "bcc".
				    */
                    isPalindrome[start][stop] = isPalindrome[start + 1][stop - 1];
                }
            }
        }
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

        System.out.println("The plaindromic substring decomposition is: ");
        isPalindromeForAllSubstrings("aaa");
        System.out.println(Arrays.toString(generate_palindromic_decomposition("aaa")));
        System.out.println();

    }
}
