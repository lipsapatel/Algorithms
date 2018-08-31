/**
 * Floyd's Triangle - Java Implementation
 *
 * Floyd's Triangle:
 *
 * 1) Floyd's Triangle is a right angled triangular array of natural numbers.
 * 2) It is named after Robert Floyd.
 * 3) Rows of the triangle are filled by consecutive numbers.
 * 4) First row will have single number which is 1.
 * 5) Second row will have 2 numbers, which are 2 and 3.
 * 6) Third row will have three numbers, which are 4, 5 and 6.
 * 7) And so on
 *
 * Example:
 *
 * First 5 lines...
 *
 * 1
 * 2 3
 * 4 5 6
 * 7 8 9 10
 * 11 12 13 14 15
 *
 * Approach:
 *
 * 1) Two nested loops
 * 2) Outer loop will take care for row count.
 * 3) Inner loop will take care of printing the numbers
 * 4) Line break after each iteration of inner loop
 * 5) Keep track of numbers printed so far in a variable
 *
 */
public class FloydTriangle {

    private static void printFloydTriangle(int rows) {

        if (rows <= 0) {
            return;
        }

        int rowCount = 1;
        int number = 1;

        while (rowCount <= rows) {

            for (int i = 0; i < rowCount; i++) {
                System.out.print(number + " ");
                number++;
            }

            rowCount++;
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int rows = 7;
        printFloydTriangle(rows);
    }
}
