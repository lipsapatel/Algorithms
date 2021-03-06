package Node;

/**
 * This is the generic node class with data and next pointer for Singly linked-list
 */
public class SinglyLinkedListNode {
    public SinglyLinkedListNode next;
    public int data;
    public SinglyLinkedListNode child; //This is for flatten a list problem
    public SinglyLinkedListNode randomPointer; //Clone Singly Linked list with random pointer

    public SinglyLinkedListNode(int data) {
        this.data = data;
        this.next = null;
        this.child = null;
        this.randomPointer = null;
    }
}
