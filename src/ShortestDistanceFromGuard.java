import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Shortest Distance From the Guard
 You are given a 2D character grid of size n * m. Each element of the grid is either a GUARD, an OPEN space or a WALL.
 Every GUARD can move up, down, left and right in the open space. They cannot move on the wall.

 Find, for every cell, the distance from the nearest guard cell. Consider -1 as this distance for WALL cells and unreachable cells.

 Input Format:
 Each character in the grid will be G, O or W.
 G - Guard
 O - Open space
 W - Wall

 Output Format:
 Put -1 in case of wall cells and unreachable cells.

 Input Format:
 If n = 5, m = 5 and rowStr = [“OOOOG”, “OWWOO”, “OOOWO”, “GWWWO”, “OOOOG”], then the input should be:

 5
 5
 OOOOG
 OWWOO
 OOOWO
 GWWWO
 OOOOG

 Output Format:
resArr will be of same size of given 2D array i.e. n * m.
 For input n = 5, m = 5 and rowStr = [“OOOOG”, “OWWOO”, “OOOWO”, “GWWWO”, “OOOOG”], output will be:

 3 3 2 1 0
 2 -1 -1 2 1
 1 2 3 -1 2
 0 -1 -1 -1 1
 1 2 2 1 0

 Constraints:
 1 <= n, m <= 10^3

 Sample Input 1:

 5
 5
 OOOOG
 OWWOO
 OOOWO
 GWWWO
 OOOOG

 Sample Output 1:

 3 3 2 1 0
 2 -1 -1 2 1
 1 2 3 -1 2
 0 -1 -1 -1 1
 1 2 2 1 0

 Explanation 1:
 All the walls are -1. All other cells have the minimum distance to a Guard.

 Sample Test Case 2:

 1
 5
 GWOWG

 Sample Output 2:

 0 -1 -1 -1 0

 Explanation 2:
 Open space in the middle is unreachable for the guards hence they have value -1.

 ********************************************************************************************
 Solution:
 Description:
 The idea is to consider the given board as a graph. Each cell has 4 neighbors, top, bottom, left and right. We cannot go to a wall neighbor.
 If we have only one guard then the problem becomes a straight-forward problem for BFS. We will put that guard in the queue
 at the start and keep running breadth-first search and denote the distance to all the cells from that guard.
 Places which cannot be reached will be marked -1.

 When there are more than one guards, we run a multi-source BFS. In multi-source breadth-first search,
 we add all the guards to the queue at the start with 0 distance. Then with every move to the neighbor,
 we add 1 to the original distance of the respective guard. We keep on storing the minimum distance of that cell
 from the guard when we reach a cell first time.

 For better understanding of multi source concept:
 https://www.geeksforgeeks.org/multi-source-shortest-path-in-unweighted-graph/

 Time Complexity (assuming that input arguments are already given and excluding time used in the declaration of output):
 O(n * m) where n denotes the number of rows of the grid and m denotes the number of columns of the grid.
 As we are iterating complete grid twice. Once while checking possible guards and adding them in the queue.
 And second time while calculating distances with the help of queue as we are maintaining visited 2d array to avoid visiting already
 visited indices. Hence total complexity will be 2 * O(n * m) → O(n * m).

 Time Complexity:
 O(n * m) where n denotes the number of rows of the grid and m denotes the number of columns of the grid.
 As time complexity assuming that input arguments are already given and excluding time used in the declaration of output is O(n * m),
 for reading input it will take O(n * m) as we are reading grid of size n * m, for declaration of output it will take O(n * m)
 as we are initializing a 2d array of integers of size n * m. Hence, total time complexity will be 3 * O(n * m) → O(n * m).

 Auxiliary Space Used:
 O(n * m) where n denotes the number of rows of the grid and m denotes the number of columns of the grid.
 As we are maintaining visited 2d array to avoid visiting already visited indices, space to store it will take O(n * m) and
 in worst case (when all are guards) queue will have n*m elements hence space for that will be O(n * m).
 Hence, the total auxiliary space used will be 2 * O(n * m) → O(n * m).

 Space Complexity:
 O(n * m) where n denotes the number of rows of the grid and m denotes the number of columns of the grid.
 To store input, it will take O(n * m) as we are storing grid of size n * m, auxiliary space used is O(n * m),
 space for output will be O(n * m) hence 3 * O(n * m) → O(n * m).

 *****************************************************************************************************************
 Approach

 1) Multi source BFS approach
 2) Add all the guard as source in queue.
 3) Do normal BFS
 4) while returning neighbors, return only valid neighbors (open)
 5) TC = O(rows * cols)
 SC = O(rows * cols)

 resources/ShortestDistanceFromGuard.jpg
 */
public class ShortestDistanceFromGuard {

    public static List<List<Integer>> findShortestDistanceFromGuard(List<List<Character>> grid) {

        int rows = grid.size();
        int cols = grid.get(0).size();

        //Create distance list
        List<List<Integer>> distance = new ArrayList<>();

        //Initialize with -1
        for(int i = 0; i < rows; i++) {
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < cols; j++) {
                list.add(-1);
            }
            distance.add(list);
        }

        //visited array
        boolean[][] visited = new boolean[rows][cols];

        //Find all guards and add to queue, visited and distance
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(grid.get(i).get(j) == 'G') {
                    int[] array = new int[2];
                    array[0] = i;
                    array[1] = j;
                    queue.add(array);
                    visited[i][j] = true;
                    distance.get(i).set(j, 0);
                }
            }
        }

        //BFS
        while(!queue.isEmpty()) {
            int[] poppedCell = queue.remove();
            int poppedCellDistance = distance.get(poppedCell[0]).get(poppedCell[1]);

            List<int[]> neighbors = getValidNeighbors(poppedCell, grid);

            if(neighbors != null) {
                for(int[] av: neighbors) {
                    int row = av[0];
                    int col = av[1];

                    if(!visited[row][col]) {
                        queue.add(av);
                        visited[row][col] = true;
                        distance.get(row).set(col, poppedCellDistance + 1);
                    }
                }
            }
        }
        return distance;
    }

    private static int moves = 4;
    private static int[] rows = {1, -1, 0, 0};
    private static int[] cols = {0, 0, 1, -1};

    private static List<int[]> getValidNeighbors(int[] poppedCell, List<List<Character>> grid) {
        List<int[]> neighbors = new ArrayList<>();

        int r = poppedCell[0];
        int c = poppedCell[1];

        for(int i = 0; i < moves; i++) {
            int nr = r + rows[i];
            int nc = c + cols[i];

            if (nr >= 0 && nr < grid.size() && nc >= 0 && nc < grid.get(0).size()) {
                if(grid.get(nr).get(nc) == 'O') {
                    int[] array = new int[2];
                    array[0] = nr;
                    array[1] = nc;
                    neighbors.add(array);
                }
            }
        }
        return neighbors;
    }

    public static void main(String[] args) {
        List<List<Character>> input = new ArrayList<>();

        List<Character> row = new ArrayList<>();
        row.add('O');
        row.add('O');
        row.add('O');
        row.add('O');
        row.add('G');
        input.add(row);

        row = new ArrayList<>();
        row.add('O');
        row.add('W');
        row.add('W');
        row.add('O');
        row.add('O');
        input.add(row);

        row = new ArrayList<>();
        row.add('O');
        row.add('O');
        row.add('O');
        row.add('W');
        row.add('O');
        input.add(row);

        row = new ArrayList<>();
        row.add('G');
        row.add('W');
        row.add('W');
        row.add('W');
        row.add('O');
        input.add(row);

        row = new ArrayList<>();
        row.add('O');
        row.add('O');
        row.add('O');
        row.add('O');
        row.add('G');
        input.add(row);

        List<List<Integer>> distance = findShortestDistanceFromGuard(input);

        for(List<Integer> r: distance) {
            System.out.println();
            for(int c: r) {
                System.out.print(c + " ");
            }
        }
    }
}
