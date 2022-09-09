package IK.Graphs.PreClass;

import java.util.ArrayList;

/**
 * Implement graph using Adjacency List
 * addEdge
 * hasEulerianPath
 * hasEulerianCycle
 *
 * Approach
 * 1) Create array of arraylist
 * 2) In constructor, initialize the number of vertices and array with empty arraylist
 * 3) addEdge(start, end, bidir) - add end edge at array index start
 * If bidir is true do reverse to add edge
 * 4) hasEulerianCycle - Return true if we have none odd degree vertex
 * 5) hasEulerianPath - Returns true if we have exactly 0 or 2 odd degree vertex
 *
 * TC: Graph construction O(V + E) or O(n + m)
 * hasEulerianCycle or hasEulerianPath - O(V) or O(n)
 *
 * SC: O(V + E) = O(n + m) to store the graph
 */
public class GraphImplementationAdjacencyList {

    private static int vertices;
    private static ArrayList<Integer>[] graph;

    public GraphImplementationAdjacencyList(int n) {

        vertices = n;
        graph = new ArrayList[n];

        //Initialize array with empty arraylist
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    private static void addEdge(int start, int end, boolean bidir) {
        graph[start].add(end);

        if(bidir) { //If it is undirected graph
            graph[end].add(start);
        }
    }

    //All vertices should have even degree
    private static boolean hasEulerianCycle() {

        for(ArrayList<Integer> edge: graph) {
            if(edge.size() % 2 != 0) {
                return false;
            }
        }

        return true;
    }

    private static boolean hasEulerianPath() {
        int odd = 0;

        for(ArrayList<Integer> edge: graph) {
            if(edge.size() % 2 != 0) {
                odd++;
            }
        }

        //If all vertices has even degree or there are only 2 vertices with odd degree.
        return odd == 0 || odd == 2;
    }

    public static void main(String[] args) {
        GraphImplementationAdjacencyList graphImplementationAdjacencyList = new GraphImplementationAdjacencyList(5);
        addEdge(0,1, true);
        addEdge(0, 4, true);
        addEdge(1, 2, true);
        addEdge(1, 3, true);
        addEdge(1, 4, true);
        addEdge(2, 3, true);
        addEdge(3, 4, true);

        System.out.println("Has Eulerian Cycle " + hasEulerianCycle());
        System.out.println("Has Eulerian Path " + hasEulerianPath());
    }

}
