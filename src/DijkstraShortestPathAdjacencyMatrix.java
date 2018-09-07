/**
 * Dijkstra's Shortest Path Algorithm (SPT) - Adjacency Matrix
 *
 * Implementing using adjacency matrix
 *
 * resources/DijkstraShortestPathAlgorithm.png
 *
 * Ordering - Searching - Get the vertex with minimum distance among all vertices.
 *
 * Approach
 *
 * 1) Start with an empty Shortest Path Tree (SPT)
 * 2) Maintain set SPT[] to keep track of vertices included in SPT
 * 3) Assign distance value to all the vertices (distance[]) and initialize all the distances with infinity
 * except the source vertex. This will be used to keep track of distances of vertices from source vertex.
 * Distance of source vertex to source vertex is 0.
 * 4) Repeat the following steps until all the vertices are processed.
 *      1) Pick vertex u which is not in SPT[ and has minimum distance. Here we will loop through the vertices and find the vertex
 *      with minimum distance.
 *      2) Add vertex u to SPT[]
 *      3) Loop over all the adjacent vertices of vertex u
 *      4) For adjacent vertex v, if v is not in SPT[] and distance[u] + edge u-v weight < distance[v] then update distance[v] =
 *      distance[u] + edge u-v weight
 *
 * Time Complexity: O(v2)
 *
 * Total vertices V
 * Total edges E
 *
 * O(V) - Iterate through all the vertices to add them in SPT
 * O(V) - Each time select a vertex with minimum distance
 * so overall complexity: O(v2)
 */
public class DijkstraShortestPathAdjacencyMatrix {

    private static int vertices;
    private static int[][] matrix;

    private static void initializeGraph(int vertex) {

        vertices = vertex;
        matrix = new int[vertices][vertices];
    }

    private static void addEdge(int source, int destination, int weight) {

        //Add edge
        matrix[source][destination] = weight;

        //Add back edge for undirected graph
        matrix[destination][source] = weight;
    }

    private static void dijkstraGetMinimumDistance(int sourceVertex) {

        boolean[] spt = new boolean[vertices];
        int[] distance = new int[vertices];
        int INFINITY = Integer.MAX_VALUE;

        //Initialize all the distance to infinity
        for (int i = 0; i < vertices; i++) {
            distance[i] = INFINITY;
        }

        //Distance of source vertex is 0
        distance[sourceVertex] = 0;

        //Create SPT
        for (int i = 0; i < vertices; i++) {

            //Get the vertex with minimum distance
            int vertexWithMinimumDistance = getMinimumVertex(spt, distance);

            //Include this vertex in SPT
            spt[vertexWithMinimumDistance] = true;

            //Iterate through all the adjacent vertex of this vertex
            for (int adjacentVertex = 0; adjacentVertex < vertices; adjacentVertex++) {

                //Check if there's an edge between vertexWithMinimumDistance and adjacentVertex
                if (matrix[vertexWithMinimumDistance][adjacentVertex] > 0) {

                    //Check if adjacentVertex is already in spt
                    if (spt[adjacentVertex] == false) {

                        int newDistance = matrix[vertexWithMinimumDistance][adjacentVertex] + distance[vertexWithMinimumDistance];
                        if (newDistance < distance[adjacentVertex]) {
                            distance[adjacentVertex] = newDistance;
                        }
                    }
                }
            }
        }

        //Print the Shortest Path Tree
        printDijkstra(sourceVertex, distance);
    }

    private static void printDijkstra(int sourceVertex, int[] distance) {

        System.out.println("Dijkstra Algorithm: (Adjacency Matrix)");

        for (int i = 0; i < vertices; i++) {
            System.out.println("Source Vertex: " + sourceVertex + " to vertex " + i + " distance: " + distance[i]);
        }
    }

    private static int getMinimumVertex(boolean[] spt, int[] distance) {

        int minDistance = Integer.MAX_VALUE;
        int vertex = -1;

        for (int i = 0; i < vertices; i++) {

            if (spt[i] == false && minDistance > distance[i]) {
                minDistance = distance[i];
                vertex = i;
            }
        }
        return vertex;
    }

    public static void main(String[] args) {
        int vertices = 6;

        initializeGraph(vertices);
        int sourceVertex = 0;

        addEdge(0, 1, 4);
        addEdge(0, 2, 3);
        addEdge(1, 2, 1);
        addEdge(1, 3, 2);
        addEdge(2, 3, 4);
        addEdge(3, 4, 2);
        addEdge(4, 5, 6);

        dijkstraGetMinimumDistance(sourceVertex);
    }
}
