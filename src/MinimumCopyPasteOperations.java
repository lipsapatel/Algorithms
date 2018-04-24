/**
 * Minimum Copy Paste Operations
 *
 * You are given character 'A' which is already printed.
 *
 * You are allowed to perform 2 operations:
 *
 * 1) Copy All: This operation will copy all printed characters.
 * 2) Paste: This operation will paste all the characters which are already copied.
 *
 * Given number N, write an algorithm to print character A exactly n times with minimum no of
 * operations.
 *
 * Character – A
 N = 6

 Option 1:
 Copy All – this will copy ‘A’
 Paste – output “AA”
 Paste – output “AAA”
 Paste – output “AAAA”
 Paste – output “AAAAA”
 Paste – output “AAAAAA”
 Total operations – 6

 Option 2:
 Copy All – this will copy ‘A’
 Paste – output “AA”
 Paste – output “AAA”
 Copy All
 Paste – output “AAAAAA”
 Total operations – 5

 Since with option 2, the task is done in 5 operations. Minimum operations – 5

 Say If N = 50 then we print 25 A’s then by doing copy all and paste will print 50 characters.
 Now to reach 25, which is multiple of 5 so if we print 5 A’s then copy and paste it 4 times
 to get 25 A’s and now to print 5 A’s, copy the already printed A and paste it 4 times.

 N = 50, printed – A

 Copy and paste it 4 times – printed – AAAAA, operations – 5
 Copy and paste it 4 times – printed AA…..AA (25), operations – 5 + 5 = 10
 Copy and paste – printed – AA…AAA (50 A’s), operations – 10 + 2 = 12

 Approach:

 1) Loop till n
 2) while n % i == 0
 3) Add i to the result
 4) Update n to n/i
 5) return result
 **/
public class MinimumCopyPasteOperations {

    private static int findMinimumOperations(int n) {

        int result = 0;

        for (int i = 2; i <= n; i++) {

            while(n % i == 0) {

                result = result + i;
                n = n/i;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int n = 50;
        System.out.println("Minimum Operations: " + findMinimumOperations(n));
    }
}
