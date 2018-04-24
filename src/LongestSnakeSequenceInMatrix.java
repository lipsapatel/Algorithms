/**
 * Given two dimensional matrix, write an algorithm to find the snake sequence
 * which has the maximum length.
 *
 * There could be many snake sequence, you need to return one with the
 * maximum length.
 *
 * Travel can be allowed in two direction:
 * right
 * down
 *
 * Snake sequence can be made if number in adjacent right cell or
 * number in adjacent down is either +1 or -1 than the number in current cell
 *
 * images/LongestSnakeSequence.png
 *
 * Take temporary two dimensional result matrix
 * Keep track of maximumLength, maximumRow and maximumColumn
 *
 * If no sequence is found then every cell itself is a sequence of length 1
 * Initialize result matrix with all 1
 *
 * if i!=0 or j!=0
 * if i > 0 and difference is 1
 * then Math.max(result[i][j], result[i-1][j]+1)
 * Update the maximumLength, maximumRow and maximumColumn if maximumLength is less
 * than result[i][j]
 *
 * if j > 0 and difference is 1
 * the update result = Math.max(result[i][j], result[i][j-1]+1)
 * Update the maximumLength, maximumRow and maximumColumn if maximumLength is less
 * than result[i][j]
 *
 * To print the snake sequence
 * while maximumLength >= 1
 *
 * print matrix[maximumRow][maximumColumn]
 * if maximumRow > 0 and difference 1
 * maximumRow--
 * else if maximumColumn > 0 and difference is 1
 * maximumColumn--
 *
 * maximumLength--
 */
public class LongestSnakeSequenceInMatrix {

    private static void findLongestSnakeSequence(int[][] matrix) {

        int maximumLength = 1;
        int maximumRow = 0;
        int maximumColumn = 0;
        int row = matrix.length;
        int column = matrix[0].length;

        //Create result matrix
        int[][] result = new int[matrix.length][matrix.length];

        //If there is no snake sequence, then every cell itself is a sequence
        //of length 1, initialize result matrix with all 1
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                result[i][j] = 1;
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {

                if (i != 0 || j != 0) {

                    //Check if difference is equal to 1
                    if (i > 0 && Math.abs(matrix[i][j] - matrix[i-1][j]) == 1) {

                        //Update the result matrix
                        result[i][j] = Math.max(result[i][j], result[i-1][j] + 1);

                        if (maximumLength < result[i][j]) {
                            maximumLength = result[i][j];
                            maximumRow = i;
                            maximumColumn = j;
                        }
                    }

                    //Check if the difference is 1 for j
                    if (j > 0 && Math.abs(matrix[i][j] - matrix[i][j-1]) == 1) {

                        //Update the result matrix
                        result[i][j] = Math.max(result[i][j], result[i][j-1] + 1);

                        if (maximumLength < result[i][j]) {
                            maximumLength = result[i][j];
                            maximumRow = i;
                            maximumColumn = j;
                        }
                    }
                }
            }
        }

        System.out.println("Maximum Length of Snake Sequence is " + maximumLength);
        //print the maximum snake sequence
        printSnakeSequence(matrix, result, maximumLength, maximumRow, maximumColumn);
    }

    private static void printSnakeSequence(int[][] matrix, int[][] result, int maximumLength, int maximumRow, int maximumColumn) {

        while(maximumLength >= 1) {

            System.out.print("- " + matrix[maximumRow][maximumColumn]);

            //difference is 1 then decrement row
            if (maximumRow > 0 && Math.abs(result[maximumRow][maximumColumn] -
                result[maximumRow - 1][maximumColumn]) == 1) {
                maximumRow--;
            }

            //difference is 1 the decrement column
            else if (maximumColumn > 0 && Math.abs(result[maximumRow][maximumColumn] -
                result[maximumRow][maximumColumn - 1]) == 1) {
                maximumColumn--;
            }

            maximumLength--;
        }
    }

    public static void main(String[] args) {
        int arrA [][] = {{1, 2, 1, 2},
                {7, 7, 2, 5},
                {6, 4, 3, 4},
                {1, 2, 2, 5}};

        findLongestSnakeSequence(arrA);
    }
}
