import Node.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree

 struct Node {
 int val;
 Node *left;
 Node *right;
 Node *next;
 }
 Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

 Initially, all next pointers are set to NULL.



 Example:



 Input: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":null,"next":null,"right":{"$id":"6","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}

 Output: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":null,"right":null,"val":7},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"6","left":null,"next":null,"right":{"$ref":"5"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"6"},"val":1}

 Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B.


 Note:

 You may only use constant extra space.
 Recursive approach is fine, implicit stack space does not count as extra space for this problem.

 resources/PopulateNextRightPointerBinaryTree.png

 Finish first level then go to second level
 The idea is to use next pointer to traverse at each level, and call getNext(root) to get the left most node at the next level.

 Time Complexity: O(n) - Time complexity of this approach is more may be O(n^2) because of getNext()
 Space Complexity: O(1)

 Approach 2:

 1) Do the BFS traversal using queue.
 2) Add nodes to queue.
 3) Size variable for count of nodes at each level.
 4) Update next pointer at each level

 Time Complexity: O(n)
 Space Complexity: O(n)
 */
public class PopulateNextRightPointerBinaryTree {

    public static BinaryTreeNode connect(BinaryTreeNode root) {
        connectHelper(root);
        return root;
    }

    public static void connectHelper(BinaryTreeNode root) {

        while(root != null) {

            BinaryTreeNode current = root;

            //Finish one level first
            while (current != null) {

                if (current.left != null) {
                    current.left.next = current.right != null ? current.right : getNext(current);
                }

                if (current.right != null) {
                    current.right.next = getNext(current);
                }

                current = current.next;
            }

            //Move to next level
            if (root.left != null) {
                root = root.left;
            } else if (root.right != null) {
                root = root.right;
            } else {
                root = getNext(root);
            }
        }
    }

    /**
     * Get the left most node at the next level
     * @param root
     * @return
     */
    public static BinaryTreeNode getNext(BinaryTreeNode root) {

        while (root.next != null) { // This for any number of nodes in between could be skipped.
            if (root.next.left != null) {
                return root.next.left;
            } else if (root.next.right != null) {
                return root.next.right;
            } else {
                root = root.next;
            }
        }
        return null;
    }

    //BFS Queue approach
    private static BinaryTreeNode populateNextRightPointer(BinaryTreeNode root) {

        //Base Case
        if (root == null) {
            return null;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {

            BinaryTreeNode prev = null; //Start of each level
            int size = queue.size(); //no of elements at each level

            for (int i = 0; i < size; i++) {

                BinaryTreeNode current = queue.remove();

                if (prev != null) {
                    prev.next = current;
                }

                prev = current;

                //Add children
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
        return root;
    }

    public static void displayBinaryTree(BinaryTreeNode root) {

        if (root != null) {
            displayBinaryTree(root.left);
            System.out.print(root.data + "-->");
            displayBinaryTree(root.right);
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);

        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);

        root.right.right = new BinaryTreeNode(7);

        BinaryTreeNode node = connect(root);
        displayBinaryTree(node);
        System.out.println();

        BinaryTreeNode root1 = new BinaryTreeNode(1);
        root1.left = new BinaryTreeNode(2);
        root1.right = new BinaryTreeNode(3);
        root1.left.left = new BinaryTreeNode(4);

        root1.left.right = new BinaryTreeNode(5);
        root1.left.left.left = new BinaryTreeNode(7);

        root1.right.right = new BinaryTreeNode(6);
        root1.right.right.right = new BinaryTreeNode(8);

        BinaryTreeNode node1 = connect(root1);
        displayBinaryTree(node1);
        System.out.println();

        BinaryTreeNode node2 = new BinaryTreeNode(2);
        node2.left = new BinaryTreeNode(1);
        node2.right = new BinaryTreeNode(3);

        node2.left.left = new BinaryTreeNode(0);
        node2.left.right = new BinaryTreeNode(7);

        node2.left.left.left = new BinaryTreeNode(2);
        node2.left.right.left = new BinaryTreeNode(1);

        node2.left.right.right = new BinaryTreeNode(0);
        node2.left.right.right.left = new BinaryTreeNode(7);

        node2.right.left = new BinaryTreeNode(9);
        node2.right.right = new BinaryTreeNode(1);

        node2.right.right.left = new BinaryTreeNode(8);
        node2.right.right.right = new BinaryTreeNode(8);

        BinaryTreeNode node3 = connect(node2);
        displayBinaryTree(node3);
    }
}
