package IK.Graphs.Class;

import java.util.ArrayList;
import java.util.HashMap;
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
 * 1) Start from cell 0
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
 * problem. You cannot use DFS because you might take longer path first and reach 100.
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
 * Approach
 * 1) Initialize graph of size 101
 * 2) Add edges from 1 to 6 throws and snake and ladder
 * 3) Do BFS and return minimum number of throws.
 * 4) In BFS, initialize visited and distance.
 * 5) Create queue and add start vertex to queue, mark it as visited and set distance to 0
 * 6) While queue is not empty, remove from queue.
 * 7) For all neighbors of v, if not visited then mark as visited, add distance, and add to queue.
 * 8) If neighbor is 100 or target, return distance.
 *
 *
 * Time Complexity: O(V) for BFS and O(V + E) for graph Here V = number of cells which 100 and edges are 6
 * for each vertex so the time complexity is n - number of cells.
 *
 * Space Complexity: O(V + E) or O(n + m) for graph and O(V) for queue
 *
 */
public class SnakeAndLadderProblem {

    //Create board with snake and ladder
    private static HashMap<Integer, Integer> board = new HashMap<>();

    private static int vertices;
    private static ArrayList<Integer>[] graph;

    private static void initializeGraph(int n) {
        vertices = n;
        graph = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    private static void addEdge(int start, int end, boolean bidir) {
        graph[start].add(end);

        if(bidir) {
            graph[end].add(start);
        }
    }

    private static int findMinimumThrowsBFS(int target) {

        initializeGraph(101);

        //Create adjacency list, capture all 6 possible throws.
        for (int i = 0; i < 100; i++) {
            for (int j = 1; j <= 6; j++) {
                if (i + j <= 100) {
                    addEdge(i, board.get(i + j), false);
                }
            }
        }

        //BFS
        return bfs(0, target);
    }

    private static int bfs(int start, int target) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        boolean[] visited = new boolean[101];
        int[] distance = new int[101];

        visited[start] = true;
        distance[start] = 0;

        while(!queue.isEmpty()) {
            int v = queue.remove();

            for(int w: graph[v]) {
                if(!visited[w]) {
                    visited[w] = true;
                    distance[w] = distance[v] + 1;
                    queue.add(w);

                    if(target == w) {
                        return distance[w];
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

        System.out.println("The minimum number of throws required to reach target 100 is " + findMinimumThrowsBFS(100));
    }
}
