package IK.Graphs.Class;

import java.util.HashMap;

/**
 Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

 Implement the IK.Graphs.Class.LRUCache class:

 IK.Graphs.Class.LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 int get(int key) Return the value of the key if the key exists, otherwise return -1.
 void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache.
 If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 The functions get and put must each run in O(1) average time complexity.



 Example 1:

 Input
 ["IK.Graphs.Class.LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 Output
 [null, null, null, 1, null, -1, null, -1, 3, 4]

 Explanation
 IK.Graphs.Class.LRUCache lRUCache = new IK.Graphs.Class.LRUCache(2);
 lRUCache.put(1, 1); // cache is {1=1}
 lRUCache.put(2, 2); // cache is {1=1, 2=2}
 lRUCache.get(1);    // return 1
 lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 lRUCache.get(2);    // returns -1 (not found)
 lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 lRUCache.get(1);    // return -1 (not found)
 lRUCache.get(3);    // return 3
 lRUCache.get(4);    // return 4


 Constraints:

 1 <= capacity <= 3000
 0 <= key <= 104
 0 <= value <= 105
 At most 2 * 105 calls will be made to get and put.

 We cannot use array because we need to shift items when we insert at head.
 We cannot use only hashmap because there's no ordering in hashmap.
 We cannot use only Doublylinked list because get is not O(1)

 resources/LRUCache.jpg

 Approach
 1) We will use HashMap and doubly linked list.
 2) The items are in reverse order of their timestamp.
 3) Least recently used item is at the tail and most recently used at head.
 4) We can perform put(insert/update) and get in O(1)
 5) Doubly linked list makes it easier to remove node from the middle and move it to head in O(1) time for update and get
 6) Check for the edge cases where there is only one node and if the node is head or tail
 7) get(key) - If the key is not present in hashmap then its a cache miss and return -1
 8) If the key is present return its value and move the node in front
 9) put(key, value) - If the key is present in hashmap, its update, update the value and move it to front.
 10) If the key is not present its insert, check if capacity is reached, if it is reached then remove the tail node which is Least recently used
 11) Then insert the node at the head.
 12) Decrement and increment currSize and also remove and insert in hashmap

 TC: O(1)
 SC: O(n)

 **/

public class LRUCache {

    public class DNode {
        int key;
        int value;
        DNode prev;
        DNode next;

        public DNode(int k, int v) {
            this.key = k;
            this.value = v;
            prev = null;
            next = null;
        }
    }

    private HashMap<Integer, DNode> cache;
    private int currSize;
    private int capacity;
    DNode head;
    DNode tail;

    public LRUCache(int capacity) {
        cache = new HashMap<>();
        currSize = 0;
        head = null;
        tail = null;
        this.capacity = capacity;
    }

    public int get(int key) {

        //If the key is not present in hashmap then its a miss so return -1
        if(!cache.containsKey(key)) {
            return -1;
        }

        //Move the node at head and return the value
        extractAndMove(key);

        return head.value;
    }

    public void extractAndMove(int key) {
        DNode node = cache.get(key);

        DNode prevNode = node.prev;
        DNode nextNode = node.next;

        //If node is head then do nothing
        if(head == node) {
            return;
        }

        prevNode.next = nextNode;

        if(nextNode != null) {
            nextNode.prev = prevNode;
        } else { //The node is tail
            tail = prevNode;
        }

        //Move at head
        node.next = head;
        head.prev = node;
        node.prev = null;
        head = node;
    }

    public void put(int key, int value) {
        //Case 1: If key exists in hashmap, then update the value and move to head
        if(cache.containsKey(key)) {
            cache.get(key).value = value;

            extractAndMove(key);

        } else { //Case 2: Check capacity and then insert

            //Remove
            if(currSize == capacity) {
                cache.remove(tail.key);

                DNode prevNode = tail.prev;

                if(prevNode != null) {
                    prevNode.next = null;
                } else { //Tail is the only node and we are removing it
                    head = null;
                }

                tail = prevNode;
                currSize--;
            }

            //Insert
            DNode node = new DNode(key, value);

            if(head == null) { //No node
                head = tail = node;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }
            currSize++;
            cache.put(key, node);
        }
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(1);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4
    }
}