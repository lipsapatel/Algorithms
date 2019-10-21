import Node.BinaryTreeNode;

import java.util.HashMap;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.

 Note:
 You may assume that duplicates do not exist in the tree.

 For example, given

 inorder = [9,3,15,20,7]
 postorder = [9,15,7,20,3]
 Return the following binary tree:

 3
 /  \
 9  20
   /  \
 15   7

 preorder = 3, 9, 20, 15, 7

 Let us see the process of constructing tree from in[] = {4, 8, 2, 5, 1, 6, 3, 7} and post[] = {8, 4, 5, 2, 6, 7, 3, 1}

 1) We first find the last node in post[]. The last node is “1”, we know this value is root as root always appear in the end of postorder traversal.

 2) We search “1” in in[] to find left and right subtrees of root. Everything on left of “1” in in[] is in left subtree and everything on right
 is in right subtree.

 1
 /    \
 [4, 8, 2, 5]   [6, 3, 7]
 3) We recur the above process for following two.
 ….b) Recur for in[] = {6, 3, 7} and post[] = {6, 7, 3}
 …….Make the created tree as right child of root.
 ….a) Recur for in[] = {4, 8, 2, 5} and post[] = {8, 4, 5, 2}.
 …….Make the created tree as left child of root.

 Time Complexity: O(n^2) - Worst case occurs when tree is left skewed Pre = {1, 2, 3, 4} In = {4, 3, 2, 1} - If it's not left skewed = O(nlogn) = n for search work for logn height
 Space Complexity: O(n) - Call Stack in worst case

 Keeping map will reduce TC = O(n)
 and SC = O(n)

 The the basic idea is to take the last element in postorder array as the root, find the position of the root in the inorder array;
 then locate the range for left sub-tree and right sub-tree and do recursion.

 Use can use hashmap which store inorder element and index. so retrieval is in O(1) time instead of O(n)
 But there's space complexity: O(n) but it it also reduces time complexity to O(n)

 Same thing if preorder and inorder is given
 */
public class ConstructBinaryTreeFromPostOrderAndInOrderTraversal {

    public static BinaryTreeNode buildTreePost(int[] in, int[] post) {

        if (in == null || post == null || in.length != post.length) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }

        return buildTreeHelperPost(map, in, 0, in.length - 1, post, 0, post.length - 1);
    }

    public static BinaryTreeNode buildTreeHelperPost(HashMap<Integer, Integer> map, int[] in, int il, int ir, int[] post, int pl, int pr) {

        if (il > ir) {
            return null;
        }

        if (il == ir) {
            return new BinaryTreeNode(in[il]);
        }

        BinaryTreeNode root = new BinaryTreeNode(post[pr]);

        //Find the index in inorder list
        /*int i = il;

        while (in[i] != root.data) {
            i++;
        }*/
        int i = map.get(root.data);
        int leftSize = i - il;

        root.left = buildTreeHelperPost(map, in, il, i - 1, post, pl, pl + leftSize - 1);
        root.right = buildTreeHelperPost(map, in, i + 1, ir, post, pl + leftSize, pr - 1);
        return root;
    }

    public static BinaryTreeNode buildTreePre(int[] in, int[] pre) {

        if (in == null || pre == null || in.length != pre.length) {
            return null;
        }

        return buildTreeHelperPre(in, 0, in.length - 1, pre, 0, pre.length - 1);
    }

    public static BinaryTreeNode buildTreeHelperPre(int[] in, int il, int ir, int[] pre, int pl, int pr) {

        if (il > ir) {
            return null;
        }

        if (il == ir) { //This is when only one node left
            return new BinaryTreeNode(in[il]);
        }

        BinaryTreeNode root = new BinaryTreeNode(pre[pl]);

        //Find the index in inorder list
        int i = il;

        //n for search
        while(root.data != in[i]) {
            i++;
        }

        int leftSize = i - il;

        //logn but n in worst case
        root.left = buildTreeHelperPre(in, il, i - 1, pre, pl + 1, pl + leftSize);
        root.right = buildTreeHelperPre(in, i + 1, ir, pre, pl + leftSize + 1, pr);
        return root;
    }

    public static void main(String[] args) {
        int[] in = new int[]{9, 3, 15, 20, 7};
        int[] post = new int[]{9, 15, 7, 20, 3};

        BinaryTreeNode root = buildTreePost(in, post);
        System.out.println("Preorder");
        displayPreOrder(root);
        System.out.println();

        int[] in1 = new int[]{9, 3, 15, 20, 7};
        int[] pre = new int[]{3, 9, 20, 15, 7};

        BinaryTreeNode root1 = buildTreePre(in1, pre);
        System.out.println("PostOrder");
        displayPostOrder(root1);
    }

    public static void displayPreOrder(BinaryTreeNode root) {

        if(root != null) {
            System.out.print(root.data + "-->");
            displayPreOrder(root.left);
            displayPreOrder(root.right);
        }
    }

    public static void displayPostOrder(BinaryTreeNode root) {

        if(root != null) {
            displayPostOrder(root.left);
            displayPostOrder(root.right);
            System.out.print(root.data + "-->");
        }
    }
}
