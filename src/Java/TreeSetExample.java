package Java;

import java.util.*;

/**
 * TreeSet is one of the most important implementations of the SortedSet
 * interface in Java that uses a Tree for storage.
 * The ordering of the elements is maintained by a set using their
 * natural ordering whether or not an explicit comparator is provided.
 * This must be consistent with equals if it is to correctly implement the
 * Set interface. It can also be ordered by a Comparator provided at set
 * creation time, depending on which constructor is used.
 * The TreeSet implements a NavigableSet interface by inheriting
 * AbstractSet class.
 *
 * Add, remove - O(logn)
 */
public class TreeSetExample {
    public static void main(String[] args) {
        TreeSet<String> ts1 = new TreeSet<>();

        // Elements are added using add() method
        ts1.add("C");
        ts1.add("A");
        ts1.add("B");

        // Duplicates will not get insert
        ts1.add("C");

        // Elements get stored in default natural
        // Sorting Order(Ascending)
        System.out.println(ts1);

        TreeMap<int[], Integer> ts2 = new TreeMap<>((i1, i2) -> {
            if(i1[0] == i2[0]) {
                return i1[1] - i2[1];
            } else {
                return i1[0] - i2[0];
            }
        });
        ts2.put(new int[]{1, 3}, 1);
        ts2.put(new int[]{0, 2}, 1);
        ts2.put(new int[]{0, 7}, 1);
        ts2.put(new int[]{1, 5}, 1);
        ts2.put(new int[]{2, 3}, 1);

        for(int[] interval: ts2.keySet()) {
            System.out.println(Arrays.toString(interval));
        }

        TreeMap<int[], Integer> treeMap = new TreeMap<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });
    }
}
