import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element
 * in constant time.
 *
 *     push(x) -- Push element x onto stack.
 *     pop() -- Removes the element on top of the stack.
 *     top() -- Get the top element.
 *     getMin() -- Retrieve the minimum element in the stack.
 *
 * Example:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 *
 * ***********************************************************************
 * Approach 1: Keep pushing duplicates.(<=)
 *
 * 1) Take 2 stack - stack and minStack
 * 2) Insert first element in both the stack.
 * 3) If the top of minStack is min then do nothing.
 * 4) If x <= top of minStack then insert the element x.
 * 4) When you pop, pop from the minStack only if it's equal.
 *
 * With this approach, if there are duplicates, then you have to insert the duplicates.
 * If all elements are duplicates, it will take O(n) space.
 * This approach works if there are not duplicates.
 *
 * resources/MinStack1.jpg
 *
 * Time Complexity: O(1)
 * Space Complexity: O(n)
 *
 * ************************************************************************
 * Approach 2: Keeping counter
 *
 * 1) Take 2 stacks - stack and minStack
 * 2) Insert the first element in both the stack. Insert in the minStack with count 1
 * 3) If the top of minStack is min then do nothing.
 * 4) If x < top then insert x into minStack with count 1
 * 5) If x == top, then increment the counter.
 * 6) When you pop, if x == top of minStack then decrement the counter.
 * 7) When counter becomes 0, pop from minStack.
 *
 * This approach is more suitable if there are lots of duplicates.
 * Because the duplicates items are not repeated instead it stores the count.
 *
 * If the elements are inserted in decreasing order with no duplicates, it consumes
 * 2n space.
 *
 *  Time Complexity: O(1)
 *  Space Complexity: O(2n)
 *
 *  ********************************************************************************
 *  Implement A Min Stack
 *
 * You have to build a min stack. Min stack should support push, pop methods
 * (as usual stack) as well as one method that returns the minimum element in the entire stack.
 *
 * You are given an integer array named operations of size n, containing values >= -1.
 *
 *     operations[i] = -1 means you have to perform a pop operation. The pop operation does not return the removed/popped element.
 *     operations[i] = 0 means you need to find the minimum element in the entire stack and add it at the end of the array to be returned.
 *     operations[i] >= 1 means you need to push operations[i] on the stack.
 *
 * Example
 * Input: [10, 5, 0, -1, 0, -1, 0]
 * Output: [5, 10, -1]
 *
 * Initially stack = [], ans = [].
 *
 * operations[0] = 10 -> push -> stack = [10], ans = []
 * operations[1] = 5 -> push -> stack = [10, 5], ans = []
 * operations[2] = 0 -> get minimum element -> stack = [10, 5], ans = [5]
 * operations[3] = -1 -> pop -> stack = [10], ans = [5]
 * operations[4] = 0 -> get minimum element ->stack = [10], ans = [5, 10]
 * operations[5] = -1 -> pop -> stack = [], ans = [5, 10]
 * operations[6] = 0 -> get minimum element -> stack = [], ans = [5, 10, -1]
 * (as stack is empty we have to consider -1 as the minimum element.)
 *
 * Constraints:
 *
 *     1 <= n <= 100000
 *     -1 <= operations[i] <= 2 * 10^9, (i=0,1,...,n-1)
 *     If stack is empty, then do nothing for pop operation.
 *     If stack is empty, then consider -1 as the minimum element.
 *
 */
public class MinStack {

    private static Stack<Integer> stack = new Stack<>();
    //private static Stack<Integer> minStack = new Stack<>();
    private static Stack<int[]> minStack = new Stack<>();

    private static void push(int x) {

        stack.push(x);

        //If the min stack is empty or this number is smaller than the
        //top of minStack, push it to minStack with count of 1
        if(minStack.isEmpty() || x < minStack.peek()[0]) {
            minStack.push(new int[]{x, 1});
        } else if (x == minStack.peek()[0]) {
            //If this number is equal to top of minStack, increment the counter
            minStack.peek()[1]++;
        }
    }

    private static int pop() {
        int x = -1;

        if(!stack.isEmpty()) {
            x = stack.pop();

            //If the top of the stack == to the top of minStack
            //then decrement the counter
            if(minStack.peek()[0] == x) {
                minStack.peek()[1]--;
            }

            //If the count in minStack == 0, then pop the item
            if(minStack.peek()[1] == 0) {
                minStack.pop();
            }
        }
        return x;
    }

    private static int peek() {
        return stack.peek();
    }

    private static int getMin() {
        if(minStack.isEmpty()) {
            return -1;
        }
        return minStack.peek()[0];
    }

/*    private static void push(int x) {
        stack.push(x);

        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    private static int pop() {
        int x = -1;
        if (!stack.isEmpty()) {
            x = stack.pop();

            if (x == minStack.peek()) {
                minStack.pop();
            }
        }
        return x;
    }

    private static int getMin() {
        if(minStack.isEmpty()) {
            return -1;
        }
        return minStack.peek();
    }

    private static int peek() {
        return stack.peek();
    }*/

    private static int[] getMin(int[] operations) {
        stack = new Stack<>();
        minStack = new Stack<>();

        ArrayList<Integer> result = new ArrayList<>();

        for(int i = 0; i < operations.length; i++) {
            if(operations[i] == 0) {
                result.add(getMin());
            } else if(operations[i] == -1) {
                pop();
            } else if(operations[i] >= 1) {
                push(operations[i]);
            }
        }

        int[] output = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            output[i] = result.get(i);
        }
        return output;
    }

    public static void main(String[] args) {
        push(-2);
        push(0);
        push(-3);
        System.out.println(getMin()); // Returns -3.
        pop();
        System.out.println(peek()); // Returns 0.
        System.out.println(getMin()); // Returns -2.

        int[] operations = {10, 5, 0, -1, 0, -1, 0};
        int[] result = getMin(operations);
        System.out.println(Arrays.toString(result));
    }
}
