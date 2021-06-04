import java.util.LinkedList;
import java.util.Stack;

/**
 * Given a Graph - do Depth First Traversal(DFS)
 *
 * DFS is an algorithm for traversing or searching tree or graph data structure.
 *
 * One starts at root(selecting arbitrary node as root in case of graph) and explores as far
 * as possible along each branch before backtracking.
 *
 * resources/GraphDepthFirstTraversal.png
 *
 * Approach:
 *
 * 1) Use stack
 * 2) Add starting node to the stack and mark visited as true.
 * 3) Pop out an element from stack and print it.
 * 4) Add all connected nodes to the stack if not visited and mark visited as true.
 * 5) Repeat above two steps until the stack is empty.
 *
 *
 */
public class GraphDepthFirstTraversal {

    private static int vertex = 6;
    private static LinkedList<Integer> graphList[] = new LinkedList[vertex];

    private static void addEdge(int source, int destination) {

        graphList[source].addFirst(destination);
    }

    private static void DFSTraversal(int startIndex) {

        System.out.println("Depth First Traversal:");
        boolean[] visited = new boolean[vertex];

        Stack<Integer> stack = new Stack<>();

        stack.push(startIndex);
        visited[startIndex] = true;

        while(!stack.isEmpty()) {

            int poppedIndex = stack.pop();

            System.out.print(poppedIndex + " ");

            //Add all the connected nodes to the stack
            LinkedList<Integer> connectedNodeList = graphList[poppedIndex];

            for (int i = 0; i < connectedNodeList.size(); i++) {
                int connectedNode = connectedNodeList.get(i);

                if (!visited[connectedNode]) {
                    stack.push(connectedNode);
                    visited[connectedNode] = true;
                }
            }
        }

        System.out.println();
    }

    private static void printGraph() {

        for (int i = 0; i < vertex; i++) {

            LinkedList<Integer> connectedNodeList = graphList[i];

            if (!connectedNodeList.isEmpty()) {

                System.out.print("source = " + i + " is connected to nodes: ");

                for (int j = 0; j < connectedNodeList.size(); j++) {
                    System.out.print(" " + connectedNodeList.get(j));
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < vertex; i++) {
            graphList[i] = new LinkedList<>();
        }

        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(3, 4);
        addEdge(2, 3);
        addEdge(4, 0);
        addEdge(4, 1);
        addEdge(4, 5);
        printGraph();

        DFSTraversal(0);
    }
}
