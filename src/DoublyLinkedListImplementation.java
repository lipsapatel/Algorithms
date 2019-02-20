import Node.DoublyLinkedListNode;

/**
 * Doubly Linked List: You can traverse in both forward and backward direction
 * It has next and prev pointer
 * It has head and tail node.
 * Requires more memory than singly linked list
 * Not easy as compared to singly linked list
 *
 * addAtStart(int data): O(1)
 * addAtEnd(int data): O(1)
 * addAfterNode(int data, DoublyLinkedListNode prevNode): O(1)
 * deleteFromStart(): O(1)
 * deleteFromEnd(): O(1)
 * getSize()
 * getElementAtIndex(int index) - Return element at specified index.
 *      If index is greater than size then return -1. O(n) worst case complexity
 * print(): O(n)
 *
 */
public class DoublyLinkedListImplementation {

    int size = 0;
    DoublyLinkedListNode head = null;
    DoublyLinkedListNode tail = null;

    public DoublyLinkedListNode addAtStart(int data) {

        System.out.println("Adding node " + data + " at the start");

        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);

        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
        return newNode;
    }

    public DoublyLinkedListNode addAtEnd(int data) {

        System.out.println("Adding node " + data + " at the end");

        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);

        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return newNode;
    }

    public DoublyLinkedListNode addAfterNode(int data, DoublyLinkedListNode prevNode) {

        if (prevNode == null) {

            System.out.println("Node after which new node needs to be added cannot be null");
            return null;
        } else if (prevNode == tail) {

            return addAtEnd(data);
        } else {

            System.out.println("Adding node after " + prevNode.data);

            DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);

            //Store the next node of the prev node
            DoublyLinkedListNode nextNode = prevNode.next;

            prevNode.next = newNode;
            newNode.prev = prevNode;
            newNode.next = nextNode;
            nextNode.prev = newNode;

            size++;

            return newNode;
        }
    }

    public void deleteFromStart() {

        if (size == 0) {
            System.out.println("List is Empty");
        } else {
            System.out.println("Deleting node " + head.data + " from start");
            head = head.next;
            size--;
        }
    }

    public void deleteFromEnd() {

        if (size == 0) {
            System.out.println("List is Empty");
        } else if (size == 1) {
            deleteFromStart();
        } else {

            //last node data
            int lastNodeData = tail.data;

            //Store the 2nd last node
            DoublyLinkedListNode prevNode = tail.prev;

            prevNode.next = null;
            tail = prevNode;

            System.out.println("Deleting node " + lastNodeData + " from end");
            size--;
        }
    }

    public int getElementAtIndex(int index) {

        if (index > size) {
            return -1;
        }

        DoublyLinkedListNode currentNode = head;
        while(index - 1 != 0) {
            currentNode = currentNode.next;
            index--;
        }

        return currentNode.data;
    }

    public int getSize() {
        return size;
    }

    public void print() { //O(n)
        DoublyLinkedListNode currentNode = head;

        System.out.println("Doubly Linked List: ");

        while(currentNode != null) {
            System.out.print("->" + currentNode.data);
            currentNode = currentNode.next;
        }

        System.out.println();
    }

    private void printReverse() { //O(n)

        DoublyLinkedListNode currentNode = tail;

        System.out.println("Doubly linked list in reverse: ");

        while (currentNode != null) {
            System.out.print(currentNode.data + "-->");
            currentNode = currentNode.prev;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoublyLinkedListImplementation doublyLinkedListImplementation = new DoublyLinkedListImplementation();

        DoublyLinkedListNode node = doublyLinkedListImplementation.addAtStart(2);
        doublyLinkedListImplementation.addAtStart(1);

        doublyLinkedListImplementation.print();

        doublyLinkedListImplementation.addAtEnd(3);

        doublyLinkedListImplementation.print();

        doublyLinkedListImplementation.addAfterNode(4, node);

        doublyLinkedListImplementation.print();

        doublyLinkedListImplementation.deleteFromStart();

        doublyLinkedListImplementation.print();

        System.out.println("Element at index 2 :" + doublyLinkedListImplementation.getElementAtIndex(2));

        doublyLinkedListImplementation.addAtStart(1);

        doublyLinkedListImplementation.print();

        doublyLinkedListImplementation.deleteFromEnd();

        doublyLinkedListImplementation.print();
        doublyLinkedListImplementation.printReverse();

        System.out.println("Size of the Linked List " + doublyLinkedListImplementation.getSize());
    }
}
