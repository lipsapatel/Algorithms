/**
 * Design a Skiplist without using any built-in libraries.
 *
 * A Skiplist is a data structure that takes O(log(n)) time to add, erase and search.
 * Comparing with treap and red-black tree which has the same function and performance,
 * the code length of Skiplist can be comparatively short and the idea behind Skiplists are
 * just simple linked lists.
 *
 * In singly or doubly linked list you cannot jump to nth node.
 *
 * Skip List gives logarithmic binary search complexity in a sorted linked list at the cost of
 * duplicating data.
 *
 * Balanced Binary Search tree gives logarithmic so why we are doing skiplist.
 * In order to balance tree you have to do rotations which requires locking an entire portion of
 * subtree. At which point your read access are blocked.
 *
 * In very heavy insert and look up/read operations where both happening together, then skiplist
 * is localized where you don't have to lock all of these.
 *
 * For example: we have a Skiplist containing [30,40,50,60,70,90] and we want to add 80 and 45 into it.
 * The Skiplist works this way:
 *
 * You can see there are many layers in the Skiplist. Each layer is a sorted linked list.
 * With the help of the top layers, add , erase and search can be faster than O(n).
 * It can be proven that the average time complexity for each operation is O(log(n)) and
 * space complexity is O(n).
 *
 * To be specific, your design should include these functions:
 *
 * bool search(int target) : Return whether the target exists in the Skiplist or not.
 * void add(int num): Insert a value into the SkipList.
 * bool erase(int num): Remove a value in the Skiplist. If num does not exist in the Skiplist, do nothing
 * and return false. If there exists multiple num values, removing any one of them is fine.
 *
 * Note that duplicates may exist in the Skiplist, your code needs to handle this situation.
 *
 * Example:
 * Skiplist skiplist = new Skiplist();
 * skiplist.add(1);
 * skiplist.add(2);
 * skiplist.add(3);
 * skiplist.search(0);   // return false.
 * skiplist.add(4);
 * skiplist.search(1);   // return true.
 * skiplist.erase(0);    // return false, 0 is not in skiplist.
 * skiplist.erase(1);    // return true.
 * skiplist.search(1);   // return false, 1 has already been erased.
 *
 * Constraints:
 * 0 <= num, target <= 20000
 * At most 50000 calls will be made to search, add, and erase.
 *
 *  Skip List is probabilistic data structure.
 * Insertion: O(n)
 * Delete: O(logn)
 * Search: O(logn)
 *
 * resources/SkipLists.jpg
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SkipList {

    public class SkipNode {
        int data;
        SkipNode next;
        SkipNode down;

        public SkipNode(int x) {
            this.data = x;
            this.next = null;
            this.down = null;
        }
    }

    private List<SkipNode> skipList;
    private final Random random;

    public SkipList() {
        skipList = new ArrayList<>();
        random = new Random();
    }

    private void insert(int num) {
        insert(num, 0, null);
    }

    private void insert(int num, int level, SkipNode lowerNode) {
        SkipNode n = new SkipNode(num);

        if(skipList.size() <= level) { //This means you are creating new level
            SkipNode minNode = new SkipNode(Integer.MIN_VALUE);
            SkipNode maxNode = new SkipNode(Integer.MAX_VALUE);
            minNode.next = n;
            n.next = maxNode;
            if(level != 0) {
                minNode.down = skipList.get(level - 1);
                SkipNode lastNode = skipList.get(level - 1);
                while (lastNode.next != null) {
                    lastNode = lastNode.next;
                }
                maxNode.down = lastNode;
            }
            skipList.add(minNode);
        } else {
            //Find the place to insert the node
            SkipNode current = skipList.get(level);

            //If current is greater than num then num needs to be
            // inserted at the beginning
            if(current.data > num) {
                n.next = current;
            } else {
                while (current.next != null && num > current.next.data) {
                    current = current.next;
                }
                SkipNode nextNode = current.next;
                current.next = n;
                n.next = nextNode;
            }
        }

        //Let's decide if we need to add node at level+
        if(random.nextBoolean()) {
            //Insert at level + 1
            insert(num, level + 1, n);
        }
        n.down = lowerNode;
    }

    private boolean search(int target) {
        return search(target, skipList.get(skipList.size() - 1));
        //Start search from the last entry
    }

    private boolean search(int target, SkipNode current) {
        if(current == null) { //not able to find the target
            return false;
        }

        while (current.next != null && current.next.data <= target) {
            current = current.next;
        }

        if(current.data == target) {
            return true;
        } else {
            return search(target, current.down);
        }
    }

    private boolean delete(int target) {
        return delete(target, skipList.get(skipList.size() - 1));
    }

    private boolean delete(int target, SkipNode current) {

        if(current == null) { //Not found
            return false;
        }

        SkipNode prevNode = null;
        SkipNode prevNodeDown = null;
        while(current.next != null && current.next.data <= target) {
            prevNode = current;
            prevNodeDown = current.down;
            current = current.next;
        }

        if(current.data == target) {
            //At this point prevNode is not null
            prevNode.next = current.next;
            delete(target, prevNodeDown);
            return true;
        } else {
            return delete(target, current.down);
        }
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.insert(5);
        skipList.insert(3);
        skipList.insert(2);
        skipList.insert(10);
        skipList.insert(15);
        skipList.insert(12);
        skipList.insert(20);
        skipList.insert(25);
        System.out.println("Search 3 " + skipList.search(3));
        System.out.println("Search 5 " + skipList.search(5));
        System.out.println("Search 15 " + skipList.search(15));
        System.out.println("Search 4 " + skipList.search(4));
        System.out.println("Search 12 " + skipList.search(12));
        System.out.println("Search 25 " + skipList.search(25));
        System.out.println("Delete 5 " + skipList.delete(5));
        System.out.println("Delete 5 " + skipList.delete(5));
        System.out.println("Search 5 " + skipList.search(5));
        System.out.println("Delete 12 " + skipList.delete(12));
        System.out.println("Search 12 " + skipList.search(12));
        System.out.println("Delete 15 " + skipList.delete(15));
        System.out.println("Search 15 " + skipList.search(15));
        System.out.println("Delete 25 " + skipList.delete(25));
        System.out.println("Search 25 " + skipList.search(25));

        SkipList skipList1 = new SkipList();
        System.out.println("Test");
        skipList1.insert(16);
        skipList1.insert(5);
        skipList1.insert(14);
        skipList1.insert(13);
        skipList1.insert(0);
        skipList1.insert(3);
        skipList1.insert(12);
        skipList1.insert(9);
        skipList1.insert(12);
        System.out.println(skipList1.delete(3));
        System.out.println(skipList1.search(6));
        skipList1.insert(7);
        System.out.println(skipList1.delete(0));
        System.out.println(skipList1.delete(1));
        System.out.println(skipList1.delete(10));
        skipList1.insert(5);
        System.out.println(skipList1.delete(3));
        System.out.println(skipList1.search(12));
        System.out.println(skipList1.search(7));
        System.out.println(skipList1.search(16));
    }
}
