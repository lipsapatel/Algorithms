package IK.Sorting.Class;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Find Median from Data Stream
 *
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle value.

 For example,
 [2,3,4], the median is 3

 [2,3], the median is (2 + 3) / 2 = 2.5

 Design a data structure that supports the following two operations:

 void addNum(int num) - Add a integer number from the data stream to the data structure.
 double findMedian() - Return the median of all elements so far.


 Example:

 addNum(1)
 addNum(2)
 findMedian() -> 1.5
 addNum(3)
 findMedian() -> 2


 Follow up:

 If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?

 Approach 3: Two Heaps
 Intuition

 The above two approaches gave us some valuable insights on how to tackle this problem. Concretely, one can infer two things:

 If we could maintain direct access to median elements at all times, then finding the median would take a constant amount of time.
 If we could find a reasonably fast way of adding numbers to our containers, additional penalties incurred could be lessened.
 But perhaps the most important insight, which is not readily observable, is the fact that we only need a consistent way to access the median
 elements. Keeping the entire input sorted is not a requirement.

 Well, if only there were a data structure which could handle our needs.

 As it turns out there are two data structures for the job:

 Heaps (or Priority Queues [1])
 Self-balancing Binary Search Trees (we'll talk more about them in Approach 4)
 Heaps are a natural ingredient for this dish! Adding elements to them take logarithmic order of time. They also give direct
 access to the maximal/minimal elements in a group.

 If we could maintain two heaps in the following way:

 A max-heap to store the smaller half of the input numbers
 A min-heap to store the larger half of the input numbers
 This gives access to median values in the input: they comprise the top of the heaps!

 Wait, what? How?

 If the following conditions are met:

 Both the heaps are balanced (or nearly balanced)
 The max-heap contains all the smaller numbers while the min-heap contains all the larger numbers
 then we can say that:

 All the numbers in the max-heap are smaller or equal to the top element of the max-heap (let's call it xx)
 All the numbers in the min-heap are larger or equal to the top element of the min-heap (let's call it yy)
 Then xx and/or yy are smaller than (or equal to) almost half of the elements and larger than (or equal to) the other half.
 That is the definition of median elements.

 This leads us to a huge point of pain in this approach: balancing the two heaps!

 Algorithm

 Two priority queues:

 A max-heap lo to store the smaller half of the numbers
 A min-heap hi to store the larger half of the numbers
 The max-heap lo is allowed to store, at worst, one more element more than the min-heap hi. Hence if we have processed kk elements:

 If k = 2*n + 1 \quad (\forall \, n \in \mathbb{Z})k=2∗n+1(∀n∈Z), then lo is allowed to hold n+1n+1 elements, while hi can hold nn elements.
 If k = 2*n \quad (\forall \, n \in \mathbb{Z})k=2∗n(∀n∈Z), then both heaps are balanced and hold nn elements each.
 This gives us the nice property that when the heaps are perfectly balanced, the median can be derived from the tops of both heaps.
 Otherwise, the top of the max-heap lo holds the legitimate median.

 Adding a number num:

 Add num to max-heap lo. Since lo received a new element, we must do a balancing step for hi. So remove the largest element
 from lo and offer it to hi.
 The min-heap hi might end holding more elements than the max-heap lo, after the previous operation. We fix that by removing
 the smallest element from hi and offering it to lo.
 The above step ensures that we do not disturb the nice little size property we just mentioned.

 A little example will clear this up! Say we take input from the stream [41, 35, 62, 5, 97, 108]. The run-though of the algorithm looks like this:

 Adding number 41
 MaxHeap lo: [41]           // MaxHeap stores the largest value at the top (index 0)
 MinHeap hi: []             // MinHeap stores the smallest value at the top (index 0)
 Median is 41
 =======================
 Adding number 35
 MaxHeap lo: [35]
 MinHeap hi: [41]
 Median is 38
 =======================
 Adding number 62
 MaxHeap lo: [41, 35]
 MinHeap hi: [62]
 Median is 41
 =======================
 Adding number 4
 MaxHeap lo: [35, 4]
 MinHeap hi: [41, 62]
 Median is 38
 =======================
 Adding number 97
 MaxHeap lo: [41, 35, 4]
 MinHeap hi: [62, 97]
 Median is 41
 =======================
 Adding number 108
 MaxHeap lo: [41, 35, 4]
 MinHeap hi: [62, 97, 108]
 Median is 51.5

 Complexity Analysis

 Time complexity: O(5 \cdot \log n) + O(1) \approx O(\log n)O(5⋅logn)+O(1)≈O(logn).

 At worst, there are three heap insertions and two heap deletions from the top. Each of these takes about O(\log n)O(logn) time.
 Finding the mean takes constant O(1)O(1) time since the tops of heaps are directly accessible.

 Add number : O(logn)
 Median : O(1)
 Space complexity: O(n) linear space to hold input in containers.

 Approach:
 1) Create max heap of all smaller elements - n/2 + 1
 2) Create min heap of all larger elements - n/2
 3) Add elements to max heap
 4) For balancing move elements to min heap
 5) If size of max heap is less than size of min heap, then move from min heap to max heap
 6) For median, return top of max heap if max heap size is greater than min heap
 7) Else add max heap and min heap both and divide by 2.

 */
public class MedianFromDataStream {

    PriorityQueue<Integer> maxHeap; //Store the smallest n/2 elements
    PriorityQueue<Integer> minHeap; //Stores the largest n/2 elements

    /** initialize your data structure here. */
    public MedianFromDataStream() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder()); //Store the smallest n/2 elements
        minHeap = new PriorityQueue<>(); //Stores the largest n/2 elements
    }

    public void addNum(int num) {

        maxHeap.offer(num);

        //Balancing step
        minHeap.offer(maxHeap.poll());

        //Maintain size - Max heap will be equal in case of even and greater by 1 in case of odd
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {

        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (double)(maxHeap.peek() + minHeap.peek())/2;
        }
    }

    public static void main(String[] args) {
        MedianFromDataStream medianFromDataStream = new MedianFromDataStream();
        medianFromDataStream.addNum(41);
        System.out.println("Median: " + medianFromDataStream.findMedian());

        medianFromDataStream.addNum(35);
        System.out.println("Median: " + medianFromDataStream.findMedian());

        medianFromDataStream.addNum(62);
        System.out.println("Median: " + medianFromDataStream.findMedian());

        medianFromDataStream.addNum(4);
        System.out.println("Median: " + medianFromDataStream.findMedian());

        medianFromDataStream.addNum(97);
        System.out.println("Median: " + medianFromDataStream.findMedian());

        medianFromDataStream.addNum(108);
        System.out.println("Median: " + medianFromDataStream.findMedian());
    }
}
