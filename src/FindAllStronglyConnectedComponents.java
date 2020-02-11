import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * A Directed graph is strongly connected if there is a path between all pairs of vertices.
 * A Strongly connected component (SCC) of a directed graph is a maximal strongly connected subgraph.
 *
 * resources/FindAllStronglyConnectedComponents.jpg
 *
 * Kosaraju's Algorithm
 *
 * 1) Do DFS traversal on all the vertices and fill in stack in the order of their departure time
 * 2) Pop elements from stack and do the DFS traversal which will print SCC.
 *
 * This will do 2 DFS
 *
 * Time Complexity: O(V + E)
 *
 * However, if we do a DFS of graph and store vertices according to their finish times,
 * we make sure that the finish time of a vertex that connects to other SCCs (other that its own SCC),
 * will always be greater than finish time of vertices in the other SCC (See this for proof).
 * For example, in DFS of above example graph, finish time of 0 is always greater than 3 and 4; 0 connected to other SCC
 * (irrespective of the sequence of vertices considered for DFS). And finish time of 3 is always greater than 4.
 * DFS doesn’t guarantee about other vertices, for example finish times of 1 and 2 may be smaller or greater than 3
 * and 4 depending upon the sequence of vertices considered for DFS
 *
 * The above algorithm is asymptotically best algorithm, but there are other algorithms like Tarjan’s algorithm and
 * path-based which have same time complexity but find SCCs using single DFS
 */
public class FindAllStronglyConnectedComponents {

    private static HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

    private static void printSCC() {
        Stack<Integer> stack = new Stack<>();

        //Do DFS and fill the stack in the order of their departure time
        dfsToFillStack(stack);

        //Reverse/Transpose graph
        HashMap<Integer, HashSet<Integer>> reverseGraph = getReverseGraph(graph);

        //DFS while popping from stack
        HashSet<Integer> visited = new HashSet<>();
        while(!stack.isEmpty()) {
            int v = stack.pop();
            if(!visited.contains(v)) {
                dfs(v, reverseGraph, visited);
                System.out.println();
            }
        }
    }

    private static void dfs(Integer s, HashMap<Integer, HashSet<Integer>> graph, HashSet<Integer> visited) {
        visited.add(s);

        System.out.print(s + " ");
        HashSet<Integer> neighbor = graph.get(s);
        if(neighbor != null) {
            for(Integer av: neighbor) {
                if(!visited.contains(av)) {
                    dfs(av, graph, visited);
                }
            }
        }
    }

    private static void dfsToFillStack(Stack<Integer> stack) {
        HashSet<Integer> visited = new HashSet<>();

        for(Integer vertex: graph.keySet()) {
            if(!visited.contains(vertex)) {
                explore(vertex, visited, stack);
            }
        }
    }

    private static void explore(Integer s, HashSet<Integer> visited, Stack<Integer> stack) {
        visited.add(s);

        HashSet<Integer> neighbor = graph.get(s);
        if(neighbor != null) {
            for(Integer av: neighbor) {
                if(!visited.contains(av)) {
                    explore(av, visited, stack);
                }
            }
        }
        stack.push(s);
    }

    private static HashMap<Integer, HashSet<Integer>> getReverseGraph(HashMap<Integer, HashSet<Integer>> graph) {

        HashMap<Integer, HashSet<Integer>> reverseGraph = new HashMap<>();

        for(Integer vertex: graph.keySet()) {
            HashSet<Integer> neighbor = graph.get(vertex);
            if(neighbor != null) {
                for(Integer av: neighbor) {
                    if(reverseGraph.containsKey(av)) {
                        reverseGraph.get(av).add(vertex);
                    } else {
                        HashSet<Integer> rgNeighbor = new HashSet<>();
                        rgNeighbor.add(vertex);
                        reverseGraph.put(av, rgNeighbor);
                    }
                }
            }
        }
        return reverseGraph;
    }
    public static void main(String[] args) {
        HashSet<Integer> neighbor = new HashSet<>();
        neighbor.add(2);
        neighbor.add(3);
        graph.put(0, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(0);
        graph.put(1, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(1);
        graph.put(2, neighbor);

        neighbor = new HashSet<>();
        neighbor.add(4);
        graph.put(3, neighbor);

        neighbor = new HashSet<>();
        graph.put(4, neighbor);

        System.out.println("Strongly connected components are ");
        printSCC();
    }
}
