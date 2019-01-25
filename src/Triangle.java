/**
 * We have triangle made of blocks. The topmost row has 1 block, the next row down has 2 blocks, the next row has 3 blocks, and so on.
 * Compute recursively (no loops or multiplication) the total number of blocks in such a triangle with the given number of rows.


 triangle(0) → 0
 triangle(1) → 1
 triangle(2) → 3
 */
public class Triangle {

    private static int triangle(int rows) {
        //Base Case
        if (rows == 0) {
            return 0;
        } else { //Recursive case
            return rows + triangle(rows - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println("Total number of blocks in triangle with 4 rows are: " + triangle(4));
        System.out.println("Total number of blocks in triangle with 0 rows are: " + triangle(0));
        System.out.println("Total number of blocks in triangle with 1 rows are: " + triangle(1));
        System.out.println("Total number of blocks in triangle with 2 rows are: " + triangle(2));
    }
}
