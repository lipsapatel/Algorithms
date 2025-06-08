package IK.Graphs.Homework2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a two-dimensional maze represented by a character grid, find the shortest path from start to goal cell. You can move vertically or horizontally—but not diagonally—one step at a time.
 *
 * There are six types of cells:
 *
 * '@' for start
 * '+' for goal
 * '.' for land
 * '#' for water
 * uppercase letter for door
 * lowercase letter for key
 * There's exactly one start and one goal cell. Other cells may appear any number of times. Water can never be visited. A door cell can only be visited after visiting a matching key, e.g. key 'a' for door 'A'. Other cells can be visited unconditionally. It is allowed to visit a cell more than once.
 *
 * Example
 * {
 * "grid": [
 * "...B",
 * ".b#.",
 * "@#+."
 * ]
 * }
 * Output:
 *
 * [
 * [2, 0],
 * [1, 0],
 * [1, 1],
 * [0, 1],
 * [0, 2],
 * [0, 3],
 * [1, 3],
 * [2, 3],
 * [2, 2]
 * ]
 * We start at [2, 0] where the start (@) is located, then we go up to [1, 0] where the adjacent piece of land (.) is, and so on. We visit the following cells on the way:
 * @ → . → b → . → . → B → . → . → +
 *
 * To get to the goal we have to walk through door 'B', and for that we have to pass by the matching key 'b' first. We do all of that in the smallest number of steps possible.
 *
 * Notes
 * Input is a list of strings. First character of the first string is cell [0, 0] of the maze, the top-left corner.
 * The second character of that string is cell [0, 1] of the maze.
 * Output is a two-dimensional array of integers with the coordinates of the cells on the shortest path from the start cell to the goal.
 * The first cell in the output must be the start cell and the last must be the goal cell.
 * If there are multiple shortest paths, return any one.
 * We guarantee there will be a path from start to goal.
 * Constraints:
 *
 * 1 <= width of the maze <= 100
 * 1 <= height of the maze <= 100
 * 'a' <= key <= 'j'
 * 'A' <= door <= 'J'
 * Multiple keys and doors of one type are possible
 *
 * Approach
 *
 * 1) We can visit the same cell again with different keys
 *
 * for Example:
 *   . . . . . .
 *   b . . @ B +
 *
 *   Here we start from @ and go to left until we get the key b
 *   Then come along the same path and go through door B and reach target.
 *
 *   Here we are visiting the same cells again
 *   But with different keys
 *
 *   2) So we can visit the same cell again with different key set
 *   3) Store the keys using 1 bit for each key. We have 10 keys so we can store them using 10 bits
 *   4) Bit 1 is set for a, Bit 2 is set for b and so on
 *   5) visited will be 3D array with row, col and keyset
 *   6) Queue will store row, col, keyset and prev to maintain parent reference
 *   7) Do BFS with parent array
 *   8) If reached the target, do dfs for path
 *
 *   TC: O(rc*2^k) where k = keys = 10 - BFS
 *   SC: O(rc*2^k) visited, queue and path
 */
public class ShortestPathIn2DGridWithDoorsandKeys {

    public static class Node {
        int row;
        int col;
        int keySet;
        Node prev;

        public Node(int row, int col, int keySet, Node prev) {
            this.row = row;
            this.col = col;
            this.keySet = keySet;
            this.prev = prev;
        }
    }

    private static ArrayList<ArrayList<Integer>> findShortestPath(ArrayList<String> grid) {

        ArrayList<ArrayList<Integer>> path = new ArrayList<>();

        //Start BFS from @ which is start
        int numRows = grid.size();
        int numCols = grid.get(0).length();

        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                if(grid.get(i).charAt(j) == '@') {
                    bfs(i, j, path, grid);
                    break;
                }
            }
        }
        Collections.reverse(path);
        return path;
    }

    private static void bfs(int row, int col, ArrayList<ArrayList<Integer>> path, ArrayList<String> grid) {
        int numRows = grid.size();
        int numCols = grid.get(0).length();

        boolean[][][] visited = new boolean[numRows][numCols][1024];
        Queue<Node> queue = new LinkedList<>();

        Node start = new Node(row, col, 0, null);
        queue.add(start);

        visited[row][col][0] = true;

        while(!queue.isEmpty()) {
            Node v = queue.remove();

            for(int[] w: getNeighbors(v, grid)) {

                char ngb = grid.get(w[0]).charAt(w[1]);

                if(ngb != '#') { //Not water

                    if((ngb == '.' || ngb == '@') && !visited[w[0]][w[1]][v.keySet]){ //Land or start and not visited, the same cell can be visited again

                        Node a = new Node(w[0], w[1], v.keySet, v);
                        queue.add(a);
                        visited[w[0]][w[1]][v.keySet] = true;


                    } else if(isKey(ngb)) { //Key

                        int key = 1 << (ngb - 'a'); //This set the bit for character
                        int keySet = v.keySet | key; //Do or to set in key set

                        if(!visited[w[0]][w[1]][keySet]) {
                            Node a = new Node(w[0], w[1], keySet, v);
                            queue.add(a);
                            visited[w[0]][w[1]][keySet] = true;
                        }

                    } else if(isDoor(ngb) && !visited[w[0]][w[1]][v.keySet]) { //Door

                        //Should have key
                        int key = 1 << (ngb - 'A');

                        //Doing and will return something > 0 if exists
                        int i = v.keySet & key;

                        //Check if key set contains key
                        if(i > 0) {

                            Node a = new Node(w[0], w[1], v.keySet, v);
                            queue.add(a);
                            visited[w[0]][w[1]][v.keySet] = true;
                        }

                    } else if(ngb == '+') {
                        Node a = new Node(w[0], w[1], v.keySet, v);
                        dfs(path, a);
                        return;
                    }
                }
            }
        }
    }

    private static void dfs(ArrayList<ArrayList<Integer>> path, Node end) {
       if(end != null) {
           path.add(new ArrayList<>(Arrays.asList(end.row, end.col)));
           dfs(path, end.prev);
       }
    }

    private static boolean isKey(char c) {
        if(c >= 'a' && c <= 'j') {
            return true;
        }
        return false;
    }

    private static boolean isDoor(char c) {
        if(c >= 'A' && c <= 'J') {
            return true;
        }
        return false;
    }

    private static List<int[]> getNeighbors(Node v, ArrayList<String> grid) {
        int numRows = grid.size();
        int numCols = grid.get(0).length();

        List<int[]> result = new ArrayList<>();

        int[][] neighbors = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        for(int[] n: neighbors) {
            int r = n[0] + v.row;
            int c = n[1] + v.col;

            if(r >= 0 && r < numRows && c >= 0 && c < numCols) {
                int[] a = {r, c};
                result.add(a);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<String> grid = new ArrayList<>();
        grid.add("...B");
        grid.add(".b#.");
        grid.add("@#+.");

        System.out.println("Shortest path " +findShortestPath(grid));

        ArrayList<String> grid1 = new ArrayList<>();
        grid1.add(".dj##.f.j#efejj..@e#+G.c.");
        grid1.add(".hdI#.#aAghficDe#J.CGa.ba");

        System.out.println("Shortest path " + findShortestPath(grid1));
    }
}
