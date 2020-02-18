/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.

 Return the minimum cuts needed for a palindrome partitioning of s.

 Example:

 Input: "aab"
 Output: 1
 Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.

Input: abcbm
 Output = 2

 Approach
 1) Construct the palindrome grid for each substring
 2) 1d DP table which store the count of each string (which starts at 0)
 3) For each substring ending in index i, find all the substrings which starts at different positions and find if it's palindrome, if it is
 then that's consider one cut so  1 + (from cut dp table, find the min cut for start - 1)
 4) Take minimum cuts outs of all those for string ending at index i and update the dp table cut

 Time Complexity: O(n^2)
 Space Complexity: O(n^2)

 resources/PalindromicPartitioningMinCut.jpg
 */
public class PalindromicPartitioningMinCut {

    private static int findMinCut(String s) {

        //Create palindrome grid
        boolean[][] isPalindrome = createPalindromeGrid(s);

        //1d DP table
        int[] cut = new int[s.length()];

        for (int end = 0; end < s.length(); end++) {
            //By default we need end cuts from 0 to end
            int minCut = end;

            for(int start = 0; start <= end; start++) {

                if(isPalindrome[start][end]) {

                    if(start == 0) {
                        //whole string is palindrome
                        minCut = Math.min(minCut, 0);
                    } else {
                        minCut = Math.min(minCut, 1 + cut[start - 1]); //Update minCut with cut[start - 1]
                    }
                }
            }
            cut[end] = minCut;
        }
        return cut[s.length() - 1];
    }

    private static boolean[][] createPalindromeGrid(String s) {
        boolean[][] grid = new boolean[s.length()][s.length()];

        for(int start = s.length() - 1; start >= 0; start--) {
            for(int end = start; end < s.length(); end++) {

                if(s.charAt(start) == s.charAt(end)) {

                    //If the length of string is 1, 2 or 3 characters then the string is palindrome
                    // If the string leaving the first and last char is palindrome, then the string is palindrome
                    if(end - start <= 2 || grid[start + 1][end - 1]) {
                        grid[start][end] = true;
                    }
                }
            }
        }
        return grid;
    }

    public static void main(String[] args) {
        String input = "abcbm";
        System.out.println("Minimum number of cuts: " + findMinCut(input));

        String input1 = "nitin";
        System.out.println("Minimum number of cuts: " + findMinCut(input1));

        String input2 = "aab";
        System.out.println("Minimum number of cuts: " + findMinCut(input2));
    }
}
