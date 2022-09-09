package IK.Trees.PreClass;

import Node.NaryNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Nary tree has only two traversal preorder and postorder
 * 1) Base Case: If root == null, return
 * 2) Print root.data
 * 3) Check if root.children is not null. Iterate root.children and make recursive call with child
 * 4) For postorder, reverse printing order
 *
 * TC: O(n)
 * SC: O(n) - In worst case or O(height of tree)
 */
public class NaryTreeRecursiveDepthFirstSearch {

    private static void naryPreorder(NaryNode root) {

        //Base Case
        if(root == null) {
            return;
        }

        System.out.println(root.data);

        if(root.children != null) {
            for (NaryNode child : root.children) {
                naryPreorder(child);
            }
        }
    }

    private static void naryPostorder(NaryNode root) {

        //Base Case
        if(root == null) {
            return;
        }

        if(root.children != null) {
            for (NaryNode child : root.children) {
                naryPostorder(child);
            }
        }
        System.out.println(root.data);
    }

    public static void main(String[] args) {

        List<NaryNode> list = new ArrayList<>();
        list.add(new NaryNode(7));
        list.add(new NaryNode(8));
        list.add(new NaryNode(9));
        list.add(new NaryNode(10));
        list.add(new NaryNode(11));

        NaryNode naryNode1 = new NaryNode(2, list);

        list = new ArrayList<>();
        list.add(naryNode1);
        list.add(new NaryNode(3));
        list.add(new NaryNode(4));
        list.add(new NaryNode(5));
        list.add(new NaryNode(6));

        NaryNode root = new NaryNode(1, list);

        System.out.println("DFS Preorder ");
        naryPreorder(root);

        System.out.println("DFS Postorder ");
        naryPostorder(root);
    }
}
