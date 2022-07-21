package IK.Recursion.PreClass;

import java.util.Arrays;

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
 * Space Complexity: O(n^2)
 *
 * Optimal Recursive Approach
 * 1) Define recursive function as (char[] strSoFar, n)
 * 2) Base Case: If n == 0, print String.valueOf(strSoFar)
 * 3) Recursive Case: Iterate from i = 0 to 9
 * 4) Append i - strSoFar[n - 1] = i
 * 5) Make recursive call (strSoFar, n - 1)
 *
 * Time Complexity: O(10^n)
 * Space Complexity: O(n)
 */
public class  PrintDecimalOfLength {

    private static void printDecimalOfLength(int n, String soFar) {

        //Base Case
        if (n == 0) {
            System.out.println(soFar);
        } else {
            //Recursive Case
            //Try all options/choices
            for (int i = 0; i < 10; i++) {
                printDecimalOfLength(n - 1, soFar+i); //This creates n new strings of length 1 to n
            }
        }
    }

    private static void printDecimalOfLengthOptimal(int[] strSoFar, int n) {
        //Base Case
        if(n == 0) {
            System.out.println(Arrays.toString(strSoFar));
        } else {
            //Recursive Case
            for(int i = 0; i < 10; i++) {
                strSoFar[n - 1] = i;
                printDecimalOfLengthOptimal(strSoFar, n - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Print decimal of length 2");
        printDecimalOfLength(2, "");
        System.out.println("Print decimal of length 3");
        printDecimalOfLength(3, "");

        System.out.println("Print decimal of length 3 optimal");
        printDecimalOfLengthOptimal(new int[3], 3);
    }
}
