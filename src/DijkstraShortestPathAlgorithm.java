/**
 * Dijkstra's Shortest Path Algorithm
 *
 * What is Dijkstra's algorithm?
 *
 * 1) Dijkstra algorithm is a greedy algorithm.
 * 2) It finds a shortest path tree for a weighted undirected graph
 * 3) This means to find a shortest paths between nodes in a graph, which
 * may represent for example road networks.
 * 4) For a given source node in the graph, the algorithm finds the
 * shortest path between source node and every other node.
 * 5) This algorithm also used for finding the shortest paths from a single
 * node to a single destination node by stopping the algorithm once the
 * shortest path to the destination node has been determined.
 * 6) Dijkstra's algorithm is very similar to Prim's algorithm.
 * In the Prim's algorithm we create minimum spanning tree (MST)
 * and in Dijkstra algorithm we create shortest path tree (SPT) from
 * the given source.
 *
 * Example
 *
 * resources/DijkstraShortestPathAlgorithm.png
 *
 * How to implement Dijkstra's algorithm?
 *
 * 1) Start with the empty Shortest Path Tree (SPT)
 * 2) Maintain a set SPT[] to keep track of vertices included in SPT.
 * 3) Assign a distance value to all the vertices.
 * say distance[] and initialize all the distances with +(infinity) except
 * the source vertex.
 * This will be used to keep track of distance of vertices from the source
 * vertex.
 * Distance of source vertex to source vertex will be 0.
 *
 * 4) Repeat the following steps until all vertices are processed.
 *    1) Pick vertex u which is not in SPT[] and has minimum distance.
 *    2) Add vertex u to SPT[]
 *    3) Loop over all the adjacent vertices of u
 *    4) For adjacent vertex v, if v is not in SPT and
 *    distance[v] > distance[u] + edge u-v weight then update
 *    distance[v] = distance[u] + edge u-v weight.
 *
 * Ways to implement
 *
 * 1) Adjacency Matrix - Searching
 * 2) Adjacency List - Binary Heap
 * 3) Adjacency List - Priority Queue
 * 4) Adjacency List - TreeMap
 *
 * Time Complexity
 *
 * The time complexity of Dijkstra algorithm depends on the data structures
 * used for graph and for ordering the edges by weight.
 *
 * Data Structure of Graph	  Ordering	        Time Complexity
 Adjacency Matrix	          Searching	        O(|V|2)
 Adjacency List	              Binary Heap	    O(|E|log|V|)
 Adjacency List	              Priority Queue	O(|E|log|V|)
 Adjacency List	              TreeMap	        O(|E|log|V|)

 Usages:

 1) Dijkstra's algorithm can be used to find the shortest route between
 one city and all other cities.
 2) Shortest path algorithm is used in network routing protocols
 3) IS-IS (Intermediate System to Intermediate System)
 4) Open Shortest Path First (OSPF)
 */
public class DijkstraShortestPathAlgorithm {
}
