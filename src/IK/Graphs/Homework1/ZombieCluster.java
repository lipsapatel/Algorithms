package IK.Graphs.Homework1;

import java.util.ArrayList;

/**
 * Zombie Clusters
 * There are zombies in Seattle. Some of them know each other directly. Others might know each other transitively, through others.
 * For example, if zombies A<->B and B<->C know each other directly, then A and C know each other indirectly and all three belong
 * to one cluster.
 *
 * Knowing which zombies know each other directly, find the number of the zombie clusters.
 *
 * Input is a square matrix where each cell, zombies[A][B], indicates whether zombie A knows zombie B directly.
 *
 * Example One
 * Example one
 *
 * {
 * "zombies": [
 * "1100",
 * "1110",
 * "0110",
 * "0001"
 * ]
 * }
 * Output:
 *
 * 2
 * There are four zombies: z0, z1, z2 and z3.
 * Each zombie knows oneself, thus the matrix diagonal has all 1s.
 * Other than that, the green matrix cells indicate that z0<->z1 and z1<->z2 know each other directly.
 * Because of the transitive property, zombies z0, z1 and z2 form one cluster.
 * The remaining zombie, z3, doesn't know others and forms their own separate cluster.
 * This gives us a total of 2 zombie clusters.
 *
 * Example Two
 * Example one
 *
 * {
 * "zombies": [
 * "10000",
 * "01000",
 * "00100",
 * "00010",
 * "00001"
 * ]
 * }
 * Output:
 *
 * 5
 * Each zombie forms their own cluster: {z0}, {z1}, {z2}, {z3}, and {z4}. The total number of clusters is 5.
 *
 * Notes
 * Constraints:
 *
 * 1 <= number of zombies <= 1000
 *
 * Approach
 *
 * 1) Count number of connected components problem
 * 2) DFS with outer loop
 * 3) Vertices = Number of zombies
 * 4) Neighbor or edge = row
 *
 * TC: O(n X n) There will be n^2 edges
 * SC: O(n) - Visited and recursive call stack
 */
public class ZombieCluster {

    private static int zombieCluster(ArrayList<String> zombies) {
        int cluster = 0;
        int n = zombies.size();
        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(i, visited, zombies, n);
                cluster++;
            }
        }
        return cluster;
    }

    private static void dfs(int start, boolean[] visited, ArrayList<String> zombies, int n) {
        visited[start] = true;

        for(int i = 0; i < n; i++) { //column
            if(!visited[i] && zombies.get(start).charAt(i) == '1') { //Zoombie start knows zoombie i so start dfs on zoombie i
                dfs(i, visited, zombies, n);
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<String> zombies = new ArrayList<>();
        zombies.add("1100");
        zombies.add("1110");
        zombies.add("0110");
        zombies.add("0001");

        System.out.println("Number of Zombie clusters are " +zombieCluster(zombies));
    }
}
