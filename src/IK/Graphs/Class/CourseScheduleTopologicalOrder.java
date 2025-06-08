package IK.Graphs.Class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
 * you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take
 * course 1.
 * Return the ordering of courses you should take to finish all courses. If there are
 * many valid answers, return any of them. If it is impossible to finish all courses,
 * return an empty array.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should
 * have finished course 0. So the correct course order is [0,1].
 * Example 2:
 *
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have
 * finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished
 * course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * Example 3:
 *
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 *
 *
 * Constraints:
 *
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 *
 * Approach
 * 1) For topological sort order, do DFS with book keeping and add the vertex to list while
 * returning from recursive call
 * 2) Return the list in reverse order which is decreasing departure time.
 * 3) If the graph has cycle, return empty list since its impossible to find topological
 * sort order
 *
 * TC: O(V + E) Graph construction and DFS
 * SC: O(V + E) Graph and O(V) for DFS
 */
public class CourseScheduleTopologicalOrder {

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

    private static void addEdge(int start, int end) {
        graph[start].add(end);
    }

    private static int[] findOrder(int n, int[][] prereq) {
        initializeGraph(n);

        for(int[] edge: prereq) {
            addEdge(edge[1], edge[0]);
        }

        boolean[] visited = new boolean[n];
        int[] arr = new int[n];
        int[] dep = new int[n];
        ArrayList<Integer> topoList = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                if(dfs(i, visited, arr, dep, topoList)) {
                    return new int[0];
                }
            }
        }
        Collections.reverse(topoList);
        return topoList.stream().mapToInt(i->i).toArray();
    }

    private static boolean dfs(int start, boolean[] visited, int[] arr, int[] dep, ArrayList<Integer> topoList) {
        visited[start] = true;
        arr[start] = ++time;

        for(int w: graph[start]) {
            if(!visited[w]) {
                if(dfs(w, visited, arr, dep, topoList)) {
                    return true;
                }
            } else {
                if(dep[w] == 0) {
                    return true;
                }
            }
        }
        topoList.add(start);
        dep[start] = ++time;
        return false;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {{1,0},{2,0},{3,1},{3,2}};

        System.out.println("Topological course order: " + Arrays.toString(findOrder(n, edges)));
    }
}
