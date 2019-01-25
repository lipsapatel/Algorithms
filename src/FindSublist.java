import java.util.ArrayList;

/**
 * Write a function sublists that finds every possible sublist of a given arraylist.
 * Example if ArrayList is {Jane, Bob, Matt, Sara}
 *
 * {Jane, Bob, Matt, Sara}                  {Bob, Matt, Sara}
 * {Jane, Bob, Matt}                        {Bob, Matt}
 * {Jane, Bob, Sara}                        {Bob, Sara}
 * {Jane, Bob}                              {Bob}
 * {Jane, Matt, Sara}                       {Matt, Sara}
 * {Jane, Matt}                             {Matt}
 * {Jana, Sara}                             {Sara}
 * {Jane}                                   {}
 *
 *
 */
public class FindSublist {

    private static void findSubList(ArrayList<String> list, ArrayList<String> chosen) {

        if (list.isEmpty()) {
            System.out.println(chosen.toString());
        } else {

            //Include
            String first = list.get(0);
            list.remove(0);
            chosen.add(first);

            //explore
            findSubList(list, chosen);

            //Exclude
            chosen.remove(chosen.size() - 1);

            //Explore
            findSubList(list, chosen);

            //un-choose, backtrack
            list.add(0, first);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Jane");
        list.add("Bob");
        list.add("Matt");
        list.add("Sara");
        findSubList(list, new ArrayList<String>());
    }
}
