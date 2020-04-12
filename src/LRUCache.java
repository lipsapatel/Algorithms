import java.util.HashMap;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache,
 * otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently used item before
 * inserting a new item.
 *
 * The cache is initialized with a positive capacity.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 * LRUCache cache = new LRUCache( 2 /capacity/)
 *   cache.put(1,1);
    cache.put(2,2);
    cache.get(1);       // returns 1
    cache.put(3,3);    // evicts key 2
    cache.get(2);       // returns -1 (not found)
    cache.put(4,4);    // evicts key 1
    cache.get(1);       // returns -1 (not found)
    cache.get(3);       // returns 3
    cache.get(4);       // returns 4
 **************************************************************************
 * Approach 1: Ordered dictionary
 *
 * We're asked to implement the structure which provides the following operations in O(1) time :
 *     Get the key / Check if the key exists
 *     Put the key
 *     Delete the first added key

 * The first two operations in O(1) time are provided by the standard hashmap, and the last one -
 * by linked list.
 * There is a structure called ordered dictionary, it combines behind both hashmap and linked list.
 * In Java it's LinkedHashMap.
 *
 Time complexity : O(1) both for put and get since all operations with ordered dictionary :
 get/in/set/move_to_end/popitem (get/containsKey/put/remove) are done in a constant time.

 Space complexity : O(capacity) since the space is used only for an ordered dictionary with at most
 capacity + 1 elements.

 Two things are important when using LinkedHashMap

 1) The access order instead of insertion order in the constructor which is set to true
 2) Overriding the removeEldest API to limit the size of Hashmap to that of capacity.
 *************************************************************************************************
 *
 * Approach 2: Hashmap + DoubleLinkedList
 *
 * The problem can be solved with a hashmap that keeps track of the keys and its values in the
 * double linked list. That results in O(1) time for put and get operations and allows to remove
 * the first added node in O(1) time as well.
 *
 * One advantage of double linked list is that the node can remove itself without other reference.
 * In addition, it takes constant time to add and remove nodes from the head or tail.
 *
 * One particularity about the double linked list implemented here is that there are pseudo head and
 * pseudo tail to mark the boundary, so that we don't need to check the null node during the update.
 *
 * Complexity Analysis
 *     Time complexity : O(1) both for put and get.
 *     Space complexity : O(capacity) since the space is used only for a hashmap
 *     and double linked list with at most capacity + 1 elements.
 *
 *  resources/LRUCache.jpg
 */
/*public class LRUCache extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}*/

public class LRUCache {

    public class DNode {
        int key;
        int value;
        DNode prev;
        DNode next;
    }

    private HashMap<Integer, DNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    DNode head;
    DNode tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        head = new DNode();
        tail = new DNode();

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DNode node = cache.get(key);

        if(node == null) {
            return -1;
        }

        //Move accessed node to the head
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DNode node = cache.get(key);

        if(node == null) {
            node = new DNode();
            node.key = key;
            node.value = value;
            cache.put(key, node);
            addNode(node);
            size++;

            if(size > capacity) {
                DNode tailNode = tail.prev;
                removeNode(tailNode);
                cache.remove(tailNode.key);
                size--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void addNode(DNode node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DNode node) {
        DNode prevNode = node.prev;
        DNode nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private void moveToHead(DNode node) {
        removeNode(node);
        addNode(node);
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}
