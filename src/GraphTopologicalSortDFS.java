import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Topological Sort: A Topological Sort or topological ordering of directed graph is a linear
 * ordering of vertices such that for every directed edge uv from vertex u to vertex v, u comes
 * before v in the ordering.
 *
 * A topological ordering is possible if and only if graph has no directed cycles, that is if
 * it is a directed acyclic graph (DAG).
 *
 * resources/GraphTopologicalSort.png
 * resources/GraphTopologicalSort1.png
 * resources/GraphTopologicalSortDFS.png
 *
 * There can be one of more topological order in any graph.
 *
 * We cannot use DFS as it is but we can modify DFS to get topological sort.
 *
 * Approach:
 *
 * Modified DFS
 *
 * 1) Use stack to store the vertices.
 * 2) Maintain boolean visited[] to keep track of already visited vertices.
 * 3) Here we make the recursive call to the adjacent vertex and then only push myself to stack after I have visited path below me.
 * 4) This will ensure that the vertices will be pushed to stack only when all of its
 * adjacent vertices are pushed to stack
 * 5) Finally print the stack
 *
 * To detect cycles, create set pathSet, Add all the node to path when visiting and remove it when done visiting all neighbors of that node.
 * If node is visited again then there is cycle.
 *
 * Time Complexity: O(V + E)
 * Space Complexity: O(V)
 *
 */
public class GraphTopologicalSortDFS {

    private static HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

    private static void topologicalSort(HashMap<Integer, HashSet<Integer>> graph) {

        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();
        HashSet<Integer> pathSet = new HashSet<>(); //If the node gets repeated in pathset then there is cycle

        for(Integer node: graph.keySet()) {
            topologicalSortUtil(graph, node, stack, visited, pathSet);
        }

        //Print topological sort order
        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    private static void topologicalSortUtil(HashMap<Integer, HashSet<Integer>> graph, Integer node,
                                            Stack<Integer> stack, HashSet<Integer> visited, HashSet<Integer> pathSet) {

        if (pathSet.contains(node)) {
            System.out.println("Graph has cycle");
            return; //throw exception
        }

        if (visited.contains(node)) {
            return;
        }

        visited.add(node);
        pathSet.add(node);

        for(Integer neighbor: graph.get(node)) {
            topologicalSortUtil(graph, neighbor, stack, visited, pathSet);
        }

        //Done with all the neighbors
        stack.push(node);
        pathSet.remove(node);
    }


    public static void main(String[] args) {
        graph.put(0, new HashSet<>());

        HashSet<Integer> neighbors = new HashSet<>();
        neighbors.add(0);
        graph.put(1, neighbors);

        neighbors = new HashSet<>();
        neighbors.add(1);
        graph.put(2, neighbors);

        graph.put(3, neighbors);

        //neighbors = new HashSet<>();
        //neighbors.add(6);
        graph.put(4, new HashSet<>());
        //graph.put(4, neighbors);

        neighbors = new HashSet<>();
        neighbors.add(2);
        neighbors.add(4);
        graph.put(5, neighbors);

        neighbors = new HashSet<>();
        neighbors.add(3);
        neighbors.add(4);
        //neighbors.add(7);
        graph.put(6, neighbors);

        neighbors = new HashSet<>();
        neighbors.add(5);
        neighbors.add(6);
        graph.put(7, neighbors);

        topologicalSort(graph);
    }

}
