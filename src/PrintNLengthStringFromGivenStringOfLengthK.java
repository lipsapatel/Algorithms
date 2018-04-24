/**
 * Print N length string from the given string of length k where characters can appear
 * multiple times.
 *
 * String k = "ALGO"
 * int n = 2
 *
 * Output: AL, LA, GA, OA, LA, LG, LL, LO, GA, GL, GG, GO, OO, OA, OL, OG
 *
 * Approach:
 *
 * 1) Loop through the String k
 * 2) Add k[i] to the result array which of size n
 * 3) Make recursive call with n - 1
 * 4) when n = 0 that means the array is full, print the string.
 *
 */
public class PrintNLengthStringFromGivenStringOfLengthK {

    private static void printAllNLengthString(int n, char[] k, char[] result) {

        if (n <= 0) {
            System.out.print(String.valueOf(result) + " ");
        } else {
            for (int i = 0; i < k.length; i++) {
                result[n - 1] = k[i];
                printAllNLengthString(n - 1, k, result);
            }
        }
    }

    public static void main(String[] args) {

        String k = "ALGO";
        int n = 2;

        printAllNLengthString(n, k.toCharArray(), new char[n]);
    }
}
