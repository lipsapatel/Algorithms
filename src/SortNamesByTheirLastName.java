import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Given a list of names (first name and last name), sort the list by their last names.
 * If two last names are same then the first name needs to be compared.
 *
 * List[] names = {"Lipsa Patel", "Yashvi Shah", "Kushal Shah", "John Kirfman", "Bryan Mckinney"};
 * Output = ["John Kirfman", "Bryan Mckinney", "Lipsa Patel", "Kushal Shah", "Yashvi Shah"]
 *
 * We have sort() and Collections.sort() but we cannot do normally sorting because we have to
 * sort by last names.
 * These sort methods use compare() method of Comparator so we need to override the compare()
 * method.
 * Inside the compare() method, we need to split last names and first names and compare the last
 * names.
 */
public class SortNamesByTheirLastName {

    private static void sortNamesByTheirLastName(ArrayList<String> names) {

        Collections.sort(names, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {

                String[] split1 = o1.split(" ");
                String[] split2 = o2.split(" ");

                String firstName1 = split1[0];
                String lastName1 = split1[1];

                String firstName2 = split2[0];
                String lastName2 = split2[1];

                if (lastName1.compareTo(lastName2) > 1) {
                    return 1;
                } else if (lastName1.compareTo(lastName2) < 1) {
                    return -1;
                } else { // When both last names are same
                    if (firstName1.compareTo(firstName2) > 1) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });

        System.out.println("The Array List after sorting by their last names " + names);
    }

    public static void main(String[] args) {

        ArrayList<String> names = new ArrayList<String>();
        names.add("Lipsa Patel");
        names.add("Yashvi Shah");
        names.add("Kushal Shah");
        names.add("Bryan Mckinney");
        names.add("John Kirfman");

        sortNamesByTheirLastName(names);
    }
}
