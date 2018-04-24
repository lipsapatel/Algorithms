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
 * 3) Here we make the recursive call to the adjacent vertex and then only push to stack.
 * 4) This will ensure that the vertices will be pushed to stack only when all of its
 * adjacent vertices are pushed to stack
 * 5) Finally print the stack
 *
 * Time Complexity: O(V + E)
 *
 */
public class GraphTopologicalSort {

    private static int vertices;
    private static LinkedList<Integer>[] graphList;

    private static void initializeGraph(int n) {
        vertices = n;
        graphList = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++) {
            graphList[i] = new LinkedList<>();
        }
    }

    private static void addEdge(int source, int destination) {

        graphList[source].addFirst(destination);
    }

    private static void topologicalSort() {

        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < vertices; i++) {

            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        System.out.println("Topological Sort: ");
        int size = stack.size();

        for (int i = 0; i < size; i++) {
            System.out.print(stack.pop() + " ");
        }
    }

    private static void topologicalSortUtil(int start, boolean[] visited, Stack<Integer> stack) {

        visited[start] = true;

        for (int i = 0; i < graphList[start].size(); i++) {

            int vertex = graphList[start].get(i);

            if (!visited[vertex]) {
                topologicalSortUtil(vertex, visited, stack);
            }
        }

        stack.push(start);
    }

    public static void main(String[] args) {

        initializeGraph(8);

        addEdge(7, 6);
        addEdge(7, 5);
        addEdge(6, 4);
        addEdge(6, 3);
        addEdge(5, 4);
        addEdge(5, 2);
        addEdge(3, 1);
        addEdge(2, 1);
        addEdge(1, 0);

        topologicalSort();
    }

}
