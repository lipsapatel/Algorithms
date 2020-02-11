import java.util.*;

/**
 * Course Schedule
 You need to take n courses and these courses are labeled from 0 to n-1. Few of these courses have prerequisites.
 You are given the prerequisites as a list of pairs where each pair is of form : [x, y]  where to take course 'x',
 you need to complete course 'y' before it. Given these pairs and also the count of total courses n,
 you need to return the ordering in which the courses should be taken. Note that there might be multiple possible answers,
 you need to just return any one of them and if any answer does not exist, return an array having -1.

 There are two arguments, n, prerequisites denoting number of courses and prerequisites list of pair
 (2d array where each inner array is of size 2) respectively.

 Output Format:
 Return an array with a possible ordering in which courses should be taken. And array containing -1 if no answer is possible.

 If n=4, e=4 and prerequisites=[ [1, 0], [2, 0], [3, 1], [3, 2] ], then input should be:
 4
 4
 1 0
 2 0
 3 1
 3 2

 Output Format:
 There should be a array of numbers representing resultant ordering of courses.
 If n=4, e=4 and prerequisites=[ [1, 0], [2, 0], [3, 1], [3, 2] ], then output should be:

 0
 1
 2
 3

 Constraints:
 1 <= n <= 10000
 0 <= e <= min(10000, (n*n-1)) where e denotes length of 2d array prerequisites.

 Explanation:
 As per sample input, course 3 is to be done after courses 1 and 2 are done. For both course 1 and 2,
 course 0 should be done before. So, two possible correct orderings are [0, 1, 2, 3] and [0, 2, 1, 3], any one of them can be returned.

 * TC = O(n + prerequisites.size())
 * SC = O(n + prerequisites.size()) for graph
 *
 * O(V + E)
 *
 * 1) Do DFS
 * 2) Maintain visitingPath for detecting cycle
 * 3) Add to list and return topological order. higher departure time to lower departure time
 *
 * This is a typical topological sorting problem which can be solved using DFS.  A directed graph is formed using each prerequisite
 * as an edge.Note that a possible ordering only exists if there is no cycle in the directed acyclic graph formed.
 */
public class CourseSchedule {

    private static List<Integer> courseSchedule(int n, List<List<Integer>> prerequisites) {

        //There are n nodes and prerequisites edges
        HashMap<Integer, HashSet<Integer>> graph = createGraph(n, prerequisites);

        HashSet<Integer> visited = new HashSet<>();
        HashSet<Integer> visitingPath = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        //DFS
        for(int i = 0; i < n; i++) {
            if(!visited.contains(i)) {
                if(dfs(i, visited, visitingPath, graph, result)) { //has cycle
                    result = new ArrayList<>();
                    result.add(-1);
                    return result;
                }
            }
        }

        //Higher departure time to lower departure time
        Collections.reverse(result);
        return result;
    }

    private static boolean dfs(int s, HashSet<Integer> visited, HashSet<Integer> visitingPath, HashMap<Integer, HashSet<Integer>> graph,
                               List<Integer> result) {

        visited.add(s);
        visitingPath.add(s);

        HashSet<Integer> neighbors = graph.get(s);

        if(neighbors != null) {
            for(int av: neighbors) {
                if(!visited.contains(av)) {
                    if(dfs(av, visited, visitingPath, graph, result)) { //cycle
                        return true;
                    }
                } else { //already visited check for cycle
                    if(visitingPath.contains(av)) {
                        return true;
                    }
                }
            }
        }
        visitingPath.remove(s);
        result.add(s);
        return false; //no cycle
    }

    private static HashMap<Integer, HashSet<Integer>> createGraph(int n, List<List<Integer>> prerequisites) {
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

        for(int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }

        //(A, B) B -> A
        for(List<Integer> edge: prerequisites) {
            HashSet<Integer> neighbor = graph.get(edge.get(1));
            neighbor.add(edge.get(0));
            graph.put(edge.get(1), neighbor);
        }
        return graph;
    }

    public static void main(String[] args) {
        List<List<Integer>> prerequisites = new ArrayList<>();
        List<Integer> edge = new ArrayList<>();
        edge.add(1);
        edge.add(0);
        prerequisites.add(edge);

        edge = new ArrayList<>();
        edge.add(2);
        edge.add(0);
        prerequisites.add(edge);

        edge = new ArrayList<>();
        edge.add(3);
        edge.add(1);
        prerequisites.add(edge);

        edge = new ArrayList<>();
        edge.add(3);
        edge.add(2);
        prerequisites.add(edge);

        List<Integer> result = courseSchedule(4, prerequisites);

        for(int order: result) {
            System.out.print(order + " ");
        }
    }
}
