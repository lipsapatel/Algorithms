import java.util.HashMap;
import java.util.HashSet;
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
 * resources/SnakeAndLadder1.png
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
 * vertex 2...vertex 7. Check the board hashmap for snake and ladder. If there's snake or ladder you can go
 * to directly to that cell.
 * 3) Directed edge for snake and ladder
 * 4) Now the problem is reduced to Shortest Path Problem. So using BFS and queue, we can solve the
 * problem.
 * Maintain distance array. Distance[target] will be the answer.
 *
 * Implementation:
 *
 * 1) Create adjacency list since the graph is sparse and not dense.
 * 2) Start from cell 0 and add it to the queue
 * 3) While queue is not empty, pop and get all the neighbors.
 * 4) Add neighbors to queue, mark as visited and update the distance.
 * 5) If we find the target in any of the neighbors, return the updated distance.
 *
 * Time Complexity: O(n) same as BFS which is O(V + E) Here V = number of cells which 100 and edges are 6
 * for each vertex so the time complexity is n - number of cells.
 *
 */
public class SnakeAndLadderProblem {

    //Create board with snake and ladder
    private static HashMap<Integer, Integer> board = new HashMap<>();

    //Graph
    private static HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

    private static int findMinimumThrowsBFS(int start, int target) {

        //Create adjacency list, capture all 6 possible throws.
        for (int i = 0; i < 100; i++) {
            HashSet<Integer> neighbor = new HashSet<>();

            for (int j = 1; j <= 6; j++) {
                if (i + j <= 100) {
                    neighbor.add(board.get(i + j));
                }
            }
            graph.put(i, neighbor);
        }

        //BFS
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, Integer> distance = new HashMap<>();

        queue.add(start);
        visited.add(start);
        distance.put(start, 0);

        while(!queue.isEmpty()) {

            Integer poppedNode = queue.remove();
            HashSet<Integer> adjacentVertex = graph.get(poppedNode);

            if(adjacentVertex != null) {
                for(Integer vertex: adjacentVertex) {
                    if(!visited.contains(vertex)) {
                        queue.add(vertex);
                        visited.add(vertex);
                        distance.put(vertex, distance.get(poppedNode) + 1);

                        if (target == vertex) {
                            return distance.get(vertex);
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        for (int i = 0; i <= 100; i++) {
            board.put(i, i);
        }

        //Ladder
        board.put(1, 38);
        board.put(4, 14);
        board.put(9, 31);
        board.put(21, 42);
        board.put(28, 84);
        board.put(51, 67);
        board.put(72, 91);
        board.put(80, 99);

        //Snake
        board.put(17, 7);
        board.put(62, 19);
        board.put(64, 60);
        board.put(92, 73);
        board.put(95, 75);
        board.put(98, 79);
        board.put(87, 36);
        board.put(54, 34);

        System.out.println("The minimum number of throws required to reach target 100 is " + findMinimumThrowsBFS(0, 100));
    }
}
