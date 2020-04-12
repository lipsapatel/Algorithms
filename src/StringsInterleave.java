/**
 * Strings Interleave
 * You're given three strings a, b and i. Write a function that checks whether i is an interleaving of a and b.
 *
 *     String i is said to be interleaving string a and b, if:
 *     len(i) = len(a) + len(b).
 *     i only contains characters present in a or b.
 *     i contains all characters of a. From a, any character a[index] should be added exactly once in i.
 *     i contains all characters of b. From b, any character b[index] should be added exactly once in i.
 *     Order of all characters in individual strings (a and b) is preserved.
 *
 * Strings can contain
 *
 *     Small alphabets - a-z
 *     Large alphabets - A-Z
 *     Numbers - 0-9
 *
 * Output Format:
 * Return a boolean if i is an interleaving of a and b.
 *
 * Constraints:
 * 0 <= len(a), len(b) <= 1000
 * 0 <= len(i) <= 2000
 *
 * Sample Input-1:
 * a = "123"
 * b = "456"
 * i = "123456"
 *
 * Sample Output-1:
 * True
 *
 * Sample Input-2:
 * a = "AAB"
 * b = "AAC"
 * i = "AAAABC"
 *
 * Sample Output-2:
 * True
 *
 * Sample Input-3:
 * a = "AAB"
 * b = "AAC"
 * i = "AAABAC"
 *
 * Sample Output-3:
 * True
 *
 * *************************************************************************************************
 *
 * Solution:
 *
 * Brute force recursive solution:
 * First character in i should match first character in a or first character in b.
 * If it matches first character in a, try matching second character in i with second character in a or first character in b
 * If it matches first character in b, try matching second character in i with second character in b or first character in a
 * If it matches none of them, terminate with a false
 *
 * Hence, keep recursing for the rest of the strings. This is an exponential solution, O(2^(len(a)+len(b)))
 * as you can either pick a character from a or a character from b.
 *
 * We can use DP to keep track of the already calculated values. Have a look at the solution for more detials.
 * Space Complexity: O(len(a) * len(b))
 * Time Complexity: O(len(a) * len(b))
 * **********************************************************************************************************
 *
 * Approach
 *
 * Recursion
 * Time Complexity: O(2 ^ (len(a) + len(b))
 * Space Complexity: O(len(a) + len(b))
 *
 * DP
 * Time Complexity: O(len(a) * len(b))
 * Space Complexity: O(len(a) * len(b))
 *
 * resources/StringsInterleaveDP.jpg
 * resources/StringsInterleaveRecursion.jpg
 */
public class StringsInterleave {

    public static boolean doStringsInterleave(String a, String b, String m) {

        if(a.length() + b.length() != m.length()) {
            return false;
        }

        return doStringsInterleaveRecursion(a, b, m, 0, 0);
    }

    private static boolean doStringsInterleaveRecursion(String a, String b, String m, int i, int j) {

        //Base case
        //When both string are empty
        if(i == a.length() && j == b.length()) {
            return true;
        }

        boolean left;
        if(i < a.length() && a.charAt(i) == m.charAt(i + j)) {
            left = doStringsInterleaveRecursion(a, b, m, i + 1, j);
        } else {
            left = false;
        }

        boolean right;
        if(j < b.length() && b.charAt(j) == m.charAt(i + j)) {
            right = doStringsInterleaveRecursion(a, b, m, i, j + 1);
        } else {
            right = false;
        }

        return left || right;
    }

    private static boolean doStringInterleaveDp(String a, String b, String m) {

        if(a.length() + b.length() != m.length()) {
            return false;
        }

        //Identify the dp table
        boolean[][] dp = new boolean[a.length() + 1][b.length() + 1];

        //Initialize the dp table - Base case
        dp[a.length()][b.length()] = true;

        //Traversal direction
        //Recursion i = 0 and j = 0
        // i = a.length() to 0 and j = b.length() to 0, do don't any thing for base case (skip it)
        for(int i = a.length(); i >= 0; i--) {
            for(int j = b.length(); j >= 0; j--) {

                //Skip the base case
                if(i == a.length() && j == b.length()) {
                    continue;
                }

                //Populate dp table
                boolean left;
                if(i < a.length() && a.charAt(i) == m.charAt(i + j)) {
                    left = dp[i + 1][j];
                } else {
                    left = false;
                }

                boolean right;
                if(j < b.length() && b.charAt(j) == m.charAt(i + j)) {
                    right = dp[i][j + 1];
                } else {
                    right = false;
                }

                dp[i][j] = left || right;
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        String a = "AAB";
        String b = "AAC";
        String m = "AAABAC";

        System.out.println("Strings Interleave " + doStringsInterleave(a, b, m));
        System.out.println("Strings Interleave using DP: " + doStringInterleaveDp(a, b, m));
    }
}
