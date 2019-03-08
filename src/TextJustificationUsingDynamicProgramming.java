/**
 * Given a sequence of words, and a limit on the number of characters that can be put in one line.
 * Put line breaks in given sequence such that the lines are printed neatly.
 *
 * Assume that the length of each word is smaller than the line width.
 *
 * Greedy Solution:
 *
 * The greedy solution is to place as many words as possible in the first line.
 * Then do the same thing for the second line and so on until all words are placed.
 *
 * For example, consider the following string “aaa bb cc ddddd” and line width as 6. Greedy method will produce following output.

 aaa bb
 cc
 ddddd
 Extra spaces in the above 3 lines are 0, 4 and 1 respectively. So total cost is 0 + 64 + 1 = 65.

 But the above solution is not the best solution. Following arrangement has more balanced spaces. Therefore less value of total cost function.

 aaa
 bb cc
 ddddd
 Extra spaces in the above 3 lines are 3, 1 and 1 respectively. So total cost is 27 + 1 + 1 = 29.

 Method 2: Dynamic Programming

 1) Compute cost matrix for all possible lines. cm[i][j] = cost to put words i to j in single line.
 2) If words doesn't fit in one line, cm[i][j] = INFINITY
 3) Calculate the optimized cost c[j] from 1 to j
    c[j] = 0 if j = 0
    c[j] = min (c[i - 1] + cm[i,j]) 1 <= i <= j if j > 0
 3) Overlapping subproblem property. The solution of sub problem c(2) used by c(3), c(4) and so on.
 4) Parallel p array that points to where each c value came from.
 5) Last line start at word p[n] and goes through word n.
 6) The previous line starts at word p[p[n] - 1] and goes through p[n] - 1 etc.

 Badness(i, j) - How bad it is to use words i to j as line

 Time Complexity: O(n^2)
 Space Complexity: O(n^2)

 */
public class TextJustificationUsingDynamicProgramming {

    private static void wordWrapUsingDP(int[] w, int n, int width) {

        //Construct the cost matrix
        int[][] cm = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            cm[i][i] = width - w[i - 1];
            for (int j = i + 1; j <= n; j++) {
                cm[i][j] = cm[i][j - 1] - w[j - 1] - 1;
            }
        }

        //Construct square of cost matrix
        int[][] cm2 = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (cm[i][j] < 0) {
                    cm2[i][j] = Integer.MAX_VALUE;
                } else {
                    cm2[i][j] = (int)Math.pow(cm[i][j], 2);
                }
            }
        }

        //Calculate optimized minimum cost for 1 to j and also construct parallel array
        int[] c = new int[n + 1];
        int[] p = new int[n + 1];

        c[0] = 0;
        for (int j = 1; j <= n; j++) {
            c[j] = Integer.MAX_VALUE;
            for (int i = 1; i <= j; i++) {
                if (c[i - 1] != Integer.MAX_VALUE && cm2[i][j] != Integer.MAX_VALUE && (c[i - 1] + cm2[i][j] < c[j])) {
                    c[j] = c[i - 1] + cm2[i][j];
                    p[j] = i;
                }
            }
        }
        printSolution(p, n);
    }

    //p[n] to n words are at last line....
    private static int printSolution(int[] p, int n) {
        int k;

        if (p[n] == 1) {
            k = 1;
        } else {
            k = printSolution(p, p[n] - 1) + 1;
        }

        System.out.println("Line number " + k + " from words no: " + p[n] + " to " + n);
        return k;
    }

    public static void main(String[] args) {
        int[] w = {3, 2, 2, 5};
        int n = w.length;
        int width = 6;

        wordWrapUsingDP(w, n, width);
    }
}
