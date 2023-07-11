package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The EmployeeController class is a REST controller that handles HTTP requests related to employees and compensations.
 * It provides endpoints to create, read, update, and retrieve information about employees and their compensations.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;
    private final CompensationService compensationService;

    /**
     * Constructor for the EmployeeController class.
     * 
     * @param employeeService    The service responsible for handling employee-related operations.
     * @param compensationService    The service responsible for handling compensation-related operations.
     */
    @Autowired
    public EmployeeController(EmployeeService employeeService, CompensationService compensationService) {
        this.employeeService = employeeService;
        this.compensationService = compensationService;
    }

    /**
     * Endpoint to create a new employee.
     * 
     * @param employee    The employee object to be created.
     * @return ResponseEntity with the created employee object and HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);
        Employee createdEmployee = employeeService.create(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve an employee by ID.
     * 
     * @param id    The ID of the employee to be retrieved.
     * @return ResponseEntity with the retrieved employee object and HTTP status 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable String id) {
        LOG.debug("Received employee read request for id [{}]", id);
        Employee employee = employeeService.read(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /**
     * Endpoint to update an employee.
     * 
     * @param id        The ID of the employee to be updated.
     * @param employee  The updated employee object.
     * @return ResponseEntity with the updated employee object and HTTP status 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee update request for id [{}] and employee [{}]", id, employee);
        employee.setEmployeeId(id);
        Employee updatedEmployee = employeeService.update(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve the reporting structure of an employee.
     * 
     * @param employeeId    The ID of the employee.
     * @return ResponseEntity with the reporting structure object and HTTP status 200 (OK).
     */
    @GetMapping("/reportingStructure/{employeeId}")
    public ResponseEntity<ReportingStructure> getReportingStructure(@PathVariable String employeeId) {
        LOG.debug("Received reporting structure request for employeeId [{}]", employeeId);
        Employee employee = employeeService.read(employeeId);
        int numberOfReports = calculateNumberOfReports(employee);
        ReportingStructure reportingStructure = new ReportingStructure(employee, numberOfReports);
        return ResponseEntity.ok(reportingStructure);
    }

    /**
     * Helper method to calculate the number of reports for an employee recursively.
     * 
     * @param employee    The employee object.
     * @return The number of reports for the employee.
     */
    private int calculateNumberOfReports(Employee employee) {
        if (employee.getDirectReports() == null || employee.getDirectReports().isEmpty()) {
            return 0;
        }
        int numberOfReports = employee.getDirectReports().size();
        for (Employee directReport : employee.getDirectReports()) {
            directReport = employeeService.read(directReport.getEmployeeId());
            numberOfReports += calculateNumberOfReports(directReport);
        }
        return numberOfReports;
    }

    /**
     * Endpoint to create a new compensation for an employee.
     * 
     * @param employeeId     The ID of the employee.
     * @param salary         The salary value.
     * @param effectiveDate  The effective date of the compensation.
     * @return ResponseEntity with the created compensation object and HTTP status 201 (Created).
     */
    @PostMapping("/{employeeId}/compensation")
    public ResponseEntity<Compensation> createCompensation(
            @PathVariable String employeeId,
            @RequestParam("salary") Double salary,
            @RequestParam("effectiveDate") String effectiveDate
    ) {
        LOG.debug("Received compensation create request for employeeId [{}], salary [{}], and effectiveDate [{}]",
                employeeId, salary, effectiveDate);
        Employee employee = employeeService.read(employeeId);
        Compensation compensation = new Compensation(employee, salary, effectiveDate);
        Compensation createdCompensation = compensationService.create(compensation);
        return new ResponseEntity<>(createdCompensation, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve the compensation for an employee.
     * 
     * @param employeeId    The ID of the employee.
     * @return ResponseEntity with the retrieved compensation object and HTTP status 200 (OK).
     */
    @GetMapping("/{employeeId}/compensation")
    public ResponseEntity<Compensation> getCompensation(@PathVariable String employeeId) {
        LOG.debug("Received compensation read request for employeeId [{}]", employeeId);
        Compensation compensation = compensationService.read(employeeId);
        return new ResponseEntity<>(compensation, HttpStatus.OK);
    }
}

