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
 * String s1 = "ball"
 * String s2 = "car"
 * Output: 3
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
 * Recursive Approach
 *
 * 1) Do insert, delete and update
 * 2) Update the indices.
 * 3) Take minimum of all three + 1
 *
 * So in worst case we need to perform the operation on every character of string, since we have 3 operations,
 * Time Complexity: O(3^n) where n is max(s1.length(), s2.length())
 * Space Complexity: O(n)
 *
 * Overlapping subproblems
 *
 * Example:
 *
 * String s1 = "CAT"
 * String s2 = "DOG"
 *
 * resources/EditDistanceOverlappingSubproblems.png
 * resources/EditDistanceRecursiveTree.png
 *
 *
 * In computer science, edit distance is a way of quantifying how dissimilar two strings (eg words) are to
 * one another by counting the minimum number of operations required to transform one string into the other.
 * Edit distances find applications in natural language processing, where automatic spelling correction can
 * determine candidate corrections for a misspelled word by selecting words from a dictionary that have a low distance to the word
 * in question.
 *
 * In bioinformatics, it can be used to quantify the similarity of DNA sequences, which can be viewed as strings of letters
 * A, C, G, T
 *
 * Dynamic Programming Approach
 *
 * 1) Create DP table.
 *
 * Time Complexity: O(s1.length() * s2.length())
 * Space Complexity: O(s1.length() * s2.length())
 *
 * resources/EditDistanceDP.jpg
 *
 * *****************************IK Question*************************************************************************************
 * Levenshtein Distance
 * * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2.
 * (each operation is counted as 1 step.)
 *
 * You have the following 3 operations permitted on a word:
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 *
 * The minimum no of steps required to convert word1 to word2 with the given set of allowed operations is called edit distance.
 * e.g. Minimum edit distance between the words 'kitten' and 'sitting', is 3.
 *
 * kitten → sitten (substitution of "s" for "k")
 * sitten → sittin (substitution of "i" for "e")
 * sittin → sitting (insertion of "g" at the end)
 *
 * Read more about edit distance here:
 * https://en.wikipedia.org/wiki/Edit_distance
 *
 * Input Format:
 * You will be given two strings word1 and word2.
 *
 * Output Format:
 * Return an integer editDist, denoting the edit distance between given two input strings.
 *
 * If word1 = “cat” and word2 = “bat”, then input should be:
 * cat
 * bat
 *
 * Output Format:
 * For input word1 = “cat” and word2 = “bat”, output will be:
 * 1
 *
 * Constraints:
 *    1 <= length(word1), length(word2) <= 1000
 *     word1 and word2 contains lower case alphabets from a to z.
 *
 * Sample Input 1:
 * cat
 * bat
 *
 * Sample Output 1:
 * 1
 *
 * Explanation 1:
 * 1: Replace c with b.
 *
 * Sample Input 2:
 * qwe
 * q
 *
 * Sample Output 2:
 * 2
 *
 * Explanation 2:
 * 1: Add w
 * 2: Add e
 *
 */
public class EditDistanceProblem {

    private static int editDistanceRecursion(String s1, String s2, int i, int j) {

        //If any of the string is finished then number of operations needed
        //would be equal to the length of other string
        //Either all characters will be removed or inserted.

        if (i == s1.length()) {
            return s2.length() - j; //all elements will be inserted
        }

        if (j == s2.length()) {
            return s1.length() - i; //all elements will be removed
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            return editDistanceRecursion(s1, s2, i + 1, j + 1);
        }

        //If nothing have worked then we need to try all 3 operations and choose minimum among them
        return 1 + Math.min(editDistanceRecursion(s1, s2, i, j + 1),/*INSERT*/
                    Math.min(editDistanceRecursion(s1, s2, i + 1, j),/*REMOVE*/
                            editDistanceRecursion(s1, s2, i + 1, j + 1) /*REPLACE*/));
    }

    private static int editDistanceDP(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        //Base case
        //If any of the string is finished then number of operations needed would be equal to the length
        //other string (Either all characters will be removed or inserted)

        //Last row will be s2.length() - j
        for (int i = 0; i <= s2.length(); i++) {
            dp[s1.length()][i] = s2.length() - i;
        }

        //Last col will be s1.length() - i
        for(int i = 0; i <= s1.length(); i++) {
            dp[i][s2.length()] = s1.length() - i;
        }

        //Traversal direction
        for(int i = s1.length() - 1; i >= 0; i--) {
            for(int j = s2.length() - 1; j >= 0; j--) {

                if(s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j + 1], dp[i + 1][j]), dp[i + 1][j + 1]);
                }
            }
        }
        printPath(dp, s1, s2);
        return dp[0][0];
    }

    //Greedy Approach to print path from dp table.
    private static void printPath(int[][] dp, String s1, String s2) {
        int i = 0;
        int j = 0;

        String s = s1;

        while(i < s1.length() && j < s2.length()) {

            if(s1.charAt(i) == s2.charAt(j)) { //If both the characters match, just increment
                i = i + 1;
                j = j + 1;
                continue;
            }

            int remainingEdit = dp[i][j] - 1;

            //Check which of the three cells matches the remaining edit
            if(dp[i][j + 1] == remainingEdit) {
                s = s.substring(0, j) + s2.charAt(j) + s.substring(j);
                j = j + 1;
                System.out.println("After Insert " + s);
            } else if (dp[i + 1][j] == remainingEdit) {
                s = s.substring(0, j) + s.substring(j + 1);
                System.out.println("After Delete " + s);
                i = i + 1;
            } else if(dp[i + 1][j + 1] == remainingEdit) {
                s = s.substring(0, j) + s2.charAt(j) + s.substring(j + 1);
                System.out.println(" After Update " + s);
                i = i + 1;
                j = j + 1;
            }
        }
        System.out.println(s);
    }

    public static void main(String[] args) {
        String s1 = "ball";
        String s2 = "car";

        System.out.println("Minimum edit distance - Recursion: " + editDistanceRecursion(s1, s2, 0, 0));
        System.out.println("Minimum edit distance - DP: " + editDistanceDP(s1, s2));
    }
}
