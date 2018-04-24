import Node.BinaryTreeNode;
import Node.SinglyLinkedListNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * In a Binary Tree, create linked lists of all nodes at each depth.
 * If tree has height = k, then create k linked lists.
 *
 * Input: Binary Tree
 * Output: k linked lists if the height of binary tree is k.
 * Each linked list will have all the nodes of each level.
 *
 * resources/LinkedListOfAllNodesAtEachLevel.png
 *
 * Approach:
 *
 * 1) Create arraylist which contains the linked list
 * 2) Take queue and add root to the queue.
 * 3) while queue is not empty
 * 4) levelNode = queue size
 * 5) while levelNode > 0
 * 7) Remove from queue
 * 8) Add to linked list
 * 9) Add left and right child to queue.
 * 10) Add linked list to arraylist.
 *
 * Time Complexity: O(n)
 *
 * resources/LinkedListOfAllNodesAtEachLevelTrace.png
 */
public class BinaryTreeCreateLinkedListOfAllNodesAtEachLevel {

    private static ArrayList<SinglyLinkedListNode> linkedListNodeArrayList = new ArrayList<SinglyLinkedListNode>();

    private static void createLinkedListOfAllNodesAtEachLevel(BinaryTreeNode root) {

        if (root == null) {
            return;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        int levelNodes = 0;

        //Add root to the queue
        queue.add(root);

        while(!queue.isEmpty()) {
            levelNodes = queue.size();

            SinglyLinkedListNode head = null;
            SinglyLinkedListNode currentNode = null;

            while (levelNodes > 0) {

                BinaryTreeNode removedNode = queue.remove();

                //Add to linked list
                SinglyLinkedListNode newNode = new SinglyLinkedListNode(removedNode.data);

                if (head == null) {
                    head = newNode;
                    currentNode = newNode;
                } else {
                    currentNode.next = newNode;
                    currentNode = currentNode.next;
                }

                //Go left and right
                if (removedNode.left != null) {
                    queue.add(removedNode.left);
                }

                if (removedNode.right != null) {
                    queue.add(removedNode.right);
                }

                levelNodes--;
            }
            //Add linked list to arraylist
            linkedListNodeArrayList.add(head);
        }

        displayLinkedList(linkedListNodeArrayList);
    }

    private static void displayLinkedList(ArrayList<SinglyLinkedListNode> arrayList) {

        for(SinglyLinkedListNode head: arrayList) {

            while(head != null) {
                System.out.print(head.data + " ");
                head = head.next;
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(10);
        root.right = new BinaryTreeNode(15);
        root.left.left = new BinaryTreeNode(20);
        root.left.right = new BinaryTreeNode(25);
        root.right.left = new BinaryTreeNode(30);
        root.right.right = new BinaryTreeNode(35);

        createLinkedListOfAllNodesAtEachLevel(root);
    }

}
