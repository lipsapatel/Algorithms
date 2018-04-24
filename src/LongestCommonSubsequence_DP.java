/**
 * Given two string subsequences, write an algorithm to find the length of longest common
 * subsequence present in both of them.
 *
 * What is Longest Common Subsequence?
 * A longest common subsequence is the sequence that appears in the same relative order,
 * but not necessarily contiguous in both string.
 *
 * Example:
 *
 * String A = "acbaed"
 * String B = "abcadf"
 *
 * Longest Common Subsequence: Length: 4 "acad"
 *
 * Approach:
 *
 * Recursion:
 *
 * Start by comparing the string in reverse order, one character at a time.
 *
 * Now we have two cases:
 *
 * 1) Both characters are same
 * Add 1 to the result and make recursive call after removing last character from both the string.
 *
 * 2) If both characters are not same
 * Remove last character from string1 and make recursive call
 * Remove last character from string2 and make recursive call
 * Return max from both of them
 *
 * Example:
 *
 * StringA = "ABCD"
 * StringB = "AEBD"
 *
 * LCS("ABCD","AEBD") = 1 + LCS("ABC", "AEB")
 *
 * StringA = "ABCDE"
 * StringB = "AEBDF"
 *
 * LCS("ABCDE", "AEBDF") = Max(LCS("ABCDE", "AEBD"), LCS("ABCD", "AEBDF"))
 *
 * In the given string of length n, there can be 2^n subsequence.
 * If we do by recursion, the Time Complexity: O(2^n)
 *
 * resources/LongestCommonSubsequenceRecursionTree.png
 *
 * Dynamic Programming:
 *
 * 1) We will solve it using Top-Down approach
 * 2) Store the solution of subproblem in array and use it whenever needed.
 * 3) This technique is called memoization.
 */
public class LongestCommonSubsequence_DP {

    private static int LCS_UsingRecursion(String A, String B) {

        if (A.length() == 0 || B.length() == 0) {
            return 0;
        }

        int lengthA = A.length();
        int lengthB = B.length();

        //check if last characters are same
        if (A.charAt(lengthA - 1) == B.charAt(lengthB - 1)) {

            //Add 1 and remove last character and make recursive call
            return 1 + LCS_UsingRecursion(A.substring(0, lengthA - 1), B.substring(0, lengthB - 1));
        } else {

            return Math.max(LCS_UsingRecursion(A.substring(0, lengthA - 1), B),
                    LCS_UsingRecursion(A, B.substring(0, lengthB - 1)));
        }
    }

    private static int LCS_UsingDP(char[] A, char[] B) {

        int[][] LCS = new int[A.length + 1][B.length + 1];
        String[][] solutionArray = new String[A.length + 1][B.length + 1];

        //If A is null then LCS is 0
        for (int i = 0; i <= B.length; i++) {
            LCS[0][i] = 0;
            solutionArray[0][i] = "0";
        }

        //If B is null then LCS = 0
        for (int i = 0; i <= A.length; i++) {
            LCS[i][0] = 0;
            solutionArray[i][0] = "0";
        }

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {

                if (A[i - 1] == B[j - 1]) {
                    LCS[i][j] = 1 + LCS[i - 1][j - 1];
                    solutionArray[i][j] = "diagonal";
                } else {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);

                    if (LCS[i][j] == LCS[i - 1][j]) {
                        solutionArray[i][j] = "top";
                    } else {
                        solutionArray[i][j] = "left";
                    }
                }
            }
        }

        //Below is the code to print the result
        String answer = "";
        int a = A.length;
        int b = B.length;

        while(solutionArray[a][b] != "0") {

            if (solutionArray[a][b] == "diagonal") {
                answer = A[a - 1] + answer;
                a--;
                b--;
            } else if (solutionArray[a][b] == "left") {
                b--;
            } else if (solutionArray[a][b] == "top"){
                a--;
            }
        }
        System.out.println("The longest common subsequence string is " + answer);

        //Printing the LCS array
        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= B.length; j++) {
                System.out.print(" " + LCS[i][j]);
            }
            System.out.println();
        }

        return LCS[A.length][B.length];
    }

    public static void main(String[] args) {

        String A = "ACB";
        String B = "ABC";

        System.out.println("The length of longest common subsequence using recursion is " +
                            LCS_UsingRecursion(A, B));

        System.out.println("The length of longest common subsequence using DP is " +
                            LCS_UsingDP(A.toCharArray(), B.toCharArray()));
    }
}
