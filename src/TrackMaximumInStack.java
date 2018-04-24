import java.util.Stack;

/**
 * Track maximum in stack.
 *
 * In a stack, keep track of maximum in it. It might be the top element in the stack but
 * once it's popped out, the maximum value should be from the rest of the element in the
 * stack.
 *
 * Approach:
 *
 * 1) There will be two Stack - The main stack and track stack
 * 2) Insert the first element in both the stack.
 * 3) From then onwards, insert element in the main stack
 * 4) Peek element from the track stack and compare it, insert the one which is maximum
 * 5) Pop the element from both the stack
 * 6) To know the maximum element in the main stack, peek element in the track stack.
 *
 * resources/TrackMaximumElementInStack.png
 *
 */
public class TrackMaximumInStack {

    //Two stack - main and track
    private static Stack<Integer> mainStack = new Stack<Integer>();
    private static Stack<Integer> trackStack = new Stack<Integer>();

    private static void insertIntoStack(int x) {

        if (mainStack.isEmpty()) {
            mainStack.push(x);
            trackStack.push(x);
        } else {

            mainStack.push(x);

            int trackElement = trackStack.peek();

            trackStack.push(Math.max(trackElement, x));
        }
    }

    private static int removeFromStack() {

        if (!mainStack.isEmpty()) {
            trackStack.pop();

            return mainStack.pop();
        }
        return 0;
    }

    private static int getMaximumInStack() {
        return trackStack.peek();
    }

    public static void main(String[] args) {

        insertIntoStack(4);
        insertIntoStack(2);
        insertIntoStack(14);
        insertIntoStack(1);
        insertIntoStack(18);
        System.out.println("Max Element is " + getMaximumInStack());
        System.out.println("Removing Element " + removeFromStack());
        System.out.println("Max Element is " + getMaximumInStack());

    }
}
