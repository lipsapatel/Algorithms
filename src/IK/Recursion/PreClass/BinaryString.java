package IK.Recursion.PreClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Print all binary strings of given length
 *
 * Recursive Approach(BFS)
 *
 * 1) Base Case: If n == 1, then add 0 and 1 to result
 * 2) Else recurse and find all permutations for n - 1 length
 * 3) Iterate all the permutations and append 0 and 1
 * 4) Return result
 * 5) There will be 2^n binary strings of length n
 * 6) This problem is called exhaustive search or exhaustive enumeration because we are exhausting all possibilities.
 *
 * Time Complexity: O(2^n) - Amount of work done at each level is 2^level = 2^n
 * Space Complexity O(2^n) stores all 2^n strings
 *
 * Iterative Approach(BFS)
 *
 * 1) Create result and add 0 and 1
 * 2) Iterate from i = 2 to n
 * 3) For each string in result append 0 and 1
 * 4) Store the result in newResult list
 * 5) Copy newResult in result (result = newResult)
 * 6) Return result
 *
 * Time Complexity: O(2^n)
 * Space Complexity: O(2^n)
 *
 * Recursive Approach - Optimal Solution (DFS)
 *
 * 1) Base Case: If n == 0, print the strSoFar
 * 2) Recursive Case: Call recursive function with strSoFar + "0" and n - 1
 * 3) Call Recursive function with strSoFar + "1" and n - 1
 *
 * Time Complexity: O(2^n)
 * Space Complexity: O(n)
 */
public class BinaryString {

    private static void binaryStringsOptimal(String strSoFar, int n) {

        //Base Case
        if(n == 0) {
            System.out.println(strSoFar);
        } else {
            binaryStringsOptimal(strSoFar + "0", n - 1);
            binaryStringsOptimal(strSoFar + "1", n - 1);
        }
    }

    private static List<String> binaryStrings(int n) {

        List<String> result = new ArrayList<>();

        binaryStringHelper(result, "", n);
        return result;
    }

    private static void binaryStringHelper(List<String> result, String soFar, int n) {
        //Base Case
        if(n == 0) {
            result.add(soFar);
        } else { //Recursive Case
            binaryStringHelper(result, soFar + "0", n - 1);
            binaryStringHelper(result, soFar + "1", n - 1);
        }
    }

    private static List<String> iterativeBinaryStrings(int n) {
        List<String> result = new ArrayList<>();
        result.add("0");
        result.add("1");

        for (int i = 2; i <= n; i++) {
            List<String> newResult = new ArrayList<>();

            for(String s: result) {
                newResult.add(s + "0");
                newResult.add(s + "1");
            }
            result = newResult;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("The list of binary strings of length 2 is " + binaryStrings(2).toString());
        System.out.println("The list of binary strings of length 3 is " + binaryStrings(3).toString());
        System.out.println("The list of binary strings of length 4 is " + binaryStrings(4).toString());

        System.out.println("The list of binary strings of length 2 is " + iterativeBinaryStrings(2).toString());
        System.out.println("The list of binary strings of length 3 is " + iterativeBinaryStrings(3).toString());
        System.out.println("The list of binary strings of length 4 is " + iterativeBinaryStrings(4).toString());

        binaryStringsOptimal("", 2);
        binaryStringsOptimal("", 3);
        binaryStringsOptimal("", 4);
    }
}
