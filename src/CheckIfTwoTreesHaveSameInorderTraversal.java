import Node.BinaryTreeNode;

import java.util.Stack;

/**
 * Check if the two trees have same inorder traversal.
 *
 * Time Complexity: O(m + n)
 * Space Complexity: O(m + n)
 *
 * If there are million nodes and there's mismatch in first 200 nodes, it will not traverse the whole tree.
 */
public class CheckIfTwoTreesHaveSameInorderTraversal {

    public class InorderTraversal {

        Stack<BinaryTreeNode> stack1;

        public InorderTraversal(BinaryTreeNode root) {

            stack1 = new Stack<>();

            pushLeft(root);
        }

        public boolean hasNext() {

            return !stack1.isEmpty();
        }

        public BinaryTreeNode next() {

            if (stack1.isEmpty()) {
                return null;
            }

            BinaryTreeNode poppedNode = stack1.pop();

            pushLeft(poppedNode.right);
            return poppedNode;
        }

        public void pushLeft(BinaryTreeNode root) {

            while(root != null) {
                stack1.push(root);
                root = root.left;
            }
        }
    }

    private boolean checkIfTwoTreesHaveSameInorderTraversal(BinaryTreeNode root1, BinaryTreeNode root2) {

        InorderTraversal it1 = new InorderTraversal(root1);
        InorderTraversal it2 = new InorderTraversal(root2);

        while (it1.hasNext() && it2.hasNext()) {

            if (it1.next().data != it2.next().data) {
                return false;
            }
        }

        if (!it1.hasNext() && !it2.hasNext()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        BinaryTreeNode root1 = new BinaryTreeNode(5);
        root1.left = new BinaryTreeNode(3);
        root1.left.left = new BinaryTreeNode(2);
        root1.left.right = new BinaryTreeNode(4);
        root1.right = new BinaryTreeNode(6);

        BinaryTreeNode root2 = new BinaryTreeNode(5);
        root2.left = new BinaryTreeNode(4);
        root2.left.left = new BinaryTreeNode(3);
        root2.left.left.left = new BinaryTreeNode(2);
        root2.right = new BinaryTreeNode(6);

        System.out.println("The two trees have same inorder traversal: " + new CheckIfTwoTreesHaveSameInorderTraversal().checkIfTwoTreesHaveSameInorderTraversal(root1, root2));
    }
}
