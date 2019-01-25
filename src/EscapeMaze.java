/**
 * A Maze is given as N*N binary matrix.
 * source = upper left most block
 * destination = lower rightmost block
 *
 * 0 means block is dead end
 * 1 menas the block can be used in the path
 *
 * resources/EscapeMaze.png
 *
 * Binary Matrix representation of the above maze
 *
 * {1, 0, 0, 0}
 {1, 1, 0, 1}
 {0, 1, 0, 0}
 {1, 1, 1, 1}

 resources/EscapeMazeSolution.png

 Following is the solution matrix

 {1, 0, 0, 0}
 {1, 1, 0, 0}
 {0, 1, 0, 0}
 {0, 1, 1, 1}

 If destination is reached
 print the solution matrix
 Else
 a) Mark current cell in solution matrix as 1.
 b) Move forward in the horizontal direction and recursively check if this
 move leads to a solution.
 c) If the move chosen in the above step doesn't lead to a solution
 then move down and check if this move leads to a solution.
 d) If none of the above solutions works then unmark this cell as 0
 (BACKTRACK) and return false.

 */
public class EscapeMaze {

    private static final int N = 4;

    private static void printSolution(int[][] solution) {

        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < N; j++) {

                System.out.print( " " + solution[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isSafe(int[][] maze, int x, int y) {

        return ( x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1);
    }

    private static boolean solveMaze(int[][] maze) {

        int[][] solution = {{0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}};

        if (!solveMazeHelper(maze, 0, 0, solution)) {
            System.out.println("Solution doesn't exists");
            return false;
        }

        printSolution(solution);
        return true;
    }


    private static boolean solveMazeHelper(int[][] maze, int x, int y, int[][] solution) {

        //Base case, If reached the destination return true
        if (x == N - 1 && y == N - 1) {
            solution[x][y] = 1;
            return true;
        } else {
            //Recursive case
            if (isSafe(maze, x, y)) {
                solution[x][y] = 1;                                                //Choose

                if (solveMazeHelper(maze, x + 1, y, solution)) {                //Explore right
                    return true;
                }

                if (solveMazeHelper(maze, x, y + 1, solution)) {                //Explore down
                    return true;
                }

                solution[x][y] = 0;                                               //Backtrack, Unchoose
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int[][] maze = {{1, 0, 0, 0},
                        {1, 1, 0, 1},
                        {0, 1, 0, 0},
                        {1, 1, 1, 1}};

        solveMaze(maze);
    }
}
