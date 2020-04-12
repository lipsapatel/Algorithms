import java.util.ArrayList;
import java.util.List;

/**
 * Word Wrap
 * Given a sequence of words (strings), and a limit on the number of characters that can be put in one line (line width),
 * put line breaks in the given sequence such that the lines are printed neatly.
 *
 * The word processors like MS Word do task of placing line breaks. The idea is to have balanced lines.
 * In other words, not have few lines with lots of extra spaces and some lines with small amount of extra spaces.
 * The extra spaces means spaces put at the end of every line.
 * Put line breaks such that the following total cost is minimized:
 *
 * Cost of a line = (Number of extra spaces in the line)^3
 * Total Cost = Sum of costs for all lines

 *     Each word belong to a single line and no word can be partially in one line and other part in a different line.
 *     Assume that the length of each word is smaller than or equal to the line width.
 *     Extra spaces means spaces put at the end of every line means white spaces between two words need to be ignored.
 *     Two words in a line will have exactly one space in between.
 *     Ignore extra white spaces at the end of last line.
 *     Note that the total cost function is not sum of extra spaces, but sum of cubes of extra spaces.
 *
 *  Return the minimum total cost min_cost.
 *
 * Constraints
 *     1 <= n <= 1000
 *     1 <= limit <= 15000
 *     1 <= length(words[i]) <= limit, for 0 <= i <= (n-1).
 *     words[i] can be composed of [a-z, A-Z], for 0 <= i <= (n-1).
 *
 * Sample Input 1
 * words = [abcdefghijkl, abcdefg, abcdefgh, abcdefghijklmnopqrstuv]
 * limit = 23
 *
 * Sample Output 1
 * result = 1674
 *
 * Explanation 1
 * Following arrangement of words in lines will have least cost:
 * Line1: “abcdefghijkl           ”
 * Line2: “abcdefg abcdefgh       ”
 * Line3: “abcdefghijklmnopqrstuv”
 * * Note that we need to ignore the extra white spaces at the end of last line. So, in the last line there will be 0 extra white spaces.
 *
 * Cost for this configuration:
 * (23 - 12)^3 + (23 - (7+1+8))^3 + (0)^3 = 1674
 *
 * Sample Input 2
 * words = [omg, very, are, extreme]
 * limit = 10
 *
 * Sample Output 2
 * result = 351
 *
 * Explanation 2
 * Following arrangement of words in lines will have least cost:
 * Line1: “omg very  ”
 * Line2: “are       ”
 * Line3: “extreme”
 *
 * Note that we need to ignore the extra white spaces at the end of last line. So, in the last line there will be 0 extra white spaces.
 *
 * Cost for this configuration:
 * (10 - (3+1+4))^3 + (10 - 3)^3 + (0)^3 = 351
 *
 * Approach:
 *
 * Recursion
 *
 * 1) Try adding 1 word on line 1
 * 2) Try adding 1st and 2nd word on line 1
 * 3) Try adding 1st, 2nd and 3rd word on line 1
 *
 * Get min of all three and add it's cube of it's own rem space.
 *
 * Time Complexity: O(n^n) ~ O(n!)
 * Space Complexity: O(n)
 *
 * DP:
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(n)
 *
 * resources/WordWrap.jpg
 * resources/WordWrapDp.jpg
 *
 * ***************************************************************************************************************
 * Solution
 * Why Greedy will not work?
   Greedy solution:
   Starting from 1st word, put as many sequence of words as possible within given limit of number of characters in a single line
 (let say limit). Let say we can put maximum of as much as i words in first line.
 Repeat the same process starting from (i+1)th word from new line. Compute the cost for each line and add them to find total cost min_cost.
 Here, greedy solution won't work.

 Eg. words = [abc, cd, e, ijklm], limit = 6
 So, list of length of words in list words will be = [3, 2, 1, 5]

 Here, greedy approach will put line break as follows:
 Line1: “abc cd”
 Line2: “e     ”
 Line3: “ijklm”

 Cost for this configuration:
 (6 - (3+1+2))^3 + (6-1)^3 +(0)^3 = 0+125+0 = 125

 While there exist more balanced configuration:
 Line1: “abc   ”
 Line2: “cd e  ”
 Line3: “ijklm”

 Cost for this configuration:
 (6 - 3)^3 + (6 - (2+1+1))^3 + (0)^3 = 27 + 8 + 0 = 35

 1) brute_force_solution.java
 Description:
 Here, we notice that each line must start with a word and each line contains some no. of sequence of words.
 Let say result(i) denotes total optimal cost if we had list of words [i,i+1,...,n-1].
 To find result(i), we notice that an optimal configuration of words in lines will be having first line starting from ith word
 and will have one or more following sequence of words within the constraint of line width.
 So, we will iterate over all possible first line configurations. i.e. first line can have sequence of words [i] OR
 [i,i+1] OR [i,i+1,i+2] OR [i,i+1,...,i+3] OR ...  [i,i+1,...,n-1] and for each configuration of first lines,
 all other successive remaining words in next some lines.

 result(i):
 min_cost = infinite
 for j:i to n-1:
 if a sequence of words from i to j can fit in a single line considering limit on line width:
 min_cost = min(min_cost, cost of current line of words from i to j + result(j+1))
 return min_cost

 result(0) is our required result.

 Time Complexity:
 O(2^n) where n is length of list words.
 In the above explanation, we notice that for computing result(i), we iterate over all j, i<=j<=n-1, and make the
 recursive call to find result(j) for all valid j. So,

 T(i) = i + (summation over j=i+1 to n-1(T(j)))

 It’s similar to problem, Let’s say we have set of n elements and we want to find number of all subsets then that will be 2^n. (https://www.mathsisfun.com/activity/subsets.html)
 Similarly we are doing that here for a word to considered in a line or not.

 By solving this we get complexity as O(2^n).

 Auxiliary Space Used:
 O(n) where n is length of list words.
 As we are calling recursive function and recursive stack will store data for n calls in worst case.

 Space Complexity:
 O(n*limit) where n is length of list words and limit is given limit on maximum number of characters in a line.
 For storing input, it will take O(n*limit) as we are storing n words which can have length equal to limit for each word
 and auxiliary space used is O(n) so, O(n*limit) + O(n) → O(n*limit).

 2) optimal_solution.java

 In the above explained brute force solution, result(i) is computed i times in total,
 once for each of 0 to (i-1). We see that this recursion has overlapping subproblems.
 This property of problem suggests that it can be solved using dynamic programming.

 We will calculate result(i) only one for every i and store it in dp array for future use. After simple modification in above pseudocode:

 dp = array of size n with all elements set to -1
 Here dp[i] = x denotes that the total minimized cost is x if we had sequence of words [i,i+1,...,n-1] and we put line breaks in a most balanced way.
 Now, we will iterate over given array from n-1 to 0, so, it will reduce recomputation.

 for i in [n - 1, 0]:
 min_cost = infinite
 for j: i to n-1:
 if a sequence of words from i to j can fit in a single line considering limit on line width:
 min_cost = min(min_cost, cost of current line of words from i to j + dp[j+1])
 dp[i] = min_cost
 return dp[0]

 result(0) is the required result.

 Time Complexity:
 O(n^2) where n is length of list words.

 In the above explained method, result(i) for each valid i will be computed only once, and for each valid i,
 we will have to traverse results result(j) for i<=j<=n-1, worst case time complexity will be n*n.

 So, time complexity will be O(n^2). (This time complexity is for the function solveBalancedLineBreaks ignoring the input.)

 Auxiliary Space Used:
 O(n) where n is length of list words.
 As we are storing computed results in a list of length n. So, to store that it will take O(n).

 Space Complexity:
 O(n*limit) where n is length of list words and limit is given limit on maximum number of characters in a line.
 For storing input, it will take O(n*limit) as we are storing n words which can have length equal to limit for each word and
 auxiliary space used is O(n) so, O(n*limit) + O(n) → O(n*limit).
 */
public class WordWrap {

    private static long solveBalancedLineBreaksRecursion(List<String> words, int limit) {

        return solveBalancedLineBreaksRecursionHelper(words, limit, 0);
    }

    private static long solveBalancedLineBreaksRecursionHelper(List<String> words, int limit, int i) {

        //Base Case
        if(i == words.size()) { //No words left so cost is 0
            return 0;
        }

        //All possible cuts
        long minCost = Long.MAX_VALUE;
        int remSpace = limit;

        for(int j = i; j < words.size(); j++) {

            if(remSpace >= words.get(j).length()) {

                remSpace = remSpace - words.get(j).length();
                long minCostForRest = solveBalancedLineBreaksRecursionHelper(words, limit, j + 1);

                if(j == words.size() - 1) { //Last word so minCost is 0 since the space after last word is not counted
                    minCost = 0;
                } else {
                    minCost = Math.min(minCost, (long) Math.pow(remSpace, 3) + minCostForRest);
                }

                //Subtract the space between two words
                remSpace = remSpace - 1;
            } else {
                break; //this breaks the for loop
            }
        }
        return minCost;
    }

    private static long solveBalancedLineBreaksDp(List<String> words, int limit) {
        //Identify the dp table - There's only one param changing i i = 0 to words.size()
        long[] dp = new long[words.size() + 1];

        //Initialize the dp table Base Case: if(i == words.size()) return 0
        dp[words.size()] = 0;

        //Traversal direction Recursion i = 0
        for(int i = words.size() - 1; i >= 0; i--) {
            //Populate the dp table

            //All possible cuts
            long minCost = Long.MAX_VALUE;
            int remSpace = limit;

            for(int j = i; j < words.size(); j++) {

                if(remSpace >= words.get(j).length()) {
                    remSpace = remSpace - words.get(j).length();

                    if(j == words.size() - 1) {
                        minCost = 0;
                    } else {
                        minCost = Math.min(minCost, (long)Math.pow(remSpace, 3) + dp[j + 1]);
                    }

                    remSpace = remSpace - 1;
                } else {
                    break;
                }
            }
            dp[i] = minCost;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        words.add("abcdefghijkl");
        words.add("abcdefg");
        words.add("abcdefgh");
        words.add("abcdefghijklmnopqrstuv");

        int limit = 23;

        System.out.println("The minimum cost using recursion is " + solveBalancedLineBreaksRecursion(words, limit));
        System.out.println("The minimum cost using DP is " + solveBalancedLineBreaksDp(words, limit));

        words = new ArrayList<>();
        words.add("omg");
        words.add("very");
        words.add("are");
        words.add("extreme");
        limit = 10;

        System.out.println("The minimum cost using recursion is " + solveBalancedLineBreaksRecursion(words, limit));
        System.out.println("The minimum cost using DP is " + solveBalancedLineBreaksDp(words, limit));
    }
}
