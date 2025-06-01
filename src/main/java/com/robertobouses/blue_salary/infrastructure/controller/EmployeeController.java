package com.robertobouses.red_salary.infrastructure.controller;

import com.robertobouses.red_salary.application.service.EmployeeService;
import com.robertobouses.red_salary.domain.model.Department;
import com.robertobouses.red_salary.domain.model.Employee;
import com.robertobouses.red_salary.domain.model.Role;
import com.robertobouses.red_salary.domain.model.Employee.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping
    public ResponseEntity<Iterable<Employee>> getAllEmployees() {
        Iterable<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    public static class CreateEmployeeRequest {
        public String name;
        public String lastName;
        public String email;
        public String photoUrl;
        public String jobTitle;
        public Long departmentId;
        public Long roleId;
        public String bankAccount;
        public Status status;
    }
public Long getDepartmentId() { return departmentId; }
    public Long getRoleId()       { return roleId; }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody CreateEmployeeRequest request) {
        Employee employee = employeeService.createEmployee(
                request.name,
                request.lastName,
                request.email,
                request.photoUrl,
                request.jobTitle,
                request.department,
                request.role,
                request.bankAccount,
                request.status
        );
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
}
