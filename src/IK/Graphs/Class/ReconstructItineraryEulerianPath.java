package IK.Graphs.Class;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * You are given a list of airline tickets where tickets[i] = [fromi, toi]
 * represent the departure and the arrival airports of one flight.
 * Reconstruct the itinerary in order and return it.
 *
 * All of the tickets belong to a man who departs from "JFK", thus,
 * the itinerary must begin with "JFK". If there are multiple valid itineraries,
 * you should return the itinerary that has the smallest lexical order when read
 * as a single string.
 *
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * You may assume all tickets form at least one valid itinerary.
 * You must use all the tickets once and only once.
 *
 *
 * Example 1:
 *
 *
 * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 *
 * Example 2:
 *
 *
 * Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],
 * ["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is
 * ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
 *
 * resources/ReconstructItinerary1.jpg
 * resources/ReconstructItinerary2.jpg
 *
 * Approach
 * 1) This is Eulerian Path problem.
 * 2) We have Eulerian path if we have 0 or 2 odd degree vertices.
 * 3) Here its given the there exists Eulerian Path
 * 4) Eulerian Path is the path which visits all edges exactly once.
 * 5) To find Eulerian Path store the vertex and it's neighbors as graph.
 * 6) The neighbors are in sorted order because we want lowest lexical order path.
 * 7) Do DFS and remove the neighbor after visiting them which we remove that edge.
 * 8) After we are done with all neighbors we add that vertex to result list.
 * 9) Return list in reverse order.
 *
 * TC: O(n + m) Sorting = mlogm in worst case if m == n then nlogn
 * SC: O(n + m)
 */
public class ReconstructItineraryEulerianPath {

    private static HashMap<String, LinkedList<String>> graph;

    private static void initializeGraph() {
        graph = new HashMap<>();
    }

    private static void addEdge(String start, String end) {
        graph.putIfAbsent(start, new LinkedList<>());
        graph.get(start).add(end);
    }

    private static List<String> findItinerary(List<List<String>> tickets) {
        initializeGraph();

        for(List<String> edge: tickets) {
            addEdge(edge.get(0), edge.get(1));
        }

        //Sort Neighbors
        for(Map.Entry<String, LinkedList<String>> entry: graph.entrySet()) {
            Collections.sort(entry.getValue()); //Cannot use queue because we cannot sort queue
        }

        //DFS
        List<String> result = new ArrayList<>();

        dfs("JFK", result);
        Collections.reverse(result);
        return result;
    }

    private static void dfs(String start, List<String> result) {

        if(graph.containsKey(start)) {

            LinkedList<String> neighbors = graph.get(start);

            while(!neighbors.isEmpty()) {

                dfs(neighbors.removeFirst(), result);
            }
        }
        result.add(start);
    }
    public static void main(String[] args) {
        List<List<String>> input = new ArrayList<>();

        List<String> itinerary = new ArrayList<String>();
        itinerary.add("JFK");
        itinerary.add("SFO");
        input.add(itinerary);

        itinerary = new ArrayList<>();
        itinerary.add("JFK");
        itinerary.add("ATL");
        input.add(itinerary);

        itinerary = new ArrayList<>();
        itinerary.add("SFO");
        itinerary.add("ATL");
        input.add(itinerary);

        itinerary = new ArrayList<>();
        itinerary.add("ATL");
        itinerary.add("JFK");
        input.add(itinerary);

        itinerary = new ArrayList<>();
        itinerary.add("ATL");
        itinerary.add("SFO");
        input.add(itinerary);

        System.out.println(findItinerary(input));
    }
}
