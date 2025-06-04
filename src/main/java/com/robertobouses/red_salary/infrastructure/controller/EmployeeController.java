package com.robertobouses.red_salary.infrastructure.controller;

import com.robertobouses.red_salary.application.service.EmployeeService;
import com.robertobouses.red_salary.domain.model.Employee;
import com.robertobouses.red_salary.domain.model.Employee.Status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    // @GetMapping
    // public ResponseEntity<Iterable<Employee>> getAllEmployees() {
    //     Iterable<Employee> employees = employeeService.getAllEmployees();
    //     return new ResponseEntity<>(employees, HttpStatus.OK);
    // }

    public static class CreateEmployeeRequest {
        public String name;
        public String lastName;
        public String email;
        public String photoUrl;
        public String jobTitle;
        public Long departmentId;
        public Long roleId;
        public UUID jobCategoryId; 
        public String bankAccount;
        public Status status;
        public BigDecimal grossAnnualSalary;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody CreateEmployeeRequest request) {
        Employee employee = employeeService.createEmployee(
            request.name,
            request.lastName,
            request.email,
            request.photoUrl,
            request.jobTitle,
            request.departmentId,
            request.roleId,
            request.jobCategoryId, 
            request.bankAccount,
            request.status,
            request.grossAnnualSalary
        );
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
}
