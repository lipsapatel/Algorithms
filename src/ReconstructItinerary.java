import java.util.*;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
 * reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK.
 * Thus, the itinerary must begin with JFK.

 Note:

 If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order
 when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 All airports are represented by three capital letters (IATA code).
 You may assume all tickets form at least one valid itinerary.

 Example 1:

 Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]

 Example 2:

 Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 But it is larger in lexical order.

 resources/ReconstructItinerary.jpg

 Eulerian Path - In graph theory, an eulerian trail or path is a path in a finite graph that visits every edge
 exactly once (allowing for revisiting vertices)

 Eulerian Cycle is eulerian path that starts and end on the same vertex.

 Approach:

 1) Build the graph using priorityQueue to achieve lexical order
 2) Do the dfs
 3) when done visiting all neighbors, add to list
 4) Print the path in reverse order

 Time Complexity: O(E + V)

 First keep going forward until you get stuck. That's a good main path already. Remaining tickets form cycles which are found on the
 way back and get merged into that main path. By writing down the path backwards when retreating from recursion,
 merging the cycles into the main path is easy -
 the end part of the path has already been written, the start part of the path hasn't been written yet, so just write down the
 cycle now and then keep backwards-writing the path.
 */
public class ReconstructItinerary {

    private static List<String> findItinerary(List<List<String>> tickets) {

        HashMap<String, PriorityQueue<String>> graph = new HashMap<>();

        buildGraph(graph, tickets);

        List<String> itinerary = new ArrayList<>();

        dfs(itinerary, graph, "JFK");
        Collections.reverse(itinerary);
        return itinerary;
    }

    private static void dfs(List<String> itinerary, HashMap<String, PriorityQueue<String>> graph, String source) {
        if (graph.get(source) == null) {
            itinerary.add(source);
            return;
        }

        /**
         * Try all the destination from this source incrementally.
         * This is important for input like [[JFK, KUL], [JFK, NRT], [NRT, JFK]]
         * because once you reach Kul, you can't go anywhere but we have tickets left, so we should go NRT first
         */
        while(!graph.get(source).isEmpty()) {
            String nextDestination = graph.get(source).poll();
            dfs(itinerary, graph, nextDestination);
        }
        itinerary.add(source);
    }

    private static void buildGraph(HashMap<String, PriorityQueue<String>> graph, List<List<String>> tickets) {

        for (List<String> ticket: tickets) {
            String source = ticket.get(0);
            String destination = ticket.get(1);

            if (!graph.containsKey(source)) {
                PriorityQueue<String> pq = new PriorityQueue<>();
                pq.add(destination);
                graph.put(source, pq);
            } else {
                graph.get(source).add(destination);
            }
        }
    }

    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();

        List<String> ticket = new ArrayList<>();
        ticket.add("JFK");
        ticket.add("A");

        tickets.add(ticket);

        ticket = new ArrayList<>();
        ticket.add("JFK");
        ticket.add("D");

        tickets.add(ticket);

        ticket = new ArrayList<>();
        ticket.add("A");
        ticket.add("C");

        tickets.add(ticket);

        ticket = new ArrayList<>();
        ticket.add("B");
        ticket.add("C");

        tickets.add(ticket);

        ticket = new ArrayList<>();
        ticket.add("C");
        ticket.add("JFK");

        tickets.add(ticket);

        ticket = new ArrayList<>();
        ticket.add("C");
        ticket.add("D");

        tickets.add(ticket);

        ticket = new ArrayList<>();
        ticket.add("D");
        ticket.add("A");

        tickets.add(ticket);

        ticket = new ArrayList<>();
        ticket.add("D");
        ticket.add("B");

        tickets.add(ticket);

        List<String> itinerary = findItinerary(tickets);

        for(String airport: itinerary) {
            System.out.print(airport + " ");
        }
    }
}
