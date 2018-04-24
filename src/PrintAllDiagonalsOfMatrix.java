/**
 * Given two dimensional matrix, write an algorithm to print all the diagonals of matrix.
 *
 * Approach:
 *
 * 1) We will solve this problem in two parts, first half of diagonals and second half of diagonals.
 * 2) For first half increment row
 * 3) For second half increment col
 * 4) For both parts, to print diagonal, decrement row and increment col
 */
public class PrintAllDiagonalsOfMatrix {

    private static void printAllDiagonalsOfMatrix(int[][] array) {

        int col;
        int row = 0;

        //First half
        while (row < array.length) {

            int rowTemp = row;
            col = 0;

            while (rowTemp >= 0) {

                System.out.print(array[rowTemp][col] + " ");
                rowTemp--;
                col++;
            }
            System.out.println();
            row++;
        }

        //For second half
        col = 1;

        while (col < array.length) {

            int colTemp = col;
            row = array.length - 1;

            while (colTemp < array.length) {

                System.out.print(array[row][colTemp] + " ");
                row--;
                colTemp++;
            }

            System.out.println();
            col++;
        }
    }

    public static void main(String[] args) {

        int [][] array = {{1,2,3,4},
                          {5,6,7,8},
                          {9,10,11,12},
                          {13,14,15,16}};

        printAllDiagonalsOfMatrix(array);
    }
}
