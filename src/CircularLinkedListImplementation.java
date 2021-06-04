import Node.SinglyLinkedListNode;

/**
 * Last node will not point to null but head node.
 * Extension of Singly Linked list
 * Node will have data and next pointer
 *
 * addAtStart(int data): O(1)
 * addAtEnd(int data): O(1)
 * deleteFromStart(): O(1)
 * getSize()
 * print()
 * getElementAtIndex(int index): Return element at specified index
 *      If index is greater than size then return -1. O(n) worst case complexity
 */
public class CircularLinkedListImplementation {

    int size = 0;
    SinglyLinkedListNode head = null;
    SinglyLinkedListNode tail = null;

    public SinglyLinkedListNode addAtStart(int data) {

        System.out.println("Adding node " + data + " at the start");
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        if (size == 0) {
            head = newNode;
            tail = newNode;
            tail.next = head;
        } else {
            newNode.next = head;
            head = newNode;
            tail.next = head;
        }

        size++;
        return newNode;
    }

    public void addAtEnd(int data) {

        System.out.println("Adding node " + data + " at the end");
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        if (size == 0) {
            addAtStart(data);
        } else {
            tail.next = newNode;
            newNode.next = head;
            tail = newNode;
        }

        size++;
    }

    public void deleteFromStart() {
        if (size == 0) {
            System.out.println("List is Empty");
        } else {
            System.out.println("Deleting node " + head.data + " from start");
            head = head.next;
            tail.next = head;
            size--;
        }
    }

    public int getElementAtIndex(int index) {
        if (index > size) {
            return -1;
        } else {
            SinglyLinkedListNode currentNode = head;
            while(index - 1 != 0) {
                currentNode = currentNode.next;
                index--;
            }

            return currentNode.data;
        }
    }

    public void print() {
        System.out.println("Circular Linked List:");

        if (size == 0) {
            System.out.println("List is Empty");
        } else {
            SinglyLinkedListNode currentNode = head;
            do {
                System.out.print(currentNode.data + "->");
                currentNode = currentNode.next;
            } while (currentNode != head);

            System.out.println();
        }
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        CircularLinkedListImplementation circularLinkedListImplementation = new CircularLinkedListImplementation();

        circularLinkedListImplementation.addAtStart(3);
        circularLinkedListImplementation.addAtStart(2);
        circularLinkedListImplementation.addAtStart(1);

        circularLinkedListImplementation.print();

        circularLinkedListImplementation.deleteFromStart();
        circularLinkedListImplementation.print();
        circularLinkedListImplementation.addAtEnd(4);
        circularLinkedListImplementation.print();

        System.out.println("The size of circular linked list is " + circularLinkedListImplementation.getSize());
        System.out.println("The element at position 2 is " + circularLinkedListImplementation.getElementAtIndex(2));
    }
}
