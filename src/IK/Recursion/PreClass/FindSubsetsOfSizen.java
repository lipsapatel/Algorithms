package IK.Recursion.PreClass;

/**
 * Find the number of subsets of size n
 *
 * There are total 2^n subsets of size n.
 * If there are n blanks and for each blank there are 2 options either to include or exclude
 *
 * Recursive Approach
 * 1) Base Case: If n == 0 then return 1 because 2^0 = 1
 * 2) Recursive Case: return 2 * function(n - 1)
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Iterative Appraoch
 * 1) result = 1
 * 2) Iterate from 1 to n
 * 3) result = result * 2
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 */
public class FindSubsetsOfSizen {

    private static int subsetRecursion(int n) {
        //Base Case
        if (n == 0) {
            return 1;
        }
        return 2 * subsetRecursion(n - 1);
    }

    private static int subsetIterative(int n) {
        int result = 1;

        for (int i = 1; i <= n; i++) {
            result = 2 * result;
        }
        return result;
    }

    private static int subsetRecursionLog(int n) {
        //Base Case
        if (n == 0) {
             return 1;
        }

        int halfResult = subsetRecursionLog(n/2);

        if (n % 2 == 0) {
            return halfResult * halfResult;
        } else {
            return halfResult * halfResult * 2;
        }
    }

    public static void main(String[] args) {
        System.out.println("The subsets of size 4 using recursion is " +subsetRecursion(4));
        System.out.println("The subsets of size 5 using iteration is " +subsetIterative(5));
        System.out.println("The subset of size 4 using recursion in log n complexity is " + subsetRecursionLog(4));
        System.out.println("The subset of size 5 using recursion in log n complexity is " + subsetRecursionLog(5));
    }
}
