/**
 * A table composed of N x M cells, each having a certain quantity of apples, is given.
 * You start from the upper-left corner. At each step you can go down or right one cell.
 * Find the maximum number of apples you can collect.

 This problem is solved in the same way as other DP problems; there is almost no difference.

 First of all we have to find a state. The first thing that must be observed is that there are at most 2 ways we can come to a cell –
 from the left (if it’s not situated on the first column) and from the top (if it’s not situated on the most upper row).
 Thus to find the best solution for that cell, we have to have already found the best solutions
 for all of the cells from which we can arrive to the current cell.

 From above, a recurrent relation can be easily obtained:

 S[i][j]=A[i][j] + max(S[i-1][j], if i>0 ; S[i][j-1], if j>0) (where i represents the row and j the column of the table ,
 its left-upper corner having coordinates {0,0} ; and A[i][j] being the number of apples situated in cell i,j).

 S[i][j] must be calculated by going first from left to right in each row and process the rows from top to bottom,
 or by going first from top to bottom in each column and process the columns from left to right.

 Pseudocode:

 For i = 0 to N - 1
 For j = 0 to M - 1
 S[i][j] = A[i][j] +
 max(S[i][j-1], if j>0 ; S[i-1][j], if i>0 ; 0)

 Output S[n-1][m-1]
 */
public class CollectMaximumApples {

    private static int collectMaximumApples(int[][] A) {

        int n = A.length; //rows
        int m = A[0].length; //columns

        int[][] S = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                S[i][j] = A[i][j] + Math.max(j > 0 ? S[i][j - 1] : 0, i > 0 ? S[i - 1][j] : 0);
            }
        }
        return S[n - 1][m - 1];
    }

    public static void main(String[] args) {
        int[][] A = { {1, 3, 4, 3, 5},
                      {2, 4, 5, 1, 3},
                      {5, 7, 2, 9, 7}};

        System.out.println("Maximum Apples collected = " + collectMaximumApples(A));
    }
}
