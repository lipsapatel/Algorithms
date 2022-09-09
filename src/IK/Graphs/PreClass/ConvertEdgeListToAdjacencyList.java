package IK.Graphs.PreClass;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Problem
 *
 * Convert Edge List To Adjacency List
 * Convert the given edge list to the adjacency list of an undirected connected graph.
 * An adjacency list represents a graph as a list of lists. The list index represents a vertex, and each element in its
 * inner list represents the other vertices that form an edge with the vertex.
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
 * [1],
 * [0, 2, 3, 4],
 * [1],
 * [1, 4],
 * [1, 3]
 * ]
 * Notes
 * There are n nodes in the graph, and each node has a distinct value from 0 to n - 1.
 * Edges are given as a list of pairs. Each pair [u, v] represents an undirected edge between node u and node v.
 * The list won't contain duplicate edges. That is, if [u, v] is present, then there will be no other occurrence of [u, v] or [v, u].
 * Every inner list of the output list should hold the nodes in ascending order.
 * Constraints:
 *
 * 1 <= n <= 103
 * 0 <= number of edges <= (n * (n - 1)) / 2
 * 0 <= value of each node <= n - 1
 * The graph won't contain self loops.
 *
 * Approach:
 *
 * 1) Create ArrayList of list
 * 2) Add elements 0 to n - 1 with empty list
 * 3) Iterate edges and add the edge to list
 * 4) Iterate Arraylist of list and sort each list
 *
 * Time Complexity: O(E + n * (Degree * log(degree))
 * Space Complexity: O(E + V) = O(m + n)
 */
public class ConvertEdgeListToAdjacencyList {

    private static ArrayList<ArrayList<Integer>> convertEdgeListToAdjacencyList(Integer n, ArrayList<ArrayList<Integer>> edges) {

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        //add empty list for all vertices.
        for (int i = 0; i < n; i++) {
            adjList.add(i, new ArrayList<>());
        }

        //Iterate edges and add to adj list
        for (ArrayList<Integer> edge : edges) {
            adjList.get(edge.get(0)).add(edge.get(1));//Undirected graph to add edge two ways
            adjList.get(edge.get(1)).add(edge.get(0));
        }

        //Sort list of list
        for (ArrayList<Integer> list : adjList) {
            Collections.sort(list);
        }
        return adjList;
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

         System.out.println("Adjacency List: " + convertEdgeListToAdjacencyList(n, edgeList).toString());
    }
}
