/**
 * Robbery
 * There are n houses built in a line, each of which contains some value in it.
 * A thief is going to steal the maximal value in these houses, but he cannot steal in two adjacent houses
 * because the owner of a stolen house will tell his two neighbors on the left and right side. What is the maximal stolen value?

 * For example, if there are four houses with values [6, 1, 2, 7], the maximal stolen value is 13, when the first and fourth houses are stolen.
 * You will be given an array of integer values, containing the value in each house.

 * Output Format:
 * Return an integer max, denoting the maximum possible robbery.
 *
 * For input n = 4 and values = [6, 1, 2, 7], output will be:
 * 13
 *
 * Constraints:
 *     1 <= n <= 10^5
 *     1 <= values[i] <= 10^4
 *
 * Sample Input 1:
 * values = [6, 1, 2, 7]
 *
 * Sample Output 1:
 * 13
 *
 * Explanation 1:
 * Steal from the first and the last house.
 *
 * Sample Input 2:
 * values = [1, 2, 4, 5, 1]
 *
 * Sample Output 2:
 * 7

 * Explanation 2:
 * Steal from the second and the fourth house.
 *
 * resources/HouseRobbery.jpg
 *
 * Approach:
 *
 * 1) Given n houses in a line.
 * 2) Robber can either rob the first house(include) or not rob the first house(exclude)
 * 3) If he robs the first house, then he needs to skip second and directly go to third.
 * 4) If he doesn't rob the first house, then he can go to second.
 *
 * Recursion:
 * Time Complexity = O(2^V) - where V = length of values array
 * Space Complexity: O(V)
 *
 * DP
 * Time Complexity: O(V)
 * Space Complexity: O(V)
 */
public class HouseRobbery {

    private static int maxStolenValueRecursion(int[] values) {
        return maxStolenValueRecursionHelper(values, 0);
    }

    private static int maxStolenValueRecursionHelper(int[] values, int i) {

        //Base Case
        if(i >= values.length) {
            return 0;
        }

        int include = values[i] + maxStolenValueRecursionHelper(values, i + 2);
        int exclude = maxStolenValueRecursionHelper(values, i + 1);

        return Math.max(include, exclude);
    }

    private static int maxStolenValueDp(int[] values) {
        //Identify the dp table
        int[] dp = new int[values.length + 2];

        //Initialize the dp table - Base Case
        dp[values.length] = 0;
        dp[values.length + 1] = 0;

        //Traversal direction
        for (int i = values.length - 1; i >= 0; i--) {

            //Populate dp table
            int include = values[i] + dp[i + 2];
            int exclude = dp[i + 1];
            dp[i] = Math.max(include, exclude);
        }

        return dp[0];
    }

    public static void main(String[] args) {
        int[] values = {6, 2, 1, 7};
        System.out.println("Max stolen value using recursion " + maxStolenValueRecursion(values));

        System.out.println("Max stolen value using dp " + maxStolenValueDp(values));
    }
}