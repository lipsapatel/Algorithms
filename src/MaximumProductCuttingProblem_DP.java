/**
 * Given a rope of length n meters, write an algorithm to cut rope in such a way that
 * product of different length is maximum.
 *
 * Atleast one cut has to be made.
 *
 * Assume that the length of rope is more or equal to 2 meters since atleast one cut has to be made.
 *
 * This problem has advantage of dynamic programming over recursion.
 * By storing and reusing the results of subproblem will reduce the complexity.
 *
 * Example
 *
 * Rope length n = 2
 * Options = (1*1)
 * Maximum Product Cutting: (1*1) = 1
 *
 * Rope length n = 3
 * Options = (1*1*1, 2*1)
 * Maximum Product Cutting: (2*1) = 2
 *
 * Rope length = 4
 * Options = (1*1*1*1, 2*2, 1*3)
 * Maximum Product Cutting: (2*2) = 4
 *
 * Rope length = 5
 * Options = (1*1*1*1*1, 2*3, 2*2*1, 3*1*1, 2*1*1*1)
 * Maximum Product Cutting: (2*3) = 6
 *
 * Approach:
 *
 * Recursion
 *
 * 1) If n = 0 or 1 return 0
 * 2) Either this cut will produce maximum product or we need to make further cuts
 * max = Math.max(max,
 *                Math.max(i * (n - i), i * maximumProductCutting(n - i)));
 * 3) Time Complexity: O(2^n)
 *
 * resources/MaximumProductCuttingRecursion.png
 *
 * Dynamic Programming
 *
 * 1) We will solve in the Bottom-up manner - solving smaller problem first.
 * 2) We will store the solution of the sub-problems when getting solved for the first time, and use
 * it in future so that we don't have to solve it again.
 *
 */
public class MaximumProductCuttingProblem_DP {

    private static int maximumProductCutting_UsingRecursion(int ropeLength) {

        //Base case
        if (ropeLength == 0 || ropeLength == 1) {
            return 0;
        }

        int max = 0;
        for (int i = 2; i < ropeLength; i++) {

            //Either this cut will maximum product or we need further cut
            max = Math.max(max, Math.max(i * (ropeLength - i), i * maximumProductCutting_UsingRecursion(ropeLength - i)));
        }

        return max;
    }

    private static int maximumProductCutting_UsingDynamicProgramming(int ropeLength) {

        //Take array to store the results of subproblems
        int[] MPC = new int[ropeLength + 1];

        MPC[1] = 1;

        for (int i = 2; i < ropeLength + 1; i++) {
            int max = 0;
            for (int j = 1; j < i; j++) {
                max = Math.max(max, Math.max(j * (i - j),
                        j * MPC[i - j]));
            }
            MPC[i] = max;
        }

        return MPC[ropeLength];
    }

    public static void main(String[] args) {
        System.out.println("The maximum product using recursion is " + maximumProductCutting_UsingRecursion(10));

        System.out.println("The maximum product using dynamic programming is " + maximumProductCutting_UsingDynamicProgramming(10));
    }
}
