package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
/**
 * The EmployeeServiceImpl class is an implementation of the EmployeeService interface.
 * It provides methods to create, read, update, and check the existence of employee objects in the persistence layer.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;

    /**
     * Constructor for the EmployeeServiceImpl class.
     *
     * @param employeeRepository    The repository responsible for accessing the persistence layer for employees.
     */
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Creates a new employee object in the persistence layer.
     *
     * @param employee    The employee object to be created.
     * @return The created employee object.
     */
    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    /**
     * Retrieves an employee object by id from the persistence layer.
     *
     * @param id    The id of the employee to be retrieved.
     * @return The retrieved employee object.
     * @throws RuntimeException if no employee is found for the given id.
     */
    @Override
    public Employee read(String id) {
        LOG.debug("Reading employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    /**
     * Updates an existing employee object in the persistence layer.
     *
     * @param employee    The employee object to be updated.
     * @return The updated employee object.
     */
    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }
    
    /**
     * Checks if an employee object exists for the given employeeId in the persistence layer.
     *
     * @param employeeId    The employeeId to check.
     * @return True if an employee object exists, false otherwise.
     */
    @Override
    public boolean exists(String employeeId) {
        LOG.debug("Checking if employee exists for employeeId [{}]", employeeId);

        return employeeRepository.existsByEmployeeId(employeeId);
    }
}
