package IK.Trees.PreClass;

import Node.NaryNode;

import java.util.ArrayList;
import java.util.List;

/**
 * resources/TableOfContent.jpg
 *
 * 1) To print table of contents with indentation, do recursive preorder traversal of nary tree
 * 2) Recursive function (root, level)
 * 3) Base Case: If root == null return
 * 4) Print root.data and append identation " ".repeat(level)
 * 5) If root.children != null
 * 6) Iterate all children and make recursive call
 *
 * TC: O(n)
 * SC: O(n) Worst Case and O(height of Tree)
 */
public class PrintTableOfContentsWithIndentation {

    private static void printTableOfContent(NaryNode root, int level) {

        //Base Case
        if(root == null) {
            return;
        }

        System.out.println(" ".repeat(level) + root.data);

        if(root.children != null) {
            for(NaryNode child: root.children) {
                printTableOfContent(child, level + 1);
            }
        }
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
        printTableOfContent(root, 0);
    }
}
