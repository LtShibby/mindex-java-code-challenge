package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeControllerTest {

    private EmployeeController employeeController;

    private EmployeeService employeeService;
    private CompensationService compensationService;

    @Before
    public void setup() {
        employeeService = Mockito.mock(EmployeeService.class);
        compensationService = Mockito.mock(CompensationService.class);
        employeeController = new EmployeeController(employeeService, compensationService);
    }

    @Test
    public void testCreateEmployee() {
        // Given
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setDepartment("Engineering");
        employee.setPosition("Developer");

        when(employeeService.create(any(Employee.class))).thenReturn(employee);

        // When
        ResponseEntity<Employee> response = employeeController.createEmployee(employee);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(employee, response.getBody());

        verify(employeeService).create(any(Employee.class));
    }

    @Test
    public void testGetEmployee() {
        // Given
        String employeeId = "123";
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setFirstName("John");
        employee.setLastName("Doe");

        when(employeeService.read(eq(employeeId))).thenReturn(employee);

        // When
        ResponseEntity<Employee> response = employeeController.getEmployee(employeeId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(employee, response.getBody());

        verify(employeeService).read(eq(employeeId));
    }

    @Test
    public void testUpdateEmployee() {
        // Given
        String employeeId = "123";
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setDepartment("Engineering");
        employee.setPosition("Developer");

        when(employeeService.update(any(Employee.class))).thenReturn(employee);

        // When
        ResponseEntity<Employee> response = employeeController.updateEmployee(employeeId, employee);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(employee, response.getBody());

        verify(employeeService).update(any(Employee.class));
    }

    @Test
    public void testGetReportingStructure() {
        // Given
        String employeeId = "123";
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setFirstName("John");
        employee.setLastName("Doe");

        when(employeeService.read(eq(employeeId))).thenReturn(employee);

        // When
        ResponseEntity<ReportingStructure> response = employeeController.getReportingStructure(employeeId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(employeeId, response.getBody().getEmployee().getEmployeeId());

        verify(employeeService).read(eq(employeeId));
    }

    @Test
    public void testCreateCompensation() {
        // Given
        String employeeId = "123";
        Double salary = 50000.0;
        String effectiveDate = "2023-07-01";
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);

        Compensation createdCompensation = new Compensation(employee, salary, effectiveDate);
        when(employeeService.read(eq(employeeId))).thenReturn(employee);
        when(compensationService.create(any(Compensation.class))).thenReturn(createdCompensation);

        // When
        ResponseEntity<Compensation> response = employeeController.createCompensation(employeeId, salary, effectiveDate);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(createdCompensation, response.getBody());

        verify(employeeService).read(eq(employeeId));
        verify(compensationService).create(any(Compensation.class));
    }

    @Test
    public void testGetCompensation() {
        // Given
        String employeeId = "123";
        Double salary = 50000.0;
        String effectiveDate = "2023-07-01";
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        Compensation compensation = new Compensation(employee, salary, effectiveDate);
        when(compensationService.read(eq(employeeId))).thenReturn(compensation);

        // When
        ResponseEntity<Compensation> response = employeeController.getCompensation(employeeId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(compensation, response.getBody());

        verify(compensationService).read(eq(employeeId));
    }
}
