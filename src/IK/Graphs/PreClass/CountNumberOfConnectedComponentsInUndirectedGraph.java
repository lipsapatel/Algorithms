package IK.Graphs.PreClass;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an undirected graph, find its total number of connected components.
 *
 * Example One
 * Graph
 *
 * {
 * "n": 5,
 * "edges": [[0 ,1], [1, 2], [0, 2], [3, 4]]
 * }
 * Output:
 *
 * 2
 * Example Two
 * Graph
 *
 * {
 * "n": 4,
 * "edges": [[0 , 1], [0 , 3], [0 , 2], [2 , 1], [2 , 3]]
 * }
 * Output:
 *
 * 1
 * Notes
 * Constraints:
 *
 * 1 <= number of nodes <= 105
 * 0 <= number of edges <= 105
 * 0 <= node value < number of nodes
 *
 * Approach
 * Count number of connected components in an undirected graph.
 *
 * 1) It can be done using BFS or DFS Recursive or DFS stack.
 * 2) Initialize Graph
 * 3) Add edge
 * 4) Iterate all the vertices and if not visited, call BFS and increment the counter
 * 5) In BFS, create queue and add start to queue and mark it as visited.
 * 6) While queue is not empty, remove from queue.
 * 7) For all neighbors of that node, if not visited, marked as visited and add to queue
 * 8) Return the count.
 *
 * TC: O(V + E) or O(n + m) - Graph construction and O(E) for visiting all the vertices and their neighbors
 * SC: O(V + E) or O(n + m) - Graph and O(V) for queue.
 *
 * resources/CountConnectedComponents.png
 * resources/CountConnectedComponents1.png
 *
 * If you need to find that whether the graph has Eulerian cycle or Path, we need to first find if the graph is unconnected then
 * the edges should not be split across multiple connected components.
 */
public class CountNumberOfConnectedComponentsInUndirectedGraph {

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

    private static int numberOfConnectedComponents(int n, ArrayList<ArrayList<Integer>> edges) {
        initializeGraph(n);

        for(ArrayList<Integer> edge: edges) {
            addEdge(edge.get(0), edge.get(1), true);
        }

        boolean[] visited = new boolean[n];
        int count = 0;

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                /*int totalVertices = bfs(i, visited);
                count++;
                if(count > 1 && totalVertices > 1) { //If we have more than 1 connected components with edges then there can't be Eulerian cycle or path
                    return false; //There cannot be Eulerian cycle or Eulerian path
                }*/
                bfs(i, visited);
                count++;
            }
        }
        return count;
    }

    private static void /*int*/ bfs(int start, boolean[] visited) {
        //int totalVertices = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        //totalVertices++;

        while(!queue.isEmpty()) {
            int v = queue.remove();

            for(int w: graph[v]) {
                if(!visited[w]) {
                    visited[w] = true;
                    //totalVertices++;
                    queue.add(w);
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 5;
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();

        ArrayList<Integer> edge = new ArrayList<>();
        edge.add(0);
        edge.add(1);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(1);
        edge.add(2);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(0);
        edge.add(2);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(3);
        edge.add(4);

        edges.add(edge);

        System.out.println(numberOfConnectedComponents(n, edges));
    }

}
