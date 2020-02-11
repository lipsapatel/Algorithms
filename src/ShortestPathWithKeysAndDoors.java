import java.util.*;

/**
 * Problem Statement:
 Given a two-dimensional grid of size n * m, a start cell and a goal cell, you have to find the shortest path from the start to the goal.
 You can go up, down, left or right from a cell, but not diagonally.
 Each cell in the grid can be either land or water or door or key or start or goal.
 You can travel on any cells but water.
 Every key and every door belongs to a particular “type”. For example a key of type ‘a’ can open a door of type ‘A’
 but not a door of type ‘B’. There can be more than one key of a particular type in the maze,
 all keys of one type are equivalent. Similarly there can be more than one door of any type.
 You cannot travel through a door unless you have picked up a key of the corresponding type before.
 A key is picked up by visiting its cell. If you have picked up a certain type of key, you can then travel through any doors
 of that type any number of times.

 It is allowed to revisit a cell.

 Input Format:
 Function has one argument: an array of strings called grid. String grid[0] is the top row of cells in the maze.
 First character in that string represents the top-left corner of the maze, the [0, 0] position.

 Characters in the strings can be either:
 '#' = Water.
 '.' = Land.
 'a' = Key of type 'a'. Any lowercase character represents a key.
 'A' = Door that can be opened with key 'a'. Any uppercase character represents a door.
 '@' = Starting cell.
 '+' = The goal cell.

 Output Format:
 Function must return a two-dimensional array of integers describing the shortest path from the start cell to the goal cell.
 First cell in the path must be the start cell, last cell must be the goal cell.
 Size of the array must be p * 2 where p is the number of cells in the path. array[i][0] and array[i][1] are
 the x and y coordinates of the i-th cell in the path.
 Just like in the input, [0, 0] is the top-left corner of the maze, [0, 1] is the second cell in the top row.

 If there are multiple shortest paths, any one of them will be accepted as a correct answer.

 Constraints:
 1 <= n, m <= 100
 There will be exactly one start cell and one goal cell.
 It is guaranteed that there exists a path from start to goal.
 'a' <= key <= 'j'
 'A' <= door <= 'J'

 Sample Input:
 grid = [
 "...B",
 ".b#.",
 "@#+."
 ]

 Sample Output:
 [
 [2, 0],
 [1, 0],
 [1, 1],
 [0, 1],
 [0, 2],
 [0, 3],
 [1, 3],
 [2, 3],
 [2, 2]
 ]
 [2, 0] are the coordinates of the start cell '@', [2, 2] are the coordinates of the goal cell ‘+’.
 In order to pass through door 'B' we first need to pick up key 'b' to open that door so we must go like this:
 '@' -> '.' -> 'b' -> '.' -> '.' -> 'B' -> '.' -> '.' -> '+'

 Approach

 1) Use BFS to find the shortest path
 2) Keep the track of keyRing, you can traverse the cell again with different keyRing but not with same keyRing.
 3) Since there are 10 keys, we can use 0000000000 to represent the different keys in the keyRing.
 4) Total combination of keyRings will be 2 ^ 10 = 1024
 5) Each cell is the vertex and we traverse each cell with different combinations of keyRing
 6) TC = O(rows * cols * 2^keys)
 7) visited will be 3D array which has row, col and keyRing.
 8) So the SC = O(rows * cols * 2^keys)

 resources/ShortestPathWithKeysAndDoors.jpg
 */
public class ShortestPathWithKeysAndDoors {

    private static char[][] grid;
    private static List<int[]> paths = new ArrayList<>();

    //Total moves from a given cell
    private static int moves = 4;
    private static int[] rows = {1, -1, 0, 0};
    private static int[] cols = {0, 0, 1, -1};

    public static class Cell {
        int row;
        int col;
        int dist;
        int keyRing;
        Cell parent;

        public Cell(int row, int col, int dist, int keyRing, Cell parent) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.keyRing = keyRing;
            this.parent = parent;
        }
    }

    private static int[][] findShortestPath(String[] g) {

        //Build the grid and return start cell
        Cell startCell = createGrid(g);

        //Do the bfs from start cell
        bfs(startCell);

        //Return paths
        Collections.reverse(paths);
        return paths.toArray(new int[paths.size()][2]);
    }

    private static Cell createGrid(String[] g) {
        int rowLength = g.length;
        int colLength = g[0].length();
        Cell startCell = null;

        grid = new char[rowLength][colLength];

        for(int i = 0; i < rowLength; i++) {
            for(int j = 0; j < colLength; j++) {
                grid[i][j] = g[i].charAt(j);

                if(grid[i][j] == '@') {
                    startCell = new Cell(i, j, 0, 0, null);
                }
            }
        }
        return startCell;
    }

    private static void bfs(Cell startCell) {
        //0000000000 - 10 bits for keyRing
        boolean[][][] visited = new boolean[grid.length][grid[0].length][1024];
        Queue<Cell> queue = new LinkedList<>();
        queue.add(startCell);
        visited[startCell.row][startCell.col][startCell.keyRing] = true;

        while(!queue.isEmpty()) {
            Cell poppedCell = queue.remove();
            char poppedChar = grid[poppedCell.row][poppedCell.col];

            //Reached the goal cell
            if(poppedChar == '+') {
                buildPaths(poppedCell);
                return;
            }

            for(Cell neighbor: getValidNeighbors(poppedCell)) {
                int nRow = neighbor.row;
                int nCol = neighbor.col;

                if(!visited[nRow][nCol][neighbor.keyRing]) {
                    visited[nRow][nCol][neighbor.keyRing] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    private static List<Cell> getValidNeighbors(Cell poppedCell) {
        List<Cell> neighbors = new ArrayList<>();

        for (int i = 0; i < moves; i++) {
            int nRow = poppedCell.row + rows[i];
            int nCol = poppedCell.col + cols[i];

            //is valid neighbors
            if(nRow >= 0 && nRow < grid.length && nCol >= 0 && nCol < grid[0].length) {

                char nChar = grid[nRow][nCol];

                //If it's water do nothing
                if(nChar == '#') {
                    continue;
                }

                //Door, check if the keyRing is available, if not do nothing
                //Door A = 0000000001 keyRing = 0000000010, do and since we need both available
                if(nChar >= 'A' && nChar <= 'J') {
                    if((poppedCell.keyRing & (1 << nChar - 'A')) == 0) {
                        continue;
                    }
                }

                Cell nCell = new Cell(nRow, nCol, poppedCell.dist + 1, poppedCell.keyRing, poppedCell);

                //Key - Update the keyRing
                if(nChar >= 'a' && nChar <= 'j') {
                    nCell.keyRing = poppedCell.keyRing | (1 << nChar - 'a');
                }
                neighbors.add(nCell);
            }
        }
        return neighbors;
    }

    private static void buildPaths(Cell poppedCell) {
        while(poppedCell != null) {
            paths.add(new int[]{poppedCell.row, poppedCell.col});
            poppedCell = poppedCell.parent;
        }
    }

    public static void main(String[] args) {
        String[] g = {"...B", ".b#.", "@#+."};
        int[][] path = findShortestPath(g);

        for(int[] p: path) {
            System.out.println(Arrays.toString(p));
        }
    }
}
