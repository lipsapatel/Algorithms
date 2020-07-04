/**
 * Design a Skiplist without using any built-in libraries.
 *
 * why skip lists instead of balanced binary trees?
 *     skip lists are implicitly balanced
 *     to balance RB tree after an update, the entire tree has to be locked to concurrent access
 *     In case of skip lists only the adjacent nodes will have to be locked
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
 * Insertion: O(logn)
 * Delete: O(logn)
 * Search: O(logn)
 *
 * Implement a skip list to insert, search and delete data using singly linked lists.
 * More precisely implement the following constructor and methods.
 *
 *     SkipList(int numberOfQueries) / initialize(int numberOfQueries):
 *     A constructor(OOP supported languages) or initializer method to initialize the skip list
 *     with the numberOfQueries.
 *     insert(int val): A void method to insert “val” in the skip list. It’s a void method.
 *     isPresent(int val): A boolean method to return true if the “val” presents in the skip list, false otherwise. Return value must be boolean.
 *     remove(int val): A void method to remove “val” from the skip list.
 *
 * Time Complexity:
 * O(n log n) where n denotes the number of queries.
 * For each operation, insert, search and remove we are traversing the same way down to
 * find out the expected node. So, all these 3 operations will require the same time to execute.
 * Each operation requires O(log n) complexity. For n operations, it is O(n log n)
 *
 * Auxiliary space:
 * O(n log n) where n is the number of nodes in the linked list.
 * As in each level there can be half of nodes of previous level, in the worst case there will
 * be n log n nodes in the skip list. So O(n log n) auxiliary space is required.
 *
 * resources/SkipLists.jpg
 */
import javax.swing.text.AbstractDocument;
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
        int size = -1;
        SkipNode startNode = null;

        if(!skipList.isEmpty()) {
            size = skipList.size() - 1;
            startNode = skipList.get(size);
        }

        int level = getLevel(); //Get the number of level this node is to be inserted
        SkipNode topNode = insertNewLevel(num, level); //insert all the new level if needed

        if(level < size) { //If level is less than size then we need to start from level else we need to start from size
            size = level;
            startNode = skipList.get(size);
        }

        while(size >= 0) {
            SkipNode[] nodes = insert(num, startNode, topNode, size); //insert in existing level - top down
            startNode = nodes[0];
            topNode = nodes[1];
            size--;
        }
    }

    private SkipNode[] insert(int num, SkipNode current, SkipNode topNode, int level) {
        SkipNode n = new SkipNode(num);
        SkipNode nextStart;
        SkipNode nextTop = n;

        if(current.data > num) {
            n.next = current;
            skipList.set(level, n);
            nextStart = (level == 0) ? null : skipList.get(level - 1);
        } else {
            while(current.next != null && current.next.data < num) {
                current = current.next;
            }
            n.next = current.next;
            current.next = n;
            nextStart = current.down;
        }
        if(topNode != null) {
            topNode.down = n;
        }
        return new SkipNode[]{nextStart, nextTop};
    }

    private SkipNode insertNewLevel(int num, int level) {
        SkipNode topNode = null;
        SkipNode lowerNode = null;

        while(skipList.size() <= level) {
            SkipNode n = new SkipNode(num);
            skipList.add(n);
            if(topNode == null) {
                topNode = n;
            }
            n.down = lowerNode;
            lowerNode = n;
        }
        return topNode;
    }

    private int getLevel() {
        int level = 0;
        while(random.nextBoolean()) {
            level++;
        }
        return level;
    }

    private boolean search(int target) {
        if(skipList.isEmpty()) {
            return false;
        }
        int level = skipList.size() - 1;
        SkipNode prev = null;
        SkipNode current = skipList.get(level);

        while(current != null) {
            if(target == current.data) {
                return true;
            } else if(target > current.data && current.next != null) {
                //It's not the last element
                prev = current;
                current = current.next;
            } else {
                if(level == 0) { //At level 0, there is not further going down so not found.
                    return false;
                }
                level--;
                current = (prev == null) ? skipList.get(level) : prev.down;
                prev = null;
            }
        }
        return false;
    }

    private boolean delete(int target) {
        if(skipList.isEmpty()) {
            return false;
        }
        int level = skipList.size() - 1; //starts from last level
        SkipNode prev = null;
        SkipNode current = skipList.get(level);
        boolean found = false;

        //Search
        while(current != null && !found) {
            if(target == current.data) {
                found = true;
            } else if(target > current.data && current.next != null) {
                //If target is greater and is not last element
                prev = current;
                current = current.next;
            } else { //go down
                if(level == 0) {
                    return false;
                }

                level--;
                current = (prev == null) ? skipList.get(level) : prev.down;
                prev = null;
            }
        }

        //Delete
        while(current != null) {

            //First element
            if(prev == null) {

                //Delete
                if(current.next == null) { //Only element
                    skipList.remove(level);
                } else { //This is the first element and having more than one
                    skipList.set(level, current.next);
                }

                //Go Down
                if(level != 0 && skipList.get(level - 1) != current.down) { //Go down if prev is null then
                    //make the first node of level - 1 as prev and if first node == current then prev remains null
                    prev = skipList.get(level - 1);
                }
                current = current.down;
            } else {
                while(prev.next != current) {
                    prev = prev.next;
                }
                //Delete
                prev.next = current.next;

                //Go Down
                current = current.down;
                prev = prev.down;
            }
            level--; //go down
        }
        return found;
    }

    public static void main(String[] args) {
/*        SkipList skipList = new SkipList();
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
        System.out.println(skipList1.search(16));*/

        SkipList skipList2 = new SkipList();
        skipList2.insert(1);
        skipList2.insert(2);
        skipList2.insert(3);
        System.out.println(skipList2.search(0));
        skipList2.insert(4);
        System.out.println(skipList2.search(1));
        System.out.println(skipList2.delete(0));
        System.out.println(skipList2.delete(1));
        System.out.println(skipList2.search(1));

        System.out.println();
        SkipList skipList3 = new SkipList();
        skipList3.insert(0);
        skipList3.insert(5);
        skipList3.insert(2);
        skipList3.insert(1);
        skipList3.insert(4);
        skipList3.insert(6);
        skipList3.insert(7);
        skipList3.insert(8);
        skipList3.insert(9);
        skipList3.insert(10);
        skipList3.insert(0);
        skipList3.insert(51);
        skipList3.insert(21);
        skipList3.insert(11);
        skipList3.insert(41);
        skipList3.insert(61);
        skipList3.insert(71);
        skipList3.insert(81);
        skipList3.insert(91);
        skipList3.insert(101);
        skipList3.insert(02);
        skipList3.insert(52);
        skipList3.insert(22);
        skipList3.insert(12);
        skipList3.insert(42);
        skipList3.insert(62);
        skipList3.insert(72);
        skipList3.insert(82);
        skipList3.insert(92);
        skipList3.insert(102);
        System.out.println(skipList3.search(0));
        System.out.println(skipList3.delete(5));
        System.out.println(skipList3.search(2));
        System.out.println(skipList3.search(3));
        System.out.println(skipList3.search(2));

        System.out.println();
        SkipList skipList4 = new SkipList();
        skipList4.insert(1);
        System.out.println(skipList4.delete(1));
        skipList4.insert(5);
        System.out.println(skipList4.delete(4));
        skipList4.insert(5);
        System.out.println(skipList4.delete(5));
        skipList4.insert(10);
        System.out.println(skipList4.search(9));
        skipList4.insert(7);
        System.out.println(skipList4.search(10));
        System.out.println(skipList4.delete(7));
        System.out.println(skipList4.delete(10));
    }
}
