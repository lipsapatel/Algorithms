import Node.SinglyLinkedListNode;

import java.util.Stack;

/**
 * Reverse LinkedList using stack
 * TC = O(n)
 * SC = O(n) for stack
 */
public class ReverseLinkedListUsingStack {

    private static SinglyLinkedListNode head = null;

    private static void addAtBegin(int data) {
        SinglyLinkedListNode node = new SinglyLinkedListNode(data);
        node.next = head;
        head = node;
    }

    private static void displayLinkedList() {
        SinglyLinkedListNode currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.data + "-->");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    private static void reverseLinkedListUsingStack() {
        Stack<SinglyLinkedListNode> stack = new Stack<>();

        if (head == null) {
            return;
        }

        SinglyLinkedListNode currentNode = head;

        //Push
        while (currentNode != null) {
            stack.push(currentNode);
            currentNode = currentNode.next;
        }

        //Pop
        currentNode = stack.pop();
        head = currentNode; //head
        while (!stack.isEmpty()) {
            currentNode.next = stack.pop();
            currentNode = currentNode.next;
        }

        currentNode.next = null; //last node to null
    }

    public static void main(String[] args) {
        addAtBegin(2);
        addAtBegin(5);
        addAtBegin(3);
        addAtBegin(8);
        displayLinkedList();

        reverseLinkedListUsingStack();
        displayLinkedList();
    }
}
