package com.robertobouses.blue_salary.application.service;

import com.robertobouses.blue_salary.domain.model.Employee;
import com.robertobouses.blue_salary.infrastructure.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(String name, String lastName) {
        Employee employee = new Employee(UUID.randomUUID(), name, lastName);
        return employeeRepository.save(employee);
    }
    
    public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
}
}