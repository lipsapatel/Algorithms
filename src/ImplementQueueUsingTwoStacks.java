import Node.SinglyLinkedListNode;

import java.util.Stack;

/**
 * Implement Queue Using Two Stacks
 * Given a sequence of enqueue and dequeue operations, return a result of their
 * execution without using a queue data structure.
 *
 * Operations are given in the form of a linked list:
 *
 *     A non-negative integer means “enqueue me”.
 *     -1 means
 *     If the queue is not empty, dequeue the current head and append it to the result.
 *     If the queue is empty, append -1 to the result.
 *
 * Result is a linked list.
 * Use two stacks as an auxiliary data structure. Using a queue isn’t allowed.
 *
 * Example One
 * Input: 1, -1, 2, -1, -1, 3, -1
 * Output: 1, 2, -1, 3
 *
 * Example Two
 * Input: 0, 1, 2, -1, 3
 * Output: 0
 *
 * The only dequeue operation results in the first enqueued element,
 * 0, to be appended to the result list.
 *
 * Constraints:
 *
 *     -1 <= value in operations list <= 2 * 10^9
 *     1 <= N <= 10^5
 *     There will be at least one dequeue (-1) operation.
 *****************************************************************
 *
 * Approach:
 *
 * 1) Use two stacks - stack1 and stack2
 * 2) All enqueue operations push an element to stack1 in O(1) time.
 * 3) For deque operation, move all elements from stack1 to stack2.
 * 4) Then pop from stack2 for each deque operation until stack2 is empty.
 * 5) After that repeat step3 if stack2 is empty.
 *
 * Time Complexity: O(n) - Atmost n elements are moved from stack1 to stack2 and each
 * operation takes O(1) so n operations takes O(n)
 * Space Complexity: O(n) - Atmost we are storing n elements in stack.
 *
 * resources/ImplementQueueUsingTwoStacks1.jpg
 * resources/ImplementQueueUsingTwoStacks2.jpg
 */
public class ImplementQueueUsingTwoStacks {

    private static Stack<Integer> stack1;
    private static Stack<Integer> stack2;

    private static SinglyLinkedListNode implementQueue(SinglyLinkedListNode operations) {
        stack1 = new Stack<>();
        stack2 = new Stack<>();

        SinglyLinkedListNode head = null;
        SinglyLinkedListNode tail = null;

        while(operations != null) {
            int data = operations.data;

            if(data >= 0) {
                enqueue(data);
            } else {
                tail = deque(tail);

                if(head == null) {
                    head = tail;
                }
            }
            operations = operations.next;
        }
        return head;
    }

    //O(1)
    private static void enqueue(int data) {
        stack1.push(data);
    }

    //O(n)
    private static SinglyLinkedListNode deque(SinglyLinkedListNode tail) {

        int dequeElement = -1;

        if(stack2.isEmpty() && stack1.isEmpty()) {
            dequeElement = -1;
        } else if(!stack2.isEmpty()) {
            dequeElement = stack2.pop();
        } else if(stack2.isEmpty()) { //transfer
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            dequeElement = stack2.pop();
        }

        SinglyLinkedListNode node = new SinglyLinkedListNode(dequeElement);
        if(tail == null) {
            tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
        return tail;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode operations = new SinglyLinkedListNode(1);
        operations.next = new SinglyLinkedListNode(-1);
        operations.next.next = new SinglyLinkedListNode(2);
        operations.next.next.next = new SinglyLinkedListNode(-1);
        operations.next.next.next.next = new SinglyLinkedListNode(-1);
        operations.next.next.next.next.next = new SinglyLinkedListNode(3);
        operations.next.next.next.next.next.next = new SinglyLinkedListNode(-1);

        SinglyLinkedListNode output = implementQueue(operations);
        while(output != null) {
            System.out.print(output.data + " ");
            output = output.next;
        }
    }
}
