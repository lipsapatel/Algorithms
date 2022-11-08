package IK.Graphs.Class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 *
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 *
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 *
 *
 *
 * Example 1:
 *
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * Example 2:
 *
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 * Example 3:
 *
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 *
 *
 * Constraints:
 *
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 *
 * Approach
 *
 * 1) a/b = 3 means a -> b = 3 and b -> a = 1/3
 * 2) Construct weighted graph from equations.
 * 3) Do DFS for all the queries
 * 4) For example: For query a/b, start = a and target = b
 * 5) Keep the product of all the edges. If we find the target, then return the product. If our product is not -1 then return we found the target.
 * 6) There are two base cases: If for a/b, if graph does not contain a or b then return -1
 * 7) If the query is a/b then return 1
 *
 * TC: O(n * m) where we are iterating all the m queries and doing dfs TC for DFS = O(n) we have n equations so the graph have around 2n nodes and n edges
 * SC: O(n + m) where n = number of equations = graph
 * m = number of result = queries
 *
 */
public class EvaluateDivision {

    private static HashMap<String, HashMap<String, Double>> graph;

    private static void initializeGraph() {
        graph = new HashMap<>();
    }

    private static void addEdge(String start, String end, double weight) {
        if(graph.containsKey(start)) {
            graph.get(start).put(end, weight);
        } else {
            graph.put(start, new HashMap<>());
            graph.get(start).put(end, weight);
        }

        double reverseWeight = 1/weight;

        if(graph.containsKey(end)) {
            graph.get(end).put(start, reverseWeight);
        } else {
            graph.put(end, new HashMap<>());
            graph.get(end).put(start, reverseWeight);
        }
    }

    private static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        initializeGraph();

        //Construct Graph
        int i = 0;
        for(List<String> edge: equations) {
            addEdge(edge.get(0), edge.get(1), values[i]);
            i++;
        }

        //Do DFS
        double[] list = new double[queries.size()];
        int j = 0;
        for(List<String> query: queries) {

            //Two Edge Case
            if(!graph.containsKey(query.get(0)) || !graph.containsKey(query.get(1))) {
                list[j] = -1;
            } else if(query.get(0).equals(query.get(1))) {
                list[j] = 1;
            } else {
                HashSet<String> visited = new HashSet<>();
                list[j] = dfs(visited, query.get(0), query.get(1), 1);
            }
            j++;
        }
        return list;
    }

    private static double dfs(HashSet<String> visited, String start, String target, double product) {
        visited.add(start);
        double res = -1;

        for(Map.Entry<String, Double> v: graph.get(start).entrySet()) {

            if(v.getKey().equals(target)) {
                res = product * v.getValue();
            } else {
                if(!visited.contains(v.getKey())) {
                    res = dfs(visited, v.getKey(), target, product * v.getValue());
                }
            }
            if(res != -1) {
                return res; //Found the target so no need to continue DFS
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();

        ArrayList<String> edge = new ArrayList<>();
        edge.add("a");
        edge.add("b");

        equations.add(edge);

        edge = new ArrayList<>();
        edge.add("b");
        edge.add("c");

        equations.add(edge);

        double[] values = {2.0, 3.0};

        List<List<String>> queries = new ArrayList<>();

        List<String> query = new ArrayList<>();
        query.add("a");
        query.add("c");

        queries.add(query);

        query = new ArrayList<>();
        query.add("b");
        query.add("a");

        queries.add(query);

        System.out.println("Output: " + Arrays.toString(calcEquation(equations, values, queries)));
    }
}
