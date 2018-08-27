package Java.Serialization;

import java.io.Serializable;

/**
 * Employee class that implements Serializable interface
 */
public class Employee implements Serializable {

    private String name;
    private int id;
    transient private int salary;

    @Override
    public String toString() {
        return "Employee{name=" + name + " id=" + id + " salary=" + salary + "}";
    }

    /**
     * This method is used for getting name
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets name
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns id
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * This method sets id
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method returns salary
     * @return int salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * This method sets salary
     * @param salary int
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }
}
