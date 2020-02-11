import java.util.*;

/**
 * Given list of words = {cat, cut, bat, bit, but}
 * start = cat
 * end = but
 *
 * Output: cat, bat, but - it could be this
 * cat, cut, but -
 * cat, bat, bit, but
 *
 * The output should be the shortest path.
 *
 * Approach
 *
 * 1) The problem is to find the shortest path from start to end and return that path
 * 2) The path can be build using backRefs
 * 3) The shortest path can be found using bfs
 * 4) We need to create adjacencylist or graph
 * 5) Creating graph will take O(NM) time where N = no of words and M = word length
 * 6) getNeighbors will return union of all the list excluding the word itself
 *
 * *at = cat, bat
 * c*t = cat, cut
 * ca* = cat
 * *ut = cut, but
 * cu* = cut
 * b*t = bat, bit, but
 * ba* = bat
 * *it = bit
 * bi* = bit
 * bu* = but
 *
 * Time Complexity = O(NM)
 * Space Complexity = O(NM)
 */
public class WordLadder {

    private static HashMap<String, List<String>> graphList = new HashMap<>();

    private static void createGraph(List<String> words) {

        for (String word: words) {
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + '*' + word.substring(i + 1, word.length());

                if (graphList.containsKey(key)) {

                    List<String> list = graphList.get(key);
                    list.add(word);
                    graphList.put(key, list);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(word);
                    graphList.put(key, list);
                }
            }
        }
    }

    private static HashSet<String> getNeighbors(String word) {

        HashSet<String> result = new HashSet<>();

        for (int i = 0; i < word.length(); i++) {

            String key = word.substring(0, i) + "*" + word.substring(i + 1, word.length());
            List<String> list = graphList.get(key);
            for (String s: list) {
                if (!s.equals(word)) {
                    result.add(s);
                }
            }
        }
        return result;
    }

    private static List<String> bfs(String start, String end, List<String> words) {

        createGraph(words);
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        HashMap<String, String> backRefs = new HashMap<>(); //child, parent

        queue.add(start);
        visited.add(start);

        while(!queue.isEmpty()) {

            String poppedNode = queue.remove();
           // if (poppedNode.equalsIgnoreCase(end)) { //If start = end and no transformation needed and in that case
            // if you want to add start to the return list then do this
             //   return buildPath(backRefs, end);
            //} else {

                HashSet<String> adjacentVertex = getNeighbors(poppedNode);

                if (adjacentVertex != null) {
                    for(String vertex: adjacentVertex) {
                        if (!visited.contains(vertex)) {

                            queue.add(vertex);
                            visited.add(vertex);
                            backRefs.put(vertex, poppedNode);
                            if (vertex.equalsIgnoreCase(end)) {
                                return buildPath(backRefs, end);
                            }
                        }
                    }
                }
            //}
        }
        return new ArrayList<>();
    }

    private static List<String> buildPath(HashMap<String, String> backRefs, String end) {
        List<String> result = new ArrayList<>();

        result.add(0, end);
        String current = end;

        while(backRefs.get(current) != null) {
            current = backRefs.get(current);
            result.add(0, current);
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("cat");
        list.add("cut");
        list.add("bat");
        list.add("bit");
        list.add("but");

        List<String> ladder = bfs("cat", "but", list);
        for(String word: ladder) {
            System.out.println(word);
        }
    }
}
