package IK.DynamicProgramming.PreClass;

/**
 * A child is trying to climb a staircase. The maximum number of steps he can climb at a time is two.
 * That is he can climb either one step or two steps at a time.
 * If there are n steps in total, in how many different ways can he climb the staircase.
 *
 * Recursive Approach
 *
 * 1) Base case: If there is one stair there is one step and if there are 2 stairs there are two ways he can climb.
 * 2) Now I will climb 1 step and make recursive call to get ways to climb n - 1 steps.
 * 3) I will climb 2 steps and make recursive call to get ways to climb n - 2 steps.
 * 4) Add both of them
 *
 * TC: O(2^n)
 * SC: O(n)
 *
 * Dynamic Programming Approach
 * 1) Create integer array of size n + 1
 * 2) Add base case to the array
 * 3) Iterate from i = 3 to n
 * 4) dp[i] = dp[i - 1] + dp[i - 2]
 * 5) Return dp[n]
 *
 * TC: O(n)
 * SC: O(n)
 *
 * Dynamic Programming Approach with O(1) space
 * 1) Create array of size 4
 * 2) Add base cases
 * 3) Iterate from i = 3 to n
 * 4) if i % 3 == 1, index is 1
 * 5) If i % 3 == 2, index is 2
 * 6) If i % 3 == 0, index is 3
 * 7) Return dp[n%3] if n % 3 is 1 or 2 or else return dp[3]
 *
 * TC: O(n)
 * SC: O(1)
 */
public class ClimbNStairs {

    private static int climbNStairsRecursive(int n) {
        //Base Case
        if(n == 1 || n == 2) {
            return n;
        }

        //Edge Case
        if(n == 0) {
            return 0;
        }

        return climbNStairsRecursive(n - 1) + climbNStairsRecursive(n - 2);
    }

    private static int climbNStairsDP(int n) {

        //Base Case
        if(n == 1 || n == 2) {
            return n;
        }

        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    private static int climbNStairsDPOptimal(int n) {

        //Base Case
        if(n == 1 || n == 2) {
            return n;
        }

        int[] dp = new int[4];

        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i <= n; i++) {
            int x, y, z;

            if(i % 3 == 0) {
                x = 3;
            } else {
                x = i % 3;
            }

            if((i - 1) % 3 == 0) {
                y = 3;
            } else {
                y = (i - 1) % 3;
            }

            if((i - 2) % 3 == 0) {
                z = 3;
            } else {
                z = (i - 2) % 3;
            }

            dp[x] = dp[y] + dp[z];
        }

        if(n % 3 == 0) {
            return dp[3];
        } else {
            return dp[n % 3];
        }
    }

    public static void main(String[] args) {
        System.out.println("Climb 6 stairs recursive " + climbNStairsRecursive(6));
        System.out.println("Climb 6 stairs DP Optimal " + climbNStairsDPOptimal(6));
        System.out.println("Climb 6 stairs DP " + climbNStairsDP(6));
    }
}
