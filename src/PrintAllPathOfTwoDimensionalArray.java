/**
 * Print all path from top left corner to bottom right corner of two dimensional array
 *
 * To explore all the paths from top left corner to bottom right corner, we will
 * either travel down or right.
 *
 * So every time we either increase row or column
 *
 * Take rowCount and colCount
 * currentRow and currentColumn = 0
 * CurrentPath = ""
 *
 * Add array[0][0] to the path
 *
 * Call recursively (currentRow+1, currentColumn, path)
 * (currentRow, currentColumn+1, path)
 *
 * To print diagonal path also
 * (currentRow+1, currentColumn+1, path)
 *
 * when currentRow = rowCount - 1, print rest of the columns and return
 * When currentColumn = colCount - 1, print rest of the rows and return
 *
 * images/printAllPathsTwoDimensionalArray.png
 */
public class PrintAllPathOfTwoDimensionalArray {

    static int[][] array;
    static int rowCount;
    static int columnCount;

    private static void printAllPaths(int[][] inputArray) {
        array = inputArray;
        rowCount = array.length;
        columnCount = array[0].length;

        printAllPaths(0, 0, "");
    }

    private static void printAllPaths(int currentRow, int currentColumn, String path) {

        if (currentRow == rowCount - 1) {

            //Add all columns
            for (int i = currentColumn; i < columnCount; i++) {

                path = path + "-" + array[currentRow][i];
            }

            System.out.println(path);
            return;
        }

        if (currentColumn == columnCount - 1) {

            //Add all rows
            for (int i = currentRow; i < rowCount; i++) {

                path = path + "-" + array[i][currentColumn];
            }

            System.out.println(path);
            return;
        }

        path = path + "-" + array[currentRow][currentColumn];
        printAllPaths(currentRow+1, currentColumn, path);
        printAllPaths(currentRow, currentColumn+1, path);

        //To print diagonal path
        //printAllPaths(currentRow+1, currentColumn+1, path);
    }

    public static void main(String[] args) {

        int[][] array = { { 1, 2, 3 }, { 4, 5, 6 } };

        printAllPaths(array);

        int[][] array1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        System.out.println("second array");
        printAllPaths(array1);
    }
}
