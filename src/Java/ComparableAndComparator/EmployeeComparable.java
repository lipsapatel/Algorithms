package Java.ComparableAndComparator;

import java.util.Arrays;

/**
 * Example of Comparable and compareTo method.
 * It can compare only on one field.
 */
public class EmployeeComparable implements Comparable<EmployeeComparable> {

    private int id;
    private String name;
    private int age;
    private long salary;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public long getSalary() {
        return salary;
    }

    public EmployeeComparable(int id, String name, int age, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public int compareTo(EmployeeComparable employeeComparable) {

        //Let's sort the employee based on id in ascending order.
        // Returns negative, zero or a positive integer as "this" employee id is
        //less than, equal to or greater than specified object.

        return (this.id - employeeComparable.id);
    }

    @Override
    //This is required to print the user friendly information about the Employee
    public String toString() {

        return "[id = " + this.id + ", name = " + this.name + ", age " + this.age + ", salary = " + this.salary + "]";
    }

    public static void main(String[] args) {

        EmployeeComparable[] employeeComparables = new EmployeeComparable[4];
        employeeComparables[0] = new EmployeeComparable(10, "Mike", 25, 10000);
        employeeComparables[1] = new EmployeeComparable(20, "Kushal", 29, 40000);
        employeeComparables[2] = new EmployeeComparable(5, "Lipsa", 23, 24000);
        employeeComparables[3] = new EmployeeComparable(1, "Pankaj", 32, 40001);

        Arrays.sort(employeeComparables);
        System.out.println("Sorting of Employees list by id: \n" + Arrays.toString(employeeComparables));
    }
}
