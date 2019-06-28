import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given List of List
 *
 * {
 *     {2, 4, 5},
 *     {5, 7, 9},
 *     {3, 4}
 * }
 *
 * Merge them and print in sorted order
 * Output: 2, 3, 4, 4, 5, 5, 7, 9
 *
 * Approach:
 *
 * 1) Create min heap of first element
 * 2) Create heap node which has value, listIndex and elementIndex
 * 3) Extract from min heap and print
 * 4) Use the listIndex and elementIndex to get new element and insert into the heap.
 * 5) Keep doing this while heap is not empty
 *
 * Time Complexity: If list size = m and inner list max element = n
 * m + mnlogm
 * Space Complexity: O(m)
 */

public class MergeList {

    public static class HeapNode {
        int value;
        int listIndex;
        int elementIndex;

        public HeapNode(int val, int li, int ei) {
            value = val;
            listIndex = li;
            elementIndex = ei;
        }
    }

    public static void printSortedList(List<List<Integer>> input) {

        int m = input.size();

        PriorityQueue<HeapNode> minHeap = new PriorityQueue<>(m, new Comparator<HeapNode>() {

            @Override
            public int compare(HeapNode n1, HeapNode n2) {
                return n1.value - n2.value;
            }
        });

        //Create initial heap of size m with first element from all m list
        for (int i = 0; i < m; i++) {

            HeapNode node = new HeapNode(input.get(i).get(0), i, 0);
            minHeap.offer(node);
        }

        while(!minHeap.isEmpty()) {
            HeapNode removedNode = minHeap.poll();

            System.out.print(removedNode.value + " ");

            if (removedNode.elementIndex + 1 < input.get(removedNode.listIndex).size()) {

                minHeap.offer(new HeapNode(input.get(removedNode.listIndex).get(removedNode.elementIndex + 1), removedNode.listIndex,
                        removedNode.elementIndex + 1));
            }
        }
    }

    public static void main(String[] args) {

        List<List<Integer>> list = new ArrayList<>();

        List<Integer> innerList = new ArrayList<>();
         innerList.add(2);
         innerList.add(4);
         innerList.add(5);

         list.add(innerList);

         innerList = new ArrayList<>();
         innerList.add(5);
         innerList.add(7);
         innerList.add(9);

         list.add(innerList);

         innerList = new ArrayList<>();
         innerList.add(3);
         innerList.add(4);

         list.add(innerList);

         printSortedList(list);
    }

}
