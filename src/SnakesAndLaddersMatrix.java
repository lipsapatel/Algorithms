import java.util.*;

/**
 * Snakes and Ladders Matrix
 Find the minimum number of die rolls necessary to reach the final cell of the given Snakes and Ladders board game.
 Rules are as usual: Player starts from cell one, rolls a die and advances 1-6 (random number of) steps at a time.
 Should they land on a cell where a ladder starts, player immediately climbs up that ladder. Similarly, having landed on a
 cell with a snake’s mouth, player goes down to the tail of that snake before they roll the die again. Game ends when the
 player arrives at the last cell.

 Input Format:
 Function has two arguments:
 n, integer, size of the board and
 moves,
 array of integer describing the snakes and ladders as follows:
 moves[i] = -1: no ladder or snake starts at cell i,
 moves[i]  < i: snake from i down to moves[i]
 moves[i]  > i: ladder from i up to moves[i]
 Indices and values in moves array are zero-based, for example moves[1]=8 means there is a ladder from cell 2 up to cell 9.

 Output Format:
 Function must return an integer, the minimum number of dice rolls required to reach the last cell.
 Return -1 if there is no possible way.

 Constraints:
 •	1 <= n <= 10^5
 •	0 <=  moves[i] <= n-1
 •	No snake starts from the last cell and no ladder starts from the first cell.
 •	No snake or ladder starts from a cell where another snake or ladder arrives to.

 Sample Input 1:
 n = 20, moves = [-1, 18, -1, -1, -1, -1, -1, -1, 2, -1, -1, 15, -1, -1, -1, -1, -1, -1, -1, -1]

 Sample Output 1:
 2

 Having started from cell 1, player rolls 1 and moves to cell 2. The stair takes them from cell 2 to cell 19. They then roll 1 to arrive to the last, 20th cell. No fewer die rolls can bring a player to the last cell.

 Sample Input 2:
 n = 8, moves = [-1, -1, -1, -1, -1, -1, -1, -1]

 Sample Output 2:
 2

 There are several ways to reach the last cell in two rolls: 6+1, 5+2, 4+3, 3+4, 2+5, 1+6. No way to reach it with any fewer rolls though.

 Custom Output Format:
 The only line of output contains the return integer value, e.g.
 2

 * Time Complexity: O(V + E) where V = n and E = n*6 so TC = O(n)
 * Space Complexity: O(V + E) for graph and O(V) for visited and queue = O(n)
 *
 * Approach
 * 1) Construct graph
 * 2) Do BFS and maintain distance array, when target is reached return the distance.
 *
 * resources/SnakesAndLaddersMatrix.jpg
 */
public class SnakesAndLaddersMatrix {

    private static int minNumberOfRolls(int n, List<Integer> moves) {

        HashMap<Integer, HashSet<Integer>> graph = createGraph(n, moves);

        int start = 0;
        int target = n - 1;

        return bfs(graph, start, target);
    }

    private static int bfs(HashMap<Integer, HashSet<Integer>> graph, int start, int target) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, Integer> distance = new HashMap<>();

        queue.add(start);
        visited.add(start);
        distance.put(start, 0);

        //If target is the first node
        if(target == start) {
            return distance.get(target);
        }

        while(!queue.isEmpty()) {
            int poppedNode = queue.remove();

            HashSet<Integer> neighbors = graph.get(poppedNode);

            if(neighbors != null) {
                for(int av: neighbors) {
                    if(!visited.contains(av)) {
                        queue.add(av);
                        visited.add(av);
                        distance.put(av, distance.get(poppedNode) + 1);

                        if(av == target) {
                            return distance.get(av);
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static HashMap<Integer, HashSet<Integer>> createGraph(int n, List<Integer> moves) {

        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

        //Each cell is a vertex in graph
        for(int i = 0; i < n; i++) {
            //Neighbors
            HashSet<Integer> neighbors = new HashSet<>();
            for(int j = 1; j <= 6; j++) {

                if(i + j < n) {
                    if(moves.get(i + j) != -1) { //either snake or ladder
                        neighbors.add(moves.get(i + j));
                    } else {
                        neighbors.add(i + j);
                    }
                }
            }
            graph.put(i, neighbors);
        }
        return graph;
    }

    public static void main(String[] args) {
        int n = 20;
        List<Integer> moves = new ArrayList<>();
        moves.add(-1);
        moves.add(18);
        moves.add(-1);
        moves.add(-1);
        moves.add(-1);
        moves.add(-1);
        moves.add(-1);
        moves.add(-1);
        moves.add(2);
        moves.add(-1);
        moves.add(-1);
        moves.add(15);
        moves.add(-1);
        moves.add(-1);
        moves.add(-1);
        moves.add(-1);
        moves.add(-1);
        moves.add(-1);
        moves.add(-1);
        moves.add(-1);

        System.out.println("The minimum dice rolls required are: " + minNumberOfRolls(n, moves));

        n = 1;
        moves = new ArrayList<>();
        moves.add(-1);

        System.out.println("The minimum dice rolls required are: " + minNumberOfRolls(n, moves));

    }
}

