package IK.Graphs.Homework2;

import java.util.ArrayList;

/**
 * Given a directed acyclic graph with n nodes, find all the possible paths starting from the node with the value 1 and ending at the node with the value n.
 *
 * Example
 * {
 * "n": 4,
 * "edges": [
 * [1, 2],
 * [1, 3],
 * [2, 3],
 * [2, 4],
 * [3, 4]
 * ]
 * }
 * Graph
 *
 * Output:
 *
 * [
 * [1, 2, 3, 4],
 * [1, 2, 4],
 * [1, 3, 4]
 * ]
 * Notes
 * Return all the possible paths in any order.
 * For a graph with n nodes, the value of all the nodes are distinct and lie in the range [1, n].
 * Edges are given as a list of pairs: a list of lists where each inner list has exactly two elements. Each pair [X, Y] represents a directed edge from X to Y.
 * The list won't contain duplicate edges. That is, no more than one instance of any pair [X, Y] will be present in the input list.
 * Constraints:
 *
 * 1 <= n <= 15
 * 1 <= node value <= n
 * There will be no cycle or self-loop in the graph.
 * 0 <= number of edges <= (n * (n - 1)) / 2
 * 1 <= any number in the input list of edges <= n
 *
 * Approach
 *
 * 1) In order to find all paths between two nodes, do DFS without visited.
 * 2) While doing DFS, add node to list
 * 3) If we reach destination, copy list to list
 * 4) While returning from DFS, remove node from list
 * 5) Start DFS from 1
 *
 * TC: O(n * 2^n) Because there are 2^n paths where every node is having directed edge
 * to every other node
 * Each path has at most n nodes in it
 *
 * O(n + m) - For doing DFS and Graph construction
 *
 * SC: O(n * 2^n) For storing 2^n paths with at most n nodes in it.
 * O(n + m) for Graph O(n) for recursive stack trace
 *
 */
public class AllPathsBetweenTwoNodes {

    private static int vertices;
    private static ArrayList<Integer>[] graph;

    private static void initializeGraph(int n) {
        vertices = n;
        graph = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    private static void addEdge(int start, int end) {
        graph[start].add(end);
    }

    private static ArrayList<ArrayList<Integer>> getAllPaths(int n, ArrayList<ArrayList<Integer>> edges) {
        initializeGraph(n + 1);

        for(ArrayList<Integer> edge: edges) {
            addEdge(edge.get(0), edge.get(1));
        }

        ArrayList<ArrayList<Integer>> pathList = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();

        dfs(1, pathList, path, n);

        return pathList;
    }

    private static void dfs(int start, ArrayList<ArrayList<Integer>> pathList, ArrayList<Integer> path, int destination) {
        path.add(start);

        if(start == destination) {
            ArrayList<Integer> pathCopy = new ArrayList<>();
            pathCopy.addAll(path);
            pathList.add(pathCopy);
        }

        for(int v: graph[start]) {
            dfs(v, pathList, path, destination);
        }

        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();

        ArrayList<Integer> edge = new ArrayList<>();
        edge.add(1);
        edge.add(2);

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
        edge.add(3);
        edge.add(4);

        edges.add(edge);

        System.out.println("All path " + getAllPaths(4, edges));
    }
}
