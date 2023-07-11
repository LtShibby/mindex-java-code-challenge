package com.mindex.challenge.data;

/**
 * The ReportingStructure class represents the reporting structure of an employee,
 * including the employee and the number of reports under the employee.
 */
public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    /**
     * Default constructor for the ReportingStructure class.
     */
    public ReportingStructure() {
        // Default constructor
    }

    /**
     * Constructor for the ReportingStructure class.
     *
     * @param employee        The employee for the reporting structure.
     * @param numberOfReports The number of reports under the employee.
     */
    public ReportingStructure(Employee employee, int numberOfReports) {
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }

    /**
     * Retrieves the employee in the reporting structure.
     *
     * @return The employee.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets the employee in the reporting structure.
     *
     * @param employee The employee.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Retrieves the number of reports under the employee.
     *
     * @return The number of reports.
     */
    public int getNumberOfReports() {
        return numberOfReports;
    }

    /**
     * Sets the number of reports under the employee.
     *
     * @param numberOfReports The number of reports.
     */
    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}
