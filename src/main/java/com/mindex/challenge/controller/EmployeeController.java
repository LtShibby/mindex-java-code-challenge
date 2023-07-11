package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final CompensationService compensationService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, CompensationService compensationService) {
        this.employeeService = employeeService;
        this.compensationService = compensationService;
    }

    // Existing endpoints for employee management

    @PostMapping("/{employeeId}/compensation")
    public ResponseEntity<Compensation> createCompensation(
            @PathVariable String employeeId,
            @RequestParam Double salary,
            @RequestParam String effectiveDate
    ) {
        // Set the employee in the compensation object
        Employee employee = employeeService.read(employeeId);

        // Create the compensation
        Compensation createdCompensation = compensationService.create(new Compensation(employee, salary, effectiveDate));

        return new ResponseEntity<>(createdCompensation, HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}/compensation")
    public ResponseEntity<Compensation> getCompensation(@PathVariable String employeeId) {
        Compensation compensation = compensationService.read(employeeId);
        return new ResponseEntity<>(compensation, HttpStatus.OK);
    }
}
