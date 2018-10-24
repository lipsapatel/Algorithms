/**
 * Prim's - Minimum Spanning Tree (MST) using Adjacency Matrix
 *
 * resources/PrimsAlgorithmMST.png
 *
 * Implementation - Adjacency Matrix
 *
 * 1) Create boolean[] mst to keep track of vertices included in MST
 * 2) Create int[] key to keep track of key and weight for each vertex. Which vertex will be included next into MST will be
 * decided based on the key weight value.
 * 3) Initialize key for all vertices as Integer.MAX_VALUE except the first vertex for which it will be 0. (Start from first vertex)
 * 4) while (all the vertices not in MST)
 *      Get the vertex with minimum key. Say it's u.
 *      Include this vertex in MST and mark mst[u] = true
 *      Iterate through all the adjacent vertices of vertex u and update keys if adjacent vertex is not already part of mst[]
 * 5) We will use Result object to store the result of each vertex. Result object will store 2 information
 *    1) First the parent vertex. For example: If for vertex v, you have edge u-v in mst[] then vertex u will be parent vertex
 *    2) Second weight of edge u-v. If you add all these weight of all the vertices in mst[] then you will get Minimum Spanning Tree weight.
 *
 * Time Complexity: O(n2)
 *
 */
public class PrimMSTUsingAdjacencyMatrix {

    private static int vertices;
    private static int[][] matrix;

    private static void initializeGraph(int vertex) {
        vertices = vertex;
        matrix = new int[vertex][vertex];
    }

    private static void addEdge(int source, int destination, int weight) {

        //Add edge
        matrix[source][destination] = weight;

        //Add back edge for undirected graph
        matrix[destination][source] = weight;

    }

    private static int getMinimumVertex(boolean[] mst, int[] key) {

        int minimumKey = Integer.MAX_VALUE;
        int vertex = -1;

        for (int i = 0; i < vertices; i++) {

            if (mst[i] == false && key[i] < minimumKey) {

                minimumKey = key[i];
                vertex = i;
            }
        }

        return vertex;
    }

    private static class ResultSet {

        //Will store the vertex parent from which current vertex is reached
        int parent;

        //Will store weight for printing MST
        int weight;
    }

    private static void primMSTUsingAdjacencyMatrix() {

        boolean[] mst = new boolean[vertices];
        ResultSet[] resultSets = new ResultSet[vertices];
        int[] key = new int[vertices];

        //Initialize all the keys to infinity and
        //Initialize resultSet for all the vertices
        for (int i = 0; i < vertices; i++) {

            key[i] = Integer.MAX_VALUE;
            resultSets[i] = new ResultSet();
        }

        //Initialize vertex 0
        key[0] = 0;
        resultSets[0] = new ResultSet();
        resultSets[0].parent = -1;

        //Create MST
        for (int i = 0; i < vertices; i++) {

            //Get the vertex with minimum weight
            int vertex = getMinimumVertex(mst, key);

            //Include this vertex in mst
            mst[vertex] = true;

            //Iterate through all the adjacent vertices of above vertex and update keys
            for (int j = 0; j < vertices; j++) {

                //Check if there's a edge
                if (matrix[vertex][j] > 0) {

                    //Check if this vertex 'j' is already in mst and if no
                    //check if key needs an update or not
                    if (mst[j] == false && matrix[vertex][j] < key[j]) {

                        //Update the key
                        key[j] = matrix[vertex][j];

                        //Update the result set
                        resultSets[j].parent = vertex;
                        resultSets[j].weight = key[j];
                    }
                }
            }
        }
        //Print MST
        printMST(resultSets);
    }

    private static void printMST(ResultSet[] resultSets) {

        int totalMinWeight = 0;

        System.out.println("Minimum Spanning Tree: ");

        for (int i = 1; i < vertices; i++) {
            System.out.println("Edge: " + i + " - " + resultSets[i].parent + " Weight: " + resultSets[i].weight);

            totalMinWeight += resultSets[i].weight;
        }

        System.out.println("Total minimum weight: " + totalMinWeight);
    }

    public static void main(String[] args) {

        int vertices = 6;

        initializeGraph(vertices);
        addEdge(0, 1, 4);
        addEdge(0, 2, 3);
        addEdge(1, 2, 1);
        addEdge(1, 3, 2);
        addEdge(2, 3, 4);
        addEdge(3, 4, 2);
        addEdge(4, 5, 6);
        primMSTUsingAdjacencyMatrix();
    }
}
