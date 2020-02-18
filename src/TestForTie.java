/**
 * You are given weight of each person.
 * Is it possible to divide them into two groups such that both the groups have same total weight.
 *
 * For Example:
 *
 * input = {1, 2, 3, 4, 5, 7}
 * Output = true
 *
 * The two groups are {7, 4} and {1, 2, 3, 5}
 *
 * Approach
 *
 * 1) Find the total weight if it's even then do the recursion subset approach
 * 2) If it's odd return false
 *
 * resources/TestForTie.png
 *
 * Recursive Approach
 *
 * TC = O(2 ^ n)
 * SC = O(n) where n = length of array
 *
 * DP Approach
 *
 * TC = n (array length) * (sum)
 * SC = O(array length)(sum)
 *
 */
public class TestForTie {

    private static boolean testForTieRecursive(int[] array) {

        int sum = computeSum(array);

        //Odd
        if(sum % 2 != 0) {
            return false;
        }

        return testForTieRecursiveHelper(array, sum/2, 0);
    }

    private static int computeSum(int[] array) {
        int sum = 0;

        for(int i = 0; i < array.length; i++) {
            sum = sum + array[i];
        }
        return sum;
    }

    private static boolean testForTieRecursiveHelper(int[] array, int sum, int index) {
        //Base Case
        if(sum == 0) {
            return true;
        }

        if (index == array.length) {
            return false;
        }

        //Avoid negative values
        boolean result = false;

        if(array[index] <= sum) {
            //Include
            result = testForTieRecursiveHelper(array, sum - array[index], index + 1);
        }
        return result || testForTieRecursiveHelper(array, sum, index + 1); //exclude
    }

    private static boolean testForTieDP(int[] array) {

        int sum = computeSum(array);

        //odd
        if(sum % 2 != 0) {
            return false;
        }

        int targetSum = sum/2;

        boolean[][] dp = new boolean[array.length + 1][targetSum + 1];

        //For sum = 0, we can get without any subset and it's always true
        //Initialize the first column as true
        for (int i = 0; i <= array.length; i++) {
            dp[i][0] = true;
        }

        //For empty subset, it's always false except the sum = 0
        //Initialize last row with false except the sum = 0 column
        for (int i = 1; i <= targetSum; i++) {
            dp[array.length][i] = false;
        }

        //Traversal Direction - For index = array.length - 0
        //Sum will be from  0 to targetSum
        for(int i = array.length - 1; i >= 0; i--) {
            for(int j = 1; j <= targetSum; j++) {

                boolean result = false;
                if (array[i] <= j) {
                    //include
                    result = dp[i + 1][j - array[i]];
                }
                dp[i][j] = result || dp[i + 1][j];
            }
        }
        return dp[0][targetSum];
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 7};
        System.out.println("We can divide into two groups of equal weight: " + testForTieRecursive(array));

        System.out.println("We can divide into two groups of equal weight: " + testForTieDP(array));
    }
}
