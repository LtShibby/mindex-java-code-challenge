package com.mindex.challenge.data;

import java.util.List;

/**
 * The Employee class represents an employee within the organization.
 */
public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private List<Employee> directReports;

    public Employee() {
    }

    /**
     * Retrieves the unique identifier of the employee.
     *
     * @return The employee ID.
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the unique identifier of the employee.
     *
     * @param employeeId The employee ID.
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Retrieves the first name of the employee.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the employee.
     *
     * @param firstName The first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves the last name of the employee.
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the employee.
     *
     * @param lastName The last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the position of the employee.
     *
     * @return The position.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the position of the employee.
     *
     * @param position The position.
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Retrieves the department of the employee.
     *
     * @return The department.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the department of the employee.
     *
     * @param department The department.
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Retrieves the list of direct reports for the employee.
     *
     * @return The list of direct reports.
     */
    public List<Employee> getDirectReports() {
        return directReports;
    }

    /**
     * Sets the list of direct reports for the employee.
     *
     * @param directReports The list of direct reports.
     */
    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employee [")
                .append("employeeId=").append(employeeId).append(",\n")
                .append("firstName=").append(firstName).append(",\n")
                .append("lastName=").append(lastName).append(",\n")
                .append("position=").append(position).append(",\n")
                .append("department=").append(department).append(",\n")
                .append("directReports=").append(directReports).append("]");

        return sb.toString();
    }
}
