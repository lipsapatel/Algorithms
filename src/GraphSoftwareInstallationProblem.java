import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Graph Software Installation Problem
 *
 * Given a list of software's which you need to install in a computer. Few software's has
 * dependency on other software's in the list, means these software can be installed only
 * when all of it's dependent software's are installed.
 *
 * Write an algorithm to print the sequence in which all the software's in the list
 * can be installed.
 *
 * Example:
 *
 * Softwares: A B C D E F
 * E depends on B, D
 * D depends on B, C
 * C depends on A
 * B depends on A
 * F no dependency
 *
 * Output: F A B C D E
 *
 * Approach: Graph Topological Sorting
 *
 * 1) Classic example of Graph Topological Sorting.
 * 2) Let's consider each software as Vertex and dependency between two software's as Edge
 * between two vertices.
 * 3) Iterate all the vertex and for each vertex get the adjacency list and if not visited do
 * recursive call
 * 4) Add to the stack
 *
 * This way the adjacency vertex is added to stack before the parent vertex.
 * Pop from the stack and print it in reverse order.
 *
 * The dependency has to be installed first.
 * So instead of stack, queue is better option.
 *
 * resources/GraphSoftwareInstallationProblem.png
 *
 * Time Complexity: O(V + E) = Number of vertices + Edges
 *
 */
public class GraphSoftwareInstallationProblem {

    private static int vertices;
    private static LinkedList<GraphNode>[] graphList;
    private static ArrayList<Character> softwares = new ArrayList<>();

    private static class GraphNode {

        char destination;
        char source;

        GraphNode(char source, char destination) {
            this.source = source;
            this.destination = destination;
        }
    }

    private static void initializeGraph(int nodes) {

        vertices = nodes;

        graphList = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++) {
            graphList[i] = new LinkedList<>();
        }
    }

    private static void addEdge(char source, char destination) {

        GraphNode graphNode = new GraphNode(source, destination);

        graphList[softwares.indexOf(source)].addFirst(graphNode);
    }

    private static void topologicalSorting() {

        boolean[] visited = new boolean[vertices];
        Queue<Character> queue = new LinkedList<>();

        //Visit each vertices if not already visited
        for (int i = 0; i < vertices; i++) {

            if (!visited[i]) {
                topologicalSortUtil(i, visited, queue);
            }
        }

        System.out.println("Software Installation Sequence: ");
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            System.out.print(queue.poll() + " ");
        }
    }

    private static void topologicalSortUtil(int vertex, boolean[] visited, Queue<Character> queue) {

        visited[vertex] = true;

        //Get all the adjacency vertices and if not already visited make recursive call
        for (int i = 0; i < graphList[vertex].size(); i++) {

            GraphNode graphNode = graphList[vertex].get(i);

            if (!visited[softwares.indexOf(graphNode.destination)]) {
                topologicalSortUtil(softwares.indexOf(graphNode.destination), visited, queue);
            }
        }
        queue.add(softwares.get(vertex));
    }

    public static void main(String[] args) {

        softwares.add('A');
        softwares.add('B');
        softwares.add('C');
        softwares.add('D');
        softwares.add('E');
        softwares.add('F');

        int vertices = softwares.size();

        initializeGraph(vertices);

        addEdge('B', 'A');
        addEdge('C', 'A');
        addEdge('D', 'C');
        addEdge('D', 'B');
        addEdge('E', 'B');
        addEdge('E', 'D');

        topologicalSorting();
    }
}
