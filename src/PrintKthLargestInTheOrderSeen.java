import Node.DoublyLinkedListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given Infinite streams of integers, when encounter "404", print K largest integers
 * in the order seen or received.
 *
 * Time Complexity: logk
 * Space Compexity: 2k
 *
 * Approach:
 *
 * 1) Take min heap of size k, when encounter "404" print the doubly linked list
 * 2) For every node in min heap, create corresponding doubly linked list
 * 3) For every extract, delete from doubly linked list
 * 4) Doubly linked list will have integers in the order seen or received.
 * 5) Both min heap and doubly linked list will have same number of elements
 */
public class PrintKthLargestInTheOrderSeen {

    PriorityQueue<DoublyLinkedListNode> minHeap;
    int reservedNumber;
    DoublyLinkedListNode head;
    DoublyLinkedListNode tail;
    int k;

    public PrintKthLargestInTheOrderSeen(int K, int n) {
        minHeap = new PriorityQueue<>(K, new Comparator<DoublyLinkedListNode>() {

            @Override
            public int compare(DoublyLinkedListNode d1, DoublyLinkedListNode d2) {
                return d1.data - d2.data;
            }
        });

        reservedNumber = n;
        head = null;
        tail = null;
        k = K;
    }

    public void addNum(int num) {

        if (num != reservedNumber) {
            DoublyLinkedListNode node = new DoublyLinkedListNode(num);

            minHeap.offer(node); //log(k)
            insertAtEnd(node);

            if (minHeap.size() > k) {
                DoublyLinkedListNode removedNode = minHeap.poll();
                deleteNode(removedNode);
            }
        } else {
            //Print k largest
            printDoublyLinkedList();
        }
    }

    public void insertAtEnd(DoublyLinkedListNode node) {

        //first node
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    public void deleteNode(DoublyLinkedListNode node) {

        if (head == node && tail == node) {
            //One node only
            head = tail = null;
        } else if (head == node) { //First node
            head = node.next;
            node.next.prev = null;
        } else if (node == tail) { //Last node
            tail = node.prev;
            tail.next = null;
        } else { //Middle node
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public void printDoublyLinkedList() {
        DoublyLinkedListNode current = head;

        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        PrintKthLargestInTheOrderSeen printKthLargestInTheOrderSeen = new PrintKthLargestInTheOrderSeen(3, 404);
        printKthLargestInTheOrderSeen.addNum(100);
        printKthLargestInTheOrderSeen.addNum(3);
        printKthLargestInTheOrderSeen.addNum(30);
        printKthLargestInTheOrderSeen.addNum(90);
        printKthLargestInTheOrderSeen.addNum(404);
        printKthLargestInTheOrderSeen.addNum(2);
        printKthLargestInTheOrderSeen.addNum(20);
        printKthLargestInTheOrderSeen.addNum(35);
        printKthLargestInTheOrderSeen.addNum(25);
        printKthLargestInTheOrderSeen.addNum(404);
    }
}
