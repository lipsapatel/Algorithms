package IK.Trees.PreClass;

import Node.NaryNode;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * resources/CalculateSpaceNaryTree.jpg
 *
 * 1) Do recursive post order traversal of nary tree
 * 2) Recursive function (root)
 * 3) Base Case: If root == null, return 0
 * 4) space = root.data
 * 5) if root.children is not null, iterate children and make recursive call to get space used
 * 6) Add it your space and return
 *
 * This is preorder initialization and post order return.
 * The final answer cannot be return until all subtree has returned an answer, so it’s a post order but not purely post order.
 *
 *
 * TC: O(n)
 * SC: O(n) Worst case or O(height of tree)
 */
public class CalculateSpaceNaryTree {

    public static void main(String[] args) {
        List<NaryNode> list = new ArrayList<>();
        list.add(new NaryNode(1));
        list.add(new NaryNode(0));
        list.add(new NaryNode(1));
        list.add(new NaryNode(0));
        list.add(new NaryNode(0));

        NaryNode naryNode1 = new NaryNode(1, list);

        list = new ArrayList<>();
        list.add(naryNode1);
        list.add(new NaryNode(0));
        list.add(new NaryNode(0));
        list.add(new NaryNode(0));
        list.add(new NaryNode(0));

        NaryNode root = new NaryNode(0, list);

        System.out.println("The space used by root is " + calculateSpace(root));
    }

    private static int calculateSpace(NaryNode root) {
        //Base Case
        if(root == null) {
            return 0;
        }

        int space = root.data;

        if(root.children != null) {
            for(NaryNode child: root.children) {
                space += calculateSpace(child);
            }
        }
        return space;
    }
}
