package IK.Graphs.Homework2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Longest Path In Weighted DAG
 * Given a weighted directed acyclic graph (DAG), find the longest path between two nodes.
 *
 * Example
 * Example one
 *
 * {
 * "dag_nodes": 4,
 * "dag_from": [1, 1, 1, 3],
 * "dag_to": [2, 3, 4, 4],
 * "dag_weight": [2, 2, 4, 3],
 * "from_node": 1,
 * "to_node": 4
 * }
 * Output:
 *
 * [1, 3, 4]
 * Total there are two paths from node 1 to node 4:
 *
 * 1 -> 4 with length 4.
 * 1 -> 3 -> 4 with length 2 + 3 = 5.
 * The latter is the longest one.
 *
 * Notes
 * The first four arguments of the function - dag_nodes, dag_from, dag_to, dag_weight - together define the given weighted DAG:
 * there are dag_nodes nodes and there is an edge from dag_from[i] node to dag_to[i] node with length dag_weight[i] for 0 <= i <= dag_nodes - 1.
 * Return an array of integers, the nodes in the longest paths from from_node to to_node (including both ends).
 * If from_node = to_node, return [from_node].
 * If there are multiple longest paths, return any one.
 * Constraints:
 *
 * There will be at most one edge connecting any pair of nodes in one direction, i.e. no multi edges.
 * to_node is reachable from from_node.
 * 1 <= dag_nodes <= 450
 * 1 <= dag_from[i], dag_to[i], from_node, to_node <= dag_nodes
 * 1 <= dag_weight[i] <= 2 * 109
 * Total number of edges in the graph <= (dag_nodes * (dag_nodes - 1)) / 2
 *
 * resources/LongestPathInDAG.jpg
 * resources/LongestPathInDAG1.png
 *
 * Approach
 * 1) To find longest path in weighted DAG, we can use DFS but that will take exponential time as shown in
 * png image.
 * 2) So we need to construct graph and find topological order
 * 3) Then we can iterate the topological order starting from the start vertex until we get end vertex.
 * 4) We update the max distance and parent
 * 5) Take distance as double because 2 x 10^9 + 2 X 10^9 results in integer max
 * 6) Initialize distance with -1 except the from node
 * 7) int parent array and initialize with -1
 * 8) Do dfs to get the path from parent array
 *
 * TC: O(V + E) for topological sort and getting max distance
 * O(number of nodes + number of edges).
 *
 * Given the constraints it is equivalent to O(number of edges).
 * Note that the complexities aren not equal to just O(numberber of nodes) because the number of edges
 * in a DAG can be way larger than the number of nodes.
 *
 * SC: O(V + E) for parent, distance, topoOrder and recursive call stack for DFS, graph construction
 *
 */
public class LongestPathInWeightedDAG {

    private static int vertices;
    private static HashMap<Integer, HashMap<Integer, Integer>> graph;

    private static void initializeGraph(int n) {
        vertices = n;
        graph = new HashMap<>();

        for(int i = 0; i < n; i++) {
            graph.put(i, new HashMap<>());
        }
    }

    private static void addEdge(int start, int end, int weight) {
        graph.get(start).put(end, weight);
    }

    private static ArrayList<Integer> findLongestPath(int dagNodes, ArrayList<Integer> fromNodes, ArrayList<Integer> toNodes,
                                                      ArrayList<Integer> weight, int from, int to) {
        //Construct graph
        int n = dagNodes + 1;
        initializeGraph(n);

        for(int i = 0; i < fromNodes.size(); i++) {
            addEdge(fromNodes.get(i), toNodes.get(i), weight.get(i));
        }

        //Get Topological order
        ArrayList<Integer> topoOrder = new ArrayList<>();
        boolean[] visited = new boolean[n];

        for(int i = 1; i < n; i++) {
            if(!visited[i]) {
                dfs(i, visited, topoOrder);
            }
        }

        Collections.reverse(topoOrder);

        //Find max distance path by iterating topo order
        double[] distance = new double[n];
        Arrays.fill(distance, -1);
        distance[from] = 0;

        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        for(int i = 0; i < topoOrder.size(); i++) {

            int v = topoOrder.get(i);

            if(distance[v] >= 0) {

                if(v == to) {
                    break;
                }

                for(Map.Entry<Integer, Integer> entry: graph.get(v).entrySet()) {
                    int w = entry.getKey();
                    int wght = entry.getValue();

                    if(distance[w] < distance[v] + wght) {
                        distance[w] = distance[v] + wght;
                        parent[w] = v;
                    }
                }
            }
        }

        ArrayList<Integer> path = new ArrayList<>();
        dfsPath(path, parent, from, to);
        Collections.reverse(path);
        return path;
    }

    private static void dfsPath(ArrayList<Integer> path, int[] parent, int from, int to) {
        path.add(to);

        if(parent[to] != -1 && to != from) {
            dfsPath(path, parent, from, parent[to]);
        }
    }

    private static void dfs(int start, boolean[] visited, ArrayList<Integer> topoOrder) {
        visited[start] = true;

        for(Map.Entry<Integer, Integer> entry: graph.get(start).entrySet()) {
            if(!visited[entry.getKey()]) {
                dfs(entry.getKey(), visited, topoOrder);
            }
        }

        topoOrder.add(start);
    }

    public static void main(String[] args) {
        int from = 1;
        int to = 4;
        int dagNodes = 4;
        ArrayList<Integer> fromNodes = new ArrayList<>();
        ArrayList<Integer> toNodes = new ArrayList<>();
        ArrayList<Integer> weight = new ArrayList<>();

        fromNodes.add(1);
        fromNodes.add(1);
        fromNodes.add(1);
        fromNodes.add(3);

        toNodes.add(2);
        toNodes.add(3);
        toNodes.add(4);
        toNodes.add(4);

        weight.add(2);
        weight.add(2);
        weight.add(4);
        weight.add(3);

        System.out.println("The longest path in DAG is " + findLongestPath(dagNodes, fromNodes, toNodes, weight, from, to));
    }
}
