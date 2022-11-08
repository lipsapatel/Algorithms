package IK.Graphs.Class;

import java.util.HashMap;

/**
 * Suppose you are at a party with n people labeled from 0 to n - 1 and among them,
 * there may exist one celebrity. The definition of a celebrity is that all the other
 * n - 1 people know the celebrity, but the celebrity does not know any of them.
 *
 * Now you want to find out who the celebrity is or verify that there is not one.
 * The only thing you are allowed to do is ask questions like: "Hi, A.
 * Do you know B?" to get information about whether A knows B.
 * You need to find out the celebrity (or verify there is not one) by asking as
 * few questions as possible (in the asymptotic sense).
 *
 * You are given a helper function bool knows(a, b) that tells you whether A knows B.
 * Implement a function int findCelebrity(n). There will be exactly one celebrity
 * if they are at the party.
 *
 * Return the celebrity's label if there is a celebrity at the party.
 * If there is no celebrity, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: graph = [[1,1,0],[0,1,0],[1,1,1]]
 * Output: 1
 * Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1
 * means person i knows person j, otherwise graph[i][j] = 0 means person i does
 * not know person j. The celebrity is the person labeled as 1 because both 0 and 2
 * know him but 1 does not know anybody.
 *
 * Example 2:
 *
 *
 * Input: graph = [[1,0,1],[1,1,0],[0,1,1]]
 * Output: -1
 * Explanation: There is no celebrity.
 *
 *
 * Constraints:
 *
 * n == graph.length == graph[i].length
 * 2 <= n <= 100
 * graph[i][j] is 0 or 1.
 * graph[i][i] == 1
 *
 *
 * Follow up: If the maximum number of allowed calls to the API knows is 3 * n,
 * could you find a solution without exceeding the maximum number of calls?
 *
 * Approach
 * 1) If A knows B, then A cannot be celebrity.
 * 2) If A does not knows B then B cannot be celebrity
 * 3) So  we will start with 0 as our celebrity candidate.
 * 4) Check if 0 knows 1, if yes then 0 cannot be celebrity candidate.
 * 5) Update celebrity candidate to 1.
 * 6) If 0 does not knows 1 then 1 cannot be celebrity candidate.
 * 7) Check if the celebrity candidate is celebrity by comparing with all vertex.
 * 8) The calls to knows API can be minimized by cache the results of knows API.
 *
 * TC: O(n)
 * SC: O(1)
 */
public class FindCelebrityUniversalSink {
    private static HashMap<HashMap<Integer, Integer> , Boolean> map = new HashMap<>();

    //This API is given
    private static boolean knows(int a, int b) {
      HashMap<Integer, Integer> key = new HashMap<>();
      key.put(a, b);

        return map.get(key);
    }

    private static int findCelebrity(int n) {
        int celebrityCandidate = 0;

        for(int i = 1; i < n; i++) {
            if(knows(celebrityCandidate, i)) {
                celebrityCandidate = i;
            }
        }

        if(isCelebrity(celebrityCandidate, n)) {
            return celebrityCandidate;
        }
        return -1;
    }

    private static boolean isCelebrity(int i, int n) {
        for(int j = 0; j < n; j++) {
           if(i == j) {
               continue;
           }

           if(knows(i, j) || !knows(j, i)) {
               return false;
           }
        }
        return true;
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> key = new HashMap<>();
        key.put(0, 0);

        map.put(key, true);

        key = new HashMap<>();
        key.put(0, 1);

        map.put(key, true);

        key = new HashMap<>();
        key.put(0, 2);

        map.put(key, false);

        key = new HashMap<>();
        key.put(1, 0);

        map.put(key, false);

        key = new HashMap<>();
        key.put(1, 1);

        map.put(key, true);

        key = new HashMap<>();
        key.put(1, 2);

        map.put(key, false);

        key = new HashMap<>();
        key.put(2, 0);

        map.put(key, true);

        key = new HashMap<>();
        key.put(2, 1);

        map.put(key, true);

        key = new HashMap<>();
        key.put(2, 2);

        map.put(key, true);

        System.out.println("The celebrity candidate is " +findCelebrity(3));
    }
}
