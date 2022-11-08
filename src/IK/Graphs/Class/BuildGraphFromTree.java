package IK.Graphs.Class;

import Node.BinaryTreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a tree, build undirected graph from tree.
 *
 *                    F
 *                 B      G
 *             A      D       I
 *                 C      E  H
 *
 * Approach
 * 1) Do any recursive tree traversal
 * 2) In recursive function pass parent
 * 3) Add root --> parent and parent --> root
 * 4) Check if parent is not null before adding parent
 *
 * TC: O(n) where n = number of nodes in tree
 * SC: O(n) There will be n vertices and at most 3 edge for each vertex.
 *
 */
public class BuildGraphFromTree {

    private static Map<Integer, ArrayList<Integer>> buildGraphFromTree(BinaryTreeNode node) {

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        buildGraph(map, node, null);
        return map;
    }

    private static void buildGraph(Map<Integer, ArrayList<Integer>> map, BinaryTreeNode root, BinaryTreeNode parent) {

        //Preorder Traversal
        if(root == null) {
            return;
        }

        map.put(root.data, new ArrayList<>());

        if(parent != null) {
            map.get(root.data).add(parent.data);
            map.get(parent.data).add(root.data);
        }

        buildGraph(map, root.left, root);
        buildGraph(map, root.right, root);
    }

    public static void main(String[] args) {
        BinaryTreeNode node = new BinaryTreeNode(1);
        node.left = new BinaryTreeNode(2);
        node.right = new BinaryTreeNode(3);

        node.left.left = new BinaryTreeNode(4);
        node.left.right = new BinaryTreeNode(5);

        node.right.right = new BinaryTreeNode(6);
        node.right.right.right = new BinaryTreeNode(7);

        node.left.right.left = new BinaryTreeNode(8);
        node.left.right.right = new BinaryTreeNode(9);

        buildGraphFromTree(node);
    }
}
