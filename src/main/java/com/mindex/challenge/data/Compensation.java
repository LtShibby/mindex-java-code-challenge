package com.mindex.challenge.data;

/**
 * The Compensation class represents the compensation information for an employee.
 */
public class Compensation {
    private Employee employee;
    private Double salary;
    private String effectiveDate;

    /**
     * Constructs a new Compensation object.
     *
     * @param employee       The employee associated with the compensation.
     * @param salary         The salary amount of the compensation.
     * @param effectiveDate  The effective date of the compensation.
     */
    public Compensation(Employee employee, Double salary, String effectiveDate) {
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    /**
     * Retrieves the employee associated with the compensation.
     *
     * @return The employee object.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets the employee associated with the compensation.
     *
     * @param employee The employee object.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Retrieves the salary amount of the compensation.
     *
     * @return The salary amount.
     */
    public Double getSalary() {
        return salary;
    }

    /**
     * Sets the salary amount of the compensation.
     *
     * @param salary The salary amount.
     */
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    /**
     * Retrieves the effective date of the compensation.
     *
     * @return The effective date.
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the effective date of the compensation.
     *
     * @param effectiveDate The effective date.
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Compensation [")
                .append("employee=").append(employee).append(",\n")
                .append("salary=").append(salary).append(",\n")
                .append("effectiveDate=").append(effectiveDate).append("]");

        return sb.toString();
    }
}
