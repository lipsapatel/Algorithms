package SortObjectArray;

import java.util.Arrays;

/**
 * Code used to sort the array of Employee objects
 */
public class SortObjectArray {

    public static void main(String[] args) {

        Employee[] employeeArray = new Employee[4];

        employeeArray[0] = new Employee(10, "Mikey", 25, 10000);
        employeeArray[1] = new Employee(20, "Lipsa", 33, 20000);
        employeeArray[2] = new Employee(5, "Lisa", 35, 5000);
        employeeArray[3] = new Employee(1, "Kushal", 32, 40000);

        //Sorting employees array using Comparable interface implementation
        Arrays.sort(employeeArray);

        System.out.println("Default Sorting of Employees list:\n" + Arrays.toString(employeeArray));
    }
}
