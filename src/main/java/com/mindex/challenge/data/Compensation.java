package com.mindex.challenge.data;

public class Compensation {
    private Employee employee;
    private Double salary;
    private String effectiveDate;

    public Compensation(Employee employee, Double salary, String effectiveDate) {
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

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
