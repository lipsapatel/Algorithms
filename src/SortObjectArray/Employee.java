package SortObjectArray;

/**
 * Employee class
 */
public class Employee implements Comparable<Employee> {

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

    public Employee(int id, String name, int age, long salary) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee employee) {

        //Let's sort the employee based on id in ascending order
        // Returns a negative integer, zero or a positive integer as this employee id
        return (this.id - employee.id);
    }

    @Override
    //This is overriden to print the user friendly information about the Employee
    public String toString() {

        return "[id=" + this.id + ", name=" + this.name + ", age=" + this.age + ", salary=" + this.salary + "]";
    }
}
