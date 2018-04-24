import java.util.LinkedList;
import java.util.Queue;

/**
 * MIT Algorithms:
 *
 * Graph Search: Graph Search is about exploring graph.
 * Graph = (V, E)
 * V = Set of vertices
 * E = Set of edges
 *
 * Unordered pairs E = {v,w} Undirected graph
 * Ordered pairs E = (v, w) Directed graph
 *
 * Applications of Graph Search:
 *
 * 1) Web Crawling
 * 2) Social networking
 * 3) Network broadcast
 * 4) Garbage Collection
 * 5) Model Checking
 * 6) Checking mathematical conjecture
 * 7) Solving puzzle and games.
 *
 * Graph Representation:
 *
 * Adjacency Lists
 *
 * Array of linked lists
 * For every vertex, we store each neighbours
 *
 * Time Complexity: O(V+E)
 * Space Complexity: O(V+E)
 *
 * For undirected graph: O(V+2E) because there are 2 edges.
 *
 * Don't revisit the vertices.
 *
 *Algorithm:
 *
 * 1) Visit of all nodes reachable from given node s
 * 2) O(V + E) time
 * 3) Look at nodes reachable in 0 moves, 1 move, 2 move
 * 4) Careful to avoid duplicates.
 *
 * Approach:
 *
 * 1) Use queue
 * 2) Add starting index to the queue and mark visited as true
 * 3) Pull an element from the queue and print it.
 * 4) Add all the connected nodes to the queue if not visited and mark visited = true
 * 5) Repeat the above 2 steps until the queue is empty
 *
 * resources/GraphBreadthFirstSearch.png
 *
 * What is Breadth First Search?
 *
 * Breadth First Search is algorithm for traversing or searching tree or graph data structures.
 * It starts at tree root and explore the neighbour nodes first before moving to the next
 * level neighbours.
 *
 */
public class GraphBreadthFirstSearch {

    private static int vertices;
    private static LinkedList<Integer> graph[];

    private static void initializeGraph(int nodes) {

        vertices = nodes;
        graph = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++) {
            graph[i] = new LinkedList<>();
        }
    }

    private static void addEdge(int source, int destination) {
        graph[source].addFirst(destination);
    }

    private static void printGraph() {

        for (int i = 0; i < graph.length; i++) {

            if (graph[i].size() > 0) {

                System.out.print("Vertex " + i + " is connected to ");

                for (int j = 0; j < graph[i].size(); j++) {

                    System.out.print(graph[i].get(j) + " ");
                }
                System.out.println();
            }
        }
    }

    private static void BFSTraversal(int startIndex) {

        System.out.println("Breadth First Search: ");

        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(startIndex);
        visited[startIndex] = true;

        while(!queue.isEmpty()) {

            int pulledIndex = queue.poll();
            System.out.print(pulledIndex + " ");

            //Add all connected nodes to the queue
            LinkedList<Integer> connectedNodeList = graph[pulledIndex];

            for (int i = 0; i < connectedNodeList.size(); i++) {

                int connectedNode = connectedNodeList.get(i);

                while (!visited[connectedNode]) {
                    queue.add(connectedNode);
                    visited[connectedNode] = true;
                }
            }
        }

        System.out.println();
    }

    public static void main(String args[]) {

        initializeGraph(6);

        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(3, 4);
        addEdge(2, 3);
        addEdge(4, 0);
        addEdge(4, 1);
        addEdge(4, 5);

        BFSTraversal(0);

    }
}
