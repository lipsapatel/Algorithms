/**
 * Print all possible strings of length k that can be formed from a set of n characters
 *
 * Given a set of characters and positive integer k, print all possible strings of length k that
 * can be formed from the given set.
 *
 * Example:
 *
 * Input:
 *
 * char[] set = {'a', 'b'}
 * k = 3
 *
 * Output:
 *
 * aaa
 * aab
 * aba
 * abb
 * baa
 * bab
 * bbb
 * bba
 *
 * Input:
 * char[] set = {'a', 'b', 'c', 'd'}
 * int k = 1;
 *
 * Output:
 * a
 * b
 * c
 * d
 *
 * For a given set of size n, there will be n^k possible string of length k.
 *
 * Approach:
 *
 * 1) Start from an empty output string - called prefix
 * 2) One by one add characters to prefix.
 * 3) Print all possible strings recursively when k == 0
 *
 * Time Complexity: O(n^k)
 */
public class PrintAllPossibleStringOfLengthK {

    public static void printAllPossibleStringOfLengthK(char[] set, String prefix, int n, int k) {

        //Base case when k == 0, print the prefix
        if (k == 0) {
            System.out.println(prefix);
            return;
        }

        for (int i = 0; i < n; i++) {

            String newPrefix = prefix + set[i];

            printAllPossibleStringOfLengthK(set, newPrefix, n, k - 1);
        }
    }

    public static void main(String[] args) {

        System.out.println("All possible string of length k");
        char[] set = {'a', 'b'};
        int k = 3;

        printAllPossibleStringOfLengthK(set, "", set.length, k);
    }
}
