package IK.Graphs.Class;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an undirected graph, return true if and only if it's bipartite.
 *
 * A graph is bipartite if we can split it's set of nodes into two independent subsets A and B
 * such that every edge in the graph have one node in A and other in B.
 *
 * The graph is not bipartite if there is odd cycle.
 * Odd cycle is cycle in graph which has odd number of edges and vertices.
 *
 * The graph is not bipartite if there's a cross edge at the same level.
 * The cross edge at the same level indicates odd cycle.
 * The cross edge at the same level have same distance and it has odd number of edges.
 *
 * The cross edge at same level has 2k + 1 edges
 * The cross edge at adjacent level has k + 1 + k - 1 = 2k edges *
 *
 * Approach
 *
 * Maintain the distance array.
 *
 * 1) If there's any cross edge, check it's distance with parent, if it's same then the graph
 * is not bipartite because there is odd cycle.
 * 2) Do the BFS and maintain the distance array.
 * 3) If the node is already visited, check if it's distance is same.
 * 4) If it's same then the graph is not bipartite.
 * 5) The outer for loop, calls BFS which returns true if there is odd cycle
 * 6) If it returns true, then return false
 * 7) Outside outer for loop return true
 * 8) If you are asked to create two sets A and B, then level 0 goes in set A, level 1 goes in set B, level 2 goes in set A
 * 9) Levels goes in set A and B alternatively. If you find odd cycle then return true. At that point the set don't matter because the graph cannot be bipartite.
 *
 * In a bipartite graph, all cycles must be of even length.
 * If the graph is not bipartite then the cycle is of odd length.
 *
 * Time Complexity: O(E + V) same as BFS
 * Space Complexity: O(V + E)
 *
 *
 * Different way of giving same problem.
 * Can the vertices of a given graph G be colored using only two colors so that adjacent vertices have different colors?
 * Also called 2-color problem
 *
 * ****************************************************************************************************
 * Different way of giving same problem
 * Note here vertices are from 1 to n. But having vertex 0 is not going to affect our solution
 *
 * We want to split a group of n people (labeled from 1 to n) into two groups of any size.
 * Each person may dislike some other people, and they should not go into the same group.
 *
 * Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that
 * the person labeled ai does not like the person labeled bi, return true if it is possible
 * to split everyone into two groups in this way.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4] and group2 [2,3].
 * Example 2:
 *
 * Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 * Example 3:
 *
 * Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2000
 * 0 <= dislikes.length <= 104
 * dislikes[i].length == 2
 * 1 <= dislikes[i][j] <= n
 * ai < bi
 * All the pairs of dislikes are unique.
 */
public class IsGraphBipartite {

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

    private static boolean isGraphBipartite(int n, ArrayList<ArrayList<Integer>> edges) {
        initializeGraph(n);

        for(ArrayList<Integer> edge: edges) {
            addEdge(edge.get(0), edge.get(1), true);
        }

        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        int[] distance = new int[n];

        //Two set a and b
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                if (bfsOddCycle(i, visited, parent, distance, a, b)) {
                    return false;
                }
            }
        }

        System.out.println(a.toString());
        System.out.println(b.toString());

        return true;
    }

    private static boolean bfsOddCycle(int start, boolean[] visited, int[] parent, int[] distance, ArrayList<Integer> a, ArrayList<Integer> b) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        distance[start] = 0;

        while(!queue.isEmpty()) {
            int v = queue.remove();

            if(distance[v] % 2 == 0) { //Levels alternate and goes in set a and b
                a.add(v);
            } else {
                b.add(v);
            }

            for(int w: graph[v]) {
                if(!visited[w]) {
                    visited[w] = true;
                    parent[w] = v;
                    queue.add(w);
                    distance[w] = distance[v] + 1;
                } else {
                    //cycle
                    if(w != parent[v]) { // This if condition can be omitted because even if I am looking at my own parent, it's at different level and it's not going to create problem, the graph will be still bipartite
                        if (distance[w] == distance[v]) {
                            return true; //odd cycle
                        }
                    }
                }
            }
        }
        return false;
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
        edge.add(2);
        edge.add(3);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(3);
        edge.add(4);

        edges.add(edge);

        edge = new ArrayList<>();
        edge.add(4);
        edge.add(0);

        edges.add(edge);

        System.out.println(isGraphBipartite(n, edges));
    }


}
