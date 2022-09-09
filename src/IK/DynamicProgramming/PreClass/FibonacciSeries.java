package IK.DynamicProgramming.PreClass;

/**
 * Fibonacci Series = 0, 1, 1, 2, 3, 5, 8, 13, 21
 *
 * Approach 1:
 * 1) Base Case: If n == 0 or n == 1 return n
 * 2) Create array of size n + 1
 * 3) Prepopulate base cases
 * 4) Iterate from i = 2 to n
 * 5) fib[i] = fib[i - 1] + fib[i - 2]
 * 6) Return fib[n]
 *
 * TC: O(n)
 * SC: O(n)
 *
 * Approach 2:
 * We need to store only previous two values, so we can reuse the space by recycling.
 * So we only need array of size 3.
 *
 * 1) Base Case: If n == 0 or n == 1, return n
 * 2) Create array of size 3
 * 3) Iterate from i = 2 to n
 * 4) If i % 3 = 0, store it at index 0
 * 5) If i % 3 = 1, store it at index 1
 * 6) If i % 3 = 2, store it at index 2
 * 7) Return fib[n % 3]
 *
 * TC: O(n)
 * SC: O(1)
 */
public class FibonacciSeries {

    private static int fibonacci(int n) {
        //Base Case
        if(n == 0 || n == 1) {
            return n;
        }

        int[] fib = new int[n + 1];

        fib[0] = 0;
        fib[1] = 1;

        for(int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        return fib[n];
    }

    private static int fibonacciOptimal(int n) {

        //Base Case
        if(n == 0 || n == 1) {
            return n;
        }

        int[] fib = new int[3];
        fib[0] = 0;
        fib[1] = 1;

        for(int i = 2; i <= n; i++) {
            fib[i % 3] = fib[(i - 1) % 3] + fib[(i - 2) % 3];
        }

        return fib[n];
    }

    public static void main(String[] args) {
        System.out.println("The fibonacci of 6 is " + fibonacci(6));
        System.out.println("The fibonacci of 6 with optimal space is " + fibonacci(6));
    }

}
