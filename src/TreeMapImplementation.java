import java.util.TreeMap;

/**
 * The map is sorted according to the natural ordering of its keys or by
 * a comparator provided at map creation time, depending on which constructor
 * is used.
 *
 * The treemap implementation is not synchronized in the sense that if a map
 * is accessed by multiple threads concurrently and at least one of the threads
 * modifies the map structurally, it must be synchronized externally.
 *
 * TreeMap does not allow null keys(like map) and thus NPE is thrown.
 * However, multiple null values can be associated with different keys.
 *
 * Iterator is fail-fast in nature, thus any concurrent modification will
 * throw ConcurrentModificationException.
 *
 * TreeMap is based upon tree data structure.
 *
 * Each node has
 *
 * 3 variables - key, value, color
 * 3 reference - left, right and parent.
 *
 * The algorithmic implementation is adopted from those of Red-Black tree.
 *
 * Guaranteed time complexity: O(logn) for containsKey, get, put, remove
 *
 * firstKey() - Returns the first(lowest) key currently in this sorted map
 * lastKey() - Returns the last(highest) key currently in this sorted map
 */
public class TreeMapImplementation {

    public static void treeMap() {
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        treeMap.put(10, "4");
        treeMap.put(20, "welcome");
        treeMap.put(15, "you");
        treeMap.put(5, "geeks");

        System.out.println("TreeMap " + treeMap);
    }

    public static void main(String[] args) {
        treeMap();
    }
}
