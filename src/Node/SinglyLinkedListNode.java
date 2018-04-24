package Node;

/**
 * This is the generic node class with data and next pointer for Singly linked-list
 */
public class SinglyLinkedListNode {
    public SinglyLinkedListNode next;
    public int data;

    public SinglyLinkedListNode(int data) {
        this.data = data;
        this.next = null;
    }
}
