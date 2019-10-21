import Node.IntervalNode;

/**
 * Consider a situation where we have a set of intervals and we need following operations to be implemented efficiently.
 1) Add an interval
 2) Remove an interval
 3) Given an interval x, find if x overlaps with any of the existing intervals.

 insert, find, delete, insert one interval, delete one interval, find one interval = O(logn)
 find all intervals = Rlogn where R is the number of interval

 Using Red-black BST = R + logn

 To search for any one interval that intersects with interval(l,h)
 1) If interval in node intersects with query interval, return it
 2) else if left subtree is null then go right
 3) else if max endpoint in left subtree is less than low then go right
 4) else go left

 resources/IntervalTree.png

 Interval node stores

 1) left, right, low, high, max
 */
public class IntervalTree {

    private static IntervalNode root = null;

    private static IntervalNode insert(IntervalNode root, int low, int high) {

        //Base Case
        if (root == null) {
            return new IntervalNode(low, high);
        }

        //Recursive Case
        if (low < root.low) { //left
            root.left = insert(root.left, low, high);
        } else { //right
            root.right = insert(root.right, low, high);
        }

        //Update max
        if (root.max < high) {
            root.max = high;
        }

        return root;
    }

    private static IntervalNode searchOverlappingInterval(IntervalNode root, int low, int high) {

        //Base Case
        if (root == null) {
            return null;
        }

        //return if it's overlapping
        if (low <= root.high && high >= root.low) {
            return root;
        }

        //If left subtree is null, go right
        //or if max endpoint in left subtree is less than low, then go to right
        if (root.left == null || root.left.max < low) {
            return searchOverlappingInterval(root.right, low, high);
        } else { //go left
            return searchOverlappingInterval(root.left, low, high);
        }
    }

    private static IntervalNode delete(IntervalNode root, int val) {

        //Base Case
        if (root == null) {
            return root;
        }

        if (val < root.low) {
            root.left = delete(root.left, val);
        } else if (val > root.low) {
            root.right = delete(root.right, val);
        } else { //Matches

            //One child or no child
            if (root.left == null || root.right == null) {

                IntervalNode kid = null;

                if (root.left != null) {
                    kid = root.left;
                }

                if (root.right != null) {
                    kid = root.right;
                }

                //No child
                if (kid == null) {
                    root = null;
                } else {
                    root = kid;
                }
            } else { //Two child

                //Find inorder successor
                IntervalNode minNode = computeMin(root.right);

                //Swap the low, high and max of the current with minNode
                root.low = minNode.low;
                root.high = minNode.high;
                root.max = minNode.max;

                root.right = delete(root.right, minNode.low);
            }
        }
        root = updateRootMax(root);
        return root;
    }

    private static IntervalNode updateRootMax(IntervalNode root) {

        if (root != null) {

            //Set root to the max val of itself, left and right
            root.max = root.high;
            if (root.left != null && root.left.max > root.max) {
                root.max = root.left.max;
            }

            if (root.right != null && root.right.max > root.max) {
                root.max = root.right.max;
            }
        }
        return root;
    }

    private static IntervalNode computeMin(IntervalNode root) {
        IntervalNode current = root;

        while(current.left != null){
            current = current.left;
        }
        return current;
    }

    private static void inorderTraversal(IntervalNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.println("[" + root.low + ", " + root.high + "] + max: " + root.max);
            inorderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
        int[][] interval = {{15, 20}, {10,30}, {17,19}, {5, 20}, {12, 15}, {30, 40}};

        for (int[] anInterval : interval) {
            root = insert(root, anInterval[0], anInterval[1]);
        }

        inorderTraversal(root);

        root = delete(root, 10);
        System.out.println("After delete 10");
        inorderTraversal(root);

        root = delete(root, 30);
        System.out.println("After delete 30");
        inorderTraversal(root);

        IntervalNode overlappedInterval = searchOverlappingInterval(root, 6, 7);

        System.out.println("The interval overlapping with {6, 7} is: {" + overlappedInterval.low + "," + overlappedInterval.high + "}");
    }

}
