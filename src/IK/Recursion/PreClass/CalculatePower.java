package IK.Recursion.PreClass;

/**
 * Calculate n^k
 *
 * Iterative Approach
 * 1) Iterate from i = 1 to k
 * 2) Store result * n in result
 * 3) Return result
 *
 * Time Complexity: O(k)
 * Space Complexity: O(1)
 *
 * Recursive Approach
 * 1) Base case: Return 1 if k == 0
 * 2) return n * CalculatePower(n, k - 1)
 *
 * Time Complexity: O(k)
 * Space Complexity: O(k) - call stack space
 */

public class CalculatePower {

    private static int calculatePowerIterative(int n, int k) {
        int result = 1;

        for(int i = 1; i <= k; i++) {
            result = result * n;
        }
        return result;
    }

    private static int calculatePowerRecursive(int n, int k) {
        //Base Case
        if(k == 0) {
            return 1;
        }

        return n * calculatePowerRecursive(n , k - 1);
    }

    public static void main(String[] args) {
        int n = 3;
        int k = 4;

        System.out.println("Iterative: " + calculatePowerIterative(n, k));
        System.out.println("Recursive: " + calculatePowerRecursive(n, k));
    }
}
