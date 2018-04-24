package Node;

/**
 * Doubly Linked-List Node Class
 * It has data, prev pointer and next pointer.
 */
public class DoublyLinkedListNode {
    public DoublyLinkedListNode prev;
    public DoublyLinkedListNode next;
    public int data;

    public DoublyLinkedListNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
