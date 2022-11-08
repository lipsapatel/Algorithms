package IK.Graphs.Class;

import java.util.ArrayList;

/**
 * There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for
 * room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room
 * without having its key.
 *
 * When you visit a room, you may find a set of distinct keys in it.
 * Each key has a number on it, denoting which room it unlocks, and you can take
 * all of them with you to unlock the other rooms.
 *
 * Given an array rooms where rooms[i] is the set of keys that you can obtain
 * if you visited room i, return true if you can visit all the rooms, or false otherwise.
 *
 * Example 1:
 *
 * Input: rooms = [[1],[2],[3],[]]
 * Output: true
 * Explanation:
 * We visit room 0 and pick up key 1.
 * We then visit room 1 and pick up key 2.
 * We then visit room 2 and pick up key 3.
 * We then visit room 3.
 * Since we were able to visit every room, we return true.
 * Example 2:
 *
 * Input: rooms = [[1,3],[3,0,1],[2],[0]]
 * Output: false
 * Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.
 *
 *
 * Constraints:
 *
 * n == rooms.length
 * 2 <= n <= 1000
 * 0 <= rooms[i].length <= 1000
 * 1 <= sum(rooms[i].length) <= 3000
 * 0 <= rooms[i][j] < n
 * All the values of rooms[i] are unique.
 *
 * Approach
 *
 * 1) The problem can be modeled as a directed Graph problem.
 * 2) Rooms are the vertices
 * 3) The keys are the directed edges.
 * 4) This finding connected components, if they are more than 1 then return false or else
 * return true
 * 5) You can do either BFS or DFS
 *
 * TC: O(V + E) for graph and BFS/DFS
 * SC: O(V + E) for graph and O(V) for DFS or BFS
 *
 */
public class KeysAndRooms {

   // private static int vertices;
    //private static ArrayList<Integer>[] graph;

   /* private static void initializeGraph(int n) {
        vertices = n;
        graph = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    private static void addEdge(int start, int end) {
        graph[start].add(end);
    }
*/
    private static boolean canVisitAllRooms(ArrayList<ArrayList<Integer>> rooms) {

        //int roomIndex = 0;
        //There is no need to construct graph because the input is given in graph form

        int n = rooms.size();

        /*initializeGraph(n);

        for(ArrayList<Integer> keys: rooms) {
            for(int edge: keys) {
                addEdge(roomIndex, edge);
            }
            roomIndex++;
        }
*/
        boolean[] visited = new boolean[n];
        int count = 0;

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                count++;
                if(count > 1) {
                    return false;
                }

                dfs(i, visited, rooms);
            }
        }
        return true;
    }

    private static void dfs(int start, boolean[] visited, ArrayList<ArrayList<Integer>> rooms) {
        visited[start] = true;

        for(int w: rooms.get(start)) {
            if(!visited[w]) {
                dfs(w, visited, rooms);
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> rooms = new ArrayList<>();

        ArrayList<Integer> keys = new ArrayList<Integer>();
        keys.add(1);

        rooms.add(keys);

        keys = new ArrayList<>();
        keys.add(2);
        rooms.add(keys);

        keys = new ArrayList<>();
        keys.add(3);
        rooms.add(keys);

        keys = new ArrayList<>();
        rooms.add(keys);

        System.out.println("Can all rooms visited: " + canVisitAllRooms(rooms));
    }
}
