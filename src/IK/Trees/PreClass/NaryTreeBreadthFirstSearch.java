package IK.Trees.PreClass;

import Node.NaryNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1) Return list of nodes at all level
 * 2) Add root element to queue
 * 3) while queue is not empty
 * 4) Take the size of queue
 * 5) Iterate while size is not zero
 * 6) Remove node
 * 7) Add to list or print
 * 8) If node.children != null
 * 9) Add all its children to queue
 * 10) Decrement size
 * 11) Add list to result
 * 12) Return result
 *
 * TC: O(n)
 * SC: O(n)
 */

public class NaryTreeBreadthFirstSearch {

    private static List<List<Integer>> breadthFirstSearch(NaryNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if(root == null) {
            return result;
        }

        Queue<NaryNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {

            int size = queue.size(); //This is for level printing

            List<Integer> list = new ArrayList<>();

            while(size > 0) {

                NaryNode node = queue.remove();
                list.add(node.data);

                if(node.children != null) {
                    queue.addAll(node.children);
                }
                size--;
            }
            result.add(list);
        }
        return result;
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

        System.out.println(breadthFirstSearch(root).toString());
    }
}
