package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;

/**
 * The EmployeeService interface provides methods to create, read, update, and check the existence of employee objects.
 */
public interface EmployeeService {
    /**
     * Creates a new employee object.
     *
     * @param employee The employee object to be created.
     * @return The created employee object.
     */
    Employee create(Employee employee);

    /**
     * Retrieves an employee object by id.
     *
     * @param id The id associated with the employee.
     * @return The retrieved employee object, or null if not found.
     */
    Employee read(String id);

    /**
     * Updates an existing employee object.
     *
     * @param employee The updated employee object.
     * @return The updated employee object.
     */
    Employee update(Employee employee);

    /**
     * Checks if an employee exists based on the employeeId.
     *
     * @param employeeId The employeeId to check.
     * @return true if the employee exists, false otherwise.
     */
    boolean exists(String employeeId);
}
