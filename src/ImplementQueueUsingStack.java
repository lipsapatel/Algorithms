import java.util.Stack;

/**
 * Implement queue using stack.
 *
 * We know Queue is FIFO (First in first out) and
 * Stack is LIFO (Last in first out)
 *
 * Approach:
 *
 * 1) Take two stacks
 * 2) Push in stack1
 * 3) Peek and pop operation move all elements from stack 1 to stack 2 and then peek/pop
 *
 */
public class ImplementQueueUsingStack {

    private static Stack<Integer> stack1 = new Stack<Integer>();
    private static Stack<Integer> stack2 = new Stack<Integer>();

    private static void push(int n) {
        stack1.push(n);
    }

    private static int peek() {
        if (stack2.isEmpty()) {
            moveItemsFromStack1ToStack2();
        }
        return stack2.peek();
    }

    private static int pop() {
        if (stack2.isEmpty()) {
            moveItemsFromStack1ToStack2();
        }
        return stack2.pop();
    }

    private static void moveItemsFromStack1ToStack2() {
        while(!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }

    public static void main(String[] args) {
        push(10);
        push(20);
        push(30);
        System.out.println("POP from Queue " + pop());
    }
}
