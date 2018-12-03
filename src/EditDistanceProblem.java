/**
 * Dynamic Programming - Edit Distance Problem
 *
 * Given two strings, s1 and s2 and edit operations. Write an algorithm to find minimum number
 * of operations required to convert s1 to s2.
 *
 * Allowed Operations:
 *
 * Insertion: Insert a new character
 * Deletion: Delete a character
 * Replace: Replace one character by another
 *
 * Example:
 *
 * String s1 = "horizon"
 * String s2 = "horzon"
 * Output: 1 (remove 'i' from string s1)
 *
 * String s1 = "horizon";
 * String s2 = "horizontal";
 * Output: 3 (insert 't', 'a', 'l' characters in string s1)
 *
 * Approach:
 *
 * Start comparing one character at a time in both strings. Here we are comparing
 * string from right to left (backwards)
 *
 * Now for every string there are two options
 *
 * 1) If last character in both the strings are same, then just igone the character
 * and solve the rest of the string recursively
 * 2) Else if last characters in both the strings are not same then we will try all the
 * possible operations (insert, delete and replace) and get the solution for rest of the string
 * recursively for each possibility and pick the minimum out of them.
 *
 * Let's say given strings are s1 and s2 with lengths m and n respectively
 *
 * case 1: last characters are same, ignore the last character.
 * Recursively solve for m - 1 and n - 1
 * case 2: last characters are not same, then try all the possible operations recursively
 *
 * 1) Insert a character into s1 (same as last character in string s2 so that last character in
 * both the strings are same)
 * now s1 length will be m + 1 and s2 length will be n
 * Ignore the last character and recursively solve for m, n - 1
 *
 * 2) Remove last character from string s1, now s1 length will be m - 1 and s2 length will be n
 * Recursively solve for m - 1, n
 *
 * 3) Replace last character into s1 (same as last character in string s2 so that last character
 * in both the strings are same) s1 length will be m and s2 length will be n, ignore last character
 * Recursively solve for m - 1 and n - 1
 *
 * Choose minimum (a, b, c)
 *
 * First we will see the recursive solution then we will improve the solution
 * by reducing its complexity using dynamic programming.
 *
 * So in worst case we need to perform the operation on every character of string, since we have 3 operations,
 * Time Complexity: O(3^n)
 *
 * Overlapping subproblems
 *
 * Example:
 *
 * String s1 = "CAT"
 * String s2 = "DOG"
 *
 * resources/EditDistanceOverlappingSubproblems.png
 *
 * As we can see that there are many sub problems which are solved repeatedly so we have overlapping
 * sub problems here.
 * We can solve it using dynamic programming in bottom-up manner. We will solve the problem and store it into
 * an array and use the solution as needed this way we will ensure that each subproblem will be solved only once.
 *
 * In computer science, edit distance is a way of quantifying how dissimilar two strings (eg words) are to
 * one another by counting the minimum number of operations required to transform one string into the other.
 * Edit distances find applications in natural language processing, where automatic spelling correction can
 * determine candidate corrections for a misspelled word by selecting words from a dictionary that have a low distance to the word
 * in question.
 *
 * In bioinformatics, it can be used to quantify the similarity of DNA sequences, which can be viewed as strings of letters
 * A, C, G, T
 */
public class EditDistanceProblem {

    private static int editDistanceRecursion(String s1, String s2, int m, int n) {

        //If any of the string is empty then number of operations needed
        //would be equal to the length of other string
        //Either all characters will be removed or inserted.

        if (m == 0) {
            return n; //all elements will be inserted
        }

        if (n == 0) {
            return m; //all elements will be removed
        }

        //If last characters are matching, ignore the last character and make
        //recursive call with last character removed
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return editDistanceRecursion(s1, s2, m - 1, n - 1);
        }

        //If nothing have worked then we need to try all 3 operations and choose minimum amont them
        return 1 + Math.min(editDistanceRecursion(s1, s2, m, n - 1),/*INSERT*/
                    Math.min(editDistanceRecursion(s1, s2, m - 1, n),/*REMOVE*/
                            editDistanceRecursion(s1, s2, m - 1, n - 1) /*REPLACE*/));
    }

    private static int editDistanceDP(String s1, String s2) {
        int[][] solution = new int[s1.length() + 1][s2.length() + 1];

        //Base case
        //If any of the string is empty then number of operations needed would be equal to the length
        //other string (Either all characters will be removed or inserted)
        for (int i = 0; i <= s2.length(); i++) {
            //All elements will be inserted in s1
            solution[0][i] = i;
        }

        for (int i = 0; i <= s1.length(); i++) {
            //All elements will be removed in s1
            solution[i][0] = i;
        }

        //Solving it in bottom-up manner
        int m = s1.length();
        int n = s2.length();

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                //If last characters are matching, ignore the last character
                //The solution will be same as without last character
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    solution[i][j] = solution[i - 1][j - 1];
                } else {

                    solution[i][j] = 1 + Math.min(solution[i][j - 1], //Insert
                            Math.min(solution[i - 1][j], //Remove
                                    solution[i - 1][j - 1])); //Replace
                }
            }
        }
        return solution[s1.length()][s2.length()];
    }
    public static void main(String[] args) {
        String s1 = "horizon";
        String s2 = "horizontal";

        System.out.println("Minimum edit distance - Recursion: " + editDistanceRecursion(s1, s2, s1.length(), s2.length()));
        System.out.println("Minimum edit distance - DP: " + editDistanceDP(s1, s2));
    }
}
