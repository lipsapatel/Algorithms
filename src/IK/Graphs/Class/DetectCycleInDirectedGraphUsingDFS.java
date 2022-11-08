package IK.Graphs.Class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Detect cycle in directed graph using DFS.
 *
 * The DFS tree of directed graph will have following edges
 *
 * 1) Tree Edge
    2) Back Edge - The departure time is not set (Ancestor)
    3) Forward Edge - Arrival time of source < Arrival time of neighbor (Descendant)
    4) Cross Edge - Arrival time of source > Arrival time of neighbor
 *
 * Approach
 * 1) Do the DFS
 * 2) Everytime before starting DFS on vertex, increment the time and set arrival time
 * 3) After the dfs is done on the vertex, increment the time and set departure time
 * 4) If there's an edge to an already visited vertex, check if it's a back edge
 * then there is cycle.
 *
 * Time Complexity: O(V + E) Graph construction and traversal
 * Space Complexity: O(V + E) - Graph space and O(V) call stack, visited and arrival, departure time.
 *
 * resources/IK.Graphs.Class.DetectCycleInDirectedGraphUsingDFS.jpg
 */
public class DetectCycleInDirectedGraphUsingDFS {

    private static int vertices;
    private static ArrayList<Integer>[] graph;
    private static int time;

    private static void initializeGraph(int n) {
        vertices = n;
        graph = new ArrayList[n];
        time = 0;

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

    private static boolean detectCycleDFS(int n, ArrayList<ArrayList<Integer>> edges) {
        initializeGraph(n);

        for(ArrayList<Integer> edge: edges) {
            addEdge(edge.get(0), edge.get(1), false);
        }

        boolean[] visited = new boolean[n];
        int[] arrivalTime = new int[n];
        int[] departureTime = new int[n];

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                if(dfs(i, visited, arrivalTime, departureTime)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(int start, boolean[] visited, int[] arrivalTime, int[] departureTime) {
        visited[start] = true;
        arrivalTime[start] = time++;

        for(int w: graph[start]) {
            if(!visited[w]) {
                if(dfs(w, visited, arrivalTime, departureTime)) {
                    return true;
                }
            } else {
                //Cycle = backedge
                if(departureTime[w] == 0) {
                    return true;
                } else if(arrivalTime[start] < arrivalTime[w]) {
                    //Forward Edge
                } else if(arrivalTime[start] > arrivalTime[w]) {
                    //Cross Edge
                }
            }
        }
        departureTime[start] = time++;
        return false;
    }

    public static void main(String[] args) {
        int n = 6;

        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();

        ArrayList<Integer> edge = new ArrayList<>();
        edge.add(0);
        edge.add(2);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(0);
        edge.add(3);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(1);
        edge.add(0);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(1);
        edge.add(3);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(2);
        edge.add(3);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(2);
        edge.add(4);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(4);
        edge.add(0);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(3);
        edge.add(5);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(5);
        edge.add(4);

        edges.add(edge);

        System.out.println("Cycle in directed Graph: " + detectCycleDFS(n, edges));
    }
}
