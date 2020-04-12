/**
 * Count Ways To Reach The Nth Stair

 Problem Statement:
 There are n stairs, a person standing at the bottom wants to reach the top. He can climb a certain number of steps at once.
 For instance, the person can climb either 1 stair or 2 stairs at a time. Count the number of ways, the person can reach the top.

 Note: Solve the problem for general case i.e. for n stairs, and different kinds of steps that can be taken
 (e.g. instead of only 1 or 2 steps, it could be 2, 3 and 5 steps at a time).

 Input Format:
 There will be two arguments: steps and n.
 An array of integer steps of size m denotes the steps the person can climb at a time and an integer n denotes the total number
 of stairs to be climbed.

 Note: Answer is guaranteed to fit in long integer.

 Output Format:
 Return an integer ways, denoting the number of ways to reach the last staircase.

For input n = 7 and steps = [2, 3], output will be:
 3

 Constraints:
 1 <= m (size of the array steps) <= 100
 1 <= steps[i], n <= 10000

 Sample Input 1:
 n = 1
 steps = [1, 2]

 Sample Output 1:
 1

 Explanation 1:
 Only one way to reach = [{1}]

Sample Input 2:
 n = 2
 steps = [1, 2]

 Sample Output 2:
 2

 Explanation 2:
 Two ways to reach = [{1, 1}, {2}]

 Sample Input 3:
 n = 7
 steps = [2, 3]

 Sample Output 3:
 3

 Explanation 3:
 Three ways to reach = [{2, 2, 3}, {2, 3, 2}, {3, 2, 2}]

 Approach

 Recursive:

 1) Try all the possibilities from the steps array and calculate remaining steps.

 Time Complexity: O(k^n) where k = size of steps array
 Space Complexity: O(n)

 DP

 Time Complexity: O(n * k) where k = length of steps array
 Space Complexity: O(n)

 resources/WaysToReachNthStair.jpg - TC for DP is wrong in this image.
 */
public class WaysToReachNthStair {

    private static long countWaysToClimbRecursive(int[] steps, int n) {
        //Base Case
        if(n == 0) {
            return 1;
        }

        long count = 0;
        for(int i = 0; i < steps.length; i++) {
            if(n >= steps[i]) {
                count = count + countWaysToClimbRecursive(steps, n - steps[i]);
            }
        }
        return count;
    }

    private static long countWaysToClimbDp(int[] steps, int n) {
        //Identify the dp table - one changing param - n
        long[] dp = new long[n + 1];

        //Initialize the dp table
        //Base case: n == 0 return 1
        dp[0] = 1;

        //Traversal direction
        //Recursion - 7 to 0
        for (int i = 1; i <= n; i++) {

            //Populate dp table
            long count = 0;
            for (int j = 0; j < steps.length; j++) {
                if(i >= steps[j]) {
                    count = count + dp[i - steps[j]];
                }
            }
            dp[i] = count;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int[] steps = {2, 3};
        int n = 7;

        System.out.println("The number of ways to climb stairs are " + countWaysToClimbRecursive(steps, n));
        System.out.println("The number of ways to climb stairs using dp are " + countWaysToClimbDp(steps, n));
    }

}