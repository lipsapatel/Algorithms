import java.util.Stack;

/**
 * Area under histogram
 *
 * You will be given an array arr of height of bars, of size n.
 * You have to answer q queries, where in each query, you will be given left index l
 * and right index r. For each query, return the largest rectangular area possible
 * in a histogram formed using (right-left+1) bars with array of heights as
 * [arr[left], arr[left+1], arr[left+2], ..., arr[right]].
 * Largest rectangle can be made of a number of contiguous bars.
 * For simplicity, you can assume that all bars have the same width and the
 * width is 1 unit.
 *
 * For example, consider the following histogram with 7 bars of heights
 * [6, 2, 5, 4, 5, 1, 6]. The largest possible rectangle possible is 12
 *
 * Example One
 * Input:
 *
 * arr = [6, 2, 5, 4, 5, 1, 6]
 * q = 1
 * For 1st query: l = 0 and r = 6.
 *
 * Output:
 * 12
 *
 * 1st query: A rectangle of area 12 can be formed using 2nd to 4th bar
 * (0-based indexing) and has maximum area possible in histogram out of all
 * possible rectangles that can be formed using contiguous bar with given
 * array of heights [arr[0],…,arr[6]] = [6, 2, 5, 4, 5, 1, 6] as l=0 and r=6.
 *
 * Example Two
 * Input:
 * arr = [2, 4, 6, 5, 8]
 * q = 2
 *
 * For 1st query: l = 0 and r = 4.
 *
 * For 2nd query: l = 3 and r = 3.
 *
 * Output:
 * 16
 * 5
 *
 * 1st query: A rectangle of area 16 can be formed using 1st to 4th bar
 * (0-based indexing) and has maximum area possible in histogram out of all
 * possible rectangles that can be formed using contiguous bar with given array
 * of heights [arr[0], …, arr[4]] = [2, 4, 6, 5, 8] as l=0 and r=4.
 *
 * 2nd query: A rectangle of area 5 can be formed using 3rd to 3rd bar
 * (0-based indexing) and has maximum area possible in histogram out of all
 * possible rectangles that can be formed using contiguous bar with given array of
 * heights [arr[3]] = [5] as l=3 and r=3.

 * Constraints:
 *
 *     1 <= n <= 2*10^5
 *     1 <= q <= 10
 *     1 <= arr[i] <= 10^9, i=(0,1,2,3,...,n-1)
 *     0 <= l <= r < n for each query.
 *
 * Approach
 *
 * Brute Force Approach
 *
 * 1) Check for all possible rectangles that start at i and ends at j
 * 2) Keep the track of smallest height.
 * 3) Width will be j - i + 1
 * 4) Find the smallest height between i and j and then calculate area
 *    area = (hsmall) * (j - i + 1)
 * 5) Keep track of maxArea
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 *
 * Optimal Approach
 *
 * 1) We need to know the left index and right index for bar of height h.
 * 2) With the stack approach, the right index is the index when we encounter smaller bar.
 * 3) Left index is the prev index.
 * 4) We traverse from left to right.
 * 5) A bar is pushed to stack if it's greater than the top of the stack
 * 6) A bar is popped from stack, when bar of smaller height is seen. When bar is popped
 * we calculate the area.
 * area = bar * (right index - left index - 1)
 * 7) How do we get the left index and right index of popped bar?
 * right index of popped bar == i which is current index.
 * Left index = Index of prev item in stack.
 *
 * 1) Create an empty stack
 * 2) i = 1 to r
 *   i) a[i] >= stack[top]
 *      push
 *   ii) a[i] < stack[top]
 *       pop until top of stack is greater
 *       for each popped item calculate area
 *       area = a[pop] * (i - prev stack - 1)
 *  3) If stack is not empty, then keep popping and calculate area
 *
 *  Time Complexity: O(n)
 *  Space Complexity: O(n)
 *
 *  resources/AreaUnderHistogram1.jpg
 *  resources/AreaUnderHistogram2.jpg
 *  resources/AreaUnderHistogram3.jpg
 *  resources/AreaUnderHistogram4.jpg
 *  resources/AreaUnderHistogram5.jpg
 *  resources/AreaUnderHistogram6.jpg
 *  resources/AreaUnderHistogram7.jpg
 *
 */
public class AreaUnderHistogram {

    private static long findMaxArea(long[] a, int l, int r) {
        long maxArea = 0;

        long maxPossibleHeight;
        long areaSoFar;

        for(int i = l; i <= r; i++) {
            maxPossibleHeight = Long.MAX_VALUE;

            for(int j = i; j <= r; j++) {
                maxPossibleHeight = Math.min(maxPossibleHeight, a[j]);
                areaSoFar = maxPossibleHeight * (j -  i + 1);
                maxArea = Math.max(areaSoFar, maxArea);
            }
        }
        return maxArea;
    }

    private static long findMaxPossibleArea(long[] a, int l, int r) {
        //Store the indexes in increasing order of height
        Stack<Integer> stack = new Stack<>();

        long maxArea = 0;
        int i = l;

        while(i <= r) {
            //If this bar is higher then push
            if(stack.isEmpty() || a[i] >= a[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int topIndex = stack.pop();

                long area = a[topIndex] * (stack.isEmpty()? i - l: i - stack.peek() - 1);

                maxArea = Math.max(area, maxArea);
            }
        }

        //Pop remaining and calculate area
        while(!stack.isEmpty()) {
            int topIndex = stack.pop();

            long area = a[topIndex] * (stack.isEmpty()? i - l: i - stack.peek() - 1);

            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }
    public static void main(String[] args) {
        long[] a = {6, 2, 5, 4, 5, 1, 6};

        System.out.println("Max area between 0 to 6 bar is " + findMaxArea(a, 0, 6));

        System.out.println("Max area between 0 to 6 using optimal approach is " +
                findMaxPossibleArea(a, 0, 6));
    }
}
