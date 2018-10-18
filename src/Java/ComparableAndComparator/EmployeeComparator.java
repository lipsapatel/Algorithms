package Java.ComparableAndComparator;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Example of Comparator and compare method.
 * It can compare on more than one field
 */
public class EmployeeComparator {

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

    public EmployeeComparator(int id, String name, int age, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    //This is required to print the user friendly information about the Employee
    public String toString() {

        return "[id = " + this.id + ", name = " + this.name + ", age " + this.age + ", salary = " + this.salary + "]";
    }

    /**
     * Comparator to sort employees list or array in order of salary
     */
    public static Comparator<EmployeeComparator> salaryComparator = new Comparator<EmployeeComparator>() {

        @Override
        public int compare(EmployeeComparator o1, EmployeeComparator o2) {

            return (int)(o1.getSalary() - o2.getSalary());
        }
    };

    /**
     * Comparator to sort employees list or array in order of Age
     */
    public static Comparator<EmployeeComparator> ageComparator = new Comparator<EmployeeComparator>() {

        @Override
        public int compare(EmployeeComparator o1, EmployeeComparator o2) {
            return o1.getAge() - o2.getAge();
        }
    };

    /**
     * Comparator to sort employee list or order in order of name
     */
    public static Comparator<EmployeeComparator> nameComparator = new Comparator<EmployeeComparator>() {

        @Override
        public int compare(EmployeeComparator o1, EmployeeComparator o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public static void main(String[] args) {

        EmployeeComparator[] employeeComparator = new EmployeeComparator[5];
        employeeComparator[0] = new EmployeeComparator(10, "Mike", 25, 10000);
        employeeComparator[1] = new EmployeeComparator(20, "Kushal", 29, 40000);
        employeeComparator[2] = new EmployeeComparator(5, "Lipsa", 23, 24000);
        employeeComparator[3] = new EmployeeComparator(1, "Pankaj", 32, 40001);
        employeeComparator[4] = new EmployeeComparator(1, "Paul", 32, 40001);

        //sort employees array using comparator by Salary
        Arrays.sort(employeeComparator, EmployeeComparator.salaryComparator);
        System.out.println("Employees list sorted by salary: \n" + Arrays.toString(employeeComparator));

        //sort employees array using comparator by Age
        Arrays.sort(employeeComparator, EmployeeComparator.ageComparator);
        System.out.println("Employees list sorted by Age: \n" + Arrays.toString(employeeComparator));

        //sort employees array using comparator by Name
        Arrays.sort(employeeComparator, EmployeeComparator.nameComparator);
        System.out.println("Employees list sorted by Name: \n" + Arrays.toString(employeeComparator));

        //Employees list sorted by ID and then name using Comparator class
        Arrays.sort(employeeComparator, new EmployeeComparatorByIdAndName());
        System.out.println("Employees list sorted by ID and Name: \n" + Arrays.toString(employeeComparator));

    }
}
