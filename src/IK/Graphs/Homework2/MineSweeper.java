package IK.Graphs.Homework2;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the current state of the Minesweeper game and a position of an unrevealed square that we click on the board,
 * return the next state of the game after revealing that square.
 *
 * The following symbols represent a game board of Minesweeper.
 *
 * M represents an unrevealed mine.
 * E represents an unrevealed empty square.
 * B represents a revealed blank square that has no mines at its adjacent eight squares.
 * A digit from 1 to 8 represents how many mines are adjacent to that revealed square
 * X represents a revealed mine.
 * Follow the following rules to generate the next state of the game.
 *
 * If a mine M is revealed, the game is over. In this case, change it to X.
 * If an empty square E with at least one adjacent mine is revealed, change it to a digit (1 to 8) representing the number of adjacent mines.
 * Here we don't make recursive call.
 * If an empty square E with no adjacent mines is revealed, change it to a revealed blank square B and recursively reveal all of its
 * adjacent unrevealed squares.
 * Return the board when no more squares are revealed.
 * Example One
 * {
 * "position": [0, 0],
 * "board": [
 * ["E", "E", "E", "E", "E"],
 * ["E", "E", "E", "E", "E"],
 * ["E", "E", "M", "E", "E"],
 * ["E", "E", "E", "E", "E"],
 * ["E", "E", "E", "E", "E"]
 * ]
 * }
 * Output:
 *
 * [
 * ["B", "B", "B", "B", "B"],
 * ["B", "1", "1", "1", "B"],
 * ["B", "1", "M", "1", "B"],
 * ["B", "1", "1", "1", "B"],
 * ["B", "B", "B", "B", "B"]
 * ]
 * Example Two
 * {
 * "position": [1, 1],
 * "board": [
 * ["E", "E", "E", "E", "E"],
 * ["E", "E", "E", "E", "E"],
 * ["E", "E", "M", "E", "E"],
 * ["E", "E", "E", "E", "E"],
 * ["E", "E", "E", "E", "E"]
 * ]
 * }
 * Output:
 *
 * [
 * ["E", "E", "E", "E", "E"],
 * ["E", "1", "E", "E", "E"],
 * ["E", "E", "M", "E", "E"],
 * ["E", "E", "E", "E", "E"],
 * ["E", "E", "E", "E", "E"]
 * ]
 * Example Three
 * {
 * "position": [2, 2],
 * "board": [
 * ["E", "E", "E", "E", "E"],
 * ["E", "E", "E", "E", "E"],
 * ["E", "E", "M", "E", "E"],
 * ["E", "E", "E", "E", "E"],
 * ["E", "E", "E", "E", "E"]
 * ]
 * }
 * Output:
 *
 * [
 * ["E", "E", "E", "E", "E"],
 * ["E", "E", "E", "E", "E"],
 * ["E", "E", "X", "E", "E"],
 * ["E", "E", "E", "E", "E"],
 * ["E", "E", "E", "E", "E"]
 * ]
 * Notes
 * Constraints:
 *
 * 1 <= number of rows and columns in the board <= 100
 * Any cell of the board will contain one of the following characters: "M", "E", "B", or a digit from "1" to "8".
 * The given position that we will click on the board is either "M" or "E".
 *
 * Approach
 * 1) Do DFS starting with the given position
 * 2) Given position, if the cell has "M" then change to "X" and return matrix
 * 3) If the current cell is E, then check the count of "M" it is surrounded by.
 * 4) For all the neighbors, if mineCount > 0 then replace the cell with count
 * 5) Else replace with "B" and make recursive DFS call for all of its neighbor
 *
 * TC:O(rc)
 * SC: O(rc) for recursive call stack
 */
public class MineSweeper {

    private static ArrayList<ArrayList<String>> getNextState(ArrayList<Integer> position, ArrayList<ArrayList<String>> board) {
        int numRows = board.size();
        int numCols = board.get(0).size();

        int row = position.get(0);
        int col = position.get(1);

        int[] start = {row, col};

        if(board.get(start[0]).get(start[1]).equals("M")) {
            board.get(start[0]).set(start[1], "X");
            return board;
        }

        dfs(start, board);
        return board;
    }

    private static void dfs(int[] start, ArrayList<ArrayList<String>> board) {

        if(board.get(start[0]).get(start[1]).equals("E")) {

            //count adjacent mines
            int mineCount = 0;
            for(int[] w: getNeighbors(start, board)) {

                if(board.get(w[0]).get(w[1]).equals("M")) {
                    mineCount++;
                }
            }

            if(mineCount > 0) {
                board.get(start[0]).set(start[1], "" + mineCount);
            } else {
                board.get(start[0]).set(start[1], "B");
                for(int[] w: getNeighbors(start, board)) {
                    dfs(w, board);
                }
            }
        }
    }

    private static List<int[]> getNeighbors(int[] start, ArrayList<ArrayList<String>> board) {
        int numRows = board.size();
        int numCols = board.get(0).size();

        int[][] neighbors = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        List<int[]> result = new ArrayList<>();

        for(int[] n: neighbors) {
            int r = n[0] + start[0];
            int c = n[1] + start[1];

            if(r >= 0 && r < numRows && c >= 0 && c < numCols) {
                int[] a = {r, c};
                result.add(a);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<String>> board = new ArrayList<>();

        ArrayList<String> row = new ArrayList<>();
        row.add("E");
        row.add("E");
        row.add("E");
        row.add("E");
        row.add("E");

        board.add(row);

        row = new ArrayList<>();
        row.add("E");
        row.add("E");
        row.add("E");
        row.add("E");
        row.add("E");

        board.add(row);

        row = new ArrayList<>();
        row.add("E");
        row.add("E");
        row.add("M");
        row.add("E");
        row.add("E");

        board.add(row);

        row = new ArrayList<>();
        row.add("E");
        row.add("E");
        row.add("E");
        row.add("E");
        row.add("E");

        board.add(row);

        row = new ArrayList<>();
        row.add("E");
        row.add("E");
        row.add("E");
        row.add("E");
        row.add("E");

        board.add(row);

        ArrayList<Integer> position = new ArrayList<>();
        position.add(0);
        position.add(0);

        System.out.println("The next state " + getNextState(position, board));
    }
}
