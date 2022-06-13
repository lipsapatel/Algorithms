package IK.Recursion.PreClass;

/**
 * Write a recursive function printDecimal that accepts an integer number of digits n and prints all base 10
 * numbers that have exactly that many digits in ascending order one per line
 *
 * Try all the options/choices
 *
 * Print all decimal strings of length n
 *
 * Approach
 * 1) Define recursive function as (strSoFar, n)
 * 2) Base Case: If n == 0 print strSoFar
 * 3) Recursive Case: Iterate from i = 0 to 9
 * 4) Make recursive call by appending one digit to strSoFar and reducing problem size by 1 (strSoFar + i, n - 1)
 *
 * Time Complexity: O(10^n)
 * Space Complexity: O(n)
 */
public class PrintDecimalOfLength {

    private static void printDecimalOfLength(int n, String soFar) {

        //Base Case
        if (n == 0) {
            System.out.println(soFar);
        } else {
            //Recursive Case
            //Try all options/choices
            for (int i = 0; i < 10; i++) {
                printDecimalOfLength(n - 1, soFar+i);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Print decimal of length 2");
        printDecimalOfLength(2, "");
        System.out.println("Print decimal of length 3");
        printDecimalOfLength(3, "");
    }
}
