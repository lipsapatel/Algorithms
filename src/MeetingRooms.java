import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
 * find the minimum number of conference rooms required.
 *
 * The basic idea of the solution is that we sequentially assign meeting to a room.
 * We use a min heap to track the earliest ending meeting. Whenever an old meeting ends before a new meeting starts,
 * we remove the old meeting. Otherwise, we need an extra room.

 The meeting rooms problem or the airplanes on the runway problem are all similar and they basically wants you to find out the min
 number of the scarce recources you need given the demand.

 In the above example, you can see there are mutiple people who booked a meeting room and mentioned the start and end time of their meetings.
 To meet this demand, the operations team would need to know how many meeting rooms they need to facilitate to the team.
 In the case of airlines problem, you will be usually given a list of departure and arrival times and be asked to find out the
 minimum number of runways you require to enable smooth flow of air traffic.

 We sort the intervals based on the start time first, have a counter, compare each start time and the corresponding end time
 by adding end times to a priority queue. If start time is lesser, increment the counter, else pop the end time from queue.

 Time Complexity: O(nlogn + nlogn) = O(nlogn)
 Space Complexity: O(n)

 */
public class MeetingRooms {

    public static int minMeetingRooms(int[][] intervals) {

        //O(nlogn)
        Arrays.sort(intervals, new Comparator<int[]>() {

            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });

        //SC = O(n)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int count = 0;

        for (int[] interval : intervals) { //n
            if (minHeap.isEmpty()) { //logn
                count++;
                minHeap.offer(interval[1]);
            } else {
                if (minHeap.peek() <= interval[0]) {
                    minHeap.poll();
                } else {
                    count++;
                }
                minHeap.offer(interval[1]);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] intervals = {{0, 10}, {5, 15}, {20, 30}, {25, 35}};

        System.out.println("Minimum meeting rooms: " + minMeetingRooms(intervals));
    }
}
