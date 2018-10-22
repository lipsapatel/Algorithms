/**
 * Prim's Algorithm - Minimum Spanning Tree (MST)
 *
 * What is Prim's algorithm?
 *
 * 1) Prim's algorithm is a greedy algorithm.
 * 2) It finds a minimum spanning tree for a weighted undirected graph.
 * 3) This means it finds a subset of the edges that forms a tree that include
 * every vertex where the total weight of all the edges in the tree is minimized.
 * 4) The algorithm operates by building this tree one vertex at a time, from an arbitrary
 * starting vertex, at each step adding the cheapest possible connection from the tree to another vertex.
 *
 * Example:
 *
 * resources/PrimsAlgorithmMST.png
 *
 * How to implement Prim's algorithm?
 *
 * mst[]
 * 0
 * 2
 * 1
 * 3
 * 4
 * 5
 *
 * vertices weight
 * 0          0
 * 1          infi 4 1
 * 2          infi 3
 * 3          infi 4 2
 * 4          infi 2
 * 5          infi 6
 *
 * 1) Start with the empty spanning tree
 * 2) Maintain a set mst[] to keep track of all the vertices included in minimum spanning tree.
 * 3) Assign a key value to all the vertices and initialize all the keys with infinity except the first
 * vertex
 * 4) Key value in step 3 will be used in making decision that which next vertex and edge will be
 * included in the mst[]. We will pick the vertex which is not included in mst[] and has minimum key.
 * So at the beginning the first vertex will be picked first.
 * 5) Repeat the following steps until all vertices are processed.
 *   Pick the vertex u which is not in mst[] and has minimum key
 *   Add vertex u to mst[]
 *   Loop over all the adjacent vertices of u
 *   For adjacent vertex v, if v is not in the mst[] and edge u-v weight is less than the key[u] weight
 *   then update key[u] = edge u-v weight
 *   4) Return mst[]
 *
 *   Ways to implement
 *
 *   1) Adjacency matrix
 *   2) Adjacency List - Binary heap
 *   3) Adjacency List - Priority queue with decrease key
 *   4) Adjacency List - Priority queue without decrease key
 *
 *   Time Complexity
 *
 *   Data Structure of Graph	Ordering	Time Complexity
     Adjacency Matrix	        Searching	O(|V|2)
     Adjacency List	            Binary Heap	O(|E|log|V|)
     Adjacency List	            Priority Queue with decrease key	O(|E|2log|V|)
     Adjacency List	            Priority Queue without decrease key – Better Implementation	O(|E|log|V|)
 */
public class PrimsAlgorithmMST {
}
