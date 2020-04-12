import java.util.ArrayList;
import java.util.List;

/**
 * Knight's tour!
 * Given a phone keypad as shown below:
 *
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * - 0 -

 * How many different phone numbers of given length can be formed starting from the given digit?
 * The constraint is that the movement from one digit to the next is similar to the movement of the Knight in a chess game.
 * For eg. if we are at 1 then the next digit can be either 6 or 8 if we are at 6 then the next digit can be 1, 7 or 0.

 * Repetition of digits are allowed - 1616161616 is a valid number.
 * The problem requires us to just give the count of different phone numbers and not necessarily list the numbers.
 * Find a polynomial-time solution, based on Dynamic Programming.

 * Input Format:
 * You will be given 2 integer values, startdigit and phonenumberlength, denoting starting digit and the required length respectively.

 * Output Format:
 * Return a long integer count, denoting the total number of valid phone numbers that can be formed.
 * If startdigit = 1 and phonenumberlength = 3, then input should be:
 * 1
 * 3

 * Output Format:
 * For input startdigit = 1 and phonenumberlength = 3, output will be:
 * 5
 *
 * Constraints:
 *     0 <= startdigit <= 9
 *     1 <=  phonenumberlength <= 30

 * Sample Input 1:
 * startdigit = 1
 * phonenumberlength = 2
 *
 * Sample Output 1:
 * 2
 *
 * Explanation 1:
 * Two possible numbers of length 2: 16, 18
 *
 * Sample Input 2:
 * startdigit = 1
 * phonenumberlength = 3
 *
 * Sample Output 2:
 * 5
 *
 * Explanation-2:
 * Possible numbers of length 3: 160, 161, 167, 181, 183
 * **************************************************************************************************************************
 *
 * Solution
 * Recursive solution
 * Think of it recursively like this: How many numbers can I construct using 10 digits starting from 1?

 * Answer is
 * [number of 9-digit numbers starting from 8] + [number of 9-digit numbers starting from 6]

 * So how many "9-digit numbers starting from 8" are there? Well,
 * [number of 8-digit numbers starting from 1] + [number of 8-digit numbers starting from 3]

 * Hence, we can recursively build this as
 * f(len, i) = sum(f(len-1, knight neighbours of i))
 *
 * Base case would be f(0, num), where
 * f(0, num) = 1, if num = starting digit
 * f(0, num) = 0, otherwise
 *
 * Optimal solution
 * We can memoize the recurrence relationship mentioned above or build an iterative version for the same problem.
 * Space Complexity: O(phonenumberlength)
 * Time Complexity: O(phonenumberlength)
 *
 * Time Complexity: O(8^n)
 * Space Complexity: O(n) where n = phoneNumberLength
 *
 * DP
 *
 * Time Complexity: O(10)(n)
 * Space Complexity: O(10)(n)
 *
 * resources/KnightTourPhoneNumber.jpg
 */
public class KnightTourPhoneNumber {

    private static int moves = 8;
    private static int[] rows = {-2, -2, -1, -1, 1, 1, 2, 2};
    private static int[] cols = {-1, 1, 2, -2, 2, -2, 1, -1};
    private static int[][] grid = {{1, 2, 3},
                                   {4, 5, 6},
                                   {7, 8, 9},
                                   {-1, 0, -1}};

    private static long numPhoneNumbersRecursive(int startDigit, int phoneNumberLength) {

        return numPhoneNumbersRecursiveHelper(startDigit, phoneNumberLength - 1, "1");
    }

    private static long numPhoneNumbersRecursiveHelper(int startDigit, int phoneNumberLength, String soFar) {

        //Base Case
        if(phoneNumberLength == 0) {
            System.out.println(soFar);
            return 1;
        }

        int[] startRowCol = getStartRowCol(startDigit);
        List<int[]> validNeighbors = getValidNeighbors(startRowCol);

        long count = 0;
        for(int[] neighbor: validNeighbors) {
            count = count + numPhoneNumbersRecursiveHelper(grid[neighbor[0]][neighbor[1]], phoneNumberLength - 1, soFar + grid[neighbor[0]][neighbor[1]]);
        }
        return count;
    }

    private static long numPhoneNumbersDp(int startDigit, int phoneNumberLength) {

        //Identify the DP table
        //2 params changing startDigit, phoneNumberLength
        long[][] dp = new long[10][phoneNumberLength];

        //Initialize the dp table
        //First col = 0
        for(int i = 0; i < 10; i++) {
            dp[i][0] = 1;
        }

        //Traversal direction
        //Recursion - 2 to 0 for phoneNumberLength
        // 1 to n - 1 since 0 is already covered in base case
        //startDigit - 0 to 9
        for(int col = 1; col < phoneNumberLength; col++) { //phoneNumberLength
            for(int row = 0; row < 10; row++) { //digit

                //Populate dp table
                int[] startRowCol = getStartRowCol(row);
                List<int[]> validNeighbors = getValidNeighbors(startRowCol); //You can also store the neighbors for each startDigit in the hashMap

                long count = 0;
                for(int[] neighbor: validNeighbors) {
                    count = count + dp[grid[neighbor[0]][neighbor[1]]][col - 1];
                }
                dp[row][col] = count;
            }
        }
        return dp[startDigit][phoneNumberLength - 1]; //that's where recursion started (1, 2)
    }

    private static List<int[]> getValidNeighbors(int[] startRowCol) {

        List<int[]> result = new ArrayList<>();

        for(int i = 0; i < moves; i++) {
            int nRow = startRowCol[0] + rows[i];
            int nCol = startRowCol[1] + cols[i];

            if(nRow >= 0 && nRow < grid.length && nCol >= 0 && nCol < grid[0].length && grid[nRow][nCol] != -1) {
                int[] neighbor = {nRow, nCol};
                result.add(neighbor);
            }
        }
        return result;
    }

    private static int[] getStartRowCol(int startDigit) {
        int[] startRowCol = new int[2];

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == startDigit) {
                    startRowCol[0] = i;
                    startRowCol[1] = j;
                    return startRowCol;
                }
            }
        }
        return startRowCol;
    }

    public static void main(String[] args) {
        System.out.println("The number of phone numbers generated: " + numPhoneNumbersRecursive(1, 3));
        System.out.println("The number of phone numbers generated using Dp " + numPhoneNumbersDp(1, 3));
    }
}
