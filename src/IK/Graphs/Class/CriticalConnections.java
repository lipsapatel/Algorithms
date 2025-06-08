package IK.Graphs.Class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1192. Critical Connections in a Network
 * Hard
 *
 * Add to List
 *
 * Share
 * There are n servers numbered from 0 to n - 1 connected by undirected
 * server-to-server connections forming a network where connections[i] = [ai, bi]
 * represents a connection between servers ai and bi. Any server can reach other
 * servers directly or indirectly through the network.
 *
 * A critical connection is a connection that, if removed, will make some
 * servers unable to reach some other server.
 *
 * Return all critical connections in the network in any order.
 *
 *
 * Example 1:
 *
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 * Example 2:
 *
 * Input: n = 2, connections = [[0,1]]
 * Output: [[0,1]]
 *
 * Constraints:
 *
 * 2 <= n <= 105
 * n - 1 <= connections.length <= 105
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * There are no repeated connections.
 *
 * Approach
 *
 * 1) We need to find deepest back edge from a node. If there is no deepest
 * backedge, then connection between parent and node is critical
 * 2) Make sure you don't consider node visiting its own parent as dbe
 * 3) There is no dbe possible from start node because it has no parent
 * 4) Do DFS with arrival time
 * 5) Initialize dbe with arrival time of node.
 * 6) Take min dbe from all neighbors and backedge starting from that vertex.
 * 7) Recursive dfs return dbe
 * 8) When the node completed it's recursive call and going back, find if dbe == arr[node]
 * 9) If it is then its critical connection.
 *
 * Watch video - https://youtu.be/bmyyxNyZKzI
 *
 * TC:O(V + E)
 * SC: O(V + E) Graph construction, parent, arrival time and visited array
 */
public class CriticalConnections {

    private static int vertices;
    private static ArrayList<Integer>[] graph;
    private static int time;

    private static void initializeGraph(int n) {
        vertices = n;
        time = 0;
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

    private static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        initializeGraph(n);

        for(List<Integer> edge: connections) {
            addEdge(edge.get(0), edge.get(1), true);
        }

        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent, - 1);

        int[] arr = new int[n];
        List<List<Integer>> criticalConnections = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(i, visited, parent, arr, criticalConnections);
            }
        }
        return criticalConnections;
    }

    private static int dfs(int start, boolean[] visited, int[] parent, int[] arr, List<List<Integer>> criticalConnections) {
        visited[start] = true;
        arr[start] = time++;
        int dbe = arr[start];

        for(int w: graph[start]) {
            if(!visited[w]) {
                parent[w] = start;
                dbe = Math.min(dbe, dfs(w, visited, parent, arr, criticalConnections));
            } else {
                //Back-edge
                if(parent[start] != w) {
                    dbe = Math.min(dbe, arr[w]);
                }
            }
        }

        //Critical connection
        if(dbe == arr[start]) {
            if(parent[start] != -1) { // for root node parent is -1 and there's no dbe
                List<Integer> edge = new ArrayList<>();
                edge.add(start);
                edge.add(parent[start]);
                criticalConnections.add(edge);
            }
        }
        return dbe;
    }

    public static void main(String[] args) {
        int n = 4;
        List<List<Integer>> connections = new ArrayList<>();

        List<Integer> edge = new ArrayList<>();
        edge.add(0);
        edge.add(1);

        connections.add(edge);

        edge = new ArrayList<>();
        edge.add(1);
        edge.add(2);

        connections.add(edge);

        edge = new ArrayList<>();
        edge.add(2);
        edge.add(0);

        connections.add(edge);

        edge = new ArrayList<>();
        edge.add(1);
        edge.add(3);

        connections.add(edge);

        System.out.println("Critical connections: " + criticalConnections(n, connections). toString());
    }
}
