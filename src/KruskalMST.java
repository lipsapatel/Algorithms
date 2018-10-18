/**
 * Kruskal's Algorithm - Minimum Spanning Tree (MST)
 *
 * What is Kruskal Algorithm?
 *
 * 1) Kruskal's algorithm for finding the Minimum Spanning Tree (MST), which finds an edge of the least possible
 * weight that connects any two trees in the forest.
 * 2) It is a greedy algorithm.
 * 3) It finds a subset of the edges that forms a tree that includes every vertex, hwere the total weight of all the edges in the tree
 * is minimized.
 * 4) If the graph is not connected, then if finds a minimum spanning forest ( a minimum spanning tree for each connected component)
 * 5) Number of edges in MST = V - 1 (V = no of vertices in Graph)
 *
 * resources/KruskalMinimumSpanningTree.png
 *
 * Approach:
 *
 * 1) Sort the edges in ascending order of weights.
 * 2) Pick the edge with the least weight. Check if including this edge in spanning tree will form a cycle.
 * If yes the ignore it. If no then add it to the spanning tree
 * 3) Repeat step 2 till spanning tree has v - 1 edges.
 * 4) Spanning tree with least weight will be formed, called Minimum Spanning Tree.
 */
public class KruskalMST {

    private static class Edge {

        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {

            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }


}
