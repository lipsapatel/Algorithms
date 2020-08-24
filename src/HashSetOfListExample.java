import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashSet;

public class HashSetOfListExample {
    public static void main(String[] args) {
        HashSet<List<Integer>> set = new HashSet<>();

        set.add(Arrays.asList(1, 2));
        set.add(Arrays.asList(2, 3));
        set.add(Arrays.asList(1, 2));

        for(List<Integer> list: set) {
            System.out.print(list.get(0));
            System.out.println(list.get(1));
        }

        List<List<Integer>> list = new ArrayList<>(set);

        for(List<Integer> list1: list) {
            System.out.print(list1.get(0));
            System.out.println(list1.get(1));
        }
    }
}
