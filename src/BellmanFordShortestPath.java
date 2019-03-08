/**
 * We use Bellman-Ford Algorithm to find the shortest path from a single source node/vertex to all
 * destination nodes/vertices.
 *
 * The edges could be relax in any order.
 * To get the shortest path, we need V - 1 iterations.
 * There could be nodes which are unreachable.
 *
 * To find if the graph contains negative weight cycle, do one more iteration
 * that is Vth iteration. If that changes distance value for any node, that means there exists a negative
 * cycle (a cycle whose edges sum to negative value).
 *
 * Negative edge doesn't mean a negative cycle.
 *
 * All V - 1 iterations are not required. If an iteration does not change any distance value
 * then we can terminate the algorithm there and return valid result.
 * It also means there's no negative cycle.
 *
 * resources/BellmanFord.png
 *
 * Time Complexity: O(EV)
 */
public class BellmanFordShortestPath {

    public static class Edge {
        int source;
        int destination;
        int weight;

        public Edge() {
            source = 0;
            destination = 0;
            weight = 0;
        }
    }

    private static class Graph {
        public int V;
        public int E;
        public Edge[] edge;

        public Graph(int vertices, int edges) {
            this.V = vertices;
            this.E = edges;

            edge = new Edge[edges];
            for (int i = 0; i < E; i++) {
                edge[i] = new Edge();
            }
        }

        private void BellmanFord(int src) {
            //Distance used to store distance from source vertex
            int[] distance = new int[V];

            //Initialize all distance to infinity
            for (int i = 0; i < V; i++) {
                distance[i] = Integer.MAX_VALUE;
            }

            //Distance for source vertex is 0
            distance[src] = 0;

            //Relax all the edges V - 1 times
            //A simple shortest path from src to any other vertex can have atmost V - 1 edges
            for (int i = 1; i < V; i++) {
                //Stop the iteration if the distance is not updated
                boolean valueChanged = false;

                for (int j = 0; j < E; j++) {
                    int u = edge[j].source;
                    int v = edge[j].destination;
                    int w = edge[j].weight;

                    if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                        distance[v] = distance[u] + w;
                        valueChanged = true;
                    }
                }

                if (!valueChanged) {
                    break;
                }
            }

            //Check for negative-weight cycle.
            //If we get shorter path (distance updated) then there's negative-weight cycle
            //vth iteration will determine negative weight cycle
            for (int j = 0; j < E; j++) {
                int u = edge[j].source;
                int v = edge[j].destination;
                int w = edge[j].weight;

                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                    System.out.println("Graph contains negative weight cycle");
                    break;
                }
            }
            printBellmanFord(distance, src);
        }

        private void printBellmanFord(int[] distance, int src) {
            for (int i = 0; i < V; i++) {
                System.out.println("Source Vertex: " + src + " to vertex " + i + " distance: " +distance[i]);
            }
        }
    }

    public static void main(String[] args) {
        int V = 5;
        int E = 8;

        Graph graph = new Graph(V, E);

        // add edge 0-1 (or A-B in above figure)
        graph.edge[0].source = 0;
        graph.edge[0].destination = 1;
        graph.edge[0].weight = -1;

        // add edge 0-2 (or A-C in above figure)
        graph.edge[1].source = 0;
        graph.edge[1].destination = 2;
        graph.edge[1].weight = 4;

        // add edge 1-2 (or B-C in above figure)
        graph.edge[2].source = 1;
        graph.edge[2].destination = 2;
        graph.edge[2].weight = 3;

        // add edge 1-3 (or B-D in above figure)
        graph.edge[3].source = 1;
        graph.edge[3].destination = 3;
        graph.edge[3].weight = 2;

        // add edge 1-4 (or A-E in above figure)
        graph.edge[4].source = 1;
        graph.edge[4].destination = 4;
        graph.edge[4].weight = 2;

        // add edge 3-2 (or D-C in above figure)
        graph.edge[5].source = 3;
        graph.edge[5].destination = 2;
        graph.edge[5].weight = 5;

        // add edge 3-1 (or D-B in above figure)
        graph.edge[6].source = 3;
        graph.edge[6].destination = 1;
        graph.edge[6].weight = 1;

        // add edge 4-3 (or E-D in above figure)
        graph.edge[7].source = 4;
        graph.edge[7].destination = 3;
        graph.edge[7].weight = -3;

        graph.BellmanFord(0);
    }
}
