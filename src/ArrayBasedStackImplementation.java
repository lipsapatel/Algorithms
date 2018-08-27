/**
 * Stack is an abstract data type (ADT)
 * Majorly all the operations are done at only one end of the stack which is top of the stack.
 * The order in which elements in stack are stored is with LIFO (Last-in-First-Out) manner. Means the element
 * inserted last will be removed first.
 *
 * resources/ArrayBasedStackImplementation.png
 *
 * Time Complexity: O(1) for push, pop, peek and isEmpty operation.
 *
 * Simple Implementation.
 *
 * The upper bound n on size of stack may be too small and too big and waste memory.
 * It's not dynamic. Array doesn't grow and shrink depending on needs at runtime.
 */
public class ArrayBasedStackImplementation {

    private static class Stack {

        //Default Capacity of stack
        private static final int CAPACITY = 1024;

        //Maximum capacity of stack
        private int n;

        //The top element of stack
        private int top = -1;

        private int[] stack;

        public Stack() {

            //Initialize stack with the default capacity
            this(CAPACITY);
        }

        public Stack(int capacity) {

            //Initialize stack with the given capacity
            n = capacity;
            stack = new int[n];
        }

        /**
         *
         * @return the current stack size
         */
        public int size() {

            return top + 1;
        }

        public boolean isEmpty() {

            return top<0;
        }


        public boolean push(int x) {

            if (top >= n) {

                System.out.println("Stack Overflow");
                return false;
            } else {

                stack[++top] = x;
                return true;
            }
        }

        public int pop() {

            if (isEmpty()) {

                System.out.println("Stack underflow");
                return 0;
            } else {

                //Decrement top
                int x = stack[top--];
                return x;
            }
        }

        public int peek() {

            if (isEmpty()) {

                System.out.println("Stack is empty");
                return 0;
            } else {
                return stack[top];
            }
        }
    }

    public static void main(String[] args) {

        Stack s = new Stack();
        s.push(10);
        s.push(20);
        s.push(30);
        System.out.println(s.pop() + " popped from stack");
    }
}
