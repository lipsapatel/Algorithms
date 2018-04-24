import Node.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

/**
 * Print Binary Tree in Vertical Order Path.
 *
 * resources/PrintBinaryTreeInVerticalOrder.png
 *
 * Approach:
 *
 * 1) Do inorder traversal
 * 2) Take variable level
 * 3) level-- on left
 * 4) level++ on right
 * 5) Take TreeMap<Integer, ArrayList> to store elements at each level
 * 6) Iterate the TreeMap and print the result
 *
 * TreeMap is ordered by keys while hashmap has no ordering on keys and values.
 *
 * resources/BinaryTreeVerticalOrderTraverse.png
 *
 */
public class PrintBinaryTreeInVerticalOrderPath {

    private static TreeMap<Integer, ArrayList> treeMap = new TreeMap<Integer, ArrayList>();
    private static int level;
    private static ArrayList arrayList;

    private static BinaryTreeNode binaryTreeVerticalOrder(BinaryTreeNode root, int level) {

        if (root == null) {
            return null;
        }

        BinaryTreeNode node = binaryTreeVerticalOrder(root.left, --level);

        //This is to go one level up to the parent
        if (node == null) {
            level++;
        }

        if (treeMap.get(level) != null) {
            ArrayList list = treeMap.get(level);
            list.add(root.data);
            treeMap.put(level, list);
        } else {
            arrayList = new ArrayList();
            arrayList.add(root.data);
            treeMap.put(level, arrayList);
        }

        return binaryTreeVerticalOrder(root.right, ++level);
    }

    private static void printVerticalOrder(TreeMap treeMap) {
       Set<Integer> keySet = treeMap.keySet();

       for (Integer key: keySet) {
           System.out.println(treeMap.get(key));
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

        binaryTreeVerticalOrder(root, 0);
        printVerticalOrder(treeMap);
    }
}
