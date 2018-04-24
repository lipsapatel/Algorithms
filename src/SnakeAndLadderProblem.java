import java.util.LinkedList;
import java.util.Queue;

/**
 * Snake and Ladder Problem
 *
 * Directed Graph - BFS using queue
 * Vertex class - cell, moves
 *
 * Given a snake and ladder game, write a function that returns the minimum number of jumps to take
 * to reach top or destination position.
 *
 * You can assume the dice you throw results in your favor - means you can control dice.
 *
 * Rules:
 *
 * 1) Start from cell 1
 * 2) Throw the dice and whatever number you get, move on the number cells on the board.
 * 3) If you reach a cell which is base of a ladder, then you have to climb up that ladder
 * without dice throw.
 * 4) If you reach a cell which is a mouth of the snake, then you have to go down to the tail
 * of snake without dice throw.
 *
 * resources/SnakeAndLadder.png
 *
 * Minimum moves required to reach end (36th cell) from start (1st cell) = 3
 *
 * 1) First throw on dice, get 2 to reach cell number 3, then take the ladder to reach 16.
 * 2) Second throw on dice to get 5 to cell number 21 and then take ladder to reach 32.
 * 3) Third throw on dice to get 4 to reach cell number 36.
 *
 * Approach:
 *
 * 1) Consider each cell as vertex in directed graph.
 * 2) From cell 1, you can go to cells 2, 3, 4, 5, 6, 7 so vertex 1 will have directed edge towards
 * vertex 2...vertex 7.
 * 3) Directed edge for snake and ladder
 * 4) Now the problem is reduced to Shortest Path Problem. So using BFS and queue, we can solve the
 * problem.
 *
 * Implementation:
 *
 * 1) Each vertex will store 2 information, cell number and number of moves required to reach
 * that cell.
 * 2) Start from cell 1 and add it to the queue
 * 3) While queue is not empty, count all the possible moves from 1 to 6
 * 4) Update the moves to moves +1
 * 5) Update the cell to i and if there's snake or ladder update the cell with board[i]
 * 6) Add it to the queue
 * 7) Maintain the visited[] to avoid going in loops
 * 8) Once reach the end return Vertex.moves
 *
 * Time Complexity: O(n)
 *
 */
public class SnakeAndLadderProblem {

    private static class Vertex {
        int cell;
        int moves;
    }

    private static int findMinimumMoves(int[] board) {

        int size = board.length;

        boolean[] visited = new boolean[size];
        Queue<Vertex> queue = new LinkedList<>();

        //Start from index 0
        Vertex vertex = new Vertex();
        vertex.cell = 0;
        vertex.moves = 0;
        queue.add(vertex);
        visited[0] = true;

        //BFS from cell Number 0
        while (!queue.isEmpty()) {

            vertex = queue.remove();
            int cellNumber = vertex.cell;

            //If reached the end
            if (cellNumber == size - 1) {
                break;
            }

            //Iterate through 1 to 6 moves, all reachable adjacent cells
            for (int i = cellNumber + 1; i <= cellNumber + 6 && i < size; i++) {

                if (!visited[i]) {

                    Vertex currentVertex = new Vertex();
                    currentVertex.moves = vertex.moves + 1;
                    visited[i] = true;

                    //If no snake or ladder
                    if (board[i] == -1) {
                        currentVertex.cell = i;
                    } else {
                        //Snake or ladder
                        currentVertex.cell = board[i];
                    }

                    //Add to queue
                    queue.add(currentVertex);
                }
            }
        }
        return vertex.moves;
    }

    public static void main(String[] args) {

        int size = 36;

        int[] board = new int[size];

        for (int i = 0; i < size; i++) {
            board[i] = -1;
        }

        //Ladders
        board[2] = 15;
        board[14] = 24;
        board[20] = 31;

        //Snakes
        board[11] = 1;
        board[29] = 3;
        board[34] = 21;

        System.out.println("Minimum Dice throws needed to reach to end: " + findMinimumMoves(board));
    }
}
