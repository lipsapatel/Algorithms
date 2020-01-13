import java.util.*;

/**
 * Topological Sort: A Topological Sort or topological ordering of directed graph is a linear
 * ordering of vertices such that for every directed edge uv from vertex u to vertex v, u comes
 * before v in the ordering.
 *
 * A topological ordering is possible if and only if graph has no directed cycles, that is if
 * it is a directed acyclic graph (DAG).
 *
 * resources/GraphTopologicalSort1.png
 *
 * Examples of topological sort uses are:
 * 1) Build system with dependencies
 * 2) Courses with pre requisites.
 *
 * Topological sort works for directed acyclic graph.
 *
 * Approach - BFS
 *
 * 1) Given graph, compute the indegree of the graph
 * 2) Initialize queue with indegree 0
 * 3) Remove from queue and print it
 * 4) For all neighbors, decrease the indegree and add to queue if it becomes zero
 *
 * Time Complexity
 * Compute Indegree = O(V + E)
 * BFS = O(V + E)
 *
 * So TC = O(V + E)
 * SC = O(V)
 *
 * resources/GraphTopologicalSortBFS.jpg
 */
public class GraphTopologicalSortBFS {

    private static HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

    //TC = O(V + E)
    //SC = O(V)
    private static HashMap<Integer, Integer> computeIndegree(HashMap<Integer, HashSet<Integer>> graph) {

        HashMap<Integer, Integer> indegree = new HashMap<>();

        //Initialize indegree map with 0. Because there may be nodes with zero indegree.
        for(Integer vertex: graph.keySet()) {
            indegree.put(vertex, 0);
        }

        for(HashSet<Integer> neighbors: graph.values()) {
            for(Integer vertex: neighbors) {
                if (indegree.containsKey(vertex)) {
                    indegree.put(vertex, indegree.get(vertex) + 1);
                } else {
                    indegree.put(vertex, 1);
                }
            }
        }
        return indegree;
    }

    private static Queue<Integer> initQueueWithZeroIndegree(HashMap<Integer, Integer> indegree) {
        Queue<Integer> queue = new LinkedList<>();

        for(Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
            if(entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }
        return queue;
    }

    //TC = O(V + E)
    private static void topologicalSort(HashMap<Integer, HashSet<Integer>> graph) {

        HashMap<Integer, Integer> indegree = computeIndegree(graph); //O(V + E)
        Queue<Integer> queue = initQueueWithZeroIndegree(indegree); //O(V)

        //BFS - O(V + E)
        while(!queue.isEmpty()) {
            Integer current = queue.remove();

            System.out.print(current + " ");
            for(Integer neighbor: graph.get(current)) {

                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        //Detect cycle - If any of the node has indegree non zero then there is cycle; return immediately
        for(Integer degree: indegree.values()) {
            if (degree != 0) {
                System.out.println("The graph has cycle");
                return;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        graph.put(0, new HashSet<>());

        HashSet<Integer> neighbors = new HashSet<>();
        neighbors.add(0);
        graph.put(1, neighbors);

        neighbors = new HashSet<>();
        neighbors.add(1);
        graph.put(2, neighbors);

        graph.put(3, neighbors);

        //neighbors = new HashSet<>();
        //neighbors.add(6);
        graph.put(4, new HashSet<>());
        //graph.put(4, neighbors);

        neighbors = new HashSet<>();
        neighbors.add(2);
        neighbors.add(4);
        graph.put(5, neighbors);

        neighbors = new HashSet<>();
        neighbors.add(3);
        neighbors.add(4);
        //neighbors.add(7);
        graph.put(6, neighbors);

        neighbors = new HashSet<>();
        neighbors.add(5);
        neighbors.add(6);
        graph.put(7, neighbors);

        topologicalSort(graph);
    }
}
