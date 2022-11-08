package IK.Graphs.Class;

import java.util.ArrayList;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
 * you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0
 * you should also have finished course 1. So it is impossible.
 *
 *
 * Constraints:
 *
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 *
 * Approach
 * 1) To find out if you can finish all the courses, there shouldn't be any cycle.
 * 2) In a directed graph, we need to detect cycle.
 * 3) We can do DFS for detecting cycle
 * 4) We need outer loop because there could be connected components.
 * 5) If departure time is not set then its back edge which indicates cycle.
 *
 * TC: O(V + E) for graph construction and DFS traversal
 * SC: O(V + E) for graph and O(V) for visited, arrival and departure
 */
public class CourseSchedule {

    private static int vertices;
    private static ArrayList<Integer>[] graph;
    private static int time;

    private static void initializeGraph(int n) {
        vertices = n;
        time = 0;

        graph = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    private static void addEdge(int start, int end, boolean bidir) {
        graph[start].add(end);

        if(bidir) {
            graph[end].add(start);
        }
    }

    private static boolean canFinish(int n, int[][] prereq) {
        initializeGraph(n);

        for(int[] edge: prereq) {
            addEdge(edge[0], edge[1], false);
        }

        boolean[] visited = new boolean[n];
        int[] arr = new int[n];
        int[] dep = new int[n];

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                if(dfs(i, visited, arr, dep)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean dfs(int start, boolean[] visited, int[] arr, int[] dep) {
        visited[start] = true;
        arr[start] = time++;

        for(int w: graph[start]) {
            if(!visited[w]) {
                if(dfs(w, visited, arr, dep)) {
                    return true;
                }
            } else {
                //Back Edge
                if(dep[w] == 0) {
                    return true;
                }
            }
        }
        dep[start] = time++;
        return false;
    }

    public static void main(String[] args) {
        int n = 2;
        int[][] edges = {{1, 0}, {0, 1}};

        System.out.println("Can Finish courses: " + canFinish(n, edges));
    }
}
