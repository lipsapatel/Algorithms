package IK.Graphs.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Create A Transpose Of A Directed Graph
 * Given a strongly connected directed graph, return its transpose.
 * The graph will be given as a reference to one of its nodes;
 * the rest of the graph can be discovered by walking its edges.
 *
 * Example
 * Input: a node of a graph like this:
 * 1 -> 2 -> 3 -> 1
 *
 * Output: a node of a graph like this:
 * 1 -> 3 -> 2 -> 1
 *
 *
 * Notes
 * Constraints:
 *
 * 1 <= number of nodes <= 315
 * Node values are unique integers, and 1 <= node value <= number of nodes
 * No multiple edges (connecting any pair of nodes in one direction) or self loops (edges connecting a node with itself) in the input graph
 * Description of the text format of the test cases
 *
 * You might need this for debugging your solution on IK UpLevel platform.
 *
 * Input and output file each contain a list or directed edges representing a directed graph.
 *
 * The input example
 *
 * Example
 *
 * is represented by
 *
 * {
 * "edges": [
 * [1, 2],
 * [2, 3],
 * [3, 1]
 * ]
 * }
 * and the output
 *
 * Output
 *
 * is represented by
 *
 * [
 * [2, 1],
 * [3, 2],
 * [1, 3]
 * ]
 *
 * Approach
 * 1) Graph Transpose means to reverse edges
 * 2) Do DFS and maintain hashmap of Integer and GraphNode for visited
 * 3) While returning from DFS, reverse edges and add neighbor to visited
 *
 * TC: O(n + m)
 * SC: O(n + m)
 */
public class GraphTranspose {

    public static class GraphNode {
        Integer value;
        ArrayList<GraphNode> neighbors;

        public GraphNode(Integer value) {
            this.value = value;
            this.neighbors = new ArrayList<>();
        }
    }

    private static GraphNode createTranspose(GraphNode node) {
        HashMap<Integer, GraphNode> map = new HashMap<>();

        return dfs(node, map);
    }

    private static GraphNode dfs(GraphNode node, HashMap<Integer, GraphNode> map) {
        //Mark as visited
        map.put(node.value, new GraphNode(node.value));

        //Iterate neighbors
        for(GraphNode neighbor: node.neighbors) {

            //If not visited
            if(!map.containsKey(neighbor.value)) {
                dfs(neighbor, map);
            }

            //While returning reverse edge
            map.get(neighbor.value).neighbors.add(map.get(node.value));
        }

        return map.get(node.value);
    }

    public static void main(String[] args) {
        GraphNode node1 = new GraphNode(1);
        GraphNode node2 = new GraphNode(2);
        GraphNode node3 = new GraphNode(3);

        node1.neighbors.add(node2);
        node2.neighbors.add(node3);
        node3.neighbors.add(node1);

        System.out.println("Transpose " +createTranspose(node1));
    }
}