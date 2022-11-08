package IK.Graphs.Class;

/**
 * In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.
 *
 * If the town judge exists, then:
 *
 * The town judge trusts nobody.
 * Everybody (except for the town judge) trusts the town judge.
 * There is exactly one person that satisfies properties 1 and 2.
 * You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi.
 *
 * Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2, trust = [[1,2]]
 * Output: 2
 * Example 2:
 *
 * Input: n = 3, trust = [[1,3],[2,3]]
 * Output: 3
 * Example 3:
 *
 * Input: n = 3, trust = [[1,3],[2,3],[3,1]]
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1 <= n <= 1000
 * 0 <= trust.length <= 104
 * trust[i].length == 2
 * All the pairs of trust are unique.
 * ai != bi
 * 1 <= ai, bi <= n
 *
 * Approach
 * 1) Everyone trust town judge so the indegree = n - 1
 * 2) Town judge trust no one so the outdegree = 0
 * 3) We can have only one array and we add indegree and subtract outdegree.
 * 4) For town judge indegree - outdegree = n - 1
 * 5) Return -1  if there is not any town judge.
 * 6) There cannot be 2 town judge because two judge has to trust each other and town judge
 * don't trust anyone.
 *
 * TC: O(E + N) Here E >= N - 1 = O(E)
 * SC: O(N)
 *
 * This is also called universal sink problem because
 * indegree for universal sink = n - 1 and outdegree = 0
 */
public class FindTownJudgeUniversalSink {

    private static int findTownJudge(int n, int[][] trust) {
        if(trust.length < n - 1) {
            return -1;
        }

        int[] degree = new int[n + 1];

        for(int[] edge: trust) {
            degree[edge[0]]--;
            degree[edge[1]]++;
        }

        for(int i = 1; i <= n; i++) {
            if(degree[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] trust = {{1, 3}, {2, 3}};

        System.out.println("Town judge is " + findTownJudge(n, trust));
    }
}
