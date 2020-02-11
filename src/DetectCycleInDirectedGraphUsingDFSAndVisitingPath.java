import java.util.*;

/**
 * Detect cycle in a directed graph
 *
 * Given a directed graph, write an algorithm to detect if the graph contains cycle or not.
 *
 * resources/DetectCycleInDirectedGraphUsingDFS.jpg
 * resources/DetectCycleInDAGUsingVisitingPath.jpg
 *
 * Approach:
 *
 * 1) Graph contains cycle if there is any back edge.
 * 2) There are two types of back edge:
 *    --> Edge from vertex to itself - self loop
 *    --> Edge from any descendant back to vertex
 *
 * 3) Use Depth First Search to detect back edge.
 * 4) Do DFS from each vertex.
 * 5) Keep the track of visiting vertices/path in visitingPath.
 * 6) If you encounter the visited vertex already present in visitingPath then there is a cycle.
 * 7) Use visited to keep the track of already visited nodes.
 *
 * Difference between visitingPath and visited:
 *
 * 1) visited - keeps the track of already visited vertices.
 * 2) visitingPath - Keeps the list of visiting path vertices.
 *
 * Time Complexity: O(V+E) = O(n + m)
 * Space Complexity: O(V +E) constructing graph, keep track of visited and visitingPath vertices. O(m + n)
 *
 * Detect Cycle In Directed Graph
 Problem Statement:
 Given a directed graph, find out whether it includes a cycle.

 Input Format:
 Function has three arguments:
 N, number of vertices.
 M, number of directed edges.
 edges, a two-dimensional array where each one of M rows represents an edge; integer values in the first and second columns of a row are
 (zero-based) indices of the starting and ending vertices, respectively, of that directed edge.

 Output Format:
  Function must return boolean true if at least one cycle exist in the given graph, otherwise it must return false.

 Constraints:
 2 <= N <= 100000
 1 <= M <= 100000
 Nodes aren’t assigned values explicitly but indexed, i.e. 0, 1, ..., N-1.
 Given graph doesn’t have multiple edges between the same vertices, i.e. if u→v edge is present, another u→v won’t be.
 Given graph can have multiple components, in other words, it might not be a connected graph.

 Sample Test Case:
 Sample Input:
 N = 5, M = 7, edges = [[0,1],[0,3],[1,3],[1,2],[2,3],[4,0],[2,4]]

 Sample Output:
 true

 Explanation:
 Given graph of 5 vertices and 7 directed edges has exactly one cycle, 0→1→2→4→0. One cycle is enough to return true.
 */
public class DetectCycleInDirectedGraphUsingDFSAndVisitingPath {

    private static boolean hasCycle(int n, int m, List<List<Integer>> edges) {

        //Create Graph
        HashMap<Integer, HashSet<Integer>> graph = createGraph(n, edges);

        HashSet<Integer> visited = new HashSet<>();
        HashSet<Integer> visitingPath = new HashSet<>();

        for (int i = 0; i < n; i++) {
            if(!visited.contains(i)) {
                if(dfs(i, visited, visitingPath, graph)) { //this has cycle
                    return true;
                }
            }
        }
        return false;
    }

    private static HashMap<Integer, HashSet<Integer>> createGraph(int n, List<List<Integer>> edges) {
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

        for(int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }

        for(List<Integer> e: edges) {
            HashSet<Integer> neighbors = graph.get(e.get(0));
            neighbors.add(e.get(1));
            graph.put(e.get(0), neighbors);
        }
        return graph;
    }

    private static boolean dfs(Integer s, HashSet<Integer> visited, HashSet<Integer> visitingPath, HashMap<Integer, HashSet<Integer>> graph) {
        visited.add(s);
        visitingPath.add(s);

        HashSet<Integer> neighbors = graph.get(s);

        if(neighbors != null) {
            for(Integer av: neighbors) {
                if(!visited.contains(av)) {
                    if(dfs(av, visited, visitingPath, graph)) { //cycle
                        return true;
                    }
                } else { //already visited vertices, check if it's in the visitingPath
                    if(visitingPath.contains(av)) {
                        return true;
                    }
                }
            }
        }
        visitingPath.remove(s);
        return false;
    }

    public static void main(String[] args) {
        int n = 5;
        int m = 7;
        List<Integer> edge = new ArrayList<>();
        edge.add(0);
        edge.add(1);

        List<List<Integer>> edges = new ArrayList<>();
        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(0);
        edge.add(3);
        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(1);
        edge.add(3);
        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(1);
        edge.add(2);
        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(2);
        edge.add(3);
        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(4);
        edge.add(0);
        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(2);
        edge.add(4);
        edges.add(edge);

        System.out.println("The graph has cycle: " + hasCycle(n, m, edges));
    }
}
