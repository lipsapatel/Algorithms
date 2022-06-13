package IK.Recursion.PreClass;

/**
 * what is Dynamic Programming?
 * Dynamic Programming is a technique to solve recursive problems in more efficient manner.
 * Many times in recursion we solve the sub-problems repeatedly.
 * In dynamic programming,we store the solution of these sub-problems
 * so that we do not have to solve them again, this is called Memoization.
 *
 * Dynamic Programming and memoization works together.
 *
 * So most of the problems are solved with 2 components of Dynamic Programming:
 * 1) Recursion - Solve the sub problems recursively
 * 2) Memoization - Store the solution of these sub-problems so that we don't have to solve
 * them again.
 *
 * IK.Recursion.PreClass.Fibonacci Series -
 * The current number is the sum of previous two numbers.
 *  Fib(0) = 0
 *  Fib(1) = 1
 *
 *  0 1 1 2 3 5 8 13 ...
 *
 *  Fib(N) = Fib(N - 1) + Fib(N - 2)
 *
 *  Recursive Solution:
 *
 *  private static int fibonacciSeries(int x) {
 *
 *      if (x == 0) {
 *          return 0;
 *      }
 *      if (x == 1) {
 *          return 1;
 *      } else {
 *          int fibonacci = fibonacciSeries(x - 1) + fibonacciSeries(x - 2);
 *          return fibonacci;
 *      }
 *  }
 *
 *  resources/FibonacciSeries.png
 *
 *  As you see in above image, while calculating fibonacciSeries(4) you need fibonacciSeries(3)
 *  and fibonacciSeries(2)
 *  For fibonacciSeries(3) you need fibonacciSeries(2) and fibonacciSeries(1)
 *
 *  But you notice fibonacciSeries(2) is calculated while calculating fibonacciSeries(4) and
 *  then you are again calculating while calculating fibonacciSeries(3).
 *
 *  So we are solving many sub-problems again and again.
 *
 *  Time Complexity:
 *  T(n) = T(n - 1) + T(n - 2) + 1 = 2^n = O(2^n)
 *
 *  Use Dynamic Programming - Memoization
 *
 *  Store the sub-problems result so that you don't have to calculate it again.
 *  So first check if the solution is already available, if yes then use it else calculate it
 *  and store it for future.
 *
 *  Bottom-up Approach
 *
 *  private static int fibonacciSeries(int x) {
 *
 *      int[] fibonacci = new int[x+1];
 *
 *      fibonacci[0] = 0;
 *      fibonacci[1] = 1;
 *
 *      for (int i = 0; i <= x; i++) {
 *          fibonacci[x] = fibonacci[x - 1] + fibonacci[x - 2];
 *      }
 *
 *      return fibonacci[x];
 *  }
 *
 *  Time Complexity: O(n)
 *  Space Complexity: O(n)
 *
 *  Two major properties of Dynamic Programming:
 *
 *  To decide whether the problem can be solved by applying Dynamic Programming we check for
 *  two properties.
 *  If problem has these two properties then we can solve that problem using Dynamic Programming:
 *
 *  1) Overlapping Sub-problems
 *  2) Optimal Substructure
 *
 *  Overlapping Sub-problems:
 *
 *  Overlapping sub-problems as the name suggests the sub-problems needs to be solved again and again.
 *  In recursion we solve those problems every time and in dynamic programming we solve these sub problems
 *  only once and store it for future use.
 *
 *  Optimal Substructure:
 *
 *  If a problem can be solved by using the solutions of the sub problems then we say problem has a Optimal
 *  Substructure Property.
 *
 *  Dynamic Programming Approaches:
 *
 *  Bottom Up
 *  Top down
 *
 *  private static int fibonacciSeries(int x) {
 *      int[] fibonacci = new int[x+1];
 *
 *      if (n == 0) {
 *          return 0;
 *      }
 *
 *      if (n == 1) {
 *          return 1;
 *      }
 *
 *      if (fibonacci[n] != 0) {
 *          return fibonacci[n];
 *      } else {
 *          fibonacci[n] = fibonacci[n-1] + fibonacc[n-2];
 *          return fibonacci[n];
 *      }
 *  }
 */
public class DynamicProgrammingFibonacciSeries {

    private static int fibonacciSeries(int x) {

        int[] fibonacci = new int[x+1];

        fibonacci[0] = 0;

        if (x > 0) {
            fibonacci[1] = 1;
        }

        for (int i = 2; i <= x; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }

        return fibonacci[x];
    }

    public static void main(String[] args) {

        System.out.println("The fibonacci Series for 0 is " + fibonacciSeries(0));
        System.out.println("The fibonacci Series for 1 is " + fibonacciSeries(1));
        System.out.println("The fibonacci Series for 2 is " + fibonacciSeries(2));
        System.out.println("The fibonacci Series for 3 is " + fibonacciSeries(3));
        System.out.println("The fibonacci Series for 4 is " + fibonacciSeries(4));
        System.out.println("The fibonacci Series for 5 is " + fibonacciSeries(5));
        System.out.println("The fibonacci Series for 6 is " + fibonacciSeries(6));
    }
}
