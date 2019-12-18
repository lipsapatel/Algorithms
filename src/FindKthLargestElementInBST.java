 import Node.BinaryTreeNode;

/**
 * Find the Kth Largest element in BST
 *
 *                        30
 *                      /    \
 *                    20      50
 *                  /   \    /   \
 *                10    25  45   55
 *
 *  Reverse In-order traversal: 55 50 45 30 25 20 10
 *  K = 5
 *
 *  Output: 25
 *
 *  Approach:
 *
 *  1) Do reverse in-order traversal
 *  2) When k == 0, return or print root.data
 *  3) Time Complexity: O(n) - The code first traverse to the right most node and then goes up k nodes O(h + k) where h = logn
 *  for balanced tree and n for skewed tree
 *  4) Space Complexity: O(n)
 *
 **********************************************************************************************************************
 *  Follow up
 What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
 How would you optimize the kthSmallest routine?

 Insert and delete in a BST were discussed last week,
 the time complexity of these operations is O(H), where H is a height of binary tree, and H = log N for the balanced tree.

 Hence without any optimisation insert/delete + search of kth element has O(2H+k) complexity. How to optimise that?

 That's a design question, basically we're asked to implement a structure which contains a BST inside and
 optimises the following operations :

 Insert

 Delete

 Find kth smallest

 Seems like a database description, isn't it? Let's use here the same logic as for LRU cache design, and
 combine an indexing structure (we could keep BST here) with a double linked list.

 Such a structure would provide:

 O(H) time for the insert and delete.

 O(k) for the search of kth smallest.


 The overall time complexity for insert/delete + search of kth smallest is O(H+k) instead of O(2H+k).

 Complexity Analysis

 Time complexity for insert/delete + search of kth smallest: O(H+k), where H is a tree height. O(logN+k) in the average case,
 O(N+k) in the worst case.

 Space complexity : O(N) to keep the linked list.

 resources/KthSmallestElementInBST.png

 ************************************************************************************************************
 * Time complexity:

 In terms of N we can write it as O(N).
 Using other variables we can write tighter bound for the same solution. In terms of height of the tree and k,
 we can write tighter bound as O(height of tree + k).

 The code first traverses down to the leftmost node which takes O(h) time, then traverses k elements in O(k) time.
 Therefore overall time complexity is O(h + k).

 Note that even if k=1 the algorithm has to go all the way down the tree to find the smallest element,
 visiting all the nodes on the way, and visiting one node takes constant time. So far we have used O(h) time
 where h is the height of the tree (worst case is when the left-most leaf of the tree is the longest one).

 Having gone all the way down to the smallest element, the algorithm then visits exactly k nodes from there
 (still constant time per node); complexity so far is O(h) + O(k).

 Having found and saved the k-th element value, the algorithm still needs to pop out from the recursion depth
 so that it can return the answer in the end. For that it will use constant time per level of recursion,
 per depth of the tree (worst case again is when we have found the k-th element at the leaf of the longest branch of the tree).
 That takes another O(h) time. Therefore the overall time complexity: O(h) + O(k) + O(h) = O(2h + k) = O(h + k).

 Auxiliary space used:

 O(height of the tree) due to recursive calls. (Assuming that you are already given BST you are not creating it.)
 */
public class FindKthLargestElementInBST {

    private static int count = 0;

    public static class Count {
        int c = 0;
    }

    private static int findKthLargestElementInBST(BinaryTreeNode root, int k, Count countClass) {

        if (root != null) {

            int kthLargest = findKthLargestElementInBST(root.right, k, countClass);

            if (kthLargest != -1) {
                return kthLargest;
            }

            count = count + 1;
            countClass.c++;

            if (count == k) {
                System.out.println(root.data);
                return root.data;
            }

            if (countClass.c == k) {
                System.out.println(root.data);
                return root.data;
            }

            return findKthLargestElementInBST(root.left, k, countClass);
        }
        return -1;
    }

    public static void main(String[] args) {

        BinaryTreeNode root = new BinaryTreeNode(30);
        root.left = new BinaryTreeNode(20);
        root.left.left = new BinaryTreeNode(10);

        root.left.right = new BinaryTreeNode(25);
        root.right = new BinaryTreeNode(50);
        root.right.left = new BinaryTreeNode(45);
        root.right.right = new BinaryTreeNode(55);

        System.out.println(findKthLargestElementInBST(root, 5, new Count()));
    }
}
