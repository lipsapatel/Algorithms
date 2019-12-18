import java.util.*;

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
 * Map of integer and Integer HashSet
 * For every vertex, we store each neighbours
 *
 * Time Complexity: O(V+E)
 * Space Complexity: O(V)
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
 * 2) Add starting index to the queue and visited.
 * 3) Update the distance map
 * 3) Remove an element from the queue (print it)
 * 4) Check if removed element == target, then return
 * 4) Add all the connected nodes to the queue if not visited and add to visited and distance map
 * 5) Repeat the above 2 steps until the queue is empty
 * 6) If queue is empty, return -1
 *
 * resources/GraphBreadthFirstSearch.png
 * resources/GraphBreadthFirstSearch.jpg
 *
 * What is Breadth First Search?
 *
 * Breadth First Search is algorithm for traversing or searching tree or graph data structures.
 * It starts at tree root and explore the neighbour nodes first before moving to the next
 * level neighbours.
 *
 */
public class GraphBreadthFirstSearch {

    private static HashMap<Integer, HashSet<Integer>> graphMap = new HashMap<>();

    private static void addEdge(int source, int destination) {

        if (graphMap.containsKey(source)) {
            HashSet<Integer> set = graphMap.get(source);
            set.add(destination);
            graphMap.put(source, set);
        } else {
            HashSet<Integer> set = new HashSet<>();
            set.add(destination);
            graphMap.put(source, set);
        }
    }

    private static void printGraph() {

        for (Integer key: graphMap.keySet()) {
            System.out.print("Vertex " + key + " is connected to ");
            for (Integer destination: graphMap.get(key)) {
                System.out.print( destination + " ");
            }
            System.out.println();
        }
    }

    //Returns the shortest distance between start and target
    private static List<Integer> bfs(int start, int target) {

        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, Integer> distance = new HashMap<>();
        HashMap<Integer, Integer> prev = new HashMap<>(); //This stores the previous node for current node

        //Add the start node
        queue.add(start);
        visited.add(start);
        distance.put(start, 0);

        while(!queue.isEmpty()) {

            Integer poppedNode = queue.remove();

            if (poppedNode == target) {
                return buildPath(prev, target);
                //return distance.get(poppedNode); //this returns the shortest distance
            } else { //Add the neighbours - O(E)

                HashSet<Integer> adjacentVertex = graphMap.get(poppedNode);

                if (adjacentVertex != null) {
                    for (Integer vertex: adjacentVertex) {
                        if (!visited.contains(vertex)) {
                            queue.add(vertex);
                            visited.add(vertex);
                            distance.put(vertex, distance.get(poppedNode) + 1);
                            prev.put(vertex, poppedNode); //The prev node for vertex is poppedNode; this is how we get there
                        }
                    }
                }
            }
        }
        //return -1; //if returning the distance
        return new ArrayList<>();
    }

    private static List<Integer> buildPath(HashMap<Integer, Integer> prev, int target) {

        ArrayList<Integer> result = new ArrayList<>();

        result.add(0, target);

        int current = target;
        while(prev.get(current) != null) {
            current = prev.get(current);
            result.add(0, current);
        }
        return result;
    }

    /*****************************************************************************************************
       BFS to print all components of an unconnected undirected graph
     */

    public static class Vertex {
        int label;
        List<Vertex> neighbors;

        public Vertex(int label) {
            this.label = label;
        }
    }

    private static void bfs(List<Vertex> graphList) {

        HashSet<Integer> visited = new HashSet<>();

        for (Vertex current: graphList) {

            if (!visited.contains(current.label)) {

                List<Integer> bfsList = new ArrayList<>();
                exploreBFS(current, visited, bfsList);

                for(int vertex: bfsList) {
                    System.out.print(vertex + " ");
                }
                System.out.println();
            }
        }
    }

    private static void exploreBFS(Vertex current, HashSet<Integer> visited, List<Integer> bfsList) {
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(current);
        visited.add(current.label);
        bfsList.add(current.label);

        while(!queue.isEmpty()) {

            Vertex poppedVertex = queue.remove();

            for (Vertex vertex: poppedVertex.neighbors) {
                if (!visited.contains(vertex.label)) {
                    queue.add(vertex);
                    visited.add(vertex.label);
                    bfsList.add(vertex.label);
                }
            }
        }
    }

    public static void main(String args[]) {

        //This is undirected graph
        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(2, 1);
        addEdge(2, 4);
        addEdge(3, 1);
        addEdge(3, 4);
        addEdge(3, 5);
        addEdge(3, 8);
        addEdge(4, 2);
        addEdge(4, 3);
        addEdge(4, 7);
        addEdge(4, 6);
        addEdge(5, 3);
        addEdge(5, 7);
        addEdge(5, 8);
        addEdge(6, 4);
        addEdge(6, 9);
        addEdge(7, 4);
        addEdge(7, 5);
        addEdge(7, 10);
        addEdge(7, 9);
        addEdge(8, 3);
        addEdge(8, 5);
        addEdge(8, 9);
        addEdge(8, 11);
        addEdge(9, 6);
        addEdge(9, 7);
        addEdge(9, 8);
        addEdge(10, 7);
        addEdge(11, 8);

        printGraph();

        System.out.println("The shortest distance between node 1 and 8 is " );
        List<Integer> list = bfs(1, 8);
        for (Integer path: list) {
            System.out.print(path + "-->");
        }

        /******/
        Vertex vertex1 = new Vertex(1);
        Vertex vertex2 = new Vertex(2);
        Vertex vertex3 = new Vertex(3);
        Vertex vertex4 = new Vertex(4);
        Vertex vertex5 = new Vertex(5);

        Vertex vertex13 = new Vertex(13);
        Vertex vertex14 = new Vertex(14);
        Vertex vertex15 = new Vertex(15);
        Vertex vertex16 = new Vertex(16);

        List<Vertex> graphList = new ArrayList<>();
        graphList.add(vertex1);
        graphList.add(vertex2);
        graphList.add(vertex3);
        graphList.add(vertex4);
        graphList.add(vertex5);
        graphList.add(vertex13);
        graphList.add(vertex14);
        graphList.add(vertex15);
        graphList.add(vertex16);

        ArrayList<Vertex> neighbors = new ArrayList<>();
        neighbors.add(vertex2);
        neighbors.add(vertex3);

        vertex1.neighbors = neighbors;

        neighbors = new ArrayList<>();
        neighbors.add(vertex1);
        vertex2.neighbors = neighbors;

        neighbors = new ArrayList<>();
        neighbors.add(vertex1);
        neighbors.add(vertex4);
        neighbors.add(vertex5);
        vertex3.neighbors = neighbors;

        neighbors = new ArrayList<>();
        neighbors.add(vertex3);
        neighbors.add(vertex5);
        vertex4.neighbors = neighbors;

        neighbors = new ArrayList<>();
        neighbors.add(vertex4);
        neighbors.add(vertex3);
        vertex5.neighbors = neighbors;

        neighbors = new ArrayList<>();
        neighbors.add(vertex14);
        neighbors.add(vertex15);
        vertex13.neighbors = neighbors;

        neighbors = new ArrayList<>();
        neighbors.add(vertex13);
        neighbors.add(vertex16);
        vertex14.neighbors = neighbors;

        neighbors = new ArrayList<>();
        neighbors.add(vertex13);
        vertex15.neighbors = neighbors;

        neighbors = new ArrayList<>();
        neighbors.add(vertex14);
        vertex16.neighbors = neighbors;

        System.out.println();
        bfs(graphList);
    }
}
