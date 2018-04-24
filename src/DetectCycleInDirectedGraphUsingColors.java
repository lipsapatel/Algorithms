import java.util.HashSet;
import java.util.LinkedList;

/**
 * Detect cycle in directed graph using colors.
 *
 * Given a directed graph, write an algorithm to find our whether graph contains cycle or not.
 * resources/CycleInDirectedGraphUsingColors.png
 *
 * Approach:
 *
 * Graph contains cycle if there is any back edges.
 * There are two types of back edges:
 *
 * 1) Edge from vertex to itself - Self loop
 * 2) Edge from any decendent back to vertex.
 *
 * Using Colors:
 *
 * We will assign every vertex a color. We will use three colors - white, gray and black
 *
 * White Color: Vertices which are not processed will be assigned white color. So at the beginning
 * all the vertices will be white.
 *
 * Gray Color: Vertices which are currently being processed. If DFS is started from particular
 * vertex, it will be gray in color till not completed.
 *
 * Black Color: Vertices for which DFS is completed is black in color.
 *
 * Cycle Detection: During DFS, if we detect vertex which is gray in color then there is a cycle
 * and edge from current vertex to gray vertex is called back edge.
 *
 */
public class DetectCycleInDirectedGraphUsingColors {

    private static int vertices;
    private static LinkedList<Integer>[] graphList;

    private static void initializeGraph(int nodes) {

        vertices = nodes;
        graphList = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++) {
            graphList[i] = new LinkedList<>();
        }
    }

    private static void addEdge(int source, int destination) {
        graphList[source].addFirst(destination);
    }

    private static boolean hasCycle() {

        //Create Color sets
        HashSet<Integer> whiteSet = new HashSet<>();
        HashSet<Integer> graySet = new HashSet<>();
        HashSet<Integer> blackSet = new HashSet<>();

        //Initially put all the vertices in white set
        for (int i = 0; i < vertices; i++) {
            whiteSet.add(i);
        }

        //Traverse only white vertices
        for (int i = 0; i < vertices; i++) {
            if (whiteSet.contains(i) && hasCycleUtil(i, whiteSet, graySet, blackSet)) {
                return true;
            }
        }

        return false;
    }

    private static boolean hasCycleUtil(int vertex, HashSet<Integer> whiteSet, HashSet<Integer> graySet,
                                        HashSet<Integer> blackSet) {

        //Visiting this vertex make it gray from white
        whiteSet.remove(vertex);
        graySet.add(vertex);

        //visit the adjacent vertex
        for (int i = 0; i < graphList[vertex].size(); i++) {

            int adjacentVertex = graphList[vertex].get(i);

            //If the vertex is present in gray Set then it has cycle
            if (graySet.contains(adjacentVertex)) {
                return true;
            }

            //If present in black set means it's already done
            if (blackSet.contains(adjacentVertex)) {
                continue;
            }

            if (hasCycleUtil(adjacentVertex, whiteSet, graySet, blackSet)) {
                return true;
            }
        }

        //If reached here means cycle is not found and make this vertex black from gray
        graySet.remove(vertex);
        blackSet.add(vertex);
        return false;
    }

    public static void main(String[] args) {

        int vertices = 5;
        initializeGraph(5);

        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 3);
        addEdge(3, 0);
        addEdge(3, 4);

        System.out.println("is Cycle present: " + hasCycle());
    }
}
