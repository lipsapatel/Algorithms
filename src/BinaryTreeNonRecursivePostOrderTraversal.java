import Node.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Post order traversal: (left, right, root)
 * Pre order traversal: (root, left, right)
 *
 * In pre order traversal if we traverse the right node first and reverse it we get the post
 * order traversal : (root, right, left)
 *
 * Follow preorder traversal and push it to another stack so it will come out in reverse order
 * LIFO
 *
 * stack1.push(root)
 *
 * while(stack1 is not empty)
 *
 *  Pop the node from stack 1 and push it to stack 2
 *
 *  push left and right of popped node in stack1
 *
 * End Loop
 *
 * Pop all the nodes from stack 2 and print it.
 *
 * images/BinaryTreeNonRecursivePostOrderTraversal.PNG
 *
 * Recursive
 * TC = O(n)
 * SC = O(n)
 *
 * Non Recursive
 * TC = O(n)
 * SC = O(n) - Stack2 will contain n nodes
 */
public class BinaryTreeNonRecursivePostOrderTraversal {

    //Recursive approach
    private void recursivePostOrderTraversal(BinaryTreeNode root) {
        if (root != null) {
            recursivePostOrderTraversal(root.left);
            recursivePostOrderTraversal(root.right);
            System.out.print(root.data + "->");
        }
    }

    private void nonRecursivePostOrderTraversal(BinaryTreeNode root) {
        if (root != null) {

            Stack<BinaryTreeNode> stack1 = new Stack<BinaryTreeNode>();
            Stack<BinaryTreeNode> stack2 = new Stack<BinaryTreeNode>();
            List<BinaryTreeNode> list = new ArrayList<>();

            //push root node first into the stack
            stack1.push(root);

            while(!stack1.isEmpty()) {

                //Preorder traversal
                BinaryTreeNode poppedNode = stack1.pop();

                stack2.push(poppedNode);
                list.add(0, poppedNode);

                if (poppedNode.left != null) {
                    stack1.push(poppedNode.left);
                }

                if (poppedNode.right != null) {
                    stack1.push(poppedNode.right);
                }
            }

            //print stack2
            System.out.println("Post order traversal using non recursive approach");

            for (BinaryTreeNode node: list) {
                System.out.print(node.data + " ");
            }

            System.out.println();

            while(!stack2.isEmpty()) {
                System.out.print(stack2.pop().data + "->");
            }
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);

        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);

        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);

        root.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(7);

        BinaryTreeNonRecursivePostOrderTraversal binaryTreeNonRecursivePostOrderTraversal = new BinaryTreeNonRecursivePostOrderTraversal();

        System.out.println("Recursive Post Order Traversal");
        binaryTreeNonRecursivePostOrderTraversal.recursivePostOrderTraversal(root);
        System.out.println();

        binaryTreeNonRecursivePostOrderTraversal.nonRecursivePostOrderTraversal(root);
    }
}
