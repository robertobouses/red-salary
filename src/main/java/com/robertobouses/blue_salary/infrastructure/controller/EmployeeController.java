package com.robertobouses.blue_salary.infrastructure.controller;

import com.robertobouses.blue_salary.application.service.EmployeeService;
import com.robertobouses.blue_salary.domain.model.Employee;
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
    // DTO para crear empleado
    public static class CreateEmployeeRequest {
        public String name;
        public String lastName;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody CreateEmployeeRequest request) {
        Employee employee = employeeService.createEmployee(request.name, request.lastName);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
}
