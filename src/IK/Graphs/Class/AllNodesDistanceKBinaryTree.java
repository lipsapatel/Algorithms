package IK.Graphs.Class;

import Node.BinaryTreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * All Nodes Distance K in Binary Tree
 * Medium
 *
 * Add to List
 *
 * Share
 * Given the root of a binary tree, the value of a target node target, and an integer k,
 * return an array of the values of all nodes that have a distance k from the target node.
 *
 * You can return the answer in any order.
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
 * Output: [7,4,1]
 * Explanation: The nodes that are a distance 2 from the target node (with value 5)
 * have values 7, 4, and 1.
 *
 * Example 2:
 * Input: root = [1], target = 1, k = 3
 * Output: []
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 500].
 * 0 <= Node.val <= 500
 * All the values Node.val are unique.
 * target is the value of one of the nodes in the tree.
 * 0 <= k <= 1000
 *
 * resources/NodesAtDistanceKBinaryTree.png
 *
 * Approach
 * 1) Construct undirected graph from binary tree doing any recursive traversal
 * 2) Pass parent in recursive traversal
 * 3) Do BFS on graph with start node and k
 * 4) Maintain distance HashSet and if distance == k or > k then return list.
 *
 * TC: O(n) where n = nodes in tree, Max edge for each node = 3
 * SC: O(n)
 */
public class AllNodesDistanceKBinaryTree {

    private static List<Integer> nodesAtDistanceK(BinaryTreeNode root, int target, int k) {

        //Create graph from binary tree
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        createGraph(map, root, null);

        return bfs(map, target, k);
    }

    private static void createGraph(Map<Integer, ArrayList<Integer>> graph, BinaryTreeNode root, BinaryTreeNode parent) {

        if(root == null) {
            return;
        }

        graph.put(root.data, new ArrayList<>());

        if(parent != null) {
            graph.get(root.data).add(parent.data);
            graph.get(parent.data).add(root.data);
        }

        createGraph(graph, root.left, root);
        createGraph(graph, root.right, root);
    }

    private static List<Integer> bfs(Map<Integer, ArrayList<Integer>> graph, int start, int k) {
        List<Integer> list = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, Integer> distance = new HashMap<>();

        queue.add(start);
        visited.add(start);
        distance.put(start, 0);

        while(!queue.isEmpty()) {
            int v = queue.remove();

            if(distance.get(v).equals(k)) {
                list.add(v);
            } else if(distance.get(v) > k) {
                return list;
            }

            for(int w: graph.get(v)) {
                if(!visited.contains(w)) {
                    visited.add(w);
                    queue.add(w);
                    distance.put(w, distance.get(v) + 1);
                }
            }
        }
        return list;
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

        List<Integer> list = nodesAtDistanceK(node, 5, 2);

        System.out.println("Nodes at distance 2 from 5 are " + list.toString());
    }
}
