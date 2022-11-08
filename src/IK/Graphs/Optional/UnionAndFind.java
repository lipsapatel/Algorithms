package IK.Graphs.Optional;

/**
 * 1) Union and Find is used when we have edges coming in and we need to keep
 * calculating number of connected components.
 * 2) We can do BFS/DFS to find number of components at each edge.
 * 3) Time Complexity for BFS/DFS = O(n + m) * O(m) = O(m^2) and if number of edges = n
 * then its O(n^2)
 * 4) When we do union, we balance the tree so that the find operation takes O(logn) time
 * 5) We need parent and size array
 * 6) Parent array is initialized with itself and size array to 1
 * 7) We first find the root of u and v
 * 8) Then we do union and decrement component count.
 * 9) Do path compression when finding the rootx. Amortize TC for find will be constant.
 *
 * TC: Union operations = O(n - 1) and it takes O(1) time
 * Find - O(logn) for 2m edges = 2mlogn With Path compression O(1) for 2m edges  = 2m
 * If m = n, then nlogn with Path Compression n + 2m = n + m = n if the graph is sparse
 * So TC = O(n)
 *
 * SC: O(n) for parent and size
 */
public class UnionAndFind {

    private static int unionAndFind(int[][] edges, int n) {
        int[] parent = new int[n];
        int[] size = new int[n];
        int component = n;

        for(int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for(int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];

            //Find
            int rootu = find(u, parent);
            int rootv = find(v, parent);

            //Union
            if(rootu != rootv) {
                if(size[rootu] < size[rootv]) {
                    parent[rootu] = rootv;
                    size[rootv] += size[rootu];
                } else {
                    parent[rootv] = rootu;
                    size[rootu] += size[rootv];
                }
                component--;
            }
        }
        return component;
    }

    private static int find(int x, int[] parent) {
        //Do path compression and then return root

        //Base Case
        if(x == parent[x]) {
            return x;
        } else {

            //Recursive case
            int rootx = find(parent[x], parent);
            parent[x] = rootx;
            return rootx;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0, 1}, {0, 2}, {3, 4}};
        System.out.println("Number of connected components " +unionAndFind(edges, n));
    }
}
