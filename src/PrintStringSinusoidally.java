import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Print A String Sinusoidally
 *
 * This is a string puzzle problem disguised as a programming problem.
 * Also called "SnakeString". For example, the phrase "Google Worked"
 * should be printed as follows (where ~ is the word separator):
 *
 *  o   ~   k
 * o g e W r e
 *G   l   o   d
 *
 * The length of each row should be the same, i.e. there should be two
 * spaces at the end of the first line, one space at the end of the second line,
 * and zero spaces at the end of the third line.

 * For the function: s = Google Worked
 * First character “G” is printed on the third row, first column.
 * Second character “o” is printed on the second row, second column.
 * Third character “o” is printed on the first row, third column.
 * Fourth character “g” is printed on the second row, fourth column.
 * Fifth character “l” is printed on the third row, fifth column.
 * Sixth character “e” is printed on the second row, sixth column.
 * Seventh character “~” is printed on the first row, seventh column.
 * “~” is printed as seventh character in the input is a space.
 *
 * This process goes on to the last character of the input string.
 *
 * Output: String will be printed sinusoidally. Format is:
 * → There will be 3 rows
 * → Print ~ for spaces
 * → First character is printed in 1st column of 3rd row
 * → Second character is printed in 2nd column of 2nd row
 * → Third character is printed in 3rd column of 1st row
 * → Fourth character is printed in 4th column of 2nd row
 * → Fifth character is printed in 5nd column of 3rd row
 * → Sixth character is printed in 6th column of 2nd row
 * → This process goes on…
 *
 * Constraints:
 *
 *     String consisting of alphanumeric characters and spaces
 *     3 <= |s| <= 10^5
 *
 * → Print ~ for spaces
 *
 * Solution:
 *
 * There are few observations:
 * → i’th character of the string is placed in i’th column of a row.
 * → Character at index 0 of string is placed in 3rd row. Then 4th, 8th and go on.
 * → Character at index 1 of string is placed in 2nd row. Then 3rd, 5th and go on.
 * → Character at index 2 of string is placed in 1st row. Then 6th, 10th and go on.
 * So, we can construct 3 strings representing 3 rows using the above information.
 *
 * Time Complexity:
 * O(|s|).
 * As we are traversing all the characters of the string,
 * so time complexity is O(|s|).
 *
 * Auxiliary Space:
 * O(|s|).
 * To store the three rows we use a 2D array with three rows and |s| columns.
 * Hence auxiliary space required is O(3*|s|) = O(|s|)
 *
 * Space Complexity:
 * O(|s|).
 *
 * Approach:
 *
 * 1) For 0th row, it places character at col index 2, 6, 10, ... so that is +4
 * 2) For 1st row, it places character at col index 1, 3, 5,... so that is +2
 * 3) For 2nd row, it places character at col index 0, 4, 8, ... so that is +4
 * 4) Fill the first row, second row and third row.
 *
 * resources/PrintStringSinusoidally.jpg
 */
public class PrintStringSinusoidally {

    public static void printStringSinusoidally(String s) {
        int n = s.length();
        char[][] wavedString = new char[3][n];

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < n; j++) {
                wavedString[i][j] = ' ';
            }
        }

        //Fill in the first row
        for(int j = 2; j < n; j += 4) {
            wavedString[0][j] = (s.charAt(j) == ' ') ? '~' : s.charAt(j);
        }

        //Fill in the second row
        for(int j = 1; j < n; j += 2) {
            wavedString[1][j] = (s.charAt(j) == ' ') ? '~' : s.charAt(j);
        }

        //Fill in the third row
        for(int j = 0; j < n; j += 4) {
            wavedString[2][j] = (s.charAt(j) == ' ') ? '~' : s.charAt(j);
        }

        //Print String
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(wavedString[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String s = "Google Worked";
        printStringSinusoidally(s);
    }
}
