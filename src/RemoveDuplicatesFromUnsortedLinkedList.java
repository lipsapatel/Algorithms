import Node.SinglyLinkedListNode;

import java.util.HashMap;

/**
 * Remove Duplicates from an unsorted Linked List.
 *
 * Input Linked List: 1 --> 2 --> 2 --> 4 --> 3 --> 3 --> 2
 * Output: 1 --> 2 --> 4 --> 3
 *
 * Input: An unsorted linked list.
 * Output: Linked list with no duplicates
 *
 * Approach:
 *
 * 1) Create HashMap
 * 2) Take two pointers, previousNode and currentNode
 * 3) previousNode = head and currentNode = head.next
 * 4) Now traverse through the linked list
 * 5) Check if the data is present in hashmap
 * 6) If it's present then delete that node using previousNode and currentNode
 * 7) If the data is not present then insert that node data into the hashmap
 * 8) Return the head of the linked list
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * If suppose addition buffer is not allowed then we have option to check every node against every
 * other node data and if find duplicates, delete that node.
 *
 * Time Complexity: O(n2)
 */
public class RemoveDuplicatesFromUnsortedLinkedList {

    private static SinglyLinkedListNode head = null;

    private static SinglyLinkedListNode removeDuplicatesFromUnsortedLinkedList(SinglyLinkedListNode head) {

        HashMap<Integer, Integer> integerHashMap = new HashMap<Integer, Integer>();

        if (head == null) {
            return null;
        }

        SinglyLinkedListNode currentNode = head.next;
        SinglyLinkedListNode previousNode = head;

        integerHashMap.put(previousNode.data, 1);

        //This is because we need to set next for current node to null if it's deleted
        //So that it's eligible for garbage collection
        SinglyLinkedListNode temp;

        while(currentNode != null) {

            int data = currentNode.data;

            if(integerHashMap.containsKey(data)) { //then delete the current node

                previousNode.next = currentNode.next;
                temp = currentNode;
                currentNode = currentNode.next;
                temp.next = null;
            } else {

                integerHashMap.put(data, 1);
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
        }

        return head;
    }

    private static void addAtBegin(int data) {

        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        newNode.next = head;
        head = newNode;
    }

    private static void displayLinkedList() {

        SinglyLinkedListNode currentNode = head;

        while(currentNode != null) {
            System.out.print(currentNode.data + "-->");
            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args) {

        addAtBegin(2);
        addAtBegin(3);
        addAtBegin(3);
        addAtBegin(4);
        addAtBegin(2);
        addAtBegin(2);
        addAtBegin(1);

        System.out.println("The original Linked List: ");
        displayLinkedList();
        System.out.println();

        removeDuplicatesFromUnsortedLinkedList(head);

        System.out.println("The Linked List with no duplicates: ");
        displayLinkedList();
        System.out.println();
    }
}
