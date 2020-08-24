/**
 * Print Matrix In Spiral Order
 *
 * Given a character matrix, return all the characters in the clockwise spiral order
 * starting from the top-left.
 *
 * Example
 * Input:
 * [
 * ['X' 'Y' 'A']
 * ['M' 'B' 'C']
 * ['P' 'Q' 'R']
 * ]
 *
 * Output: "XYACRQPMB"
 * return string "XYACRQPMB" of length rows * cols = 9.
 *
 * Constraints:
 *
 *     1 <= rows, cols
 *     1 <= rows * cols <= 10^5
 *     Any character in matrix will be either uppercase letter ('A' - 'Z') or lowercase letter ('a' - 'z').
 *     Avoid recursion.
 *
 * This problem is less about logic, but more about careful index manipulation.
 *
 * Hint - It may be faster to write this, if you name your variables clearly.
 * Instead of i,j,k,l etc, try naming them like row, col, start, end etc.
 * That will also help your interviewer follow along more easily.
 *
 * resources/SpiralOrder1.jpg
 * resources/SpiralOrder2.jpg
 * resources/SpiralOrder3.jpg
 * resources/SpiralOrder4.jpg
 *
 * Approach
 *
 * 1) We put signs where to turn.
 * 2) We can divide the matrix into 4 parts
 *    top-left
 *    top-right
 *    bottom-right
 *    bottom-left
 * 3) Pattern for putting signs is same for top-right, bottom-right and bottom-left
 * 4) We need to decide for each currRow and currCol if the signs need to be placed.
 * 5) Top- right
 *    currRow == cols - 1 - currCol;
 *
 *    Bottom-right
 *    rows - 1 - currRow == cols - 1 - currCol
 *
 *    Bottom-left
 *    rows - 1 - currRow == currCol
 * 6) Top-left
 *    currRow == currCol + 1;
 *
 *  How to find if it's top-left?
 *  If currRow < (rows + 1)/2
 *     currCol < (cols)/2
 *
 * Time Complexity: O(r * c)
 * Space Complexity: O(1)
 *
 */
public class PrintMatrixInSpiralOrder {

    private static int[][] directionArray = {
            {0, 1}, //right
            {1, 0}, //down
            {0, -1}, //left
            {-1, 0} //top
    };

    private static String printSpirally(char[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int total = rows * cols;

        char[] result = new char[total];

        //Initial position is top-left corner with direction towards right
        int direction = 0;
        int currRow = 0;
        int currCol = 0;

        for(int i = 0; i < total; i++) {
            result[i] = mat[currRow][currCol];

            if(shouldTurn(currRow, currCol, rows, cols)) {
                direction = (direction + 1) % 4;
            }

            currRow += directionArray[direction][0];
            currCol += directionArray[direction][1];
        }
        return String.valueOf(result);
    }

    private static boolean shouldTurn(int currRow, int currCol, int rows, int cols) {

        if(currRow < (rows + 1)/2 && currCol < cols/2) {
            //Top-left
            return currRow == currCol + 1;
        } else {
            return Math.min(currRow, rows - 1 - currRow) == Math.min(currCol, cols - 1 - currCol);
        }
    }

    public static void main(String[] args) {
        char[][] mat = {{'X', 'Y', 'A'},
                         {'M', 'B', 'C'},
                         {'P', 'Q', 'R'}};

        System.out.println("Spiral order " +printSpirally(mat));
    }
}
