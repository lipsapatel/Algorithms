/**
 * Dynamic Programming - Stairs Climbing puzzle.
 *
 * A child is climbing up the stairs with n steps and can hop either 1 step, 2 steps or 3 steps
 * at a time.
 *
 * Implement the method to count how many possible ways the child can jump up the stairs.
 *
 * Number of stairs = 0
 * Number of ways = 0
 *
 * Number of stairs = 1
 * Number of ways = 1
 *
 * Number of stairs = 2
 * Number of ways = 2 ({11}, {2})
 *
 * Number of stairs = 3
 * Number of ways = 4 ({11}, {12}, {21}, {3})
 *
 * Number of stairs = 4
 * Number of ways = 8 ({111}, {22}, {121}, {31}, {13}, {4}, {211}, {112})
 *
 * Number of stairs = 5
 * Number of ways = 15 ({1111}, {5}, {23}, {32}, {221}, {122}, {212}, {2111}, {1112},
 *                      {113}, {311}, {1121}, {1211}, {41}, {14})
 *
 * Say child has to take n steps.
 * At every step child has 3 options, to take 1 step, 2 steps and 3 steps
 * If a child takes 1 step, then the number of ways to complete is (n-1) + 1
 * If a child takes 2 steps, then the number of ways to complete is (n-2) + 1
 * If a child takes 3 steps, then the number of ways to complete is (n-3) + 1
 *
 * So the total number of ways to complete n steps = No of ways to complete (n-1) steps +
 *                                                   No of ways to complete (n-2) steps +
 *                                                   No of ways to complete (n-3) steps + 1
 *
 * Using Recursion
 *
 * If we use recursion then all the sub-problems will be calculate repeatedly.
 *
 * public int possibleWaysToClimbStairs(int n) {
 *     if (n < 1) {
 *         return 0;
 *     }
 *
 *     return 1 + possibleWaysToClimbStairs(n - 1) + possibleWaysToClimbStairs(n - 2) +
 *                possibleWaysToClimbStairs(n - 3);
 *
 * }
 *
 * Dynamic Programming
 *
 * We will solve it using Top-Down approach
 * We need to store the solutions for sub problems in an array.
 *
 * Time Complexity: O(logn)
 */
public class PossibleWaysToClimbStairs {

    private static int possibleWaysToClimbStairs(int n, int[] subProblemArray) {

        if (n < 1) {
            return 0;
        }

        if (subProblemArray[n] > 0) {
            return subProblemArray[n];
        }

        subProblemArray[n]  = 1 + possibleWaysToClimbStairs(n - 1, subProblemArray) +
                                possibleWaysToClimbStairs(n - 2, subProblemArray) +
                                possibleWaysToClimbStairs(n - 3, subProblemArray);

        return subProblemArray[n];
    }

    public static void main(String[] args) {

        int n = 3;
        int[] subProblemArray = new int[n + 1];

        System.out.println("The possible ways to climb 3 steps " + possibleWaysToClimbStairs(n , subProblemArray));

        int n1 = 4;
        int[] subProblemArray1 = new int[n1 + 1];

        System.out.println("The possible ways to climb 4 steps " + possibleWaysToClimbStairs(n1, subProblemArray1));

        int n2 = 5;
        int[] subProblemArray2 = new int[n2 + 1];

        System.out.println("The possbile ways to climb 5 steps " + possibleWaysToClimbStairs(n2, subProblemArray2));
    }
}
