package IK.Recursion.PreClass;

/**
 * The fibonacci sequence is a famous bit of mathematics, and it happens to have a recursive definition.
 * The first two values in the sequence are 0 and 1 (essentially 2 base cases).
 * Each subsequent value is the sum of the previous two values, so the whole sequence is: 0, 1, 1, 2, 3, 5, 8, 13, 21 and so on.
 * Define a recursive fibonacci(n) method that returns the nth fibonacci number, with n=0 representing the start of the sequence.


 fibonacci(0) → 0
 fibonacci(1) → 1
 fibonacci(2) → 1

 Time Complexity: O(2 ^ (n - 1))
 Constant work in single instance of function
 So time complexity = no of nodes = O(2 ^ height)

 If there is O(K) work in base case then all leaf will do O(K) work and non leaf node will do O(1) work
 So total TC = O(1 * non leaf nodes) + O(k * leaf nodes)

 In a complete binary tree, total no of nodes = 2 ^ h = 2 ^ 3 = 8
 No of leaf nodes = 2 ^ h - 1 = 2 ^ 2 = 4
 Non leaf nodes = 2 ^ h - 1 = 4

 Space Complexity: O(n) = height of tree - Auxillary space

 Any recursive code can be modeled iteratively if you use your own stack

   5
 /   \
 4   3
 /\   /\
 3 2  2 1
 /\   /\   /\  /\
 2 1 1 0   1 0
/\
 1 0

 Preorder traversal tells you the execution sequence.

 */
public class Fibonacci {

    private static int fibonacci(int n) {
        //Base Case
        if (n == 0 || n == 1) {
            return n;
        } else { //Recursive case
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    // Think of fibonacci series as additive series 0, 1, 1, 2, 3, 5, 8, 13 ...
    //If we are calculating fib(n), we make recursive call to n - 1 with two base cases removing one
    //So it's not recursion that makes time complexity exponential
    private static int fibonacciLinear(int n, int b1, int b2) {
        //Base Case
        if(n == 0) {
            return b1;
        } else {
            return fibonacciLinear(n - 1, b2, b1 + b2);
        }
    }

    public static void main(String[] args) {
        System.out.println("0th fibonacci number is: " + fibonacci(0));
        System.out.println("1st fibonacci number is: " + fibonacci(1));
        System.out.println("2nd fibonacci number is: " + fibonacci(2));
        System.out.println("7th fibonacci number is: " + fibonacci(7));

        System.out.println("0th fibonacci number is: " + fibonacciLinear(0, 0, 1));
        System.out.println("1st fibonacci number is: " + fibonacciLinear(1, 0, 1));
        System.out.println("2nd fibonacci number is: " + fibonacciLinear(2, 0, 1));
        System.out.println("7th fibonacci number is: " + fibonacciLinear(7, 0, 1));
    }
}
