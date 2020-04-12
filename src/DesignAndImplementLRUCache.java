import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Design And Implement LRU Cache
 * The LRU caching scheme removes the least recently used frame,
 * when the cache is full and a new page is referenced which is not there in the cache.
 *
 * You are given one integer named capacity, denoting the maximum size possible of the
 * LRU cache. Also you are given three integer arrays named query_type, key and value, each having size n.
 * query_type[i], key[i] and value[i] together denotes one query. So there are total n queries.
 *
 * query_type contains 0 or 1. query_type[i] = 0 means ith query is GET query and
 * query_type[i] = 1 means ith query is SET query. key and value arrays contain only positive integers.
 *
 * You have to return an array containing answers for GET queries.
 *
 * GET query:
 * For GET query only key[i] matters, do not care what is stored in value[i].
 * For each GET query append one integer in the array to be returned.
 * Append the value of the key[i], if the key[i] exists in the cache, otherwise append -1.
 *
 * SET query:
 * If key[i] is already present in the cache then update its value to value[i],
 * else add key[i] with value value[i] in the cache.
 *
 * If n = 7,
 * capacity = 2,
 *
 * index     query_type                 key       value
 * 0              1                    5             11
 * 1              1                    10           22
 * 2              0                    5               1
 * 3              1                    15           33
 * 4              0                    10             1
 * 5              1                    5             55
 * 6              0                    5               1
 *
 * Output will be:
 * 11
 * -1
 * 55
 *
 * Constraints:
 *     1 <= n <= 10^5
 *     1 <= capacity <= 10^5
 *     query_type[i] belongs to {0, 1}
 *     1 <= key[i] <= 10^5
 *     1 <= value[i] <= 10^5
 *
 * query_type = [1 1 0 1 0 1 0]
 * key = [5 10 5 15 10 5 5]
 * value = [11 22 1 33 1 55 1]
 *
 * Sample Output:
 * [11 -1 55]
 *
 * Time Complexity: O(1)
 * Space Complexity: O(capacity)
 */
public class DesignAndImplementLRUCache {

    public static class DNode {
        int key;
        int value;
        DNode next;
        DNode prev;
    }

    private static HashMap<Integer, DNode> cache;
    private static int size;
    private static int capacity;
    private static DNode head;
    private static DNode tail;

    private static int get(int key) {
        DNode node = cache.get(key);

        if(node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    private static void put(int key, int value) {
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

    private static void addNode(DNode node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    private static void removeNode(DNode node) {
        DNode prevNode = node.prev;
        DNode nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private static void moveToHead(DNode node) {
        removeNode(node);
        addNode(node);
    }

    private static int[] implementLRUCache(int c, int[] queryType, int[] key, int[] value) {
        cache = new HashMap<>();
        size = 0;
        capacity = c;

        head = new DNode();
        tail = new DNode();

        head.next = tail;
        tail.prev = head;

        ArrayList<Integer> result = new ArrayList<>();

        for(int i = 0; i < queryType.length; i++) {
            if (queryType[i] == 1) { //PUT
                put(key[i], value[i]);
            } else if (queryType[i] == 0) { //GET
                result.add(get(key[i]));
            }
        }

        int[] output = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            output[i] = result.get(i);
        }
        return output;
    }

    public static void main(String[] args) {
        int[] queryType = {1, 1, 0, 1, 0, 1, 0};
        int[] key = {5, 10, 5, 15, 10, 5, 5};
        int[] value = {11, 22, 1, 33, 1, 55, 1};
        int capacity = 2;

        int[] result = implementLRUCache(capacity,queryType, key, value);

        System.out.println(Arrays.toString(result));
    }
}
