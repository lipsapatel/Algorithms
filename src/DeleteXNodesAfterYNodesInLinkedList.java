import Node.SinglyLinkedListNode;

/**
 * Delete X Nodes after Y Nodes in Linked List.
 *
 * Given a linked list and x and y.
 * Delete x number of nodes after y nodes from start.
 *
 * Example:
 * Linked List
 * --> 10 --> 20 --> 30 --> 40 --> 50 --> 60 --> 70 --> 80 --> 90 --> 100 --> 110 --> 120
 * Delete 4 nodes after 5 nodes
 *
 * --> 10 --> 20 --> 30 --> 40 --> 50 --> 100 --> 110 --> 120
 *
 * resources/DeleteXNodesAfterYNodesInLinkedList.png
 *
 * Approach:
 *
 * 1) We need two pointers
 * 2) One pointer to the node prior to the nodes to be deleted. - Move pointer starting from
 * head by y.
 * 3) Another pointer to the node after nodes to be deleted. - Move pointer starting from y by x.
 * 4) Then just link these two nodes.
 */
public class DeleteXNodesAfterYNodesInLinkedList {

    private static int size = 0;
    private static SinglyLinkedListNode head = null;

    private static void addNodeAtEnd(int data) {

        SinglyLinkedListNode node = new SinglyLinkedListNode(data);

        if (head == null) {
            head = node;
            head.next = null;
        } else {

            SinglyLinkedListNode currentNode = head;

            while(currentNode.next != null) {

                currentNode = currentNode.next;

            }

            currentNode.next = node;
            node.next = null;
        }

        size++;
    }

    private static void display() {

        SinglyLinkedListNode currentNode = head;

        while (currentNode != null) {

            System.out.print(" --> " + currentNode.data);
            currentNode = currentNode.next;
        }
    }

    private static void deleteXNodesAfterYNodes(int x, int y) {

        if (y >= size) {
            System.out.println("Invalid number");
            return;
        }

        SinglyLinkedListNode currentNode = head;

        while(y > 1) {

            currentNode = currentNode.next;
            y--;
        }

        SinglyLinkedListNode previousNode = currentNode;

        //We need the node after deleting x nodes
        x = x + 1;

        while (x > 0 && currentNode != null) {

            currentNode = currentNode.next;
            x--;
        }

        previousNode.next = currentNode;
    }

    public static void main(String[] args) {

        addNodeAtEnd(10);
        addNodeAtEnd(20);
        addNodeAtEnd(30);
        addNodeAtEnd(40);
        addNodeAtEnd(50);
        addNodeAtEnd(60);
        addNodeAtEnd(70);
        addNodeAtEnd(80);
        addNodeAtEnd(90);
        addNodeAtEnd(100);
        addNodeAtEnd(110);
        addNodeAtEnd(120);

        display();

        System.out.println();

        deleteXNodesAfterYNodes(4, 5);

        display();
        System.out.println();
    }
}
