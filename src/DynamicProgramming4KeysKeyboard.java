/**
 * Dynamic Programming - 4 keys keyboard problem.
 *
 * Given 4 keys as below:
 *
 * 1) A
 * 2) Ctrl + A
 * 3) Ctrl + C
 * 4) Ctrl + V
 *
 * If you can only press the keyboard N times (with above four keys), please write program
 * to produce maximum numbers of A. If possible also please print out the sequence of keys.
 *
 * Input:
 *
 * N = Number of keys you can press
 *
 * Output
 *
 * M = Number of A's you can produce
 *
 * N = 2
 Output: 2 (AA)

 ‘A’ key pressed
 ‘A’ key pressed

 N = 7
 Output: 8 (AAAAAAAA)

 ‘A’ key pressed
 ‘A’ key pressed
 ‘A’ key pressed
 ‘A’ key pressed
 Ctrl + A
 Ctrl + C
 Ctrl + V

 Approach:

 1) Using Recursion
 2) Press Ctrl A, C and V only if there are atleast 3 keystrokes
 3) If keystrokes are less than 4, then output is n
 4) If n > 3 then we have two options:
 Either press A or Ctrl+A, C and V and select the optimal solution.

 Time Complexity: O(2^n) - Exponential

 resources/RecursionTreeNumberOfKeys.png

 If we look at the above tree there are subproblems which are solve repeatedly.

 Dynamic Programming:

 Time Complexity: O(n)

 1) Solve the problem in bottom-up manner
 2) Start from the smallest problem and gradually increase n.
 3) Store the solution and use them as needed.
 4) Use the solution array to print the result.
 */
public class DynamicProgramming4KeysKeyboard {

    private static int printMaxAUsingRecursion(int n) {

        if (n < 4) {
            return n;
        }

        //Two options
        int x = printMaxAUsingRecursion(n - 1) + 1;

        int y = printMaxAUsingRecursion(n - 3) * 2;

        return Math.max(x, y);
    }

    private static int printMaxAUsingDynamicProgramming(int n) {

        int[] solution = new int[n + 1];

        solution[0] = 0;
        solution[1] = 1;
        solution[2] = 2;
        solution[3] = 3;

        for (int i = 4; i <= n; i++) {

            //Print 1 character and solve for i - 1
            int x = solution[i - 1] + 1;
            int y = solution[i - 3] * 2;

            solution[i] = Math.max(x, y);

        }

        //Print the sequence
        //If the previous entry is 1 less then char A is pressed else Ctrl + A, C and V is pressed
        //Decrease i by 1 or 3
        String sequence = "";
        for (int i = n; i >= 1;) {

            if (solution[i] - solution[i - 1] == 1) {
                sequence = "char key pressed\n" + sequence;
                i = i - 1;
            } else {
                sequence = "Paste\n" + sequence;
                sequence = "Copy\n" + sequence;
                sequence = "Select\n" + sequence;
                i = i - 3;
            }
        }
        System.out.println(sequence);
        return solution[n];
    }

    public static void main(String[] args) {
        int n = 10;

        long startTime  = System.currentTimeMillis();
        System.out.println("Maximum output: " + printMaxAUsingRecursion(n));
        long end  = System.currentTimeMillis();
        System.out.println("Time taken: " + (end-startTime) + " milliseconds");

        System.out.println();
        long startTime1  = System.currentTimeMillis();
        System.out.println(printMaxAUsingDynamicProgramming(n));
        long end1  = System.currentTimeMillis();
        System.out.println("Time taken: " + (end1-startTime1) + " milliseconds");
    }
}
