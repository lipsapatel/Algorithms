/**
  The knight is placed on the first block of an empty board and, moving according to the rules of chess,
 must visit each square exactly once.

 Path followed by Knight to cover all the cells

 Following is chessboard with 8 x 8 cells. Numbers in cells indicate move number of Knight.
 knight-tour-problem

 All possible move for knight

        -|-
      |--0--|
        -|-

 Total 8 possible moves

 Backtracking Algorithm for Knight’s tour Approach

 Following is the Backtracking algorithm for Knight’s tour problem.

 1) Base Case: If all squares are visited
    print the solution
 Else
 a) Add one of the next moves to solution vector and recursively
 check if this move leads to a solution. (A Knight can make maximum
 eight moves. We choose one of the 8 moves in this step).
 b) If the move chosen in the above step doesn't lead to a solution
 then remove this move from the solution vector and try other
 alternative moves. - Backtrack
 c) If none of the alternatives work then return false (Returning false
 will remove the previously added item in recursion and if false is
 returned by the initial call of recursion then "no solution exists" )

 Time Complexity: 8 (possible knight moves) ^ N x N
 */
public class SolveKnightTour {

    private static int N = 6;

    private static void solveKnightTour() {

        int[][] solution = new int[N][N];

        //Initialize matrix with -1
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                solution[i][j] = -1;
            }
        }

        //8 possible move for knight - order starting with x = 2 and y = 1 and going anti clock wise works for N= 8
        int[] xMove = {2, 2, -2, -2, 1, -1, 1, -1};
        int[] yMove = {1, -1, 1, -1, 2, 2, -2, -2};
        //int xMove[] = {2, 1, -1, -2, -2, -1, 1, 2};
        //int yMove[] = {1, 2, 2, 1, -1, -2, -2, -1};

        //Since knight is in first block, mark this move
        solution[0][0] = 0;

        if (!solveKnightTourUtil(0, 0, 1, solution, xMove, yMove)) {
            System.out.println("Solution doesn't exists");
        } else {
            printSolution(solution);
        }
    }

    private static void printSolution(int[][] solution) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean solveKnightTourUtil(int x, int y, int move, int[][] solution, int[] xMove, int[] yMove) {

        //Base case, If all cells are covered
        if (move == N * N) {
            return true;
        }

        //Try all possible moves
        for (int i = 0; i < 8; i++) {

            int nextx = x + xMove[i];
            int nexty = y + yMove[i];

            //If this move is safe then move
            if (isSafe(nextx, nexty, solution)) {

                //Make move
                solution[nextx][nexty] = move;

                if (solveKnightTourUtil(nextx, nexty, move + 1, solution, xMove, yMove)) {
                    return true;
                } else {
                    //Backtrack and try other move
                    solution[nextx][nexty] = -1;
                }
            }
        }
        return false;
    }

    private static boolean isSafe(int x, int y, int[][] solution) {

        return (x >= 0 && x < N && y >= 0 && y < N && solution[x][y] == -1);
    }

    public static void main(String[] args) {
        solveKnightTour();
    }
}
