import Node.SinglyLinkedListNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement stack using Linked list
 *
 * Stack - LIFO Last in First out
 * Operations: Push, Pop, getSize and Display
 *
 * push(): Pushes the element at the head of the linked-list and increment the size. O(1)
 * pop(): Remove the head element and decrement the size. O(1)
 * getSize(): Returns size
 * display(): Print the linked list.
 */
public class StackUsingLinkedList {

    SinglyLinkedListNode head = null;
    int size = 0;

    public void push(int data) {

        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        if (size == 0) {
            head = newNode;
        } else {
            SinglyLinkedListNode temp = head;
            head = newNode;
            newNode.next = temp;
        }

        size++;
        System.out.println("The new element " + data + " pushed to the stack");
    }

    public int pop() {

        if (size == 0) {
            System.out.println("The stack is empty");
            return -1;
        } else {
            SinglyLinkedListNode temp = head;
            head = temp.next;
            size --;

            return temp.data;
        }
    }

    public void display() {
        SinglyLinkedListNode curr = head;

        while(curr != null) {
            System.out.print(curr.data + "->");
            curr = curr.next;
        }
        System.out.println();
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        StackUsingLinkedList stackUsingLinkedList = new StackUsingLinkedList();

        stackUsingLinkedList.push(2);
        stackUsingLinkedList.push(3);
        stackUsingLinkedList.push(4);
        stackUsingLinkedList.push(5);

        stackUsingLinkedList.display();

        System.out.println("The element popped " + stackUsingLinkedList.pop());

        stackUsingLinkedList.display();
    }
}
