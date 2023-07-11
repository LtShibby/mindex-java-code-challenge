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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;
    private final CompensationService compensationService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, CompensationService compensationService) {
        this.employeeService = employeeService;
        this.compensationService = compensationService;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);
        Employee createdEmployee = employeeService.create(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable String id) {
        LOG.debug("Received employee read request for id [{}]", id);
        Employee employee = employeeService.read(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee update request for id [{}] and employee [{}]", id, employee);
        employee.setEmployeeId(id);
        Employee updatedEmployee = employeeService.update(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @GetMapping("/reportingStructure/{employeeId}")
    public ResponseEntity<ReportingStructure> getReportingStructure(@PathVariable String employeeId) {
        LOG.debug("Received reporting structure request for employeeId [{}]", employeeId);
        Employee employee = employeeService.read(employeeId);
        int numberOfReports = calculateNumberOfReports(employee);
        ReportingStructure reportingStructure = new ReportingStructure(employee, numberOfReports);
        return ResponseEntity.ok(reportingStructure);
    }

    private int calculateNumberOfReports(Employee employee) {
        if (employee.getDirectReports() == null || employee.getDirectReports().isEmpty()) {
            return 0;
        }
        int numberOfReports = employee.getDirectReports().size();
        for (Employee directReport : employee.getDirectReports()) {
            numberOfReports += calculateNumberOfReports(directReport);
        }
        return numberOfReports;
    }

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

    @GetMapping("/{employeeId}/compensation")
    public ResponseEntity<Compensation> getCompensation(@PathVariable String employeeId) {
        LOG.debug("Received compensation read request for employeeId [{}]", employeeId);
        Compensation compensation = compensationService.read(employeeId);
        return new ResponseEntity<>(compensation, HttpStatus.OK);
    }
}
