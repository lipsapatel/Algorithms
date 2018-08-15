package Java;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This throws ConcurrentModificationException as soon as the ArrayList is modified.
 * If we change the implementation to CopyOnWriteArrayList, then we don't get any exception.
 * Notice that it allows the modification of list, but it doesn't change the iterator and we
 * get same element as it was on original list.
 */
public class CopyOnWriteArrayListExample {

    public static void main(String[] args) {

        //List<String> arrayList = new ArrayList<>();

        List<String> arrayList = new CopyOnWriteArrayList<>();

        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");

        //Get the iterator
        Iterator<String> iterator = arrayList.iterator();

        //Manipulate list while iterating
        while(iterator.hasNext()) {

            System.out.println("ArrayList is: " + arrayList);

            String string = iterator.next();
            System.out.println(string);

            if (string.equals("2")) {
                arrayList.remove("5");
            }

            if (string.equals("3")) {
                arrayList.add("3 found");
            }

            //Below code don't throw ConcurrentModificationException because it doesn't change the modCount variable of arrayList
            if (string.equals("4")) {
                arrayList.set(1, "4");
            }
        }
    }
}
