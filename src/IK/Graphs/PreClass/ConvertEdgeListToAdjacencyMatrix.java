package IK.Graphs.PreClass;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Convert Edge List To Adjacency Matrix
 * Convert the given edge list to the adjacency matrix of an undirected connected graph.
 * The adjacency matrix is a matrix with rows and columns labeled by graph vertices, with a 1 or 0
 * in position (u, v) according to whether vertices u and v are adjacent or not.
 *
 * Example
 * {
 * "n": 5,
 * "edges": [
 * [0, 1],
 * [1, 4],
 * [1, 2],
 * [1, 3],
 * [3, 4]
 * ]
 * }
 * Output:
 *
 * [
 * [0, 1, 0, 0, 0],
 * [1, 0, 1, 1, 1],
 * [0, 1, 0, 0, 0],
 * [0, 1, 0, 0, 1],
 * [0, 1, 0, 1, 0]
 * ]
 * Notes
 * There are n nodes in the graph, and each node has a distinct value from 0 to n - 1.
 * Edges are given as a list of pairs. Each pair [u, v] represents an undirected edge between node u and node v.
 * The list won't contain duplicate edges. That is, if [u, v] is present, then there will be no
 * other occurrence of [u, v] or [v, u].
 * If there is an edge between node u and node v, the value at position (u, v) of the adjacency
 * matrix is 1; otherwise, it is 0.
 *
 * Constraints:
 *
 * 1 <= n <= 103
 * 0 <= number of edges <= (n * (n - 1)) / 2
 * 0 <= value of each node <= n - 1
 * The graph won't contain self loops.
 *
 * Approach
 * 1) Create ArrayList of List of boolean of size n
 * 2) Iterate edges and set the boolean value true for edge in both direction
 * 3) Return the matrix
 *
 * TC: O(E + V) or O(m + n)
 * SC: O(n^2) or O(V^2)
 */
public class ConvertEdgeListToAdjacencyMatrix {

    private static ArrayList<ArrayList<Boolean>> convertEdgeListToAdjacencyMatrix(Integer n, ArrayList<ArrayList<Integer>> edges) {

        ArrayList<ArrayList<Boolean>> adjMatrix = new ArrayList<>(n);

        //Initialize Adjacency Matrix
        for(int i = 0; i < n; i++) {
            ArrayList<Boolean> list = new ArrayList<>(Collections.nCopies(n, false));
            adjMatrix.add(i, list);
        }

        //Iterate edges and add to adjacency matrix
        for(ArrayList<Integer> edge: edges) {
            adjMatrix.get(edge.get(0)).set(edge.get(1), true);
            adjMatrix.get(edge.get(1)).set(edge.get(0), true);
        }

        return adjMatrix;
    }

    public static void main(String[] args) {
        Integer n = 5;

        ArrayList<ArrayList<Integer>> edgeList = new ArrayList<>();

        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);

        edgeList.add(list);

        list = new ArrayList<>();
        list.add(1);
        list.add(4);

        edgeList.add(list);

        list = new ArrayList<>();
        list.add(1);
        list.add(2);

        edgeList.add(list);

        list = new ArrayList<>();
        list.add(1);
        list.add(3);

        edgeList.add(list);

        list = new ArrayList<>();
        list.add(3);
        list.add(4);

        edgeList.add(list);

        convertEdgeListToAdjacencyMatrix(n, edgeList);
    }

}
