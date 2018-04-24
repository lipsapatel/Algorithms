import java.util.Arrays;

/**
 * A binary heap is the heap data structure created using binary tree.
 *
 * Binary Heap rules:
 *
 * 1) Binary heap has to be complete binary tree at all levels except last level.
 * This is called Shape Property.
 *
 * 2) Heap Property:
 * Max Heap: Nodes are greater than equal to each of it's child nodes
 * Min Heap: Nodes are less than equal to each of it's child nodes
 *
 * Implementation Details:
 *
 * 1) Use array to store data
 * 2) Start from index 1 and not 0
 * 3) For any given node at position i
 * 4) Left child is at position 2i
 * 5) Right child is at position 2i + 1
 * 6) Parent is at position i/2
 *
 * images/Max-Heap.png
 * images/Min-Heap.png
 *
 * Heap has three operations:
 * 1) Insert
 * 2) Delete
 * 3) Extract Min and Extract Max
 *
 * Insert:
 *
 * Add element at the bottom leaf of heap
 * Perform Bubble up operation
 * Bubble up operation is called heapify up, cascade up, percolate up, sift up,
 * up heap, trickle up
 *
 * Bubble up operation:
 *
 * If inserted element is smaller than it's parent node, then swap it with the parent
 * - Min Heap
 * If inserted element is greater than it's parent node, then swap it with the parent
 * - Max Heap
 *
 * Keep repeating the above steps. If the node reaches it's correct position then STOP.
 *
 * images/MinHeapBubbleUp.png
 * images/MaxHeapBubbleUp.png
 *
 * Extract Min
 *
 * Take out the element from root. It will be minimum in case of Min Heap.
 * Take the last element from the last level and replace it with the root.
 * Perform sink down
 * Sink down operation is also called heapify down, sift down, percolate down, cascade down,
 * trickle down, bubble down
 *
 * Sink down operation:
 *
 * If the replace element is greater than any of it's child element, swap it with it's minimum
 * child - Min Heap
 *
 * If the replaced element is smaller than any of it's child element, swap it with it's maximum
 * child - Max Heap
 *
 * Keep repeating the above steps, if the node reaches it's correct position stop.
 *
 * resources/ExtractMin.png
 * resources/ExtractMax.png
 *
 * Delete:
 *
 * Find the index of element to be deleted.
 * Take out the element from the last level from heap and replace the index with this element.
 * Perform Sink down
 *
 * Space Complexity : O(n)
 * Search: O(n)
 * Insert: O(logn)
 * Delete: O(logn)
 *
 */
public class BinaryMinHeapImplementation {

    private static int[] minHeap;
    private static int size;
    private static int position;

    private static void createHeap(int[] array) {
        size = array.length;
        position = 0;
        minHeap = new int[size + 1];

        if (array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                insertMinHeap(array[i]);
            }
        }
    }

    private static void insertMinHeap(int element) {

        //If it's the first element to be inserted
        if (position == 0) {
            minHeap[position + 1] = element; //since it starts at index 1
            position = 2;
        } else { //Not the first element

            //insert it at the end and then perform bubble-up operation
            minHeap[position++] = element;
            bubbleUp();
        }
    }

    private static void bubbleUp() {

        int elementPosition = position - 1;

        //If it's less than parent than swap it
        while (elementPosition/2 > 0 && minHeap[elementPosition] < minHeap[elementPosition/2]) {
            swap(elementPosition, elementPosition/2);

            //Update the element position after swap with parent
            elementPosition = elementPosition/2;
        }
    }

    private static void swap(int a, int b) {
        int temp = minHeap[a];
        minHeap[a] = minHeap[b];
        minHeap[b] = temp;
    }

    private static int extractMin() {

        //Min will be the first element in case of min heap
        int min = minHeap[1];

        //Replace that empty space with the last element
        minHeap[1] = minHeap[position - 1];
        minHeap[position - 1] = 0;
        position--;

        //Perform sink down operation
        sinkDown(1);
        return min;
    }

    private static void sinkDown(int elementPosition) {

        //Smallest element position
        int smallestElementPosition = elementPosition;

        //Check if left child is smaller than the smallest element
        //If yes then update smallest element with left child
        if (2 * elementPosition < position &&
                minHeap[2 * elementPosition] < minHeap[smallestElementPosition]) {

            smallestElementPosition = 2 * elementPosition;
        }

        //Check if right child is smaller than the smallest element
        //If yes then update smallest element with the right child
        if (2* elementPosition + 1 < position &&
                minHeap[2 * elementPosition + 1] < minHeap[smallestElementPosition]) {

            smallestElementPosition = 2 * elementPosition + 1;
        }

        //Perform swap between the elementPosition and smallestElementPosition
        if (smallestElementPosition != elementPosition) {
            swap(elementPosition, smallestElementPosition);
            sinkDown(smallestElementPosition);
        }
    }

    private static void displayMinHeap() {
        //If you don't want to display oth element then loop the array and print it.
        System.out.println(Arrays.toString(minHeap));
    }

    public static void main(String[] args) {

        int[] array = {3, 2, 1, 7, 8, 4, 10, 16, 12};
        System.out.println("Original Array: " + Arrays.toString(array));

        createHeap(array);

        System.out.println("Min Heap: ");
        displayMinHeap();

        System.out.println("Extract Min: ");

        for (int i = 0; i < array.length; i++) { //This will print it in increasing order
            System.out.print(extractMin() + ",");
        }

    }
}
