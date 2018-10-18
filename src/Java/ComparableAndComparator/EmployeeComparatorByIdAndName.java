package Java.ComparableAndComparator;

import java.util.Comparator;

/**
 * Separate class implementation of Comparator interface that will compare two Employees object
 * first on their id and if they are same then on name
 */
public class EmployeeComparatorByIdAndName implements Comparator<EmployeeComparator> {

    @Override
    public int compare(EmployeeComparator e1, EmployeeComparator e2) {

        int flag = e1.getId() - e2.getId();

        if (flag == 0) {
            flag = e1.getName().compareTo(e2.getName());
        }

        return flag;
    }
}
