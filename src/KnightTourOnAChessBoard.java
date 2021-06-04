import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Knight's Tour On A Chess Board

 Problem Statement:
 You are given a rows * cols chessboard and a knight that moves like in normal chess.
 Currently knight is at starting position denoted by start_row th row and start_col th col, and want to
 reach at ending position denoted by end_row th row and end_col th col.

 The goal is to calculate the minimum number of moves that the knight needs to take to get from
 starting position to ending position.

 start_row, start_col, end_row and end_col are 0-indexed.

 Input Format:
 There are six arguments. First is an integer denoting rows, second is an integer denoting cols, third is an integer
 denoting start_row, fourth is an integer denoting start_col, fifth is an integer denoting end_row and sixth is an
 integer denoting end_col.

 Output Format:
 Return an integer.

 If it is possible to reach from starting position to ending position then return minimum number of moves
 that the knight needs to take to get from starting position to ending position.

 If it is not possible to reach from starting position to ending position then return -1.

 Constraints:
 1 <= rows * cols <= 10^5
 0 <= start_row, end_row < rows
 0 <= start_col, end_col < cols

 Sample Test Case:
 Sample Input:
 rows = 5
 cols = 5
 start_row = 0
 start_col = 0
 end_row = 4
 end_col = 1

 Sample Output:
 3

 Explanation:
 3 moves to reach from (0, 0) to (4, 1):
 (0, 0) -> (1, 2) -> (3, 3) -> (4, 1).

 Approach
 1) Do BFS and find the shortest path from starting vertex to all other vertex
 2) Consider every cell as a vertex and the neighbors will be 8 possible valid moves of knight

 Time Complexity: O(rows * cols) BFS TC = O(V + E) here V = all cells
 Space Complexity: distance 2d array O(rows * cols)

 resources/KnightTourOnAChessBoard.jpg
 */
public class KnightTourOnAChessBoard {

    private static int findMinNoOfMoves(int rows, int cols, int startRow, int startCol, int endRow, int endCol) {

        int moves = 8; //The total number of moves knight can take from the given position
        int[] row = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] col = {-1, 1, -2, 2, -2, 2, -1, 1};

        //Base Case
        if (startRow == endRow && startCol == endCol) {
            return 0;
        }

        //(row, col) Queue for BFS
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();

        int[][] distance = new int[rows][cols];

        //Fill the 2d array with -1 default value
        //Fill all the rows with -1
        for(int[] r: distance) {
            Arrays.fill(r, -1);
        }

        //Visited is implied in distance array. -1 means not visited.

        //Starting position can be reached from starting position in 0 moves
        distance[startRow][startCol] = 0;

        queue.add(new Pair<>(startRow, startCol));

        //BFS
        while(!queue.isEmpty()) {
            Pair<Integer, Integer> poppedNode = queue.remove();

            int currRow = poppedNode.getKey();
            int currCol = poppedNode.getValue();

            //Explore all the neighbors
            for(int i = 0; i < moves; i++) {

                int nRow = currRow + row[i];
                int nCol = currCol + col[i];

                if(isValidPosition(nRow, nCol, rows, cols)) {
                    //Not already visited
                    if(distance[nRow][nCol] == -1) {
                        distance[nRow][nCol] = distance[currRow][currCol] + 1;

                        //If reached the end position, return the distance
                        if (nRow == endRow && nCol == endCol) {
                            return distance[endRow][endCol];
                        }

                        queue.add(new Pair<>(nRow, nCol));
                    }
                }
            }
        }
        return -1;
    }

    private static boolean isValidPosition(int nRow, int nCol, int rows, int cols) {
        return (nRow >= 0 && nRow < rows && nCol >= 0 && nCol < cols);
    }

    public static void main(String[] args) {
        System.out.println("The number of minimum moves to reach (4, 1) from (0, 0) " +
                findMinNoOfMoves(5, 5, 0, 0, 4, 1));
    }
}
