package IK.Graphs.Optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a graph, construct Minimum Spanning Tree
 *
 * Approach
 * 1) Scan the edges in increasing order of their weights.
 * 2) If adding edge creates cycle then drop it.
 * 3) Keep track of edges added and their cost.
 * 4) Kruskal's algorithm uses Union and Find to add edges in increasing order of their weight.
 * 5) If the graph is disconnected and number of components is not 1 then we cannot construct MST
 * 6) Return cost or edges in MST
 * 7) Do Quick Union with path compression
 * 8) Also keep track of connected components. If its not 1 then MST cannot be constructed.
 *
 * TC: O(n) - For Union and Find
 * O(nlogn) - For sorting
 * O(nlogn)
 *
 * SC: O(n) - For parent array, size array, result holding edges
 */
public class KruskalMST {

    private static List<List<Integer>> kruskal(int[][] edges, int n) {

        int totalCost = 0;
        List<List<Integer>> result = new ArrayList<>();

        int[] parent = new int[n];
        int[] size = new int[n];
        int components = n;

        for(int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        //Sort edges by weight
        Arrays.sort(edges, (a, b) -> {return a[2] - b[2];});

        for(int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];

            int rootu = find(u, parent);
            int rootv = find(v, parent);

            if(rootu != rootv) {
                //Do union
                if(size[rootu] < size[rootv]) {
                    parent[rootu] = rootv;
                    size[rootv] += size[rootu];
                } else {
                    parent[rootv] = rootu;
                    size[rootu] += size[rootv];
                }
                components--;

                totalCost += edge[2];
                List<Integer> e = new ArrayList<>();
                e.add(edge[0]);
                e.add(edge[1]);
                result.add(e);
            }
        }
        System.out.println(totalCost);
        if(components == 1) {
            return result;
        } else {
            return new ArrayList<>(); //There are more than one connected components and graph is disconnected so MST cannot be constructed.
        }
    }

    private static int find(int x, int[] parent) {
        //Base Case
        if(x == parent[x]) {
            return x;
        } else {
            int rootx = find(parent[x], parent);
            parent[x] = rootx;
            return rootx;
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{5, 3, 2}, {5, 0, 3}, {0, 1, 7}, {1, 4, 9}, {4, 2, 8}, {2, 1, 11}, {0, 4, 5}, {3, 2, 12}};
        int n = 6;

        System.out.println(kruskal(edges, n).toString());
    }
}
