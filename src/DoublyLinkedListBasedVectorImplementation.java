import Node.DoublyLinkedListNode;

/**
 * Implement Vector using DoublyLinkedList
 *
 * Two operations
 * 1) insertAtRank
 * 2) removeAtRank
 *
 * O(n) time to find the place
 * O(1) for insert or remove
 */
public class DoublyLinkedListBasedVectorImplementation {

    private static int size = 0;
    private static DoublyLinkedListNode head = null;
    private static DoublyLinkedListNode tail = null;

    private static void insertAtRank(int rank, int element) {

        //Check for Boundary Conditions
        if (rank < 0 || rank > size) {
            System.out.println("Out of Boundary");
        }

        //New Node
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(element);

        //Next Node
        DoublyLinkedListNode nextNode = getElementAtRank(rank);

        //Previous Node
        DoublyLinkedListNode previousNode = nextNode.prev;

        nextNode.prev = newNode;
        previousNode.next = newNode;
        newNode.next = nextNode;
        newNode.prev = previousNode;
        size++;
    }

    private static DoublyLinkedListNode getElementAtRank(int rank) {

        if (rank > size) {
            return null;
        }

        DoublyLinkedListNode currentNode = head;
        while(rank - 1 != 0) {

            currentNode = currentNode.next;
            rank--;
        }
        return currentNode;
    }

    private static DoublyLinkedListNode removeAtRank(int rank) {

        //Check Boundary conditions
        if (rank < 0 || rank > size) {
            System.out.println("Out of Boundary");
        }

        DoublyLinkedListNode nodeToBeDeleted = getElementAtRank(rank);
        DoublyLinkedListNode nextNode = nodeToBeDeleted.next;
        DoublyLinkedListNode previousNode = nodeToBeDeleted.prev;

        previousNode.next = nextNode;
        nextNode.prev = previousNode;
        size--;

        return nodeToBeDeleted;
    }

    private static void printDoublyLinkedList() {

        DoublyLinkedListNode currentNode = head;

        int index = size;

        while (index - 1 != 0) {
            System.out.println(currentNode.data);
            currentNode = currentNode.next;
            index--;
        }
        System.out.println(currentNode.data);
    }

    public static void main(String[] args) {

        DoublyLinkedListNode node = new DoublyLinkedListNode(304);

        head = node;
        tail = node;

        DoublyLinkedListNode nextNode = new DoublyLinkedListNode(33);

        node.next = nextNode;
        nextNode.prev = node;

        size = size+2;

        insertAtRank(2, 20);

        insertAtRank(2, 100);
        removeAtRank(2);

        printDoublyLinkedList();
    }
}
