import java.util.HashMap;
import java.util.Vector;

/**
 * Given A Graph, Build A New One With Reversed Edges
 Given a strongly connected directed graph G, containing n nodes and m edges, you have to build a new graph containing n nodes,
 where edges are reversed than the original graph G.
 This is also called Transposing the graph.

 Input Format:
 There is only one argument pointing to a random node of the graph G.

 Output Format:
 Return any node in the new graph.
 As input is a strongly connected graph, you will be able to visit all the nodes, given any random node.
 Also when we reverse all the edges of strongly connected graph it will remain strongly connected graph, hence returning any one node is sufficient.

 Constraints:

 1 <= n <= 315
 Given graph does not contain multi edges (there will not be more than one edge connecting same pair of vertices in the same direction) and self loops.
 Each node contains distinct values.
 1 <= value stored in node <= n
 You are not allowed to modify the given graph. Return completely new graph.

 Sample Input:
 Any node of the graph where:
 For n = 3
 nodes = [1 2 3]
 edges = [(1 -> 2), (2 -> 3), (3 -> 1)]
 you could be given any node (1, 2 or 3) as input.

 Sample Output:
 Any node of the new graph where:
 For n = 3
 nodes = [1 2 3]
 edges = [(2 -> 1), (3 -> 2), (1 -> 3)]

 you could return any node as output.
************************************************************************************
 Solution:
 To solve this problem simple DFS will work!

 Time Complexity:
 As we are doing normal DFS, time complexity of our solution will be O(n + e) where n denotes the number of nodes and e denotes the number of edges.
 (We are given that it does not contain multiple edges and self loops, hence in the worst case the maximum number of edges possible is n * (n - 1).
 So time complexity will be O(n + n^2) that is O(n^2).)

 Space complexity:
 As we are creating a new graph, space complexity of our solution will be O(n + e) where n denotes the number of nodes and e denotes the number of edges.
 (We are given that it does not contain multiple edges and self loops, hence in the worst case the maximum number of edges possible is n * (n - 1).
 So space complexity will be O(n + n^2) that is O(n^2).)
 *********************************************************************************************
 *
 * Approach:
 *
 * 1) Do DFS and maintain visited hashSet which contains the integer value of node and node itself.
 *
 * TC = O(V + E)
 * SC = O(V + E)
 */
public class ReverseTransposeGraph {

    public static class Node {
        Integer val;
        Vector<Node> neighbors = new Vector<>();

        public Node(Integer val) {
            this.val = val;
            neighbors.clear();
        }
    }

    public static Node buildReverseGraph(Node node) {
        HashMap<Integer, Node> visited = new HashMap<>();

        dfs(node, visited);
        return visited.get(node.val);
    }

    public static void dfs(Node s, HashMap<Integer, Node> visited) {
        visited.put(s.val, new Node(s.val));

        Vector<Node> neighbors = s.neighbors;

        for(Node av: neighbors) {
            if(!visited.containsKey(av.val)) {
                dfs(av, visited);
            }

            //3->1 becomes 1->3
            visited.get(av.val).neighbors.add(visited.get(s.val));
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        node1.neighbors.add(node2);
        node2.neighbors.add(node3);
        node3.neighbors.add(node1);

        Node reverseGraph = buildReverseGraph(node1);

    }
}
