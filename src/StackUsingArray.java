/**
 * What is Stack?
 *
 * 1) Stack is an abstract data type (ADT) and very useful in programming.
 * 2) Abstract data type that serves as a collection of elements
 * 3) Majorly all the operations are done at only one end of the stack which is top of the stack.
 * 4) The order in which elements in stack are stored is with LIFO (Last-in-First-Out) manner.
 *    Means the elements inserted last will be removed first.
 *
 *    resources/StackOperations.png
 *
 *    Java already has a built-in class for Stack.
 *
 *    Ways to implement Stack
 *
 *    1) Implement stack using Array
 *    2) Implement stack using LinkedList
 *
 *    Operations
 *
 *    1) Initialize topIndex = -1
 *    2) push(): Increment topIndex by 1 and add element at this index in array - O(1)
 *    3) pop(): Remove element from the topIndex, decrement topIndex by 1 - O(1)
 *    3) peek(): Return element from topIndex - O(1)
 *    4) isEmpty(): Check if topIndex < 0 then return true else return false - O(1)
 *    5) getSize(): Returns topIndex + 1 - O(1)
 */
public class StackUsingArray {

    private static int size;
    private static int[] stack;
    private static int topIndex = -1;

    public StackUsingArray(int givenSize) {
        size = givenSize;
        stack = new int[size];
    }

    public void push(int number) {

        if (topIndex == size - 1) {

            System.out.println("Stack overflow,...cannot insert new element");
            return;
        }

        System.out.println("Inserting " + number + " into Stack.");
        topIndex = topIndex + 1;
        stack[topIndex] = number;
    }

    public Integer pop() {

        if (topIndex < 0) {
            System.out.println("Stack is empty, nothing to pop");
            return null;
        }

        System.out.println("Popping from Stack:");
        Integer x = stack[topIndex];
        topIndex--;
        return x;
    }

    public Integer peek() {

        if (topIndex < 0) {
            System.out.println("Stack is empty, nothing to pop");
            return null;
        }

        System.out.println("Peeking from stack");
        return stack[topIndex];
    }

    public boolean isEmpty() {

        return topIndex < 0;
    }

    public int getSize() {

        return topIndex + 1;
    }

    public void displayStack() {

        System.out.println("Stack (top --> bottom):");

        for (int i = topIndex; i >= 0; i--) {
            System.out.print(stack[i] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        int size = 4;
        StackUsingArray stackUsingArray = new StackUsingArray(size);

        System.out.println("Is Stack Empty: " + stackUsingArray.isEmpty());
        stackUsingArray.push(1);
        stackUsingArray.push(2);
        stackUsingArray.push(3);
        stackUsingArray.push(4);
        stackUsingArray.push(5);

        stackUsingArray.displayStack();

        System.out.println("Popped Element: " + stackUsingArray.pop());
        System.out.println("Popped Element: " + stackUsingArray.pop());

        stackUsingArray.displayStack();

        stackUsingArray.push(6);

        System.out.println("Peeking Element: " + stackUsingArray.peek());
        stackUsingArray.displayStack();
        System.out.println("Is Stack Empty: " + stackUsingArray.isEmpty());
        System.out.println("Stack Size: " + stackUsingArray.getSize());
    }
}
