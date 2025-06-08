package IK.Graphs.Optional;

import java.util.Arrays;

/**
 * The Earliest Moment When Everyone Become Friends
 *
 * Share
 * There are n people in a social group labeled from 0 to n - 1. You are given an array logs where logs[i] = [timestampi, xi, yi] indicates that xi and yi will be friends at the time timestampi.
 *
 * Friendship is symmetric. That means if a is friends with b, then b is friends with a. Also, person a is acquainted
 * with a person b if a is friends with b, or a is a friend of someone acquainted with b.
 *
 * Return the earliest time for which every person became acquainted with every other person. If there is no such earliest time, return -1.
 *
 * Example 1:
 *
 * Input: logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]], n = 6
 * Output: 20190301
 * Explanation:
 * The first event occurs at timestamp = 20190101 and after 0 and 1 become friends we have the following friendship groups [0,1], [2], [3], [4], [5].
 * The second event occurs at timestamp = 20190104 and after 3 and 4 become friends we have the following friendship groups [0,1], [2], [3,4], [5].
 * The third event occurs at timestamp = 20190107 and after 2 and 3 become friends we have the following friendship groups [0,1], [2,3,4], [5].
 * The fourth event occurs at timestamp = 20190211 and after 1 and 5 become friends we have the following friendship groups [0,1,5], [2,3,4].
 * The fifth event occurs at timestamp = 20190224 and as 2 and 4 are already friends anything happens.
 * The sixth event occurs at timestamp = 20190301 and after 0 and 3 become friends we have that all become friends.
 * Example 2:
 *
 * Input: logs = [[0,2,0],[1,0,1],[3,0,3],[4,1,2],[7,3,1]], n = 4
 * Output: 3
 *
 *
 * Constraints:
 *
 * 2 <= n <= 100
 * 1 <= logs.length <= 104
 * logs[i].length == 3
 * 0 <= timestampi <= 109
 * 0 <= xi, yi <= n - 1
 * xi != yi
 * All the values timestampi are unique.
 * All the pairs (xi, yi) occur at most one time in the input.
 *
 * Approach
 * 1) logs needs to be sorted by timestamp if its not already
 * 2) Union and Find where edges are dynamic datastructure and we need to calculate
 * number of connected components after every edge
 * 3) The time complexity of doing BFS/DFS is O(m (n + m)) = O(m^2)
 * 4) Do find to find the root of both ends to edge
 * 5) If the root is not same, then do union, make sure the tree length don't increase
 * and tree is balanced
 * 6) So that the find operation takes O(logn) time
 * 7) We need parent array and size array
   8) Decrement component after union
   9) If component is 1 then return the timestamp
 9) Do path compression when finding the rootx. Amortize TC for find will be constant.
 *
 * TC: Union operations = O(n - 1) and it takes O(1) time
 * Find - O(logn) for 2m edges = 2mlogn With Path compression O(1) for 2m edges  = 2m
 * If m = n, then nlogn with Path Compression n + 2m = n + m = n if the graph is sparse
 * So TC = O(n) + O(nlogn) for sorting

     SC = O(n) for parent and size array
 */
public class EveryoneBecomeFriends {

    private static int earliestAcq(int[][] logs, int n) {
        int[] parent = new int[n];
        int[] size = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        int component = n;

        Arrays.sort(logs, (a, b) -> {
            return a[0] - b[0];
        });

        for(int[] edge: logs) {

            int u = edge[1];
            int v = edge[2];

            int rootu = find(u, parent);
            int rootv = find(v, parent);

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

            if(component == 1) {
                return edge[0];
            }
        }
        return -1;
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
        int[][] logs = {{20190101,0,1},{20190104,3,4},{20190107,2,3},{20190211,1,5},
                {20190224,2,4},{20190301,0,3},{20190312,1,2},{20190322,4,5}};
        int n = 6;

        System.out.println("The timestamp when everyone is acquainted is " +earliestAcq(logs, n));
    }
}
