import javafx.util.Pair;

import java.util.*;

/**
 * Given a weighted DAG (directed acyclic graph), where weight of an edge denotes the length of that edge, a node from_node and a node to_node, you have to find longest path from from_node to to_node.
 Nodes in the graph are numbered from 1 to dag_nodes, where dag_nodes denotes the total number of nodes in the graph.

 Input Format:
 There are six arguments.
 1.	An integer dag_nodes
 2.	An integer array dag_from
 3.	An integer array dag_to
 4.	An integer array dag_weight
 5.	An integer from_node
 6.	An integer to_node

 Output Format:
 Return an array pathArr, describing longest path from from_node to to_node.
 There can be multiple longest paths, so you are free to return any of them.

 Input Format:
 If dag_nodes = 4, dag_edges = 4, dag_from = [1, 1, 1, 3], dag_to = [2, 3, 4, 4], dag_weight =  [2, 2, 4, 3], from_node = 1 and to_node = 4, then input should be:

 4 4
 1 2 2
 1 3 2
 1 4 4
 3 4 3
 1
 4

 Output Format:
 5 is the length of longest path in weighted DAG.
 Actual path is:
 1
 3
 4

 Constraints:
 •	Given graph does not contain multi edges (there will not be more than one edge connecting same pair of vertices).
 •	to_node is reachable from from_node.
 •	1 <= dag_nodes <= 450
 •	1 <= dag_from[i], dag_to[i], from_node, to_node <= dag_nodes
 •	1 <= dag_weight[i] <= 2 * 10^9
 •	Total number of edges in graph (dag_edges) <=
 (dag_nodes * (dag_nodes - 1)) / 2

 Sample Input:
 dag_nodes = 4
 dag_from = [1 1 1 3]
 dag_to = [2 3 4 4]
 dag_weight =  [2 2 4 3]
 from_node = 1
 to_node = 4

 Sample Output:
 [1 3 4]

 Explanation:
 Total there are two paths from node 1 to node 4.
 1) 1 -> 4 with length 4.
 2) 1 -> 3 -> 4 with length 2 + 3 = 5.
 So, Longest path from node 1 to node 4 is [1 3 4] with length 5.

 Solution
 If you are getting wrong answer then first thing to check is that you are using appropriate data type to store the values.
 int will not work, need to use long long int.
 Given graph is dag. In dag we can divide our nodes in different levels, with "each edge" going from upper level to lower level.
 So we can start updating maximum length level wise, starting from upper level and then moving to level below it!
 To divide nodes level wise with each edge going from upper level to lower level, we can use topological sort!
 For more clear idea have a look at the solution provided by us.
 Time complexity of the solution is O(number of nodes in dag + number of edges in dag) and that can be re-written as O(number of edges in dag)
 from given constraints.
 Auxiliary space used by the solution is O(number of nodes in dag + number of edges in dag) and that can be re-written as O(number of edges in dag)
 from given constraints.
 Space complexity of the solution is also O(number of nodes in dag + number of edges in dag) and that can be re-written as O(number of edges in dag)
 from given constraints.
 Note that we should not write any of them as O(numberber of nodes in dag) because number of edges in dag can be way larger than number of nodes!

 * Approach
 *
 * 1) Do topological sort using DFS
 * 2) Pop from stack and update distance for each node to find the longest distance. Also maintain pathRef for building the longest path.
 *
 * Dijkstra cannot be used for finding longest distance.
 *
 *  resources/LongestPathInDAG.jpg
 *  resources/LongestPathInDAGNoDijkstra.jpg
 *
 * It's also called single source longest path
 *
 * Time Complexity: O(V + E)
 * Space Complexity: O(V + E)
 */
public class LongestPathInWeightedDAG {

    private static HashMap<Integer, HashSet<Pair<Integer, Long>>> graph = new HashMap<>(); //Weight could be really big

    private static int[] findLongestPath(int dagNodes, int[] dagFrom, int[] dagTo, int[] dagWeight, int source, int destination) {

        //Create Graph
        createGraph(dagNodes, dagFrom, dagTo, dagWeight);

        //Do topological sort TC = O(V + E)
        Stack<Integer> stack = new Stack<>();
        getTopologicalSort(stack);

        //Find the longest distance by iterating the stack/topological order TC = O(V + E)
        return findLongestDistancePath(stack, source, destination);
    }

    private static int[] findLongestDistancePath(Stack<Integer> stack, int source, int destination) {
        HashMap<Integer, Long> distance = new HashMap<>(); //(node, distance)
        HashMap<Integer, Integer> pathRef = new HashMap<>(); //(child, parent)

        for(Integer vertex: graph.keySet()) {
            distance.put(vertex, Long.MIN_VALUE);
        }
        distance.put(source, (long) 0);

        while(!stack.isEmpty()) {
            int poppedVertex = stack.pop();

            if(poppedVertex == destination) {
                return buildPath(pathRef, destination);
            }

            HashSet<Pair<Integer, Long>> neighbor = graph.get(poppedVertex);

            if(neighbor != null) {
                for(Pair<Integer, Long> av: neighbor) {
                    long newDistance = distance.get(poppedVertex) + av.getValue();
                    if(newDistance > distance.get(av.getKey())) {
                        distance.put(av.getKey(), newDistance);
                        pathRef.put(av.getKey(), poppedVertex);
                    }
                }
            }
        }

        return new int[0];
    }

    private static int[] buildPath(HashMap<Integer, Integer> pathRef, int destination) {
        List<Integer> result = new ArrayList<>();
        result.add(0, destination);

        int current = destination;
        while(pathRef.get(current) != null) {
            current = pathRef.get(current);
            result.add(0, current);
        }

        int[] resultArray = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }

    private static void getTopologicalSort(Stack<Integer> stack) {

        HashSet<Integer> visited = new HashSet<>();

        for(Integer vertex: graph.keySet()) {
            if(!visited.contains(vertex)) {
                dfs(vertex, visited, stack);
            }
        }
    }

    private static void dfs(int s, HashSet<Integer> visited, Stack<Integer> stack) {
        visited.add(s);

        HashSet<Pair<Integer, Long>> neighbor = graph.get(s);

        if(neighbor != null) {
            for(Pair<Integer, Long> av: neighbor) {
                if(!visited.contains(av.getKey())) {
                    dfs(av.getKey(), visited, stack);
                }
            }
        }
        stack.push(s);
    }

    private static void createGraph(int dagNodes, int[] dagFrom, int[] dagTo, int[] dagWeight) {
        //1 to dagNodes
        for (int i = 1; i <= dagNodes; i++) {
            graph.put(i, new HashSet<>());
        }

        for(int i = 0; i < dagFrom.length; i++) {
            HashSet<Pair<Integer, Long>> neighbor = graph.get(dagFrom[i]);

            Pair<Integer, Long> pair = new Pair<>(dagTo[i], (long)dagWeight[i]);
            neighbor.add(pair);

            graph.put(dagFrom[i], neighbor);
        }
    }

    public static void main(String[] args) {
        int dagNodes = 4;
        int[] dagFrom = {1, 1, 1, 3};
        int[] dagTo = {2, 3, 4, 4};
        int[] dagWeight = {2, 2, 4, 3};
        int source = 1;
        int destination = 4;

        System.out.println("The longest path from 1 to 4 is " + Arrays.toString(findLongestPath(dagNodes, dagFrom, dagTo, dagWeight, source, destination)));

        int dagNodes1 = 3;
        int[] dagFrom1 = {3, 2, 3};
        int[] dagTo1 = {2, 1, 1};
        int[] dagWeight1 = {2000000000, 2000000000, 2000000000};
        int source1 = 3;
        int destination1 = 1;

        System.out.println("The longest path from 3 to 1 is " + Arrays.toString(findLongestPath(dagNodes1, dagFrom1, dagTo1, dagWeight1, source1, destination1)));
    }
}
