import Node.SinglyLinkedListNode;

/**
 * LinkedList: As name suggest,it is a list that is Linked.
 *
 * Linked List consist of Nodes.
 * Nodes are nothing but objects of a class and each node has data and link to next node.
 * The last node in the list points to NULL. So when you reach there you know the list ends here.
 *
 * Operations:
 *
 * Add at the start: Add node at the beginning of the linked list. It's time complexity is O(1).
 *
 * Add at the end: Add node at the end of the linked list. It's time complexity is O(n) since to
 * add a node at the end you need to go till the end of the array.
 *
 * Delete at the start: Delete a node from the beginning of the linked list. It's time complexity is O(1)
 *
 * Delete at the end: Delete a node from the end of the linked list. It's time complexity is O(n) since
 * to delete a node at the end you need to go till the end of the linked list.
 *
 * Get Size: Returns the size of the linked list.
 *
 * Get Element at Index: Returns an element at the specified index. If the index is greater than the size
 * then return -1. Worst case time complexity is O(n)
 *
 * Add Element at specified index: Add element at specified index. If the index is greater than size
 * then print "INVALID POSITION". Worst case time complexity is O(n).
 *
 * Display: Prints the entire linked list. It's time complexity is O(n).
 */
public class SinglyLinkedListImplementation {

    private static SinglyLinkedListNode head = null;
    private static int size = 0;

    private static void addAtBegin(int data) {

        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        newNode.next = head;
        head = newNode;
        size++;
    }

    private static int deleteAtBegin(){

        int deleteNodeData = head.data;

        head = head.next;
        size--;
        return deleteNodeData;
    }

    private static void deleteAtEnd() {

        SinglyLinkedListNode currentNode = head;

        if (currentNode.next == null) {
            head = null;
            size--;
        } else {
            while(currentNode.next.next != null) {
                currentNode = currentNode.next;
            }

            currentNode.next = null;
            size--;
        }
    }

    private static void addAtEnd(int data) {

        SinglyLinkedListNode currentNode = head;

        if(currentNode == null) {
            addAtBegin(data);
        } else {

            SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }

            currentNode.next = newNode;
            size++;
        }
    }

    private static int getElementAtIndex(int index) {

        if (index > size) {
            return -1;
        } else {

            SinglyLinkedListNode currentNode = head;

            while (index - 1 != 0) {
                currentNode = currentNode.next;
                index--;
            }

            return currentNode.data;
        }
    }

    private static int getSize() {
        return size;
    }

    private static int findIndexOfElement(int data) {

        SinglyLinkedListNode currentNode = head;
        int index = 1;

        while (currentNode != null) {

            if (currentNode.data == data) {
                return index;
            } else {
                currentNode = currentNode.next;
                index++;
            }
        }

        return -1;
    }

    private static void addElementAtIndex(int data, int index) {

        if (index == 1) {
            addAtBegin(data);
        }

        int length = size;

        if (index > length + 1 || index < 1) {
            System.out.println("Invalid position");
        }

        if (index == length + 1) {
            addAtEnd(data);
        }

        if (index > 1 && index <= length) {

            SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

            SinglyLinkedListNode currentNode = head;

             while(index - 2 > 0) {

                 currentNode = currentNode.next;
                 index --;
             }

             newNode.next = currentNode.next;
             currentNode.next = newNode;
             size++;
        }
    }

    private static void displayLinkedList() {
        System.out.println();
        SinglyLinkedListNode currentNode = head;

        while (currentNode != null) {

            System.out.print(currentNode.data + "-->");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        addAtBegin(5);
        addAtBegin(15);
        addAtEnd(20);
        addAtEnd(21);
        displayLinkedList();
        deleteAtBegin();
        deleteAtEnd();
        addElementAtIndex(10, 2);
        addAtEnd(15);
        displayLinkedList();
        System.out.println("\n Size of the list is: " + size);
        System.out.println(" Element at 2nd position : " + getElementAtIndex(2));
        System.out.println(" Searching element 15, location : " + findIndexOfElement(15));
    }
}
